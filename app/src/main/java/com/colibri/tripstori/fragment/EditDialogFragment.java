package com.colibri.tripstori.fragment;

import android.app.AlertDialog;
import android.app.Dialog;
import android.content.DialogInterface;
import android.os.Bundle;
import android.view.KeyEvent;
import android.widget.EditText;
import android.widget.TextView;

import com.colibri.tripstori.TSApp;

import java.util.List;

/**
 * Created by OPOB7414 on 07/06/2016.
 */
public class EditDialogFragment extends TSDialogFragment {

    public static final String TITLE = "title";
    private static final String TAG = "EditDialogFragment";

    @Override
    public Dialog onCreateDialog(Bundle savedInstanceState) {
        setType(TSDialogFragment.EDIT);

        Bundle bundle = getArguments();

        String title = bundle.getString(TITLE);

        AlertDialog.Builder alertDialogBuilder = new AlertDialog.Builder(getActivity());
        alertDialogBuilder.setTitle(title);

        final EditText input = new EditText(getActivity());
        alertDialogBuilder.setView(input);

        //null should be your on click listener
        alertDialogBuilder.setPositiveButton("OK", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                ((EditDialogListener)getActivity()).onFinishEditDialog(input.getText().toString());
                TSApp.logDebug(TAG, "onFinishEditDialog : "+input.getText().toString());
                dialog.dismiss();
            }
        });
        alertDialogBuilder.setNegativeButton("Annuler", new DialogInterface.OnClickListener() {

            @Override
            public void onClick(DialogInterface dialog, int which) {
                dialog.dismiss();
            }
        });

        return alertDialogBuilder.create();
    }

    public interface EditDialogListener {
        void onFinishEditDialog(String inputText);
    }
}
