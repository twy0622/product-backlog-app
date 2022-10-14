package com.example.software;

import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.LiveData;
import androidx.lifecycle.ViewModelProvider;

import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.widget.TextView;


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
import java.util.ArrayList;
import java.util.Date;
import java.util.List;


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

    static TaskViewModel mTaskViewModel;

//    Date today =

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.analytics);

        mTaskViewModel = new ViewModelProvider(this).get(TaskViewModel.class);

        Intent intent = getIntent();
        String name = intent.getStringExtra("name");

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
        barDataSet.setValueTextColor(Color.WHITE);
        barChart.setDrawGridBackground(false);
        barChart.setNoDataTextColor(Color.WHITE);

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
        Date barDate = new Date(today.getYear(), today.getMonth(), today.getDate()-6);
        LiveData<List<Log_Task>> data = mTaskViewModel.getDurationByMemberID(id, barDate);
        float duration = 0;
        for (int i = 0; i < data.getValue().size(); i++) {
            duration += data.getValue().get(i).getTaskHours();
        }

        barEntriesArrayList.add(new BarEntry(1f, duration));

        barDate.setDate(barDate.getDate()+1);
        data = mTaskViewModel.getDurationByMemberID(id, barDate);
        duration = 0;
        if (data != null) {
            for (int i = 0; i < data.getValue().size(); i++) {
                duration += data.getValue().get(i).getTaskHours();
            }
        }
        barEntriesArrayList.add(new BarEntry(2f, duration));

        barDate.setDate(barDate.getDate()+1);
        data = mTaskViewModel.getDurationByMemberID(id, barDate);
        duration = 0;
        if (data != null) {
            for (int i = 0; i < data.getValue().size(); i++) {
                duration += data.getValue().get(i).getTaskHours();
            }
        }
        barEntriesArrayList.add(new BarEntry(3f, duration));

        barDate.setDate(barDate.getDate()+1);
        data = mTaskViewModel.getDurationByMemberID(id, barDate);
        duration = 0;
        if (data != null) {
            for (int i = 0; i < data.getValue().size(); i++) {
                duration += data.getValue().get(i).getTaskHours();
            }
        }
        barEntriesArrayList.add(new BarEntry(4f, duration));

        barDate.setDate(barDate.getDate()+1);
        data = mTaskViewModel.getDurationByMemberID(id, barDate);
        duration = 0;
        if (data != null) {
            for (int i = 0; i < data.getValue().size(); i++) {
                duration += data.getValue().get(i).getTaskHours();
            }
        }
        barEntriesArrayList.add(new BarEntry(5f, duration));

        barDate.setDate(barDate.getDate()+1);
        data = mTaskViewModel.getDurationByMemberID(id, barDate);
        duration = 0;
        if (data != null) {
            for (int i = 0; i < data.getValue().size(); i++) {
                duration += data.getValue().get(i).getTaskHours();
            }
        }
        barEntriesArrayList.add(new BarEntry(6f, duration));

        barDate.setDate(barDate.getDate()+1);
        data = mTaskViewModel.getDurationByMemberID(id, barDate);
        duration = 0;
        if (data != null) {
            for (int i = 0; i < data.getValue().size(); i++) {
                duration += data.getValue().get(i).getTaskHours();
            }
        }
        barEntriesArrayList.add(new BarEntry(7f, duration));
    }
}
