package manager;

import android.telephony.SmsManager;
import database.DatabaseAdapter;
import database.Log;
import database.Token;

public class ManagerSMS {
    public SmsManager smsManager;
    public String msg;
    DatabaseAdapter db;

    public ManagerSMS(DatabaseAdapter db) {
        this.db = db;
        smsManager = SmsManager.getDefault();

    }

    public void setMSG(Log log, Token token) {
        msg = token.toStringSMS() + " " + log.toStringSMS();
    }

    public void setMSG(String msg) {
        this.msg = msg;
    }

    public void sendSMS() {
        smsManager.sendTextMessage(db.getAllOnlyNumbers().get(0), null, msg, null, null);
    }
}
