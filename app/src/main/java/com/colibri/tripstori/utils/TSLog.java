package com.colibri.tripstori.utils;

import android.app.Activity;
import android.util.Log;

/**
 * Created by olivierbriand on 05/06/2015.
 */
public class TSLog {

    private static boolean mDebug = false;


    public static void verb(String t, String m) {
        if(mDebug)
            Log.v(t, m);
    }

    public static void debug(String t, String m) {
        if(mDebug)
            Log.d(t, m);
    }

    public static void info(String t, String m) {
        if(mDebug)
            Log.i(t, m);
    }

    public static void warn(String t, String m) {
        if(mDebug)
            Log.w(t, m);
    }

    public static void error(String t, String m) {
        Log.e(t, m);
    }

}
