package com.colibri.tripstori.model;

import java.util.ArrayList;

/**
 * Created by OPOB7414 on 27/05/2016.
 */
public class Story {

    private String mName;
    private String mDescription;
    private String mDate;
    private ArrayList<Interest> mInterests = new ArrayList<>();

    public Story() {
    }

    public Story(String mName, String mDescription, String mDate) {
        this.mName = mName;
        this.mDescription = mDescription;
        this.mDate = mDate;
    }

    public Story(String mName, String mDescription, String mDate, ArrayList<Interest> mInterests) {
        this.mName = mName;
        this.mDescription = mDescription;
        this.mDate = mDate;
        this.mInterests = mInterests;
    }

    public String getName() {
        return mName;
    }

    public void setName(String mName) {
        this.mName = mName;
    }

    public String getDescription() {
        return mDescription;
    }

    public void setDescription(String mDescription) {
        this.mDescription = mDescription;
    }

    public String getDate() {
        return mDate;
    }

    public void setmDate(String mDate) {
        this.mDate = mDate;
    }

    public ArrayList<Interest> getInterests() {
        return mInterests;
    }

    public void setInterests(ArrayList<Interest> mInterests) {
        this.mInterests = mInterests;
    }

    public void addInterest(Interest interest) {
        this.mInterests.add(interest);
    }

    public void deleteInterest(Interest interest) {
        Interest interestToRemove = null;
        for(Interest i : mInterests) {
            if(interest.equals(i)) {
                interestToRemove = i;
            }
        }
        if(interestToRemove != null) {
            this.mInterests.remove(interestToRemove);
        }
    }
}
