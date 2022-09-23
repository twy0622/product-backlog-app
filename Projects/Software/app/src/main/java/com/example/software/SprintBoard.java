package com.example.software;

import android.database.Cursor;
import android.database.DatabaseUtils;
import android.database.sqlite.SQLiteException;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.ViewModelProvider;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import androidx.room.Database;
import androidx.room.Room;
import androidx.sqlite.db.SupportSQLiteDatabase;


import com.example.software.provider.BaseEntity;
import com.example.software.provider.BaseEntityDao;
import com.example.software.provider.MultiDatabase;

import java.util.ArrayList;

public class SprintBoard extends AppCompatActivity {

//    private SprintViewModel mSprintViewModel;
//
//    ArrayList<Sprint> sprint = new ArrayList<>();
    RecyclerView recyclerView;
    RecyclerView.LayoutManager layoutManager;
    SprintRecyclerViewAdapter adapter;
    TextView stories;
    TextView date;

    MultiDatabase mDB;
    BaseEntityDao mDao;




    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.sprint_board);

        recyclerView = findViewById(R.id.sprintRecyclerView);

        layoutManager = new LinearLayoutManager(this);
        recyclerView.setLayoutManager(layoutManager);

        mDB = Room.databaseBuilder(this,MultiDatabase.class,"basedb")
                .allowMainThreadQueries()
                .build();
        mDao = mDB.baseEntityDao();
        addSomeDataViaRoom();
        String dynamicTableName = "testing";
        addTable(dynamicTableName);
        addSomeDataOutsideOfRoom(dynamicTableName);
        SupportSQLiteDatabase sdb = mDB.getOpenHelper().getWritableDatabase();
        Cursor csr = sdb.query("SELECT * FROM " + BaseEntity.BASETABLE_NAME);
        DatabaseUtils.dumpCursor(csr);
        csr = sdb.query("SELECT * FROM " + dynamicTableName);
        DatabaseUtils.dumpCursor(csr);
        mDB.close();

//        adapter = new SprintRecyclerViewAdapter();
//        adapter.setSprint(sprint);
//        recyclerView.setAdapter(adapter);

//        mSprintViewModel = new ViewModelProvider(this).get(SprintViewModel.class);
//
//        mSprintViewModel.getAllSprint().observe(this, newData-> {
//            adapter.setSprint(newData);
//            adapter.notifyDataSetChanged();

        }

    private boolean addTable(String tableName) {

        SupportSQLiteDatabase sdb = mDB.getOpenHelper().getWritableDatabase();
        try {
            sdb.execSQL(BaseEntity.BASETABLE_CREATE_SQL.replace(BaseEntity.BASETABLE_NAME_PLACEHOLDER, tableName));
        } catch (SQLiteException e) {
            return false;
        }
        return true;
    }

    private void addSomeDataViaRoom() {
        if (mDao.getRowCount() > 0) return;
        mDao.insertRow("A");
        mDao.insertRow("B");
        mDao.insertRow("C");
    }

    private void addSomeDataOutsideOfRoom(String tableName) {
        SupportSQLiteDatabase sdb = mDB.getOpenHelper().getWritableDatabase();
        if (BaseEntity.getTableRowCount(sdb,tableName) > 0) return;
        BaseEntity.insertRow(sdb,tableName,"X");
        BaseEntity.insertRow(sdb,tableName,"Y");
        BaseEntity.insertRow(sdb,tableName,"Z");
    }




    public void addingSprint() {
        EditText sprintName = findViewById(R.id.nameSprint);
        String name = sprintName.getText().toString();
        Button sprintDate = findViewById(R.id.sprintCalendar);
        String date = sprintName.getText().toString();


//        Sprint sprintItems = new Sprint(name,date);
        
    }

}
