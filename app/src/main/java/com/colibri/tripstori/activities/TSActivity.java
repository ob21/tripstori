package com.colibri.tripstori.activities;

import android.app.ActivityManager;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.ActionBarActivity;
import android.support.v7.app.AppCompatActivity;

import com.colibri.tripstori.TSApp;
import com.colibri.tripstori.database.InterestsDataSource;
import com.colibri.tripstori.manager.DataManager;
import com.colibri.tripstori.utils.VolleyManager;

/**
 * Created by olivierbriand on 08/06/2015.
 */
public class TSActivity  extends AppCompatActivity {

    private static final String TAG = "TSActivity";
    private InterestsDataSource mDataSource;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        mDataSource = DataManager.getInstance().getDataSource();

        ActivityManager am = (ActivityManager) getSystemService(ACTIVITY_SERVICE);
        int heapSize = am.getMemoryClass();
        VolleyManager.init(this, (heapSize * 1024 * 1024 / 8));
        TSApp.logDebug(TAG, "init Volley");
    }

    protected InterestsDataSource getDataSource(){
        return mDataSource;
    }

    protected DataManager getDataManager(){
        return DataManager.getInstance();
    }

}
