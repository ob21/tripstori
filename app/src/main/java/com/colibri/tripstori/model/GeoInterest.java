package com.colibri.tripstori.model;

import android.graphics.Bitmap;

/**
 * Created by olivierbriand on 09/06/2015.
 */
public class GeoInterest extends Interest {

    private double mLongitude;

    private double mLatitude;

    private String mLocation;

    public GeoInterest(long id, String title, int type, String location, double longitude, double latitude) {
        super(id, title, type, location, longitude, latitude);
        setLocation(location);
        setLongitude(longitude);
        setLatitude(latitude);
    }

    @Override
    public String toString() {
        return this.getTitle();
    }

    public String getLocation() {
        return mLocation;
    }

    public void setLocation(String location) {
        this.mLocation = location;
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
