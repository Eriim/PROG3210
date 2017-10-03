package com.erictossell.fitnesstracker;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.TextView;

public class createProfile extends AppCompatActivity {
    private TextView welcomeTextView;

    private String welcomeText;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_create_profile);
        welcomeTextView = (TextView) findViewById(R.id.welcomeTextView);
        welcomeText = getIntent().getStringExtra("username");
        welcomeTextView.setText(welcomeText);

    }
}
