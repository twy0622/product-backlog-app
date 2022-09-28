package com.example.software.provider;

import android.app.Application;
import androidx.lifecycle.LiveData;

import java.util.List;

public class TaskRepository {
    private TaskDao mTaskDao;
    private LiveData<List<Task>> mAllTasks;
    private LiveData<List<Task>> mSprintTasks;

    TaskRepository(Application application) {
        TaskDatabase db = TaskDatabase.getDatabase(application);
        mTaskDao = db.taskDao();
        mAllTasks = mTaskDao.getAllTask();
    }

    LiveData<List<Task>> getAllTasks() {
        return mAllTasks;
    }

    LiveData<List<Task>> getSprintTasks(String sprint) {
        return mTaskDao.getSprintTask(sprint);
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

    void updateSprint(String sprint){
        TaskDatabase.databaseWriteExecutor.execute(()->{
            mTaskDao.updateSprint(sprint);
        });
    }

    void updateTask(int id, String category, String name, String description, String priority,
                    String status, String assigned, String tag, int storyPoints){
        TaskDatabase.databaseWriteExecutor.execute(()->{
            mTaskDao.updateTask(id,category,name,description,priority,status,assigned,tag,storyPoints);
        });
    }
}
