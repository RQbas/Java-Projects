package com.app.verification;

import java.util.ArrayList;

import android.annotation.SuppressLint;
import android.os.Bundle;
import android.support.v7.app.ActionBarActivity;
import android.widget.ListView;
import android.widget.TextView;
import database.DatabaseAdapter;
import database.Device;
import devicelist.DeviceListAdapter;


@SuppressLint("NewApi")
public class StatusActivity extends ActionBarActivity {
    ListView deviceList;
    static TextView statusTextView;
    DeviceListAdapter adapterDL;
    static ArrayList<Device> list;
    DatabaseAdapter db;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_status);
        setDatabase();
        setTextView();
        setDeviceList();
    }

    @Override
    protected void onDestroy() {
        updateDeviceListDB();
        super.onDestroy();
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
        adapterDL = new DeviceListAdapter(list, this);
        deviceList = (ListView) findViewById(R.id.deviceList);
        deviceList.setAdapter(adapterDL);
    }

    public void createDevices(ArrayList<Device> list) {
        list = db.getAllDevices();
        if (list.isEmpty()) {
            db.insertDevice("Router", false);
            db.insertDevice("Blender", false);
        }

    }

    static public void updateDeviceList(ArrayList<Device> overridingList) {
        list = overridingList;
    }

    public void updateDeviceListDB() {
        for (Device device : list)
            db.updateDevice(device);
    }



}
