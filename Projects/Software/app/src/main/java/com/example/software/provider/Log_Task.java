package com.example.software.provider;

import androidx.annotation.NonNull;
import androidx.room.ColumnInfo;
import androidx.room.Entity;
import androidx.room.PrimaryKey;

@Entity(tableName = "log_tasks")
public class Log_Task {
    @PrimaryKey(autoGenerate = true)
    @NonNull
    @ColumnInfo(name = "logTaskId")
    private int logTaskId;

    @ColumnInfo(name = "logTaskCategory")
    private String logCategory;

    @ColumnInfo(name = "logTaskName")
    private String logName;

    @ColumnInfo(name = "logTaskPriority")
    private String logPriority;

    @ColumnInfo(name = "logTaskStatus")
    private String logStatus;

    @ColumnInfo(name = "logTaskAssigned")
    private String logAssigned;

    @ColumnInfo(name = "logTaskTag")
    private String logTag;

    @ColumnInfo(name = "logTaskStoryPoints")
    private int logStoryPoints;

    @ColumnInfo(name = "logTaskDescription")
    private String logDescription;

    @ColumnInfo(name = "logHours")
    private int logHours;

    public Log_Task(String logCategory, String logName, String logPriority, String logStatus, String
            logAssigned, String logTag, int logStoryPoints, String logDescription, int logHours) {
        this.logCategory = logCategory;
        this.logName = logName;
        this.logPriority = logPriority;
        this.logStatus = logStatus;
        this.logAssigned = logAssigned;
        this.logTag = logTag;
        this.logStoryPoints = logStoryPoints;
        this.logDescription = logDescription;
        this.logHours = logHours;
    }

    public String getLogCategory() {
        return logCategory;
    }

    public String getLogName() {
        return logName;
    }

    public String getLogPriority() {
        return logPriority;
    }

    public String getLogStatus() {
        return logStatus;
    }

    public String getLogAssigned() {
        return logAssigned;
    }

    public String getLogTag() {
        return logTag;
    }

    public int getLogStoryPoints() {
        return logStoryPoints;
    }

    public String getLogDescription() {
        return logDescription;
    }

    public int getLogHours() {
        return logHours;
    }

    public int getLogTaskId() {
        return logTaskId;
    }

    public void setLogTaskId(int logTaskId) {
        this.logTaskId = logTaskId;
    }
}
