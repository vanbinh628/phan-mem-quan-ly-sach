package com.nguyendungdeveloper.foodyversion20.Activity;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.KeyEvent;
import android.view.View;
import android.view.inputmethod.EditorInfo;
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
import com.nguyendungdeveloper.foodyversion20.Adapter.SachAdapter;
import com.nguyendungdeveloper.foodyversion20.Model.Sach;
import com.nguyendungdeveloper.foodyversion20.controller.Controller_Sach;
import com.nguyendungdeveloper.foodyversion20.R;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class Activity_QuanLySach extends AppCompatActivity {
    public static final String VaLUEID="valueid";
    public static final String VaLUESACH="valuesach";
    public static final String BUNDEL="bundel";
    ListView lv;
    TextView txtthem;
    EditText edittimkiem;
    private List<Sach> arrayContact;
    private SachAdapter adapter;
    protected void onStart() {
        super.onStart();
        arrayContact.clear();
        ReadJson(Controller_Sach.LocalURl+"sach/getsach.php");
    }
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.layout_danhmucsach);
        lv=(ListView)findViewById(R.id.lvdanhmucsach);
        txtthem=(TextView)findViewById(R.id.txtthem);
        edittimkiem=(EditText)findViewById(R.id.edittimkiem_qls);
        arrayContact=new ArrayList<Sach>();
        adapter=new SachAdapter(this,R.layout.item_danhmucsach,arrayContact);
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
                Intent intent=new Intent(Activity_QuanLySach.this,Activity_ThemSach.class);
                startActivity(intent);
            }
        });

        edittimkiem.setOnEditorActionListener(new TextView.OnEditorActionListener() {
            public boolean onEditorAction(TextView v, int actionId, KeyEvent event) {
                if ((event != null && (event.getKeyCode() == KeyEvent.KEYCODE_ENTER)) || (actionId == EditorInfo.IME_ACTION_DONE)) {
                    //do what you want on the press of 'done'
                    String tensach=edittimkiem.getText().toString();
                    VolleyTimKiem(Controller_Sach.LocalURl+"sach/timkiemsach.php",tensach);
                }
                return false;
            }
        });
//        Controller_Sach contro = new Controller_Sach();
//        contro.CoSan();
//        for(int i=0;i<Controller_Sach.list_Sach.size();i++)
//        {
//            arrayContact.add(Controller_Sach.list_Sach.get(i));
//        }


    }
//    void guiBundle(int id)
//    {
//        Intent intent=new Intent(Activity_QuanLySach.this,Activity_ThongTinSach.class);
//        Bundle bundle=new Bundle();
//        bundle.putInt(VaLUEID,id);
//        intent.putExtra(BUNDEL, bundle);
//        this.startActivity(intent);
//    }
void guiBundle(Sach sach)
{
    Intent intent=new Intent(Activity_QuanLySach.this,Activity_ThongTinSach.class);
    intent.putExtra(VaLUESACH, sach);
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
                Toast.makeText(Activity_QuanLySach.this,error.toString(),Toast.LENGTH_LONG).show();

            }
        }

        );
        requestQueue.add(jsonArrayRequest);
    }
    public void VolleyTimKiem (String url, final String tensach) {
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
                            } catch (JSONException e) {
                                e.printStackTrace();
                            }


                        }
                        adapter.notifyDataSetChanged();
                    }

                }, new Response.ErrorListener() {
                    @Override
                    public void onErrorResponse(VolleyError volleyerro) {
                        Toast.makeText(Activity_QuanLySach.this,"loi",Toast.LENGTH_SHORT).show();
                        return;
                    }
                }){

            @Override
            protected Map<String, String> getParams()
                    throws AuthFailureError {
                Map<String, String> params = new HashMap<>();
                params.put("tensach",tensach);
                return params;
            }
        };
        requestQueue.add(strRequest);//add
    }

}
