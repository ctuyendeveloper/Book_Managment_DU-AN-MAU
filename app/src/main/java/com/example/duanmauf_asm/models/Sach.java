package com.example.duanmauf_asm.models;

public class Sach {
    private int maSach;
    private String tenSach;
    private int maLoai;
    private int giaThue;
    private String tenLoai;
    private int soLuongDaMuon;

    public int getMaSach() {
        return maSach;
    }

    public void setMaSach(int maSach) {
        this.maSach = maSach;
    }

    public String getTenSach() {
        return tenSach;
    }

    public void setTenSach(String tenSach) {
        this.tenSach = tenSach;
    }

    public int getGiaThue() {
        return giaThue;
    }

    public void setGiaThue(int giaThue) {
        this.giaThue = giaThue;
    }

    public String getTenLoai() {
        return tenLoai;
    }

    public void setTenLoai(String tenLoai) {
        this.tenLoai = tenLoai;
    }

    public Sach(int maSach, String tenSach, int soLuongDaMuon) {
        this.maSach = maSach;
        this.tenSach = tenSach;
        this.soLuongDaMuon = soLuongDaMuon;
    }

    public int getSoLuongDaMuon() {
        return soLuongDaMuon;
    }

    public void setSoLuongDaMuon(int soLuongDaMuon) {
        this.soLuongDaMuon = soLuongDaMuon;
    }

    public Sach(int maSach, String tenSach, int maLoai, int giaThue, String tenLoai) {
        this.maSach = maSach;
        this.tenSach = tenSach;
        this.maLoai = maLoai;
        this.giaThue = giaThue;
        this.tenLoai = tenLoai;
    }

    public int getMaLoai() {
        return maLoai;
    }

    public void setMaLoai(int maLoai) {
        this.maLoai = maLoai;
    }
}
