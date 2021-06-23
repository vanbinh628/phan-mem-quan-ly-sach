package com.nguyendungdeveloper.foodyversion20.Model;

import java.io.Serializable;

public class KhachHang implements Serializable {
    int id;
    String ten;
    int gioitinh;
    String ngaysinh;
    String diachi;
    String dienthoai;

    public void setDienthoai(String dienthoai) {
        this.dienthoai = dienthoai;
    }

    public String getDienthoai() {

        return dienthoai;
    }

    public void setId(int id) {
        this.id = id;
    }

    public void setTen(String ten) {
        this.ten = ten;
    }

    public void setGioitinh(int gioitinh) {
        this.gioitinh = gioitinh;
    }

    public void setNgaysinh(String ngaysinh) {
        this.ngaysinh = ngaysinh;
    }

    public void setDiachi(String diachi) {
        this.diachi = diachi;
    }

    public int getId() {

        return id;
    }

    public String getTen() {
        return ten;
    }

    public int getGioitinh() {
        return gioitinh;
    }

    public String getNgaysinh(String s) {
        return ngaysinh;
    }

    public String getDiachi() {
        return diachi;
    }

    public KhachHang(int id, String ten, int gioitinh, String ngaysinh, String diachi,String dienthoai) {

        this.id = id;
        this.ten = ten;
        this.gioitinh = gioitinh;
        this.ngaysinh = ngaysinh;
        this.diachi = diachi;
        this.dienthoai=dienthoai;
    }
    public KhachHang()
    {
        this.id = -1;
        this.ten = "";
        this.gioitinh = 0;
        this.ngaysinh = "";
        this.diachi = "";
        this.dienthoai="";
    }

    public String getNgaysinh() {
        return ngaysinh;
    }
}
