package com.nguyendungdeveloper.foodyversion20.Model;

public class SachChon {
    int id;
    int soluong;
    boolean chon;

    public SachChon(int id, int soluong, boolean chon) {
        this.id = id;
        this.soluong = soluong;
        this.chon = chon;
    }
    public SachChon(int id)
    {
        this.id = id;
        this.soluong = 0;
        this.chon = false;
    }

    public void setId(int id) {
        this.id = id;
    }

    public void setSoluong(int soluong) {
        this.soluong = soluong;
    }

    public void setChon(boolean chon) {
        this.chon = chon;
    }

    public int getId() {

        return id;
    }

    public int getSoluong() {
        return soluong;
    }

    public boolean isChon() {
        return chon;
    }
}
