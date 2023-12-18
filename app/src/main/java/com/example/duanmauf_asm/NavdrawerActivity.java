package com.example.duanmauf_asm;

import android.app.Activity;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.support.v4.media.MediaBrowserCompat;
import android.view.LayoutInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.Menu;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.example.duanmauf_asm.DAO.LoaiSachDAO;
import com.example.duanmauf_asm.DAO.ThuThuDAO;
import com.example.duanmauf_asm.adapter.ThanhVienAdapter;
import com.example.duanmauf_asm.fragment.fragment_loaisach;
import com.example.duanmauf_asm.fragment.fragment_phieumuon;
import com.example.duanmauf_asm.fragment.fragment_sach;
import com.example.duanmauf_asm.fragment.fragment_thanhvien;
import com.example.duanmauf_asm.models.LoaiSach;
import com.google.android.material.snackbar.Snackbar;
import com.google.android.material.navigation.NavigationView;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AlertDialog;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.navigation.NavController;
import androidx.navigation.Navigation;
import androidx.navigation.ui.AppBarConfiguration;
import androidx.navigation.ui.NavigationUI;
import androidx.drawerlayout.widget.DrawerLayout;
import androidx.appcompat.app.AppCompatActivity;

import com.example.duanmauf_asm.databinding.ActivityNavdrawerBinding;

import java.util.ArrayList;

public class NavdrawerActivity extends AppCompatActivity {
    TextView tvTenTaiKhoan;
    private ArrayList<LoaiSach> list;
    ThuThuDAO thuThuDAO;
    LoaiSachDAO loaiSachDAO;

    private AppBarConfiguration mAppBarConfiguration;
    private ActivityNavdrawerBinding binding;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        binding = ActivityNavdrawerBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());

        setSupportActionBar(binding.appBarNavdrawer.toolbar);
//        binding.appBarNavdrawer.fab.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View view) {
//                Snackbar.make(view, "Replace with your own action", Snackbar.LENGTH_LONG)
//                        .setAction("Action", null).show();
//            }
//        });
        DrawerLayout drawer = binding.drawerLayout;
        NavigationView navigationView = binding.navView;
        // Passing each menu ID as a set of Ids because each
        // menu should be considered as top level destinations.
        mAppBarConfiguration = new AppBarConfiguration.Builder(
                R.id.nav_qlphieumuon, R.id.nav_qlloaisach, R.id.nav_qlsach, R.id.nav_qlthanhvien, R.id.nav_dangxuat, R.id.nav_doimatkhau, R.id.nav_sachnhieunhat, R.id.nav_doanhthu, R.id.nav_test)
                .setOpenableLayout(drawer)
                .build();
        NavController navController = Navigation.findNavController(this, R.id.nav_host_fragment_content_navdrawer);
        NavigationUI.setupActionBarWithNavController(this, navController, mAppBarConfiguration);
        NavigationUI.setupWithNavController(navigationView, navController);
        SharedPreferences sharedPreferences = getSharedPreferences("THONGTIN", MODE_PRIVATE);
        NavigationView view = findViewById(R.id.nav_view);
        View headerView = view.getHeaderView(0);
        String loaiTK = sharedPreferences.getString("loaitaikhoan", "");
        if (!loaiTK.equals("Admin")){
            Menu menu = navigationView.getMenu();
            MenuItem galleryItem = menu.findItem(R.id.nav_doanhthu);
            MenuItem galleryItem2 = menu.findItem(R.id.nav_sachnhieunhat);
            galleryItem.setVisible(false);
            galleryItem2.setVisible(false);
        }
        tvTenTaiKhoan = headerView.findViewById(R.id.tvTenTaiKhoan);
        String hoten = sharedPreferences.getString("hoten", "");
        tvTenTaiKhoan.setText(hoten);
    }



    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.navdrawer, menu);
        return true;
    }

    @Override
    public boolean onSupportNavigateUp() {
        NavController navController = Navigation.findNavController(this, R.id.nav_host_fragment_content_navdrawer);
        return NavigationUI.navigateUp(navController, mAppBarConfiguration)
                || super.onSupportNavigateUp();
    }
}