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
    final Calendar myCalendar= Calendar.getInstance();
    EditText sprintDateInput;
    EditText sprintNameInput;
    Button addSprint;

    static TaskViewModel mTaskViewModel;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.add_sprint);
        sprintDateInput= findViewById(R.id.chooseSprintDate);
        sprintNameInput = findViewById(R.id.memberNameInput);
        addSprint = findViewById(R.id.addSprintButton);

        mTaskViewModel = new ViewModelProvider(this).get(TaskViewModel.class);


        DatePickerDialog.OnDateSetListener date = (view, year, month, day) -> {
            myCalendar.set(Calendar.YEAR, year);
            myCalendar.set(Calendar.MONTH,month);
            myCalendar.set(Calendar.DAY_OF_MONTH,day);
            updateLabel();
        };
        sprintDateInput.setOnClickListener(view -> new DatePickerDialog(AddSprint.this,date,myCalendar.get(Calendar.YEAR),myCalendar.get(Calendar.MONTH),myCalendar.get(Calendar.DAY_OF_MONTH)).show());

        addSprint.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String sprintNameInString = sprintNameInput.getText().toString();
                String sprintDateInString = sprintDateInput.getText().toString();

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



    private void updateLabel(){
        String myFormat="yyyy/MM/dd";
        SimpleDateFormat dateFormat=new SimpleDateFormat(myFormat, Locale.US);
        sprintDateInput.setText(dateFormat.format(myCalendar.getTime()));
    }
}

