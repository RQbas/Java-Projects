package database;

import java.util.ArrayList;

import android.content.Context;
import android.database.SQLException;
import android.database.sqlite.SQLiteDatabase;
import database.table.DeviceTable;
import database.table.LogTable;
import database.table.PhoneTable;
import database.table.TokenTable;

public class DatabaseAdapter {
    static final String DEBUG_TAG = "SqLiteDB";

    static final int DB_VERSION = 1;
    static final String DB_NAME = "database.db";

    private SQLiteDatabase db;
    private Context context;
    private DatabaseHelper dbHelper;
    private DeviceTable dt;
    private PhoneTable pt;
    private LogTable lt;
    private TokenTable tt;

    public DatabaseAdapter(Context context) {
        this.context = context;
    }

    public DatabaseAdapter open() {
        dbHelper = new DatabaseHelper(context, DB_NAME, null, DB_VERSION);
        try {
            db = dbHelper.getWritableDatabase();
            dt = new DeviceTable(db);
            pt = new PhoneTable(db);
            lt = new LogTable(db);
            tt = new TokenTable(db);
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
        lt.insertLog(log);
    }

    public ArrayList<Log> getAllLogs() {
        return lt.getAllLogs();
    }

    public void clearLogsTable() {
        lt.clearLogsTable();
    }

    public int insertNumber(String phoneNumber) {
        return pt.insertNumber(phoneNumber);
    }

    public boolean updateNumber(PhoneNumber number) {
        return pt.updateNumber(number);
    }

    public boolean updateNumber(int id, String phoneNumber) {
        return pt.updateNumber(id, phoneNumber);
    }

    public boolean deleteNumber(int id) {
        return pt.deleteNumber(id);
    }

    public PhoneNumber getPhoneNumber(int id) {
        return pt.getPhoneNumber(id);
    }

    public ArrayList<String> getAllNumbers() {
        return pt.getAllNumbers();
    }

    public ArrayList<String> getAllOnlyNumbers() {
        return pt.getAllOnlyNumbers();
    }

    public void clearPhoneTable() {
        pt.clearPhoneTable();
    }

    public void insertDevice(String name, boolean isActive) {
        dt.insertDevice(name, isActive);
    }

    public void updateDevice(Device device) {
        dt.updateDevice(device);
    }

    public void updateDevice(int id, String name, int ON_OFF) {
        dt.updateDevice(id, name, ON_OFF);
    }

    public Device getDevice(int id) {
        return dt.getDevice(id);
    }

    public boolean deleteDevice(int id) {
        return dt.deleteDevice(id);
    }

    public ArrayList<Device> getAllDevices() {
        return dt.getAllDevices();
    }

    public ArrayList<String> getAllDevicesToString() {
        return dt.getAllDevicesToString();
    }

    public void clearDeviceTable() {
        dt.clearDeviceTable();
    }

    public void insertToken(Token token) {
        tt.insertToken(token);
    }

    public boolean deleteToken(int id) {
        return tt.deleteToken(id);
    }

    public Token getToken(int id) {
        return tt.getToken(id);
    }

    public ArrayList<Token> getAllTokens() {
        return tt.getAllTokens();
    }

    public ArrayList<String> getAllTokensToString() {
        return tt.getAllTokensToString();
    }



}
