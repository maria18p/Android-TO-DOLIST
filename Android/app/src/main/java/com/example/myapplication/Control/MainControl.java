package com.example.myapplication.Control;

import android.app.Activity;
import android.content.Context;
import android.util.Log;
import android.widget.Toast;

import com.example.myapplication.MainActivity;
import com.example.myapplication.Model.Note;

import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.HashMap;

public class MainControl {
    private WebClient webClient;
    private static MainControl instance;
    private Activity currentActivity;
    private ArrayList<Note> notes;

    public static void init(Activity activity){
        instance = new MainControl(activity);
    }

    private MainControl(Activity newActivity){
        currentActivity = newActivity;
        webClient = new WebClient(currentActivity.getApplicationContext());
        notes = new ArrayList<>();
    }

    public static void reloadNotes() {
        instance.webClient.getAllNotes();
    }

    public static void appendNote(String id, String title, String text) {
        Note newNote = new Note(id, title, text);
        instance.notes.add(newNote);
    }

    public static void updateNotes() {
        ((MainActivity) instance.currentActivity).updateNotes(instance.notes);
    }

    public static void clearNotes(){
        instance.notes = new ArrayList<>();
        ((MainActivity) instance.currentActivity).clearNotes();
    }

    public static void deleteNote(String id) {
        HashMap<String, String> params = new HashMap<>();
        params.put("_id", id);
        instance.webClient.deleteNote(params);
    }

    private void setCurrentContext(Activity newActivity){
        this.currentActivity = newActivity;
    }

    private Activity getCurrentContext(){
        return currentActivity;
    }

    public static void switchToNewNote(Activity newActivity){
        instance.setCurrentContext(newActivity);
    }

    public static Context getContext(){
        return instance.getCurrentContext();
    }

    public static void makeToast(String text){
        Toast.makeText(MainControl.getContext(), text, Toast.LENGTH_SHORT).show();
    }

    public static void createNewNote(String title, String text){
        HashMap<String, String> params = new HashMap<>();
        params.put("title", title);
        params.put("text", text);
        instance.webClient.write(params);
    }

}
