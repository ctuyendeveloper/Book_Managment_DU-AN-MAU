package com.example.duanmauf_asm.fragment;

import static android.content.Context.MODE_PRIVATE;

import android.app.Activity;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;

import androidx.appcompat.app.AlertDialog;
import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;
import android.widget.Toast;

import com.example.duanmauf_asm.DAO.ThuThuDAO;
import com.example.duanmauf_asm.LoginActivity;
import com.example.duanmauf_asm.NavdrawerActivity;
import com.example.duanmauf_asm.R;

/**
 * A simple {@link Fragment} subclass.
 * Use the {@link fragment_doimatkhau#newInstance} factory method to
 * create an instance of this fragment.
 */
public class fragment_doimatkhau extends Fragment {

    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";

    // TODO: Rename and change types of parameters
    private String mParam1;
    private String mParam2;

    public fragment_doimatkhau() {
        // Required empty public constructor
    }

    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     * @param param1 Parameter 1.
     * @param param2 Parameter 2.
     * @return A new instance of fragment fragment_doimatkhau.
     */
    // TODO: Rename and change types and number of parameters
    public static fragment_doimatkhau newInstance(String param1, String param2) {
        fragment_doimatkhau fragment = new fragment_doimatkhau();
        Bundle args = new Bundle();
        args.putString(ARG_PARAM1, param1);
        args.putString(ARG_PARAM2, param2);
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (getArguments() != null) {
            mParam1 = getArguments().getString(ARG_PARAM1);
            mParam2 = getArguments().getString(ARG_PARAM2);
        }
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        // Inflate the layout for this fragment
        AlertDialog.Builder builder = new AlertDialog.Builder(getContext());
        LayoutInflater inflater1 = ((Activity)getContext()).getLayoutInflater();
        View view = inflater1.inflate(R.layout.dialog_doimatkhau, null);
        builder.setView(view);

        EditText edtOldPass = view.findViewById(R.id.edtOldPass);
        EditText edtNewPass = view.findViewById(R.id.edtNewPass);
        EditText edtReNewPass = view.findViewById(R.id.edtReNewPass);

        builder.setNegativeButton("Đổi mật khẩu", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                String oldPass = edtOldPass.getText().toString();
                String newPass = edtNewPass.getText().toString();
                String reNewPass = edtReNewPass.getText().toString();
                if (newPass.equals(reNewPass)){
                    SharedPreferences sharedPreferences = getContext().getSharedPreferences("THONGTIN", MODE_PRIVATE);
                    String matt = sharedPreferences.getString("matt", "");
                    // cập nhật
                    ThuThuDAO thuThuDAO = new ThuThuDAO(getContext());
                    boolean check = thuThuDAO.capNhatMatKhau(matt, oldPass, newPass);
                    if (check){
                        Toast.makeText(getContext(), "Cập nhật mật khẩu thành công", Toast.LENGTH_SHORT).show();
                        Intent intent = new Intent(getContext(), LoginActivity.class);
                        intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK | Intent.FLAG_ACTIVITY_CLEAR_TASK);
                        startActivity(intent);
                    }
                    else {
                        Toast.makeText(getContext(), "Cập nhật mật khẩu thất bại", Toast.LENGTH_SHORT).show();
                    }
                }
                else {
                    Toast.makeText(getContext(), "Nhập mật khẩu mới và xác nhận không trùng nhau", Toast.LENGTH_SHORT).show();
                }
            }
        });

        builder.setPositiveButton("Hủy", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {

            }
        });
        builder.show();
        return inflater.inflate(R.layout.fragment_doimatkhau, container, false);
    }
}