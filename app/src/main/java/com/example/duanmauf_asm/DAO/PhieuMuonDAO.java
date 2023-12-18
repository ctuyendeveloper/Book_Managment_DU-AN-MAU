package com.example.duanmauf_asm.DAO;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

import com.example.duanmauf_asm.database.Db;
import com.example.duanmauf_asm.models.PhieuMuon;

import java.util.ArrayList;

public class PhieuMuonDAO {
    Db dbHelper;
    public PhieuMuonDAO(Context context){
        dbHelper = new Db(context);
    }

    public ArrayList<PhieuMuon> getDSPhieuMuon(){
        ArrayList<PhieuMuon> list = new ArrayList<>();
        SQLiteDatabase sqLiteDatabase = dbHelper.getReadableDatabase();
        Cursor cursor = sqLiteDatabase.rawQuery("SELECT pm.MaPhieu, tv.HoTenThanhVien, s.TenSach, pm.Ngay, pm.TraSach, pm.TienThue " + "FROM PHIEUMUON pm, THANHVIEN tv, THUTHU tt, SACH s " + "WHERE pm.MaThanhVien = tv.MaThanhVien AND tt.MaThuThu = pm.MaThuThu AND pm.MaSach = s.MaSach ORDER BY pm.MaPhieu DESC", null);
        if (cursor.getCount() != 0){
            cursor.moveToFirst();
            do {
                list.add(new PhieuMuon(cursor.getInt(0), cursor.getString(1), cursor.getString(2), cursor.getString(3), cursor.getInt(4), cursor.getInt(5)));
            }while (cursor.moveToNext());
        }
        return list;
    }

    public boolean thayDoiTrangThai(int maPM){
        SQLiteDatabase sqLiteDatabase = dbHelper.getWritableDatabase();
        ContentValues contentValues = new ContentValues();
        contentValues.put("traSach", 1);
        long check = sqLiteDatabase.update("PHIEUMUON", contentValues, "MaPhieu = ?", new String[]{String.valueOf(maPM)});
        if (check == -1){
            return false;
        }
        return true;
    }

    public boolean themPhieuMuon(PhieuMuon phieuMuon){
        SQLiteDatabase sqLiteDatabase = dbHelper.getWritableDatabase();
        ContentValues contentValues = new ContentValues();
        // mapm integer primary key autoincrement, matv integer references THANHVIEN(matv), matt text references THUTHU(matt), masach integer references SACH(masach), ngay text, trasach integer, tienthue integer
        contentValues.put("MaThanhVien", phieuMuon.getMaTV());
        contentValues.put("MaThuThu", phieuMuon.getMatt());
        contentValues.put("masach", phieuMuon.getMasach());
        contentValues.put("ngay", phieuMuon.getNgay());
        contentValues.put("trasach", phieuMuon.getTraSach());
        contentValues.put("tienthue", phieuMuon.getTienThue());

        long check = sqLiteDatabase.insert("PHIEUMUON", null, contentValues);

        if (check == -1){
            return false;
        }
        return true;
    }
}
