package com.erictossell.fitnesstracker;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;

import com.erictossell.fitnesstracker.Database.AppDatabase;
import com.erictossell.fitnesstracker.Database.SaveSharedPreference;
import com.erictossell.fitnesstracker.Database.Weight;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class trackWeight extends AppCompatActivity {
    private AppDatabase database;
    private EditText weightEditText;
    private Button trackWeightButton;
    private ListView weightListView;
    private ArrayAdapter<String> listAdapter;
    private List<Integer> weightList = new ArrayList<Integer>();
    private List<Date> dateList = new ArrayList<Date>();
    private ArrayList<String> listV = new ArrayList<String>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_track_weight);
        database = AppDatabase.getDatabase(getApplicationContext());
        weightEditText = (EditText) findViewById(R.id.weightEditText);
        trackWeightButton = (Button) findViewById(R.id.addWeightButton);
        weightListView = (ListView) findViewById(R.id.weightListView);

        String email = SaveSharedPreference.getUserName(getApplicationContext());
        long id = database.userDao().getUserId(email);
        weightList = database.weightDao().getIntWeight(id);
        dateList = database.weightDao().getDateWeight(id);

        if(weightList.size() > 0) {
            for (int i = 0; i < weightList.size(); i++){
                String construct = "Weight: " + weightList.get(i) + " Date: " + dateList.get(i);
                listV.add(i, construct);
            }
            listAdapter = new ArrayAdapter<String>(this, android.R.layout.simple_list_item_1, listV);
            weightListView.setAdapter(listAdapter);
        }

        // adds weight object to database
        trackWeightButton.setOnClickListener((new View.OnClickListener() {
            public void onClick(View v) {
                int value = Integer.parseInt(weightEditText.getText().toString());
                Date date = new Date();
                String units = "lbs";
                String email = SaveSharedPreference.getUserName(getApplicationContext());
                long id = database.userDao().getUserId(email);
                Weight weight = new Weight(id, value, date, units);
                database.weightDao().addWeight(weight);
                Intent intent = new Intent(trackWeight.this, calorieTracker.class);
                startActivity(intent);
            }
        }));

    }
}
