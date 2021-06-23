package com.nguyendungdeveloper.foodyversion20.Activity;

import android.app.DatePickerDialog;
import android.app.Dialog;
import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.DialogFragment;
import android.support.v4.app.FragmentManager;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.Toast;

import com.android.volley.AuthFailureError;
import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonArrayRequest;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;
import com.nguyendungdeveloper.foodyversion20.Adapter.SachMuonAdapter;
import com.nguyendungdeveloper.foodyversion20.Adapter.SachTraAdapter;
import com.nguyendungdeveloper.foodyversion20.Model.MuonSach;
import com.nguyendungdeveloper.foodyversion20.Model.Sach;
import com.nguyendungdeveloper.foodyversion20.controller.Controller_Sach;
import com.nguyendungdeveloper.foodyversion20.Model.SachChon;
import com.nguyendungdeveloper.foodyversion20.R;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;
import java.util.HashMap;
import java.util.List;
import java.util.Map;


public class Activity_ListChonTraSach extends AppCompatActivity implements DatePickerDialog.OnDateSetListener{
    String URL="http://192.168.28.2/sach/insertmuon.php";
    ListView lv;
    Button btnmuon;
    EditText edithantra;
    private List<Sach> arrayContact;
    private SachTraAdapter adapter;
    private int idkh=-1;

    @Override
    protected void onStart() {
        super.onStart();
        ReadJson("http://192.168.28.2/sach/getsachsmuon.php",idkh);
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.layout_chonsachmuon);
        lv=(ListView)findViewById(R.id.lvchonsachmuon);
        btnmuon=(Button)findViewById(R.id.btnmuon_muon);
        edithantra=(EditText)findViewById(R.id.edithantra_muon);
        btnmuon.setText("Trả Sách");
        arrayContact=new ArrayList<Sach>();
        adapter=new SachTraAdapter(this,R.layout.item_muonsach,arrayContact);
        lv.setAdapter(adapter);
        Intent intent=getIntent();
        Bundle bundle=intent.getBundleExtra(Activity_ThongTinKH.BUNDEL);//khai báo nhận
        idkh=bundle.getInt(Activity_ThongTinKH.VALUEID);//đưa giá trị vào cái gì đó
        ReadJson("http://192.168.28.2/sach/getsachsmuon.php",idkh);


        btnmuon.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                LaySachChon();
            }
        });
        edithantra.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                datePicker(v);
            }
        });
    }
    private void LaySachChon()
    {
        List<MuonSach> muonSaches=new ArrayList<>();
        for(int i=0;i<adapter.getSachmuons().size();i++)
        {
            if(adapter.getSachmuons().get(i).isChon()) {
                SachChon sachChon = adapter.getSachmuons().get(i);
                MuonSach muonSach = new MuonSach(sachChon.getId(), idkh, sachChon.getSoluong());
                muonSaches.add(muonSach);
                if(muonSach.getSoluong()>0)
                {
                    VolleyUpdate(Controller_Sach.LocalURl+"sach/updatemuon.php",muonSach);
                }
                else
                {
                    VolleyDelete(Controller_Sach.LocalURl+"sach/deletemuon.php",muonSach.getId());
                }
            }
        }
        finish();

//        MuonSach muonSach=new MuonSach();
//        muonSach.setHanTra(edithantra.getText().toString());
//        muonSach.setNgaymuon(new java.util.Date());
//        SimpleDateFormat formatter = new SimpleDateFormat("dd-MM-yyyy");
//        System.out.println(formatter.format(muonSach.getHanTra()));
//        System.out.println(formatter.format(muonSach.getNgaymuon()));
    }
    public void VolleyUpdate (String url, final MuonSach muonsach) {
        RequestQueue requestQueue= Volley.newRequestQueue(this);
        StringRequest strRequest = new StringRequest(Request.Method.POST, url,
                new Response.Listener<String>() {
                    @Override
                    public void onResponse(String response) {
                        if(response.trim().equals("success"))
                        {
                            Toast.makeText(Activity_ListChonTraSach.this,"Sửa thành công",Toast.LENGTH_SHORT).show();
                        }
                        else
                        {
                            Toast.makeText(Activity_ListChonTraSach.this,"Lỗi Sửa không thành công query",Toast.LENGTH_SHORT).show();
                            return;
                        }
                    }
                },
                new Response.ErrorListener() {
                    @Override
                    public void onErrorResponse(VolleyError volleyerro) {
                        Toast.makeText(Activity_ListChonTraSach.this,"Lỗi kết nối",Toast.LENGTH_SHORT).show();
                        return;
                    }
                }){

            @Override
            protected Map<String, String> getParams()
                    throws AuthFailureError {
                Map<String, String> params = new HashMap<>();
                params.put("id",String.valueOf(muonsach.getIdsach()));
                params.put("soluong",String.valueOf(muonsach.getSoluong()));
                return params;
            }
        };
        requestQueue.add(strRequest);//add
    }
    public void VolleyDelete (String url, final int idmuonsach) {
        RequestQueue requestQueue= Volley.newRequestQueue(this);
        StringRequest strRequest = new StringRequest(Request.Method.POST, url,
                new Response.Listener<String>() {
                    @Override
                    public void onResponse(String response) {
                        if(response.trim().equals("success"))
                        {
                            Toast.makeText(Activity_ListChonTraSach.this,"xóa thành công",Toast.LENGTH_SHORT).show();
                        }
                        else
                        {
                            Toast.makeText(Activity_ListChonTraSach.this,"Lỗi xóa không thành công query",Toast.LENGTH_SHORT).show();
                            return;
                        }
                    }
                },
                new Response.ErrorListener() {
                    @Override
                    public void onErrorResponse(VolleyError volleyerro) {
                        Toast.makeText(Activity_ListChonTraSach.this,"Lỗi kết nối",Toast.LENGTH_SHORT).show();
                        return;
                    }
                }){

            @Override
            protected Map<String, String> getParams()
                    throws AuthFailureError {
                Map<String, String> params = new HashMap<>();
                params.put("id",String.valueOf(idmuonsach));
                return params;
            }
        };
        requestQueue.add(strRequest);//add
    }
    public void ReadJson (String url, final int id_kh) {
        RequestQueue requestQueue=Volley.newRequestQueue(this);
        StringRequest strRequest = new StringRequest(Request.Method.POST, url,
                new Response.Listener<String>() {
                    @Override
                    public void onResponse(String response) {
                        System.out.println(response);
                        JSONArray jsonArr = null;
                        try {
                            jsonArr = new JSONArray(response);
                        } catch (JSONException e) {
                            e.printStackTrace();
                        }
                        arrayContact.clear();
                        for (int i = 0; i < jsonArr.length(); i++)
                        {
                            JSONObject object= null;
                            try {
                                object = jsonArr.getJSONObject(i);
                                arrayContact.add(new Sach(
                                        object.getInt("Id"),
                                        object.getString("Img"),
                                        object.getString("TenSach"),
                                        object.getString("TacGia"),
                                        object.getString("Loai"),
                                        object.getString("NgayNhap"),
                                        object.getString("ViTri"),
                                        object.getInt("SoLuong"),
                                        object.getLong("Tien")
                                ));
                                adapter.getSachmuons().add(new SachChon(object.getInt("Id")));
                            } catch (JSONException e) {
                                e.printStackTrace();
                            }


                        }
                        adapter.notifyDataSetChanged();
                    }

                }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError volleyerro) {
                Toast.makeText(Activity_ListChonTraSach.this,"loi",Toast.LENGTH_SHORT).show();
                return;
            }
        }){

            @Override
            protected Map<String, String> getParams()
                    throws AuthFailureError {
                Map<String, String> params = new HashMap<>();
                params.put("idkh",String.valueOf(id_kh));
                return params;
            }
        };
        requestQueue.add(strRequest);//add
    }

    public void datePicker(View view){

        FragmentManager fm = this.getSupportFragmentManager();
        Activity_SuaSach.DatePickerFragment dialog = new Activity_SuaSach.DatePickerFragment();
        dialog.show(fm, "date");
    }

    /**
     * To set date on TextView
     * @param calendar
     */
    private void setDate(final Calendar calendar) {
        SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd");
        String s = formatter.format(calendar.getTime());

                edithantra.setText(s);

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

