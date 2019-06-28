package com.jonetech.trademe.views;

import android.arch.lifecycle.Observer;
import android.arch.lifecycle.ViewModelProviders;
import android.content.DialogInterface;
import android.content.Intent;
import android.support.annotation.Nullable;
import android.support.design.widget.FloatingActionButton;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.Toast;

import com.jonetech.trademe.R;
import com.jonetech.trademe.adapters.CreditorListAdapter;
import com.jonetech.trademe.adapters.CreditorViewModel;
import com.jonetech.trademe.model.Creditor;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.List;
import java.util.zip.Inflater;

public class MainActivity extends AppCompatActivity {

    public static final String USER_ID = "user_id";
    public static final String USER_NAME = "user_name";
    public static final String USER_DATE = "user_date";
    private CreditorViewModel fCreditorViewModel;
    public static final int NEW_CREDITOR_ACTIVITY_REQUEST_CODE = 1;
    public static final String EXTRA_DATA_ID = "extra_data_id";
    private Toolbar fToolbar;
    List<Creditor> fCreditors;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        fCreditorViewModel = ViewModelProviders.of(this).get(CreditorViewModel.class);

        RecyclerView mRecyclerView = findViewById(R.id.customer_recyclerview);

        fToolbar = findViewById(R.id.homeToolBar);
        setSupportActionBar(fToolbar);

        final CreditorListAdapter creditorList = new CreditorListAdapter(this);

        FloatingActionButton fab = (FloatingActionButton) findViewById(R.id.fab_create);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(MainActivity.this, NewCustomerActivity.class);
                startActivityForResult(intent, NEW_CREDITOR_ACTIVITY_REQUEST_CODE);
            }
        });

        mRecyclerView.setAdapter(creditorList);

        mRecyclerView.setLayoutManager(new LinearLayoutManager(this));

        fCreditorViewModel.getAllCreditors().observe(this, new Observer<List<Creditor>>() {
            @Override
            public void onChanged(@Nullable List<Creditor> creditors) {
                fCreditors = creditors;
                creditorList.setCreditors(creditors);
            }
        });

        creditorList.setOnClickListener(new CreditorListAdapter.ClickListener() {
            @Override
            public void onItemClick(View v, int position) {
                Creditor mCreditor = creditorList.getWordAtPosition(position);
                String name = mCreditor.getName();
                int user_id = mCreditor.getId();
                String date = mCreditor.getDate();

                Intent mIntent = new Intent(MainActivity.this, CustomerActivity.class);
                mIntent.putExtra(USER_ID, user_id);
                mIntent.putExtra(USER_NAME, name);
                mIntent.putExtra(USER_DATE, date);

                startActivity(mIntent);
            }
        });
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {

        getMenuInflater().inflate(R.menu.menu, menu);
        return super.onCreateOptionsMenu(menu);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {

        int id = item.getItemId();

        if (id == R.id.delete) {

            fCreditorViewModel.deleteAllCreditor();

            displayToast("Records deleting...");

        } else if (id == R.id.create) {
            Intent intent = new Intent(MainActivity.this, NewCustomerActivity.class);
            startActivityForResult(intent, NEW_CREDITOR_ACTIVITY_REQUEST_CODE);
        }
        return super.onOptionsItemSelected(item);
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);

        if (requestCode == NEW_CREDITOR_ACTIVITY_REQUEST_CODE && resultCode == RESULT_OK) {

            String date = Calendar.getInstance().toString();

            Creditor customer = new Creditor(data.getStringExtra(NewCustomerActivity.EXTRA_REPLY), date);

            fCreditorViewModel.insert(customer);

            displayToast("New customer added");
        } else {
            displayToast("Fields can\'t be empty");
        }

    }

    private void displayToast(String message) {
        Toast.makeText(this, message, Toast.LENGTH_SHORT).show();
    }

}
