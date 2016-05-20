package com.colibri.tripstori;

import android.app.Application;
import android.util.Log;

/**
 * Created by olivierbriand on 05/06/2015.
 */
public class TSApp extends Application {

    private static final String TAG = "TSApp";
    private static final String APP = "TS";

    private static boolean mDebug = true;

    public TSApp() {
    }

    @Override
    public void onCreate() {
        super.onCreate();
        logInfo(TAG, "onCreate");    }


    public static void logVerbose(String t, String m) {
        if(mDebug)
            Log.v(APP + " " + t, m);
    }

    public static void logDebug(String t, String m) {
        if(mDebug)
            Log.d(APP + " " + t, m);
    }

    public static void logInfo(String t, String m) {
        if(mDebug)
            Log.i(APP + " " + t, m);
    }

    public static void logWarn(String t, String m) {
        if(mDebug)
            Log.w(APP + " " + t, m);
    }

    public static void logError(String t, String m) {
        Log.e(APP + " " + t, m);
    }
}
