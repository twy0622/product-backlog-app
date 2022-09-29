package com.example.software;

import android.app.DatePickerDialog;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.example.software.provider.Sprint;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Locale;

public class AddSprint extends AppCompatActivity {
    final Calendar myCalendar= Calendar.getInstance();
    EditText sprintDateInput;
    EditText sprintNameInput;
    Button addSprint;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.add_sprint);
        sprintDateInput= findViewById(R.id.chooseDate);
        sprintNameInput = findViewById(R.id.sprintNameInput);
        addSprint = findViewById(R.id.addSprintButton);

        addSprint.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String sprintNameinString = sprintNameInput.getText().toString();
                String sprintDateinString = sprintDateInput.getText().toString();
                Sprint sprintAdding = new Sprint(0, sprintNameinString, sprintDateinString);


                Toast.makeText(AddSprint.this, "" + sprintNameinString + " has been" +
                        " added.", Toast.LENGTH_SHORT).show();
//
//                TaskViewModel databaseHelper = new TaskViewModel(AddSprint.this);
//
//                boolean success = databaseHelper.insertSprint(sprintAdding);
            }
        });


        DatePickerDialog.OnDateSetListener date = (view, year, month, day) -> {
            myCalendar.set(Calendar.YEAR, year);
            myCalendar.set(Calendar.MONTH,month);
            myCalendar.set(Calendar.DAY_OF_MONTH,day);
            updateLabel();
        };
        sprintDateInput.setOnClickListener(view -> new DatePickerDialog(AddSprint.this,date,myCalendar.get(Calendar.YEAR),myCalendar.get(Calendar.MONTH),myCalendar.get(Calendar.DAY_OF_MONTH)).show());


    }



    private void updateLabel(){
        String myFormat="dd/MM/yyyy";
        SimpleDateFormat dateFormat=new SimpleDateFormat(myFormat, Locale.US);
        sprintDateInput.setText(dateFormat.format(myCalendar.getTime()));
    }
}

