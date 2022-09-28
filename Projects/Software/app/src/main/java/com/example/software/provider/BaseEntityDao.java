package com.example.software.provider;


import androidx.room.Dao;
import androidx.room.Insert;
import androidx.room.Query;

@Dao
public interface BaseEntityDao {

    @Insert
    long insertRow(BaseEntity baseEntity);

    @Query("INSERT INTO base (value) VALUES(:the_value)")
    void insertRow(String the_value);

    @Query("SELECT count() FROM base")
    Integer getRowCount();

}
