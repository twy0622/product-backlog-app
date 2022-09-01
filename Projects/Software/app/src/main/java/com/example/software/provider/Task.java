package com.example.software.provider;

import androidx.annotation.NonNull;
import androidx.room.ColumnInfo;
import androidx.room.Entity;
import androidx.room.PrimaryKey;

@Entity(tableName = "tasks")
public class Task {
    @PrimaryKey(autoGenerate = true)
    @NonNull
    @ColumnInfo(name = "taskId")
    private int id;

    @ColumnInfo(name = "taskName")
    private String name;

    @ColumnInfo(name = "taskDescription")
    private String description;

    @ColumnInfo(name = "taskPriority")
    private String priority;

    @ColumnInfo(name = "taskStatus")
    private String status;

    @ColumnInfo(name = "taskAssigned")
    private String assigned;

    @ColumnInfo(name = "taskTags")
    private String tags;

    @ColumnInfo(name = "taskStoryPoints")
    private int storyPoints;

    public Task(String name, String description, String priority, String status, String assigned, String tags, int storyPoints) {
        this.name = name;
        this.description = description;
        this.priority = priority;
        this.status = status;
        this.assigned = assigned;
        this.tags = tags;
        this.storyPoints = storyPoints;
    }
}