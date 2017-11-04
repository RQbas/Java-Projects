package pl.kubas.rafal.database;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteDatabase.CursorFactory;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;

import static pl.kubas.rafal.database.DatabaseAdapter.DB_VERSION;
import static pl.kubas.rafal.database.DatabaseAdapter.DEBUG_TAG;
import static pl.kubas.rafal.database.table.DeviceTable.DB_CREATE_DEVICE_TABLE;
import static pl.kubas.rafal.database.table.DeviceTable.DB_DEVICE_TABLE;
import static pl.kubas.rafal.database.table.DeviceTable.DROP_DEVICE_TABLE;
import static pl.kubas.rafal.database.table.LogTable.DB_CREATE_LOGS_TABLE;
import static pl.kubas.rafal.database.table.LogTable.DB_LOGS_TABLE;
import static pl.kubas.rafal.database.table.LogTable.DROP_LOGS_TABLE;
import static pl.kubas.rafal.database.table.PhoneTable.DB_CREATE_PHONE_TABLE;
import static pl.kubas.rafal.database.table.PhoneTable.DB_PHONE_TABLE;
import static pl.kubas.rafal.database.table.PhoneTable.DROP_PHONE_TABLE;
import static pl.kubas.rafal.database.table.TokenTable.DB_CREATE_TOKEN_TABLE;
import static pl.kubas.rafal.database.table.TokenTable.DB_TOKEN_TABLE;
import static pl.kubas.rafal.database.table.TokenTable.DROP_TOKEN_TABLE;

public class DatabaseHelper extends SQLiteOpenHelper {


    public DatabaseHelper(Context context, String name, CursorFactory factory, int version) {
        super(context, name, factory, version);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL(DB_CREATE_PHONE_TABLE);
        db.execSQL(DB_CREATE_DEVICE_TABLE);
        db.execSQL(DB_CREATE_LOGS_TABLE);
        db.execSQL(DB_CREATE_TOKEN_TABLE);


        Log.d(DEBUG_TAG, "Database creating...");
        Log.d(DEBUG_TAG, "Table " + DB_PHONE_TABLE + " ver." + DB_VERSION + " created");
        Log.d(DEBUG_TAG, "Table " + DB_DEVICE_TABLE + " ver." + DB_VERSION + " created");
        Log.d(DEBUG_TAG, "Table " + DB_LOGS_TABLE + " ver." + DB_VERSION + " created");
        Log.d(DEBUG_TAG, "Table " + DB_TOKEN_TABLE + " ver." + DB_VERSION + " created");
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        db.execSQL(DROP_PHONE_TABLE);
        db.execSQL(DROP_DEVICE_TABLE);
        db.execSQL(DROP_LOGS_TABLE);
        db.execSQL(DROP_TOKEN_TABLE);


        Log.d(DEBUG_TAG, "Database updating...");
        Log.d(DEBUG_TAG, "Table " + DB_PHONE_TABLE + " updated from ver." + oldVersion + " to ver." + newVersion);
        Log.d(DEBUG_TAG, "Table " + DB_DEVICE_TABLE + " updated from ver." + oldVersion + " to ver." + newVersion);
        Log.d(DEBUG_TAG, "Table " + DB_LOGS_TABLE + " updated from ver." + oldVersion + " to ver." + newVersion);
        Log.d(DEBUG_TAG, "Table " + DB_TOKEN_TABLE + " updated from ver." + oldVersion + " to ver." + newVersion);
        Log.d(DEBUG_TAG, "All data is lost.");

        onCreate(db);
    }


}

