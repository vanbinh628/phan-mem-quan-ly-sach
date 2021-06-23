package com.nguyendungdeveloper.foodyversion20.Activity;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Adapter;
import android.widget.ArrayAdapter;
import android.widget.BaseAdapter;
import android.widget.Button;
import android.widget.GridView;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.android.volley.AuthFailureError;
import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonArrayRequest;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;
import com.nguyendungdeveloper.foodyversion20.Model.KhachHang;
import com.nguyendungdeveloper.foodyversion20.Model.Sach;
import com.nguyendungdeveloper.foodyversion20.Model.SachChon;
import com.nguyendungdeveloper.foodyversion20.R;
import com.nguyendungdeveloper.foodyversion20.controller.Controller_Sach;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class Activity_ThongTinKH extends AppCompatActivity {
    public static final String VALUEID ="valueip" ;
    public static final String BUNDEL ="bundel" ;
    String URL="sach/getsach.php";
    TextView txtten,txtgoitinh,txtngaysinh,txtdiachi,txtdienthoai;
    Button btnmuon;
    Button btntra;
    ImageView imag;
    GridView griddata;
    int idkh=-1;
    ArrayAdapter<String> adapter;
    Controller_Sach controller_sach;
    ArrayList<String> items;
    String strten="Tên: ", strgioitinh="Giới tính: ",strngsinh="ngày sinh: ",strdiachi="địa chỉ: ",strdienthoai="dienthoai: ";

    @Override
    protected void onStart() {
        super.onStart();
        items.clear();
        ReadJson(Controller_Sach.LocalURl+"sach/getmuon.php",idkh);

    }

    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.layout_khachhang_muon);
        KhoiTao();
//        Intent intent=getIntent();
//        Bundle bundle=intent.getBundleExtra(Activity_QuanLySach.BUNDEL);//khai báo nhận
        Intent intent=getIntent();
        final KhachHang kh=(KhachHang) intent.getSerializableExtra(Activity_QuanLyKH.VaLUEKH);
        idkh=kh.getId();
        if (kh==null)
            return;
        LayThongTin(kh);
        items=new ArrayList<>();
//        items= ReadJson(Controller_Sach.LocalURl+"sach/getmuon.php");
        adapter=new ArrayAdapter(this,android.R.layout.simple_list_item_1,items);
        griddata.setAdapter(adapter);
        onStart();
        btnmuon.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                guiBundle(kh.getId());
            }
        });
        btntra.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent=new Intent(Activity_ThongTinKH.this,Activity_ListChonTraSach.class);
                Bundle bundle=new Bundle();
                bundle.putInt(VALUEID, kh.getId());
                intent.putExtra(BUNDEL, bundle);
                startActivity(intent);
            }
        });


    }
    private void guiBundle(int idkh)
    {
        Intent intent=new Intent(Activity_ThongTinKH.this,Activity_ListChonSach.class);
        Bundle bundle=new Bundle();
        bundle.putInt(VALUEID, idkh);
        intent.putExtra(BUNDEL, bundle);
        this.startActivity(intent);
    }

    private void KhoiTao()
    {
        txtten=(TextView)findViewById(R.id.txttenkh_tt);
        txtgoitinh=(TextView)findViewById(R.id.txtgioitinh_tt);
        txtngaysinh=(TextView)findViewById(R.id.txtngaysinh_tt);
        txtdiachi=(TextView)findViewById(R.id.txtdiachi_tt);
        txtdienthoai=(TextView)findViewById(R.id.txtdienthoai_tt);
        imag=(ImageView)findViewById(R.id.imagkh);
        btnmuon=(Button)findViewById(R.id.btnmuon);
        btntra=(Button)findViewById(R.id.btntra);
        griddata=(GridView)findViewById(R.id.griddata);
    }
    private void LayThongTin(KhachHang kh)
    {
        txtten.setText(strten+kh.getTen());
        if(kh.getGioitinh()==1) {
            txtgoitinh.setText(strgioitinh + "Nam");
            imag.setImageResource(R.mipmap.ic_male);
        }
        else {
            txtgoitinh.setText(strgioitinh + "Nữ");
            imag.setImageResource(R.mipmap.ic_female);
        }
        txtngaysinh.setText(strngsinh+kh.getNgaysinh());
        txtdiachi.setText(strdiachi+kh.getDiachi());
        txtdienthoai.setText(strdienthoai+kh.getDienthoai());

    }
    public void ReadJson (String url, final int id_kh) {
        RequestQueue requestQueue=Volley.newRequestQueue(this);
        StringRequest strRequest = new StringRequest(Request.Method.POST, url,
                new Response.Listener<String>() {
                    @Override
                    public void onResponse(String response) {
                        System.out.println(response);
                        items.clear();
                        JSONArray jsonArr = null;
                        try {
                            jsonArr = new JSONArray(response);
                        } catch (JSONException e) {
                            e.printStackTrace();
                        }

                        for (int i = 0; i < jsonArr.length(); i++)
                        {
                            JSONObject object= null;
                            try {
                                object = jsonArr.getJSONObject(i);
                                items.add(object.getString("TenSach"));
                                items.add(object.getString("SoLuong"));
                                items.add(object.getString("SoTien"));
                                items.add(object.getString("NgayMuon"));
                                items.add(object.getString("HanTra"));
                            } catch (JSONException e) {
                                e.printStackTrace();
                            }


                        }
                        adapter.notifyDataSetChanged();
                    }

                }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError volleyerro) {
                Toast.makeText(Activity_ThongTinKH.this,"loi",Toast.LENGTH_SHORT).show();
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

    private static final int ROW_ITEMS = 5;

    private static final class GridAdapter extends BaseAdapter {

        final ArrayList<String> mItems;
        final int mCount;

        /**
         * Default constructor
         * @param items to fill data to
         */
        private GridAdapter(final ArrayList<String> items) {

            mCount = items.size() * ROW_ITEMS;
            mItems = new ArrayList<String>(mCount);

            // for small size of items it's ok to do it here, sync way
            for (String item : items) {
                // get separate string parts, divided by ,
                final String[] parts = item.split(",");

                // remove spaces from parts
                for (String part : parts) {
                    part.replace(" ", "");
                    mItems.add(part);
                }
            }
        }

        @Override
        public int getCount() {
            return mCount;
        }

        @Override
        public Object getItem(final int position) {
            return mItems.get(position);
        }

        @Override
        public long getItemId(final int position) {
            return position;
        }

        @Override
        public View getView(final int position, final View convertView, final ViewGroup parent) {

            View view = convertView;

            if (view == null) {
                view = LayoutInflater.from(parent.getContext()).inflate(android.R.layout.simple_list_item_1, parent, false);
            }

            final TextView text = (TextView) view.findViewById(android.R.id.text1);

            text.setText(mItems.get(position));

            return view;
        }
    }


}

