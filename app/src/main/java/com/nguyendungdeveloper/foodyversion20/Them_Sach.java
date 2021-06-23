package com.nguyendungdeveloper.foodyversion20;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Toast;

import com.nguyendungdeveloper.foodyversion20.Model.Sach;
import com.nguyendungdeveloper.foodyversion20.R;

public class Them_Sach extends AppCompatActivity {
    EditText edittensach, editloai, edittacgia, editngaynhap, editvitri, editsoluong;
    ImageView anhsach;

    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.layout_themsuasach);
        KhoiTao();
    }
    protected void KhoiTao()
    {
        edittensach=(EditText)findViewById(R.id.edittensach);
        editloai=(EditText)findViewById(R.id.editloai);
        edittacgia=(EditText)findViewById(R.id.edittacgia);
        editngaynhap=(EditText)findViewById(R.id.editngaynhap);
        editvitri=(EditText)findViewById(R.id.editvitri);
        editsoluong=(EditText)findViewById(R.id.editsoluong);
        anhsach=(ImageView)findViewById(R.id.img_sach_item_them);
    }
    public void OnClickLuu(View v) {
        int id = 0;
        String tensach = edittensach.getText().toString();
        String loai = editloai.getText().toString();
        String tacgia = edittacgia.getText().toString();
        String ngaynhap = editngaynhap.getText().toString();
        String vitri = editvitri.getText().toString();
        int soluong;
        if (editsoluong.getText().toString().trim() != "") {
            soluong = Integer.parseInt(editsoluong.getText().toString());
        } else {
            soluong = 0;
        }

        //Sach sach = new Sach(id, "", tensach, loai, tacgia, ngaynhap, vitri, soluong);
        //System.out.println(sach.getTenSach());

    }


}