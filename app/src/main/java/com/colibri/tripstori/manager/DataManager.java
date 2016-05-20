package com.colibri.tripstori.manager;

import android.content.Context;

import com.colibri.tripstori.TSApp;
import com.colibri.tripstori.database.InterestsDataSource;

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

}
