package devicelist;

import java.util.ArrayList;

import com.app.verification.R;

import android.content.Context;
import android.graphics.Color;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.Button;
import android.widget.ListAdapter;
import android.widget.TextView;

public class DeviceListAdapter extends BaseAdapter implements ListAdapter {
    private ArrayList<Device> list = new ArrayList<Device>();
    private Context context;
    TextView deviceName;
    Button statusButton;


    public DeviceListAdapter(ArrayList<Device> list, Context context) {
        this.list = list;
        this.context = context;
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

        // Handle buttons and add onClickListeners
        statusButton = (Button) view.findViewById(R.id.statusButton);
        statusButton.setText(list.get(position).displayForButton());
        statusButton.setTag(position);
        setButtonColor(list.get(position).isOn());

        statusButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                int position = (Integer) v.getTag();
                statusButton.setText(list.get(position).displayForButton());
                list.get(position).changeStatus();
                setButtonColor(list.get(position).isOn());
                notifyDataSetChanged();
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
}


