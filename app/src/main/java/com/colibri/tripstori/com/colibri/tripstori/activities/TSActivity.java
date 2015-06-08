package com.colibri.tripstori.com.colibri.tripstori.activities;

import android.support.v7.app.ActionBarActivity;

import com.colibri.tripstori.TSApp;

/**
 * Created by olivierbriand on 08/06/2015.
 */
public class TSActivity  extends ActionBarActivity {

    protected void logv(String message) {
        ((TSApp)getApplication()).getLog().verb(getLocalClassName(), message);
    }

    protected void logd(String message) {
        ((TSApp)getApplication()).getLog().debug(getLocalClassName(), message);
    }

    protected void logi(String message) {
        ((TSApp)getApplication()).getLog().info(getLocalClassName(), message);
    }

    protected void logw(String message) {
        ((TSApp)getApplication()).getLog().warn(getLocalClassName(), message);
    }

    protected void loge(String message) {
        ((TSApp)getApplication()).getLog().error(getLocalClassName(), message);
    }

}
