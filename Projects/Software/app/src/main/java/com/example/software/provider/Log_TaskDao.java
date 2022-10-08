package com.example.software.provider;

import androidx.lifecycle.LiveData;
import androidx.room.Dao;
import androidx.room.Insert;
import androidx.room.Query;

import java.util.List;

@Dao
public interface Log_TaskDao {
    @Query("select * from log_tasks")
    LiveData<List<Log_Task>> getLogTasks();

    @Query("select * from log_tasks where logHours = :hours")
    LiveData<List<Log_Task>> getLogHours(int hours);

    @Insert
    void addLogTask(Log_Task log_task);

}
