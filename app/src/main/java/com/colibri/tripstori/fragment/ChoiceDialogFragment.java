package com.colibri.tripstori.fragment;

import android.app.AlertDialog;
import android.app.Dialog;
import android.content.DialogInterface;
import android.content.res.Resources;
import android.os.Bundle;
import android.support.v4.app.DialogFragment;

import java.util.List;

/**
 * Created by OPOB7414 on 27/05/2016.
 */
public class ChoiceDialogFragment extends TSDialogFragment {

    public static final String DATA = "choices";

    public static final String SELECTED = "selected";

    @Override
    public Dialog onCreateDialog(Bundle savedInstanceState)
    {
        setType(TSDialogFragment.CHOICE);
        Bundle bundle = getArguments();

        AlertDialog.Builder dialog = new AlertDialog.Builder(getActivity());

        dialog.setTitle("Please Select");
        dialog.setPositiveButton("Cancel", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                dismiss();
            }
        });

        List<String> list = (List<String>)bundle.get(DATA);
        int position = bundle.getInt(SELECTED);

        CharSequence[] cs = list.toArray(new CharSequence[list.size()]);
        dialog.setSingleChoiceItems(cs, position, (DialogInterface.OnClickListener) getActivity());

        return dialog.create();
    }
}
