package com.example.myapplication;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.ScrollView;
import android.widget.TextView;

import com.example.myapplication.Control.MainControl;
import com.example.myapplication.Model.Note;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {

    private Button newNoteBtn;
    private Button refreshBtn;
    private LinearLayout noteLayout;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        initComponents();
        //INITIALIZE MAIN_CONTROL
        MainControl.init(this);
    }

    //INITIALIZE THE UI COMPONENTS
    private void initComponents(){
        noteLayout = findViewById(R.id.noteLayout);
        newNoteBtn = findViewById(R.id.addNoteBtn);
        newNoteBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(MainActivity.this, AddNoteActivity.class);
                startActivity(intent);
            }
        });

        refreshBtn = findViewById(R.id.reloadNotes);
        refreshBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                MainControl.reloadNotes();
            }
        });
    }

    public void updateNotes(ArrayList<Note> notes){

        for(int i = 0; i < notes.size(); i++){
            String id    = notes.get(i).get_id();
            String title = notes.get(i).getTitle();
            String text  = notes.get(i).getText();
            noteLayout.addView(createCustomView(this.getApplicationContext(), id, title, text));
        }
    }

    // CREATE A CUSTOM NOTE VIEW DYNAMICALLY
    public static LinearLayout createCustomView(Context context, String id, String title, String text) {
        LinearLayout result = new LinearLayout(context);
        result.setOrientation(LinearLayout.HORIZONTAL);
        LinearLayout linearLayout = new LinearLayout(context);
        linearLayout.setLayoutParams(new LinearLayout.LayoutParams(
                ViewGroup.LayoutParams.WRAP_CONTENT,
                ViewGroup.LayoutParams.WRAP_CONTENT));
        linearLayout.setOrientation(LinearLayout.VERTICAL);

        Button deleteButton = new Button(context);
        deleteButton.setLayoutParams(new LinearLayout.LayoutParams(
                ViewGroup.LayoutParams.WRAP_CONTENT,
                ViewGroup.LayoutParams.WRAP_CONTENT));
        deleteButton.setText("Delete");

        deleteButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                MainControl.deleteNote(id);
            }
        });
        result.addView(deleteButton);

        TextView titleTextView = new TextView(context);
        titleTextView.setLayoutParams(new LinearLayout.LayoutParams(
                ViewGroup.LayoutParams.WRAP_CONTENT,
                ViewGroup.LayoutParams.WRAP_CONTENT));
        titleTextView.setText(title);
        linearLayout.addView(titleTextView);

        TextView textTextView = new TextView(context);
        textTextView.setLayoutParams(new LinearLayout.LayoutParams(
                ViewGroup.LayoutParams.WRAP_CONTENT,
                ViewGroup.LayoutParams.WRAP_CONTENT));
        textTextView.setText(text);
        linearLayout.addView(textTextView);

        result.addView(linearLayout);

        return result;
    }


    public void clearNotes() {
        noteLayout.removeAllViewsInLayout();
    }
}