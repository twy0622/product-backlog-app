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
    private int taskId;

    @ColumnInfo(name = "taskCategory")
    private String category;

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

    @ColumnInfo(name = "taskTag")
    private String tag;

    @ColumnInfo(name = "taskStoryPoints")
    private int storyPoints;

    @ColumnInfo(name = "taskSprint")
    private String sprint;




    public Task(String category, String name, String description, String priority, String status, String assigned, String tag, int storyPoints, String sprint) {
        this.category = category;
        this.name = name;
        this.description = description;
        this.priority = priority;
        this.status = status;
        this.assigned = assigned;
        this.tag = tag;
        this.storyPoints = storyPoints;
        this.sprint = sprint;

    }

    public String getCategory() { return category; }
    public String getName() { return name; }
    public String getDescription() { return description; }
    public String getPriority() { return priority; }
    public String getStatus() { return status; }
    public String getAssigned() { return assigned; }
    public String getTag() { return tag; }
    public int getStoryPoints() { return storyPoints; }
    public String getSprint() { return sprint; }



    public int getTaskId() { return taskId; }
    public void setTaskId(@NonNull int taskId) { this.taskId = taskId; }
    public Task getTask() { return this; }
}