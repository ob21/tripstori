package com.colibri.tripstori.com.colibri.tripstori.activities;

import android.support.v7.app.ActionBarActivity;
import android.util.Log;

import com.colibri.tripstori.TSApp;

/**
 * Created by olivierbriand on 08/06/2015.
 */
public class TSActivity  extends ActionBarActivity {

    protected void logv(Class c, String m) {
        ((TSApp)getApplication()).getLog().verb(c.getName().substring(c.getName().lastIndexOf(".")), m);
    }

    protected void logd(Class c, String m) {
        ((TSApp)getApplication()).getLog().debug(c.getName().substring(c.getName().lastIndexOf(".")), m);
    }

    protected void logi(Class c, String m) {
        ((TSApp)getApplication()).getLog().info(c.getName().substring(c.getName().lastIndexOf(".")), m);
    }

    protected void logw(Class c, String m) {
        ((TSApp)getApplication()).getLog().warn(c.getName().substring(c.getName().lastIndexOf(".")), m);
    }

    protected void loge(Class c, String m) {
        ((TSApp)getApplication()).getLog().error(c.getName().substring(c.getName().lastIndexOf(".")) ,m);
    }

}
