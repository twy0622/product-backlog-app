package com.example.software.provider;

import android.app.Application;
import android.util.Log;

import androidx.annotation.NonNull;
import androidx.lifecycle.AndroidViewModel;
import androidx.lifecycle.LiveData;
import java.util.List;

import kotlin.jvm.internal.PropertyReference0Impl;

public class TaskViewModel extends AndroidViewModel {
    private TaskRepository mRepository;
    private LiveData<List<Task>> mAllTasks;
    private LiveData<List<Sprint>> mAllSprints;
    private LiveData<List<Members>> mAllMembers;
    private LiveData<List<Log_Task>> mAllTaskDateTimes;

    public TaskViewModel(@NonNull Application application) {
        super(application);
        mRepository = new TaskRepository(application);
        mAllTasks = mRepository.getAllTasks();
        mAllSprints = mRepository.getAllSprints();
        mAllMembers = mRepository.getAllTeamMembers();
        mAllTaskDateTimes = mRepository.getTaskDateHours();
    }

    public void insertTaskDateTime(TaskDateTime taskDateTime){
        mRepository.insert(taskDateTime);
    }

    //Task View Models

    public LiveData<List<Task>> getAllTasks() {
        return mAllTasks;
    }


    public LiveData<List<Task>> getSprintTasks(String sprint) {
        return mRepository.getSprintTasks(sprint);
    }

    public LiveData<List<Task>> getSprintStatus(String sprint, String status) {
        return mRepository.getSprintStatus(sprint, status);
    }

    public LiveData<List<Task>> getSprintStatus2(String sprint, String status1, String status2, String status3) {
        return mRepository.getSprintStatus2(sprint, status1, status2, status3);
    }

    public LiveData<List<Log_Task>> getAllTaskDateTimes() {
        return mAllTaskDateTimes;
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

    public void endSprint(String sprint) {
        mRepository.endSprint(sprint);
    }

    public void updateSprint(int id, String sprint) {
        mRepository.updateSprint(id, sprint);
    }

    public void updateTask(int id, String category, String name, String description, String priority,
                           String status, String assigned, String tag, int storyPoints) {
        mRepository.updateTask(id,category,name,description,priority,status,assigned,tag,
                storyPoints);
    }

//    public int getTaskHoursSum(){ return mRepository.getTaskHoursSum(); }

    //Sprint View Models

    public LiveData<List<Sprint>> getAllSprints() {
        return mAllSprints;
    }

    public LiveData<List<Sprint>> getSprintName(String name) {
        return mRepository.getSprintName(name);
    }

    public LiveData<List<Sprint>> getSprintDate(String date) {
        return mRepository.getSprintDate(date);
    }

    public void addSprint(Sprint sprint) {
        mRepository.addSprint(sprint);
    }

    public void deleteSprintByID(int id) {
        mRepository.deleteSprintByID(id);
    }

    public void deleteSprintByName(String name) {
        mRepository.deleteSprintByName(name);
    }

    public void deleteAllSprints() {
        mRepository.deleteAllSprints();
    }

    public void updateSprintDetails(int id, String sprintName, String sprintDate) {
        mRepository.updateSprintDetails(id,sprintName,sprintDate);
    }

    //Member View Models

    public LiveData<List<Members>> getAllTeamMembers() {
        return mAllMembers;
    }

    public LiveData<List<Members>> getMemberName(String memberName) {
        return mRepository.getMemberName(memberName);
    }

    public LiveData<List<Members>> getMemberEmail(String memberEmail) {
        return mRepository.getMemberEmail(memberEmail);
    }

    public void addTeamMember(Members member) {
        mRepository.addTeamMember(member);
    }

    public void deleteTeamMemberByID(int memberID) {
        mRepository.deleteTeamMemberByID(memberID);
    }

    public void deleteTeamMember(String memberName) {
        mRepository.deleteTeamMember(memberName);
    }

    public void deleteAllMembers() {
        mRepository.deleteAllMembers();
    }

    public void updateTeamMembers(int memberID, String memberName, String memberEmail) {
        mRepository.updateTeamMembers(memberID,memberName,memberEmail);
    }

}
