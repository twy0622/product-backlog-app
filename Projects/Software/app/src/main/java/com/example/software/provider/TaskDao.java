package com.example.software.provider;

import androidx.lifecycle.LiveData;
import androidx.room.Dao;
import androidx.room.Insert;
import androidx.room.Query;

import java.util.List;

@Dao
public interface TaskDao {
    @Query("select * from tasks")
    LiveData<List<Task>> getAllTask();

    @Insert
    void addTask(Task task);

    @Query("delete from tasks where taskId= :id")
    void deleteById(int id);

    @Query("delete FROM tasks")
    void deleteAllTasks();
}
