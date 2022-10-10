package com.example.software.provider;

import android.app.Application;
import android.os.AsyncTask;
import android.util.Log;

import androidx.lifecycle.LiveData;

import java.util.List;

public class TaskRepository {
    private TaskDao mTaskDao;
    private SprintDao mSprintDao;
    private MembersDao mMembersDao;
    private LiveData<List<Sprint>> mAllSprints;
    private LiveData<List<Members>> mAllMembers;
    private LiveData<List<Task>> mAllTasks;
    private LiveData<List<Task>> mSprintTasks;
    private LiveData<List<Log_Task>> mAllTaskHours;

    TaskRepository(Application application) {
        TaskDatabase db = TaskDatabase.getDatabase(application);
        mTaskDao = db.taskDao();
        mSprintDao = db.sprintDao();
        mMembersDao = db.membersDao();
        mAllMembers = mMembersDao.getAllTeamMembers();
        mAllSprints = mSprintDao.getAllSprints();
        mAllTasks = mTaskDao.getAllTask();
        mAllTaskHours = mTaskDao.getTaskDateHours();
    }

    public void insert(TaskDateTime taskDateTime){
        new insertAsync(mTaskDao).execute(taskDateTime);
    }

    private static class insertAsync extends AsyncTask<TaskDateTime, Void, Void>{
        private TaskDao taskDaoAsync;

        insertAsync(TaskDao taskDao){
            taskDaoAsync = taskDao;
        }

        @Override
        protected Void doInBackground(TaskDateTime... taskDateTimes) {
            int identifier = (int) taskDaoAsync.addTask(taskDateTimes[0].task);

            for (Log_Task log_task : taskDateTimes[0].log_tasks){
                log_task.setTaskIdFK(identifier);
            }
            taskDaoAsync.addLogTask(taskDateTimes[0].log_tasks);
            return null;
        }
    }

    //Task Repositories

    LiveData<List<Task>> getAllTasks() {
        return mAllTasks;
    }

    LiveData<List<Task>> getSprintTasks(String sprint) {
        return mTaskDao.getSprintTask(sprint);
    }

    LiveData<List<Task>> getSprintStatus(String sprint, String status) {
        return mTaskDao.getSprintStatus(sprint, status);
    }

    LiveData<List<Task>> getSprintStatus2(String sprint, String status1, String status2, String status3) {
        return mTaskDao.getSprintStatus2(sprint, status1, status2, status3);
    }

    LiveData<List<Log_Task>> getTaskDateHours() { return mAllTaskHours; }

    LiveData<List<Log_Task>> getTaskHours(int taskIdFK){
        mAllTaskHours = mTaskDao.getTaskHours(taskIdFK);
        return mAllTaskHours;
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

    void updateSprint(int id, String sprint){
        TaskDatabase.databaseWriteExecutor.execute(()->{
            mTaskDao.updateSprint(id, sprint);
        });
    }

    void endSprint(String sprint){
        TaskDatabase.databaseWriteExecutor.execute(()->{
            mTaskDao.endSprint(sprint);
        });
    }

    void updateTask(int id, String category, String name, String description, String priority,
                    String status, String assigned, String tag, int storyPoints, int accHours){
        TaskDatabase.databaseWriteExecutor.execute(()->{
            mTaskDao.updateTask(id,category,name,description,priority,status,assigned,tag,
                    storyPoints, accHours);
        });

    }

    void updateAccHours(int id, int hours){
        TaskDatabase.databaseWriteExecutor.execute(()->{
            mTaskDao.updateAccHours(id, hours);
        });
    }

    //Sprint Repositories

    LiveData<List<Sprint>> getAllSprints(){
        return mAllSprints;
    }

    LiveData<List<Sprint>> getSprintName(String sprint) {
        return mSprintDao.getSprintName(sprint);
    }

    LiveData<List<Sprint>> getSprintDate(String date) {
        return mSprintDao.getSprintDate(date);
    }

    void addSprint(Sprint sprint) {
        TaskDatabase.databaseWriteExecutor.execute(() -> mSprintDao.addSprint(sprint));
    }

    void deleteSprintByID(int id) {
        TaskDatabase.databaseWriteExecutor.execute(() -> mSprintDao.deleteSprintByID(id));
    }

    void deleteSprintByName(String name){
        TaskDatabase.databaseWriteExecutor.execute(() -> mSprintDao.deleteSprintByName(name));
    }

    void deleteAllSprints(){
        TaskDatabase.databaseWriteExecutor.execute(()->{
            mSprintDao.deleteAllSprints();
        });
    }

    void updateSprintDetails(int id, String sprintName, String sprintDate){
        TaskDatabase.databaseWriteExecutor.execute(()->{
            mSprintDao.updateSprintDetails(id, sprintName, sprintDate);
        });
    }

    //Members Repositories

    LiveData<List<Members>> getAllTeamMembers() {
        return mAllMembers;
    }

    LiveData<List<Members>> getMemberName(String memberName) {
        return mMembersDao.getMemberName(memberName);
    }

    LiveData<List<Members>> getMemberEmail(String memberEmail) {
        return mMembersDao.getMemberEmail(memberEmail);
    }

    void addTeamMember(Members member) {
        TaskDatabase.databaseWriteExecutor.execute(() -> mMembersDao.addTeamMember(member));
    }

    void deleteTeamMemberByID(int memberID) {
        TaskDatabase.databaseWriteExecutor.execute(() -> mMembersDao.deleteTeamMemberByID(memberID));
    }
    void deleteTeamMember(String memberName) {
        TaskDatabase.databaseWriteExecutor.execute(() ->mMembersDao.deleteTeamMember(memberName));
    }

    void deleteAllMembers() {
        TaskDatabase.databaseWriteExecutor.execute(() -> mMembersDao.deleteAllTeamMembers());
    }

    void updateTeamMembers(int id, String memberName, String memberEmail) {
        TaskDatabase.databaseWriteExecutor.execute(() -> {mMembersDao.updateTeamMembers(id, memberName
        , memberEmail);
    });
}

}
