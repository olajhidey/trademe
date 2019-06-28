package com.jonetech.trademe.views;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import com.jonetech.trademe.R;
import com.jonetech.trademe.adapters.CreditorSummaryViewModel;
import com.jonetech.trademe.model.CreditActivity;
import com.jonetech.trademe.utils.CreditorSummaryRepository;

import java.util.Calendar;

public class NewSummaryActivity extends AppCompatActivity {

    private String fChoice;
    public static final String TITLE_REPLY = "com.jonetech.trademe.TITLE_REPLY";
    public static final String CHOICE_REPLY = "com.jonetech.trademe.CHOICE_REPLY";
    public static final String AMOUNT_REPLY = "com.jonetech.trademe.AMOUNT_REPLY";
    private TextView fTitle;
    private TextView fAmount;
    private Spinner fSpinner;
    private CreditorSummaryViewModel viewModel;
    private int fUserid;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_new_summary);

        fTitle = findViewById(R.id.summary_title);
        fAmount = findViewById(R.id.amount);
        Button mButton = findViewById(R.id.submit_btn);

        Intent intent = getIntent();

        fUserid = intent.getIntExtra(CustomerActivity.USER_ID, 1);

        viewModel = new CreditorSummaryViewModel(getApplication(), fUserid);

        fSpinner = (Spinner) findViewById(R.id.mode_spinner);

        //Create an arrayAdapter using the string array and a default spinner layout
        final ArrayAdapter<CharSequence> adapter = ArrayAdapter.createFromResource(this, R.array.mode_selection, android.R.layout.simple_spinner_item );

        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);

        fSpinner.setAdapter(adapter);

        fSpinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {

                fChoice = (String) parent.getItemAtPosition(position);
                System.out.println(fChoice);
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });

        mButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent mIntent = new Intent();

                if(TextUtils.isEmpty(fTitle.getText().toString())){
                    displayToast("Textfield can\'t be left empty");
                }else{

                    String text = fTitle.getText().toString();
                    String amount = fAmount.getText().toString();
                    String choice = fChoice;
                    Calendar mCalendar = Calendar.getInstance();

                    CreditActivity mCreditActivity = new CreditActivity(fUserid, Double.valueOf(amount), Double.valueOf(amount), Double.valueOf(amount), text, mCalendar.toString());
                    viewModel.insertCreditorActivity(mCreditActivity);
                    displayToast("Creditor Activity created..");
                }
            }
        });
    }

    private void displayToast(String message) {
        Toast.makeText(this, message, Toast.LENGTH_SHORT).show();
    }

}
