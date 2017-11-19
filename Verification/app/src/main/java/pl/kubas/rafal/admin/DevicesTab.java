package pl.kubas.rafal.admin;

import android.annotation.SuppressLint;
import android.os.Bundle;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.app.ActionBar;
import android.support.v7.app.ActionBar.Tab;
import android.support.v7.app.ActionBar.TabListener;
import android.support.v7.widget.CardView;
import android.view.View;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.Toast;

import com.app.verification.R;

import pl.kubas.rafal.database.DatabaseAdapter;
import pl.kubas.rafal.dialogs.NewDeviceDialog;


public class DevicesTab extends AdminTab implements TabListener {
    private final int tabID = 1;
    private DatabaseAdapter db;
    private CardView addButton;
    private CardView deleteButton;
    private CardView clearDeviceButton;
    private ListView listDevices;
    private ArrayAdapter<String> adapter;
    private ActionBar adminBar;
    private Runnable run;
    private int selectedItemIndex = 0;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.tab_devices);
        super.setActionBar(adminBar, this, tabID);
        setDatabase();
        setDeviceList();
        setAddButton();
        setDeleteButton();
        setClearDeviceButton();
        setDeviceListUpdater();
    }

    @Override
    public void onWindowFocusChanged(boolean hasFocus) {
        super.onWindowFocusChanged(hasFocus);
        if (hasFocus) {
            runOnUiThread(run);
        }
    }

    @Override
    public void onTabReselected(Tab tab, FragmentTransaction ft) {
    }

    @Override
    public void onTabSelected(Tab tab, FragmentTransaction ft) {
        super.setTabActivity(this, tab.getPosition());
    }

    @Override
    public void onTabUnselected(Tab arg0, FragmentTransaction arg1) {
        super.updateFirstInitialization();

    }

    private void setDatabase() {
        db = new DatabaseAdapter(getApplicationContext());
        db.open();
    }

    void setDeviceListUpdater() {
        run = new Runnable() {
            @SuppressLint("NewApi")
            public void run() {
                adapter.clear();
                adapter.addAll(db.getAllDevicesToString());
                adapter.notifyDataSetChanged();
                listDevices.invalidateViews();
                listDevices.refreshDrawableState();
            }
        };
    }


    private void updateDeviceList() {
        runOnUiThread(run);
    }

    public void setClearDeviceButton() {
        clearDeviceButton = (CardView) findViewById(R.id.buttonClearDeviceTable);
        clearDeviceButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                db.clearDeviceTable();
                updateDeviceList();
                Toast.makeText(getBaseContext(), "Device list cleaned", Toast.LENGTH_LONG).show();
            }
        });
    }


    private void setAddButton() {
        addButton = (CardView) findViewById(R.id.buttonAddDevice);
        addButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                new NewDeviceDialog(DevicesTab.this, db);
            }
        });
    }

    public void setDeleteButton() {
        deleteButton = (CardView) findViewById(R.id.buttonDeleteDevice);
        deleteButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                try {
                    db.deleteDevice(getItemIndex());
                    Toast.makeText(getBaseContext(), "Device deleted", Toast.LENGTH_LONG).show();
                    updateDeviceList();
                } catch (Exception e) {
                }

            }
        });
    }

    private int getItemIndex() {
        String deviceIndex = adapter.getItem(selectedItemIndex).toString();
        deviceIndex = deviceIndex.substring((deviceIndex.indexOf("(") + 1), deviceIndex.indexOf(")"));
        return Integer.parseInt(deviceIndex);
    }

    public void setDeviceList() {
        adapter = new ArrayAdapter<String>(this, R.layout.list_text, db.getAllDevicesToString());
        listDevices = (ListView) findViewById(R.id.listView);
        listDevices.setAdapter(adapter);
        listDevices.setSelector(android.R.color.darker_gray);

        listDevices.setOnItemClickListener(new OnItemClickListener() {

            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                selectedItemIndex = position;
            }
        });
    }
}
