package com.example.duanmauf_asm.adapter;

import android.app.Activity;
import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.duanmauf_asm.DAO.PhieuMuonDAO;
import com.example.duanmauf_asm.DAO.ThanhVienDAO;
import com.example.duanmauf_asm.R;
import com.example.duanmauf_asm.models.PhieuMuon;
import com.example.duanmauf_asm.models.ThanhVien;

import java.util.ArrayList;

public class PhieuMuonAdapter extends RecyclerView.Adapter<PhieuMuonAdapter.ViewHolder>{
    private Context context;
    private ArrayList<PhieuMuon> list;

    public PhieuMuonAdapter(Context context, ArrayList<PhieuMuon> list) {
        this.context = context;
        this.list = list;
    }


    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater inflater = ((Activity)context).getLayoutInflater();
        View view = inflater.inflate(R.layout.item_phieumuon, parent,false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        holder.txtMaPM.setText("Mã Phiếu Mượn: " + list.get(position).getMaPM());
        holder.txtTenTV.setText("Tên Thành Viên: " + list.get(position).getTenTV());
        holder.txtTenSach.setText("Tên Sách: " + list.get(position).getTenSach());
        holder.txtNgay.setText("Ngày: " + list.get(position).getNgay());
        String trangthai = "";
        if (list.get(position).getTraSach() == 1) {
            trangthai = "Đã trả sách";
            holder.btnTraSach.setVisibility(View.GONE);
        }
        else {
            trangthai = "Chưa trả sách";
            holder.btnTraSach.setVisibility(View.VISIBLE);
        }
        holder.txtTrangThai.setText("Trạng thái: " + trangthai);
        holder.txtTien.setText("Tiền: " + list.get(position).getTienThue());

        holder.btnTraSach.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                PhieuMuonDAO phieuMuonDAO = new PhieuMuonDAO(context);
                boolean kiemtra = phieuMuonDAO.thayDoiTrangThai(list.get(holder.getAdapterPosition()).getMaPM());
                if (kiemtra){
                    list.clear();
                    list = phieuMuonDAO.getDSPhieuMuon();
                    notifyDataSetChanged();
                    Toast.makeText(context, "Thay đổi trạng thái thành công", Toast.LENGTH_SHORT).show();
                }
                else {
                    Toast.makeText(context, "Thay đổi trạng thái không thành công", Toast.LENGTH_SHORT).show();
                }
            }
        });
    }


    @Override
    public int getItemCount() {
        return list.size();
    }
    public class ViewHolder extends RecyclerView.ViewHolder{
        TextView txtMaPM, txtTenTV, txtTenSach, txtNgay, txtTien, txtTrangThai;
        Button btnTraSach;
        public ViewHolder(@NonNull View itemView) {
            super(itemView);

            txtMaPM = itemView.findViewById(R.id.tvMaPhieuPM);
            txtTenTV = itemView.findViewById(R.id.tvTenTVPM);
            txtTenSach = itemView.findViewById(R.id.tvTenSachPM);
            txtNgay = itemView.findViewById(R.id.tvNgayMuonPM);
            txtTien = itemView.findViewById(R.id.tvTienThuePM);
            txtTrangThai = itemView.findViewById(R.id.tvTrangThaiPM);
            btnTraSach = itemView.findViewById(R.id.btnTraSachPM);
        }
    }
    private void loadData(){
        list.clear();
        notifyDataSetChanged();
    }
}
