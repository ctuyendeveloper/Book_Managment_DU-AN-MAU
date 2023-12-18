package com.example.duanmauf_asm.DAO;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

import com.example.duanmauf_asm.database.Db;
import com.example.duanmauf_asm.models.ThanhVien;

import java.util.ArrayList;

public class ThanhVienDAO {
    Db db;
    public ThanhVienDAO(Context context){
        db = new Db(context);
    }
    public ArrayList<ThanhVien> getDSThanhVien(){
        ArrayList<ThanhVien> list = new ArrayList<>();
        SQLiteDatabase sqLiteDatabase = db.getReadableDatabase();
        Cursor cursor = sqLiteDatabase.rawQuery("SELECT * FROM THANHVIEN", null);
        if (cursor.getCount() != 0){
            cursor.moveToFirst();
            do {
                list.add(new ThanhVien(cursor.getInt(0), cursor.getString(1), cursor.getString(2)));

            }while (cursor.moveToNext());
        }
        return list;
    }
    public boolean themThanhVien(String hoTen, String namSinh) {
        SQLiteDatabase sqLiteDatabase = db.getWritableDatabase();
        ContentValues contentValues = new ContentValues();
        contentValues.put("HoTenThanhVien", hoTen);
        contentValues.put("NamSinh", namSinh);

        long check = sqLiteDatabase.insert("THANHVIEN", null, contentValues);
        if (check == -1) {
            return false;
        }
        return true;
    }
    public boolean capNhatThongTinTV(int maTV, String hoTen, String namSinh){
        SQLiteDatabase sqLiteDatabase = db.getWritableDatabase();
        ContentValues contentValues = new ContentValues();
        contentValues.put("HoTenThanhVien", hoTen);
        contentValues.put("NamSinh", namSinh);
        long check = sqLiteDatabase.update("THANHVIEN", contentValues, "MaThanhVien = ?", new String[]{String.valueOf(maTV)});
        if (check == -1) {
            return false;
        }
        return true;
    }
    public int xoaThongTinTV(int maTV){
        SQLiteDatabase sqLiteDatabase = db.getWritableDatabase();
        Cursor cursor = sqLiteDatabase.rawQuery("SELECT * FROM PHIEUMUON WHERE MaThanhVien = ?", new String[]{String.valueOf(maTV)});
        if (cursor.getCount() != 0){
            return -1;
        }

        long check = sqLiteDatabase.delete("THANHVIEN", "MaThanhVien = ?", new String[]{String.valueOf(maTV)});
        if (check == -1) {
            return 0;
        }
        return 1;
    }
}
