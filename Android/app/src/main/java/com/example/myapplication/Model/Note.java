package com.example.myapplication.Model;

public class Note {

    private String _id;
    private String title;
    private String text;


    public Note(String _id, String title, String text) {
        this._id = _id;
        this.title = title;
        this.text = text;
    }


    public String get_id() {
        return _id;
    }

    public void set_id(String _id) {
        this._id = _id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getText() {
        return text;
    }

    public void setText(String text) {
        this.text = text;
    }

    @Override
    public String toString() {
        return  title + ":\n" + text;
    }
}


