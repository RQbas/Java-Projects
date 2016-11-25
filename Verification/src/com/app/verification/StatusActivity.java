package com.app.verification;

import java.util.ArrayList;

import android.annotation.SuppressLint;
import android.os.Bundle;
import android.support.v7.app.ActionBarActivity;
import android.widget.ListView;
import android.widget.TextView;
import devicelist.Device;
import devicelist.DeviceListAdapter;


@SuppressLint("NewApi")
public class StatusActivity extends ActionBarActivity {
    ListView deviceList;
    TextView statusTextView;
    DeviceListAdapter adapterDL;
    ArrayList<Device> list;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_status);
        setTextView();
        setDeviceList();
    }

    public void setDeviceList() {
        list = new ArrayList<Device>();
        addNewDevice("Router");
        addNewDevice("Blender");


        adapterDL = new DeviceListAdapter(list, this);
        deviceList = (ListView) findViewById(R.id.deviceList);
        deviceList.setAdapter(adapterDL);

    }

    public void setTextView() {
        statusTextView = (TextView) findViewById(R.id.statusTextView);
        statusTextView.setText("Devices list");
    }

    public void addNewDevice(String name) {
        list.add(new Device(list.size() + 1, name));

    }


}
