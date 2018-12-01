package com.example.recytechco.util;

import android.content.Context;
import android.content.SharedPreferences;

import com.example.recytechco.models.Element;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;

public class Config {

    public static final String DATABASE_NAME = "recytechco-db";

    public static final String TABLE_USER = "user";
    public static final String COLUMN_USER_ID = "user_id";
    public static final String COLUMN_FULL_NAME = "user_full_name";
    public static final String COLUMN_USERNAME = "user_username";
    public static final String COLUMN_PASSWORD = "user_password";
    public static final String COLUMN_POINTS = "user_points";

    public static final String TABLE_HISTORY = "history";
    public static final String COLUMN_HISTORY_ID = "history_id";
    public static final String COLUMN_ELEMENT_ID = "element_id";
    public static final String COLUMN_ELEMENT_NAME = "element_name";
    public static final String COLUMN_AMOUNT = "amount";
    public static final String COLUMN_HISTORY_DATE = "history_date";

    public static final String TAG = Config.class.getSimpleName();
    public static final String FULL_NAME_PREF = "full_name_preference";
    public static final String USER_ID_PREF = "user_id_preference";

    public static String getCurrentDateInString() {
        SimpleDateFormat format = new SimpleDateFormat("MMM dd, yyyy");
        return format.format(new Date());
    }

    public static ArrayList<Element> getElements() {
        ArrayList<Element> elements = new ArrayList<>();
        elements.add(new Element(1, "Mouse", 1));
        elements.add(new Element(2, "Keyboard", 2));
        elements.add(new Element(3, "Speakers", 4));
        elements.add(new Element(4, "Mother board", 5));
        elements.add(new Element(5, "Printer", 8));
        elements.add(new Element(6, "Monitor", 10));

        return elements;
    }

    public static String getFullName(Context context) {
        SharedPreferences pref = context.getSharedPreferences(TAG, Context.MODE_PRIVATE);
        return pref.getString(FULL_NAME_PREF, "");
    }

    public static void setFullName(Context context, String fullName) {
        context.getSharedPreferences(TAG, Context.MODE_PRIVATE).edit()
                .putString(FULL_NAME_PREF, fullName).commit();
    }

    public static int getUserId(Context context) {
        SharedPreferences pref = context.getSharedPreferences(TAG, Context.MODE_PRIVATE);
        return pref.getInt(USER_ID_PREF, -1);
    }

    public static void setUserId(Context context, int userId) {
        context.getSharedPreferences(TAG, Context.MODE_PRIVATE).edit()
                .putInt(USER_ID_PREF, userId).commit();
    }
}
