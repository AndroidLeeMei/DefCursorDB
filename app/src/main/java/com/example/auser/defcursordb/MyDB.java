package com.example.auser.defcursordb;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.SQLException;
import android.database.sqlite.SQLiteDatabase;
/**
 * Created by auser on 2017/11/22.
 */

// 建立自己資料庫的類別
public class MyDB {
    public SQLiteDatabase db = null;
    private final static String DATABASE_NAME = "db1.db";
    private final static String TABLE_NAME = "table01";
    private final static String _ID = "_id";
    private final static String NAME = "name";
    private final static String PRICE = "price";
    // create databse
    private final static String CREATE_TABLE = "CREATE TABLE " + TABLE_NAME + " (" + _ID + "INTEGER PRIMARY KEY," + NAME + " TEXT," + PRICE + " INTEGER)";
    private Context mCtx = null;

    public MyDB(Context ctx) {
        this.mCtx = ctx;
    }

    public void open() throws SQLException {
        db = mCtx.openOrCreateDatabase(DATABASE_NAME, 0, null);
        try {
            db.execSQL(CREATE_TABLE);
        } catch (Exception e) {
        }
    }

    public Cursor getAll() {
        return db.query(TABLE_NAME, new String[]{_ID, NAME, PRICE}, null, null, null, null, null, null);
    }

    public Cursor get(long rowId) throws SQLException {
        Cursor mCursor = db.query(TABLE_NAME, new String[]{_ID, NAME, PRICE}, _ID + "=" + rowId, null, null, null, null, null);
        if (mCursor != null) {
            mCursor.moveToFirst();
        }
        return mCursor;
    }

    public long append(String name, int price) {
        ContentValues args = new ContentValues();
        args.put(NAME, name);
        args.put(PRICE, price);
        return db.insert(TABLE_NAME, null, args);

    }

    public boolean delete(long rowId) {
        return db.delete(TABLE_NAME, _ID + "=" + rowId, null) > 0;
    }

    public boolean update(long rowId, String name, int price) {
        ContentValues args = new ContentValues();
        args.put(NAME, name);
        args.put(PRICE, price);
        return db.update(TABLE_NAME, args, _ID + "=" + rowId, null) > 0;
    }

}
