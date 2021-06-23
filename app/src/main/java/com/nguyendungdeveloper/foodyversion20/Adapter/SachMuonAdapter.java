package com.nguyendungdeveloper.foodyversion20.Adapter;

import android.content.Context;
import android.content.Intent;
import android.media.Image;
import android.net.Uri;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.CompoundButton;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.nguyendungdeveloper.foodyversion20.*;

import com.nguyendungdeveloper.foodyversion20.Activity.Activity_QuanLySach;
import com.nguyendungdeveloper.foodyversion20.Activity.Activity_SuaSach;
import com.nguyendungdeveloper.foodyversion20.Activity.Activity_ThongTinSach;
import com.nguyendungdeveloper.foodyversion20.Model.Sach;
import com.nguyendungdeveloper.foodyversion20.Model.SachChon;
import com.nguyendungdeveloper.foodyversion20.controller.Controller_Sach;
import com.nguyendungdeveloper.foodyversion20.controller.DownloadImageTask;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by BinhPC on 20/03/2018.
 */

public class SachMuonAdapter extends ArrayAdapter<Sach> {

    private Context context;
    private int resource;
    private  List<Sach> arrSach;
    private List<SachChon> sachmuons;

    public List<SachChon> getSachmuons() {
        return sachmuons;
    }

    public SachMuonAdapter(Context context, int resource, List<Sach> objects) {
        super(context, resource, objects);
        this.context=context;
        this.resource=resource;
        this.arrSach=objects;
        sachmuons=new ArrayList<>();
    }

    public void setSachmuons(List<SachChon> sachmuons) {
        this.sachmuons = sachmuons;
    }

    @NonNull
    @Override
    public View getView(final int position, View convertView, ViewGroup parent) {

        final ViewHolder viewHolder;
        if(convertView==null)
        {
            viewHolder=new ViewHolder();
            convertView= LayoutInflater.from(context).inflate(R.layout.item_muonsach,parent,false);
            viewHolder.imgsach=(ImageView)convertView.findViewById(R.id.imgitemmuon);
            viewHolder.txttensach=(TextView) convertView.findViewById(R.id.txtitemmuontensach);
            viewHolder.txttacgia=(TextView)convertView.findViewById(R.id.txtitemmuontacgia);
            viewHolder.txtgia=(TextView)convertView.findViewById(R.id.txtitemgia);
            viewHolder.btngiam=(Button) convertView.findViewById(R.id.btniteammuongiam);
            viewHolder.btntang=(Button) convertView.findViewById(R.id.btnitemmuontang);
            viewHolder.cbitem=(CheckBox) convertView.findViewById(R.id.cbiteammuon);
            viewHolder.edsoluong=(EditText) convertView.findViewById(R.id.edititemmuon);
            convertView.setTag(viewHolder);
        }
        else
        {
            viewHolder=(ViewHolder)convertView.getTag();
        }

        final Sach Sach=arrSach.get(position);
        viewHolder.txttensach.setText(Sach.getTenSach());
        viewHolder.txttacgia.setText(Sach.getTacGia());
        viewHolder.txtgia.setText(String.valueOf(Sach.getTien())+"đ");
        new DownloadImageTask(viewHolder.imgsach).execute(Controller_Sach.LocalURl+Sach.getImg());
//        if(Sach.ismAvarta())
//        {
//            viewHolder.imgAvatart.setBackgroundResource(R.drawable.male);
//            Image a;
//
//        }
//        else
//        {
//            viewHolder.imgAvatart.setBackgroundResource(R.drawable.female);
//        }
        viewHolder.btntang.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                TangSL(viewHolder.edsoluong,Sach.getSoLuong());
                int sl=Integer.parseInt(viewHolder.edsoluong.getText().toString());
                sachmuons.get(position).setSoluong(sl);
            }
        });
        viewHolder.btngiam.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
               GiamSL(viewHolder.edsoluong);
                int sl=Integer.parseInt(viewHolder.edsoluong.getText().toString());
                sachmuons.get(position).setSoluong(sl);
            }
        });
        viewHolder.cbitem.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                if(isChecked==true)
                {
                    sachmuons.get(position).setChon(true);
                }
                else
                {
                    sachmuons.get(position).setChon(false);
                }
            }
        });



        return convertView;
    }

    public class ViewHolder
    {
        ImageView imgsach;
        TextView txttensach;
        TextView txttacgia;
        TextView txtgia;
        Button btngiam;
        Button btntang;
        CheckBox cbitem;
        EditText edsoluong;
    }
    public void TangSL(EditText edit,int max)
    {
        int sl=Integer.parseInt(edit.getText().toString());
        if(sl<max) {
            sl = sl + 1;
            edit.setText(String.valueOf(sl));
        }
        else {
            Toast.makeText(context,"Sách trong kho đã hết",Toast.LENGTH_SHORT).show();
        }

    }
    public void GiamSL(EditText edit)
    {
        int sl=Integer.parseInt(edit.getText().toString());
        if(sl>0)
        {
            sl=sl-1;
            edit.setText(String.valueOf(sl));
        }

    }
    public void intentmess(String chuoi)
    {
        Intent intent= new Intent();
        context.startActivity(new Intent(Intent.ACTION_VIEW, Uri.fromParts("sms", chuoi, null)));

    }

}
