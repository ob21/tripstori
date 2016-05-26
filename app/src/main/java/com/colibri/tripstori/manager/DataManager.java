package com.colibri.tripstori.manager;

import android.content.Context;

import com.colibri.tripstori.TSApp;
import com.colibri.tripstori.database.InterestsDataSource;
import com.colibri.tripstori.model.Interest;

import java.util.ArrayList;

/**
 * Created by Olivier Briand on 20/05/2016.
 */
public class DataManager {

    private final static String TAG = "DataManager";
    private static DataManager mInstance;
    private Context mContext;
    private InterestsDataSource mDataSource;

    public static DataManager getInstance() {
        if(mInstance == null) {
            mInstance = new DataManager();
        }
        return mInstance;
    }

    public void init(Context c) {
        TSApp.logDebug(TAG, "init context : "+c);
        mContext = c;

        mDataSource = new InterestsDataSource(mContext);
        mDataSource.open();
        TSApp.logDebug(TAG, "init create data source : "+mDataSource);
    }

    public InterestsDataSource getDataSource() {
        TSApp.logDebug(TAG, "getDataSource : "+mDataSource);
        return mDataSource;
    }

    public ArrayList<Interest> getInterests() {
        TSApp.logDebug(TAG, "getInterests : "+mDataSource.getAllInterests());
        return mDataSource.getAllInterests();
    }

    public void createGeoInterest(String title, Interest.Type type, double longitude, double latitude) {
        TSApp.logDebug(TAG, "createGeoInterest");
        mDataSource.createGeoInterest(title, type, longitude, latitude);
    }

    public void createNoteInterest(String title, Interest.Type type, String text) {
        TSApp.logDebug(TAG, "createNoteInterest");
        mDataSource.createNoteInterest(title, type, text);
    }

    public void createWebInterest(String title, Interest.Type type, String webUrl, String text) {
        TSApp.logDebug(TAG, "createWebInterest");
        mDataSource.createWebInterest(title, type, webUrl, text);
    }

    public void createImageInterest(String title, Interest.Type type, String imgUrl, String text) {
        TSApp.logDebug(TAG, "createImageInterest");
        mDataSource.createImageInterest(title, type, imgUrl, text);
    }


}
