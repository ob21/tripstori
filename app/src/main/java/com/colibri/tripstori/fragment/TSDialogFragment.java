package com.colibri.tripstori.fragment;

import android.app.Dialog;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.v4.app.DialogFragment;

/**
 * Created by OPOB7414 on 02/06/2016.
 */
public class TSDialogFragment extends DialogFragment {

    private int mType;
    public final static int CONFIRM = 1;
    public final static int CHOICE = 2;

    @NonNull
    @Override
    public Dialog onCreateDialog(Bundle savedInstanceState) {
        return super.onCreateDialog(savedInstanceState);
    }

    public void setType(int type) {
        mType = type;
    }

    public int getType() {
        return mType;
    }
}
