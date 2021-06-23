package com.nguyendungdeveloper.foodyversion20.Activity;

import android.app.DatePickerDialog;
import android.app.Dialog;
import android.os.Bundle;
import android.support.v4.app.DialogFragment;
import android.support.v4.app.FragmentManager;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import com.android.volley.AuthFailureError;
import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;
import com.nguyendungdeveloper.foodyversion20.Model.KhachHang;
import com.nguyendungdeveloper.foodyversion20.R;
import com.nguyendungdeveloper.foodyversion20.controller.Controller_Sach;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.GregorianCalendar;
import java.util.HashMap;
import java.util.Map;

public class Activity_ThemKhachHang extends AppCompatActivity implements DatePickerDialog.OnDateSetListener{
    EditText editten, editgioitinh, editngaysinh, editdiachi, editdienthoai;
    String URL="sach/insertkhachhang.php";
    int gioitinh=0;
    String arr[]={
            "Nữ",
            "Nam",};
    Spinner spin;
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.layout_themsuakhachhang);
        //Lấy đối tượng Spinner ra
        KhoiTao();
        //thiết lập sự kiện chọn phần tử cho Spinner
        spin.setOnItemSelectedListener(new MyProcessEvent());
        editngaysinh.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                datePicker(v);
            }
        });

    }
    public void KhoiTao()
    {
        spin=(Spinner) findViewById(R.id.spingioitinhkh);
        //Gán Data source (arr) vào Adapter
        ArrayAdapter<String> adapter=new ArrayAdapter<String>
                (
                        this,
                        android.R.layout.simple_spinner_item,
                        arr);
        //phải gọi lệnh này để hiển thị danh sách cho Spinner
        adapter.setDropDownViewResource
                (android.R.layout.simple_list_item_single_choice);
        //Thiết lập adapter cho Spinner
        spin.setAdapter(adapter);
        editten=(EditText)findViewById(R.id.edittenkh);
        editngaysinh=(EditText)findViewById(R.id.editngaysinhkh);
        editdiachi=(EditText)findViewById(R.id.editdiachi);
        editdienthoai=(EditText)findViewById(R.id.editdienthoai);
    }
    //Class tạo sự kiện
    private class MyProcessEvent implements
            AdapterView.OnItemSelectedListener
    {
        //Khi có chọn lựa thì vào hàm này
        public void onItemSelected(AdapterView<?> arg0,
                                   View arg1,
                                   int arg2,
                                   long arg3) {
            //arg2 là phần tử được chọn trong data source
            if(arr[arg2]=="Nữ")
                gioitinh=0;
            else gioitinh=1;
        }
        //Nếu không chọn gì cả
        public void onNothingSelected(AdapterView<?> arg0) {
        }
    }
    protected KhachHang GanKhachHang()
    {
        KhachHang kh=new KhachHang();
        kh.setTen(editten.getText().toString());
        kh.setGioitinh(gioitinh);
        kh.setNgaysinh(editngaysinh.getText().toString());
        kh.setDiachi(editdiachi.getText().toString());
        kh.getNgaysinh(editngaysinh.getText().toString());

        return kh;
    }
    public void OnClickLuu(View v) {
        KhachHang kh=GanKhachHang();
        if(kh.getTen().equals(""))
        {
            Toast.makeText(Activity_ThemKhachHang.this,"Tên không để trống",Toast.LENGTH_SHORT).show();
            return;
        }
        if(kh.getNgaysinh().equals(""))
        {
            Toast.makeText(Activity_ThemKhachHang.this,"Ngày nhập không để trống",Toast.LENGTH_SHORT).show();
            return;
        }
        VolleyInsert(Controller_Sach.LocalURl+URL,kh);
    }
    public void VolleyInsert (String url, final KhachHang kh) {
        RequestQueue requestQueue= Volley.newRequestQueue(this);
        StringRequest strRequest = new StringRequest(Request.Method.POST, url,
                new Response.Listener<String>() {
                    @Override
                    public void onResponse(String response) {
                        if(response.trim().equals("success"))
                        {
                            Toast.makeText(Activity_ThemKhachHang.this,"Sửa thành công",Toast.LENGTH_SHORT).show();
                            finish();
                        }
                        else
                        {
                            Toast.makeText(Activity_ThemKhachHang.this,"Lỗi Sửa không thành công query",Toast.LENGTH_SHORT).show();
                            return;
                        }
                    }
                },
                new Response.ErrorListener() {
                    @Override
                    public void onErrorResponse(VolleyError volleyerro) {
                        Toast.makeText(Activity_ThemKhachHang.this,"lỗi",Toast.LENGTH_SHORT).show();
                        return;
                    }
                }){

            @Override
            protected Map<String, String> getParams()
                    throws AuthFailureError {
                Map<String, String> params = new HashMap<>();
                params.put("ten",kh.getTen());
                params.put("gioitinh",String.valueOf(kh.getGioitinh()));
                params.put("ngaysinh",kh.getNgaysinh());
                params.put("diachi",kh.getDiachi());
                params.put("dienthoai",kh.getDienthoai());
                return params;
            }
        };
        requestQueue.add(strRequest);//add
    }
    public void datePicker(View view){

        FragmentManager fm = this.getSupportFragmentManager();
        Activity_ThemKhachHang.DatePickerFragment dialog = new Activity_ThemKhachHang.DatePickerFragment();
        dialog.show(fm, "date");
    }

    /**
     * To set date on TextView
     * @param calendar
     */
    private void setDate(final Calendar calendar) {
        SimpleDateFormat formatter = new SimpleDateFormat("dd-MM-yyyy");
        String s = formatter.format(calendar.getTime());

        ((EditText) findViewById(R.id.editngaysinhkh))
                .setText(s);

    }

    /**
     * To receive a callback when the user sets the date.
     * @param view
     * @param year
     * @param month
     * @param day
     */
    @Override
    public void onDateSet(DatePicker view, int year, int month, int day) {
        Calendar cal = new GregorianCalendar(year, month, day);
        setDate(cal);
    }

    /**
     * Create a DatePickerFragment class that extends DialogFragment.
     * Define the onCreateDialog() method to return an instance of DatePickerDialog
     */
    public static class DatePickerFragment extends DialogFragment {

        @Override
        public Dialog onCreateDialog(Bundle savedInstanceState) {
            final Calendar c = Calendar.getInstance();
            int year = c.get(Calendar.YEAR);
            int month = c.get(Calendar.MONTH);
            int day = c.get(Calendar.DATE);


            return new DatePickerDialog(getActivity(),
                    (DatePickerDialog.OnDateSetListener)
                            getActivity(), year, month, day);
        }


    }


}