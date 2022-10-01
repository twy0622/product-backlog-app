package com.example.software.provider;

import androidx.lifecycle.LiveData;
import androidx.room.Dao;
import androidx.room.Insert;
import androidx.room.Query;

import java.util.List;

@Dao
public interface SprintDao {

    @Query("select * from sprints")
    LiveData<List<Sprint>> getAllSprints();

    @Query("select * from sprints where sprintName =:sprintName")
    LiveData<List<Sprint>> getSprintName(String sprintName);

    @Query("select * from sprints where sprintDate =:sprintDate")
    LiveData<List<Sprint>> getSprintDate(String sprintDate);

    @Insert
    void addSprint(Sprint sprint);

    @Query("delete from sprints where sprintID=:id")
    void deleteSprintByID(int id);

    @Query("delete from sprints where sprintName = :name")
    void deleteSprintByName(String name);

    @Query("delete FROM sprints")
    void deleteAllSprints();

    @Query("UPDATE sprints SET sprintName =:sprintName, sprintDate =:sprintDate WHERE sprintID =:id")
    void updateSprintDetails(int id, String sprintName, String sprintDate);
}
