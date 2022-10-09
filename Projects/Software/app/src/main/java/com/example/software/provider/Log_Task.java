package com.example.software.provider;

import static androidx.room.ForeignKey.CASCADE;

import androidx.room.Embedded;
import androidx.room.Entity;
import androidx.room.ForeignKey;
import androidx.room.PrimaryKey;
import androidx.room.Relation;

@Entity(tableName= "log_task", foreignKeys = @ForeignKey(entity = Task.class, parentColumns = "taskId",
                childColumns = "logTaskID",
                onDelete = CASCADE,
                onUpdate = CASCADE))
public class Log_Task {

    @PrimaryKey(autoGenerate = true)
    private int logTaskID;

//    private int taskIdfk;

    private String taskDate;

    private int taskHours;

    public int getLogTaskID() {
        return logTaskID;
    }

    public int getTaskHours() {
        return taskHours;
    }

    public String getTaskDate() {
        return taskDate;
    }

    public Log_Task(String taskDate, int taskHours) {
        this.taskDate = taskDate;
        this.taskHours = taskHours;
    }


    public void setLogTaskID(int logTaskID) {
        this.logTaskID = logTaskID;
    }

    public void setTaskDate(String date) {
        this.taskDate = date;
    }

    public void setTaskHours(int hours) {
        this.taskHours = hours;
    }

}