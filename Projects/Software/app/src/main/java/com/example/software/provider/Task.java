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

    public Task(String category, String name, String description, String priority, String status, String assigned, String tag, int storyPoints) {
        this.category = category;
        this.name = name;
        this.description = description;
        this.priority = priority;
        this.status = status;
        this.assigned = assigned;
        this.tag = tag;
        this.storyPoints = storyPoints;
    }

    public Task getTask() {
        return this;
    }
    public int getTaskId() { return taskId; }

    public String getCategory() { return category; }
    public String getName() { return name; }
    public String getDescription() { return description; }
    public String getPriority() { return priority; }
    public String getStatus() { return status; }
    public String getAssigned() { return assigned; }
    public String getTag() { return tag; }
    public int getStoryPoints() { return storyPoints; }

    public void setTaskId(int taskId) {
        this.taskId = taskId;
    }

    public void setCategory(String category) {
        this.category = category;
    }
    public void setName(String name) {
        this.name = name;
    }
    public void setDescription(String description) {
        this.description = description;
    }
    public void setPriority(String priority) {
        this.priority = priority;
    }
    public void setStatus(String status) {
        this.status = status;
    }
    public void setAssigned(String assigned) {
        this.assigned = assigned;
    }
    public void setTag(String tag) {
        this.tag = tag;
    }
    public void setStoryPoints(int storyPoints) {
        this.storyPoints = storyPoints;
    }
}