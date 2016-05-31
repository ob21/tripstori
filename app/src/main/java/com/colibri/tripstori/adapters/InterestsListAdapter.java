package com.colibri.tripstori.adapters;

import android.app.Activity;
import android.content.Context;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import com.android.volley.toolbox.NetworkImageView;
import com.colibri.tripstori.R;
import com.colibri.tripstori.TSApp;
import com.colibri.tripstori.activities.MainActivity;
import com.colibri.tripstori.model.Interest;
import com.colibri.tripstori.utils.VolleyManager;

import java.util.ArrayList;

/**
 * Created by olivierbriand on 09/06/2015.
 */
public class InterestsListAdapter extends RecyclerView.Adapter<InterestsListAdapter.InterestViewHolder> {

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
    private MainActivity mActivity;
    private LayoutInflater mInflater;

    public InterestsListAdapter(MainActivity activity, ArrayList<Interest> interests) {
        TSApp.logDebug(TAG, "InterestsListAdapter : interests nb = "+interests.size());
        mActivity = activity;
        mInflater = LayoutInflater.from(activity);
        mInterests = interests;
    }

    public void setInterests(ArrayList<Interest> interests) {
        TSApp.logDebug(TAG, "setInterests");
        mInterests = interests;
    }

    public Interest getItem(int i) {
        return mInterests.get(i);
    }

    @Override
    public long getItemId(int i) {
        return mInterests.get(i).getId();
    }

    @Override
    public int getItemCount() {
        return mInterests.size();
    }

    @Override
    public InterestViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view;
        InterestViewHolder holder = null;
        // create a new view
        if(viewType == VIEW_TYPE_NOTE) {
            TSApp.logDebug(TAG, "onCreateViewHolder note");
            view = mInflater.inflate(R.layout.item_note_interest, parent, false);
            holder = new InterestViewHolder(view);
            holder.type = VIEW_TYPE_NOTE;
            holder.image = (NetworkImageView) view.findViewById(R.id.item_interest_niv);
            holder.id = (TextView) view.findViewById(R.id.item_interest_id);
            holder.title = (TextView) view.findViewById(R.id.item_interest_title);
            holder.text = (TextView) view.findViewById(R.id.item_interest_text);
        } else
        if(viewType == VIEW_TYPE_GEO){
            TSApp.logDebug(TAG, "onCreateViewHolder geo");
            view = mInflater.inflate(R.layout.item_geo_interest, parent, false);
            holder = new InterestViewHolder(view);
            holder.type = VIEW_TYPE_GEO;
            holder.image = (NetworkImageView) view.findViewById(R.id.item_interest_niv);
            holder.id = (TextView) view.findViewById(R.id.item_interest_id);
            holder.title = (TextView) view.findViewById(R.id.item_interest_title);
            holder.longitude = (TextView) view.findViewById(R.id.item_interest_longitude);
            holder.latitude = (TextView) view.findViewById(R.id.item_interest_latitude);
        } else
        if(viewType == VIEW_TYPE_IMAGE){
            TSApp.logDebug(TAG, "onCreateViewHolder image");
            view = mInflater.inflate(R.layout.item_image_interest, parent, false);
            holder = new InterestViewHolder(view);
            holder.type = VIEW_TYPE_IMAGE;
            holder.image = (NetworkImageView) view.findViewById(R.id.item_interest_niv);
            holder.id = (TextView) view.findViewById(R.id.item_interest_id);
            holder.title = (TextView) view.findViewById(R.id.item_interest_title);
            holder.img = (TextView) view.findViewById(R.id.item_interest_img);
        } else
        if(viewType == VIEW_TYPE_WEB){
            TSApp.logDebug(TAG, "onCreateViewHolder web");
            view = mInflater.inflate(R.layout.item_web_interest, parent, false);
            holder = new InterestViewHolder(view);
            holder.type = VIEW_TYPE_WEB;
            holder.image = (NetworkImageView) view.findViewById(R.id.item_interest_niv);
            holder.id = (TextView) view.findViewById(R.id.item_interest_id);
            holder.title = (TextView) view.findViewById(R.id.item_interest_title);
            holder.web = (TextView) view.findViewById(R.id.item_interest_web);
        }
        return holder;
    }

    @Override
    public void onBindViewHolder(InterestViewHolder holder, int position) {
        TSApp.logDebug(TAG, "onBindViewHolder");
        Interest interest = mInterests.get(position);

        holder.id.setText("id="+String.valueOf(interest.getId()));
        holder.title.setText(interest.getTitle() + " - " + interest.getType());
        holder.image.setDefaultImageResId(R.drawable.photo);
        holder.image.setErrorImageResId(R.drawable.photo);

        if(holder.type == VIEW_TYPE_NOTE) {
            holder.text.setText(interest.getText());
            holder.image.setImageUrl(IMAGE_URL, VolleyManager.getImageLoader());
        } else
        if(holder.type == VIEW_TYPE_GEO) {
            holder.longitude.setText(String.valueOf(interest.getLongitude()));
            holder.latitude.setText(String.valueOf(interest.getLatitude()));
            holder.image.setImageUrl(MAPS_URL, VolleyManager.getImageLoader());
        } else
        if(holder.type == VIEW_TYPE_IMAGE) {
            holder.img.setText(String.valueOf(interest.getImageUrl()));
            holder.image.setImageUrl(IMAGE_URL, VolleyManager.getImageLoader());
        } else
        if(holder.type == VIEW_TYPE_WEB) {
            holder.web.setText(String.valueOf(interest.getWebUrl()));
            holder.image.setImageUrl(IMAGE_URL, VolleyManager.getImageLoader());
        }

        holder.image.setOnLongClickListener(mActivity);
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

    public class InterestViewHolder extends RecyclerView.ViewHolder {
        public int type;
        public NetworkImageView image;
        public TextView id;
        public TextView title;
        public TextView img;
        public TextView web;
        public TextView longitude;
        public TextView latitude;
        public TextView text;

        public InterestViewHolder(View itemView) {
            super(itemView);
        }
    }

    public interface InterestsRecyclerViewListener extends View.OnLongClickListener {
        void onLongItemClick(int position);
    }
}
