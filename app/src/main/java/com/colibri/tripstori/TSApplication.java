package com.colibri.tripstori;

import android.app.Application;
import android.util.Log;

/**
 * Created by olivierbriand on 05/06/2015.
 */
public class TSApplication extends Application {

    private static final String TAG = "TSApplication";

    @Override
    public void onCreate() {
        super.onCreate();
        Log.i(TAG, "onCreate");    }
}
