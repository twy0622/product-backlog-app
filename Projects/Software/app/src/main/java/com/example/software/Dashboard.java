package com.example.software;

import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.ViewModelProvider;

import android.app.DatePickerDialog;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

import com.example.software.provider.Sprint;
import com.example.software.provider.TaskViewModel;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Locale;

public class Dashboard extends AppCompatActivity {
    final Calendar myStartCalendar= Calendar.getInstance();
    final Calendar myEndCalendar= Calendar.getInstance();
    EditText startDateInput;
    EditText endDateInput;
    int dateRange;

//    static TaskViewModel mTaskViewModel;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.dashboard);
        startDateInput = findViewById(R.id.chooseStartDate);
        endDateInput = findViewById(R.id.chooseEndDate);

//        mTaskViewModel = new ViewModelProvider(this).get(TaskViewModel.class);

        DatePickerDialog.OnDateSetListener date = (view, year, month, day) -> {
            myStartCalendar.set(Calendar.YEAR, year);
            myStartCalendar.set(Calendar.MONTH, month);
            myStartCalendar.set(Calendar.DAY_OF_MONTH, day);
            updateLabel();
        };
        startDateInput.setOnClickListener(view -> new DatePickerDialog(Dashboard.this,date,myStartCalendar.get(Calendar.YEAR),myStartCalendar.get(Calendar.MONTH),myStartCalendar.get(Calendar.DAY_OF_MONTH)).show());
        endDateInput.setOnClickListener(view -> new DatePickerDialog(Dashboard.this,date,myEndCalendar.get(Calendar.YEAR),myEndCalendar.get(Calendar.MONTH),myEndCalendar.get(Calendar.DAY_OF_MONTH)).show());

        if (myStartCalendar.get(Calendar.MONTH) == myEndCalendar.get(Calendar.MONTH)) {
            dateRange = myStartCalendar.get(Calendar.DAY_OF_MONTH) - myEndCalendar.get(Calendar.DAY_OF_MONTH) + 1;
        } else {
            int lastDateOfMonth = myStartCalendar.getActualMaximum(Calendar.DAY_OF_MONTH);
            dateRange = lastDateOfMonth + myEndCalendar.get(Calendar.DAY_OF_MONTH) - myStartCalendar.get(Calendar.DAY_OF_MONTH) + 1;
        }
        System.out.println(dateRange);

    }

    private void updateLabel () {
        String myFormat = "dd/MM/yyyy";
        SimpleDateFormat dateFormat = new SimpleDateFormat(myFormat, Locale.US);
        startDateInput.setText(dateFormat.format(myStartCalendar.getTime()));
        endDateInput.setText(dateFormat.format(myEndCalendar.getTime()));
    }
}