package com.app.verification;

import android.annotation.SuppressLint;
import android.annotation.TargetApi;
import android.content.Intent;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.support.v7.app.ActionBarActivity;
import android.view.View;
import android.widget.Button;

@TargetApi(11)
public class MainActivity extends ActionBarActivity {

    public Button buttonSettings;
    public Button buttonStatus;
    public Button buttonLogs;
    private SQLiteDatabase db;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        setSettingButton();
        setStatusButton();
        setLogButton();
        setExitButton();
    }

    public void setStatusButton() {
        buttonStatus = (Button) findViewById(R.id.buttonStatus);
        buttonStatus.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intentStatus = new Intent(MainActivity.this, StatusActivity.class);
                startActivity(intentStatus);
            }
        });
    }

    public void setSettingButton() {
        buttonSettings = (Button) findViewById(R.id.buttonSettings);
        buttonSettings.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intentSettings = new Intent(MainActivity.this, SettingsActivity.class);
                startActivity(intentSettings);
            }
        });
    }


    public void setLogButton() {
        buttonLogs = (Button) findViewById(R.id.buttonLogs);
        buttonLogs.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intentLogs = new Intent(MainActivity.this, LogsActivity.class);
                startActivity(intentLogs);
            }
        });
    }

    public void setExitButton() {
        buttonLogs = (Button) findViewById(R.id.buttonExit);
        buttonLogs.setOnClickListener(new View.OnClickListener() {
            @SuppressLint("NewApi")
            @Override
            public void onClick(View view) {
                finishAffinity();
            }
        });
    }

}
