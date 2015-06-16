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
    public View getView(int position, View convertView, ViewGroup parent) {
        View view;
        ViewHolder holder;
        if(convertView == null) {
            view = mInflater.inflate(R.layout.item_interest, parent, false);
            holder = new ViewHolder();
            holder.image = (NetworkImageView)view.findViewById(R.id.item_interest_niv);
            holder.id = (TextView)view.findViewById(R.id.item_interest_id);
            holder.title = (TextView)view.findViewById(R.id.item_interest_title);
            view.setTag(holder);
        } else {
            view = convertView;
            holder = (ViewHolder)view.getTag();
        }

        Interest interest = mInterests.get(position);
        if(position % 2 == 0) {
            holder.image.setImageUrl(IMAGE_URL, VolleyManager.getImageLoader());
        } else {
            holder.image.setImageUrl(IMAGE1_URL, VolleyManager.getImageLoader());
        }
        holder.id.setText(String.valueOf(interest.getId()));
        holder.title.setText(interest.getTitle());

        return view;
    }

    private class ViewHolder {
        public NetworkImageView image;
        public TextView id;
        public TextView title;
    }
}
