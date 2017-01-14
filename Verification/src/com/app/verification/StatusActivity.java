package com.app.verification;

import android.annotation.SuppressLint;
import android.os.Bundle;
import android.support.v7.app.ActionBarActivity;
import android.widget.ListView;
import android.widget.TextView;
import database.DatabaseAdapter;
import devicelist.DeviceListAdapter;


@SuppressLint("NewApi")
public class StatusActivity extends ActionBarActivity {
    ListView deviceList;
    TextView statusTextView;
    DeviceListAdapter adapterDL;
    DatabaseAdapter db;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_status);
        setDatabase();
        setTextView();
        setDeviceList();

    }

    private void setDatabase() {
        db = new DatabaseAdapter(getApplicationContext());
        db.open();
    }

    public void setTextView() {
        statusTextView = (TextView) findViewById(R.id.statusTextView);
        statusTextView.setText("Device List");
    }

    public void setDeviceList() {
        adapterDL = new DeviceListAdapter(this, db);
        deviceList = (ListView) findViewById(R.id.deviceList);
        deviceList.setAdapter(adapterDL);
    }
}
