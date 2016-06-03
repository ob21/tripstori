package com.colibri.tripstori.activities;

import android.app.DatePickerDialog;
import android.app.TimePickerDialog;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.view.MenuItem;
import android.view.View;
import android.widget.DatePicker;
import android.widget.TextView;
import android.widget.TimePicker;
import android.widget.Toast;

import com.colibri.tripstori.R;
import com.colibri.tripstori.TSApp;
import com.colibri.tripstori.model.Interest;
import com.google.android.gms.common.GooglePlayServicesNotAvailableException;
import com.google.android.gms.common.GooglePlayServicesRepairableException;
import com.google.android.gms.location.places.Place;
import com.google.android.gms.location.places.ui.PlacePicker;

import java.util.Calendar;

/**
 * Created by OPOB7414 on 02/06/2016.
 */
public class AddInterestActivity extends TSActivity implements View.OnClickListener {

    public final static String CHOICE = "choice";
    private static final String TAG = "AddInterestActivity";
    private static final int PLACE_PICKER_REQUEST = 1;

    private int mChoice;
    private TextView mEditDate;
    private TextView mEditTime;
    private int mYear;
    private int mMonth;
    private int mDay;
    private int mHour;
    private int mMinute;
    private TextView mLocation;
    private TextView mImageUrl;
    private TextView mWebUrl;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_addinterest);

        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        getSupportActionBar().setTitle(R.string.add_activity_title);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        mChoice = getIntent().getIntExtra(CHOICE, 0);
        TSApp.logDebug(TAG, "mChoice = " + mChoice);

        mLocation = (TextView) findViewById(R.id.textview_location);
        mLocation.setVisibility(View.GONE);
        mImageUrl = (TextView) findViewById(R.id.textview_image_url);
        mImageUrl.setVisibility(View.GONE);
        mWebUrl = (TextView) findViewById(R.id.textview_web_url);
        mWebUrl.setVisibility(View.GONE);

        switch (mChoice) {
            case 0:
                getSupportActionBar().setTitle(getString(R.string.add_activity_title_with_type, Interest.typeFromInt(Interest.NOTE).toLowerCase()));
                break;
            case 1:
                getSupportActionBar().setTitle(getString(R.string.add_activity_title_with_type, Interest.typeFromInt(Interest.GEO).toLowerCase()));
                mLocation.setVisibility(View.VISIBLE);
                break;
            case 2:
                getSupportActionBar().setTitle(getString(R.string.add_activity_title_with_type, Interest.typeFromInt(Interest.IMAGE).toLowerCase()));
                mImageUrl.setVisibility(View.VISIBLE);
                break;
            case 3:
                getSupportActionBar().setTitle(getString(R.string.add_activity_title_with_type, Interest.typeFromInt(Interest.WEB).toLowerCase()));
                mWebUrl.setVisibility(View.VISIBLE);
                break;
            default:
                getSupportActionBar().setTitle(getString(R.string.add_activity_title_with_type, Interest.typeFromInt(Interest.NOTE).toLowerCase()));
                break;
        }

        mEditDate = (TextView) findViewById(R.id.textview_date);
        mEditDate.setOnClickListener(this);
        mEditTime = (TextView) findViewById(R.id.textview_time);
        mEditTime.setOnClickListener(this);

        mLocation.setOnClickListener(this);
        mImageUrl.setOnClickListener(this);
        mWebUrl.setOnClickListener(this);

        // Get Current Date
        final Calendar c = Calendar.getInstance();
        mYear = c.get(Calendar.YEAR);
        mMonth = c.get(Calendar.MONTH);
        mDay = c.get(Calendar.DAY_OF_MONTH);
        mHour = c.get(Calendar.HOUR_OF_DAY);
        mMinute = c.get(Calendar.MINUTE);

        mEditDate.setText(mDay + "-" + (mMonth + 1) + "-" + mYear);
        mEditTime.setText(mHour + ":" + mMinute);

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

    @Override
    public void onClick(View v) {

        if (v == mEditDate) {

            // Get Current Date
            final Calendar c = Calendar.getInstance();
            mYear = c.get(Calendar.YEAR);
            mMonth = c.get(Calendar.MONTH);
            mDay = c.get(Calendar.DAY_OF_MONTH);


            DatePickerDialog datePickerDialog = new DatePickerDialog(this,
                    new DatePickerDialog.OnDateSetListener() {

                        @Override
                        public void onDateSet(DatePicker view, int year,
                                              int monthOfYear, int dayOfMonth) {

                            mEditDate.setText(dayOfMonth + "-" + (monthOfYear + 1) + "-" + year);

                        }
                    }, mYear, mMonth, mDay);
            datePickerDialog.show();

        }

        if (v == mEditTime) {

            // Get Current Time
            final Calendar c = Calendar.getInstance();
            mHour = c.get(Calendar.HOUR_OF_DAY);
            mMinute = c.get(Calendar.MINUTE);

            // Launch Time Picker Dialog
            TimePickerDialog timePickerDialog = new TimePickerDialog(this,
                    new TimePickerDialog.OnTimeSetListener() {

                        @Override
                        public void onTimeSet(TimePicker view, int hourOfDay,
                                              int minute) {

                            mEditTime.setText(hourOfDay + ":" + minute);
                        }
                    }, mHour, mMinute, false);
            timePickerDialog.show();
        }

        if (v == mLocation) {
            PlacePicker.IntentBuilder builder = new PlacePicker.IntentBuilder();
            try {
                startActivityForResult(builder.build(AddInterestActivity.this), PLACE_PICKER_REQUEST);
            } catch (GooglePlayServicesRepairableException e) {
                TSApp.logError(TAG, ""+e);
            } catch (GooglePlayServicesNotAvailableException e) {
                TSApp.logError(TAG, ""+e);
            }
        }

        if (v == mImageUrl) {

        }

        if (v == mWebUrl) {

        }


    }

    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        if (requestCode == PLACE_PICKER_REQUEST) {
            if (resultCode == RESULT_OK) {
                Place place = PlacePicker.getPlace(this, data);
                String toastMsg = String.format("Place: %s", place.getName());
                Toast.makeText(this, toastMsg, Toast.LENGTH_LONG).show();
                mLocation.setText(place.getName());
            }
        }
    }

}
