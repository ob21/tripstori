package com.colibri.tripstori.model;

import android.graphics.Bitmap;

/**
 * Created by olivierbriand on 09/06/2015.
 */
public class Interest {

    public enum Type {
        GEO(0),
        NOTE(1),
        NONE(2);

        private final int mValue;
        private Type(int value) {
            this.mValue = value;
        }

        public int getValue() {
            return mValue;
        }
    }

    private long mId;

    private Type mtype;

    private String mTitle;

    private String mUrl;

    private Bitmap mImage;

    private long mLongitude;

    private long mLatitude;

    private String mText;

    public Interest(long id, String title, Type type) {
        setId(id);
        setTitle(title);
        setType(type);
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

    public Type getType() {
        return mtype;
    }

    public void setType(Type type) {
        this.mtype = type;
    }

    public static Type typeFromInt(int anInt) {
        if(anInt==0) {
            return Type.GEO;
        } else
        if(anInt==1) {
            return Type.NOTE;
        } else
            return Type.NONE;
    }

    @Override
    public String toString() {
        return mTitle;
    }
}
