package com.colibri.tripstori.model;

/**
 * Created by olivierbriand on 09/06/2015.
 */
public class NoteInterest extends Interest {

    private String mText;

    public NoteInterest(long id, String title, Type type, String text) {
        super(id, title, type, text);
        setText(text);
    }

    @Override
    public String toString() {
        return this.getTitle();
    }

    public String getText() {
        return mText;
    }

    public void setText(String text) {
        this.mText = text;
    }
}
