package database.table;

import android.content.ContentValues;
import android.database.sqlite.SQLiteDatabase;

public class DeviceTable {

    static final String KEY_ID = "_id";
    static final String ID_OPTIONS = "INTEGER PRIMARY KEY AUTOINCREMENT";
    static final int ID_COLUMN = 0;

    static final String DB_DEVICE_TABLE = "device";
    static final String KEY_ACTIVE = "active";
    static final String ACTIVE_OPTIONS = "INTEGER DEFAULT 0";
    static final int ACTIVE_COLUMN = 2;
    static final String KEY_NAME = "name";
    static final String NAME_OPTIONS = "TEXT";
    static final int NAME_COLUMN = 1;
    static final String DB_CREATE_DEVICE_TABLE = "CREATE TABLE " + DB_DEVICE_TABLE + "( " + KEY_ID + " " + ID_OPTIONS
            + ", " + KEY_NAME + " " + NAME_OPTIONS + ", " + KEY_ACTIVE + " " + ACTIVE_OPTIONS + ");";
    static final String DROP_DEVICE_TABLE = "DROP TABLE IF EXISTS " + DB_DEVICE_TABLE;

    SQLiteDatabase db;

    public DeviceTable(SQLiteDatabase db) {
        this.db = db;
    }

    public void insertDevice(String name, boolean isActive) {
        ContentValues newDevice = new ContentValues();
        int val = isActive ? 1 : 0;
        newDevice.put(KEY_ACTIVE, val);
        newDevice.put(KEY_NAME, name);
        db.insert(DB_DEVICE_TABLE, null, newDevice);
    }

}
