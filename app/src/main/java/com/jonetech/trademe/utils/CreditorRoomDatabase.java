package com.jonetech.trademe.utils;

import android.arch.persistence.db.SupportSQLiteDatabase;
import android.arch.persistence.room.Database;
import android.arch.persistence.room.Room;
import android.arch.persistence.room.RoomDatabase;
import android.content.Context;
import android.os.AsyncTask;
import android.support.annotation.NonNull;

import com.jonetech.trademe.model.CreditActivity;
import com.jonetech.trademe.model.CreditDao;
import com.jonetech.trademe.model.Creditor;

import java.util.Calendar;

@Database(entities = {Creditor.class, CreditActivity.class}, version = 1, exportSchema = false)
public abstract class CreditorRoomDatabase extends RoomDatabase {

    public abstract CreditDao fCreditDao();

    private static CreditorRoomDatabase INSTANCE;

    private static RoomDatabase.Callback sRoomDatabaseCallback = new RoomDatabase.Callback(){

        @Override
        public void onOpen(@NonNull SupportSQLiteDatabase db) {
            super.onOpen(db);
            new PopulateDbAsync(INSTANCE).execute();
        }
    };

    public static CreditorRoomDatabase getDatabase(Context context) {

        if (INSTANCE == null) {
            synchronized (CreditorRoomDatabase.class) {
                if (INSTANCE == null) {
                    // Create our database

                    INSTANCE = Room.databaseBuilder(context.getApplicationContext(),
                            CreditorRoomDatabase.class, "creditor_database").
                            fallbackToDestructiveMigration()
                            .addCallback(sRoomDatabaseCallback)
                            .build();
                }
            }
        }
        return INSTANCE;
    }



    private static class PopulateDbAsync extends AsyncTask<Void, Void, Void> {

        private final CreditDao dao;
        String[] words = {"dolphin", "crocodile", "cobra"};
        CreditActivity[]  activity = {
                new CreditActivity(1, 100f, 0f, 100f, "Hakeem paid", "12-11-21"),
        };

        public PopulateDbAsync(CreditorRoomDatabase instance) {
            dao = instance.fCreditDao();
        }

        @Override
        protected Void doInBackground(final Void... params) {

            if (dao.getAnyCreditor().length < 1) {

                for (int i = 0; i < words.length; i++) {

                    String mCalendar = Calendar.getInstance().toString();
                    Creditor mWord = new Creditor(words[i], mCalendar);
                    CreditActivity mActivity = new CreditActivity(activity[i].getUser_id(), activity[i].getAmount_paid(), activity[i].getPrev_bal(), activity[i].getNew_bal(), activity[i].getTitle(), activity[i].getDate());
                    dao.insertCreditor(mWord);
                    dao.insertCreditorActivity(mActivity);
                }
            }

            return null;
        }

    }
}
