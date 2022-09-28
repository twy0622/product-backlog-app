package com.example.software.provider;

import android.content.ContentValues;
import android.database.Cursor;
import android.provider.BaseColumns;

import androidx.room.ColumnInfo;
import androidx.room.Entity;
import androidx.room.Ignore;
import androidx.room.OnConflictStrategy;
import androidx.room.PrimaryKey;
import androidx.sqlite.db.SupportSQLiteDatabase;

@Entity(tableName = "base")
public class BaseEntity {

    public static final String BASETABLE_NAME = "base";
    public static final String BASETABLE_COL_ID = BaseColumns._ID;
    public static final String BASETABLE_COL_VALUE = "value";
    public static final String BASETABLE_NAME_PLACEHOLDER = ":tablename:";
    public static final String BASETABLE_CREATE_SQL = "CREATE TABLE IF NOT EXISTS "
            + BASETABLE_NAME_PLACEHOLDER +
            "(" +
            BASETABLE_COL_ID + " INTEGER PRIMARY KEY," +
            BASETABLE_COL_VALUE + " TEXT)";
    @PrimaryKey
    @ColumnInfo(name = BASETABLE_COL_ID)
    Long id;
    @ColumnInfo(name = BASETABLE_COL_VALUE)
    String value;

    public BaseEntity() {}

    @Ignore
    public BaseEntity(String value) {
        this.value = value;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getValue() {
        return value;
    }

    public void setValue(String value) {
        this.value = value;
    }

    @Ignore
    public static Long insertRow(SupportSQLiteDatabase sdb, String tableName, String value) {
        ContentValues cv = new ContentValues();
        cv.put(BASETABLE_COL_VALUE,value);
        return sdb.insert(tableName, OnConflictStrategy.IGNORE,cv);
    }

    @Ignore
    public static int getTableRowCount(SupportSQLiteDatabase sdb,String tableName) {
        int rv = 0;
        Cursor csr = sdb.query("SELECT count() FROM " + tableName,null);
        if (csr.moveToFirst()) {
            rv = csr.getInt(0);
        }
        csr.close();
        return rv;
    }
}
