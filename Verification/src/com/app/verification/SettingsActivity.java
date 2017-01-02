package com.app.verification;

import java.util.ArrayList;
import java.util.List;

import admin.PhoneTab;
import android.annotation.SuppressLint;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.ActionBarActivity;
import android.view.Gravity;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;
import database.DatabaseAdapter;
import database.PhoneNumber;


@SuppressLint("NewApi")
public class SettingsActivity extends ActionBarActivity {
    DatabaseAdapter db;

    TextView settingsTextView;
    EditText phoneTextField;
    Button buttonSave;
    ListView listNumbers;
    String ADMIN_PASS = "+1+";
    private List<PhoneNumber> numbers;

    ArrayAdapter adapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_settings);
        setDatabase();
        setTextView();
        setPhoneTextField();
        setSaveButton();
    }


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.logs, menu);
        return true;
    }



    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        int id = item.getItemId();
        if (id == R.id.action_settings) {
            String pass = phoneTextField.getText().toString();
            if (pass.equals("+1")) {
                Intent launchAdminPanel = new Intent(SettingsActivity.this, PhoneTab.class);
                startActivity(launchAdminPanel);
            }
            return true;
        }
        return false;
    }



    private void setDatabase() {
        db = new DatabaseAdapter(getApplicationContext());
        db.open();
    }


    public void setPhoneTextField() {
        phoneTextField = (EditText) findViewById(R.id.phoneNumberText);
        phoneTextField.setGravity(Gravity.CENTER_HORIZONTAL);
    }

    public void setTextView() {
        settingsTextView = (TextView) findViewById(R.id.settingsTextView);
        settingsTextView.setText("Enter phone number");
    }

    public void setSaveButton() {
        buttonSave = (Button) findViewById(R.id.buttonSaveNumber);
        buttonSave.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String phoneNumber = phoneTextField.getText().toString();
                numbers = new ArrayList<PhoneNumber>();
                db.insertNumber(phoneNumber);
                Toast.makeText(getBaseContext(), "Number added", Toast.LENGTH_LONG).show();

            }
        });
    }

}
