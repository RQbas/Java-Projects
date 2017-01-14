package devicelist;

import com.app.verification.R;
import com.app.verification.SettingsActivity;

import android.content.Context;
import android.content.Intent;
import android.graphics.Color;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.Button;
import android.widget.ListAdapter;
import android.widget.TextView;
import android.widget.Toast;
import database.DatabaseAdapter;
import database.Device;
import database.Log;
import database.Token;
import manager.ManagerSMS;

public class DeviceListAdapter extends BaseAdapter implements ListAdapter {
    private Context context;
    ManagerSMS managerSMS;
    TextView deviceName;
    Button statusButton;
    boolean isNumberProvided;
    DatabaseAdapter db;

    Device selectedDevice;
    Log logSelectedDevice;


    public DeviceListAdapter(Context context, DatabaseAdapter db) {
        setDatabaseAdapter(db);
        setContext(context);
        setManagerSMS();
    }

    private void setDatabaseAdapter(DatabaseAdapter db) {
        this.db = db;
    }

    private void setContext(Context context) {
        this.context = context;
    }

    private void setManagerSMS() {
        managerSMS = new ManagerSMS(db);
    }

    @Override
    public int getCount() {
        return db.getAllDevices().size();
    }

    @Override
    public Object getItem(int position) {
        return db.getAllDevices().get(position);
    }

    @Override
    public long getItemId(int position) {
        return db.getAllDevices().get(position).getId();
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        View view = convertView;
        if (view == null) {
            LayoutInflater inflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
            view = inflater.inflate(R.layout.list_device, null);
        }
        deviceName = (TextView) view.findViewById(R.id.deviceName);
        deviceName.setText(db.getAllDevices().get(position).getName());


        statusButton = (Button) view.findViewById(R.id.statusButton);
        statusButton.setText(db.getAllDevices().get(position).displayForButton());
        statusButton.setTag(position);
        setButtonColor(db.getAllDevices().get(position).isOn());

        statusButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                int index = (Integer) v.getTag();
                try {
                    getSelectedDevice(index);
                    changeSelectedDeviceStatus();
                    updateSelectedDevice();
                    generateNewLog();
                    manageSendingSMS();
                    changeButtonDisplay();
                    notifyDataSetChanged();
                } catch (Exception e) {
                    assertNumberProvided();
                }
            }

            private void getSelectedDevice(int index) {
                selectedDevice = db.getAllDevices().get(index);
            }

            private void changeSelectedDeviceStatus() {
                selectedDevice.changeStatus();
            }

            private void updateSelectedDevice() {
                db.updateDevice(selectedDevice);
            }

            private void generateNewLog() {
                logSelectedDevice = new Log(selectedDevice.getName(), selectedDevice.isOn());
                db.insertLog(logSelectedDevice);
            }

            private void manageSendingSMS() {
                managerSMS.setMSG(logSelectedDevice, generateTokenSMS());
                managerSMS.sendSMS();
            }

            private void changeButtonDisplay() {
                setButtonColor(selectedDevice.isOn());
                statusButton.setText(selectedDevice.displayForButton());
            }

            private Token generateTokenSMS() {
                return db.getTokenToSMS();
            }


        });

        return view;
    }

    void setButtonColor(boolean isOn) {
        if (isOn)
            statusButton.setBackgroundColor(Color.rgb(255, 0, 0));
        else
            statusButton.setBackgroundColor(Color.rgb(0, 255, 0));

    }

    private void assertNumberProvided() {
        if (db.isPhoneTableEmpty()) {
            Toast.makeText(context, "Provide phone number", Toast.LENGTH_LONG).show();
            Intent intentSettings = new Intent(context, SettingsActivity.class);
            context.startActivity(intentSettings);
        }
    }
}


