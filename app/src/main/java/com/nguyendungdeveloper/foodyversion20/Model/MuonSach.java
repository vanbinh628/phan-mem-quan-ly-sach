package com.nguyendungdeveloper.foodyversion20.Model;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

public class MuonSach {
    int id;
    int idsach;
    int idkh;
    int soluong;
    Date ngaymuon;
    Date hantra;
    public String layNgayMuon()
    {
        SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd");
        return formatter.format(ngaymuon);
    }
    public String layHanTra()
    {
        SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd");
        return formatter.format(hantra);
    }
    public MuonSach(int idsach, int idkh, int soluong)
    {
        this.id = idsach;
        this.idsach = idsach;
        this.idkh = idkh;
        this.soluong = soluong;
    }
    public MuonSach(int idsach, int idkh, int soluong, Date ngaymuon, Date ngaytra) {
        this.id = idsach;
        this.idsach = idsach;
        this.idkh = idkh;
        this.soluong = soluong;
        this.ngaymuon = ngaymuon;
        this.hantra = ngaytra;
    }
    public MuonSach()
    {
        this.id = -1;
        this.idsach = -1;
        this.idkh =-1;
        this.soluong = 0;
        this.ngaymuon = null;
        this.hantra = null;
    }

    public void setId(int id) {
        this.id = id;
    }

    public void setIdsach(int idsach) {
        this.idsach = idsach;
    }

    public void setIdkh(int idkh) {
        this.idkh = idkh;
    }

    public void setSoluong(int soluong) {
        this.soluong = soluong;
    }

    public void setNgaymuon(String ngaymuon) {
        SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd");
        Date date = null;
        try {
            date = formatter.parse(ngaymuon);
            this.ngaymuon =date;
        } catch (ParseException e) {
            e.printStackTrace();
            this.ngaymuon=null;
        }

    }
    public void setHanTra(Date hantra)
    {
        this.hantra=hantra;
    }
    public void setNgaymuon(Date ngaymuon)
    {
        this.ngaymuon=ngaymuon;
    }
    public void setHanTra(String ngaytra) {
        SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd");
        Date date = null;
        try {
            date = formatter.parse(ngaytra);
            this.hantra =date;
        } catch (ParseException e) {
            e.printStackTrace();
            this.hantra=null;
        }
    }

    public int getId() {

        return id;
    }

    public int getIdsach() {
        return idsach;
    }

    public int getIdkh() {
        return idkh;
    }

    public int getSoluong() {
        return soluong;
    }

    public Date getNgaymuon() {
        return ngaymuon;
    }

    public Date getHanTra() {
        return hantra;
    }
}
