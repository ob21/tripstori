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


    public void verb(Class c, String m) {
        if(!mDebug)
            Log.v(c.getName(), m);
    }

    public void info(Class c, String m) {
        if(!mDebug)
            Log.i(c.getName(), m);
    }

    public void warn(Class c, String m) {
        if(!mDebug)
            Log.w(c.getName(), m);
    }

    public void error(Class c, String m) {
        Log.e(c.getName(), m);
    }

}
