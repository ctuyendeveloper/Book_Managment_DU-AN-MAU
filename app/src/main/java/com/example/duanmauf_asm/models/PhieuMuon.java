package com.example.duanmauf_asm.models;

public class PhieuMuon {
    private int maPM;
    private int maTV;
    private String matt;
    private int masach;
    private String tenTV;
    private String tenSach;
    private String ngay;
    private int traSach;
    private int tienThue;

    public PhieuMuon(int maPM, String tenTV, String tenSach, String ngay, int traSach, int tienThue) {
        this.maPM = maPM;
//        this.maTV = maTV;
//        this.matt = matt;
//        this.masach = masach;
        this.tenTV = tenTV;
        this.tenSach = tenSach;
        this.ngay = ngay;
        this.traSach = traSach;
        this.tienThue = tienThue;
    }
    public PhieuMuon(int matv, String matt, int masach, String ngay, int trasach, int tienthue) {
        this.maTV = matv;
        this.matt = matt;
        this.masach = masach;
        this.ngay = ngay;
        this.traSach = trasach;
        this.tienThue = tienthue;
    }


    public int getMaPM() {
        return maPM;
    }

    public void setMaPM(int maPM) {
        this.maPM = maPM;
    }

    public int getMaTV() {
        return maTV;
    }

    public void setMaTV(int maTV) {
        this.maTV = maTV;
    }

    public String getMatt() {
        return matt;
    }

    public void setMatt(String matt) {
        this.matt = matt;
    }

    public int getMasach() {
        return masach;
    }

    public void setMasach(int masach) {
        this.masach = masach;
    }

    public String getTenTV() {
        return tenTV;
    }

    public void setTenTV(String tenTV) {
        this.tenTV = tenTV;
    }

    public String getTenSach() {
        return tenSach;
    }

    public void setTenSach(String tenSach) {
        this.tenSach = tenSach;
    }

    public String getNgay() {
        return ngay;
    }

    public void setNgay(String ngay) {
        this.ngay = ngay;
    }

    public int getTraSach() {
        return traSach;
    }

    public void setTraSach(int traSach) {
        this.traSach = traSach;
    }

    public int getTienThue() {
        return tienThue;
    }

    public void setTienThue(int tienThue) {
        this.tienThue = tienThue;
    }
}
