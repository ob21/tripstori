package com.colibri.tripstori.database;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;

/**
 * Created by olivierbriand on 10/06/2015.
 */

public class TSDbHelper extends SQLiteOpenHelper {

    public static final String TABLE_INTERESTS = "interests";
    public static final String COLUMN_ID = "id_interest";
    public static final String COLUMN_TITLE = "title_interest";
    public static final String COLUMN_TYPE = "type_interest";
    public static final String COLUMN_IMGURL = "imgurl_interest";
    public static final String COLUMN_WEBURL = "weburl_interest";
    public static final String COLUMN_LOCATION = "location_interest";
    public static final String COLUMN_LONGITUDE = "longitude_interest";
    public static final String COLUMN_LATITUDE = "latitude_interest";
    public static final String COLUMN_TEXT = "text_interest";

    private static final String DATABASE_NAME = "interests.db";
    private static final int DATABASE_VERSION = 1;

    // Database creation sql statement
    private static final String DATABASE_CREATE = "create table "
            + TABLE_INTERESTS + "("
            + COLUMN_ID + " integer primary key autoincrement, "
            + COLUMN_TITLE + " text not null,"
            + COLUMN_TYPE + " integer,"
            + COLUMN_IMGURL + " text,"
            + COLUMN_WEBURL + " text,"
            + COLUMN_LOCATION + " text,"
            + COLUMN_LONGITUDE + " double,"
            + COLUMN_LATITUDE + " double,"
            + COLUMN_TEXT + " text"
            + ");";

    public TSDbHelper(Context context) {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase database) {
        database.execSQL(DATABASE_CREATE);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        Log.w(TSDbHelper.class.getName(),
                "Upgrading database from version " + oldVersion + " to "
                        + newVersion + ", which will destroy all old data");
        db.execSQL("DROP TABLE IF EXISTS " + TABLE_INTERESTS);
        onCreate(db);
    }

}