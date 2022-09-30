package com.example.software;
import android.content.Intent;
import android.os.Bundle;
import android.view.MenuItem;
import android.widget.TextView;


import androidx.annotation.NonNull;
import androidx.appcompat.app.ActionBarDrawerToggle;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.view.GravityCompat;
import androidx.drawerlayout.widget.DrawerLayout;
import androidx.lifecycle.ViewModelProvider;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import androidx.appcompat.widget.Toolbar;

import com.example.software.provider.Sprint;
import com.example.software.provider.TaskViewModel;
import com.google.android.material.navigation.NavigationView;

import java.util.ArrayList;

public class SprintBoard extends AppCompatActivity implements NavigationView.OnNavigationItemSelectedListener {

//    private Sprint mSprintViewModel;
//
    ArrayList<Sprint> sprint = new ArrayList<>();
    RecyclerView recyclerView;
    RecyclerView.LayoutManager layoutManager;
    TextView stories;
    TextView date;
    DrawerLayout drawerLayout;
    Toolbar toolbar;
    SprintBoardAdapter adapter;

    TaskViewModel mSprintViewModel;





    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.drawer_layout_sprint_board);

        toolbar = findViewById(R.id.toolbar_sprintboard);
        setSupportActionBar(toolbar);

        drawerLayout = findViewById(R.id.drawer_layout_sprint_board);

        ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(
                this, drawerLayout, toolbar,
                R.string.navigation_drawer_open, R.string.navigation_drawer_close);
        drawerLayout.addDrawerListener(toggle);
        toggle.syncState();

        NavigationView navigationView = findViewById(R.id.navView_sprint_board);
        navigationView.setNavigationItemSelectedListener(this);
//
//        DatabaseHelper databaseHelper = new DatabaseHelper(this);

        recyclerView = findViewById(R.id.sprint_board_recyclerView);

        layoutManager = new LinearLayoutManager(this);
        recyclerView.setLayoutManager(layoutManager);

        //List<Sprint> allSprints = new databaseHelper.getAllSprint();

        adapter = new SprintBoardAdapter(this);
        adapter.setSprint(sprint);
        recyclerView.setAdapter(adapter);

        mSprintViewModel = new ViewModelProvider(this).get(TaskViewModel.class);

        mSprintViewModel.getAllSprints().observe(this, newData-> {
            adapter.setSprint(newData);
            adapter.notifyDataSetChanged();

        });





    }

    @Override
    public boolean onNavigationItemSelected(@NonNull MenuItem item) {
        int id = item.getItemId();

        if (id == R.id.goToAddTask) {
            Intent addTaskIntent = new Intent(getApplicationContext(), AddTask.class);
            startActivity(addTaskIntent);
        }
        else if (id == R.id.goToProductBackLog) {
            Intent productBacklogIntent = new Intent(getApplicationContext(), ProductBacklog.class);
            startActivity(productBacklogIntent);
        }
        else if (id == R.id.goToSprintBoard) {
//            Intent sprintBoardIntent = new Intent(getApplicationContext(), SprintBoard.class);
//            startActivity(sprintBoardIntent);
        }
        else if (id == R.id.goToTeamMembers) {
//            Intent teamMembersIntent = new Intent(getApplicationContext(), TeamMembers.class);
//            startActivity(teamMembersIntent);
        }

        drawerLayout.closeDrawer(GravityCompat.START);

        return true;
    }






//    public void addingSprint() {
//        EditText sprintName = findViewById(R.id.nameSprint);
//        String name = sprintName.getText().toString();
//        Button sprintDate = findViewById(R.id.sprintCalendar);
//        String date = sprintName.getText().toString();
//
//
////        Sprint sprintItems = new Sprint(name,date);
//
//    }

}
