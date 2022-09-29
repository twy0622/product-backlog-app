package com.example.software;

import androidx.appcompat.app.AppCompatActivity;
import androidx.drawerlayout.widget.DrawerLayout;
import androidx.lifecycle.ViewModelProvider;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;
import android.widget.Spinner;

import com.example.software.provider.Task;
import com.example.software.provider.TaskViewModel;

import java.util.ArrayList;

public class SprintOverview extends AppCompatActivity {
    RecyclerView completedRecycler;
    RecyclerView inProgressRecycler;
    RecyclerView notStartedRecycler;
    ArrayList<Task> taskList;
    RecyclerView.LayoutManager completedLayoutManager;
    RecyclerView.LayoutManager inProgressLayoutManager;
    RecyclerView.LayoutManager notStartedLayoutManager;
    SprintRecyclerViewAdapter completedAdapter;
    SprintRecyclerViewAdapter inProgressAdapter;
    SprintRecyclerViewAdapter notStartedAdapter;
    static TaskViewModel mTaskViewModel;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.sprint_overview);

        completedRecycler = findViewById(R.id.completed_recycler_view);
        completedLayoutManager = new LinearLayoutManager(this);
        completedRecycler.setLayoutManager(completedLayoutManager);

        inProgressRecycler = findViewById(R.id.in_progress_recycler_view);
        inProgressLayoutManager = new LinearLayoutManager(this);
        inProgressRecycler.setLayoutManager(inProgressLayoutManager);

        notStartedRecycler = findViewById(R.id.not_started_recycler_view);
        notStartedLayoutManager = new LinearLayoutManager(this);
        notStartedRecycler.setLayoutManager(notStartedLayoutManager);

        taskList = new ArrayList<>();

        completedAdapter = new SprintRecyclerViewAdapter(this);
        completedRecycler.setAdapter(completedAdapter);

        inProgressAdapter = new SprintRecyclerViewAdapter(this);
        inProgressRecycler.setAdapter(inProgressAdapter);

        notStartedAdapter = new SprintRecyclerViewAdapter(this);
        notStartedRecycler.setAdapter(notStartedAdapter);

        mTaskViewModel = new ViewModelProvider(this).get(TaskViewModel.class);
        mTaskViewModel.getSprintStatus("Completed").observe(this, newData -> {
            completedAdapter.setTask(newData);
            completedAdapter.notifyDataSetChanged();
        });

        mTaskViewModel.getSprintStatus2("In Progress", "Developing", "Testing").observe(this, newData -> {
            inProgressAdapter.setTask(newData);
            inProgressAdapter.notifyDataSetChanged();
        });

        mTaskViewModel.getSprintStatus("Not Started").observe(this, newData -> {
            notStartedAdapter.setTask(newData);
            notStartedAdapter.notifyDataSetChanged();
        });
    }

}