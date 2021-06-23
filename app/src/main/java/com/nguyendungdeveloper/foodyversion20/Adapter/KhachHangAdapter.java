package com.nguyendungdeveloper.foodyversion20.Adapter;

import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import android.support.annotation.NonNull;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import com.nguyendungdeveloper.foodyversion20.R;
import com.nguyendungdeveloper.foodyversion20.Model.KhachHang;

import java.util.List;

public class KhachHangAdapter extends ArrayAdapter<KhachHang> {

    private Context context;
    private int resource;
    private List<KhachHang> arrKhachHang;
    public KhachHangAdapter(Context context,int resource, List<KhachHang> objects) {
        super(context, resource, objects);
        this.context=context;
        this.resource=resource;
        this.arrKhachHang=objects;
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

        KhachHang KhachHang=arrKhachHang.get(position);
        viewHolder.tvName.setText(KhachHang.getTen());
        viewHolder.tvNumber.setText(KhachHang.getDienthoai());
        final String number=viewHolder.tvNumber.getText().toString().trim();
        if(KhachHang.getGioitinh()==1)
        {
            viewHolder.imgAvatart.setBackgroundResource(R.mipmap.ic_male);

        }
        else
        {
            viewHolder.imgAvatart.setBackgroundResource(R.mipmap.ic_female);
        }

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
