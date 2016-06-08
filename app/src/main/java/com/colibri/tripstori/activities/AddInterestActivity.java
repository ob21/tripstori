package com.colibri.tripstori.activities;

import android.app.DatePickerDialog;
import android.app.TimePickerDialog;
import android.content.Context;
import android.content.Intent;
import android.database.Cursor;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.net.Uri;
import android.os.Bundle;
import android.provider.MediaStore;
import android.support.v7.widget.Toolbar;
import android.view.MenuItem;
import android.view.MotionEvent;
import android.view.View;
import android.view.inputmethod.InputMethodManager;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.TimePicker;
import android.widget.Toast;

import com.colibri.tripstori.R;
import com.colibri.tripstori.TSApp;
import com.colibri.tripstori.fragment.EditDialogFragment;
import com.colibri.tripstori.manager.DataManager;
import com.colibri.tripstori.model.Interest;
import com.google.android.gms.common.GooglePlayServicesNotAvailableException;
import com.google.android.gms.common.GooglePlayServicesRepairableException;
import com.google.android.gms.location.places.Place;
import com.google.android.gms.location.places.ui.PlacePicker;

import java.text.DecimalFormat;
import java.util.Calendar;

/**
 * Created by OPOB7414 on 02/06/2016.
 */
public class AddInterestActivity extends TSActivity implements View.OnClickListener, EditDialogFragment.EditDialogListener {

    public final static String CHOICE = "choice";
    private static final String TAG = "AddInterestActivity";
    private static final int PLACE_PICKER_REQUEST = 1;
    private static final int REQUEST_IMAGE_CAPTURE = 2;
    private static final int REQUEST_IMAGE_PICK = 3;

    private static final int NOTE = 0;
    private static final int GEO = 1;
    private static final int IMAGE = 2;
    private static final int WEB = 3;

    private int mChoice;
    private TextView mEditDate;
    private TextView mEditTime;
    private int mYear;
    private int mMonth;
    private int mDay;
    private int mHour;
    private int mMinute;
    private double mLongitude;
    private double mLatitude;
    private TextView mLocation;
    private TextView mImageUrl;
    private TextView mWebUrl;
    private ImageView mImageView;

    private EditText mEditTitle;
    private EditText mEditDescription;
    private String mInterestLat;
    private String mInterestLong;
    private String mInterestImageUrl;
    private String mInterestWebUrl;
    private Button mSaveButton;

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

        mEditTitle = (EditText)findViewById(R.id.edittext_title);
        mEditDescription = (EditText)findViewById(R.id.edittext_text);

        mLocation = (TextView) findViewById(R.id.textview_location);
        mLocation.setVisibility(View.GONE);
        mImageUrl = (TextView) findViewById(R.id.textview_image_url);
        mImageUrl.setVisibility(View.GONE);
        mWebUrl = (TextView) findViewById(R.id.textview_web_url);
        mWebUrl.setVisibility(View.GONE);

        mImageView = (ImageView)findViewById(R.id.imageview);

        switch (mChoice) {
            case NOTE:
                getSupportActionBar().setTitle(getString(R.string.add_activity_title_with_type, Interest.typeFromInt(Interest.NOTE).toLowerCase()));
                break;
            case GEO:
                getSupportActionBar().setTitle(getString(R.string.add_activity_title_with_type, Interest.typeFromInt(Interest.GEO).toLowerCase()));
                mLocation.setVisibility(View.VISIBLE);
                break;
            case IMAGE:
                getSupportActionBar().setTitle(getString(R.string.add_activity_title_with_type, Interest.typeFromInt(Interest.IMAGE).toLowerCase()));
                mImageUrl.setVisibility(View.VISIBLE);
                break;
            case WEB:
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
        mMonth = c.get(Calendar.MONTH) + 1;
        mDay = c.get(Calendar.DAY_OF_MONTH);
        mHour = c.get(Calendar.HOUR_OF_DAY);
        mMinute = c.get(Calendar.MINUTE);

        mEditDate.setText(convert2Digits(mDay) + "-" + (convert2Digits(mMonth)) + "-" + mYear);
        mEditTime.setText(convert2Digits(mHour) + ":" + convert2Digits(mMinute));

        mSaveButton = (Button)findViewById(R.id.save_bt);
        mSaveButton.setOnClickListener(this);

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
    public boolean onTouchEvent(MotionEvent event) {
        InputMethodManager imm = (InputMethodManager)getSystemService(Context.INPUT_METHOD_SERVICE);
        imm.hideSoftInputFromWindow(getCurrentFocus().getWindowToken(), 0);
        return false;
    }

    @Override
    public void onClick(View v) {

        if (v != null) {
            InputMethodManager imm = (InputMethodManager)getSystemService(Context.INPUT_METHOD_SERVICE);
            imm.hideSoftInputFromWindow(v.getWindowToken(), 0);
        }

        if (v == mEditDate) {
            DatePickerDialog datePickerDialog = new DatePickerDialog(this,
                    new DatePickerDialog.OnDateSetListener() {

                        @Override
                        public void onDateSet(DatePicker view, int year,
                                              int monthOfYear, int dayOfMonth) {

                            mEditDate.setText(dayOfMonth + "-" + (monthOfYear + 1) + "-" + year);
                            mYear = year;
                            mMonth = monthOfYear + 1;
                            mDay = dayOfMonth;

                        }
                    }, mYear, mMonth - 1, mDay);
//            String date = mDay + "." + mMonth + "." + mYear + "_" + mHour + "h" +  mMinute;
//            TSApp.logDebug(TAG, "date = "+date);
            datePickerDialog.show();

        }

        if (v == mEditTime) {
            // Launch Time Picker Dialog
            TimePickerDialog timePickerDialog = new TimePickerDialog(this,
                    new TimePickerDialog.OnTimeSetListener() {

                        @Override
                        public void onTimeSet(TimePicker view, int hourOfDay,
                                              int minute) {

                            mEditTime.setText(hourOfDay + ":" + minute);
                            mHour = hourOfDay;
                            mMinute = minute;
                        }
                    }, mHour, mMinute, true);
//            String date = mDay + "." + mMonth + "." + mYear + "_" + mHour + "h" +  mMinute;
//            TSApp.logDebug(TAG, "date = "+date);
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
            TSApp.logDebug(TAG, "mImageUrl");

            Intent takePictureIntent = new Intent(MediaStore.ACTION_IMAGE_CAPTURE);
            if (takePictureIntent.resolveActivity(getPackageManager()) != null) {
                startActivityForResult(takePictureIntent, REQUEST_IMAGE_CAPTURE);
            }
//            Intent i = new Intent(Intent.ACTION_PICK,
//                    android.provider.MediaStore.Images.Media.EXTERNAL_CONTENT_URI);
//            startActivityForResult(i, REQUEST_IMAGE_PICK);
        }

        if (v == mWebUrl) {
            TSApp.logDebug(TAG, "choose web url");
            EditDialogFragment dialog = new EditDialogFragment();
            Bundle bundle = new Bundle();
            bundle.putString(EditDialogFragment.TITLE, "Ajoutez une adresse web");
            dialog.setArguments(bundle);
            dialog.show(getSupportFragmentManager(), "EditDialog");
        }

        if (v == mSaveButton) {
            TSApp.logDebug(TAG, "save button");
            String date = convert2Digits(mDay) + "." + convert2Digits(mMonth) + "." + mYear + "_" + convert2Digits(mHour) + "h" +  convert2Digits(mMinute);
            TSApp.logDebug(TAG, "date = "+date);
            switch(mChoice) {
                case NOTE:
                    if(!mEditTitle.getText().toString().isEmpty() && !mEditDescription.getText().toString().isEmpty()) {
                        DataManager.getInstance().createNoteInterest(mEditTitle.getText().toString(), Interest.NOTE, mEditDescription.getText().toString() + " " + date);
                        finish();
                    } else {
                        fieldsWarning();
                    }
                    break;
                case GEO:
                    if(!mEditTitle.getText().toString().isEmpty() && mLongitude!=0 && mLatitude!=0) {
                        DataManager.getInstance().createGeoInterest(mEditTitle.getText().toString() + " " + date, Interest.GEO, mLongitude, mLatitude);
                        finish();
                    } else {
                        fieldsWarning();
                    }
                    break;
                case IMAGE:
                    if(!mEditTitle.getText().toString().isEmpty() && !mInterestImageUrl.isEmpty() && !mEditDescription.getText().toString().isEmpty()) {
                        DataManager.getInstance().createImageInterest(mEditTitle.getText().toString(), Interest.IMAGE, mInterestImageUrl, mEditDescription.getText().toString() + date);
                        finish();
                    } else {
                        fieldsWarning();
                    }
                    break;
                case WEB:
                    if(!mEditTitle.getText().toString().isEmpty() && !mInterestWebUrl.isEmpty() && !mEditDescription.getText().toString().isEmpty()) {
                        DataManager.getInstance().createWebInterest(mEditTitle.getText().toString(), Interest.WEB, mInterestWebUrl, mEditDescription.getText().toString() + date);
                        finish();
                    } else {
                        fieldsWarning();
                    }
                    break;
            }
        }

    }

    private void fieldsWarning() {
        Toast.makeText(this, "Remplissez les champs pour créer un intérêt", Toast.LENGTH_LONG).show();
    }

    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        if (requestCode == PLACE_PICKER_REQUEST) {
            if (resultCode == RESULT_OK) {
                Place place = PlacePicker.getPlace(this, data);
                String toastMsg = String.format("Place: %s", place.getName());
                Toast.makeText(this, toastMsg, Toast.LENGTH_LONG).show();
                mLocation.setText(place.getName() + " " + place.getLatLng());
                mLongitude = place.getLatLng().longitude;
                mLatitude = place.getLatLng().latitude;
            }
        }
        if (requestCode == REQUEST_IMAGE_CAPTURE && resultCode == RESULT_OK) {
            Bundle extras = data.getExtras();
            Bitmap imageBitmap = (Bitmap) extras.get("data");
            mInterestImageUrl = "link_to_file_captured";
            mImageView.setImageBitmap(imageBitmap);
        }
        if(requestCode == REQUEST_IMAGE_PICK && resultCode == RESULT_OK && data != null) {
            TSApp.logDebug(TAG, "got REQUEST_IMAGE_PICK result");
            Uri selectedImage = data.getData();
            String[] filePathColumn = { MediaStore.Images.Media.DATA };

            Cursor cursor = getContentResolver().query(selectedImage,
                    filePathColumn, null, null, null);
            cursor.moveToFirst();

            int columnIndex = cursor.getColumnIndex(filePathColumn[0]);
            String picturePath = cursor.getString(columnIndex);
            cursor.close();

            Bitmap bitmap = BitmapFactory.decodeFile(picturePath);
            mImageView.setImageBitmap(bitmap);
        }

    }

    @Override
    public void onFinishEditDialog(String inputText) {
        TSApp.logDebug(TAG, "onFinishEditDialog : " + inputText);
        mInterestWebUrl = inputText;
    }

    private String convert2Digits(int i) {
        return new DecimalFormat("00").format(i);
    }
}
