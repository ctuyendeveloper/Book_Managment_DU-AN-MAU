package com.example.duanmauf_asm.fragment;

import android.os.Bundle;

import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.duanmauf_asm.DAO.Top10vDoanhThuDAO;
import com.example.duanmauf_asm.R;
import com.example.duanmauf_asm.adapter.Top10Adapter;
import com.example.duanmauf_asm.models.Sach;

import java.util.ArrayList;
public class fragment_thongkeSTop10 extends Fragment {

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_thongke_s_top10, container, false);
        RecyclerView recyclerTop10 = view.findViewById(R.id.recyclerTop10);

        Top10vDoanhThuDAO thongKeDAO = new Top10vDoanhThuDAO(getContext());
        ArrayList<Sach> list = thongKeDAO.getTop10();

        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(getContext());
        recyclerTop10.setLayoutManager(linearLayoutManager);
        Top10Adapter adapter = new Top10Adapter(getContext(), list);
        recyclerTop10.setAdapter(adapter);
        // Inflate the layout for this fragment
        return view;
    }
}