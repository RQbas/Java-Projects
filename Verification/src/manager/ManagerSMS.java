package manager;

import java.util.ArrayList;

import android.telephony.SmsManager;

public class ManagerSMS {
    public SmsManager smsManager;
    public ArrayList<String> phoneList = new ArrayList<String>();
    public String msg;

    public ManagerSMS(ArrayList<String> phoneList) {
        this.phoneList = phoneList;
        smsManager = SmsManager.getDefault();

    }

    public void setMSG(String msg) {
        this.msg = msg;
    }

    public void sendSMS() {
        smsManager.sendTextMessage(phoneList.get(0), null, msg, null, null);
    }
}
