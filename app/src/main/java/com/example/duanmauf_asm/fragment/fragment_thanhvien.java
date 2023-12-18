package com.example.duanmauf_asm.fragment;

import android.app.AlertDialog;
import android.content.DialogInterface;
import android.os.Bundle;

import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;
import android.widget.Toast;

import com.example.duanmauf_asm.DAO.ThanhVienDAO;
import com.example.duanmauf_asm.R;
import com.example.duanmauf_asm.adapter.ThanhVienAdapter;
import com.example.duanmauf_asm.models.ThanhVien;
import com.google.android.material.floatingactionbutton.FloatingActionButton;

import java.util.ArrayList;

public class fragment_thanhvien extends Fragment {
    ThanhVienDAO thanhVienDAO;
    ArrayList<ThanhVien> list;
    RecyclerView recyclerThanhVien;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_thanhvien, container, false);
        recyclerThanhVien = view.findViewById(R.id.recyclerThanhVien);
        FloatingActionButton floatAdd = view.findViewById(R.id.fabTV);
        thanhVienDAO = new ThanhVienDAO(getContext());

        loadData();
        floatAdd.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                showDialog();
            }
        });
        return view;

    }
    private void showDialog(){
        AlertDialog.Builder builder = new AlertDialog.Builder(getContext());
        LayoutInflater inflater = getLayoutInflater();
        View view = inflater.inflate(R.layout.dialog_themtv, null);
        builder.setCancelable(false);
        builder.setView(view);

        EditText edtHoTen = view.findViewById(R.id.edtHoTen);
        EditText edtNamSinh = view.findViewById(R.id.edtNamSinh);

        builder.setPositiveButton("Hủy", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {

            }
        });

        builder.setNegativeButton("Thêm", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                String hoten = edtHoTen.getText().toString();
                String namsinh = edtNamSinh.getText().toString();

                boolean check = thanhVienDAO.themThanhVien(hoten, namsinh);
                if (check){
                    Toast.makeText(getContext(), "Thêm thành công", Toast.LENGTH_SHORT).show();
                    loadData();
                }
                else {
                    Toast.makeText(getContext(), "Thêm thất bại", Toast.LENGTH_SHORT).show();
                }
            }
        });

        AlertDialog alertDialog = builder.create();
        alertDialog.show();
    }
    private void loadData(){
        list = thanhVienDAO.getDSThanhVien();

        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(getContext());
        recyclerThanhVien.setLayoutManager(linearLayoutManager);
        ThanhVienAdapter adapter = new ThanhVienAdapter(getContext(), list, thanhVienDAO);
        recyclerThanhVien.setAdapter(adapter);
    }
}