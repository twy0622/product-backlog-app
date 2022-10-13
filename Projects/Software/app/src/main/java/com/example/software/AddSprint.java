package com.example.software;

import android.app.DatePickerDialog;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.ViewModelProvider;

import com.example.software.provider.Sprint;
import com.example.software.provider.TaskViewModel;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Locale;

public class AddSprint extends AppCompatActivity {
    final Calendar myStartSprintCalendar = Calendar.getInstance();
    final Calendar myEndSprintCalendar = Calendar.getInstance();
    EditText sprintStartDateInput;
    EditText sprintEndDateInput;
    EditText sprintNameInput;
    Button addSprint;

    static TaskViewModel mTaskViewModel;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.add_sprint);
        sprintStartDateInput = findViewById(R.id.chooseSprintStartDate);
        sprintStartDateInput = findViewById(R.id.chooseSprintEndDate);
        sprintNameInput = findViewById(R.id.memberNameInput);
        addSprint = findViewById(R.id.addSprintButton);

        mTaskViewModel = new ViewModelProvider(this).get(TaskViewModel.class);


        DatePickerDialog.OnDateSetListener date = (view, year, month, day) -> {
            myStartSprintCalendar.set(Calendar.YEAR, year);
            myStartSprintCalendar.set(Calendar.MONTH,month);
            myStartSprintCalendar.set(Calendar.DAY_OF_MONTH,day);
            updateSprintStartLabel();
        };

        DatePickerDialog.OnDateSetListener end_date = (view, year, month, day) -> {
            myEndSprintCalendar.set(Calendar.YEAR, year);
            myEndSprintCalendar.set(Calendar.MONTH, month);
            myEndSprintCalendar.set(Calendar.DAY_OF_MONTH, day);
            updateSprintEndLabel();
        };

        sprintStartDateInput.setOnClickListener(view -> new DatePickerDialog(AddSprint.this,date, myStartSprintCalendar.get(Calendar.YEAR), myStartSprintCalendar.get(Calendar.MONTH), myStartSprintCalendar.get(Calendar.DAY_OF_MONTH)).show());
//        sprintEndDateInput.setOnClickListener(view -> new DatePickerDialog(AddSprint.this,date, myEndSprintCalendar.get(Calendar.YEAR), myEndSprintCalendar.get(Calendar.MONTH), myEndSprintCalendar.get(Calendar.DAY_OF_MONTH)).show());


        addSprint.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String sprintNameInString = sprintNameInput.getText().toString();
                String sprintDateInString = sprintStartDateInput.getText().toString();

                Sprint sprint = new Sprint(sprintNameInString, sprintDateInString);
                mTaskViewModel.addSprint(sprint);

                Toast.makeText(AddSprint.this, "" + sprintNameInString + " has been" +
                        " added.", Toast.LENGTH_SHORT).show();

                finish();
//
//                TaskViewModel databaseHelper = new TaskViewModel(AddSprint.this);
//
//                boolean success = databaseHelper.insertSprint(sprintAdding);
            }
        });


    }



    private void updateSprintStartLabel(){
        String myFormat="yyyy/MM/dd";
        SimpleDateFormat dateFormat=new SimpleDateFormat(myFormat, Locale.US);
        sprintStartDateInput.setText(dateFormat.format(myStartSprintCalendar.getTime()));
    }

    private void updateSprintEndLabel(){
        String myFormat="yyyy/MM/dd";
        SimpleDateFormat dateFormat=new SimpleDateFormat(myFormat, Locale.US);
        sprintEndDateInput.setText(dateFormat.format(myEndSprintCalendar.getTime()));
    }
}

