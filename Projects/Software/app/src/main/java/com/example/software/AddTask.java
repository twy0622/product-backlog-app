package com.example.software;

import androidx.annotation.NonNull;
import androidx.appcompat.app.ActionBarDrawerToggle;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.MenuItem;

import androidx.appcompat.widget.Toolbar;
import androidx.core.view.GravityCompat;
import androidx.drawerlayout.widget.DrawerLayout;

import com.google.android.material.navigation.NavigationView;

public class AddTask extends AppCompatActivity {
    private DrawerLayout drawer;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.drawer_layout);

        Toolbar toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        DrawerLayout drawer = findViewById(R.id.drawer_layout);

        ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(this, drawer, toolbar,
                R.string.navigation_drawer_open, R.string.navigation_drawer_close);
        drawer.addDrawerListener(toggle);
        toggle.syncState();

        NavigationView navigationView = findViewById(R.id.navigationView);
        navigationView.setNavigationItemSelectedListener(new NavigationView.OnNavigationItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected(@NonNull MenuItem item) {
                int id = item.getItemId();

                if (id == R.id.goToAddTask) {
                    Intent addTaskIntent = new Intent(getApplicationContext(), AddTask.class);
                    startActivity(addTaskIntent);
                } else if (id == R.id.goToProductBackLog) {
                    Intent productBackLogIntent = new Intent(getApplicationContext(), ProductBacklog.class);
                    startActivity(productBackLogIntent);
                } //else if (id == R.id.goToSprintBoard) {
//            Intent sprintBoardIntent = new Intent(getApplicationContext(), SprintBoard.class);
//            startActivity(sprintBoardIntent);
                //} else if (id == R.id.goToTeamMembers) {
//            Intent teamMembersIntent = new Intent(getApplicationContext(), TeamMembers.class);
//            startActivity(teamMembersIntent);
                //}

                drawer.closeDrawer(GravityCompat.START);
                return true;
            }
        });


    }

    @Override
    public void onBackPressed() {
       if (drawer.isDrawerOpen(GravityCompat.START)) {
           drawer.closeDrawer(GravityCompat.START);
        }
       super.onBackPressed();
    }


}