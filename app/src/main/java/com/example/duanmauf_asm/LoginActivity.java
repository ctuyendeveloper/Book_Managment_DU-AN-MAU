package com.example.duanmauf_asm;

import androidx.appcompat.app.AppCompatActivity;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.example.duanmauf_asm.DAO.ThuThuDAO;

public class LoginActivity extends AppCompatActivity {
    EditText edtUser, edtPass;
    Button buttonLogin;
    ThuThuDAO thuThuDAO;
    TextView tvHoten;
    @SuppressLint("MissingInflatedId")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        edtUser = findViewById(R.id.edtAccount);
        edtPass = findViewById(R.id.edtPass);
//        tvHoten = (TextView)findViewById(R.id.tvTenTaiKhoan);

        thuThuDAO = new ThuThuDAO(this);
        buttonLogin = findViewById(R.id.btnLogin);
        buttonLogin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String user = edtUser.getText().toString();
                String pass = edtPass.getText().toString();
//                String tentaikhoan = new String();

                try {
                    if(user.isEmpty() || pass.isEmpty()){
                        String textvl = "";
                        if (user.isEmpty()) {
                            textvl += "Chưa nhập Username \n";
                        }
                        if (pass.isEmpty()) {
                            textvl += "Chưa nhập Password";
                        }
                        Toast.makeText(LoginActivity.this, textvl, Toast.LENGTH_SHORT).show();
                    }
                    else{
                        if (thuThuDAO.checkDangNhap(user, pass)) {
                            //... lưu Shared Preferences
                            SharedPreferences sharedPreferences = getSharedPreferences("THONGTIN", MODE_PRIVATE);
                            SharedPreferences.Editor editor = sharedPreferences.edit();
                            editor.putString("matt", user);
                            editor.commit();
                            startActivity(new Intent(LoginActivity.this, NavdrawerActivity.class));
                        }
                        else{
                            Toast.makeText(LoginActivity.this, "Đăng nhập không thành công", Toast.LENGTH_SHORT).show();
                        }
                    }
                } catch (Exception e){
                    System.out.println(e);
                }
            }
        });
    }
}