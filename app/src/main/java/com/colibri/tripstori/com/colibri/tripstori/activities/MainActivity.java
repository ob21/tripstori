package com.colibri.tripstori.com.colibri.tripstori.activities;

import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.ListView;

import com.colibri.tripstori.R;
import com.colibri.tripstori.com.colibri.tripstori.adapters.InterestsListAdapter;
import com.colibri.tripstori.com.colibri.tripstori.model.Interest;

import java.util.ArrayDeque;
import java.util.ArrayList;


public class MainActivity extends TSActivity {

    private ListView mInterestsList;
    private InterestsListAdapter mListAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        getInterests();

        mInterestsList = (ListView)findViewById(R.id.interests_lv);
        mListAdapter = new InterestsListAdapter(this, getInterests());
        mInterestsList.setAdapter(mListAdapter);

        logi(getClass(), "onCreate");
    }

    private ArrayList<Interest> getInterests() {
        ArrayList<Interest> interests = new ArrayList<>();
        interests.add(new Interest("0", "title0"));
        interests.add(new Interest("1", "title1"));
        interests.add(new Interest("2", "title2"));
        return interests;
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
