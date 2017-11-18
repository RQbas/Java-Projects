package pl.kubas.rafal.activities;

import android.Manifest;
import android.annotation.SuppressLint;
import android.os.Bundle;
import android.support.v4.app.ActivityCompat;
import android.support.v7.app.ActionBarActivity;
import android.support.v7.app.AppCompatActivity;
import android.widget.ListView;
import android.widget.TextView;

import com.app.verification.R;

import pl.kubas.rafal.database.DatabaseAdapter;
import pl.kubas.rafal.devicelist.DeviceListAdapter;


@SuppressLint("NewApi")
public class StatusActivity extends AppCompatActivity {
    ListView deviceList;
    TextView statusTextView;
    DeviceListAdapter adapterDL;
    DatabaseAdapter db;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_status);
        ActivityCompat.requestPermissions(this,new String[]{Manifest.permission.SEND_SMS},1);
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
