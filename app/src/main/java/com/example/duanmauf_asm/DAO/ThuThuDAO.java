package com.example.duanmauf_asm.DAO;

import android.content.ContentValues;
import android.content.Context;
import android.content.SharedPreferences;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

import com.example.duanmauf_asm.database.Db;

public class ThuThuDAO {
    Db db;
    SharedPreferences sharedPreferences;
    public ThuThuDAO(Context context){
        db = new Db(context);
        sharedPreferences = context.getSharedPreferences("THONGTIN", Context.MODE_PRIVATE);
    }
    public boolean checkDangNhap(String matt, String matkhau){
        SQLiteDatabase sqLiteDatabase = db.getReadableDatabase();
        // matt, hoten, loaitaikhoan
        Cursor cursor = sqLiteDatabase.rawQuery("SELECT * FROM THUTHU WHERE MaThuThu = ? AND MatKhau = ?", new String[]{matt, matkhau});
        if (cursor.getCount() != 0){
            cursor.moveToFirst(); // đưa con trỏ về dòng đầu tiên
            // lưu sharedpreferences
            SharedPreferences.Editor editor = sharedPreferences.edit();
            editor.putString("matt", cursor.getString(0));
            editor.putString("loaitaikhoan", cursor.getString(3));
            editor.putString("hoten", cursor.getString(1));
            editor.commit();
            return true;
        }
        else {
            return false;
        }

    }
    public boolean capNhatMatKhau(String username, String oldPass, String newPass){
        SQLiteDatabase sqLiteDatabase = db.getWritableDatabase();
        Cursor cursor = sqLiteDatabase.rawQuery("SELECT * FROM THUTHU WHERE MaThuThu = ? AND MatKhau = ?", new String[]{username, oldPass});
        if (cursor.getCount() > 0){
            ContentValues contentValues = new ContentValues();
            contentValues.put("MatKhau", newPass);
            long check = sqLiteDatabase.update("THUTHU", contentValues, "MaThuThu = ?", new String[]{username});
            if (check == -1)
                return false;
            return true;
        }
        return false;
    }
}
