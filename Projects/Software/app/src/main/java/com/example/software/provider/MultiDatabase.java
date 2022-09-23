package com.example.software.provider;

import androidx.room.RoomDatabase;

@androidx.room.Database(version = 1, entities = {BaseEntity.class})
public abstract class MultiDatabase extends RoomDatabase {

    public abstract BaseEntityDao baseEntityDao();
}
