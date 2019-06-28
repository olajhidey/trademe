package com.jonetech.trademe.adapters;

import android.app.Application;
import android.arch.lifecycle.AndroidViewModel;
import android.arch.lifecycle.LiveData;
import android.support.annotation.NonNull;

import com.jonetech.trademe.model.Creditor;
import com.jonetech.trademe.utils.CreditorRepository;

import java.util.List;

public class CreditorViewModel extends AndroidViewModel {

    private CreditorRepository fCreditorRepository;
    private LiveData<List<Creditor>> allCreditors;

    public CreditorViewModel(@NonNull Application application) {
        super(application);

        fCreditorRepository = new CreditorRepository(application);
        allCreditors = fCreditorRepository.getAllCreditors();

    }

    /**
     * Get all the creditor in the creditor_table
     * @return everything as a list of Creditor
     */
    public LiveData<List<Creditor>> getAllCreditors() {
        return allCreditors;
    }

    /**
     * Insert the creditor into our db
     * @param creditor
     */
    public void insert(Creditor creditor){
        fCreditorRepository.insertCreditor(creditor);
    }

    /**
     * Delete all the creditor on our db
     *
     */
    public void deleteAllCreditor(){
        fCreditorRepository.deleteAllCreditor();
    }
}
