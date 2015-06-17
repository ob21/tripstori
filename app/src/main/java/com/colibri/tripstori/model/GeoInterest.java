package com.colibri.tripstori.model;

import android.graphics.Bitmap;

/**
 * Created by olivierbriand on 09/06/2015.
 */
public class GeoInterest extends Interest {

    private long mLongitude;

    private long mLatitude;

    public GeoInterest(long id, String title, Type type, long longitude, long latitude) {
        super(id, title, type);
        setLongitude(longitude);
        setLatitude(latitude);
    }

    @Override
    public String toString() {
        return this.getTitle();
    }

    public long getLongitude() {
        return mLongitude;
    }

    public void setLongitude(long longitude) {
        this.mLongitude = longitude;
    }

    public long getLatitude() {
        return mLatitude;
    }

    public void setLatitude(long latitude) {
        this.mLatitude = latitude;
    }
}
