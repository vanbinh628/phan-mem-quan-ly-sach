package com.nguyendungdeveloper.foodyversion20.Adapter;

import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import android.support.annotation.NonNull;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.nguyendungdeveloper.foodyversion20.Model.KhachHang;
import com.nguyendungdeveloper.foodyversion20.Model.Sach;
import com.nguyendungdeveloper.foodyversion20.R;
import com.nguyendungdeveloper.foodyversion20.controller.Controller_Sach;
import com.nguyendungdeveloper.foodyversion20.controller.DownloadImageTask;

import java.util.List;

public class NhapKhoAdapter extends ArrayAdapter<Sach> {

    private Context context;
    private int resource;
    private  List<Sach> arrSach;
    public NhapKhoAdapter(Context context, int resource, List<Sach> objects) {
        super(context, resource, objects);
        this.context=context;
        this.resource=resource;
        this.arrSach=objects;
    }

    @NonNull
    @Override
    public View getView(int position, View convertView, ViewGroup parent) {

        ViewHolder viewHolder;
        if(convertView==null)
        {
            viewHolder=new ViewHolder();
            convertView= LayoutInflater.from(context).inflate(R.layout.item_khachhang,parent,false);
            viewHolder.imgAvatart=(ImageView)convertView.findViewById(R.id.imagitemkh);
            viewHolder.tvName=(TextView)convertView.findViewById(R.id.txtitemkhten);
            viewHolder.tvNumber=(TextView)convertView.findViewById(R.id.txtitemkhdienthoai);
            convertView.setTag(viewHolder);
        }
        else
        {
            viewHolder=(ViewHolder)convertView.getTag();
        }

        Sach sach=arrSach.get(position);
        viewHolder.tvName.setText(sach.getTenSach());
        viewHolder.tvNumber.setText(sach.getTacGia());
        final String number=viewHolder.tvNumber.getText().toString().trim();
        new DownloadImageTask(viewHolder.imgAvatart).execute(Controller_Sach.LocalURl+sach.getImg());

        return convertView;
    }

    public class ViewHolder
    {
        ImageView imgAvatart;
        TextView tvName;
        TextView tvNumber;
    }
    public void intentcall(String chuoi)
    {
        Intent intent= new Intent();
        intent.setAction(Intent.ACTION_CALL);
        intent.setData(Uri.parse("tel:"+chuoi));
        context.startActivity(intent);
    }
    public void intentmess(String chuoi)
    {
        Intent intent= new Intent();
        context.startActivity(new Intent(Intent.ACTION_VIEW, Uri.fromParts("sms", chuoi, null)));
    }
}
