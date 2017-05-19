package com.colibri.tripstori.fragment;

import android.app.AlertDialog;
import android.app.Dialog;
import android.content.DialogInterface;
import android.os.Bundle;
import android.support.v4.app.DialogFragment;

/**
 * Created by OPOB7414 on 27/05/2016.
 */
public class ConfirmDialogFragment extends TSDialogFragment {

    public static final String TITLE = "title";
    public static final String MESSAGE = "message";

    private DialogInterface.OnClickListener mOnclick;

    @Override
    public Dialog onCreateDialog(Bundle savedInstanceState) {
        setType(TSDialogFragment.CONFIRM);
        AlertDialog.Builder alertDialogBuilder = new AlertDialog.Builder(getActivity());

        Bundle bundle = getArguments();
        String title = bundle.getString(TITLE);
        String message = bundle.getString(MESSAGE);

        alertDialogBuilder.setTitle(title);
        alertDialogBuilder.setMessage(message);

        //null should be your on click listener
        alertDialogBuilder.setPositiveButton("OK", mOnclick);
        alertDialogBuilder.setNegativeButton("Annuler", new DialogInterface.OnClickListener() {

            @Override
            public void onClick(DialogInterface dialog, int which) {
                dialog.dismiss();
            }
        });


        return alertDialogBuilder.create();
    }

    public void setOnClickOKListener(DialogInterface.OnClickListener l) {
        mOnclick = l;
    }
}
