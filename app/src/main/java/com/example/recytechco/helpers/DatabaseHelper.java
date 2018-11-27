package com.example.recytechco.helpers;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;

import com.example.recytechco.util.Config;

public class DatabaseHelper extends SQLiteOpenHelper {

    private static final String tag = DatabaseHelper.class.getSimpleName();
    private static final int DATABASE_VERSION = 1;
    private static final String DATABASE_NAME = Config.DATABASE_NAME;

    private static DatabaseHelper mDatabaseHelper;

    private DatabaseHelper(Context context) {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);

    }

    public static DatabaseHelper getInstance(Context context) {
        if (mDatabaseHelper == null) {
            synchronized (DatabaseHelper.class) {
                if (mDatabaseHelper == null)
                    mDatabaseHelper = new DatabaseHelper(context);
            }
        }
        return mDatabaseHelper;
    }

    @Override
    public void onCreate(SQLiteDatabase db) {

        // Create tables SQL execution
        String CREATE_USER_TABLE = "CREATE TABLE " + Config.TABLE_USER + "("
                + Config.COLUMN_USER_ID + " INTEGER PRIMARY KEY AUTOINCREMENT, "
                + Config.COLUMN_FULL_NAME + " TEXT NOT NULL, "
                + Config.COLUMN_USERNAME + " TEXT NOT NULL, "
                + Config.COLUMN_PASSWORD + " TEXT NOT NULL, "
                + Config.COLUMN_POINTS + " INTEGER "
                + ")";

        String CREATE_HISTORY_TABLE = "CREATE TABLE " + Config.TABLE_HISTORY + "("
                + Config.COLUMN_HISTORY_ID + " INTEGER PRIMARY KEY AUTOINCREMENT, "
                + Config.COLUMN_ELEMENT_ID + " INTEGER NOT NULL, "
                + Config.COLUMN_USER_ID + " INTEGER NOT NULL, "
                + Config.COLUMN_ELEMENT_NAME + " TEXT NOT NULL, "
                + Config.COLUMN_AMOUNT + " INTEGER NOT NULL, " +
                " FOREIGN KEY (" + Config.COLUMN_USER_ID + ") REFERENCES " + Config.TABLE_USER + "(" + Config.COLUMN_USER_ID + ") ON UPDATE CASCADE ON DELETE CASCADE "
                + ")";

        db.execSQL(CREATE_USER_TABLE);
        db.execSQL(CREATE_HISTORY_TABLE);

        Log.d(tag, "DB created!");
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int i, int i1) {
        // Drop older table if existed
        db.execSQL("DROP TABLE IF EXISTS " + Config.TABLE_USER);

        // Create tables
        onCreate(db);
    }

    @Override
    public void onOpen(SQLiteDatabase db) {
        super.onOpen(db);

        // Enable foreign key constraints like ON UPDATE CASCADE, ON DELETE CASCADE
        db.execSQL("PRAGMA foreign_keys=ON;");
    }
}
