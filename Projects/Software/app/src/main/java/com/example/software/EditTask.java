package com.example.software;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;

import com.example.software.provider.DemoAdapter;

import java.util.LinkedList;
import java.util.List;

public class EditTask extends AppCompatActivity {

    String []data = {"Hello", "Hii", "welcome"};
    int counter = 0;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.product_backlog);

        List<String> items = new LinkedList<>();
        items.add("Code It");

        RecyclerView recyclerView = findViewById(R.id.recyclerView);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        DemoAdapter adapter = new DemoAdapter(items);
        recyclerView.setAdapter(adapter);

        findViewById(R.id.add).setOnClickListener(view -> {
            items.add(data[counter%3]);
            counter++;
            adapter.notifyItemInserted(items.size()-1);
        });
    }
}