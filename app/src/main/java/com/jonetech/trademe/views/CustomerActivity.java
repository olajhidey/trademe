package com.jonetech.trademe.views;

import android.arch.lifecycle.LiveData;
import android.arch.lifecycle.Observer;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;

import com.jonetech.trademe.R;
import com.jonetech.trademe.adapters.CreditorActivityListAdapter;
import com.jonetech.trademe.adapters.CreditorSummaryViewModel;
import com.jonetech.trademe.adapters.CreditorViewModel;
import com.jonetech.trademe.model.CreditActivity;
import com.jonetech.trademe.model.Creditor;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

public class CustomerActivity extends AppCompatActivity {

    public static final String USER_ID = "user_id";
    private TextView customer;
    private int userid;
    private TextView emptyText;
    private CreditorSummaryViewModel summaryViewModel;
    private CreditorViewModel model;
    List<CreditActivity> fAllActivty;
    private static final int NEW_CREDITOR_SUMMARY_CODE = 1;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_customer);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        customer = findViewById(R.id.customer);
        emptyText = findViewById(R.id.empty_text);
        /**
         * Get intent if it exists
         */
        Intent intent = getIntent();

        /**
         * Assign the intents to variables so it can be appended on the view
         * So we can use to fetch data from the CreditDao to the recyclerview
         */
        String name = intent.getStringExtra(MainActivity.USER_NAME);
        userid = intent.getIntExtra(MainActivity.USER_ID, 1);
        String mDate =  intent.getStringExtra(MainActivity.USER_DATE);

        customer.setText(name);

        RecyclerView mRecyclerView = findViewById(R.id.customer_summary_recyclerview);
        final CreditorActivityListAdapter listAdapter = new CreditorActivityListAdapter(this);
        mRecyclerView.setLayoutManager(new LinearLayoutManager(this));
        mRecyclerView.setAdapter(listAdapter);

        summaryViewModel = new CreditorSummaryViewModel(getApplication(), userid);

        summaryViewModel.getAllCreditActivity(userid).observe(this, new Observer<List<CreditActivity>>() {
            @Override
            public void onChanged(@Nullable List<CreditActivity> creditActivities) {
                fAllActivty = creditActivities;

                if(fAllActivty.size() == 0){
                    emptyText.setVisibility(View.VISIBLE);
                }else {
                    emptyText.setVisibility(View.GONE);
                }

                if(fAllActivty != null){
                    listAdapter.setActivity(creditActivities);
                }

            }
        });



        FloatingActionButton fab = (FloatingActionButton) findViewById(R.id.fab);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(CustomerActivity.this, NewSummaryActivity.class);
                intent.putExtra(USER_ID, userid);
                startActivity(intent);
            }
        });
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
    }

//    @Override
//    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
//        super.onActivityResult(requestCode, resultCode, data);
//
//        if( requestCode == NEW_CREDITOR_SUMMARY_CODE && resultCode == RESULT_OK ){
//
//            // Get the extras data
//            String title = data.getStringExtra(NewSummaryActivity.TITLE_REPLY);
//            String choice = data.getStringExtra(NewSummaryActivity.CHOICE_REPLY);
//            Double amount = Double.parseDouble(data.getStringExtra(NewSummaryActivity.AMOUNT_REPLY));
//            String date = Calendar.getInstance().toString();
//
//            CreditActivity mCreditActivity = new CreditActivity(userid, amount, amount, amount, title, date);
//
//            summaryViewModel.insertCreditorActivity(mCreditActivity);
//
//            displayToast("Activity added successfully");
//
//        }else{
//            displayToast("Fields can\'t be empty");
//        }
//    }

    private void displayToast(String message) {
        Toast.makeText(this, message, Toast.LENGTH_SHORT).show();
    }
}
