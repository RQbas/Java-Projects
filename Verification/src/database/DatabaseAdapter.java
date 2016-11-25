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
    static final String DB_PHONE_TABLE = "phoneNumber";

    static final String KEY_ID = "_id";
    static final String ID_OPTIONS = "INTEGER PRIMARY KEY AUTOINCREMENT";
    static final int ID_COLUMN = 0;

    static final String KEY_PHONE = "phone";
    static final String PHONE_OPTIONS = "INTEGER DEFAULT 0";
    static final int PHONE_COLUMN = 1;

    static final String DB_CREATE_PHONE_TABLE = "CREATE TABLE " + DB_PHONE_TABLE + "( " + KEY_ID + " " + ID_OPTIONS
            + ", " + KEY_PHONE + " " + PHONE_OPTIONS + ");";
    static final String DROP_PHONE_TABLE = "DROP TABLE IF EXISTS " + DB_PHONE_TABLE;

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
