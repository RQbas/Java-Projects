package com.app.verification;

import java.util.List;

import android.annotation.SuppressLint;
import android.os.Bundle;
import android.support.v7.app.ActionBarActivity;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.TextView;
import database.DatabaseAdapter;
import database.Log;


@SuppressLint("NewApi")
public class LogsActivity extends ActionBarActivity {
    DatabaseAdapter db;
    ArrayAdapter adapter;
    TextView logTextView;
    ListView logList;
    List<Log> list;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_logs);
        setDatabase();
        setTextView();
        setLogList();
    }

    private void setDatabase() {
        db = new DatabaseAdapter(getApplicationContext());
        db.open();
    }

    public void setTextView() {
        logTextView = (TextView) findViewById(R.id.logTextView);
        logTextView.setText("Logs");
    }

    public void setLogList() {
        adapter = new ArrayAdapter<Log>(this, android.R.layout.simple_list_item_1, db.getAllLogs());
        logList = (ListView) findViewById(R.id.logList);
        logList.setAdapter(adapter);

    }
}
