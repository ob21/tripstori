package com.colibri.tripstori;

import android.app.ActivityManager;
import android.app.Application;
import android.content.Context;
//import android.support.multidex.MultiDex;
import android.util.Log;

import com.colibri.tripstori.manager.DataManager;
import com.colibri.tripstori.utils.VolleyManager;

/**
 * Created by olivierbriand on 05/06/2015.
 */
public class TSApp extends Application {

    private static final String TAG = "TSApp";
    private static final String APP = "#ts";

    private static boolean mDebug = true;

    @Override
    protected void attachBaseContext(Context base) {
        super.attachBaseContext(base);
//        MultiDex.install(this);
    }

    @Override
    public void onCreate() {
        super.onCreate();
        logDebug(TAG, "onCreate");
        DataManager.getInstance().init(getApplicationContext());

        ActivityManager am = (ActivityManager) getSystemService(ACTIVITY_SERVICE);
        int heapSize = am.getMemoryClass();
        VolleyManager.init(this, (heapSize * 1024 * 1024 / 8));
        TSApp.logDebug(TAG, "init Volley");
    }


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
