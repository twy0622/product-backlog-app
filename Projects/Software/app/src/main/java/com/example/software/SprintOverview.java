package com.example.software;

import androidx.appcompat.app.AppCompatActivity;
import androidx.drawerlayout.widget.DrawerLayout;
import androidx.lifecycle.ViewModelProvider;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;
import android.widget.ArrayAdapter;
import android.widget.Spinner;

import com.example.software.provider.Task;
import com.example.software.provider.TaskViewModel;

import java.util.ArrayList;

public class SprintOverview extends AppCompatActivity {
    RecyclerView completedRecycler;
    ArrayList<Task> taskList;
    RecyclerView.LayoutManager layoutManager;
    RecyclerViewAdapter completedAdapter;
    Spinner tagSpinner;
    static TaskViewModel mTaskViewModel;
    DrawerLayout drawerLayout;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.sprint_overview);

        completedRecycler = findViewById(R.id.completed_recycler_view);

        layoutManager = new LinearLayoutManager(this);
        completedRecycler.setLayoutManager(layoutManager);
        taskList = new ArrayList<>();
        completedAdapter = new RecyclerViewAdapter(this);
        completedRecycler.setAdapter(completedAdapter);
        mTaskViewModel = new ViewModelProvider(this).get(TaskViewModel.class);
        mTaskViewModel.getAllTasks().observe(this, newData -> {
            completedAdapter.setTask(newData);
            completedAdapter.notifyDataSetChanged();
        });
//        ArrayAdapter<String> tagAdapter = new ArrayAdapter<String>(SprintOverview.this,
//                android.R.layout.simple_list_item_1, getResources().getStringArray(R.array.status));
//        String selectedTag = (String) adapterView.getItemAtPosition(i);
//        completedAdapter.getFilter().filter("Completed");
    }

}