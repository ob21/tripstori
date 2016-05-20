package com.colibri.tripstori.adapters;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import com.android.volley.toolbox.NetworkImageView;
import com.colibri.tripstori.R;
import com.colibri.tripstori.TSApp;
import com.colibri.tripstori.model.Interest;
import com.colibri.tripstori.utils.VolleyManager;

import java.util.ArrayList;

/**
 * Created by olivierbriand on 09/06/2015.
 */
public class InterestsListAdapter extends BaseAdapter {

    private static final String TAG = "InterestsListAdapter";

    public static final String IMAGE_URL =
            "https://developer.android.com/images/training/system-ui.png";

    public static final String MAPS_URL = "https://maps.googleapis.com/maps/api/staticmap?center=40.714728,-73.998672&zoom=12&size=400x400";

    private static final int VIEW_TYPE_NONE = 0;
    private static final int VIEW_TYPE_NOTE = 1;
    private static final int VIEW_TYPE_GEO = 2;
    private static final int VIEW_TYPE_IMAGE = 3;
    private static final int VIEW_TYPE_WEB = 4;

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
        } else
        if (mInterests.get(position).getType() == Interest.Type.GEO){
            return VIEW_TYPE_GEO;
        } else
        if (mInterests.get(position).getType() == Interest.Type.IMAGE){
            return VIEW_TYPE_IMAGE;
        } else
        if (mInterests.get(position).getType() == Interest.Type.WEB){
            return VIEW_TYPE_WEB;
        } else
            return VIEW_TYPE_NONE;
    }

    @Override
    public int getViewTypeCount() {
        return 5;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {

        TSApp.logDebug(TAG, "getView : position="+position);

        ViewHolder holder = null;

        int type = getItemViewType(position);

        TSApp.logDebug(TAG, "getView : type="+type);

        if (convertView == null) {
            holder = new ViewHolder();
            if(type == VIEW_TYPE_NOTE) {
                TSApp.logDebug(TAG, "getView 1 : convertView=null so create new ViewHolder");
                convertView = mInflater.inflate(R.layout.item_note_interest, parent, false);
                holder.image = (NetworkImageView) convertView.findViewById(R.id.item_interest_niv);
                holder.id = (TextView) convertView.findViewById(R.id.item_interest_id);
                holder.title = (TextView) convertView.findViewById(R.id.item_interest_title);
                holder.text = (TextView) convertView.findViewById(R.id.item_interest_text);
            } else
            if(type == VIEW_TYPE_GEO){
                TSApp.logDebug(TAG, "getView 2 : convertView=null so create new ViewHolder");
                convertView = mInflater.inflate(R.layout.item_geo_interest, parent, false);
                holder.image = (NetworkImageView) convertView.findViewById(R.id.item_interest_niv);
                holder.id = (TextView) convertView.findViewById(R.id.item_interest_id);
                holder.title = (TextView) convertView.findViewById(R.id.item_interest_title);
                holder.longitude = (TextView) convertView.findViewById(R.id.item_interest_longitude);
                holder.latitude = (TextView) convertView.findViewById(R.id.item_interest_latitude);
            } else
            if(type == VIEW_TYPE_IMAGE){
                TSApp.logDebug(TAG, "getView 3 : convertView=null so create new ViewHolder");
                convertView = mInflater.inflate(R.layout.item_image_interest, parent, false);
                holder.image = (NetworkImageView) convertView.findViewById(R.id.item_interest_niv);
                holder.id = (TextView) convertView.findViewById(R.id.item_interest_id);
                holder.title = (TextView) convertView.findViewById(R.id.item_interest_title);
                holder.img = (TextView) convertView.findViewById(R.id.item_interest_img);
            } else
            if(type == VIEW_TYPE_WEB){
                TSApp.logDebug(TAG, "getView 4 : convertView=null so create new ViewHolder");
                convertView = mInflater.inflate(R.layout.item_web_interest, parent, false);
                holder.image = (NetworkImageView) convertView.findViewById(R.id.item_interest_niv);
                holder.id = (TextView) convertView.findViewById(R.id.item_interest_id);
                holder.title = (TextView) convertView.findViewById(R.id.item_interest_title);
                holder.web = (TextView) convertView.findViewById(R.id.item_interest_web);
            }
            convertView.setTag(holder);
        } else {
            TSApp.logDebug(TAG, "getView 1 : convertView!=null so get tag ViewHolder");
            holder = (ViewHolder) convertView.getTag();
        }

        Interest interest = mInterests.get(position);

        holder.id.setText(String.valueOf(interest.getId()));
        holder.title.setText(interest.getTitle() + " - " + interest.getType());
        holder.image.setDefaultImageResId(R.drawable.placeholder);
        holder.image.setErrorImageResId(R.drawable.error);

        if(type == VIEW_TYPE_NOTE) {
            holder.type = VIEW_TYPE_NOTE;
            holder.text.setText(interest.getText());
            holder.image.setImageUrl(IMAGE_URL, VolleyManager.getImageLoader());
        } else
        if(type == VIEW_TYPE_GEO) {
            holder.type = VIEW_TYPE_GEO;
            holder.longitude.setText(String.valueOf(interest.getLongitude()));
            holder.latitude.setText(String.valueOf(interest.getLatitude()));
            holder.image.setImageUrl(MAPS_URL, VolleyManager.getImageLoader());
        } else
        if(type == VIEW_TYPE_IMAGE) {
            holder.type = VIEW_TYPE_IMAGE;
            holder.img.setText(String.valueOf(interest.getImageUrl()));
            holder.image.setImageUrl(IMAGE_URL, VolleyManager.getImageLoader());
        } else
        if(type == VIEW_TYPE_WEB) {
            holder.type = VIEW_TYPE_WEB;
            holder.web.setText(String.valueOf(interest.getWebUrl()));
            holder.image.setImageUrl(IMAGE_URL, VolleyManager.getImageLoader());
        }

        return convertView;
    }

    private class ViewHolder {
        public int type;
        public NetworkImageView image;
        public TextView id;
        public TextView title;
        public TextView img;
        public TextView web;
        public TextView longitude;
        public TextView latitude;
        public TextView text;
    }
}
