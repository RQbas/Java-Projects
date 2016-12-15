package database;

import java.util.ArrayList;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.SQLException;
import android.database.sqlite.SQLiteDatabase;

public class DatabaseAdapter {
    static final String DEBUG_TAG = "SqLiteDB";

    static final int DB_VERSION = 1;
    static final String DB_NAME = "database.db";

    static final String KEY_ID = "_id";
    static final String ID_OPTIONS = "INTEGER PRIMARY KEY AUTOINCREMENT";
    static final int ID_COLUMN = 0;



    static final String DB_PHONE_TABLE = "phoneNumber";
    static final String KEY_PHONE = "phone";
    static final String PHONE_OPTIONS = "INTEGER DEFAULT 0";
    static final int PHONE_COLUMN = 1;

    static final String DB_CREATE_PHONE_TABLE = "CREATE TABLE " + DB_PHONE_TABLE + "( " + KEY_ID + " " + ID_OPTIONS
            + ", " + KEY_PHONE + " " + PHONE_OPTIONS + ");";
    static final String DROP_PHONE_TABLE = "DROP TABLE IF EXISTS " + DB_PHONE_TABLE;



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


    static final String DB_LOGS_TABLE = "logs";

    static final String LOG_KEY_DATE = "date";
    static final String LOG_DATE_OPTIONS = "TEXT";
    static final int LOG_DATE_COLUMN = 1;

    static final String LOG_KEY_NAME = "name";
    static final String LOG_NAME_OPTIONS = "TEXT";
    static final int LOG_NAME_COLUMN = 2;

    static final String LOG_KEY_ACTIVE = "active";
    static final String LOG_ACTIVE_OPTIONS = "TEXT";
    static final int LOG_ACTIVE_COLUMN = 3;

    static final String DB_CREATE_LOGS_TABLE = "CREATE TABLE " + DB_LOGS_TABLE + "( " + KEY_ID + " " + ID_OPTIONS + ", "
            + LOG_KEY_DATE + " " + LOG_DATE_OPTIONS + ", " + LOG_KEY_NAME + " " + LOG_NAME_OPTIONS + ", "
            + LOG_KEY_ACTIVE + " " + LOG_ACTIVE_OPTIONS + ");";
    static final String DROP_LOGS_TABLE = "DROP TABLE IF EXISTS " + DB_LOGS_TABLE;

    private SQLiteDatabase db;
    private Context context;
    private DatabaseHelper dbHelper;


    public DatabaseAdapter(Context context) {
        this.context = context;
    }

    public DatabaseAdapter open() {
        dbHelper = new DatabaseHelper(context, DB_NAME, null, DB_VERSION);
        try {
            db = dbHelper.getWritableDatabase();
        } catch (SQLException e) {
            db = dbHelper.getReadableDatabase();
        }
        return this;
    }

    public void close() {
        dbHelper.close();
    }

    public String getName() {
        return DB_NAME;
    }

    public void insertLog(Log log) {
        ContentValues newLog = new ContentValues();
        newLog.put(LOG_KEY_DATE, log.getDate());
        newLog.put(LOG_KEY_NAME, log.getName());
        newLog.put(LOG_KEY_ACTIVE, log.getStatus());
        db.insert(DB_LOGS_TABLE, null, newLog);
    }


    public void insertDevice(String name, boolean isActive) {
        ContentValues newDevice = new ContentValues();
        int val = isActive ? 1 : 0;
        newDevice.put(KEY_ACTIVE, val);
        newDevice.put(KEY_NAME, name);
        db.insert(DB_DEVICE_TABLE, null, newDevice);
    }

    public ArrayList<Log> getAllLogs() {
        ArrayList<Log> list = new ArrayList<Log>();
        String[] columns = {KEY_ID, LOG_KEY_DATE, LOG_KEY_NAME, LOG_KEY_ACTIVE};
        Cursor cursor = db.query(DB_LOGS_TABLE, columns, null, null, null, null, null);
        if (cursor.moveToFirst()) {
            do {
                Log log = new Log(cursor.getInt(ID_COLUMN), cursor.getString(LOG_DATE_COLUMN),
                        cursor.getString(LOG_NAME_COLUMN), cursor.getString(LOG_ACTIVE_COLUMN));
                list.add(log);
            } while (cursor.moveToNext());
        }
        if (cursor != null && !cursor.isClosed()) {
            cursor.close();
        }

        return list;
    }

    public void clearLogsTable() {
        db.execSQL("DELETE FROM " + DB_LOGS_TABLE);
        db.execSQL("DELETE FROM SQLITE_SEQUENCE WHERE NAME='" + DB_LOGS_TABLE + "'");
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

    public Device getDevice(int id) {
        String[] columns = {KEY_ID, KEY_NAME, KEY_ACTIVE};
        String where = KEY_ID + "=" + id;
        Cursor cursor = db.query(DB_DEVICE_TABLE, columns, where, null, null, null, null);
        Device device = null;
        if (cursor != null && cursor.moveToFirst()) {

            String name = cursor.getString(NAME_COLUMN);
            int active = Integer.parseInt(cursor.getString(ACTIVE_COLUMN));
            boolean isActive = (cursor.getInt(ACTIVE_COLUMN) == 1) ? true : false;
            device = new Device(id, name, isActive);
        }
        return device;
    }

    public boolean deleteDevice(int id) {
        String where = KEY_ID + "=" + id;
        return db.delete(DB_DEVICE_TABLE, where, null) > 0;
    }


    public void clearDeviceTable() {
        db.execSQL("DELETE FROM " + DB_DEVICE_TABLE);
        db.execSQL("DELETE FROM SQLITE_SEQUENCE WHERE NAME='" + DB_DEVICE_TABLE + "'");
    }



    public int insertNumber(String phoneNumber) {
        ContentValues newPhoneNumber = new ContentValues();
        newPhoneNumber.put(KEY_PHONE, phoneNumber);
        return (int) db.insert(DB_PHONE_TABLE, null, newPhoneNumber);
    }

    public boolean updateNumber(PhoneNumber number) {
        int id = number.getId();
        String phoneNumber = number.getPhoneNumber();
        return updateNumber(id, phoneNumber);
    }

    public boolean updateNumber(int id, String phoneNumber) {
        String where = KEY_ID + "=" + id;
        int Number = Integer.parseInt(phoneNumber);
        ContentValues updateValues = new ContentValues();
        updateValues.put(KEY_PHONE, Number);
        return db.update(DB_PHONE_TABLE, updateValues, where, null) > 0;
    }

    public boolean deleteNumber(int id) {
        String where = KEY_ID + "=" + id;
        return db.delete(DB_PHONE_TABLE, where, null) > 0;
    }

    public void clearPhoneTable() {
        db.execSQL("DELETE FROM " + DB_PHONE_TABLE);
        db.execSQL("DELETE FROM SQLITE_SEQUENCE WHERE NAME='" + DB_PHONE_TABLE + "'");
    }

    public ArrayList<String> getAllNumbers() {
        ArrayList<String> list = new ArrayList<String>();
        String[] columns = {KEY_ID, KEY_PHONE};
        Cursor cursor = db.query(DB_PHONE_TABLE, columns, null, null, null, null, null);
        if (cursor.moveToFirst()) {
            do {
                PhoneNumber phoneNumber = new PhoneNumber(Integer.parseInt(cursor.getString(0)), cursor.getString(1));
                list.add(phoneNumber.toString());
            } while (cursor.moveToNext());
        }
        if (cursor != null && !cursor.isClosed()) {
            cursor.close();
        }

        return list;
    }

    public ArrayList<String> getAllOnlyNumbers() {
        ArrayList<String> list = new ArrayList<String>();
        String[] columns = {KEY_ID, KEY_PHONE};
        Cursor cursor = db.query(DB_PHONE_TABLE, columns, null, null, null, null, null);
        if (cursor.moveToFirst()) {
            do {
                PhoneNumber phoneNumber = new PhoneNumber(Integer.parseInt(cursor.getString(0)), cursor.getString(1));
                list.add(phoneNumber.getPhoneNumber());
            } while (cursor.moveToNext());
        }
        if (cursor != null && !cursor.isClosed()) {
            cursor.close();
        }

        return list;
    }

    public PhoneNumber getPhoneNumber(int id) {
        String[] columns = {KEY_ID, KEY_PHONE};
        String where = KEY_ID + "=" + id;
        Cursor cursor = db.query(DB_PHONE_TABLE, columns, where, null, null, null, null);
        PhoneNumber number = null;
        if (cursor != null && cursor.moveToFirst()) {
            String phoneNumber = cursor.getString(PHONE_COLUMN);
            number = new PhoneNumber(id, phoneNumber);
        }
        return number;
    }

}
