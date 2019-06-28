package com.jonetech.trademe.adapters;

import android.app.Application;
import android.arch.lifecycle.AndroidViewModel;
import android.arch.lifecycle.LiveData;
import android.support.annotation.NonNull;

import com.jonetech.trademe.model.CreditActivity;
import com.jonetech.trademe.model.Creditor;
import com.jonetech.trademe.utils.CreditorSummaryRepository;

import java.util.List;

public class CreditorSummaryViewModel extends AndroidViewModel {

    private CreditorSummaryRepository fRepository;
    public LiveData<List<CreditActivity>> allCreditActivity;

    public CreditorSummaryViewModel(@NonNull Application application, int id) {
        super(application);

        fRepository = new CreditorSummaryRepository(application);
        allCreditActivity = fRepository.getAllActivity(id);
    }

    /**
     * Get all the creditor activity
     * @return
     */
    public LiveData<List<CreditActivity>> getAllCreditActivity(int id) {
        return allCreditActivity;
    }

    /**
     * Insert Creditor activity to the database
     * @param activity
     */
    public void insertCreditorActivity(CreditActivity activity){
        fRepository.insertCreditorSummary(activity);
    }
}
