package com.colibri.tripstori.com.colibri.tripstori.utils;

import android.app.Activity;
import android.util.Log;

/**
 * Created by olivierbriand on 05/06/2015.
 */
public class TSLog {

    private boolean mDebug = false;

    public void setDebug(boolean d) {
        mDebug = d;
    }


    public void verb(String c, String m) {
        if(mDebug)
            Log.v(c, m);
    }

    public void debug(String c, String m) {
        if(mDebug)
            Log.d(c, m);
    }

    public void info(String c, String m) {
        if(mDebug)
            Log.i(c, m);
    }

    public void warn(String c, String m) {
        if(mDebug)
            Log.w(c, m);
    }

    public void error(String c, String m) {
        Log.e(c, m);
    }

}
