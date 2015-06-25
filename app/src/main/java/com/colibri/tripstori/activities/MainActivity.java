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

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
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

        SimpleDateFormat sdf = new SimpleDateFormat("yyyyMMdd_HHmmss");
        String currentDateandTime = sdf.format(new Date());

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_add_note) {
            mDatasource.createNoteInterest("title note "+currentDateandTime, Interest.Type.NOTE, "text description "+currentDateandTime);
            mListAdapter.setInterests(mDatasource.getAllInterests());
            mListAdapter.notifyDataSetChanged();
            return true;
        }
        if (id == R.id.action_add_geo) {
            mDatasource.createGeoInterest("title geo "+currentDateandTime, Interest.Type.GEO, 24.345, 47.456);
            mListAdapter.setInterests(mDatasource.getAllInterests());
            mListAdapter.notifyDataSetChanged();
            return true;
        }
        if (id == R.id.action_add_img) {
            mDatasource.createImageInterest("title img "+currentDateandTime, Interest.Type.IMAGE, InterestsListAdapter.IMAGE_URL, "image comment");
            mListAdapter.setInterests(mDatasource.getAllInterests());
            mListAdapter.notifyDataSetChanged();
            return true;
        }
        if (id == R.id.action_add_web) {
            mDatasource.createWebInterest("title web "+currentDateandTime, Interest.Type.WEB, "http://google.fr", "web comment");
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
