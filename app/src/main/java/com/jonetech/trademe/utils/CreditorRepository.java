package com.jonetech.trademe.utils;

import android.app.Application;
import android.arch.lifecycle.LiveData;
import android.os.AsyncTask;

import com.jonetech.trademe.model.CreditDao;
import com.jonetech.trademe.model.Creditor;

import java.util.List;

public class CreditorRepository {

    private CreditDao fCreditDao;
    private LiveData<List<Creditor>> allCreditors;


    public CreditorRepository(Application application){
        CreditorRoomDatabase db = CreditorRoomDatabase.getDatabase(application);
        fCreditDao = db.fCreditDao();
        allCreditors = fCreditDao.getAllCreditors();
    }

    /**
     * Live data to get all our creditors in the database.
     * @return a list of creditors that are appended to the view for use.
     */
    public LiveData<List<Creditor>> getAllCreditors() {return allCreditors;}

    /**
     * Method to be used by our view to add creditors information
     * @param creditor
     */
    public void insertCreditor(Creditor creditor){
        new InsertCreditorAsyncTask(fCreditDao).execute(creditor);
    }

    /**
     * Method to be used by the view model to delete all the available creditor
     * information in the database
     * -------NOTE:--------
     * This should be used carefully and would add a constraint on the view before
     * deleting so users are conscious of what they are doing.
     *
     *
     */
    public void deleteAllCreditor(){
        new DeleteCreditorAsnycTask(fCreditDao).execute();
    }

    /**
     * AsyncTask to add Creditor information underground
     */
    public class InsertCreditorAsyncTask extends AsyncTask<Creditor, Void, Void>{

        CreditDao credit;

        public InsertCreditorAsyncTask(CreditDao creditDao){
            this.credit = creditDao;
        }

        @Override
        protected Void doInBackground(Creditor... creditors) {

            credit.insertCreditor(creditors[0]);
            return null;
        }
    }

    /**
     * AsyncTask to delete all of the creditors information available in our
     * database so we can start afresh all over
     */
    public class DeleteCreditorAsnycTask extends AsyncTask<Void, Void, Void>{

        private CreditDao fCreditDao;

        private DeleteCreditorAsnycTask(CreditDao creditDao){
            fCreditDao = creditDao;
        }

        @Override
        protected Void doInBackground(Void... creditors) {
            fCreditDao.deleteCreditor();
            return null;
        }
    }
}

