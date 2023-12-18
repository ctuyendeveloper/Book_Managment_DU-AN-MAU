package com.example.duanmauf_asm.adapter;

import android.app.Activity;
import android.app.AlertDialog;
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
import androidx.recyclerview.widget.RecyclerView;

import com.example.duanmauf_asm.DAO.ThanhVienDAO;
import com.example.duanmauf_asm.NavdrawerActivity;
import com.example.duanmauf_asm.R;
import com.example.duanmauf_asm.models.ThanhVien;

import java.util.ArrayList;

public class ThanhVienAdapter extends RecyclerView.Adapter<ThanhVienAdapter.ViewHolder>{
    private Context context;
    private ArrayList<ThanhVien> list;
    private ThanhVienDAO thanhVienDAO;

    public ThanhVienAdapter(Context context, ArrayList<ThanhVien> list, ThanhVienDAO thanhVienDAO) {
        this.context = context;
        this.list = list;
        this.thanhVienDAO = thanhVienDAO;
    }


    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater inflater = ((Activity)context).getLayoutInflater();
        View view = inflater.inflate(R.layout.item_thanhvien, parent,false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        holder.txtMaTV.setText("Mã Thành Viên: " + list.get(position).getMaTV());
        holder.txtHoTen.setText(list.get(position).getHoTen());
        String textdulieu = String.valueOf(holder.txtHoTen);
        Bundle bundle = new Bundle();
        bundle.putString("textdulieu", textdulieu);
        holder.txtNamSinh.setText("Năm sinh: " + list.get(position).getNamSinh());
        holder.btnEditTV.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                showDialogCapNhatTT(list.get(holder.getAdapterPosition()));
            }
        });
        holder.btnXoaTV.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                int check = thanhVienDAO.xoaThongTinTV(list.get(holder.getAdapterPosition()).getMaTV());
                switch (check){
                    case 1:
                        Toast.makeText(context, "Xóa thành công", Toast.LENGTH_SHORT).show();
                        loadData();
                        break;
                    case 0:
                        Toast.makeText(context, "Xóa thất bại", Toast.LENGTH_SHORT).show();
                        break;
                    case -1:
                        Toast.makeText(context, "Thành viên tồn tại phiếu mượn, không được phép xóa", Toast.LENGTH_SHORT).show();
                        break;
                    default:
                        break;
                }
            }
        });
    }
    private void showDialogCapNhatTT(ThanhVien thanhVien){
        AlertDialog.Builder builder = new AlertDialog.Builder(context);
        LayoutInflater inflater = ((Activity)context).getLayoutInflater();
        View view = inflater.inflate(R.layout.dialog_capnhat_tv, null);
        builder.setView(view);

        TextView txtMaTV = view.findViewById(R.id.tvMaTV);
        EditText edtHoTen = view.findViewById(R.id.edtHoTen);
        EditText edtNamSinh = view.findViewById(R.id.edtNamSinh);

        txtMaTV.setText("Mã Thành Viên: " + thanhVien.getMaTV());
        edtHoTen.setText(thanhVien.getHoTen());
        edtNamSinh.setText(thanhVien.getNamSinh());

        builder.setNegativeButton("Chỉnh Sửa", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                String hoten = edtHoTen.getText().toString();
                String namsinh = edtNamSinh.getText().toString();

                int id = thanhVien.getMaTV();

                boolean check = thanhVienDAO.capNhatThongTinTV(id, hoten, namsinh);
                if (check){
                    Toast.makeText(context, "Cập nhật thành công", Toast.LENGTH_SHORT).show();
                    loadData();
                }
                else {
                    Toast.makeText(context, "Cập nhật không thành công", Toast.LENGTH_SHORT).show();
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


    @Override
    public int getItemCount() {
        return list.size();
    }
    public class ViewHolder extends RecyclerView.ViewHolder{
        TextView txtMaTV, txtHoTen, txtNamSinh;
        Button btnEditTV, btnXoaTV;
        public ViewHolder(@NonNull View itemView) {
            super(itemView);

            txtMaTV = itemView.findViewById(R.id.tvMaTV);
            txtHoTen = itemView.findViewById(R.id.tvTenTV);
            txtNamSinh = itemView.findViewById(R.id.tvNamSinhTV);
            btnEditTV = itemView.findViewById(R.id.btnEditTV);
            btnXoaTV = itemView.findViewById(R.id.btnXoaTV);
        }
    }
    private void loadData(){
        list.clear();
        list = thanhVienDAO.getDSThanhVien();
        notifyDataSetChanged();
    }
}
