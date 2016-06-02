package com.colibri.tripstori.activities;

import android.app.DatePickerDialog;
import android.app.TimePickerDialog;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.view.MenuItem;
import android.view.View;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.TextView;
import android.widget.TimePicker;

import com.colibri.tripstori.R;
import com.colibri.tripstori.TSApp;
import com.colibri.tripstori.model.Interest;

import java.util.Calendar;

/**
 * Created by OPOB7414 on 02/06/2016.
 */
public class AddInterestActivity extends TSActivity implements View.OnClickListener {

    public final static String CHOICE = "choice";
    private static final String TAG = "AddInterestActivity";

    private int mChoice;
    private TextView mEditDate;
    private TextView mEditTime;
    private int mYear;
    private int mMonth;
    private int mDay;
    private int mHour;
    private int mMinute;

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

        mEditDate = (TextView) findViewById(R.id.textview_date);
        mEditDate.setOnClickListener(this);
        mEditTime = (TextView) findViewById(R.id.textview_time);
        mEditTime.setOnClickListener(this);

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


    }
}
