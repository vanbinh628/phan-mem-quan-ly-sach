package com.nguyendungdeveloper.foodyversion20.Activity;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.nguyendungdeveloper.foodyversion20.R;


public class Activity_Main extends AppCompatActivity {
    LinearLayout linequanlysach,linethemsach,linelichsu,linechomuonsach;
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.layout_main);
        KhoiTao();
        linethemsach.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(Activity_Main.this, Activity_NhapKho.class);
                Activity_Main.this.startActivity(intent);
            }
        });
        linequanlysach.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(Activity_Main.this, Activity_QuanLySach.class);
                Activity_Main.this.startActivity(intent);
            }
        });
//        linelichsu.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {
//                Intent intent = new Intent(Activity_Main.this, Activity_ThemSach.class);
//                Activity_Main.this.startActivity(intent);
//            }
//        });
        linechomuonsach.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(Activity_Main.this, Activity_QuanLyKH.class);
                Activity_Main.this.startActivity(intent);
            }
        });

    }
    protected void KhoiTao()
    {
        linequanlysach=(LinearLayout) findViewById(R.id.linequanlysach);
        linelichsu=(LinearLayout) findViewById(R.id.linelichsu);
        linechomuonsach=(LinearLayout) findViewById(R.id.linechomuonsach);
        linethemsach=(LinearLayout) findViewById(R.id.linethemsach);

    }
    public void ChuyenIntent(Class b) {
        Intent intent = new Intent(Activity_Main.this, b.getClass());
        Activity_Main.this.startActivity(intent);
    }
}