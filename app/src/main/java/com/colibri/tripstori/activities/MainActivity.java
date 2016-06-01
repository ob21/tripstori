package com.colibri.tripstori.activities;

import android.content.ActivityNotFoundException;
import android.content.DialogInterface;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.v4.content.ContextCompat;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Toast;

import com.colibri.tripstori.factory.PDFFactory;
import com.colibri.tripstori.R;
import com.colibri.tripstori.TSApp;
import com.colibri.tripstori.adapters.InterestsListAdapter;
import com.colibri.tripstori.fragment.ConfirmDialogFragment;
import com.colibri.tripstori.manager.DataManager;
import com.colibri.tripstori.model.Interest;

import java.io.File;
import java.text.SimpleDateFormat;
import java.util.Date;


public class MainActivity extends TSActivity implements DialogInterface.OnClickListener, InterestsListAdapter.OnInterestClickListener, InterestsListAdapter.OnInterestLongClickListener {

    private static final String TAG = "MainActivity";
    private RecyclerView mInterestsList;
    private InterestsListAdapter mListAdapter;
    private RecyclerView.LayoutManager mLayoutManager;
    private Interest mInterestToDelete;
    private FloatingActionButton mAddButton;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

//        getSupportActionBar().setBackgroundDrawable(ContextCompat.getDrawable(this, R.drawable.panoramic_639860_960_720));

        mAddButton = (FloatingActionButton)findViewById(R.id.fab);

        mInterestsList = (RecyclerView)findViewById(R.id.interests_lv);

        mLayoutManager = new LinearLayoutManager(this);
        mInterestsList.setHasFixedSize(true);
        mInterestsList.setLayoutManager(mLayoutManager);

        mListAdapter = new InterestsListAdapter(this, getDataManager().getInterests(), this);
        mInterestsList.setAdapter(mListAdapter);
        mListAdapter.setOnLongItemClickListener(this);

        TSApp.logInfo(getClass().getName(), "onCreate");

        mAddButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast.makeText(MainActivity.this, "add interest", Toast.LENGTH_LONG).show();
            }
        });
    }

    @Override
    protected void onResume() {
        super.onResume();
        mListAdapter.notifyDataSetChanged();
        TSApp.logDebug(TAG, "onResume this="+this);
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
            getDataManager().createNoteInterest("title note "+currentDateandTime, Interest.Type.NOTE, "text description "+currentDateandTime);
            mListAdapter.setInterests(getDataManager().getInterests());
            mListAdapter.notifyDataSetChanged();
            return true;
        }
        if (id == R.id.action_add_geo) {
            getDataManager().createGeoInterest("title geo "+currentDateandTime, Interest.Type.GEO, 24.345, 47.456);
            mListAdapter.setInterests(getDataManager().getInterests());
            mListAdapter.notifyDataSetChanged();
            return true;
        }
        if (id == R.id.action_add_img) {
            getDataManager().createImageInterest("title img " + currentDateandTime, Interest.Type.IMAGE, InterestsListAdapter.IMAGE_URL, "image comment");
            mListAdapter.setInterests(getDataManager().getInterests());
            mListAdapter.notifyDataSetChanged();
            return true;
        }
        if (id == R.id.action_add_web) {
            getDataManager().createWebInterest("title web " + currentDateandTime, Interest.Type.WEB, "http://google.fr", "web comment");
            mListAdapter.setInterests(getDataManager().getInterests());
            mListAdapter.notifyDataSetChanged();
            return true;
        }
        if (id == R.id.action_delete_all) {
            getDataManager().deleteInterests();
            mListAdapter.setInterests(getDataManager().getInterests());
            mListAdapter.notifyDataSetChanged();
            return true;
        }
        if (id == R.id.action_pdf) {
            Toast.makeText(this, "make pdf", Toast.LENGTH_LONG).show();
            createPdf();
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

    private void createPdf() {
        File file = PDFFactory.getInstance().createPdf();
        openFile(file);
    }

    private void openFile(File file) {
        Uri path = Uri.fromFile(file);
        Intent pdfOpenintent = new Intent(Intent.ACTION_VIEW);
        pdfOpenintent.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
        pdfOpenintent.setDataAndType(path, "application/pdf");
        try {
            startActivity(pdfOpenintent);
        } catch (ActivityNotFoundException e) {

        }
    }

    @Override
    public void onClick(DialogInterface dialog, int which) {
        DataManager.getInstance().deleteInterest(mInterestToDelete);
        Toast.makeText(this, "delete : "+mInterestToDelete, Toast.LENGTH_SHORT).show();
        mListAdapter.setInterests(getDataManager().getInterests());
        mListAdapter.notifyDataSetChanged();
    }

    @Override
    public void onInterestClick(final View v, final Interest i) {
        runOnUiThread(new Runnable() {
            @Override
            public void run() {
                Toast.makeText(MainActivity.this, "onInterestClick "+i.getId(), Toast.LENGTH_LONG).show();
                TSApp.logDebug(TAG, "# onInterestClick "+i.getId());
            }
        });
    }

    @Override
    public void onInterestLongClick(final View v, final Interest i) {
        runOnUiThread(new Runnable() {
            @Override
            public void run() {
                TSApp.logDebug(TAG, "# onInterestLongClick "+i.getId());
                mInterestToDelete = i;
                new ConfirmDialogFragment().show(getSupportFragmentManager(), "DeleteDialog");
            }
        });
    }
}
