package com.example.software.provider;

import android.content.Context;
import androidx.room.Database;
import androidx.room.Room;
import androidx.room.RoomDatabase;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

@Database(entities = {Task.class, Sprint.class}, version = 1)
public abstract class TaskDatabase extends RoomDatabase {
    public static final String TASK_DATABASE_NAME = "taskDatabase";

    public abstract TaskDao taskDao();

    public abstract SprintDao sprintDao();

    // marking the instance as volatile to ensure atomic access to the variable
    private static volatile TaskDatabase INSTANCE;

    private static final int NUMBER_OF_THREADS = 4;
    static final ExecutorService databaseWriteExecutor = Executors.newFixedThreadPool(NUMBER_OF_THREADS);

    static TaskDatabase getDatabase(final Context context) {
        if (INSTANCE == null) {
            synchronized (TaskDatabase.class) {
                if (INSTANCE == null) {
                    INSTANCE = Room.databaseBuilder(context.getApplicationContext(),
                            TaskDatabase.class, TASK_DATABASE_NAME)
                            .build();
                }
            }
        }
        return INSTANCE;
    }
}
