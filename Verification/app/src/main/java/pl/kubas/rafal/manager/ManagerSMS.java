package pl.kubas.rafal.manager;

import android.telephony.SmsManager;

import pl.kubas.rafal.database.DatabaseAdapter;
import pl.kubas.rafal.database.Log;
import pl.kubas.rafal.database.Token;

public class ManagerSMS {
    public SmsManager smsManager;
    public String msg;
    private DatabaseAdapter db;

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
