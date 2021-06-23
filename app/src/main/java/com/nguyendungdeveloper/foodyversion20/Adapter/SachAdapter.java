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
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.nguyendungdeveloper.foodyversion20.*;

import com.nguyendungdeveloper.foodyversion20.Activity.Activity_QuanLySach;
import com.nguyendungdeveloper.foodyversion20.Activity.Activity_SuaSach;
import com.nguyendungdeveloper.foodyversion20.Model.Sach;
import com.nguyendungdeveloper.foodyversion20.controller.Controller_Sach;
import com.nguyendungdeveloper.foodyversion20.controller.DownloadImageTask;

import java.util.List;

/**
 * Created by BinhPC on 20/03/2018.
 */

public class SachAdapter extends ArrayAdapter<Sach> {
    private Context context;
    private int resource;
    private  List<Sach> arrSach;
    public SachAdapter(Context context,int resource, List<Sach> objects) {
        super(context, resource, objects);
        this.context=context;
        this.resource=resource;
        this.arrSach=objects;
    }

    @NonNull
    @Override
    public View getView(final int position, View convertView, ViewGroup parent) {

        ViewHolder viewHolder;
        if(convertView==null)
        {
            viewHolder=new ViewHolder();
            convertView= LayoutInflater.from(context).inflate(R.layout.item_danhmucsach,parent,false);
            viewHolder.imgAvatart=(ImageView)convertView.findViewById(R.id.imagitemSach);
            viewHolder.tvtensach=(TextView)convertView.findViewById(R.id.tvitemtensach);
            viewHolder.tvtacgia=(TextView)convertView.findViewById(R.id.tvitemtacgia);
            viewHolder.btnSua=(ImageView) convertView.findViewById(R.id.btnitemsua);
            convertView.setTag(viewHolder);
        }
        else
        {
            viewHolder=(ViewHolder)convertView.getTag();
        }

        Sach Sach=arrSach.get(position);
        viewHolder.tvtensach.setText(Sach.getTenSach());
        viewHolder.tvtacgia.setText(Sach.getTacGia());
        final String number=viewHolder.tvtacgia.getText().toString().trim();
        new DownloadImageTask(viewHolder.imgAvatart).execute(Controller_Sach.LocalURl+Sach.getImg());
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
        viewHolder.btnSua.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Sach sachchon= arrSach.get(position);
                IntentSua(sachchon);
                System.out.println("oc cho");
            }
        });


        return convertView;
    }

    public class ViewHolder
    {
        ImageView imgAvatart;
        TextView tvtensach;
        TextView tvtacgia;
        ImageView btnSua;
    }
    public void IntentSua(Sach sach)
    {
            Intent intent=new Intent(getContext(),Activity_SuaSach.class);
//            Bundle bundle=new Bundle();bundle.putInt(Activity_QuanLySach.VaLUEID,id);
//            intent.putExtra(Activity_QuanLySach.BUNDEL, bundle);
//            this.context.startActivity(intent);
//            System.out.println(intent.getBundleExtra(Activity_QuanLySach.BUNDEL).getInt(Activity_QuanLySach.VaLUEID));
            intent.putExtra(Activity_QuanLySach.VaLUESACH,sach);
            this.context.startActivity(intent);
    }
    public void intentmess(String chuoi)
    {
        Intent intent= new Intent();
        context.startActivity(new Intent(Intent.ACTION_VIEW, Uri.fromParts("sms", chuoi, null)));

    }
}
