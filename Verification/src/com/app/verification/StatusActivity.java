package com.app.verification;

import java.util.ArrayList;

import android.annotation.SuppressLint;
import android.os.Bundle;
import android.support.v7.app.ActionBarActivity;
import android.widget.ListView;
import android.widget.TextView;
import database.DatabaseAdapter;
import database.Device;
import database.Log;
import devicelist.DeviceListAdapter;


@SuppressLint("NewApi")
public class StatusActivity extends ActionBarActivity {
    ListView deviceList;
    TextView statusTextView;
    DeviceListAdapter adapterDL;
    static ArrayList<Device> list;
    static ArrayList<Log> logList;
    static ArrayList<String> phoneList;
    DatabaseAdapter db;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_status);
        setDatabase();
        setTextView();
        setLogList();
        setDeviceList();

    }

    public void setLogList() {
        logList = new ArrayList<Log>();
    }

    @Override
    protected void onDestroy() {
        updateDeviceListDB();
        updateLogList();
        super.onDestroy();
    }

    private void updateLogList() {
        for (Log log : logList)
            db.insertLog(log);
        logList.clear();

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
        createDevices(list);
        list = db.getAllDevices();
        phoneList = db.getAllOnlyNumbers();
        adapterDL = new DeviceListAdapter(list, this, phoneList);
        deviceList = (ListView) findViewById(R.id.deviceList);
        deviceList.setAdapter(adapterDL);
    }

    public void createDevices(ArrayList<Device> list) {
        list = db.getAllDevices();
    }

    static public void updateDeviceList(ArrayList<Device> overridingList) {
        list = overridingList;
    }

    static public void addLog(Log log) {
        logList.add(log);
    }



    public void updateDeviceListDB() {
        for (Device device : list)
            db.updateDevice(device);
    }



}
