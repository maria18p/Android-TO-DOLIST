package com.example.myapplication;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.example.myapplication.Control.MainControl;

public class AddNoteActivity extends AppCompatActivity {

    private Button backBtn;
    private Button submitBtn;
    private EditText title;
    private EditText noteText;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_note);
        MainControl.switchToNewNote(this);
        initComponents();
    }

    private void initComponents() {
        title = findViewById(R.id.newNoteTitleTextfield);
        noteText = findViewById(R.id.noteTextTextfield);
        backBtn = findViewById(R.id.AddNoteBackBtn);
        backBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(AddNoteActivity.this, MainActivity.class);
                startActivity(intent);
            }
        });

        submitBtn = findViewById(R.id.submitNewActivity);
        submitBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
//                MainControl.makeToast(title.getText() + " " + noteText.getText());
                MainControl.createNewNote(title.getText().toString(), noteText.getText().toString());

                Toast.makeText(AddNoteActivity.this, "Note submitted", Toast.LENGTH_SHORT).show();

            }
        });

    }
}