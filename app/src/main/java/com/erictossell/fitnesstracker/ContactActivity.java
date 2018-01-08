package com.erictossell.fitnesstracker;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

public class ContactActivity extends Activity {

    private Button emailButton;
    private Button textButton;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_contact);
        emailButton = (Button) findViewById(R.id.emailButton);

        emailButton.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                Intent emailIntent = new Intent(Intent.ACTION_SENDTO,
                        Uri.fromParts("mailto", "erictossell@gmail.com", null));
                startActivity(Intent.createChooser(emailIntent, "Send email..."));

            }
        });
        textButton = (Button) findViewById(R.id.textButton);
        textButton.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                Intent textIntent = new Intent(Intent.ACTION_MAIN);
                textIntent.addCategory(Intent.CATEGORY_APP_MESSAGING);

                startActivity(textIntent);

            }
        });

    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        MenuInflater inflater = getMenuInflater();
        inflater.inflate(R.menu.menu, menu);
        return true;
    }

    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()){
            case R.id.menu_calorieTracker:
                startActivity(new Intent(this, calorieTracker.class));
                return true;
            case R.id.menu_createProfile:
                startActivity(new Intent(this, createProfile.class));
                return true;
            case R.id.menu_trackWeight:
                startActivity(new Intent(this, trackWeight.class));
                return true;
            default:
                return super.onOptionsItemSelected(item);
        }
    }
}
