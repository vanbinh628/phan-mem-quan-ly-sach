package com.nguyendungdeveloper.foodyversion20.controller;

import com.nguyendungdeveloper.foodyversion20.Model.Sach;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.List;

public class Controller_Sach {
    public static String LocalURl="http://192.168.28.2/";
    public static   List<Sach> list_Sach=new ArrayList<>();
    public Controller_Sach()
    {
    }
    public void Xoa()
    {
        list_Sach=new ArrayList<>();
    }
    public void CoSan()
    {
        Sach a=new Sach();
        a.setId(0);
        a.setTenSach("Hoa vàng trên cỏ xanh");
        a.setTacGia("Nguyễn Nhật Ánh");
        a.setLoai("Truyện ngắn");
        a.setNgayNhap("2/2/2018");
        Sach b=new Sach();
        b.setId(1);
        b.setTenSach("Chỉ còn lại");
        b.setTacGia("Nguyễn Nhật");
        b.setLoai("Truyện ngắn");
        Sach c=new Sach();
        c.setId(2);
        c.setTenSach("I love you");
        c.setTacGia("Nguyễn Ánh");
        c.setLoai("Truyện ngắn");
        list_Sach.add(a);
        list_Sach.add(b);
        list_Sach.add(c);
    }
    public boolean themList(Sach a)
    {
        try {
            list_Sach.add(a);
            return true;
        }
        catch (Exception e)
        {
            System.out.println(e);
            return false;
        }

    }
    public Sach GetSachById(int id)
    {
        for (Sach sach:list_Sach )
        {
            if(sach.getId()==id)
                return sach;
        }
        return null;
    }
    public void InSachById(int id)
    {
        for (Sach sach:list_Sach )
        {
            if(sach.getId()==id) {
                System.out.println(sach.getId());
                System.out.println(sach.getTenSach());
                System.out.println(sach.getTacGia());
                System.out.println(sach.getLoai());
                System.out.println(sach.getSoLuong());
                return;
            }
        }
        System.out.println("Khong co");
    }
    public boolean SuaSachById(Sach sach)
    {
        for (int i=0;i<list_Sach.size();i++)
        {
            if(list_Sach.get(i).getId()==sach.getId())
                list_Sach.remove(i);
                list_Sach.add(i,sach);
                return true;
        }
        return false;
    }
    public boolean DeleteSachById(int id)
    {
        for (Sach sach:list_Sach )
        {
            if(sach.getId()==id) {
                list_Sach.remove(sach);
                return true;
            }
        }
        System.out.println("khong co de xoa");
        return false;
    }

}
