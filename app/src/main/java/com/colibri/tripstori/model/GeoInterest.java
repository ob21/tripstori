package com.colibri.tripstori.model;

import android.graphics.Bitmap;

/**
 * Created by olivierbriand on 09/06/2015.
 */
public class GeoInterest extends Interest {

    private double mLongitude;

    private double mLatitude;

    public GeoInterest(long id, String title, Type type, double longitude, double latitude) {
        super(id, title, type, longitude, latitude);
        setLongitude(longitude);
        setLatitude(latitude);
    }

    @Override
    public String toString() {
        return this.getTitle();
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
}
