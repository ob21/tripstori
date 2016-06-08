package com.colibri.tripstori.adapters;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
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
public class InterestsListAdapter extends RecyclerView.Adapter<InterestsListAdapter.InterestViewHolder> {

    private static final String TAG = "InterestsListAdapter";

    public static final String IMAGE_URL =
            "https://developer.android.com/images/training/system-ui.png";

    public static final String RANDO1_URL = "https://fr.wikipedia.org/wiki/Randonn%C3%A9e_p%C3%A9destre#/media/File:Randonneurs_Glacier_Tour.jpg";

    public static final String RANDO2_URL = "https://fr.wikipedia.org/wiki/Randonn%C3%A9e_p%C3%A9destre#/media/File:Tour_to_the_Quebrada_de_las_Conchas.jpg";

    public static final String MAPS_URL = "https://maps.googleapis.com/maps/api/staticmap?center=40.714728,-73.998672&zoom=12&size=600x400";

    private static final int VIEW_TYPE_NONE = 0;
    private static final int VIEW_TYPE_NOTE = 1;
    private static final int VIEW_TYPE_GEO = 2;
    private static final int VIEW_TYPE_IMAGE = 3;
    private static final int VIEW_TYPE_WEB = 4;

    private ArrayList<Interest> mInterests = new ArrayList<>();
    private LayoutInflater mInflater;
    private OnInterestClickListener mClickListener;
    private OnInterestLongClickListener mLongClickListener;

    public InterestsListAdapter(Context context, ArrayList<Interest> interests, OnInterestClickListener listener) {
        TSApp.logDebug(TAG, "InterestsListAdapter : interests nb = "+interests.size());
        mInflater = LayoutInflater.from(context);
        mClickListener = listener;
        mInterests = interests;
    }

    public void setInterests(ArrayList<Interest> interests) {
        TSApp.logDebug(TAG, "setInterests");
        mInterests = interests;
    }

    public void setOnLongItemClickListener(OnInterestLongClickListener listener) {
        TSApp.logDebug(TAG, "setOnItemClickListener : "+listener);
        mLongClickListener = listener;
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
            holder.image = (ImageView) view.findViewById(R.id.item_interest_iv);
//            holder.netimage = (NetworkImageView) view.findViewById(R.id.item_interest_niv);
            holder.id = (TextView) view.findViewById(R.id.item_interest_id);
            holder.title = (TextView) view.findViewById(R.id.item_interest_title);
            holder.text = (TextView) view.findViewById(R.id.item_interest_text);
        } else
        if(viewType == VIEW_TYPE_GEO){
            TSApp.logDebug(TAG, "onCreateViewHolder geo");
            view = mInflater.inflate(R.layout.item_geo_interest, parent, false);
            holder = new InterestViewHolder(view);
            holder.type = VIEW_TYPE_GEO;
            holder.image = (ImageView) view.findViewById(R.id.item_interest_iv);
//            holder.netimage = (NetworkImageView) view.findViewById(R.id.item_interest_niv);
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
            holder.image = (ImageView) view.findViewById(R.id.item_interest_iv);
//            holder.netimage = (NetworkImageView) view.findViewById(R.id.item_interest_niv);
            holder.id = (TextView) view.findViewById(R.id.item_interest_id);
            holder.title = (TextView) view.findViewById(R.id.item_interest_title);
            holder.img = (TextView) view.findViewById(R.id.item_interest_img);
        } else
        if(viewType == VIEW_TYPE_WEB){
            TSApp.logDebug(TAG, "onCreateViewHolder web");
            view = mInflater.inflate(R.layout.item_web_interest, parent, false);
            holder = new InterestViewHolder(view);
            holder.type = VIEW_TYPE_WEB;
            holder.image = (ImageView) view.findViewById(R.id.item_interest_iv);
//            holder.netimage = (NetworkImageView) view.findViewById(R.id.item_interest_niv);
            holder.id = (TextView) view.findViewById(R.id.item_interest_id);
            holder.title = (TextView) view.findViewById(R.id.item_interest_title);
            holder.web = (TextView) view.findViewById(R.id.item_interest_web);
        }
        return holder;
    }

    @Override
    public void onBindViewHolder(InterestViewHolder holder, final int position) {
        TSApp.logDebug(TAG, "onBindViewHolder");
        Interest interest = mInterests.get(position);

        holder.id.setText("id="+String.valueOf(interest.getId()));
        holder.title.setText(interest.getTitle() + " - " + interest.getType());
//        holder.netimage.setDefaultImageResId(R.drawable.photo);
//        holder.netimage.setErrorImageResId(R.drawable.photo);

        TSApp.logDebug(TAG, "interest = "+interest);

        if(holder.type == VIEW_TYPE_NOTE) {
            holder.text.setText(interest.getText());
//            holder.netimage.setImageUrl(RANDO1_URL, VolleyManager.getImageLoader());
            holder.image.setImageResource(R.drawable.rando1);
            TSApp.logDebug(TAG, "set note item with image : "+RANDO1_URL);
            holder.setOnItemClickListener(new InterestViewHolder.OnItemClickListener() {
                @Override
                public void onItemClick(View v) {
                    TSApp.logDebug(TAG, "click on note : "+position);
                    notifyOnClickListener(v, position);
                }
            });
            holder.setOnItemLongClickListener(new InterestViewHolder.OnItemLongClickListener() {
                @Override
                public void onItemLongClick(View v) {
                    TSApp.logDebug(TAG, "long click on note : "+position);
                    notifyOnLongClickListener(v, position);
                }
            });
        } else
        if(holder.type == VIEW_TYPE_GEO) {
            holder.longitude.setText(String.valueOf(interest.getLongitude()));
            holder.latitude.setText(String.valueOf(interest.getLatitude()));
//            holder.netimage.setImageUrl(MAPS_URL, VolleyManager.getImageLoader());
            holder.setOnItemClickListener(new InterestViewHolder.OnItemClickListener() {
                @Override
                public void onItemClick(View v) {
                    TSApp.logDebug(TAG, "click on geo : "+position);
                    notifyOnClickListener(v, position);
                }
            });
            holder.setOnItemLongClickListener(new InterestViewHolder.OnItemLongClickListener() {
                @Override
                public void onItemLongClick(View v) {
                    TSApp.logDebug(TAG, "long click on geo : "+position);
                    notifyOnLongClickListener(v, position);
                }
            });
        } else
        if(holder.type == VIEW_TYPE_IMAGE) {
            holder.img.setText(String.valueOf(interest.getImageUrl()));
//            holder.netimage.setImageUrl(RANDO2_URL, VolleyManager.getImageLoader());
            holder.image.setImageResource(R.drawable.rando2);
            holder.setOnItemClickListener(new InterestViewHolder.OnItemClickListener() {
                @Override
                public void onItemClick(View v) {
                    TSApp.logDebug(TAG, "click on image : "+position);
                    notifyOnClickListener(v, position);
                }
            });
            holder.setOnItemLongClickListener(new InterestViewHolder.OnItemLongClickListener() {
                @Override
                public void onItemLongClick(View v) {
                    TSApp.logDebug(TAG, "long click on image : "+position);
                    notifyOnLongClickListener(v, position);
                }
            });
        } else
        if(holder.type == VIEW_TYPE_WEB) {
            holder.web.setText(String.valueOf(interest.getWebUrl()));
//            holder.netimage.setImageUrl(RANDO1_URL, VolleyManager.getImageLoader());
            holder.image.setImageResource(R.drawable.rando1);
            holder.setOnItemClickListener(new InterestViewHolder.OnItemClickListener() {
                @Override
                public void onItemClick(View v) {
                    TSApp.logDebug(TAG, "click on web : "+position);
                    notifyOnClickListener(v, position);
                }
            });
            holder.setOnItemLongClickListener(new InterestViewHolder.OnItemLongClickListener() {
                @Override
                public void onItemLongClick(View v) {
                    TSApp.logDebug(TAG, "long click on web : "+position);
                    notifyOnLongClickListener(v, position);
                }
            });
        }
    }

    private void notifyOnClickListener(View v, int p) {
        mClickListener.onInterestClick(v, getItem(p));
    }

    private void notifyOnLongClickListener(View v, int p) {
        mLongClickListener.onInterestLongClick(v, getItem(p));
    }

    @Override
    public int getItemViewType(int position) {
        if (mInterests.get(position).getType() == Interest.NOTE) {
            return VIEW_TYPE_NOTE;
        } else
        if (mInterests.get(position).getType() == Interest.GEO){
            return VIEW_TYPE_GEO;
        } else
        if (mInterests.get(position).getType() == Interest.IMAGE){
            return VIEW_TYPE_IMAGE;
        } else
        if (mInterests.get(position).getType() == Interest.WEB){
            return VIEW_TYPE_WEB;
        } else
            return VIEW_TYPE_NONE;
    }

    public static class InterestViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener, View.OnLongClickListener {
        public int type;
        public NetworkImageView netimage;
        public ImageView image;
        public TextView id;
        public TextView title;
        public TextView img;
        public TextView web;
        public TextView longitude;
        public TextView latitude;
        public TextView text;
        private OnItemClickListener mClickListener;
        private OnItemLongClickListener mLongClickListener;

        public InterestViewHolder(View itemView) {
            super(itemView);
            itemView.setOnClickListener(this);
            itemView.setOnLongClickListener(this);
        }

        public void setOnItemClickListener(OnItemClickListener listener) {
            mClickListener = listener;
        }

        public void setOnItemLongClickListener(OnItemLongClickListener listener) {
            mLongClickListener = listener;
        }

        @Override
        public void onClick(View v) {
            if(mClickListener != null) {
                mClickListener.onItemClick(v);
            }
        }

        @Override
        public boolean onLongClick(View v) {
            if(mLongClickListener != null) {
                mLongClickListener.onItemLongClick(v);
            }
            return true;
        }

        public interface OnItemClickListener {
            void onItemClick(View v);
        }

        public interface OnItemLongClickListener {
            void onItemLongClick(View v);
        }
    }

    public interface OnInterestClickListener {
        void onInterestClick(View v, Interest i);
    }

    public interface OnInterestLongClickListener {
        void onInterestLongClick(View v, Interest i);
    }
}
