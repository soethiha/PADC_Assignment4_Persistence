package com.soethiha.padc_assignment4.data.persistence;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import com.soethiha.padc_assignment4.data.persistence.RestaurantContract.RestaurantEntry;
import com.soethiha.padc_assignment4.data.persistence.RestaurantContract.RestaurantTagEntry;

/**
 * PADC_Assignment4
 *
 * @author SoeThihaTun
 * @version 1.0
 * @since 2017/07/05
 */

public class RestaurantDBHelper extends SQLiteOpenHelper {

    public static final String DATABASE_NAME = "restaurants.db";
    private static final int DATABASE_VERSION = 1;

    // SQL Query Constants

    private static final String SQL_CREATE_RESTAURANT_TABLE = "CREATE TABLE " + RestaurantEntry.TABLE_NAME + " (" +
            RestaurantEntry._ID + " INTEGER PRIMARY KEY AUTOINCREMENT, " +
            RestaurantEntry.COLUMN_TITLE + " TEXT NOT NULL, " +
            RestaurantEntry.COLUMN_ADDR_SHORT + " TEXT, " +
            RestaurantEntry.COLUMN_IMAGE + " TEXT, " +
            RestaurantEntry.COLUMN_TOTAL_RATING_COUNT + " INTEGER NOT NULL, " +
            RestaurantEntry.COLUMN_AVG_RATING_VALUE + " INTEGER NOT NULL, " +
            RestaurantEntry.COLUMN_IS_AD + " INTEGER DEFAULT 0, " +
            RestaurantEntry.COLUMN_IS_NEW + " INTEGER DEFAULT 0, " +
            RestaurantEntry.COLUMN_LEAD_TIME_IN_MIN + " TEXT, " +

            " UNIQUE (" + RestaurantEntry.COLUMN_TITLE + ") ON CONFLICT IGNORE" +
            " );";

    private static final String SQL_CREATE_RESTAURANT_TAG_TABLE = "CREATE TABLE " + RestaurantTagEntry.TABLE_NAME + " (" +
            RestaurantTagEntry._ID + " INTEGER PRIMARY KEY AUTOINCREMENT, " +
            RestaurantTagEntry.COLUMN_RESTAURANT_TITLE + " TEXT NOT NULL, " +
            RestaurantTagEntry.COLUMN_TAG + " TEXT, " +

            " UNIQUE (" + RestaurantTagEntry.COLUMN_RESTAURANT_TITLE + ", " +
            RestaurantTagEntry.COLUMN_TAG + ") ON CONFLICT IGNORE" +
            " );";

    public RestaurantDBHelper(Context context) {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL(SQL_CREATE_RESTAURANT_TABLE);
        db.execSQL(SQL_CREATE_RESTAURANT_TAG_TABLE);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        // Drop the tables first
        db.execSQL("DROP TABLE IF EXISTS " + RestaurantTagEntry.TABLE_NAME);
        db.execSQL("DROP TABLE IF EXISTS " + RestaurantEntry.TABLE_NAME);

        // Recreate the tables
        onCreate(db);
    }
}