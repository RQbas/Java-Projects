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
    TextView statusTextView;
    DeviceListAdapter adapterDL;
    ArrayList<Device> list;
    DatabaseAdapter db;
    static int indexDevice;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_status);
        setDatabase();
        setTextView();
        setDeviceList(statusTextView);
    }

    private void setDatabase() {
        db = new DatabaseAdapter(getApplicationContext());
        db.open();

    }

    public void setDeviceList(TextView textview) {
        createDevices(list);
        list = db.getAllDevices();
        adapterDL = new DeviceListAdapter(list, this, textview, this);
        deviceList = (ListView) findViewById(R.id.deviceList);
        deviceList.setAdapter(adapterDL);
        statusTextView.setText(String.valueOf(indexDevice));


    }

    static public void getDevicePosition(int position) {
        indexDevice = position;

    }

    public void setTextView() {
        statusTextView = (TextView) findViewById(R.id.statusTextView);
        statusTextView.setText("Device List");
    }

    public void createDevices(ArrayList<Device> list) {
        list = db.getAllDevices();
        if (list.isEmpty()) {
            db.insertDevice("Router", false);
            db.insertDevice("Blender", false);
        }

    }


}
