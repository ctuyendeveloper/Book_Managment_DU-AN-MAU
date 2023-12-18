package com.example.duanmauf_asm.models;

public class ThuThu {
    private int maTT;
    private String tenTT;
    private String matKhau;

    public int getMaTT() {
        return maTT;
    }

    public void setMaTT(int maTT) {
        this.maTT = maTT;
    }

    public String getTenTT() {
        return tenTT;
    }

    public void setTenTT(String tenTT) {
        this.tenTT = tenTT;
    }

    public String getMatKhau() {
        return matKhau;
    }

    public void setMatKhau(String matKhau) {
        this.matKhau = matKhau;
    }

    public ThuThu(int maTT, String tenTT, String matKhau) {
        this.maTT = maTT;
        this.tenTT = tenTT;
        this.matKhau = matKhau;
    }
}
