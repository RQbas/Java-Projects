package com.app.verification;

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
    private SQLiteDatabase db;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        setSettingButton();
        setStatusButton();
    }

    public void setStatusButton() {
        buttonSettings = (Button) findViewById(R.id.buttonStatus);
        buttonSettings.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intentSettings = new Intent(MainActivity.this, StatusActivity.class);
                startActivity(intentSettings);
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

}
