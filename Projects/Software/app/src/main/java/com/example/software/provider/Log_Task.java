package com.example.software.provider;

import static androidx.room.ForeignKey.CASCADE;

import androidx.annotation.NonNull;
import androidx.room.ColumnInfo;
import androidx.room.Embedded;
import androidx.room.Entity;
import androidx.room.ForeignKey;
import androidx.room.Index;
import androidx.room.PrimaryKey;
import androidx.room.Relation;

@Entity(tableName= "log_task", indices = {@Index(value = {"logId"}, unique = true)},foreignKeys = @ForeignKey(entity = Task.class, parentColumns = "taskId",
                childColumns = "taskIdFK",
                onDelete = ForeignKey.CASCADE,
                onUpdate = ForeignKey.CASCADE))
public class Log_Task {
    @PrimaryKey(autoGenerate = true)
    @NonNull
    private int logId;

    private int taskIdFK;

    private String taskDate;

    private int taskHours;

    public int getTaskIdFK() {
        return taskIdFK;
    }

    public int getTaskHours() {
        return taskHours;
    }

    public String getTaskDate() {
        return taskDate;
    }

    public int getLogId() {
        return logId;
    }

    public Log_Task(String taskDate, int taskHours) {
        this.taskDate = taskDate;
        this.taskHours = taskHours;
    }

    public void setLogId(int logId) {
        this.logId = logId;
    }

    public void setTaskIdFK(int taskIdFK) {
        this.taskIdFK = taskIdFK;
    }

    public void setTaskDate(String date) {
        this.taskDate = date;
    }

    public void setTaskHours(int hours) {
        this.taskHours = hours;
    }

}