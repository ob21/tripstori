package com.colibri.tripstori.model;

import android.graphics.Bitmap;

/**
 * Created by olivierbriand on 09/06/2015.
 */
public class Interest {

    private long mId;

    private String mTitle;

    private String mUrl;

    private Bitmap mImage;

    public Interest(long id, String title) {
        setId(id);
        setTitle(title);
    }

    public long getId() {
        return mId;
    }

    public void setId(long id) {
        this.mId = id;
    }

    public String getTitle() {
        return mTitle;
    }

    public void setTitle(String title) {
        this.mTitle = title;
    }

    @Override
    public String toString() {
        return mTitle;
    }
}
