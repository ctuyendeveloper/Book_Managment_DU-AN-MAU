package com.example.duanmauf_asm.fragment;

import android.annotation.SuppressLint;
import android.content.DialogInterface;
import android.os.Bundle;

import androidx.appcompat.app.AlertDialog;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import com.example.duanmauf_asm.DAO.LoaiSachDAO;
import com.example.duanmauf_asm.R;
import com.example.duanmauf_asm.adapter.LoaiSachAdapter;
import com.example.duanmauf_asm.models.LoaiSach;
import com.google.android.material.floatingactionbutton.FloatingActionButton;

import java.util.ArrayList;
import java.util.HashMap;

public class fragment_loaisach extends Fragment {

    RecyclerView recyclerLoaiSach;
    LoaiSachDAO loaiSachDAO;
    EditText edtLoaiSach;
    int maLoai;

    @SuppressLint("MissingInflatedId")
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_loaisach, container, false);
        recyclerLoaiSach = view.findViewById(R.id.recyclerLoaiSach);
        loaiSachDAO = new LoaiSachDAO(getContext());
        FloatingActionButton floatingActionButton = view.findViewById(R.id.fabLS);
        floatingActionButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
//                String tenloai = edtLoaiSach.getText().toString();
//
//                if (loaiSachDAO.themLoaiSach(tenloai)){
//                    // thông báo + load danh sách
//                    loadData();
//                    Toast.makeText(getContext(), "Thêm loại sách thành công", Toast.LENGTH_SHORT).show();
//                    edtLoaiSach.setText("");
//                }
//                else {
//                    Toast.makeText(getContext(), "Thêm loại sách không thành công", Toast.LENGTH_SHORT).show();
//                }
                showDialog();
            }
        });
        loadData();
        return view;
    }

    private void loadData() {
        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(getContext());
        recyclerLoaiSach.setLayoutManager(linearLayoutManager);
        ArrayList<LoaiSach> list = loaiSachDAO.getDSLoaiSach();
        LoaiSachAdapter adapter = new LoaiSachAdapter(getContext(), list, loaiSachDAO);
        recyclerLoaiSach.setAdapter(adapter);
    }

    private void showDialog() {
        AlertDialog.Builder builder = new AlertDialog.Builder(getContext());
        LayoutInflater inflater = getLayoutInflater();
        View view = inflater.inflate(R.layout.dialog_themloaisach, null);
        EditText edtLoaiSach = view.findViewById(R.id.edtTLoaiSach);
        builder.setCancelable(false);
        builder.setView(view);

        builder.setNegativeButton("Thêm", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                String tenloai = edtLoaiSach.getText().toString();
                if (loaiSachDAO.themLoaiSach(tenloai)) {
                    // thông báo + load danh sách
                    loadData();
                    Toast.makeText(getContext(), "Thêm loại sách thành công", Toast.LENGTH_SHORT).show();
                    edtLoaiSach.setText("");

                } else {
                    Toast.makeText(getContext(), "Thêm loại sách không thành công", Toast.LENGTH_SHORT).show();
                }
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
}