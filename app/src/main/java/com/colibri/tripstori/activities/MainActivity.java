package com.colibri.tripstori.activities;

import android.app.ActivityManager;
import android.content.ActivityNotFoundException;
import android.content.Intent;
import android.graphics.Rect;
import android.graphics.pdf.PdfDocument;
import android.net.Uri;
import android.os.Bundle;
import android.os.Environment;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.Toast;

import com.android.volley.RequestQueue;
import com.android.volley.toolbox.ImageLoader;
import com.colibri.tripstori.R;
import com.colibri.tripstori.adapters.InterestsListAdapter;
import com.colibri.tripstori.database.InterestsDataSource;
import com.colibri.tripstori.model.Interest;
import com.colibri.tripstori.utils.TSLog;
import com.colibri.tripstori.utils.VolleyManager;
import com.itextpdf.text.Document;
import com.itextpdf.text.DocumentException;
import com.itextpdf.text.Paragraph;
import com.itextpdf.text.pdf.PdfWriter;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStream;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Random;

import crl.android.pdfwriter.PDFWriter;
import crl.android.pdfwriter.PaperSize;
import crl.android.pdfwriter.StandardFonts;
import crl.android.pdfwriter.Transformation;


public class MainActivity extends TSActivity {

    private static final String TAG = "MainActivity";
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

        TSLog.info(getClass().getName(), "onCreate");
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
            mDatasource.createImageInterest("title img " + currentDateandTime, Interest.Type.IMAGE, InterestsListAdapter.IMAGE_URL, "image comment");
            mListAdapter.setInterests(mDatasource.getAllInterests());
            mListAdapter.notifyDataSetChanged();
            return true;
        }
        if (id == R.id.action_add_web) {
            mDatasource.createWebInterest("title web " + currentDateandTime, Interest.Type.WEB, "http://google.fr", "web comment");
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
