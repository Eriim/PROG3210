package com.erictossell.fitnesstracker;

import android.app.Activity;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.View;
import android.widget.*;
import android.content.Intent;

import com.erictossell.fitnesstracker.Database.AppDatabase;
import com.erictossell.fitnesstracker.Database.MacroPlan;
import com.erictossell.fitnesstracker.Database.Meal;
import com.erictossell.fitnesstracker.Database.SaveSharedPreference;

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
    private TextView caloriesProgressTextView;
    private TextView proteinProgressTextView;
    private TextView fatProgressTextView;
    private TextView carbProgressTextView;

    private MacroPlan dailyMacro;
    private Integer dailyCalories;
    private Integer addCalories;
    private Integer currentCalories;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_calorie_tracker);
        database = AppDatabase.getDatabase(getApplicationContext());

        String email = SaveSharedPreference.getUserName(getApplicationContext());
        long id = database.userDao().getUserId(email);
        MacroPlan macroPlan = database.macroPlanDao().getMacroPlan(id);
        dailyCaloriesProgressBar = (ProgressBar) findViewById(R.id.caloriesProgressBar);
        dailyCaloriesProgressBar.setMax(macroPlan.getCalories().intValue());
        proteinProgressBar = (ProgressBar) findViewById(R.id.proteinProgressBar);
        proteinProgressBar.setMax(macroPlan.getProtein().intValue());
        fatProgressBar = (ProgressBar) findViewById(R.id.fatProgressBar);
        fatProgressBar.setMax(macroPlan.getFat().intValue());
        carbProgressBar = (ProgressBar) findViewById(R.id.carbProgressBar);
        carbProgressBar.setMax(macroPlan.getCarb().intValue());

        caloriesProgressTextView = (TextView) findViewById(R.id.caloriesProgressTextView);
        proteinProgressTextView = (TextView) findViewById(R.id.proteinProgressTextView);
        fatProgressTextView = (TextView) findViewById(R.id.fatProgressTextView);
        carbProgressTextView = (TextView) findViewById(R.id.carbProgressTextView);
        populateProgess();

        mealSpinner = (Spinner) findViewById(R.id.mealSpinner);
        populateSpinner();
        addMealButton = (Button) findViewById(R.id.addMealButton);
        eatMealButton = (Button) findViewById(R.id.eatMealButton);
        eatMealButton.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                Meal meal = database.mealDao().getMeal(mealSpinner.getSelectedItem().toString());
                dailyCaloriesProgressBar.incrementProgressBy(meal.getCalories());
                proteinProgressBar.incrementProgressBy(meal.getProtein());
                fatProgressBar.incrementProgressBy(meal.getFat());
                carbProgressBar.incrementProgressBy(meal.getCarbohydrates());
                populateProgess();
            }
        });
        addMealButton.setOnClickListener((new View.OnClickListener(){
            public void onClick(View v){
                Intent intent = new Intent(calorieTracker.this, addMeal.class);
                startActivity(intent);
            }
        }));

        if (dailyMacro == null) {
            dailyMacro = new MacroPlan(id, 0.00, 0.00, 0.00, 0.00);
        }
    }
    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        MenuInflater inflater = getMenuInflater();
        inflater.inflate(R.menu.menu, menu);
        return super.onCreateOptionsMenu(menu);
    }

    public void populateSpinner() {
        List<String> list = database.mealDao().getAllMeals();
        ArrayAdapter<String> dataAdapter = new ArrayAdapter<String>(this, android.R.layout.simple_spinner_dropdown_item, list);
        dataAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        mealSpinner.setAdapter(dataAdapter);
    }
    public void populateProgess() {
        int currentCalories = dailyCaloriesProgressBar.getProgress();
        int currentProtein = proteinProgressBar.getProgress();
        int currentFat = fatProgressBar.getProgress();
        int currentCarb = carbProgressBar.getProgress();
        int totalCalories = dailyCaloriesProgressBar.getMax();
        int totalProtein = proteinProgressBar.getMax();
        int totalFat = fatProgressBar.getMax();
        int totalCarb = carbProgressBar.getMax();
        String calories = currentCalories+"/"+totalCalories;
        String protein = currentProtein+"/"+totalProtein;
        String fat = currentFat+"/"+totalFat;
        String carb = currentCarb+"/"+totalCarb;
        caloriesProgressTextView.setText(calories);
        proteinProgressTextView.setText(protein);
        fatProgressTextView.setText(fat);
        carbProgressTextView.setText(carb);
    }
}
