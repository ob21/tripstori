package com.colibri.tripstori.model;

import android.graphics.Bitmap;

import java.util.Date;

/**
 * Created by olivierbriand on 09/06/2015.
 */
public class Interest {

    public enum Type {
        NONE(0),
        NOTE(1),
        GEO(2),
        IMAGE(3),
        WEB(4);

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

    private String mImageUrl;

    private String mWebUrl;

    private double mLongitude;

    private double mLatitude;

    private String mText;

    private Date mDate;


    public Interest(long id, String title, Type type, String text, Date date) {
        setId(id);
        setTitle(title);
        setType(type);
        setImageUrl(null);
        setWebUrl(null);
        setLongitude(0);
        setLatitude(0);
        setText(text);
        setDate(date);
    }

    public Interest(long id, String title, Type type, double longitude, double latitude) {
        setId(id);
        setTitle(title);
        setType(type);
        setImageUrl(null);
        setWebUrl(null);
        setLongitude(longitude);
        setLatitude(latitude);
        setText(null);
    }

    public Interest(long id, String title, Type type, String imgUrl, String text) {
        setId(id);
        setTitle(title);
        setType(type);
        setImageUrl(imgUrl);
        setLongitude(0);
        setLatitude(0);
        setText(text);
    }

    public Interest(long id, String title, Type type, String webUrl) {
        setId(id);
        setTitle(title);
        setType(type);
        setImageUrl(null);
        setWebUrl(webUrl);
        setLongitude(0);
        setLatitude(0);
        setText(null);
    }

    public Interest(long id, String title, Type type, String imgUrl, String webURl, double longitude, double latitude, String text) {
        setId(id);
        setTitle(title);
        setType(type);
        setImageUrl(imgUrl);
        setWebUrl(webURl);
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

    public String getImageUrl() {
        return mImageUrl;
    }

    public void setImageUrl(String imageUrl) {
        this.mImageUrl = imageUrl;
    }

    public String getWebUrl() {
        return mWebUrl;
    }

    public void setWebUrl(String webUrl) {
        this.mWebUrl = webUrl;
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

    public Date getDate() {
        return mDate;
    }

    public void setDate(Date date) {
        this.mDate = date;
    }

    public static Type typeFromInt(int anInt) {
        if(anInt==1) {
            return Type.NOTE;
        } else
        if(anInt==2) {
            return Type.GEO;
        } else
        if(anInt==3) {
            return Type.IMAGE;
        } else
        if(anInt==4) {
            return Type.WEB;
        } else
            return Type.NONE;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        Interest interest = (Interest) o;

        if (mId != interest.mId) return false;
        if (Double.compare(interest.mLongitude, mLongitude) != 0) return false;
        if (Double.compare(interest.mLatitude, mLatitude) != 0) return false;
        if (mtype != interest.mtype) return false;
        if (mTitle != null ? !mTitle.equals(interest.mTitle) : interest.mTitle != null)
            return false;
        if (mImageUrl != null ? !mImageUrl.equals(interest.mImageUrl) : interest.mImageUrl != null)
            return false;
        if (mWebUrl != null ? !mWebUrl.equals(interest.mWebUrl) : interest.mWebUrl != null)
            return false;
        if (mText != null ? !mText.equals(interest.mText) : interest.mText != null) return false;
        return mDate != null ? mDate.equals(interest.mDate) : interest.mDate == null;

    }

    @Override
    public String toString() {
        return "Interest{" +
                "mId=" + mId +
                ", mtype=" + mtype +
                ", mTitle='" + mTitle + '\'' +
                ", mImageUrl='" + mImageUrl + '\'' +
                ", mWebUrl='" + mWebUrl + '\'' +
                ", mLongitude=" + mLongitude +
                ", mLatitude=" + mLatitude +
                ", mText='" + mText + '\'' +
                ", mDate=" + mDate +
                '}';
    }
}
