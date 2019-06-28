package com.jonetech.trademe.model;

import android.arch.persistence.room.ColumnInfo;
import android.arch.persistence.room.Entity;
import android.arch.persistence.room.PrimaryKey;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

@Entity(tableName = "creditor_table")
public class Creditor {

    /**
     * Primary ID for each creditors
     */
    @PrimaryKey(autoGenerate = true)
    private int id;

    /**
     * Name of each creditors added by the trader
     */
    @NonNull
    @ColumnInfo(name = "name")
    private String name;

    /**
     * Date of the goods added
     */
    @Nullable
    @ColumnInfo(name="date")
    private String date;

    public Creditor(String name, @Nullable String date){

        this.name = name;
        this.date = date;

    }

    @Nullable
    public String getDate() {
        return date;
    }

    public void setDate(@Nullable String date) {
        this.date = date;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    @NonNull
    public String getName() {
        return name;
    }

    public void setName(@NonNull String name) {
        this.name = name;
    }

}
