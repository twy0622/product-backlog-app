package com.example.software.provider;

import android.app.Application;
import androidx.annotation.NonNull;
import androidx.lifecycle.AndroidViewModel;
import androidx.lifecycle.LiveData;
import java.util.List;

public class TaskViewModel extends AndroidViewModel {
    private TaskRepository mRepository;
    private LiveData<List<Task>> mAllTasks;

    public TaskViewModel(@NonNull Application application) {
        super(application);
        mRepository = new TaskRepository(application);
        mAllTasks = mRepository.getAllTasks();
    }

    public LiveData<List<Task>> getAllTasks() {
        return mAllTasks;
    }

    public LiveData<List<Task>> getSprintTasks(String sprint) {
        return mRepository.getSprintTasks(sprint);
    }

    public LiveData<List<Task>> getSprintStatus(String status) {
        return mRepository.getSprintStatus(status);
    }

    public LiveData<List<Task>> getSprintStatus2(String status1, String status2, String status3) {
        return mRepository.getSprintStatus2(status1, status2, status3);
    }

    public void insert(Task task) {
        mRepository.insert(task);
    }

    public void deleteAll(){
        mRepository.deleteAll();
    }

    public void deleteById(int id){
        mRepository.deleteById(id);
    }

    public void updateSprint(String sprint) {
        mRepository.updateSprint(sprint);
    }

    public void updateTask(int id, String category, String name, String description, String priority,
                           String status, String assigned, String tag, int storyPoints) {
        mRepository.updateTask(id,category,name,description,priority,status,assigned,tag,storyPoints);
    }
}
