package com.colibri.tripstori.utils;

import android.Manifest;
import android.annotation.TargetApi;
import android.app.Activity;
import android.content.Context;
import android.content.DialogInterface;
import android.content.SharedPreferences;
import android.content.pm.PackageManager;
import android.os.Build;
import android.support.v4.app.ActivityCompat;
import android.support.v4.content.ContextCompat;
import android.support.v7.app.AlertDialog;
import com.colibri.tripstori.R;

import com.colibri.tripstori.TSApp;

/**
 * Created by OPOB7414 on 09/06/2016.
 */
public class PermissionUtility {

    private static final String TAG = "PermissionUtility";
    private Activity mActivity;

    private final static String ASKED_KEY = "asked";

    public PermissionUtility(Activity activity) {
        mActivity = activity;
    }

    public static final int MY_PERMISSIONS_REQUEST_READ_EXTERNAL_STORAGE = 123;
    @TargetApi(Build.VERSION_CODES.JELLY_BEAN)
    public boolean checkStoragePermission(final Context context)
    {
        TSApp.logDebug(TAG, "checkStoragePermission");
        int currentAPIVersion = Build.VERSION.SDK_INT;
        if(currentAPIVersion>=android.os.Build.VERSION_CODES.M)
        {
            if (ContextCompat.checkSelfPermission(context, Manifest.permission.READ_EXTERNAL_STORAGE) != PackageManager.PERMISSION_GRANTED) {
                if (ActivityCompat.shouldShowRequestPermissionRationale((Activity) context, Manifest.permission.READ_EXTERNAL_STORAGE)) {
                    AlertDialog.Builder alertBuilder = new AlertDialog.Builder(context);
                    alertBuilder.setCancelable(true);
                    alertBuilder.setTitle("Permission nécéssaire");
                    alertBuilder.setMessage("La permission d'accès au stockage externe est nécéssaire");
                    alertBuilder.setPositiveButton(android.R.string.yes, new DialogInterface.OnClickListener() {
                        @TargetApi(Build.VERSION_CODES.JELLY_BEAN)
                        public void onClick(DialogInterface dialog, int which) {
                            ActivityCompat.requestPermissions((Activity) context, new String[]{Manifest.permission.READ_EXTERNAL_STORAGE}, MY_PERMISSIONS_REQUEST_READ_EXTERNAL_STORAGE);
                            setAsked();
                        }
                    });
                    AlertDialog alert = alertBuilder.create();
                    alert.show();
                } else {
                    ActivityCompat.requestPermissions((Activity) context, new String[]{Manifest.permission.READ_EXTERNAL_STORAGE}, MY_PERMISSIONS_REQUEST_READ_EXTERNAL_STORAGE);
                    setAsked();
                }
                return false;
            } else {
                return true;
            }
        } else {
            return true;
        }
    }

    public void showStoragePermissionInfo(final OnPermissionInfoListener permissionInfoListener) {
        TSApp.logDebug(TAG, "showStoragePermissionInfo");
        AlertDialog.Builder alertBuilder = new AlertDialog.Builder(mActivity);
        alertBuilder.setCancelable(true);
        alertBuilder.setTitle("Permission nécéssaire");
        alertBuilder.setMessage("La permission d'accès au stockage externe est nécéssaire : veuillez aller dans les préférences de l'application.");
        alertBuilder.setPositiveButton(android.R.string.yes, new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                dialog.dismiss();
                permissionInfoListener.onPermissionInfoOk();
            }
        });
        alertBuilder.setNegativeButton(android.R.string.no, new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                dialog.dismiss();
            }
        });
        AlertDialog alert = alertBuilder.create();
        alert.show();
    }

    public void setAsked() {
        SharedPreferences sharedPref = mActivity.getPreferences(Context.MODE_PRIVATE);
        SharedPreferences.Editor editor = sharedPref.edit();
        editor.putBoolean(ASKED_KEY, true);
        editor.commit();
    }

    public boolean isAsked() {
        SharedPreferences sharedPref = mActivity.getPreferences(Context.MODE_PRIVATE);
        return sharedPref.getBoolean(ASKED_KEY, false);
    }

    public interface OnPermissionInfoListener {
        void onPermissionInfoOk();
    }
}