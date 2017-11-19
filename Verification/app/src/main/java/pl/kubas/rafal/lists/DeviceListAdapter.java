package pl.kubas.rafal.lists;

import android.content.Context;
import android.content.Intent;
import android.support.v4.content.ContextCompat;
import android.support.v7.widget.CardView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.ListAdapter;
import android.widget.TextView;
import android.widget.Toast;

import com.app.verification.R;

import pl.kubas.rafal.activities.SettingsActivity;
import pl.kubas.rafal.database.DatabaseAdapter;
import pl.kubas.rafal.database.Device;
import pl.kubas.rafal.database.Log;
import pl.kubas.rafal.database.Token;
import pl.kubas.rafal.manager.ManagerSMS;

public class DeviceListAdapter extends BaseAdapter implements ListAdapter {
    private Context context;
    private ManagerSMS managerSMS;
    private TextView deviceName;
    private CardView statusButton;
    private ImageView statusOnOFF;
    private boolean isNumberProvided;
    private DatabaseAdapter db;

    private Device selectedDevice;
    private Log logSelectedDevice;


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
        statusOnOFF = (ImageView) view.findViewById(R.id.statusOnOFF);
        statusButton = (CardView) view.findViewById(R.id.statusButton);
        deviceName = (TextView) view.findViewById(R.id.deviceName);

        deviceName.setText(db.getAllDevices().get(position).getName());
        statusButton.setTag(position);
        setCardViewColor(db.getAllDevices().get(position).isOn());

        statusButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                int index = (Integer) v.getTag();

                if (isNumberProvided()) {
                    getSelectedDevice(index);
                    changeSelectedDeviceStatus();
                    updateSelectedDevice();
                    generateNewLog();
                    manageSendingSMS();
                    changeButtonDisplay();
                    notifyDataSetChanged();
                } else {
                    handleNumberNotProvided();
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
                setCardViewColor(selectedDevice.isOn());
            }

            private Token generateTokenSMS() {
                return db.getTokenToSMS();
            }


        });

        return view;
    }

    void setCardViewColor(boolean isOn) {
        statusOnOFF.setImageDrawable(isOn ? context.getDrawable(R.drawable.ic_on) : context.getDrawable(R.drawable.ic_off));
        deviceName.setTextColor(isOn ? ContextCompat.getColor(context, R.color.orangeLight) : ContextCompat.getColor(context, R.color.grayDark));
    }

    private boolean isNumberProvided() {
        return db.isPhoneTableEmpty() ? false : true;

    }

    private void handleNumberNotProvided() {
        Toast.makeText(context, "Provide phone number", Toast.LENGTH_LONG).show();
        Intent intentSettings = new Intent(context, SettingsActivity.class);
        context.startActivity(intentSettings);
    }
}


