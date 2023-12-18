package com.example.duanmauf_asm.DAO;

import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

import com.example.duanmauf_asm.database.Db;
import com.example.duanmauf_asm.models.Sach;

import java.util.ArrayList;

public class Top10vDoanhThuDAO {
    Db db;
    public Top10vDoanhThuDAO(Context context){
        db = new Db(context);
    }

    public ArrayList<Sach> getTop10(){
        ArrayList<Sach> list = new ArrayList<>();
        SQLiteDatabase sqLiteDatabase = db.getReadableDatabase();
        Cursor cursor = sqLiteDatabase.rawQuery("SELECT pm.MaSach, sc.TenSach, COUNT(pm.MaSach) FROM PHIEUMUON pm, SACH sc WHERE pm.MaSach = sc.MaSach GROUP BY pm.MaSach, sc.TenSach ORDER BY COUNT(pm.MaSach) DESC limit 10", null);
        if (cursor.getCount() != 0 ){
            cursor.moveToFirst();
            do {
                list.add(new Sach(cursor.getInt(0), cursor.getString(1), cursor.getInt(2)));
            }while (cursor.moveToNext());
        }
        return list;
    }
    public int getDoanhThu(String ngaybatdau, String ngayketthuc){
        float doanhthu = 0;
        ngaybatdau = ngaybatdau.replace("/", "");
        ngayketthuc = ngayketthuc.replace("/", "");
        SQLiteDatabase sqLiteDatabase = db.getReadableDatabase();
        Cursor cursor = sqLiteDatabase.rawQuery("SELECT SUM(TienThue) \n" + "FROM PHIEUMUON \n" + "WHERE substr(Ngay,7)||substr(Ngay,4,2)||substr(Ngay,1,2) between ? and ?", new String[]{ngaybatdau, ngayketthuc});
        if (cursor.getCount() != 0 ){
            cursor.moveToFirst();
            doanhthu = cursor.getInt(0);
        }
        return (int) doanhthu;
    }
}
