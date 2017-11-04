package pl.kubas.rafal.activities;

import android.annotation.SuppressLint;
import android.annotation.TargetApi;
import android.content.Intent;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.support.v7.app.ActionBarActivity;
import android.support.v7.widget.CardView;
import android.view.View;

import com.app.verification.R;

@TargetApi(11)
public class MainActivity extends ActionBarActivity {

    public CardView buttonSettings;
    public CardView buttonStatus;
    public CardView buttonLogs;
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
        buttonStatus = (CardView) findViewById(R.id.buttonStatus);
        buttonStatus.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intentStatus = new Intent(MainActivity.this, StatusActivity.class);
                startActivity(intentStatus);
            }
        });
    }

    public void setSettingButton() {
        buttonSettings = (CardView) findViewById(R.id.buttonSettings);
        buttonSettings.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intentSettings = new Intent(MainActivity.this, SettingsActivity.class);
                startActivity(intentSettings);
            }
        });
    }


    public void setLogButton() {
        buttonLogs = (CardView) findViewById(R.id.buttonLogs);
        buttonLogs.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intentLogs = new Intent(MainActivity.this, LogsActivity.class);
                startActivity(intentLogs);
            }
        });
    }

    public void setExitButton() {
        buttonLogs = (CardView) findViewById(R.id.buttonExit);
        buttonLogs.setOnClickListener(new View.OnClickListener() {
            @SuppressLint("NewApi")
            @Override
            public void onClick(View view) {
                finishAffinity();
            }
        });
    }

}
