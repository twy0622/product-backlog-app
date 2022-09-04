package com.example.software;

import androidx.annotation.NonNull;
import androidx.appcompat.app.ActionBarDrawerToggle;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;

import androidx.appcompat.widget.Toolbar;
import androidx.core.view.GravityCompat;
import androidx.drawerlayout.widget.DrawerLayout;

public class AddTask extends AppCompatActivity {
    private DrawerLayout drawer;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.add_task);

        Toolbar toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        drawer = findViewById(R.id.drawer_layout);

        ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(this, drawer, toolbar,
                R.string.navigation_drawer_open, R.string.navigation_drawer_close);
        drawer.addDrawerListener(toggle);
        toggle.syncState();
    }

    @Override
    public void onBackPressed() {
       if (drawer.isDrawerOpen(GravityCompat.START)) {
           drawer.closeDrawer(GravityCompat.START);
        }
       super.onBackPressed();
    }

    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        int id = item.getItemId();

        if (id == R.id.goToAddTask) {
            Intent addTaskIntent = new Intent(this, AddTask.class);
            startActivity(addTaskIntent);
        } else if (id == R.id.goToProductBackLog) {
            Intent productBackLogIntent = new Intent(this, ProductBacklog.class);
            startActivity(productBackLogIntent);
        } //else if (id == R.id.goToSprintBoard) {
//            Intent sprintBoardIntent = new Intent(this, SprintBoard.class);
//            startActivity(sprintBoardIntent);
        //} else if (id == R.id.goToTeamMembers) {
//            Intent teamMembersIntent = new Intent(this, TeamMembers.class);
//            startActivity(teamMembersIntent);
        //}

        drawer.closeDrawer(GravityCompat.START);
        return true;
    }
}