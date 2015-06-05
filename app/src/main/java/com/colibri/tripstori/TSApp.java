package com.colibri.tripstori;

import android.app.Application;
import android.util.Log;

import com.colibri.tripstori.com.colibri.tripstori.utils.TSLog;

/**
 * Created by olivierbriand on 05/06/2015.
 */
public class TSApp extends Application {

    private static final String TAG = "TSApp";

    private TSLog mLogger;

    public TSApp() {
        mLogger = new TSLog();
        mLogger.setDebug(true);
    }

    public TSLog getLog() {
        return mLogger;
    }

    @Override
    public void onCreate() {
        super.onCreate();
        Log.i(TAG, "onCreate");    }
}
