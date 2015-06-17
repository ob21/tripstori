package com.colibri.tripstori.activities;

import android.app.ActivityManager;
import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.ImageView;
import android.widget.ListView;

import com.android.volley.RequestQueue;
import com.android.volley.toolbox.ImageLoader;
import com.colibri.tripstori.R;
import com.colibri.tripstori.adapters.InterestsListAdapter;
import com.colibri.tripstori.database.InterestsDataSource;
import com.colibri.tripstori.model.Interest;
import com.colibri.tripstori.utils.VolleyManager;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class MainActivity extends TSActivity {

    private ListView mInterestsList;
    private InterestsListAdapter mListAdapter;

    private InterestsDataSource mDatasource;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        ActivityManager am = (ActivityManager) getSystemService(ACTIVITY_SERVICE);
        int heapSize = am.getMemoryClass();
        VolleyManager.init(this, (heapSize * 1024 * 1024 / 8));

        mDatasource = new InterestsDataSource(this);
        mDatasource.open();

        mInterestsList = (ListView)findViewById(R.id.interests_lv);
        mListAdapter = new InterestsListAdapter(this, mDatasource.getAllInterests());
        mInterestsList.setAdapter(mListAdapter);

        logi(getClass(), "onCreate");
    }

    @Override
    protected void onResume() {
        super.onResume();
        mListAdapter.notifyDataSetChanged();
    }


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_add_note) {
            mDatasource.createInterest("title "+new int[1], Interest.Type.NOTE);
            mListAdapter.setInterests(mDatasource.getAllInterests());
            mListAdapter.notifyDataSetChanged();
            return true;
        }
        if (id == R.id.action_add_geo) {
            mDatasource.createInterest("title "+new int[1], Interest.Type.GEO);
            mListAdapter.setInterests(mDatasource.getAllInterests());
            mListAdapter.notifyDataSetChanged();
            return true;
        }
        if (id == R.id.action_delete_all) {
            mDatasource.deleteAllInterests();
            mListAdapter.setInterests(mDatasource.getAllInterests());
            mListAdapter.notifyDataSetChanged();
            return true;
        }
        if (id == R.id.action_settings) {
            return true;
        }
        if (id == R.id.action_about) {
            startActivity(new Intent(MainActivity.this, AboutActivity.class));
            return true;
        }

        return super.onOptionsItemSelected(item);
    }
}
