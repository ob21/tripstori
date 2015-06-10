package com.colibri.tripstori.adapters;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.colibri.tripstori.R;
import com.colibri.tripstori.model.Interest;

import java.util.ArrayList;

/**
 * Created by olivierbriand on 09/06/2015.
 */
public class InterestsListAdapter extends BaseAdapter {

    private ArrayList<Interest> mInterests = new ArrayList<>();
    private LayoutInflater mInflater;

    public InterestsListAdapter(Context context, ArrayList<Interest> interests) {
        mInflater = LayoutInflater.from(context);
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
            holder.id = (TextView)view.findViewById(R.id.item_interest_id);
            holder.title = (TextView)view.findViewById(R.id.item_interest_title);
            view.setTag(holder);
        } else {
            view = convertView;
            holder = (ViewHolder)view.getTag();
        }

        Interest interest = mInterests.get(position);
        holder.id.setText(interest.getId());
        holder.title.setText(interest.getTitle());

        return view;
    }

    private class ViewHolder {
        public TextView id;
        public TextView title;
    }
}
