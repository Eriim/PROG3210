package com.erictossell.fitnesstracker;

import android.app.Activity;
import android.os.AsyncTask;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
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
    private Spinner mealSpinner;
    private TextView caloriesProgressTextView;
    private TextView proteinProgressTextView;
    private TextView fatProgressTextView;
    private TextView carbProgressTextView;

    private MacroPlan dailyMacro;
    private int protein;
    private int fat;
    private int carb;
    private int calories;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_calorie_tracker);

        Intent service = new Intent(this, DailyResetService.class);
        startService(service);
        // initialize database
        database = AppDatabase.getDatabase(getApplicationContext());

        // retrieve current user macros

        // retrieve user email
        String email = SaveSharedPreference.getUserName(getApplicationContext());
        // retrieve user ID with email for use later on
        long id = database.userDao().getUserId(email);
        // retrieve users macroNutrients for the day
        final MacroPlan macroPlan = database.macroPlanDao().getMacroPlan(id);


        // if they havent selected a plan redirect to the createProfile page
        if(macroPlan == null) {
            Intent intent = new Intent(calorieTracker.this, createProfile.class);
            startActivity(intent);
        }
        // if they have user data continue to initialize activity
        else {
            initialize(macroPlan);
        }
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



    // get items from meal database to spinner
    public void populateSpinner() {
        List<String> list = database.mealDao().getAllMeals();
        ArrayAdapter<String> dataAdapter = new ArrayAdapter<String>(this, android.R.layout.simple_spinner_dropdown_item, list);
        dataAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        mealSpinner.setAdapter(dataAdapter);
    }
    // updates textviews relating to progress
    public void populateProgess() {
        int currentProtein = SaveSharedPreference.getProtein(getApplicationContext());
        int currentFat = SaveSharedPreference.getFat(getApplicationContext());
        int currentCarb = SaveSharedPreference.getCarb(getApplicationContext());
        int currentCalories = SaveSharedPreference.getCalories(getApplicationContext());

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

    //runs on create
    private void initialize(MacroPlan macroPlan) {
        dailyCaloriesProgressBar = (ProgressBar) findViewById(R.id.caloriesProgressBar);
        dailyCaloriesProgressBar.setMax(macroPlan.getCalories().intValue());
        proteinProgressBar = (ProgressBar) findViewById(R.id.proteinProgressBar);
        proteinProgressBar.setMax(macroPlan.getProtein().intValue());
        fatProgressBar = (ProgressBar) findViewById(R.id.fatProgressBar);
        fatProgressBar.setMax(macroPlan.getFat().intValue());
        carbProgressBar = (ProgressBar) findViewById(R.id.carbProgressBar);
        carbProgressBar.setMax(macroPlan.getCarb().intValue());
        proteinProgressBar.setProgress(protein);
        fatProgressBar.setProgress(fat);
        carbProgressBar.setProgress(carb);
        dailyCaloriesProgressBar.setProgress(calories);

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

                protein = protein + meal.getProtein();
                fat = fat + meal.getFat();
                carb = carb + meal.getCarbohydrates();
                calories = calories + meal.getCalories();
                SaveSharedPreference.setDailyProtein(getApplicationContext(), protein);
                SaveSharedPreference.setDailyFat(getApplicationContext(), fat);
                SaveSharedPreference.setDailyCarb(getApplicationContext(), carb);
                SaveSharedPreference.setDailyCalorie(getApplicationContext(), calories);

                proteinProgressBar.setProgress(protein);
                fatProgressBar.setProgress(fat);
                carbProgressBar.setProgress(carb);
                dailyCaloriesProgressBar.setProgress(calories);

                populateProgess();
            }
        });
        addMealButton.setOnClickListener((new View.OnClickListener() {
            public void onClick(View v) {
                Intent intent = new Intent(calorieTracker.this, addMeal.class);
                startActivity(intent);
            }
        }));
    }
}
