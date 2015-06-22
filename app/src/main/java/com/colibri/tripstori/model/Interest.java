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

    private double mLongitude;

    private double mLatitude;

    private String mText;


    public Interest(long id, String title, Type type, String text) {
        setId(id);
        setTitle(title);
        setType(type);
        setLongitude(0);
        setLatitude(0);
        setText(text);
    }

    public Interest(long id, String title, Type type, double longitude, double latitude) {
        setId(id);
        setTitle(title);
        setType(type);
        setLongitude(longitude);
        setLatitude(latitude);
        setText(null);
    }

    public Interest(long id, String title, Type type, double longitude, double latitude, String text) {
        setId(id);
        setTitle(title);
        setType(type);
        setLongitude(longitude);
        setLatitude(latitude);
        setText(text);
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

    public double getLongitude() {
        return mLongitude;
    }

    public void setLongitude(double longitude) {
        this.mLongitude = longitude;
    }

    public double getLatitude() {
        return mLatitude;
    }

    public void setLatitude(double latitude) {
        this.mLatitude = latitude;
    }

    public String getText() {
        return mText;
    }

    public void setText(String text) {
        this.mText = text;
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
