package com.example.software.provider;

import static androidx.room.ForeignKey.CASCADE;

import androidx.room.Embedded;
import androidx.room.Entity;
import androidx.room.ForeignKey;
import androidx.room.PrimaryKey;
import androidx.room.Relation;

@Entity(tableName= "log_task", foreignKeys = @ForeignKey(entity = Task.class, parentColumns = "taskId",
                childColumns = "taskIdfk",
                onDelete = CASCADE,
                onUpdate = CASCADE))
public class Log_Task {

    @PrimaryKey(autoGenerate = true)
    private int logTaskID;

    private int taskIdfk;

    private String date;

    private int hours;

    public Log_Task(String date, int hours) {
        this.date = date;
        this.hours = hours;
    }

    public int getLogTaskID() {
        return logTaskID;
    }

    public void setLogTaskID(int logTaskID) {
        this.logTaskID = logTaskID;
    }

    public String getDateTest() {
        return date;
    }

    public void setDateTest(String date) {
        this.date = date;
    }

    public int getHours() {
        return hours;
    }

    public void setHours(int hours) {
        this.hours = hours;
    }
}