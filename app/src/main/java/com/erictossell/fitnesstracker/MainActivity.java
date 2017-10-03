package com.erictossell.fitnesstracker;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.EditText;
import android.widget.Button;


public class MainActivity extends AppCompatActivity {

    private EditText mealNameEditText;
    private EditText caloriesEditText;
    private EditText proteinEditText;
    private EditText fatEditText;
    private EditText carbohydratesEditText;
    private EditText servingSizeEditText;
    private Button addMealButton;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        mealNameEditText = (EditText) findViewById(R.id.mealNameEditText);
        caloriesEditText = (EditText) findViewById(R.id.caloriesEditText);
        proteinEditText = (EditText) findViewById(R.id.proteinEditText);
        fatEditText = (EditText) findViewById(R.id.fatEditText);
        carbohydratesEditText = (EditText) findViewById(R.id.carbohydratesEditText);
        servingSizeEditText = (EditText) findViewById(R.id.servingSizeEditText);
    }
}
