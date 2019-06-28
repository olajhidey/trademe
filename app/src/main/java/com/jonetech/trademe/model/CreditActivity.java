package com.jonetech.trademe.model;

import android.arch.persistence.room.ColumnInfo;
import android.arch.persistence.room.Entity;
import android.arch.persistence.room.ForeignKey;
import android.arch.persistence.room.PrimaryKey;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;

import java.util.Calendar;

@Entity(tableName = "creditor_activity_table")
public class CreditActivity {

    @NonNull
    @PrimaryKey(autoGenerate = true)
    @ColumnInfo(name = "id")
    private int id;

    @NonNull
    @ColumnInfo(name = "user_id")
    private int user_id;

    @NonNull
    @ColumnInfo(name = "title")
    private String title;

    @Nullable
    @ColumnInfo(name= "amount_paid")
    private double amount_paid;

    @Nullable
    @ColumnInfo(name="prev_bal")
    private double prev_bal;

    @Nullable
    @ColumnInfo(name="new_bal")
    private double new_bal;

    @Nullable
    @ColumnInfo(name="date")
    private String date ;

    public CreditActivity(int user_id, double amount_paid, double prev_bal, double new_bal, String title , String date){
        this.user_id = user_id;
        this.amount_paid = amount_paid;
        this.new_bal = new_bal;
        this.prev_bal = prev_bal;
        this.date = date;
        this.title = title;
    }

    @NonNull
    public int getId() {
        return id;
    }

    public void setId(@NonNull int id) {
        this.id = id;
    }

    @NonNull
    public int getUser_id() {
        return user_id;
    }

    public void setUser_id(@NonNull int user_id) {
        this.user_id = user_id;
    }

    @Nullable
    public double getAmount_paid() {
        return amount_paid;
    }

    public void setAmount_paid(@Nullable double amount_paid) {
        this.amount_paid = amount_paid;
    }

    @Nullable
    public double getPrev_bal() {
        return prev_bal;
    }

    public void setPrev_bal(@Nullable double prev_bal) {
        this.prev_bal = prev_bal;
    }

    @Nullable
    public double getNew_bal() {
        return new_bal;
    }

    public void setNew_bal(@Nullable double new_bal) {
        this.new_bal = new_bal;
    }

    @NonNull
    public String getDate() {
        return date;
    }

    public void setDate(@NonNull String date) {
        this.date = date;
    }

    public void setTitle(@NonNull String title) {
        this.title = title;
    }

    @NonNull
    public String getTitle() {
        return title;
    }
}
