package com.example.duanmauf_asm.fragment;

import android.app.DatePickerDialog;
import android.graphics.Color;
import android.os.Bundle;
import androidx.fragment.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.TextView;

import com.example.duanmauf_asm.DAO.Top10vDoanhThuDAO;
import com.example.duanmauf_asm.R;
import com.github.mikephil.charting.charts.PieChart;
import com.github.mikephil.charting.components.Description;
import com.github.mikephil.charting.components.Legend;
import com.github.mikephil.charting.data.Entry;
import com.github.mikephil.charting.data.PieData;
import com.github.mikephil.charting.data.PieDataSet;
import com.github.mikephil.charting.data.PieEntry;
import com.github.mikephil.charting.highlight.Highlight;
import com.github.mikephil.charting.listener.OnChartValueSelectedListener;

import java.util.ArrayList;
import java.util.Calendar;


public class fragment_doanhthu extends Fragment implements OnChartValueSelectedListener {
    private String ngaybatdau;
    private String ngayketthuc;
    private Top10vDoanhThuDAO top10vDoanhThuDAO;
    private  int doanhthu;


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_doanhthu, container, false);
        // Inflate the layout for this fragment
        EditText edtStart = view.findViewById(R.id.edtStart);
        EditText edtEnd = view.findViewById(R.id.edtEnd);
        Calendar calendar = Calendar.getInstance();
        top10vDoanhThuDAO = new Top10vDoanhThuDAO(getContext());
        Button btnThongKe = view.findViewById(R.id.btnThongKe);
        TextView txtKetQua = view.findViewById(R.id.txtKetQua);

        edtStart.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                DatePickerDialog datePickerDialog = new DatePickerDialog(
                        getContext(),
                        new DatePickerDialog.OnDateSetListener() {
                            @Override
                            public void onDateSet(DatePicker view, int year, int month, int dayOfMonth) {
                                String ngay = "";
                                String thang = "";

                                if (dayOfMonth < 10){
                                    ngay = "0" + dayOfMonth;
                                }
                                else {
                                    ngay = String.valueOf(dayOfMonth);
                                }

                                if ((month + 1) < 10){
                                    thang = "0" + (month + 1);
                                }
                                else {
                                    thang = String.valueOf(month + 1);
                                }

                                edtStart.setText(year + "/" + thang + "/" + ngay);
                            }
                        },
                        calendar.get(Calendar.YEAR),
                        calendar.get(Calendar.MONTH),
                        calendar.get(Calendar.DAY_OF_MONTH)
                );

                datePickerDialog.show();
            }
        });

        edtEnd.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                DatePickerDialog datePickerDialog = new DatePickerDialog(
                        getContext(),
                        new DatePickerDialog.OnDateSetListener() {
                            @Override
                            public void onDateSet(DatePicker view, int year, int month, int dayOfMonth) {
                                String ngay = "";
                                String thang = "";

                                if (dayOfMonth < 10){
                                    ngay = "0" + dayOfMonth;
                                }
                                else {
                                    ngay = String.valueOf(dayOfMonth);
                                }

                                if ((month + 1) < 10){
                                    thang = "0" + (month + 1);
                                }
                                else {
                                    thang = String.valueOf(month + 1);
                                }

                                edtEnd.setText(year + "/" + thang + "/" + ngay);
                            }
                        },
                        calendar.get(Calendar.YEAR),
                        calendar.get(Calendar.MONTH),
                        calendar.get(Calendar.DAY_OF_MONTH)
                );

                datePickerDialog.show();
            }
        });
        btnThongKe.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
//                PieChart mChart = view.findViewById(R.id.piechart);
//                mChart.setRotationEnabled(true);
//                mChart.setDescription(new Description());
//                mChart.setHoleRadius(35f);
//                mChart.setTransparentCircleAlpha(0);
//                mChart.setCenterText("Thống kê");
//                mChart.setCenterTextSize(10);
//                mChart.setDrawEntryLabels(true);
                ngaybatdau = edtStart.getText().toString();
                ngayketthuc = edtEnd.getText().toString();
//                addDataSet(mChart);
//                mChart.setOnChartValueSelectedListener(this);
//                int doanhthu = top10vDoanhThuDAO.getDoanhThu(ngaybatdau, ngayketthuc);
//                txtKetQua.setText(doanhthu + " VND");
                doanhthu = top10vDoanhThuDAO.getDoanhThu(ngaybatdau, ngayketthuc);
                txtKetQua.setText(doanhthu + " VND");
            }
        });
        PieChart mChart = view.findViewById(R.id.piechart);
        mChart.setRotationEnabled(true);
        mChart.setDescription(new Description());
        mChart.setHoleRadius(35f);
        mChart.setTransparentCircleAlpha(0);
        mChart.setCenterText("Thống kê");
        mChart.setCenterTextSize(10);
        mChart.setDrawEntryLabels(true);
//        ngaybatdau = edtStart.getText().toString();
//        ngayketthuc = edtEnd.getText().toString();
        addDataSet(mChart);
        mChart.setOnChartValueSelectedListener(this);
//        int doanhthu = top10vDoanhThuDAO.getDoanhThu(ngaybatdau, ngayketthuc);
//        txtKetQua.setText(doanhthu + " VND");
//        btnThongKe.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View view) {
////                PieChart mChart = view.findViewById(R.id.piechart);
////                mChart.setRotationEnabled(true);
////                mChart.setDescription(new Description());
////                mChart.setHoleRadius(35f);
////                mChart.setTransparentCircleAlpha(0);
////                mChart.setCenterText("Thống kê");
////                mChart.setCenterTextSize(10);
////                mChart.setDrawEntryLabels(true);
//                ngaybatdau = edtStart.getText().toString();
//                ngayketthuc = edtEnd.getText().toString();
////                addDataSet(mChart);
////                mChart.setOnChartValueSelectedListener(this);
////                int doanhthu = top10vDoanhThuDAO.getDoanhThu(ngaybatdau, ngayketthuc);
////                txtKetQua.setText(doanhthu + " VND");
//            }
//        });
        return view;
    }
    private void addDataSet(PieChart pieChart) {
        ArrayList<PieEntry> entrys = new ArrayList<>();
        int yData = 110000;
        String xData = "Doanh Thu";
            entrys.add(new PieEntry(yData, xData));
        PieDataSet pieDataSet=new PieDataSet(entrys," ");
        pieDataSet.setSliceSpace(2);
        pieDataSet.setValueTextSize(12);
        ArrayList<Integer> colors=new ArrayList<>();
        colors.add(Color.BLUE);
        colors.add(Color.RED);
        pieDataSet.setColors(colors);
        Legend legend=pieChart.getLegend();
        legend.setForm(Legend.LegendForm.CIRCLE);
        PieData pieData=new PieData(pieDataSet);
        pieChart.setData(pieData);
        pieChart.invalidate();
    }

    @Override
    public void onValueSelected(Entry e, Highlight h) {

    }

    @Override
    public void onNothingSelected() {

    }
}