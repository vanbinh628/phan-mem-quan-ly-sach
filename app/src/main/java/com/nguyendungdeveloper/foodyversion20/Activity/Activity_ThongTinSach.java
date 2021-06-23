package com.nguyendungdeveloper.foodyversion20.Activity;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
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

import java.util.HashMap;
import java.util.Map;

public class Activity_ThongTinSach extends AppCompatActivity {
    public static final String VaLUESACH="valuesach";
    TextView txttensach,txttacgia,txtloai,txtngaynhap,txtvitri,txtsoluong,txtTien;
    ImageView imagsach;
    Button btnxoa;
    Button btnsua;
    int id=0;
    String URL="sach/deletesach.php";
    Controller_Sach controller_sach;
    String strtensach="Tên sách: ", strtacgia="Tên tác gia: ",strsoluon="Số Lượng: ",strloai="Thể loại: ",strvitri="Vị trí: ",strngaynhap="Ngày Nhập: ",strtien="Tiền Thuê: ";
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.layout_thongtinsach);
        KhoiTao();
        Intent intent=getIntent();
        final Sach sach=(Sach)intent.getSerializableExtra(Activity_QuanLySach.VaLUESACH);
        LayThongTin(sach);
        btnxoa.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                VolleyDelete(controller_sach.LocalURl+URL,sach.getId());

            }
        });
        btnsua.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                guiBundle(sach);
            }
        });

    }
    void guiBundle(Sach sach)
    {
        Intent intent=new Intent(Activity_ThongTinSach.this,Activity_SuaSach.class);
        intent.putExtra(VaLUESACH, sach);
        this.startActivity(intent);
    }
    public void VolleyDelete (String url, final int idxoa) {
        RequestQueue requestQueue= Volley.newRequestQueue(this);
        StringRequest strRequest = new StringRequest(Request.Method.POST, url,
                new Response.Listener<String>() {
                    @Override
                    public void onResponse(String response) {
                        if(response.trim().equals("success"))
                        {
                            Toast.makeText(Activity_ThongTinSach.this,"Xóa thành công",Toast.LENGTH_SHORT).show();
                            startActivity(new Intent(Activity_ThongTinSach.this,Activity_QuanLySach.class));
                        }
                        else
                        {
                            Toast.makeText(Activity_ThongTinSach.this,"Lỗi xóa không thành công query",Toast.LENGTH_SHORT).show();
                            return;
                        }
                    }
                },
                new Response.ErrorListener() {
                    @Override
                    public void onErrorResponse(VolleyError volleyerro) {
                        Toast.makeText(Activity_ThongTinSach.this,"loi",Toast.LENGTH_SHORT).show();
                        return;
                    }
                }){

            @Override
            protected Map<String, String> getParams()
                    throws AuthFailureError {
                Map<String, String> params = new HashMap<>();
                params.put("id",String.valueOf(idxoa));
                return params;
            }
        };
        requestQueue.add(strRequest);//add
    }
    private void KhoiTao()
    {
        txttensach=(TextView)findViewById(R.id.txttensach_tt);
        txttacgia=(TextView)findViewById(R.id.txttacgia_tt);
        txtloai=(TextView)findViewById(R.id.txtloai_tt);
        txtngaynhap=(TextView)findViewById(R.id.txtngaynnhap_tt);
        txtvitri=(TextView)findViewById(R.id.txtvitri_tt);
        txtsoluong=(TextView)findViewById(R.id.txtsoluon_tt);
        txtTien=(TextView)findViewById(R.id.txttien_tt);
        imagsach=(ImageView)findViewById(R.id.imagesach_tt);
        btnxoa=(Button)findViewById(R.id.btnxoa_tt);
        btnsua=(Button)findViewById(R.id.btnsua_tt);

    }
    private void LayThongTin(Sach sach)
    {
        txttensach.setText(strtensach+sach.getTenSach());
        txttacgia.setText(strtacgia+sach.getTacGia());
        txtloai.setText(strloai+sach.getLoai());
        txtngaynhap.setText(strngaynhap+sach.getNgayNhap());
        txtsoluong.setText(strsoluon+sach.getSoLuong());
        txtvitri.setText(strvitri+sach.getViTri());
        txtTien.setText(strtien+sach.getTien()+" đ");
        new DownloadImageTask(imagsach).execute(Controller_Sach.LocalURl+sach.getImg());
    }

}
