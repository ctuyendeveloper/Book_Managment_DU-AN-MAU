package com.example.duanmauf_asm.adapter;

import android.annotation.SuppressLint;
import android.app.Activity;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AlertDialog;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.duanmauf_asm.DAO.LoaiSachDAO;
import com.example.duanmauf_asm.DAO.ThanhVienDAO;
import com.example.duanmauf_asm.R;
import com.example.duanmauf_asm.models.LoaiSach;
import com.example.duanmauf_asm.models.ThanhVien;

import java.util.ArrayList;

public class LoaiSachAdapter extends RecyclerView.Adapter<LoaiSachAdapter.ViewHolder>{
    private Context context;
    private ArrayList<LoaiSach> list;
    private LoaiSachDAO loaiSachDAO;

    public LoaiSachAdapter(Context context, ArrayList<LoaiSach> list, LoaiSachDAO dao) {
        this.context = context;
        this.list = list;
        this.loaiSachDAO = dao;
    }


    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater inflater = ((Activity)context).getLayoutInflater();
        View view = inflater.inflate(R.layout.item_loaisach, parent,false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        holder.txtTenLS.setText(list.get(position).getTenLoai());
        holder.txtMaLS.setText("Mã Loại Sách: " + (list.get(position).getMaLoai()));
        holder.btnDeleteLS.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                LoaiSachDAO loaiSachDAO = new LoaiSachDAO(context);
                int check = loaiSachDAO.xoaLoaiSach(list.get(holder.getAdapterPosition()).getMaLoai());
                switch (check) {
                    case 1:
                        // load Data
                        list.clear();
                        list = loaiSachDAO.getDSLoaiSach();
                        notifyDataSetChanged();
                        break;
                    case -1:
                        Toast.makeText(context, "Không thể xóa loại sách này \nVì loại sách có trong phiếu mượn", Toast.LENGTH_SHORT).show();
                        break;
                    case 0:
                        Toast.makeText(context, "Xóa loại sách không thành công", Toast.LENGTH_SHORT).show();
                        break;
                    default:
                        break;
                }
            }
        });
        holder.btnEditLS.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                showDialogCapNhatTT(list.get(holder.getAdapterPosition()));
            }
        });

    }


    @Override
    public int getItemCount() {
        return list.size();
    }
    public class ViewHolder extends RecyclerView.ViewHolder{
        TextView txtMaLS, txtTenLS;
        Button btnEditLS, btnDeleteLS;
        public ViewHolder(@NonNull View itemView) {
            super(itemView);

            txtMaLS = itemView.findViewById(R.id.tvMaLS);
            txtTenLS = itemView.findViewById(R.id.tvTenLS);
            btnEditLS = itemView.findViewById(R.id.btnEditLS);
            btnDeleteLS = itemView.findViewById(R.id.btnDeleteLS);
        }
    }
    private void showDialogCapNhatTT(LoaiSach loaiSach){
        AlertDialog.Builder builder = new AlertDialog.Builder(context);
        LayoutInflater inflater = ((Activity)context).getLayoutInflater();
        View view = inflater.inflate(R.layout.dialog_capnhat_loaisach, null);
        builder.setView(view);

        EditText edtCSLoaiSach2 = view.findViewById(R.id.edtCSLoaiSach);

        edtCSLoaiSach2.setText(loaiSach.getTenLoai());

        builder.setNegativeButton("Chỉnh Sửa", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                String loaisach = edtCSLoaiSach2.getText().toString();
                int id = loaiSach.getMaLoai();
                if (loaiSachDAO.capnhatLoaiSach(id, loaisach)) {
                    Toast.makeText(context, "Chỉnh sửa thành công", Toast.LENGTH_SHORT).show();
                    loadData();
                }
                else {
                    Toast.makeText(context, "Chỉnh sửa không thành công", Toast.LENGTH_SHORT).show();
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

    private void loadData(){
        list.clear();
        list = loaiSachDAO.getDSLoaiSach();
        notifyDataSetChanged();
    }
}
