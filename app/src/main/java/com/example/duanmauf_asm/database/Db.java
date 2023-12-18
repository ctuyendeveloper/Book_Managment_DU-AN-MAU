package com.example.duanmauf_asm.database;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

public class Db extends SQLiteOpenHelper {
    public Db(Context context) {
        super(context, "PNLIB", null, 1);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        String sql = "CREATE TABLE PHIEUMUON(MaPhieu integer primary key autoincrement" +
                "," + "MaThanhVien integer references THANHVIEN(MaThanhVien)," +
                "MaThuThu text references THUTHU(MaThuThu)," +
                " MaSach text references SACH(MaSach), Ngay text, TienThue integer, TraSach integer)";
        db.execSQL(sql);
        sql = "CREATE TABLE THUTHU(MaThuThu text primary key" + ", HoTen text, MatKhau text, loaiTaiKhoan text)";
        db.execSQL(sql);
        sql = "CREATE TABLE THANHVIEN(MaThanhVien integer primary key autoincrement" + ", HoTenThanhVien text, NamSinh text)";
        db.execSQL(sql);
        sql = "CREATE TABLE LOAISACH(MaLoai integer primary key autoincrement" + ", TenLoai text)";
        db.execSQL(sql);
        sql = "CREATE TABLE SACH(MaSach integer primary key autoincrement" + ", TenSach text, GiaThue float, Maloai integer references LOAISACH(MaLoai))";
        db.execSQL(sql);
        db.execSQL("INSERT INTO LOAISACH VALUES (1, 'Học Tập'),(2,'Truyện Tranh'),(3, 'Truyện Hài')");
        db.execSQL("INSERT INTO SACH VALUES (1,'Sách ABC',10000,1), (2,'Sách CSS',50000,2)");
        db.execSQL("INSERT INTO THUTHU VALUES ('thuthu01','Nguyễn Công Tuyền','123456', 'Admin'), ('thuthu02','Nguyễn Văn Tuấn','1234567', 'Thủ Thư')");
        db.execSQL("INSERT INTO THANHVIEN VALUES (1,'Đỗ Quang Hiếu','2003'),(2,'Nguyễn Bá Lộc','2000'),(3,'Đỗ Trung Hiếu','2009'),(4,'Trần Thái Dũng','1999'),(5,'Nguyễn Công Tuyền','2012')");
        db.execSQL("INSERT INTO PHIEUMUON VALUES (1,1,'thuthu01', 1, '19/05/2022', 90000, 2),(2,2,'thuthu02', 2, '30/05/2022', 20000, 1)");
//        sql = "INSERT INTO PHANLOAI VALUES(null, 'Lương làm thêm', 'Thu')";
//        db.execSQL(sql);
//        sql = "INSERT INTO PHANLOAI VALUES(null, 'Cày thuê', 'Thu')";
//        db.execSQL(sql);
//        sql = "INSERT INTO PHANLOAI VALUES(null, 'Chơi game', 'Chi')";
//        db.execSQL(sql);
//        sql = "CREATE TABLE GIAODICH(MaGD integer primary key autoincrement, Tien float, MaLoai integer references PHANLOAI(MaLoai))";
//        db.execSQL(sql);
//        sql = "INSERT INTO GIAODICH VALUES(null, 1000, 1)";
//        db.execSQL(sql);
//        sql = "INSERT INTO GIAODICH VALUES(null, 500, 2)";
//        db.execSQL(sql);
//        sql = "INSERT INTO GIAODICH VALUES(null, 4500, 3)";
//        db.execSQL(sql);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int i, int i1) {
        if(i != i1){
            String delete = "DROP TABLE IF EXISTS PHIEUMUON";
            db.execSQL(delete);
            delete = "DROP TABLE IF EXISTS THUTHU";
            db.execSQL(delete);
            delete = "DROP TABLE IF EXISTS THANHVIEN";
            db.execSQL(delete);
            delete = "DROP TABLE IF EXISTS LOAISACH";
            db.execSQL(delete);
            delete = "DROP TABLE IF EXISTS SACH";
            db.execSQL(delete);
            onCreate(db);
        }
    }

}
