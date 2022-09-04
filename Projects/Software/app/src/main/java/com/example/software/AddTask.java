package com.example.software;

import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.ViewModelProvider;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.Spinner;
import android.widget.Toast;

import com.example.software.provider.Task;
import com.example.software.provider.TaskViewModel;

import java.util.ArrayList;

public class AddTask extends AppCompatActivity {

    private RadioGroup radioCategoryGroup;

    static TaskViewModel mTaskViewModel;
//    ArrayList<String> taskList = new ArrayList<String>();
//    ArrayAdapter myAdapter;
    RecyclerViewAdapter adapter;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.add_task);

        adapter = new RecyclerViewAdapter();
        mTaskViewModel = new ViewModelProvider(this).get(TaskViewModel.class);
        mTaskViewModel.getAllTasks().observe(this, newData -> {
            adapter.setTask(newData);
            adapter.notifyDataSetChanged();
        });

        // Dropdown list Values
        Spinner prioritySpinner = (Spinner) findViewById(R.id.priorityBox);
        ArrayAdapter<String> priorityAdapter = new ArrayAdapter<String>(AddTask.this,
                android.R.layout.simple_list_item_1, getResources().getStringArray(R.array.priority));
        priorityAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        prioritySpinner.setAdapter(priorityAdapter);

        Spinner statusSpinner = (Spinner) findViewById(R.id.statusBox);
        ArrayAdapter<String> statusAdapter = new ArrayAdapter<String>(AddTask.this,
                android.R.layout.simple_list_item_1, getResources().getStringArray(R.array.status));
        statusAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        statusSpinner.setAdapter(statusAdapter);

        Spinner assignSpinner = (Spinner) findViewById(R.id.assignedBox);
        ArrayAdapter<String> assignAdapter = new ArrayAdapter<String>(AddTask.this,
                android.R.layout.simple_list_item_1, getResources().getStringArray(R.array.assigned));
        assignAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        assignSpinner.setAdapter(assignAdapter);

        Spinner tagSpinner = (Spinner) findViewById(R.id.tagBox);
        ArrayAdapter<String> tagAdapter = new ArrayAdapter<String>(AddTask.this,
                android.R.layout.simple_list_item_1, getResources().getStringArray(R.array.tags));
        tagAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        tagSpinner.setAdapter(tagAdapter);

        Button create = findViewById(R.id.createButton);
        create.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                radioCategoryGroup = (RadioGroup) findViewById(R.id.radioCategory);
                int selectedId = radioCategoryGroup.getCheckedRadioButtonId();
                RadioButton taskCategory = (RadioButton)findViewById(selectedId);
                String category = taskCategory.getText().toString();

                EditText taskName = findViewById(R.id.nameBox);
                String name = taskName.getText().toString();

                Spinner taskPriority = (Spinner)findViewById(R.id.priorityBox);
                String priority = taskPriority.getSelectedItem().toString();

                Spinner taskStatus = (Spinner)findViewById(R.id.statusBox);
                String status = taskStatus.getSelectedItem().toString();

                Spinner taskAssigned = (Spinner)findViewById(R.id.assignedBox);
                String assigned = taskAssigned.getSelectedItem().toString();

                Spinner taskTag = (Spinner)findViewById(R.id.tagBox);
                String tag = taskTag.getSelectedItem().toString();

                EditText taskSP = findViewById(R.id.storyPointsBox);
                int sp = Integer.valueOf(taskSP.getText().toString());

                EditText taskDescription = findViewById(R.id.descriptionBox);
                String description = taskDescription.getText().toString();

                Task task = new Task(category,name,description,priority,status,assigned,tag,sp);

//                 causing crash cause of incomplete database code
                mTaskViewModel.insert(task);

                Toast myMessage = Toast.makeText(getApplicationContext(),
                        "Task successfully created.",Toast.LENGTH_SHORT);
                myMessage.show();

                // reset fields after creating a task
                radioCategoryGroup.clearCheck();
                taskName.setText("");
                taskPriority.setSelection(0);
                taskStatus.setSelection(0);
                taskAssigned.setSelection(0);
                taskTag.setSelection(0);
                taskSP.setText("");
                taskDescription.setText("");
            }
        });

        Button productBacklog = findViewById(R.id.goNext);
        productBacklog.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(getApplicationContext(), ProductBacklog.class);
                startActivity(intent);
            }
        });
    }

}