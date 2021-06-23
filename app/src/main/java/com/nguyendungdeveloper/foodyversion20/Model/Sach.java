package com.nguyendungdeveloper.foodyversion20.Model;

import java.io.Serializable;
import java.util.Date;

public class Sach implements Serializable {
    private int id;
    private String img;
    private String tenSach ;
    private String loai;
    private String ngayNhap;
    private String tacGia;
    private String viTri;
    private int soLuong;
    private long tien;
    public Sach()
    {
        this.id = 0;
        this.img = "";
        this.tenSach = "";
        this.loai = "";
        this.tacGia = "";
        this.viTri = "";
        this.soLuong = 0;
        this.ngayNhap=null;
        this.tien=0;
    }



    public Sach(int id, String img, String tenSach, String tacGia, String loai, String ngayNhap, String viTri, int soLuong,long tien) {
        this.id = id;
        this.img = img;
        this.tenSach = tenSach;
        this.loai = loai;
        this.tacGia = tacGia;
        this.viTri = viTri;
        this.soLuong = soLuong;
        this.ngayNhap=ngayNhap;
        this.tien=tien;
    }

    public void setTien(long tien) {
        this.tien = tien;
    }

    public long getTien() {

        return tien;
    }

    public Sach(Sach sach) {
        this.id = sach.id;
        this.img = sach.img;
        this.tenSach = sach.tenSach;
        this.loai = sach.loai;
        this.tacGia = sach.tacGia;
        this.viTri = sach.viTri;
        this.soLuong = sach.soLuong;
        this.ngayNhap=sach.ngayNhap;
        this.tien=sach.tien;

    }

    public void setId(int id) {
        this.id = id;
    }

    public void setImg(String img) {
        this.img = img;
    }

    public void setTenSach(String tenSach) {
        this.tenSach = tenSach;
    }

    public void setLoai(String loai) {
        this.loai = loai;
    }

    public void setTacGia(String tacGia) {
        this.tacGia = tacGia;
    }

    public void setViTri(String viTri) {
        this.viTri = viTri;
    }

    public void setSoLuong(int soLuong) {
        this.soLuong = soLuong;
    }

    public int getId() {
        return id;
    }

    public String getImg() {
        return img;
    }

    public String getTenSach() {
        return tenSach;
    }

    public String getLoai() {
        return loai;
    }

    public String getTacGia() {
        return tacGia;
    }

    public String getViTri() {
        return viTri;
    }

    public int getSoLuong() {
        return soLuong;
    }

    public void setNgayNhap(String ngayNhap) {
        this.ngayNhap = ngayNhap;
    }

    public String getNgayNhap() {

        return ngayNhap;
    }
}
