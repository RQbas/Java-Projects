package database.table;

import java.util.ArrayList;

import android.content.ContentValues;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import database.Device;

public class DeviceTable {

    public static final String DB_DEVICE_TABLE = "device";

    static final String KEY_ID = "_id";
    static final String ID_OPTIONS = "INTEGER PRIMARY KEY AUTOINCREMENT";
    static final int ID_COLUMN = 0;

    static final String KEY_ACTIVE = "active";
    static final String ACTIVE_OPTIONS = "INTEGER DEFAULT 0";
    static final int ACTIVE_COLUMN = 2;

    static final String KEY_NAME = "name";
    static final String NAME_OPTIONS = "TEXT";
    static final int NAME_COLUMN = 1;

    public static final String DB_CREATE_DEVICE_TABLE = "CREATE TABLE " + DB_DEVICE_TABLE + "( " + KEY_ID + " "
            + ID_OPTIONS + ", " + KEY_NAME + " " + NAME_OPTIONS + ", " + KEY_ACTIVE + " " + ACTIVE_OPTIONS + ");";
    public static final String DROP_DEVICE_TABLE = "DROP TABLE IF EXISTS " + DB_DEVICE_TABLE;

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

    public void updateDevice(Device device) {
        int id = device.getId();
        Boolean isON = device.isOn();
        String name = device.getName();
        int val = isON ? 1 : 0;
        updateDevice(id, name, val);
    }

    public void updateDevice(int id, String name, int ON_OFF) {
        String where = KEY_ID + "=" + id;
        ContentValues updateValues = new ContentValues();
        updateValues.put(KEY_ACTIVE, ON_OFF);
        updateValues.put(KEY_NAME, name);
        db.update(DB_DEVICE_TABLE, updateValues, where, null);
    }

    public boolean deleteDevice(int id) {
        String where = KEY_ID + "=" + id;
        return db.delete(DB_DEVICE_TABLE, where, null) > 0;
    }

    public Device getDevice(int id) {
        String[] columns = {KEY_ID, KEY_NAME, KEY_ACTIVE};
        String where = KEY_ID + "=" + id;
        Cursor cursor = db.query(DB_DEVICE_TABLE, columns, where, null, null, null, null);
        Device device = null;
        if (cursor != null && cursor.moveToFirst()) {

            String name = cursor.getString(NAME_COLUMN);
            boolean isActive = (cursor.getInt(ACTIVE_COLUMN) == 1) ? true : false;
            device = new Device(id, name, isActive);
        }
        return device;
    }

    public ArrayList<String> getAllDevicesToString() {
        ArrayList<String> list = new ArrayList<String>();
        String[] columns = {KEY_ID, KEY_NAME, KEY_ACTIVE};
        Cursor cursor = db.query(DB_DEVICE_TABLE, columns, null, null, null, null, null);
        if (cursor.moveToFirst()) {
            do {
                boolean isActive = (cursor.getInt(ACTIVE_COLUMN) == 1) ? true : false;
                Device device = new Device(cursor.getInt(ID_COLUMN), cursor.getString(NAME_COLUMN), isActive);
                list.add(device.toString());
            } while (cursor.moveToNext());
        }
        if (cursor != null && !cursor.isClosed()) {
            cursor.close();
        }

        return list;
    }

    public ArrayList<Device> getAllDevices() {
        ArrayList<Device> list = new ArrayList<Device>();
        String[] columns = {KEY_ID, KEY_NAME, KEY_ACTIVE};
        Cursor cursor = db.query(DB_DEVICE_TABLE, columns, null, null, null, null, null);
        if (cursor.moveToFirst()) {
            do {
                boolean isActive = (cursor.getInt(ACTIVE_COLUMN) == 1) ? true : false;
                Device device = new Device(cursor.getInt(ID_COLUMN), cursor.getString(NAME_COLUMN), isActive);
                list.add(device);
            } while (cursor.moveToNext());
        }
        if (cursor != null && !cursor.isClosed()) {
            cursor.close();
        }

        return list;
    }

    public void clearDeviceTable() {
        db.execSQL("DELETE FROM " + DB_DEVICE_TABLE);
        db.execSQL("DELETE FROM SQLITE_SEQUENCE WHERE NAME='" + DB_DEVICE_TABLE + "'");
    }

}
