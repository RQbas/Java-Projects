package database.table;

import android.content.ContentValues;
import android.database.sqlite.SQLiteDatabase;
import database.Token;

public class TokenTable {
    SQLiteDatabase db;

    public static final String DB_TOKEN_TABLE = "tokens";

    static final String KEY_ID = "_id";
    static final String ID_OPTIONS = "INTEGER PRIMARY KEY AUTOINCREMENT";
    static final int ID_COLUMN = 0;

    static final String KEY_USED = "used";
    static final String USED_OPTIONS = "INTEGER DEFAULT 0";
    static final int USED_COLUMN = 1;

    static final String KEY_TOKEN = "token";
    static final String TOKEN_OPTIONS = "TEXT";
    static final int TOKEN_COLUMN = 2;

    public static final String DB_CREATE_TOKEN_TABLE = "CREATE TABLE " + DB_TOKEN_TABLE + "( " + KEY_ID + " "
            + ID_OPTIONS + ", " + KEY_USED + " " + USED_OPTIONS + ", " + KEY_TOKEN + " " + TOKEN_OPTIONS + ");";
    public static final String DROP_TOKEN_TABLE = "DROP TABLE IF EXISTS " + DB_TOKEN_TABLE;

    public TokenTable(SQLiteDatabase db) {
        this.db = db;
    }

    public void insertToken(Token token) {
        ContentValues newLog = new ContentValues();

        newLog.put(KEY_ID, token.getId());
        newLog.put(KEY_USED, token.isUsed() ? 1 : 0);
        newLog.put(KEY_TOKEN, token.getToken());
        db.insert(DB_TOKEN_TABLE, null, newLog);
    }

}
