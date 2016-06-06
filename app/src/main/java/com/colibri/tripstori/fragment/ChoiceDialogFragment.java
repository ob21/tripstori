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

        dialog.setTitle("Choisissez ce que vous voulez notez");
        dialog.setPositiveButton("Annuler", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                dismiss();
            }
        });

        List<String> list = (List<String>)bundle.get(DATA);
        int position = bundle.getInt(SELECTED);

        CharSequence[] cs = list.toArray(new CharSequence[list.size()]);
        dialog.setItems(cs, (DialogInterface.OnClickListener) getActivity());

        return dialog.create();
    }
}
