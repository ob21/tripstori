package com.colibri.tripstori.factory;

import android.content.Context;
import android.os.Environment;

import com.colibri.tripstori.TSApp;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;

import crl.android.pdfwriter.PDFWriter;
import crl.android.pdfwriter.PaperSize;
import crl.android.pdfwriter.StandardFonts;

/**
 * Created by OPOB7414 on 27/05/2016.
 */
public class PDFFactory {

    private final static String TAG = "PDFFactory";
    private static PDFFactory mInstance;
    private Context mContext;

    public static PDFFactory getInstance() {
        if(mInstance == null) {
            mInstance = new PDFFactory();
        }
        return mInstance;
    }

    public void init(Context c) {
        TSApp.logDebug(TAG, "init context : "+c);
        mContext = c;
    }

    public File createPdf() {
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
        return file;
    }

    public File createFile(String fileName, String pdfContent, String encoding) {
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

}
