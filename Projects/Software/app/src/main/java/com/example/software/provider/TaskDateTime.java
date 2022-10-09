package com.example.software.provider;

import androidx.room.Embedded;
import androidx.room.Entity;
import androidx.room.Relation;

import java.util.List;

public class TaskDateTime {

    @Embedded
    public Task task;
    @Relation(
            parentColumn = "taskId",
            entityColumn = "logId",
            entity = Log_Task.class
    )
    public List<Log_Task> log_tasks;

    public TaskDateTime(Task task, List<Log_Task> log_tasks) {
        this.task = task;
        this.log_tasks = log_tasks;
    }
}
