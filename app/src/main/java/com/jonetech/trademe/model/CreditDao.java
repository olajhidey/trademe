package com.jonetech.trademe.model;

import android.arch.lifecycle.LiveData;
import android.arch.persistence.room.Dao;
import android.arch.persistence.room.Delete;
import android.arch.persistence.room.Insert;
import android.arch.persistence.room.Query;

import java.util.List;

@Dao
public interface CreditDao {

    @Insert
    void insertCreditor(Creditor creditor);

    @Query("SELECT * from creditor_table ORDER BY name  ASC")
    LiveData<List<Creditor>> getAllCreditors();

    @Insert
    void insertCreditorActivity(CreditActivity creditActivity);

    @Query("SELECT * from creditor_activity_table WHERE user_id = :creditor")
    LiveData<List<CreditActivity>> getAllActivity(int creditor);

    @Query("DELETE FROM creditor_table")
    void deleteCreditor();

    @Query("SELECT * FROM creditor_table LIMIT 1")
    Creditor[] getAnyCreditor();

    @Query("SELECT * FROM creditor_activity_table LIMIT 1")
    CreditActivity[] getAnyCreditorActivity();

}
