package pl.kubas.rafal.activities;

import android.Manifest;
import android.annotation.SuppressLint;
import android.os.Bundle;
import android.support.v4.app.ActivityCompat;
import android.support.v7.app.AppCompatActivity;
import android.view.MenuItem;
import android.widget.ListView;

import com.app.verification.R;

import pl.kubas.rafal.database.DatabaseAdapter;
import pl.kubas.rafal.lists.DeviceListAdapter;


@SuppressLint("NewApi")
public class StatusActivity extends AppCompatActivity {
    private ListView deviceList;
    private DeviceListAdapter adapterDL;
    private DatabaseAdapter db;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_status);
        ActivityCompat.requestPermissions(this, new String[]{Manifest.permission.SEND_SMS}, 1);
        setDatabase();
        setDeviceList();
        setBackButton();
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case android.R.id.home:
                finish();
                return true;
        }
        return super.onOptionsItemSelected(item);
    }

    private void setBackButton() {
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
    }

    private void setDatabase() {
        db = new DatabaseAdapter(getApplicationContext());
        db.open();
    }

    public void setDeviceList() {
        adapterDL = new DeviceListAdapter(this, db);
        deviceList = (ListView) findViewById(R.id.deviceList);
        deviceList.setAdapter(adapterDL);
    }
}
