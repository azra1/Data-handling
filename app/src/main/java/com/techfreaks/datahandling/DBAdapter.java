package com.techfreaks.datahandling;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.SQLException;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;

/**
 * Created by acer on 8/10/2017.
 */

public class DBAdapter {
    static final String KEY_ROWID = "_id";
    static final String KEY_NAME = "name";
    static final String KEY_EMAIL = "email";
    //static final String TAG = "com.techfreaks.datahandling.DBAdapter";
    static final String DATABASE_NAME = "mydata";
    static final String DATABASE_TABLE = "contacts";
    static final int DATABASE_VerSION = 1;
    static final String DATABASE_CREATE = "create table contacts(_id integer primary key autoincrement,name text not null, email text not null);";
    final Context context;
    DatabaseHelper DBHelper;
    SQLiteDatabase db;

    public DBAdapter(Context cxt) {
        this.context = cxt;
        DBHelper = new DatabaseHelper(context);
    }

    private static class DatabaseHelper extends SQLiteOpenHelper {

        public DatabaseHelper(Context context) {
            super(context, DATABASE_NAME, null, DATABASE_VerSION);
        }

        @Override
        public void onCreate(SQLiteDatabase db) {
            try {
                db.execSQL(DATABASE_CREATE);
            } catch (Exception e) {
                e.printStackTrace();
            }
        }

        @Override
        public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
           // Log.w(TAG, "UPGRADING DATABASE VERSION FROM " + oldVersion + " to " + newVersion + " which will destroy all data ");
            db.execSQL("Delete table if exists contacts");
             onCreate(db);

        }
    }

    public DBAdapter open() throws SQLException {
        db = DBHelper.getWritableDatabase();
        return this;
    }

    public void close() {
        DBHelper.close();
    }

    public long insertContact(String name, String email) {
        ContentValues v = new ContentValues();
        v.put(KEY_NAME, name);
        v.put(KEY_EMAIL, email);
        return db.insert(DATABASE_TABLE, null, v);
    }

    public boolean deleteContact(long rowId) {
        return db.delete(DATABASE_TABLE, KEY_ROWID + "=" + rowId, null) > 0;
    }

    public Cursor getAllContacts() {
        return db.query(DATABASE_TABLE, new String[]{KEY_ROWID, KEY_NAME, KEY_EMAIL}, null, null, null, null, null);
    }

    public Cursor getContact(long rowId) {
        Cursor myCursor = db.query(true, DATABASE_TABLE, new String[]{KEY_ROWID, KEY_NAME, KEY_EMAIL}, KEY_ROWID + "=" + rowId, null, null, null, null, null);
        if (myCursor != null) {
            myCursor.moveToFirst();
        }
        return myCursor;
    }

    public boolean upDateContact(long rowId, String name, String email) {
        ContentValues values = new ContentValues();
        values.put(KEY_NAME, name);
        values.put(KEY_EMAIL, email);
        return db.update(DATABASE_TABLE, values, KEY_ROWID + "=" + rowId, null) > 0;
    }
}



