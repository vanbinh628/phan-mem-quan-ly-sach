package com.nguyendungdeveloper.foodyversion20.Activity;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonArrayRequest;
import com.android.volley.toolbox.Volley;
import com.nguyendungdeveloper.foodyversion20.Adapter.KhachHangAdapter;
import com.nguyendungdeveloper.foodyversion20.Adapter.SachAdapter;
import com.nguyendungdeveloper.foodyversion20.Model.KhachHang;
import com.nguyendungdeveloper.foodyversion20.Model.Sach;
import com.nguyendungdeveloper.foodyversion20.R;
import com.nguyendungdeveloper.foodyversion20.controller.Controller_Sach;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;

public class Activity_QuanLyKH extends AppCompatActivity {
    public static final String VaLUEKH="valuekh";
    ListView lv;
    TextView txtthem;
    private List<KhachHang> arrayContact;
    private KhachHangAdapter adapter;
    @Override
    protected void onStart() {
        super.onStart();
        arrayContact.clear();
        ReadJson(Controller_Sach.LocalURl+"sach/getkhachhang.php");
    }
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.layout_khachhang);
        lv=(ListView)findViewById(R.id.lvdanhmucsach);
        txtthem=(TextView)findViewById(R.id.txtthemkhachhang);
        arrayContact=new ArrayList<KhachHang>();
        adapter=new KhachHangAdapter(this,R.layout.item_khachhang,arrayContact);
        onStart();
        lv.setAdapter(adapter);
        onStart();
        lv.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                int idsach=arrayContact.get(position).getId();
                guiBundle(arrayContact.get(position));

            }
        });
        txtthem.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent=new Intent(Activity_QuanLyKH.this,Activity_ThemKhachHang.class);
                startActivity(intent);
            }
        });
    }
    void guiBundle(KhachHang kh)
    {
        Intent intent=new Intent(Activity_QuanLyKH.this,Activity_ThongTinKH.class);
        intent.putExtra(VaLUEKH,kh);
        this.startActivity(intent);
    }
    private  void ReadJson(String url)
    {
        final RequestQueue requestQueue= Volley.newRequestQueue(this);
        JsonArrayRequest jsonArrayRequest=new JsonArrayRequest(Request.Method.GET, url, null, new Response.Listener<JSONArray>() {
            @Override
            public void onResponse(JSONArray response) {
                //Toast.makeText(MainActivity.this,response.toString(),Toast.LENGTH_LONG).show();
                arrayContact.clear();
                for(int i=0;i<response.length();i++)
                {
                    try {
                        JSONObject object=response.getJSONObject(i);

                        arrayContact.add(new KhachHang(
                                object.getInt("Id"),
                                object.getString("Ten"),
                                object.getInt("GioiTinh"),
                                object.getString("NgaySinh"),
                                object.getString("DiaChi"),
                                object.getString("DienThoai")
                        ));

                    }
                    catch (JSONException e)
                    {
                        e.printStackTrace();
                    }

                }
                adapter.notifyDataSetChanged();
            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                Toast.makeText(Activity_QuanLyKH.this,error.toString(),Toast.LENGTH_LONG).show();

            }
        }

        );
        requestQueue.add(jsonArrayRequest);
    }

}
