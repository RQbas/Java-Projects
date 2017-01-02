package admin;

import java.util.List;

import com.app.verification.R;

import android.os.Bundle;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.app.ActionBar;
import android.support.v7.app.ActionBar.Tab;
import android.support.v7.app.ActionBar.TabListener;
import android.view.Gravity;
import android.view.View;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;
import database.DatabaseAdapter;
import database.Device;


public class DevicesTab extends AdminTab implements TabListener {
    final int tabID = 1;
    DatabaseAdapter db;
    Button addButton;
    Button deleteButton;
    Button clearDeviceButton;
    TextView deviceTextView;
    EditText deviceNameField;
    ListView listNumbers;
    List<Device> numbers;
    ArrayAdapter adapter;
    ActionBar adminBar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.tab_devices);
        super.setActionBar(adminBar, this, tabID);
        setDatabase();
        setTextView();
        setAddButton();
        setDeviceNameField();
        setDeleteButton();
        setClearDeviceButton();
        setDeviceList();
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

    public void setTextView() {
        deviceTextView = (TextView) findViewById(R.id.deviceTextView);
        deviceTextView.setText("Devices panel");
    }

    public void setDeviceNameField() {
        deviceNameField = (EditText) findViewById(R.id.deviceNameField);
        deviceNameField.setGravity(Gravity.CENTER_HORIZONTAL);
    }


    public void setClearDeviceButton() {
        clearDeviceButton = (Button) findViewById(R.id.buttonClearDeviceTable);
        clearDeviceButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                db.clearDeviceTable();
            }
        });
    }

    private void setAddButton() {
        addButton = (Button) findViewById(R.id.buttonAddDevice);
        addButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String deviceName = deviceNameField.getText().toString();
                try {
                    db.insertDevice(deviceName, false);
                    Toast.makeText(getBaseContext(), "Device added", Toast.LENGTH_LONG).show();
                    deviceNameField.getText().clear();
                } catch (Exception e) {
                }
            }
        });

    }

    public void setDeleteButton() {
        deleteButton = (Button) findViewById(R.id.buttonDeleteDevice);
        deleteButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String numberID = deviceTextView.getText().toString();
                try {
                    numberID = numberID.substring((numberID.indexOf("(") + 1), numberID.indexOf(")"));
                    db.deleteDevice(Integer.parseInt(numberID));
                    Toast.makeText(getBaseContext(), "Device deleted", Toast.LENGTH_LONG).show();
                } catch (Exception e) {
                }

            }
        });
    }

    public void setDeviceList() {
        adapter = new ArrayAdapter<String>(this, android.R.layout.simple_list_item_1, db.getAllDevicesToString());
        listNumbers = (ListView) findViewById(R.id.listView);
        listNumbers.setAdapter(adapter);


        listNumbers.setOnItemClickListener(new OnItemClickListener() {

            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                String device = ((TextView) view).getText().toString();
                deviceTextView.setText(device);
            }
        });
        {
        }

    }
}
