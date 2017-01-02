package devicelist;

import java.util.ArrayList;

import com.app.verification.R;
import com.app.verification.StatusActivity;

import android.content.Context;
import android.graphics.Color;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.Button;
import android.widget.ListAdapter;
import android.widget.TextView;
import database.Device;
import database.Log;
import manager.ManagerSMS;

public class DeviceListAdapter extends BaseAdapter implements ListAdapter {
    private ArrayList<Device> list = new ArrayList<Device>();
    private Context context;
    ManagerSMS managerSMS;
    TextView deviceName;
    Button statusButton;


    public DeviceListAdapter(ArrayList<Device> list, Context context, ArrayList<String> phoneList) {
        this.list = list;
        this.context = context;
        managerSMS = new ManagerSMS(phoneList);
    }



    @Override
    public int getCount() {
        return list.size();
    }



    @Override
    public Object getItem(int position) {
        return list.get(position);
    }



    @Override
    public long getItemId(int position) {
        return list.get(position).getId();
    }



    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        View view = convertView;
        if (view == null) {
            LayoutInflater inflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
            view = inflater.inflate(R.layout.list_device, null);
        }
        deviceName = (TextView) view.findViewById(R.id.deviceName);
        deviceName.setText(list.get(position).getName());


        statusButton = (Button) view.findViewById(R.id.statusButton);
        statusButton.setText(list.get(position).displayForButton());
        statusButton.setTag(position);
        setButtonColor(list.get(position).isOn());

        statusButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                int position = (Integer) v.getTag();

                list.get(position).changeStatus();
                Log log = new Log(list.get(position).getName(), list.get(position).isOn());

                StatusActivity.updateDeviceList(list);
                StatusActivity.addLog(log);

                managerSMS.setMSG(log.toStringSMS());
                managerSMS.sendSMS();

                setButtonColor(list.get(position).isOn());
                statusButton.setText(list.get(position).displayForButton());


                notifyDataSetChanged();
            }
        });
        return view;
    }



    public ArrayList<Device> getList() {
        return this.list;
    }

    void setButtonColor(boolean isOn) {
        if (isOn)
            statusButton.setBackgroundColor(Color.rgb(255, 0, 0));
        else
            statusButton.setBackgroundColor(Color.rgb(0, 255, 0));

    }
}


