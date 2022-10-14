package com.example.software;

import static com.example.software.SprintOverview.mTaskViewModel;

import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.LiveData;
import androidx.lifecycle.ViewModelProvider;

import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.widget.TextView;
import android.widget.Toast;


import com.example.software.provider.Log_Task;
import com.example.software.provider.TaskViewModel;
import com.github.mikephil.charting.charts.BarChart;
import com.github.mikephil.charting.components.XAxis;
import com.github.mikephil.charting.data.BarData;
import com.github.mikephil.charting.data.BarDataSet;
import com.github.mikephil.charting.data.BarEntry;
import com.github.mikephil.charting.formatter.ValueFormatter;
import com.github.mikephil.charting.utils.ColorTemplate;

import java.lang.reflect.Array;
import java.text.ParseException;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;


public class Analytics extends AppCompatActivity {

    // variable for our bar chart
    BarChart barChart;

    // variable for our bar data.
    BarData barData;

    // variable for our bar data set.
    BarDataSet barDataSet;

    // array list for storing entries.
    ArrayList barEntriesArrayList = new ArrayList();

    //member id
    int id;
    String name;

    static TaskViewModel mTaskViewModel;
    ExecutorService executorService1 = Executors.newFixedThreadPool(8);
    int data1;
    int data2;
    int data3;
    int data4;
    int data5;
    int data6;
    int data7;

//    Date today =

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.analytics);



        mTaskViewModel = new ViewModelProvider(this).get(TaskViewModel.class);

        Intent intent = getIntent();
        name = intent.getStringExtra("name");

        id = intent.getIntExtra("keyID",0);

        // setting page title
        TextView title = findViewById(R.id.analytics_title);
        title.setText(name + " Analytics");

        // initializing variable for bar chart.
        barChart = findViewById(R.id.idBarChart);

        // calling method to get bar entries.
        getBarEntries();

        // creating a new bar data set.
        barDataSet = new BarDataSet(barEntriesArrayList, "Past 7 days time spent");

        // creating a new bar data and
        // passing our bar data set.
        barData = new BarData(barDataSet);

        // below line is to set data
        // to our bar chart.
        barChart.setData(barData);

        // adding color to our bar data set.
        barDataSet.setColors(ColorTemplate.MATERIAL_COLORS);

        // setting color.
        barDataSet.setValueTextColor(Color.BLACK);
        barChart.setDrawGridBackground(false);

        // setting text size
        barDataSet.setValueTextSize(16f);
        barChart.getDescription().setEnabled(false);
        barChart.setBackgroundColor(Color.parseColor("#FFFFFF"));
        XAxis xAxis = barChart.getXAxis();
        xAxis.setValueFormatter(new ValueFormatter() {
            @Override
            public String getFormattedValue(float value) {
                String[] DAYS = { "SUN", "MON", "TUE", "WED", "THU", "FRI", "SAT" };
                Date today = new Date();
                return DAYS[ ((int) (value + today.getDay())) % 7];
            }
        });
    }
    private void getBarEntries() {
        // creating a new array list
        barEntriesArrayList = new ArrayList<>();
        // adding new entry to our array list with bar
        // entry and passing x and y axis value to it.

        Date today = new Date();
        Date barDate1 = new Date(today.getYear(), today.getMonth(), today.getDate()-6);
//        executorService1.execute(new Runnable() {
//            @Override
//            public void run() {
//                data1 = mTaskViewModel.getDurationByMemberID(id, barDate1);
//            }
//        });
        barEntriesArrayList.add(new BarEntry(1f, data1));


        Date barDate2 = new Date(today.getYear(), today.getMonth(), today.getDate()-5);
//        executorService1.execute(new Runnable() {
//            @Override
//            public void run() {
//                data2 = mTaskViewModel.getDurationByMemberID(id, barDate2);
//            }
//        });
        barEntriesArrayList.add(new BarEntry(2f, Integer.parseInt(String.valueOf(data2))));


        Date barDate3 = new Date(today.getYear(), today.getMonth(), today.getDate()-4);
        executorService1.execute(new Runnable() {
            @Override
            public void run() {
                data3 = mTaskViewModel.getDurationByMemberID(id, barDate3);
            }
        });
        barEntriesArrayList.add(new BarEntry(3f, Integer.parseInt(""+data3)));


        Date barDate4 = new Date(today.getYear(), today.getMonth(), today.getDate()-3);
        executorService1.execute(new Runnable() {
            @Override
            public void run() {
                data4 = mTaskViewModel.getDurationByMemberID(id, barDate4);
            }
        });
        barEntriesArrayList.add(new BarEntry(4f, data4));


        Date barDate5 = new Date(today.getYear(), today.getMonth(), today.getDate()-2);
        executorService1.execute(new Runnable() {
            @Override
            public void run() {
                data5 = mTaskViewModel.getDurationByMemberID(id, barDate5);
            }
        });
        barEntriesArrayList.add(new BarEntry(5f, data5));


        Date barDate6 = new Date(today.getYear(), today.getMonth(), today.getDate()-1);
        executorService1.execute(new Runnable() {
            @Override
            public void run() {
                data6 = mTaskViewModel.getDurationByMemberID(id, barDate6);
            }
        });
        barEntriesArrayList.add(new BarEntry(6f, data6));


        Date barDate7 = new Date(today.getYear(), today.getMonth(), today.getDate());
        executorService1.execute(new Runnable() {
            @Override
            public void run() {
                data7 = mTaskViewModel.getDurationByMemberID(id, barDate7);
            }
        });
        barEntriesArrayList.add(new BarEntry(7f, data7));


        Toast.makeText(getApplicationContext(), String.valueOf(data3), Toast.LENGTH_LONG).show();
    }
}
