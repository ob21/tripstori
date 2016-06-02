package com.colibri.tripstori.model;

import android.graphics.Bitmap;

import java.util.Date;

/**
 * Created by olivierbriand on 09/06/2015.
 */
public class Interest {

    public final static int NONE = 0;
    public final static int NOTE = 1;
    public final static int GEO = 2;
    public final static int IMAGE = 3;
    public final static int WEB = 4;

    private long mId;

    private int mType;

    private String mTitle;

    private String mImageUrl;

    private String mWebUrl;

    private double mLongitude;

    private double mLatitude;

    private String mText;

    private Date mDate;


    public Interest(long id, String title, int type, String text, Date date) {
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

    public Interest(long id, String title, int type, double longitude, double latitude) {
        setId(id);
        setTitle(title);
        setType(type);
        setImageUrl(null);
        setWebUrl(null);
        setLongitude(longitude);
        setLatitude(latitude);
        setText(null);
    }

    public Interest(long id, String title, int type, String imgUrl, String text) {
        setId(id);
        setTitle(title);
        setType(type);
        setImageUrl(imgUrl);
        setLongitude(0);
        setLatitude(0);
        setText(text);
    }

    public Interest(long id, String title, int type, String webUrl) {
        setId(id);
        setTitle(title);
        setType(type);
        setImageUrl(null);
        setWebUrl(webUrl);
        setLongitude(0);
        setLatitude(0);
        setText(null);
    }

    public Interest(long id, String title, int type, String imgUrl, String webURl, double longitude, double latitude, String text) {
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

    public int getType() {
        return mType;
    }

    public void setType(int type) {
        this.mType = type;
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

    public static String typeFromInt(int anInt) {
        if(anInt==1) {
            return "NOTE";
        } else
        if(anInt==2) {
            return "GEO";
        } else
        if(anInt==3) {
            return "IMAGE";
        } else
        if(anInt==4) {
            return "WEB";
        } else
            return "NONE";
    }

    public static int intFromType(String type) {
        if(type.equals("NOTE")) {
            return 1;
        } else
        if(type.equals("GEO")) {
            return 2;
        } else
        if(type.equals("IMAGE")) {
            return 3;
        } else
        if(type.equals("WEB")) {
            return 4;
        } else
            return 0;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        Interest interest = (Interest) o;

        if (mId != interest.mId) return false;
        if (Double.compare(interest.mLongitude, mLongitude) != 0) return false;
        if (Double.compare(interest.mLatitude, mLatitude) != 0) return false;
        if (mType != interest.mType) return false;
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
                ", mType=" + mType +
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
