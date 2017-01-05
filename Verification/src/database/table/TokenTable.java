package database.table;

import java.util.ArrayList;

import android.content.ContentValues;
import android.database.Cursor;
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

    public boolean deleteToken(int id) {
        String where = KEY_ID + "=" + id;
        return db.delete(DB_TOKEN_TABLE, where, null) > 0;
    }

    public Token getToken(int id) {
        String[] columns = {KEY_ID, KEY_USED, KEY_TOKEN};
        String where = KEY_ID + "=" + id;
        Cursor cursor = db.query(DB_TOKEN_TABLE, columns, where, null, null, null, null);
        Token token = null;
        if (cursor != null && cursor.moveToFirst()) {
            String tokenCode = cursor.getString(TOKEN_COLUMN);
            boolean isUsed = (cursor.getInt(USED_COLUMN) == 1) ? true : false;
            token = new Token(id, isUsed, tokenCode);
        }
        return token;
    }

    public ArrayList<Token> getAllTokens() {
        ArrayList<Token> list = new ArrayList<Token>();
        String[] columns = {KEY_ID, KEY_USED, KEY_TOKEN};
        Cursor cursor = db.query(DB_TOKEN_TABLE, columns, null, null, null, null, null);
        if (cursor.moveToFirst()) {
            do {
                boolean isUsed = (cursor.getInt(USED_COLUMN) == 1) ? true : false;
                Token token = new Token(cursor.getInt(ID_COLUMN), isUsed, cursor.getString(TOKEN_COLUMN));
                list.add(token);
            } while (cursor.moveToNext());
        }
        if (cursor != null && !cursor.isClosed()) {
            cursor.close();
        }

        return list;
    }

    public ArrayList<String> getAllTokensToString() {
        ArrayList<String> list = new ArrayList<String>();
        String[] columns = {KEY_ID, KEY_USED, KEY_TOKEN};
        Cursor cursor = db.query(DB_TOKEN_TABLE, columns, null, null, null, null, null);
        if (cursor.moveToFirst()) {
            do {
                boolean isUsed = (cursor.getInt(USED_COLUMN) == 1) ? true : false;
                Token token = new Token(cursor.getInt(ID_COLUMN), isUsed, cursor.getString(TOKEN_COLUMN));
                list.add(token.toString());
            } while (cursor.moveToNext());
        }
        if (cursor != null && !cursor.isClosed()) {
            cursor.close();
        }

        return list;
    }



}
