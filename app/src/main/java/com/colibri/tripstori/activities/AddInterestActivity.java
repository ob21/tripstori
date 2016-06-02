package com.colibri.tripstori.activities;

import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.view.MenuItem;
import android.view.View;
import android.widget.RadioButton;
import android.widget.RadioGroup;

import com.colibri.tripstori.R;
import com.colibri.tripstori.TSApp;
import com.colibri.tripstori.model.Interest;

/**
 * Created by OPOB7414 on 02/06/2016.
 */
public class AddInterestActivity extends TSActivity  {

    public final static String CHOICE = "choice";
    private static final String TAG = "AddInterestActivity";

    private int mChoice;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_addinterest);

        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        getSupportActionBar().setTitle(R.string.add_activity_title);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        mChoice = getIntent().getIntExtra(CHOICE, 0);
        TSApp.logDebug(TAG, "mChoice = "+mChoice);

        RadioGroup rg = (RadioGroup)findViewById(R.id.radio);
        switch(mChoice) {
            case 0:
                rg.check(R.id.radio_note);
                break;
            case 1:
                rg.check(R.id.radio_geo);
                break;
            case 2:
                rg.check(R.id.radio_image);
                break;
            case 3:
                rg.check(R.id.radio_web);
                break;
            default:
                rg.check(R.id.radio_note);
                break;
        }

    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            // Respond to the action bar's Up/Home button
            case android.R.id.home:
                onBackPressed();
        }
        return super.onOptionsItemSelected(item);
    }

    public void onRadioButtonClicked(View view) {
        // Is the button now checked?
        boolean checked = ((RadioButton) view).isChecked();

        // Check which radio button was clicked
        switch(view.getId()) {
            case R.id.radio_note:
                if (checked)
                    break;
            case R.id.radio_geo:
                if (checked)
                    break;
            case R.id.radio_image:
                if (checked)
                    break;
            case R.id.radio_web:
                if (checked)
                    break;
        }
    }
}
