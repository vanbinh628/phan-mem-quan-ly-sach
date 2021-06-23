package com.nguyendungdeveloper.foodyversion20;


import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.widget.ListView;

import com.nguyendungdeveloper.foodyversion20.Adapter.SachAdapter;
import com.nguyendungdeveloper.foodyversion20.Model.Sach;
import com.nguyendungdeveloper.foodyversion20.controller.Controller_Sach;

import java.util.ArrayList;
import java.util.List;


public class MainActivity extends AppCompatActivity {
    ListView lv;
    private List<Sach> arrayContact;
    private SachAdapter adapter;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.layout_themsuasach);
//        ConnectionHelper connectionHelper=new ConnectionHelper();
//        System.out.println(connectionHelper.connectionclasss());
//        System.out.println("hello");
        Sach abc=new Sach();
        abc.setId(3);
        abc.setTenSach("mùa thu rơi");
        Controller_Sach controller_sach=new Controller_Sach();
        System.out.println(controller_sach.themList(abc));
        System.out.println(Controller_Sach.list_Sach.get(3).getTenSach());
//        lv=(ListView)findViewById(R.id.lvdanhmucsach);
//        arrayContact=new ArrayList<Sach>();
//        adapter=new SachAdapter(this,R.layout.item_danhmucsach,arrayContact);
//        lv.setAdapter(adapter);
//
//        Controller_Sach contro = new Controller_Sach();
//        contro.CoSan();
//       arrayContact=Controller_Sach.list_Sach;
//        adapter=new SachAdapter(this,R.layout.item_danhmucsach,arrayContact);
//        Sach a=new Sach();
//        a.setTenSach("Oc Chó");
////        for(int i=0;i<Controller_Sach.list_Sach.size();i++)
////        {
////            arrayContact.add(Controller_Sach.list_Sach.get(i));
////        }
//        adapter.notifyDataSetChanged();


    }


}
