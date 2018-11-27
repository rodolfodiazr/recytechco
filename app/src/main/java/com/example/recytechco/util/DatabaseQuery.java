package com.example.recytechco.util;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteException;
import android.util.Log;

import com.example.recytechco.helpers.DatabaseHelper;
import com.example.recytechco.models.History;
import com.example.recytechco.models.User;

import java.util.ArrayList;


public class DatabaseQuery {

    private static final String tag = DatabaseQuery.class.getSimpleName();

    private Context mContext;
    private DatabaseHelper mDatabaseHelper;
    private SQLiteDatabase mSqLiteDatabase;

    public interface DatabaseQueryListener {
        void onSuccess();
    }

    public DatabaseQuery(Context context) {
        mContext = context;
        mDatabaseHelper = DatabaseHelper.getInstance(context);
    }

    public User getUserBy(String username) {
        User user = null;
        Cursor cursor = null;
        mSqLiteDatabase = mDatabaseHelper.getReadableDatabase();
        try {
            cursor = mSqLiteDatabase.query(Config.TABLE_USER, null,
                    Config.COLUMN_USERNAME + " = ? ", new String[]{username},
                    null, null, null);

            if (cursor != null && cursor.moveToFirst()) {
                String fullName = cursor.getString(cursor.getColumnIndex(Config.COLUMN_FULL_NAME));
                String password = cursor.getString(cursor.getColumnIndex(Config.COLUMN_PASSWORD));
                int points = cursor.getInt(cursor.getColumnIndex(Config.COLUMN_POINTS));

                user = new User(username, fullName, password, points);
            }
        } catch (SQLiteException e) {
            Log.d(tag, e.getMessage());
        } finally {
            if (cursor != null) {
                cursor.close();
            }
            mSqLiteDatabase.close();
        }

        return user;
    }

    public User getUserBy(String username, String password) {
        User user = null;
        Cursor cursor = null;
        mSqLiteDatabase = mDatabaseHelper.getReadableDatabase();
        try {
            cursor = mSqLiteDatabase.query(Config.TABLE_USER, null,
                    Config.COLUMN_USERNAME + " = ? and " + Config.COLUMN_PASSWORD + " = ? ",
                    new String[]{username, password}, null, null, null);

            if (cursor != null && cursor.moveToFirst()) {
                String fullName = cursor.getString(cursor.getColumnIndex(Config.COLUMN_FULL_NAME));
                int points = cursor.getInt(cursor.getColumnIndex(Config.COLUMN_POINTS));

                user = new User(username, fullName, password, points);
            }
        } catch (SQLiteException e) {
            Log.d(tag, e.getMessage());
        } finally {
            if (cursor != null) {
                cursor.close();
            }
            mSqLiteDatabase.close();
        }

        return user;
    }


    public ArrayList<History> getHistories() {
        mSqLiteDatabase = mDatabaseHelper.getReadableDatabase();

        Cursor cursor = null;
        try {

            cursor = mSqLiteDatabase.query(Config.TABLE_HISTORY, null, null,
                    null, null, null, null, null);

            if (cursor != null) {
                if (cursor.moveToFirst()) {
                    ArrayList<History> histories = new ArrayList<>();
                    do {
                        int elementId = cursor.getInt(cursor.getColumnIndex(Config.COLUMN_ELEMENT_ID));
                        String elementName = cursor.getString(cursor.getColumnIndex(Config.COLUMN_ELEMENT_NAME));
                        int amount = cursor.getInt(cursor.getColumnIndex(Config.COLUMN_AMOUNT));
                        String date = cursor.getString(cursor.getColumnIndex(Config.COLUMN_HISTORY_DATE));

                        histories.add(new History(elementId, elementName, amount, "", date));
                    } while (cursor.moveToNext());

                    return histories;
                }
            }
        } catch (Exception e) {
            Log.d(tag, e.getMessage());
        } finally {
            if (cursor != null) {
                cursor.close();
            }
            mSqLiteDatabase.close();
        }

        return new ArrayList<>();
    }

    public void add(User user, DatabaseQueryListener listener) {
        mSqLiteDatabase = mDatabaseHelper.getReadableDatabase();

        ContentValues contentValues = new ContentValues();
        contentValues.put(Config.COLUMN_USERNAME, user.getUsername());
        contentValues.put(Config.COLUMN_FULL_NAME, user.getFullName());
        contentValues.put(Config.COLUMN_PASSWORD, user.getPassword());
        try {
            mSqLiteDatabase.insertOrThrow(Config.TABLE_USER, null, contentValues);
            listener.onSuccess();
        } catch (SQLiteException e) {
            Log.d("DatabaseQuery", e.getMessage());
        } finally {
            mSqLiteDatabase.close();
        }
    }
}