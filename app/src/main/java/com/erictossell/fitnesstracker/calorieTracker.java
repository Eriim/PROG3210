package com.erictossell.fitnesstracker;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.*;

public class calorieTracker extends AppCompatActivity {

    private ProgressBar dailyCaloriesProgressBar;
    private EditText addCaloriesEditText;
    private Button addCaloriesButton;
    private TextView totalCaloriesTextView;
    private TextView currentCaloriesTextView;

    private Integer dailyCalories;
    private Integer addCalories;
    private Integer currentCalories;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_calorie_tracker);

        dailyCalories = (int) getIntent().getDoubleExtra("calories", 2000.00);

        dailyCaloriesProgressBar = (ProgressBar) findViewById(R.id.caloriesProgressBar);
        dailyCaloriesProgressBar.setMax(dailyCalories);

        addCaloriesEditText = (EditText) findViewById(R.id.addCalroiesEditText);
        addCaloriesButton = (Button) findViewById(R.id.addCaloriesButton);

        totalCaloriesTextView = (TextView) findViewById(R.id.totalCaloriesTextView);
        totalCaloriesTextView.setText(dailyCalories.toString());
        currentCaloriesTextView = (TextView) findViewById(R.id.currentCaloriesTextView);
        currentCalories = dailyCaloriesProgressBar.getProgress();
        currentCaloriesTextView.setText(currentCalories.toString());

        addCaloriesButton.setOnClickListener((new View.OnClickListener(){
            public void onClick(View v){
                addCalories = Integer.parseInt(addCaloriesEditText.getText().toString());
                dailyCaloriesProgressBar.incrementProgressBy(addCalories);
                currentCalories = dailyCaloriesProgressBar.getProgress();
                currentCaloriesTextView.setText(currentCalories.toString());
            }

        }
        ));
    }
}
