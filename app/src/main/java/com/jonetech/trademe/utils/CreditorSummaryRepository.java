package com.jonetech.trademe.utils;

import android.app.Application;
import android.arch.lifecycle.LiveData;
import android.os.AsyncTask;

import com.jonetech.trademe.model.CreditActivity;
import com.jonetech.trademe.model.CreditDao;
import com.jonetech.trademe.model.Creditor;

import java.util.List;

public class CreditorSummaryRepository {

    private CreditDao fCreditDao;
    private LiveData<List<CreditActivity>> allActivity;

    public CreditorSummaryRepository(Application application){
        CreditorRoomDatabase db = CreditorRoomDatabase.getDatabase(application);
        this.fCreditDao = db.fCreditDao();
//        allActivity = fCreditDao.getAllActivity(creditor);
    }

    public  LiveData<List<CreditActivity>> getAllActivity(int id) { return fCreditDao.getAllActivity(id); }

    public void insertCreditorSummary(CreditActivity creditActivity){
        new InsertCreditorActivityAsyncTask(fCreditDao).execute(creditActivity);
    }

    /**
     * AsyncTask to perform insertion of Creditor Activities
     * in the behind the scene thread
     */
    public class InsertCreditorActivityAsyncTask extends AsyncTask<CreditActivity, Void, Void>{

        private CreditDao fCreditDao;

        private InsertCreditorActivityAsyncTask(CreditDao creditDao){
            fCreditDao = creditDao;
        }

        @Override
        protected Void doInBackground(CreditActivity... creditActivities) {

            fCreditDao.insertCreditorActivity(creditActivities[0]);
            return null;
        }
    }
}

