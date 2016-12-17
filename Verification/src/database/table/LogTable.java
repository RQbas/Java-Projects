package database.table;

import java.util.ArrayList;

import android.content.ContentValues;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import database.Log;

public class LogTable {

    SQLiteDatabase db;

    static final String KEY_ID = "_id";
    static final String ID_OPTIONS = "INTEGER PRIMARY KEY AUTOINCREMENT";
    static final int ID_COLUMN = 0;

    public static final String DB_LOGS_TABLE = "logs";

    static final String KEY_DATE = "date";
    static final String DATE_OPTIONS = "TEXT";
    static final int DATE_COLUMN = 1;

    static final String KEY_NAME = "name";
    static final String NAME_OPTIONS = "TEXT";
    static final int NAME_COLUMN = 2;

    static final String KEY_ACTIVE = "active";
    static final String ACTIVE_OPTIONS = "TEXT";
    static final int ACTIVE_COLUMN = 3;

    public static final String DB_CREATE_LOGS_TABLE =
            "CREATE TABLE " + DB_LOGS_TABLE + "( " + KEY_ID + " " + ID_OPTIONS + ", " + KEY_DATE + " " + DATE_OPTIONS
                    + ", " + KEY_NAME + " " + NAME_OPTIONS + ", " + KEY_ACTIVE + " " + ACTIVE_OPTIONS + ");";
    public static final String DROP_LOGS_TABLE = "DROP TABLE IF EXISTS " + DB_LOGS_TABLE;

    public LogTable(SQLiteDatabase db) {
        this.db = db;
    }

    public void insertLog(Log log) {
        ContentValues newLog = new ContentValues();
        newLog.put(KEY_DATE, log.getDate());
        newLog.put(KEY_NAME, log.getName());
        newLog.put(KEY_ACTIVE, log.getStatus());
        db.insert(DB_LOGS_TABLE, null, newLog);
    }



    public ArrayList<Log> getAllLogs() {
        ArrayList<Log> list = new ArrayList<Log>();
        String[] columns = {KEY_ID, KEY_DATE, KEY_NAME, KEY_ACTIVE};
        Cursor cursor = db.query(DB_LOGS_TABLE, columns, null, null, null, null, null);
        if (cursor.moveToFirst()) {
            do {
                Log log = new Log(cursor.getInt(ID_COLUMN), cursor.getString(DATE_COLUMN),
                        cursor.getString(NAME_COLUMN), cursor.getString(ACTIVE_COLUMN));
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


}
