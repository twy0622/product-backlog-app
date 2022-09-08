package com.example.software.provider;

import android.app.Application;
import androidx.lifecycle.LiveData;

import java.util.List;

public class TaskRepository {
    private TaskDao mTaskDao;
    private LiveData<List<Task>> mAllTasks;

    TaskRepository(Application application) {
        TaskDatabase db = TaskDatabase.getDatabase(application);
        mTaskDao = db.taskDao();
        mAllTasks = mTaskDao.getAllTask();
    }

    LiveData<List<Task>> getAllTasks() {
        return mAllTasks;
    }

    void insert(Task task) {
        TaskDatabase.databaseWriteExecutor.execute(() -> mTaskDao.addTask(task));
    }

    void deleteById(int id){
        TaskDatabase.databaseWriteExecutor.execute(() -> mTaskDao.deleteById(id));
    }

    void deleteAll(){
        TaskDatabase.databaseWriteExecutor.execute(()->{
            mTaskDao.deleteAllTasks();
        });
    }
}
