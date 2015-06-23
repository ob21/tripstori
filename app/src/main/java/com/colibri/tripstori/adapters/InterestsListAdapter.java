package com.colibri.tripstori.adapters;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.android.volley.toolbox.ImageLoader;
import com.android.volley.toolbox.NetworkImageView;
import com.colibri.tripstori.R;
import com.colibri.tripstori.model.Interest;
import com.colibri.tripstori.utils.VolleyManager;

import java.util.ArrayList;

/**
 * Created by olivierbriand on 09/06/2015.
 */
public class InterestsListAdapter extends BaseAdapter {

    private static final String IMAGE_URL =
            "http://developer.android.com/images/training/system-ui.png";
    private static final String IMAGE1_URL =
            "http://www.setgoogle.com/images/domain/homepage/google_icon/google_play.png";
    private static final int VIEW_TYPE_NONE = 0;
    private static final int VIEW_TYPE_NOTE = 1;
    private static final int VIEW_TYPE_GEO = 2;

    private ArrayList<Interest> mInterests = new ArrayList<>();
    private LayoutInflater mInflater;

    public InterestsListAdapter(Context context, ArrayList<Interest> interests) {
        mInflater = LayoutInflater.from(context);
        mInterests = interests;
    }

    public void setInterests(ArrayList<Interest> interests) {
        mInterests = interests;
    }

    @Override
    public int getCount() {
        return mInterests.size();
    }

    @Override
    public Object getItem(int i) {
        return mInterests.get(i);
    }

    @Override
    public long getItemId(int i) {
        return 0;
    }


    @Override
    public int getItemViewType(int position) {
        if (mInterests.get(position).getType() == Interest.Type.NOTE) {
            return VIEW_TYPE_NOTE;
        } else if (mInterests.get(position).getType() == Interest.Type.GEO) {
            return VIEW_TYPE_GEO;
        } else {
            return VIEW_TYPE_NONE;
        }
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        View view;

        ViewNoteHolder noteHolder = null;
        ViewGeoHolder geoHolder = null;
        ViewHolder holder = null;

        int type = getItemViewType(position);

        if(type == VIEW_TYPE_NOTE) {

            if (convertView == null) {
                view = mInflater.inflate(R.layout.item_note_interest, parent, false);
                holder = new ViewHolder();
                holder.image = (NetworkImageView) view.findViewById(R.id.item_interest_niv);
                holder.id = (TextView) view.findViewById(R.id.item_interest_id);
                holder.title = (TextView) view.findViewById(R.id.item_interest_title);
                holder.text = (TextView) view.findViewById(R.id.item_interest_text);
                view.setTag(holder);
            } else {
                view = convertView;
                holder = (ViewHolder) view.getTag();
            }

            Interest interest = mInterests.get(position);

            holder.id.setText(String.valueOf(interest.getId()));
            holder.title.setText(interest.getTitle() + " - " + interest.getType());
            holder.text.setText(interest.getText());

        } else {

            if (convertView == null) {
                view = mInflater.inflate(R.layout.item_geo_interest, parent, false);
                holder = new ViewHolder();
                holder.image = (NetworkImageView) view.findViewById(R.id.item_interest_niv);
                holder.id = (TextView) view.findViewById(R.id.item_interest_id);
                holder.title = (TextView) view.findViewById(R.id.item_interest_title);
                holder.longitude = (TextView) view.findViewById(R.id.item_interest_longitude);
                holder.latitude = (TextView) view.findViewById(R.id.item_interest_latitude);
                view.setTag(holder);
            } else {
                view = convertView;
                holder = (ViewHolder) view.getTag();
            }

            Interest interest = mInterests.get(position);

            holder.id.setText(String.valueOf(interest.getId()));
            holder.title.setText(interest.getTitle() + " - " + interest.getType());
            holder.longitude.setText(String.valueOf(interest.getLongitude()));
            holder.latitude.setText(String.valueOf(interest.getLatitude()));

        }

        return view;
    }

    private class ViewNoteHolder {
        public NetworkImageView image;
        public TextView id;
        public TextView title;
        public TextView text;
    }

    private class ViewGeoHolder {
        public NetworkImageView image;
        public TextView id;
        public TextView title;
        public TextView longitude;
        public TextView latitude;
    }

    private class ViewHolder {
        public NetworkImageView image;
        public TextView id;
        public TextView title;
        public TextView longitude;
        public TextView latitude;
        public TextView text;
    }
}
