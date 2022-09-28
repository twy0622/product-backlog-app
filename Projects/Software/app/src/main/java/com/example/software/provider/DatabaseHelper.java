package com.example.software.provider;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import androidx.annotation.Nullable;

import java.util.ArrayList;
import java.util.List;

public class DatabaseHelper extends SQLiteOpenHelper {

    public static final String DATABASE_NAME = "product_database.db";
    public static final String TASK_TABLE = "TASK_TABLE";
    public static final String SPRINT_TABLE = "SPRINT_TABLE";
    public static final String COLUMN_TASK_CATEGORY = "taskCategory";
    public static final String COLUMN_TASK_NAME = "taskName";
    public static final String COLUMN_TASK_PRIORITY = "taskPriority";
    public static final String COLUMN_TASK_STATUS = "taskStatus";
    public static final String COLUMN_TASK_ASSIGNED = "taskAssigned";
    public static final String COLUMN_TASK_TAG = "taskTag";
    public static final String COLUMN_TASK_STORY_POINTS = "taskStoryPoints";
    public static final String COLUMN_SPRINT_NAME = "sprintName";
    public static final String COLUMN_SPRINT_DATE = "sprintDate";
    public static final String COLUMN_ID = "ID";

    public DatabaseHelper(@Nullable Context context) {
        super(context, "sprint_database.db", null, 1);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        String task_table = "CREATE TABLE " + TASK_TABLE + " (" + COLUMN_ID + " INTEGER PRIMARY KEY AUTOINCREMENT, " +
                COLUMN_TASK_CATEGORY + " TEXT, " + COLUMN_TASK_NAME + " TEXT, " + COLUMN_TASK_PRIORITY
                + " TEXT, " + COLUMN_TASK_STATUS + " TEXT, " + COLUMN_TASK_ASSIGNED + " TEXT, " +
                COLUMN_TASK_TAG + " TEXT, " + COLUMN_TASK_STORY_POINTS + " TEXT)";

        String sprint_table = "CREATE TABLE " + SPRINT_TABLE + " (" + COLUMN_ID + " INTEGER PRIMARY KEY AUTOINCREMENT, " +
                COLUMN_SPRINT_NAME + " TEXT, " + COLUMN_SPRINT_DATE + " TEXT)";
        db.execSQL(task_table);
        db.execSQL(sprint_table);

    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        db.execSQL("DROP TABLE IF EXISTS " + TASK_TABLE);
        db.execSQL("DROP TABLE IF EXISTS " + SPRINT_TABLE);
        onCreate(db);
    }

    public boolean insertSprint(Sprint sprintModel) {
        SQLiteDatabase sqLiteDatabase = this.getWritableDatabase();

        ContentValues cv = new ContentValues();
        cv.put(COLUMN_SPRINT_NAME, sprintModel.getSprintName());
        cv.put(COLUMN_SPRINT_DATE, sprintModel.getSprintDate());

        long insert = sqLiteDatabase.insert(SPRINT_TABLE, null,cv);

        return insert != -1;
    }

    public List<Sprint> getAllSprint() {

        List<Sprint> returnSprintList = new ArrayList<>();

        String queryString = "SELECT * FROM " + SPRINT_TABLE;

        SQLiteDatabase db = this.getReadableDatabase();

        Cursor cursor = db.rawQuery(queryString, null);

        if (cursor.moveToFirst()) {
            //loop through the cursor(result set) and create new sprint objects
            do {
                int SprintID = cursor.getInt(0);
                String SprintName = cursor.getString(1);
                String SprintDate = cursor.getString(2);

                Sprint newSprint = new Sprint(SprintID,SprintName,SprintDate);
                returnSprintList.add(newSprint);
            } while (cursor.moveToNext());
        }
        else {
            //nothing is added to the list here.
        }
        cursor.close();
        db.close();
        return returnSprintList;
    }

}


