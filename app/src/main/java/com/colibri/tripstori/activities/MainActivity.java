package com.colibri.tripstori.activities;

import android.content.ActivityNotFoundException;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.os.Environment;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.ListView;
import android.widget.Toast;

import com.colibri.tripstori.R;
import com.colibri.tripstori.TSApp;
import com.colibri.tripstori.adapters.InterestsListAdapter;
import com.colibri.tripstori.model.Interest;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Date;

import crl.android.pdfwriter.PDFWriter;
import crl.android.pdfwriter.PaperSize;
import crl.android.pdfwriter.StandardFonts;


public class MainActivity extends TSActivity {

    private static final String TAG = "MainActivity";
    private ListView mInterestsList;
    private InterestsListAdapter mListAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        mInterestsList = (ListView)findViewById(R.id.interests_lv);
        mListAdapter = new InterestsListAdapter(this, getDataManager().getInterests());
        mInterestsList.setAdapter(mListAdapter);

        TSApp.logInfo(getClass().getName(), "onCreate");
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
            getDataSource().deleteAllInterests();
            mListAdapter.setInterests(getDataSource().getAllInterests());
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
        PDFWriter mPDFWriter = new PDFWriter(PaperSize.A4_WIDTH, PaperSize.A4_HEIGHT); // 595 x 842
        mPDFWriter.setFont(StandardFonts.SUBTYPE, StandardFonts.HELVETICA);
        mPDFWriter.addText(40, 800, 18, "pdf for tripstori"); // place here the right location for text
        mPDFWriter.setFont(StandardFonts.TIMES_BOLD, StandardFonts.HELVETICA_BOLD);
        mPDFWriter.addText(80, 750, 18, "pdf for tripstori 2");
        mPDFWriter.newPage();
        mPDFWriter.setFont(StandardFonts.COURIER, StandardFonts.COURIER_OBLIQUE);
        mPDFWriter.addText(40, 700, 30, "pdf for tripstori 3");
        mPDFWriter.addLine(40, 650, 550, 650);
        mPDFWriter.addRectangle(40, 200, 400, 200); //fromLeft, frombottom, largeur, hauteur
        //mPDFWriter.addImage();
        File file = createFile("helloworld.pdf", mPDFWriter.asString(), "ISO-8859-1");
        openFile(file);

    }

    private File createFile(String fileName, String pdfContent, String encoding) {
        File newFile = new File(Environment.getExternalStorageDirectory() + "/download/" + fileName);
        try {
            newFile.createNewFile();
            try {
                FileOutputStream pdfFile = new FileOutputStream(newFile);
                pdfFile.write(pdfContent.getBytes(encoding));
                pdfFile.close();
            } catch(FileNotFoundException e) {
                //
            }
        } catch(IOException e) {
            //
        }
        return newFile;

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
}
