package com.example.software;

import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.ViewModelProvider;

import android.app.DatePickerDialog;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.example.software.provider.Sprint;
import com.example.software.provider.TaskViewModel;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
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

        DatePickerDialog.OnDateSetListener start_date = (view, year, month, day) -> {
            myStartCalendar.set(Calendar.YEAR, year);
            myStartCalendar.set(Calendar.MONTH, month);
            myStartCalendar.set(Calendar.DAY_OF_MONTH, day);
            updateStartLabel();
        };
        DatePickerDialog.OnDateSetListener end_date = (view, year, month, day) -> {
            myEndCalendar.set(Calendar.YEAR, year);
            myEndCalendar.set(Calendar.MONTH, month);
            myEndCalendar.set(Calendar.DAY_OF_MONTH, day);
            updateEndLabel();
        };
        startDateInput.setOnClickListener(view -> new DatePickerDialog(Dashboard.this,start_date,myStartCalendar.get(Calendar.YEAR),myStartCalendar.get(Calendar.MONTH),myStartCalendar.get(Calendar.DAY_OF_MONTH)).show());
        endDateInput.setOnClickListener(view -> new DatePickerDialog(Dashboard.this,end_date,myEndCalendar.get(Calendar.YEAR),myEndCalendar.get(Calendar.MONTH),myEndCalendar.get(Calendar.DAY_OF_MONTH)).show());

        Button selectRange = findViewById(R.id.selectRange);
        selectRange.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (myStartCalendar.get(Calendar.MONTH) == myEndCalendar.get(Calendar.MONTH)) {
                    dateRange = myEndCalendar.get(Calendar.DAY_OF_MONTH) - myStartCalendar.get(Calendar.DAY_OF_MONTH) + 1;
                } else {

                    int difference = myEndCalendar.get(Calendar.MONTH) - myStartCalendar.get(Calendar.MONTH);
                    int lastDateOfMonth = myStartCalendar.getActualMaximum(Calendar.DAY_OF_MONTH);
                    int bounded = lastDateOfMonth * difference;
                    dateRange = bounded + myEndCalendar.get(Calendar.DAY_OF_MONTH) - myStartCalendar.get(Calendar.DAY_OF_MONTH) + 1;
                }
                Toast.makeText(getApplicationContext(),String.valueOf(dateRange),Toast.LENGTH_SHORT).show();
            }
        });


    }

    private void updateStartLabel () {
        String myFormat = "dd/MM/yyyy";
        SimpleDateFormat dateFormat = new SimpleDateFormat(myFormat, Locale.US);
        startDateInput.setText(dateFormat.format(myStartCalendar.getTime()));
    }
    private void updateEndLabel () {
        String myFormat = "dd/MM/yyyy";
        SimpleDateFormat dateFormat = new SimpleDateFormat(myFormat, Locale.US);
        endDateInput.setText(dateFormat.format(myEndCalendar.getTime()));
    }
}