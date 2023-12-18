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
import android.widget.SimpleAdapter;
import android.widget.Spinner;
import android.widget.Toast;

import com.example.duanmauf_asm.DAO.LoaiSachDAO;
import com.example.duanmauf_asm.DAO.SachDAO;
import com.example.duanmauf_asm.R;
import com.example.duanmauf_asm.adapter.SachAdapter;
import com.example.duanmauf_asm.models.LoaiSach;
import com.example.duanmauf_asm.models.Sach;
import com.google.android.material.floatingactionbutton.FloatingActionButton;

import java.util.ArrayList;
import java.util.HashMap;

public class fragment_sach extends Fragment {
    RecyclerView recyclerSach;
    SachDAO sachDAO;
    LinearLayoutManager linearLayoutManager;
    ArrayList<Sach> list;
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_sach, container, false);
        recyclerSach = view.findViewById(R.id.recyclerviewSach);
        FloatingActionButton floatAdd = view.findViewById(R.id.fabS);

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
        View view = inflater.inflate(R.layout.dialog_themsach, null);
        builder.setCancelable(false);
        builder.setView(view);

        EditText edtTenSach = view.findViewById(R.id.edtTenSach);
        EditText edtTien = view.findViewById(R.id.edtTien);
        Spinner spnLoaiSach = view.findViewById(R.id.spnLoaiSach);

        SimpleAdapter simpleAdapter = new SimpleAdapter(
                getContext(),
                getDSLoaiSach(),
                android.R.layout.simple_list_item_1,
                new String[]{"TenLoai"},
                new int[]{android.R.id.text1}
        );
        spnLoaiSach.setAdapter(simpleAdapter);

        builder.setNegativeButton("Thêm", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                String tensach = edtTenSach.getText().toString();
                int tien = Integer.parseInt(edtTien.getText().toString());

                HashMap<String, Object> hs = (HashMap<String, Object>) spnLoaiSach.getSelectedItem();
                int maloai = (int) hs.get("MaLoai");

                boolean check = sachDAO.themSachMoi(tensach, tien, maloai);
                if (check){
                    Toast.makeText(getContext(), "Thêm thành công", Toast.LENGTH_SHORT).show();
                    loadData();
                }
                else {
                    Toast.makeText(getContext(), "Thêm không thành công", Toast.LENGTH_SHORT).show();
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

    private ArrayList<HashMap<String, Object>> getDSLoaiSach(){
        LoaiSachDAO loaiSachDAO = new LoaiSachDAO(getContext());
        ArrayList<LoaiSach> list = loaiSachDAO.getDSLoaiSach();
        ArrayList<HashMap<String, Object>> listHM = new ArrayList<>();

        for (LoaiSach loai: list){
            HashMap<String, Object> hs = new HashMap<>();
            hs.put("MaLoai", loai.getMaLoai());
            hs.put("TenLoai", loai.getTenLoai());
            listHM.add(hs);
        }

        return listHM;
    }

    private void loadData(){
        sachDAO = new SachDAO(getContext());
        list = sachDAO.getDSDauSach();

        linearLayoutManager = new LinearLayoutManager(getContext());
        recyclerSach.setLayoutManager(linearLayoutManager);

        SachAdapter adapter = new SachAdapter(getContext(), list, getDSLoaiSach(), sachDAO);
        recyclerSach.setAdapter(adapter);
    }
}