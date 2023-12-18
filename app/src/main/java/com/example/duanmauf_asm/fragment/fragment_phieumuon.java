package com.example.duanmauf_asm.fragment;

import android.content.Context;
import android.content.DialogInterface;
import android.content.SharedPreferences;
import android.os.Bundle;

import androidx.appcompat.app.AlertDialog;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.SimpleAdapter;
import android.widget.Spinner;
import android.widget.Toast;

import com.example.duanmauf_asm.DAO.PhieuMuonDAO;
import com.example.duanmauf_asm.DAO.SachDAO;
import com.example.duanmauf_asm.DAO.ThanhVienDAO;
import com.example.duanmauf_asm.R;
import com.example.duanmauf_asm.adapter.PhieuMuonAdapter;
import com.example.duanmauf_asm.adapter.ThanhVienAdapter;
import com.example.duanmauf_asm.models.PhieuMuon;
import com.example.duanmauf_asm.models.Sach;
import com.example.duanmauf_asm.models.ThanhVien;
import com.google.android.material.floatingactionbutton.FloatingActionButton;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.HashMap;
import java.util.Locale;


public class fragment_phieumuon extends Fragment {
    PhieuMuonDAO phieuMuonDAO;
    ArrayList<PhieuMuon> list;
    RecyclerView recyclerPhieuMuon;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_phieumuon, container, false);
        recyclerPhieuMuon = view.findViewById(R.id.recyclerPhieuMuon);

        phieuMuonDAO = new PhieuMuonDAO(getContext());
        FloatingActionButton floatAdd = view.findViewById(R.id.fabPM);

        loadData();
        floatAdd.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                showDialog();
            }
        });
        return view;
    }
    private void showDialog(){
        AlertDialog.Builder builder = new AlertDialog.Builder(getContext());
        LayoutInflater inflater = getLayoutInflater();
        View view = inflater.inflate(R.layout.dialog_themphieumuon, null);
        Spinner spnThanhVien = view.findViewById(R.id.spnThanhVien);
        Spinner spnSach = view.findViewById(R.id.spnSach);

        getDataThanhVien(spnThanhVien);
        getDataSach(spnSach);
        builder.setCancelable(false);
        builder.setView(view);

        builder.setNegativeButton("Chỉnh Sửa", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {

                // lấy mã thành viên
                HashMap<String, Object> hsTV = (HashMap<String, Object>) spnThanhVien.getSelectedItem();
                int matv = (int) hsTV.get("matv");

                // lấy mã sách
                HashMap<String, Object> hsSach = (HashMap<String, Object>) spnSach.getSelectedItem();
                int masach = (int) hsSach.get("masach");

                int tien = (int) hsSach.get("giathue");

                themPhieuMuon(matv, masach, tien);
            }
        });

        builder.setPositiveButton("Hủy", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {

            }
        });

        AlertDialog alertDialog = builder.create();
        alertDialog.show();
    }
    private void getDataThanhVien(Spinner spnThanhVien){
        ThanhVienDAO thanhVienDAO = new ThanhVienDAO(getContext());
        ArrayList<ThanhVien> list = thanhVienDAO.getDSThanhVien();

        ArrayList<HashMap<String, Object>> listHM = new ArrayList<>();
        for (ThanhVien tv: list){
            HashMap<String, Object> hs = new HashMap<>();
            hs.put("matv", tv.getMaTV());
            hs.put("hoten", tv.getHoTen());
            listHM.add(hs);
        }

        SimpleAdapter simpleAdapter = new SimpleAdapter(
                getContext(),
                listHM,
                android.R.layout.simple_list_item_1,
                new String[]{"hoten"},
                new int[]{android.R.id.text1});
        spnThanhVien.setAdapter(simpleAdapter);
    }
    private void getDataSach(Spinner spnSach){
        SachDAO sachDAO = new SachDAO(getContext());
        ArrayList<Sach> list = sachDAO.getDSDauSach();

        ArrayList<HashMap<String, Object>> listHM = new ArrayList<>();
        for (Sach sc: list){
            HashMap<String, Object> hs = new HashMap<>();
            hs.put("masach", sc.getMaSach());
            hs.put("tensach", sc.getTenSach());
            hs.put("giathue", sc.getGiaThue());
            listHM.add(hs);
        }

        SimpleAdapter simpleAdapter = new SimpleAdapter(
                getContext(),
                listHM,
                android.R.layout.simple_list_item_1,
                new String[]{"tensach"},
                new int[]{android.R.id.text1});
        spnSach.setAdapter(simpleAdapter);
    }
    private void themPhieuMuon(int matv, int masach, int tien){
        // lấy mã thủ thư
        SharedPreferences sharedPreferences = getContext().getSharedPreferences("THONGTIN", Context.MODE_PRIVATE);
        String matt = sharedPreferences.getString("matt", "");

        // lấy ngày hiện tại
        Date currentTime = Calendar.getInstance().getTime();

        SimpleDateFormat simpleDateFormat = new SimpleDateFormat("dd/mm/yyyy", Locale.getDefault());
        String ngay = simpleDateFormat.format(currentTime);

        PhieuMuon phieuMuon = new PhieuMuon(matv, matt, masach, ngay, 0, tien);
        boolean kiemtra = phieuMuonDAO.themPhieuMuon(phieuMuon);

        if (kiemtra){
            Toast.makeText(getContext(), "Thêm phiếu mượn thành công", Toast.LENGTH_SHORT).show();
            loadData();
            System.out.println(list);
        }
        else {
            Toast.makeText(getContext(), "Thêm phiếu mượn thất bại", Toast.LENGTH_SHORT).show();
        }
    }
    private void loadData(){
        list = phieuMuonDAO.getDSPhieuMuon();

        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(getContext());
        recyclerPhieuMuon.setLayoutManager(linearLayoutManager);
        PhieuMuonAdapter adapter = new PhieuMuonAdapter(getContext(), list);
        recyclerPhieuMuon.setAdapter(adapter);
    }
}