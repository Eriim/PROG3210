package com.erictossell.fitnesstracker;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.content.Intent;
import android.widget.*;

import com.erictossell.fitnesstracker.Database.AppDatabase;
import com.erictossell.fitnesstracker.Database.Meal;

public class addMeal extends AppCompatActivity {
    private AppDatabase database;
    private EditText mealNameEditText;
    private EditText caloriesEditText;
    private EditText proteinEditText;
    private EditText fatEditText;
    private EditText carbEditText;
    private EditText servingSizeEditText;
    private Button addMealButton;

    private String mealName;
    private Integer calories;
    private Integer protein;
    private Integer fat;
    private Integer carbs;
    private String servingSize = "0";


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_meal);
        database = AppDatabase.getDatabase(getApplicationContext());
        initialize();
    }
    // run on activity start
    private void initialize(){
        mealNameEditText = (EditText) findViewById(R.id.mealNameEditText);
        caloriesEditText = (EditText) findViewById(R.id.caloriesEditText);
        proteinEditText = (EditText) findViewById(R.id.proteinEditText);
        fatEditText = (EditText) findViewById(R.id.fatEditText);
        carbEditText = (EditText) findViewById(R.id.carbEditText);
        servingSizeEditText = (EditText) findViewById(R.id.servingSizeEditText);

        addMealButton = (Button) findViewById(R.id.addMealButton);
        addMealButton.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v){
                mealName = mealNameEditText.getText().toString();
                calories = Integer.parseInt(caloriesEditText.getText().toString());
                protein = Integer.parseInt(proteinEditText.getText().toString());
                fat = Integer.parseInt(fatEditText.getText().toString());
                carbs = Integer.parseInt(carbEditText.getText().toString());
                Meal meal = new Meal(mealName, calories, protein, fat, carbs, servingSize);
                database.mealDao().addMeal(meal);
                Intent intent = new Intent(addMeal.this, calorieTracker.class);
                startActivity(intent);
            }
        });
    }
}
