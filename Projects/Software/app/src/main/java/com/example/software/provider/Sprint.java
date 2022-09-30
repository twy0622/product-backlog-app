package com.example.software.provider;

import androidx.annotation.NonNull;
import androidx.room.ColumnInfo;
import androidx.room.Entity;
import androidx.room.PrimaryKey;

@Entity(tableName = "sprints")
public class Sprint{
    @PrimaryKey(autoGenerate = true)
    @NonNull
    @ColumnInfo(name = "sprintID")
    private int sprintID;

    @ColumnInfo(name = "sprintName")
    private String sprintName;

    @ColumnInfo(name = "sprintDate")
    private String sprintDate;

    public Sprint(String sprintName, String sprintDate) {
        this.sprintName = sprintName;
        this.sprintDate = sprintDate;
    }

    public int getSprintID() {
        return sprintID;
    }

    public String getSprintName() {
        return sprintName;
    }

    public String getSprintDate() {
        return sprintDate;
    }

    public void setSprintID(int sprintID) {
        this.sprintID = sprintID;
    }

    public Sprint getSprintStuff() {
        return this;
    }
}
