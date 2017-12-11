package com.erictossell.fitnesstracker;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.*;
import android.content.Intent;

import com.erictossell.fitnesstracker.Database.AppDatabase;
import com.erictossell.fitnesstracker.Database.Meal;

import java.util.ArrayList;
import java.util.List;

public class calorieTracker extends AppCompatActivity {

    private AppDatabase database;
    private ProgressBar dailyCaloriesProgressBar;
    private ProgressBar proteinProgressBar;
    private ProgressBar fatProgressBar;
    private ProgressBar carbProgressBar;

    private EditText addCaloriesEditText;
    private Button addCaloriesButton;
    private Button addMealButton;
    private Button eatMealButton;
    private TextView totalCaloriesTextView;
    private TextView currentCaloriesTextView;
    private Spinner mealSpinner;

    private Integer dailyCalories;
    private Integer addCalories;
    private Integer currentCalories;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_calorie_tracker);
        database = AppDatabase.getDatabase(getApplicationContext());
        dailyCalories = (int) getIntent().getDoubleExtra("calories", 2000.00);

        dailyCaloriesProgressBar = (ProgressBar) findViewById(R.id.caloriesProgressBar);
        dailyCaloriesProgressBar.setMax(dailyCalories);
        proteinProgressBar = (ProgressBar) findViewById(R.id.proteinProgressBar);
        proteinProgressBar.setMax(200);
        fatProgressBar = (ProgressBar) findViewById(R.id.fatProgressBar);
        fatProgressBar.setMax(200);
        carbProgressBar = (ProgressBar) findViewById(R.id.carbProgressBar);
        carbProgressBar.setMax(200);

        mealSpinner = (Spinner) findViewById(R.id.mealSpinner);
        populateSpinner();
        addMealButton = (Button) findViewById(R.id.addMealButton);
        eatMealButton = (Button) findViewById(R.id.eatMealButton);
        totalCaloriesTextView = (TextView) findViewById(R.id.totalCaloriesTextView);
        totalCaloriesTextView.setText(dailyCalories.toString());
        currentCaloriesTextView = (TextView) findViewById(R.id.currentCaloriesTextView);
        currentCalories = dailyCaloriesProgressBar.getProgress();
        currentCaloriesTextView.setText(currentCalories.toString());

        eatMealButton.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
            Meal meal = database.mealDao().getMeal(mealSpinner.getSelectedItem().toString());
            dailyCaloriesProgressBar.incrementProgressBy(meal.getCalories());
            proteinProgressBar.incrementProgressBy(meal.getProtein());
            fatProgressBar.incrementProgressBy(meal.getFat());
            carbProgressBar.incrementProgressBy(meal.getCarbohydrates());
            }
        });
        addMealButton.setOnClickListener((new View.OnClickListener(){
            public void onClick(View v){
                Intent intent = new Intent(calorieTracker.this, addMeal.class);
                startActivity(intent);
            }
        }));

    }
    public void populateSpinner() {

        List<String> list = database.mealDao().getAllMeals();
        ArrayAdapter<String> dataAdapter = new ArrayAdapter<String>(this, android.R.layout.simple_spinner_dropdown_item, list);
        dataAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        mealSpinner.setAdapter(dataAdapter);
    }
}
