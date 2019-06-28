package com.jonetech.trademe.views;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import com.jonetech.trademe.R;

public class NewCustomerActivity extends AppCompatActivity {

    private Toolbar fToolbar;
    private EditText fEditText;
    public static String EXTRA_REPLY = "com.jonetech.trademe.REPLY";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_new_customer);

        fToolbar = findViewById(R.id.newToolBar);

        fEditText = findViewById(R.id.new_customer_text);

        final Button mButton = findViewById(R.id.btn_new_customer);

        mButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent replyIntent = new Intent();

                if(TextUtils.isEmpty(fEditText.getText().toString())){
                    setResult(RESULT_CANCELED, replyIntent);
                }else{

                    String customer = fEditText.getText().toString();

                    replyIntent.putExtra(EXTRA_REPLY, customer);
                    setResult(RESULT_OK, replyIntent);
                }
               finish();
            }
        });




        setSupportActionBar(fToolbar);
    }

    public void createNewCustomer(View view) {
    }
}
