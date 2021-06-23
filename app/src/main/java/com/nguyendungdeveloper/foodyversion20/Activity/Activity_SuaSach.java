package com.nguyendungdeveloper.foodyversion20.Activity;

import android.app.DatePickerDialog;
import android.app.Dialog;
import android.app.ProgressDialog;
import android.graphics.Bitmap;
import android.net.Uri;
import android.os.AsyncTask;
import android.provider.MediaStore;
import android.support.v4.app.DialogFragment;
import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.FragmentManager;
import android.support.v7.app.AppCompatActivity;
import android.util.Base64;
import android.view.View;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.ImageView;
import android.support.v4.app.DialogFragment;
import android.widget.TextView;
import android.widget.Toast;

import com.android.volley.AuthFailureError;
import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;
import com.nguyendungdeveloper.foodyversion20.Model.Sach;
import com.nguyendungdeveloper.foodyversion20.R;
import com.nguyendungdeveloper.foodyversion20.controller.Controller_Sach;
import com.nguyendungdeveloper.foodyversion20.controller.DownloadImageTask;
import com.nguyendungdeveloper.foodyversion20.controller.RequestHandler;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;
import java.util.HashMap;
import java.util.Map;

public class Activity_SuaSach extends AppCompatActivity implements DatePickerDialog.OnDateSetListener{
    public static final String UPLOAD_URL = "http://192.168.28.2/sach/updateupload.php";
    private int PICK_IMAGE_REQUEST = 1;

    private Bitmap bitmap;
    private Uri filePath;

    EditText edittensach, editloai, edittacgia, editngaynhap, editvitri, editsoluong,edittien;
    ImageView anhsach;
    TextView txtchoose;
    Controller_Sach controller_sach;
    Intent intent;
    int id=0;
    String URL="sach/updatesach.php";
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.layout_themsuasach);
        KhoiTao();
        intent=getIntent();
//        Bundle bundle=intent.getBundleExtra(Activity_QuanLySach.BUNDEL);//khai báo nhận
//        id=bundle.getInt(Activity_QuanLySach.VaLUEID);//đưa giá trị vào cái gì đó
        Sach sach=(Sach)intent.getSerializableExtra(Activity_QuanLySach.VaLUESACH);
        id=sach.getId();
        LayThongTin(sach);
        editngaynhap.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                datePicker(v);
            }
        });
        txtchoose.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                showFileChooser();
            }
        });
    }
//    protected boolean KiemTraKhongTrong(String edit1,String edit2,String edit3)
//    {
//        if(edit1.trim()==""||edit2.trim()=="")
//        {
//            return  false;
//        }
//        if(edit3)
//
//    }
    protected void KhoiTao()
    {
        edittensach=(EditText)findViewById(R.id.edittensach);
        editloai=(EditText)findViewById(R.id.editloai);
        edittacgia=(EditText)findViewById(R.id.edittacgia);
        editngaynhap=(EditText)findViewById(R.id.editngaynhap);
        editvitri=(EditText)findViewById(R.id.editvitri);
        editsoluong=(EditText)findViewById(R.id.editsoluong);
        anhsach=(ImageView)findViewById(R.id.img_sach_item_them);
        edittien=(EditText)findViewById(R.id.edittien);
        txtchoose=(TextView)findViewById(R.id.txtchoose);
    }
    protected void LayThongTin(Sach sach)
    {
        edittensach.setText(sach.getTenSach());
        edittacgia.setText(sach.getTacGia());
        editloai.setText(sach.getLoai());
            editngaynhap.setText(sach.getNgayNhap().toString());
        if(sach.getImg().equals("")==false) {
            new DownloadImageTask(anhsach).execute(Controller_Sach.LocalURl+sach.getImg());
        }

        editvitri.setText(sach.getViTri());
        editsoluong.setText(String.valueOf(sach.getSoLuong()));
        edittien.setText(String.valueOf(sach.getSoLuong()));
    }
    protected Sach GanSach(int idsach)
    {
        Sach sach=new Sach();
        sach.setId(idsach);
        sach.setTenSach(edittensach.getText().toString());
        sach.setLoai(editloai.getText().toString());
        //String startDateString=  editngaynhap.getText().toString();
//        DateFormat df = new SimpleDateFormat("dd/MMM/yyyy");
//        try {
//            sach.setNgayNhap(df.parse(startDateString));
//        } catch (ParseException e) {
//            e.printStackTrace();
//            sach.setNgayNhap(null);
//        }
        sach.setNgayNhap(editngaynhap.getText().toString());

        // sach.setNgayNhap(DateFormat. editngaynhap.getText().toString());
        sach.setViTri(editvitri.getText().toString());
        if(editsoluong.getText().toString().equals(""))
            sach.setSoLuong(0);

        else sach.setSoLuong(Integer.parseInt(editsoluong.getText().toString()));
        if(edittien.getText().toString().equals(""))
            sach.setTien(0);
        else  sach.setTien(Long.parseLong(edittien.getText().toString()));
        return sach;
    }
    public void OnClickLuu(View v) {
        Sach sach=GanSach(id);
        if(sach.getTenSach().equals(""))
        {
            Toast.makeText(Activity_SuaSach.this,"Tên không để trống",Toast.LENGTH_SHORT).show();
            return;
        }
        if(sach.getNgayNhap().equals(""))
        {
            Toast.makeText(Activity_SuaSach.this,"Ngày nhập không để trống",Toast.LENGTH_SHORT).show();
            return;
        }
        if(sach.getSoLuong()<=0||editsoluong.getText().toString().equals(""))
        {
            Toast.makeText(Activity_SuaSach.this,"Số lượng phải lớn hơn 0",Toast.LENGTH_SHORT).show();
            return;
        }
        if(sach.getTien()<0||edittien.getText().toString().equals(""))
        {
            Toast.makeText(Activity_SuaSach.this,"Tiền thuê không để trống",Toast.LENGTH_SHORT).show();
            return;
        }
        if(sach.getImg().equals("")==false||anhsach.getDrawable().getConstantState()==getResources().getDrawable( R.drawable.notimage).getConstantState())
        {
            VolleyUpdate(Controller_Sach.LocalURl+URL,sach);
        }
        else {
            UpdateuploadImage(sach);
        }
    }

        public void VolleyUpdate (String url, final Sach sach) {
            RequestQueue requestQueue= Volley.newRequestQueue(this);
            StringRequest strRequest = new StringRequest(Request.Method.POST, url,
                    new Response.Listener<String>() {
                        @Override
                        public void onResponse(String response) {
                            if(response.trim().equals("success"))
                            {
                                Toast.makeText(Activity_SuaSach.this,"Sửa thành công",Toast.LENGTH_SHORT).show();
                                finish();
                            }
                            else
                            {
                                Toast.makeText(Activity_SuaSach.this,"Lỗi Sửa không thành công query",Toast.LENGTH_SHORT).show();
                                return;
                            }
                        }
                    },
                    new Response.ErrorListener() {
                        @Override
                        public void onErrorResponse(VolleyError volleyerro) {
                            Toast.makeText(Activity_SuaSach.this,"Lỗi kết nối",Toast.LENGTH_SHORT).show();
                            return;
                        }
                    }){

                @Override
                protected Map<String, String> getParams()
                        throws AuthFailureError {
                    Map<String, String> params = new HashMap<>();
                    params.put("id",String.valueOf(sach.getId()));
                    params.put("img",sach.getImg());
                    params.put("tensach",sach.getTenSach());
                    params.put("tacgia",sach.getTacGia());
                    params.put("loai",sach.getLoai());
                    params.put("ngaynhap",sach.getNgayNhap());
                    params.put("vitri",sach.getViTri());
                    params.put("soluong",String.valueOf(sach.getSoLuong()));
                    params.put("tien",String.valueOf(sach.getTien()));
                    return params;
                }
            };
            requestQueue.add(strRequest);//add
        }
    private void showFileChooser() {
        Intent intent = new Intent();
        intent.setType("image/*");
        intent.setAction(Intent.ACTION_GET_CONTENT);
        startActivityForResult(Intent.createChooser(intent, "Select Picture"), PICK_IMAGE_REQUEST);
    }//gọi intent

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);

        if (requestCode == PICK_IMAGE_REQUEST && resultCode == RESULT_OK && data != null && data.getData() != null) {

            filePath = data.getData();
            try {
                bitmap = MediaStore.Images.Media.getBitmap(getContentResolver(), filePath);
                anhsach.setImageBitmap(bitmap);
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }

    public String getStringImage(Bitmap bmp){
        ByteArrayOutputStream baos = new ByteArrayOutputStream();
        bmp.compress(Bitmap.CompressFormat.JPEG, 100, baos);
        byte[] imageBytes = baos.toByteArray();
        String encodedImage = Base64.encodeToString(imageBytes, Base64.DEFAULT);
        return encodedImage;
    }

    private void UpdateuploadImage(final Sach sach){
        class UploadImage extends AsyncTask<Bitmap,Void,String> {

            ProgressDialog loading;
            RequestHandler rh = new RequestHandler();

            @Override
            protected void onPreExecute() {
                super.onPreExecute();
                loading = ProgressDialog.show(Activity_SuaSach.this, "Uploading...", null,true,true);
            }

            @Override
            protected void onPostExecute(String s) {
                super.onPostExecute(s);
                loading.dismiss();
                Toast.makeText(getApplicationContext(),s,Toast.LENGTH_LONG).show();
            }

            @Override
            protected String doInBackground(Bitmap... params) {
                Bitmap bitmap = params[0];
                String uploadImage = getStringImage(bitmap);

                HashMap<String,String> data = new HashMap<>();
                data.put("id",String.valueOf(sach.getId()));
                data.put("image", uploadImage);
                data.put("tensach",sach.getTenSach());
                data.put("tacgia",sach.getTacGia());
                data.put("loai",sach.getLoai());
                data.put("ngaynhap",sach.getNgayNhap());
                data.put("vitri",sach.getViTri());
                data.put("soluong",String.valueOf(sach.getSoLuong()));
                data.put("tien",String.valueOf(sach.getTien()));
                String result = rh.sendPostRequest(UPLOAD_URL,data);

                return result;
            }
        }

        UploadImage ui = new UploadImage();
        ui.execute(bitmap);
    }
    /**
     * To set date on TextView
     * @paramcalendar
     */
    public void datePicker(View view){

        FragmentManager fm = this.getSupportFragmentManager();
        DatePickerFragment dialog = new DatePickerFragment();
        dialog.show(fm, "date");
    }

    /**
     * To set date on TextView
     * @param calendar
     */
    private void setDate(final Calendar calendar) {
        SimpleDateFormat formatter = new SimpleDateFormat("dd-MM-yyyy");
        String s = formatter.format(calendar.getTime());

        ((EditText) findViewById(R.id.editngaynhap))
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
