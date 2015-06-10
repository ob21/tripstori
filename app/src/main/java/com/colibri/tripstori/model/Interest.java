package com.colibri.tripstori.model;

import android.graphics.Bitmap;

/**
 * Created by olivierbriand on 09/06/2015.
 */
public class Interest {

    private String mId;

    private String mTitle;

    private String mUrl;

    private Bitmap mImage;

    public Interest(String id, String title) {
        setId(id);
        setTitle(title);
    }

    public String getId() {
        return mId;
    }

    public void setId(String id) {
        this.mId = id;
    }

    public String getTitle() {
        return mTitle;
    }

    public void setTitle(String title) {
        this.mTitle = title;
    }
}
