package com.colibri.tripstori.activities;

import android.content.ActivityNotFoundException;
import android.content.DialogInterface;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
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
import com.colibri.tripstori.fragment.ChoiceDialogFragment;
import com.colibri.tripstori.fragment.ConfirmDialogFragment;
import com.colibri.tripstori.fragment.TSDialogFragment;
import com.colibri.tripstori.manager.DataManager;
import com.colibri.tripstori.model.Interest;

import java.io.File;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;


public class MainActivity extends TSActivity implements DialogInterface.OnClickListener, InterestsListAdapter.OnInterestClickListener, InterestsListAdapter.OnInterestLongClickListener {

    private static final String TAG = "MainActivity";
    private RecyclerView mInterestsList;
    private InterestsListAdapter mListAdapter;
    private RecyclerView.LayoutManager mLayoutManager;
    private Interest mInterestToDelete;
    private FloatingActionButton mAddButton;
    private TSDialogFragment mCurrentDialog;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

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
                ArrayList<String> choices = new ArrayList<String>();
                choices.add("Note");
                choices.add("Lieu");
                choices.add("Image");
                choices.add("Page web");
                ArrayList<Integer> images = new ArrayList<Integer>();
                images.add(R.drawable.note_icon);
                images.add(R.drawable.geo_icon);
                images.add(R.drawable.image_icon);
                images.add(R.drawable.web_icon);
                mCurrentDialog = new ChoiceDialogFragment();
                Bundle bundle = new Bundle();
                bundle.putStringArrayList(ChoiceDialogFragment.STRING_CHOICES, choices);
                bundle.putIntegerArrayList(ChoiceDialogFragment.IMAGES_CHOICES, images);
                bundle.putInt(ChoiceDialogFragment.SELECTED, 0);
                bundle.putString(ChoiceDialogFragment.TITLE, "Ajouter un intérêt");
                mCurrentDialog.setArguments(bundle);
                mCurrentDialog.show(getSupportFragmentManager(), "ChoiceDialog");
            }
        });
    }

    @Override
    protected void onResume() {
        super.onResume();
        mListAdapter.setInterests(getDataManager().getInterests());
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
            getDataManager().createNoteInterest("title note "+currentDateandTime, Interest.NOTE, "text description "+currentDateandTime);
            mListAdapter.setInterests(getDataManager().getInterests());
            mListAdapter.notifyDataSetChanged();
            return true;
        }
        if (id == R.id.action_add_geo) {
            getDataManager().createGeoInterest("title geo "+currentDateandTime, Interest.GEO, "default location", 24.345, 47.456);
            mListAdapter.setInterests(getDataManager().getInterests());
            mListAdapter.notifyDataSetChanged();
            return true;
        }
        if (id == R.id.action_add_img) {
            getDataManager().createImageInterest("title img " + currentDateandTime, Interest.IMAGE, InterestsListAdapter.IMAGE_URL, "image comment");
            mListAdapter.setInterests(getDataManager().getInterests());
            mListAdapter.notifyDataSetChanged();
            return true;
        }
        if (id == R.id.action_add_web) {
            getDataManager().createWebInterest("title web " + currentDateandTime, Interest.WEB, "http://google.fr", "web comment");
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
        switch(mCurrentDialog.getType()) {
            case TSDialogFragment.CONFIRM :
                if(mInterestToDelete != null) {
                    DataManager.getInstance().deleteInterest(mInterestToDelete);
                    Toast.makeText(this, "delete : "+mInterestToDelete, Toast.LENGTH_SHORT).show();
                    mListAdapter.setInterests(getDataManager().getInterests());
                    mListAdapter.notifyDataSetChanged();
                    mInterestToDelete = null;
                }
                break;
            case TSDialogFragment.CHOICE:
                TSApp.logDebug(TAG, "onclick dialog choice which = "+which);
                Intent intent = new Intent(MainActivity.this, AddInterestActivity.class);
                intent.putExtra(AddInterestActivity.CHOICE, which);
                startActivity(intent);
                dialog.dismiss();
                break;
        }
        mCurrentDialog = null;

    }

    @Override
    public void onInterestClick(final View v, final Interest i) {
        runOnUiThread(new Runnable() {
            @Override
            public void run() {
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
                mCurrentDialog = new ConfirmDialogFragment();
                Bundle bundle = new Bundle();
                bundle.putString(ConfirmDialogFragment.TITLE, "Effacer cet intérêt");
                bundle.putString(ConfirmDialogFragment.MESSAGE, "Êtes-vous sûr ?");
                mCurrentDialog.setArguments(bundle);
                mCurrentDialog.show(getSupportFragmentManager(), "DeleteDialog");
            }
        });
    }
}
