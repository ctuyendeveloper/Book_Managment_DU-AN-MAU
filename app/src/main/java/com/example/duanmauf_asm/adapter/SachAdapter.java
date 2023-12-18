package com.example.duanmauf_asm.adapter;

import android.annotation.SuppressLint;
import android.app.Activity;
import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.SimpleAdapter;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.duanmauf_asm.DAO.SachDAO;
import com.example.duanmauf_asm.R;
import com.example.duanmauf_asm.models.Sach;

import java.util.ArrayList;
import java.util.HashMap;

public class SachAdapter extends RecyclerView.Adapter<SachAdapter.ViewHolder>{
    private Context context;
    private ArrayList<Sach> list;
    private ArrayList<HashMap<String, Object>> listHM;
    private SachDAO sachDAO;

    public SachAdapter(Context context, ArrayList<Sach> list, ArrayList<HashMap<String, Object>> listHM, SachDAO sachDAO) {
        this.context = context;
        this.list = list;
        this.listHM = listHM;
        this.sachDAO = sachDAO;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater inflater = ((Activity)context).getLayoutInflater();
        View view = inflater.inflate(R.layout.item_sach, parent, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        holder.txtMaSach.setText("Mã sách: " + list.get(position).getMaSach());
        holder.txtTenSach.setText("Tên sách: " + list.get(position).getTenSach());
        holder.txtGiaThue.setText("Giá thuê: " + list.get(position).getGiaThue());
        holder.txtTenLoai.setText("Tên loại: " + list.get(position).getTenLoai());

        holder.btnEditS.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                showDialog(list.get(holder.getAdapterPosition()));
            }
        });

        holder.btnDeleteS.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                int check = sachDAO.xoaSach(list.get(holder.getAdapterPosition()).getMaSach());
                switch (check){
                    case 1:
                        Toast.makeText(context, "Xóa thành công", Toast.LENGTH_SHORT).show();
                        loadData();
                        break;
                    case 0:
                        Toast.makeText(context, "Xóa không thành công", Toast.LENGTH_SHORT).show();
                        break;
                    case -1:
                        Toast.makeText(context, "Không xóa được sách này \nVì sách có trong phiếu mượn", Toast.LENGTH_SHORT).show();
                    default:
                        break;
                }
            }
        });
    }

    @Override
    public int getItemCount() {
        return list.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder{
        TextView txtMaSach, txtTenSach, txtGiaThue, txtTenLoai;
        Button btnEditS, btnDeleteS;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);

            txtMaSach = itemView.findViewById(R.id.tvMaS);
            txtTenSach = itemView.findViewById(R.id.tvTenS);
            txtGiaThue = itemView.findViewById(R.id.tvTienS);
            txtTenLoai = itemView.findViewById(R.id.tvTheLoaiS);
            btnEditS = itemView.findViewById(R.id.btnEditS);
            btnDeleteS = itemView.findViewById(R.id.btnDeleteS);
        }
    }
    private void showDialog(Sach sach){
        AlertDialog.Builder builder = new AlertDialog.Builder(context);
        LayoutInflater inflater = ((Activity)context).getLayoutInflater();
        View view = inflater.inflate(R.layout.dialog_capnhat_sach, null);
        builder.setView(view);

        EditText edtTenSach = view.findViewById(R.id.edtTenSach);
        EditText edtTien = view.findViewById(R.id.edtTien);
        @SuppressLint({"MissingInflatedId", "LocalSuppress"}) TextView txtMaSach = view.findViewById(R.id.tvCNMaSach);
        Spinner spnLoaiSach = view.findViewById(R.id.spnCNLoaiSach);

        txtMaSach.setText("Mã Sách: " + sach.getMaSach());
        edtTenSach.setText(sach.getTenSach());
        edtTien.setText(String.valueOf(sach.getGiaThue()));

        SimpleAdapter simpleAdapter = new SimpleAdapter(
                context,
                listHM,
                android.R.layout.simple_list_item_1,
                new String[]{"TenLoai"},
                new int[]{android.R.id.text1}
        );
        spnLoaiSach.setAdapter(simpleAdapter);

        int index = 0;
        int position = -1;

        for (HashMap<String, Object> item : listHM){
            if ((int) item.get("MaLoai") == sach.getMaLoai()){
                position = index;
            }
            index++;
        }
        spnLoaiSach.setSelection(position);

        builder.setNegativeButton("Cập nhật", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                String tensach = edtTenSach.getText().toString();
                int tien = Integer.parseInt(edtTien.getText().toString());

                HashMap<String, Object> hs = (HashMap<String, Object>) spnLoaiSach.getSelectedItem();
                int maloai = (int) hs.get("MaLoai");

                boolean check = sachDAO.capNhatThongTinSach(sach.getMaSach(), tensach, tien, maloai);
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

    private void loadData(){
        list.clear();
        list = sachDAO.getDSDauSach();
        notifyDataSetChanged();
    }

}
