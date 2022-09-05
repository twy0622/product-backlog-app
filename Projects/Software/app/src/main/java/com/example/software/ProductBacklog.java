package com.example.software;

import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.ViewModelProvider;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import android.os.Bundle;
import android.widget.ArrayAdapter;
import android.widget.Spinner;

import com.example.software.provider.TaskViewModel;

public class ProductBacklog extends AppCompatActivity {
    RecyclerView recyclerView;
    RecyclerView.LayoutManager layoutManager;
    RecyclerViewAdapter adapter;

    static TaskViewModel mTaskViewModel;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.product_backlog);

        //spinner for filtering tags
        Spinner tagFilterSpinner = findViewById(R.id.filterTagBox);
        ArrayAdapter<String> filterTagAdapter = new ArrayAdapter<String>(ProductBacklog.this,
                android.R.layout.simple_list_item_1, getResources().getStringArray(R.array.filterTags));
        filterTagAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        tagFilterSpinner.setAdapter(filterTagAdapter);


//        Button applyFilter = findViewById(R.id.applyFilterButton);

        recyclerView = findViewById(R.id.task_recycler_view);

        layoutManager = new LinearLayoutManager(this);
        recyclerView.setLayoutManager(layoutManager);

        adapter = new RecyclerViewAdapter();
        recyclerView.setAdapter(adapter);

        mTaskViewModel = new ViewModelProvider(this).get(TaskViewModel.class);
        mTaskViewModel.getAllTasks().observe(this, newData -> {
            adapter.setTask(newData);
            adapter.notifyDataSetChanged();
        });
    }
}