package com.erictossell.fitnesstracker;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import com.erictossell.fitnesstracker.Database.AppDatabase;
import com.erictossell.fitnesstracker.Database.SaveSharedPreference;
import com.erictossell.fitnesstracker.Database.Weight;

import java.util.Date;

public class trackWeight extends AppCompatActivity {
    private AppDatabase database;
    private EditText weightEditText;
    private Button trackWeightButton;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_track_weight);
        String email = SaveSharedPreference.getUserName(getApplicationContext());
        final long id = database.userDao().getUserId(email);
        weightEditText = (EditText) findViewById(R.id.weightEditText);
        trackWeightButton = (Button) findViewById(R.id.addWeightButton);

        // adds weight object to database
        trackWeightButton.setOnClickListener((new View.OnClickListener() {
            public void onClick(View v) {
                int value = Integer.parseInt(weightEditText.getText().toString());
                Date date = new Date();
                String units = "lbs";
                Weight weight = new Weight(id, value, date, units);
                database.weightDao().addWeight(weight);
                Intent intent = new Intent(trackWeight.this, calorieTracker.class);
                startActivity(intent);
            }
        }));

    }
}
