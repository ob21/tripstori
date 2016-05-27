package com.colibri.tripstori.database;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.SQLException;
import android.database.sqlite.SQLiteDatabase;
import android.util.Log;

import com.colibri.tripstori.model.Interest;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by olivierbriand on 10/06/2015.
 *
 * Use : http://www.vogella.com/tutorials/AndroidSQLite/article.html
 */

public class InterestsDataSource {

    private static final String TAG = "InterestsDataSource";
    // Database fields
    private SQLiteDatabase database;
    private TSDbHelper dbHelper;
    private String[] allColumns = { TSDbHelper.COLUMN_ID,
            TSDbHelper.COLUMN_TITLE, TSDbHelper.COLUMN_TYPE, TSDbHelper.COLUMN_IMGURL, TSDbHelper.COLUMN_WEBURL,
            TSDbHelper.COLUMN_LONGITUDE, TSDbHelper.COLUMN_LATITUDE,
            TSDbHelper.COLUMN_TEXT};

    public InterestsDataSource(Context context) {
        dbHelper = new TSDbHelper(context);
    }

    public void open() throws SQLException {
        database = dbHelper.getWritableDatabase();
    }

    public void close() {
        dbHelper.close();
    }

    public Interest createGeoInterest(String title, Interest.Type type, double longitude, double latitude) {
        ContentValues values = new ContentValues();
        values.put(TSDbHelper.COLUMN_TITLE, title);
        values.put(TSDbHelper.COLUMN_TYPE, type.getValue());
        values.put(TSDbHelper.COLUMN_IMGURL, "");
        values.put(TSDbHelper.COLUMN_WEBURL, "");
        values.put(TSDbHelper.COLUMN_LONGITUDE, longitude);
        values.put(TSDbHelper.COLUMN_LATITUDE, latitude);
        values.put(TSDbHelper.COLUMN_TEXT, "");
        long insertId = database.insert(TSDbHelper.TABLE_INTERESTS, null,
                values);
        Cursor cursor = database.query(TSDbHelper.TABLE_INTERESTS,
                allColumns, TSDbHelper.COLUMN_ID + " = " + insertId, null,
                null, null, null);
        cursor.moveToFirst();
        Interest newInterest= cursorToInterest(cursor);
        cursor.close();
        return newInterest;
    }

    public Interest updateGeoInterest(String title, Interest.Type type, double longitude, double latitude) {
        ContentValues values = new ContentValues();
        values.put(TSDbHelper.COLUMN_TITLE, title);
        values.put(TSDbHelper.COLUMN_TYPE, type.getValue());
        values.put(TSDbHelper.COLUMN_IMGURL, "");
        values.put(TSDbHelper.COLUMN_WEBURL, "");
        values.put(TSDbHelper.COLUMN_LONGITUDE, longitude);
        values.put(TSDbHelper.COLUMN_LATITUDE, latitude);
        values.put(TSDbHelper.COLUMN_TEXT, "");
        long replaceId = database.replace(TSDbHelper.TABLE_INTERESTS, null,
                values);
        Cursor cursor = database.query(TSDbHelper.TABLE_INTERESTS,
                allColumns, TSDbHelper.COLUMN_ID + " = " + replaceId, null,
                null, null, null);
        cursor.moveToFirst();
        Interest updatedInterest= cursorToInterest(cursor);
        cursor.close();
        return updatedInterest;
    }

    public Interest createNoteInterest(String title, Interest.Type type, String text) {
        ContentValues values = new ContentValues();
        values.put(TSDbHelper.COLUMN_TITLE, title);
        values.put(TSDbHelper.COLUMN_TYPE, type.getValue());
        values.put(TSDbHelper.COLUMN_IMGURL, "");
        values.put(TSDbHelper.COLUMN_WEBURL, "");
        values.put(TSDbHelper.COLUMN_LONGITUDE, 0);
        values.put(TSDbHelper.COLUMN_LATITUDE, 0);
        values.put(TSDbHelper.COLUMN_TEXT, text);
        long insertId = database.insert(TSDbHelper.TABLE_INTERESTS, null,
                values);
        Cursor cursor = database.query(TSDbHelper.TABLE_INTERESTS,
                allColumns, TSDbHelper.COLUMN_ID + " = " + insertId, null,
                null, null, null);
        cursor.moveToFirst();
        Interest newInterest= cursorToInterest(cursor);
        cursor.close();
        return newInterest;
    }

    public Interest createImageInterest(String title, Interest.Type type, String imgUrl, String text) {
        ContentValues values = new ContentValues();
        values.put(TSDbHelper.COLUMN_TITLE, title);
        values.put(TSDbHelper.COLUMN_TYPE, type.getValue());
        values.put(TSDbHelper.COLUMN_IMGURL, imgUrl);
        values.put(TSDbHelper.COLUMN_WEBURL, "");
        values.put(TSDbHelper.COLUMN_LONGITUDE, 0);
        values.put(TSDbHelper.COLUMN_LATITUDE, 0);
        values.put(TSDbHelper.COLUMN_TEXT, text);
        long insertId = database.insert(TSDbHelper.TABLE_INTERESTS, null,
                values);
        Cursor cursor = database.query(TSDbHelper.TABLE_INTERESTS,
                allColumns, TSDbHelper.COLUMN_ID + " = " + insertId, null,
                null, null, null);
        cursor.moveToFirst();
        Interest newInterest= cursorToInterest(cursor);
        cursor.close();
        return newInterest;
    }

    public Interest createWebInterest(String title, Interest.Type type, String webUrl, String text) {
        ContentValues values = new ContentValues();
        values.put(TSDbHelper.COLUMN_TITLE, title);
        values.put(TSDbHelper.COLUMN_TYPE, type.getValue());
        values.put(TSDbHelper.COLUMN_IMGURL, "");
        values.put(TSDbHelper.COLUMN_WEBURL, webUrl);
        values.put(TSDbHelper.COLUMN_LONGITUDE, 0);
        values.put(TSDbHelper.COLUMN_LATITUDE, 0);
        values.put(TSDbHelper.COLUMN_TEXT, text);
        long insertId = database.insert(TSDbHelper.TABLE_INTERESTS, null,
                values);
        Cursor cursor = database.query(TSDbHelper.TABLE_INTERESTS,
                allColumns, TSDbHelper.COLUMN_ID + " = " + insertId, null,
                null, null, null);
        cursor.moveToFirst();
        Interest newInterest= cursorToInterest(cursor);
        cursor.close();
        return newInterest;
    }

    public void deleteInterest(Interest interest) {
        long id = interest.getId();
        System.out.println("Interest deleted with id: " + id);
        database.delete(TSDbHelper.TABLE_INTERESTS, TSDbHelper.COLUMN_ID
                + " = " + id, null);
    }

    public void deleteAllInterests() {
        System.out.println("All interests deleted");
        database.delete(TSDbHelper.TABLE_INTERESTS, null, null);
    }

    public ArrayList<Interest> getAllInterests() {
        ArrayList<Interest> interests = new ArrayList<Interest>();

        Cursor cursor = database.query(TSDbHelper.TABLE_INTERESTS,
                allColumns, null, null, null, null, null);

        cursor.moveToFirst();
        while (!cursor.isAfterLast()) {
            Interest interest = cursorToInterest(cursor);
            interests.add(interest);
            cursor.moveToNext();
        }
        // make sure to close the cursor
        cursor.close();
        return interests;
    }

    private Interest cursorToInterest(Cursor cursor) {
        Log.i(TAG, "cursor count = "+cursor.getColumnCount());
        Interest interest = new Interest(cursor.getLong(0),
                cursor.getString(1),
                Interest.typeFromInt(cursor.getInt(2)),
                cursor.getString(3),
                cursor.getString(4),
                cursor.getDouble(5),
                cursor.getDouble(6),
                cursor.getString(7));
        return interest;
    }
}


