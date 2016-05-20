package com.colibri.tripstori.activities;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.ActionBarActivity;
import android.support.v7.app.AppCompatActivity;

import com.colibri.tripstori.TSApp;
import com.colibri.tripstori.database.InterestsDataSource;
import com.colibri.tripstori.manager.DataManager;

/**
 * Created by olivierbriand on 08/06/2015.
 */
public class TSActivity  extends AppCompatActivity {

    private InterestsDataSource mDataSource;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        mDataSource = DataManager.getInstance().getDataSource();
    }

    protected InterestsDataSource getDataSource(){
        return mDataSource;
    }
}
