package com.colibri.tripstori.fragment;

import android.app.AlertDialog;
import android.app.Dialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.res.Resources;
import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.support.v4.app.DialogFragment;
import android.support.v4.content.res.ResourcesCompat;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.ListAdapter;
import android.widget.TextView;

import com.colibri.tripstori.R;
import com.colibri.tripstori.TSApp;

import java.util.List;

/**
 * Created by OPOB7414 on 27/05/2016.
 */
public class ChoiceDialogFragment extends TSDialogFragment {

    public static final String STRING_CHOICES = "choices";

    public static final String IMAGES_CHOICES = "images";

    public static final String SELECTED = "selected";

    public static final String TITLE = "title";
    private static final String TAG = "ChoiceDialogFragment";

    @Override
    public Dialog onCreateDialog(Bundle savedInstanceState)
    {
        setType(TSDialogFragment.CHOICE);
        Bundle bundle = getArguments();

        AlertDialog.Builder dialog = new AlertDialog.Builder(getActivity());

        String title = bundle.getString(TITLE);

        dialog.setTitle(title);
        dialog.setPositiveButton("Annuler", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                dismiss();
            }
        });

        final List<String> items = (List<String>)bundle.get(STRING_CHOICES);
        final List<Integer> images = (List<Integer>)bundle.get(IMAGES_CHOICES);

        TSApp.logDebug(TAG, "items = "+items);
        if(items != null) TSApp.logDebug(TAG, "items.get(0) = "+items.get(0));

        ListAdapter adapter = new ArrayAdapter<String>(
                getActivity(), R.layout.dialog_item, items) {

            ViewHolder holder;

            class ViewHolder {
                ImageView icon;
                TextView title;
            }

            public View getView(int position, View convertView,
                                ViewGroup parent) {
                final LayoutInflater inflater = (LayoutInflater) getActivity()
                        .getSystemService(
                                Context.LAYOUT_INFLATER_SERVICE);

                if (convertView == null) {
                    convertView = inflater.inflate(
                            R.layout.dialog_item, null);

                    holder = new ViewHolder();
                    holder.icon = (ImageView) convertView
                            .findViewById(R.id.dialog_item_iv);
                    holder.title = (TextView) convertView
                            .findViewById(R.id.dialog_item_tv);
                    convertView.setTag(holder);
                } else {
                    // view already defined, retrieve view holder
                    holder = (ViewHolder) convertView.getTag();
                }

                if(images!=null && !images.isEmpty() && images.size()==items.size()) {
                    Drawable drawable = ResourcesCompat.getDrawable(getResources(), images.get(0), null);
                    switch(position) {
                        case 0:
                            drawable = ResourcesCompat.getDrawable(getResources(), images.get(0), null);
                            break;
                        case 1:
                            drawable = ResourcesCompat.getDrawable(getResources(), images.get(1), null);
                            break;
                        case 2:
                            drawable = ResourcesCompat.getDrawable(getResources(), images.get(2), null);
                            break;
                        case 3:
                            drawable = ResourcesCompat.getDrawable(getResources(), images.get(3), null);
                            break;
                    }
                    holder.icon.setImageDrawable(drawable);
                } else {
                    holder.icon.setVisibility(View.GONE);
                }
                TSApp.logDebug(TAG, "set choice : "+items.get(position));
                holder.title.setText(items.get(position));

                return convertView;
            }
        };

        dialog.setAdapter(adapter, (DialogInterface.OnClickListener) getActivity());

        return dialog.create();
    }

}

