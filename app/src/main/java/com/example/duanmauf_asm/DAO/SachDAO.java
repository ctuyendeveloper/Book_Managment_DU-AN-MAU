package com.example.duanmauf_asm.DAO;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

import com.example.duanmauf_asm.database.Db;
import com.example.duanmauf_asm.models.Sach;

import java.util.ArrayList;

public class SachDAO {
    Db db;
    public SachDAO(Context context){
        db = new Db(context);
    }
    public ArrayList<Sach> getDSDauSach(){
        ArrayList<Sach> list = new ArrayList<>();
        SQLiteDatabase sqLiteDatabase = db.getReadableDatabase();
        Cursor cursor = sqLiteDatabase.rawQuery("SELECT sc.MaSach, sc.TenSach,sc.maloai, sc.GiaThue, lo.TenLoai FROM SACH sc, LOAISACH lo WHERE sc.MaLoai = lo.MaLoai", null);
        if (cursor.getCount() != 0){
            cursor.moveToFirst();
            do {
                list.add(new Sach(cursor.getInt(0), cursor.getString(1), cursor.getInt(2), cursor.getInt(3),cursor.getString(4)));

            }while (cursor.moveToNext());
        }
        return list;
    }
//    public Sach(int maSach, String tenSach, int maLoai, int giaThue, String tenLoai) {
//        this.maSach = maSach;
//        this.tenSach = tenSach;
//        this.maLoai = maLoai;
//        this.giaThue = giaThue;
//        this.tenLoai = tenLoai;
//    }
    public boolean themSachMoi(String tensach, int giatien, int maloai){
        SQLiteDatabase sqLiteDatabase = db.getWritableDatabase();
        ContentValues contentValues = new ContentValues();
        contentValues.put("tensach", tensach);
        contentValues.put("giathue", giatien);
        contentValues.put("maloai", maloai);
        long check = sqLiteDatabase.insert("SACH", null, contentValues);
        if (check == -1)
            return false;
        return true;
    }

    public boolean capNhatThongTinSach(int masach, String tensach, int giathue, int maloai){
        SQLiteDatabase sqLiteDatabase = db.getWritableDatabase();
        ContentValues contentValues = new ContentValues();
        contentValues.put("TenSach", tensach);
        contentValues.put("GiaThue", giathue);
        contentValues.put("MaLoai", maloai);
        long check = sqLiteDatabase.update("SACH", contentValues, "MaSach = ?", new String[]{String.valueOf(masach)});
        if (check == -1)
            return false;
        return true;
    }
    public int xoaSach(int masach){
        SQLiteDatabase sqLiteDatabase = db.getWritableDatabase();
        Cursor cursor = sqLiteDatabase.rawQuery("SELECT * FROM PHIEUMUON WHERE MaSach = ?", new String[]{String.valueOf(masach)});
        if (cursor.getCount() != 0) {
            return -1;
        }

        long check = sqLiteDatabase.delete("SACH", "MaSach = ?", new String[]{String.valueOf(masach)});
        if (check == -1)
            return 0;
        return 1;
    }
}
