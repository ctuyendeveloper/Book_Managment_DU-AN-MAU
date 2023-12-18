package com.example.duanmauf_asm.DAO;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

import com.example.duanmauf_asm.database.Db;
import com.example.duanmauf_asm.models.LoaiSach;

import java.util.ArrayList;

public class LoaiSachDAO {
    Db db;
    public LoaiSachDAO(Context context){
        db = new Db(context);
    }
    public ArrayList<LoaiSach> getDSLoaiSach(){
        ArrayList<LoaiSach> list = new ArrayList<>();
        SQLiteDatabase sqLiteDatabase = db.getReadableDatabase();
        Cursor cursor = sqLiteDatabase.rawQuery("SELECT * FROM LOAISACH", null);
        if (cursor.getCount() != 0){
            cursor.moveToFirst();
            do {
                list.add(new LoaiSach(cursor.getInt(0), cursor.getString(1)));
            }while (cursor.moveToNext());
        }
        return list;
    }
    public boolean themLoaiSach(String tenloai){
        SQLiteDatabase sqLiteDatabase = db.getWritableDatabase();
        ContentValues contentValues = new ContentValues();
        contentValues.put("TenLoai", tenloai);

        long check = sqLiteDatabase.insert("LOAISACH", null, contentValues);
        if (check == -1)
            return false;
        return true;
    }
    public int xoaLoaiSach(int id){
        SQLiteDatabase sqLiteDatabase = db.getWritableDatabase();
        Cursor cursor = sqLiteDatabase.rawQuery("SELECT * FROM SACH WHERE MaLoai = ?", new String[]{String.valueOf(id)});
        if (cursor.getCount() != 0){
            return -1;
        }

        long check = sqLiteDatabase.delete("LOAISACH", "MaLoai = ?", new String[]{String.valueOf(id)});
        if (check == -1)
            return 0;
        return 1;
    }
    public boolean capnhatLoaiSach(int maloai, String tenloai){
        SQLiteDatabase sqLiteDatabase = db.getWritableDatabase();
        ContentValues contentValues = new ContentValues();
        contentValues.put("TenLoai", tenloai);
        long check = sqLiteDatabase.update("LOAISACH", contentValues, "MaLoai = ?", new String[]{String.valueOf(maloai)});
        if (check == -1)
            return false;
        return true;
    }
}
