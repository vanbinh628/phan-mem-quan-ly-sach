package com.nguyendungdeveloper.foodyversion20.Activity;

import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;
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
import com.nguyendungdeveloper.foodyversion20.Adapter.KhachHangAdapter;
import com.nguyendungdeveloper.foodyversion20.Adapter.NhapKhoAdapter;
import com.nguyendungdeveloper.foodyversion20.Model.KhachHang;
import com.nguyendungdeveloper.foodyversion20.Model.Sach;
import com.nguyendungdeveloper.foodyversion20.R;
import com.nguyendungdeveloper.foodyversion20.controller.Controller_Sach;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class Activity_NhapKho extends AppCompatActivity {
    final Context context = this;
    private Button button;
    private EditText result;

    public static final String VaLUEKH="valuekh";
    ListView lv;
    TextView txtthem;
    private List<Sach> arrayContact;
    private NhapKhoAdapter adapter;
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.layout_khachhang);
        lv=(ListView)findViewById(R.id.lvdanhmucsach);
        arrayContact=new ArrayList<Sach>();
        adapter=new NhapKhoAdapter(this,R.layout.item_khachhang,arrayContact);

        lv.setAdapter(adapter);
        ReadJson(Controller_Sach.LocalURl+"sach/getsach.php");
        lv.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                int idsach=arrayContact.get(position).getId();
                Dialog_Them(view,idsach);

            }
        });

    }
    void guiBundle(KhachHang kh)
    {
        Intent intent=new Intent(Activity_NhapKho.this,Activity_ThongTinKH.class);
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
                Toast.makeText(Activity_NhapKho.this,error.toString(),Toast.LENGTH_LONG).show();

            }
        }

        );
        requestQueue.add(jsonArrayRequest);
    }
    public void Dialog_Them(View arg0, final int idsach) {

        // get prompts.xml view
        LayoutInflater li = LayoutInflater.from(context);
        View promptsView = li.inflate(R.layout.dialog_them, null);

        AlertDialog.Builder alertDialogBuilder = new AlertDialog.Builder(
                context);

        // set prompts.xml to alertdialog builder
        alertDialogBuilder.setView(promptsView);

        final EditText userInput = (EditText) promptsView
                .findViewById(R.id.editTextDialogUserInput);

        // set dialog message
        alertDialogBuilder
                .setCancelable(false)
                .setPositiveButton("OK",
                        new DialogInterface.OnClickListener() {
                            public void onClick(DialogInterface dialog,int id) {
                                // get user input and set it to result
                                // edit text
                                if(userInput.getText().toString().equals("")==false) {
                                    int soluong = Integer.parseInt(userInput.getText().toString());
                                    VolleyUpdate(Controller_Sach.LocalURl+"sach/updatenhapkho.php",idsach,soluong);
                                }
                                else {
                                    Toast.makeText(context,"Nhập số lượng",Toast.LENGTH_SHORT).show();
                                }
                            }
                        })
                .setNegativeButton("Cancel",
                        new DialogInterface.OnClickListener() {
                            public void onClick(DialogInterface dialog,int id) {
                                dialog.cancel();
                            }
                        });

        // create alert dialog
        AlertDialog alertDialog = alertDialogBuilder.create();

        // show it
        alertDialog.show();

    }
    public void VolleyUpdate (String url, final int id, final int soluong) {
        RequestQueue requestQueue= Volley.newRequestQueue(this);
        StringRequest strRequest = new StringRequest(Request.Method.POST, url,
                new Response.Listener<String>() {
                    @Override
                    public void onResponse(String response) {
                        if(response.trim().equals("success"))
                        {
                            Toast.makeText(Activity_NhapKho.this,"Sửa thành công",Toast.LENGTH_SHORT).show();
                        }
                        else
                        {
                            Toast.makeText(Activity_NhapKho.this,"Lỗi Sửa không thành công query",Toast.LENGTH_SHORT).show();
                            return;
                        }
                    }
                },
                new Response.ErrorListener() {
                    @Override
                    public void onErrorResponse(VolleyError volleyerro) {
                        Toast.makeText(Activity_NhapKho.this,"Lỗi kết nối",Toast.LENGTH_SHORT).show();
                        return;
                    }
                }){

            @Override
            protected Map<String, String> getParams()
                    throws AuthFailureError {
                Map<String, String> params = new HashMap<>();
                params.put("id",String.valueOf(id));
                params.put("soluong",String.valueOf(soluong));
                return params;
            }
        };
        requestQueue.add(strRequest);//add
    }
}