package com.erictossell.fitnesstracker;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.view.animation.Interpolator;
import android.widget.Button;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.SeekBar;
import android.widget.TextView;


import org.w3c.dom.Text;

public class createProfile extends AppCompatActivity {

    private TextView activityLevelTextView;
    private TextView caloriesTextView;
    private EditText nameEditText;
    private EditText ageEditText;
    private EditText feetEditText;
    private EditText inchesEditText;
    private EditText weightEditText;
    private EditText goalWeightEditText;
    private SeekBar activitySeekBar;
    private Button calculateButton;
    private RadioButton maleRadioButton;
    private RadioButton femaleRadioButton;

    private Integer age;
    private Integer feet;
    private Integer inches;
    private Integer weight;
    private Integer goalWeight;
    private Integer calories;
    private String name;
    private String gender;
    private String activity;



    private String email;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_create_profile);
        nameEditText = (EditText) findViewById(R.id.nameEditText);
        email = getIntent().getStringExtra("username");
        nameEditText.setText(email);

        ageEditText = (EditText) findViewById(R.id.ageEditText);
        feetEditText = (EditText) findViewById(R.id.feetEditText);
        inchesEditText = (EditText) findViewById(R.id.inchesEditText);
        weightEditText = (EditText) findViewById(R.id.weightEditText);
        goalWeightEditText = (EditText) findViewById(R.id.goalWeightEditText);
        activitySeekBar = (SeekBar) findViewById(R.id.activitySeekBar);
        ageEditText = (EditText) findViewById(R.id.ageEditText);

        Button calculateButton = (Button) findViewById(R.id.calculateCaloriesButton);
        calculateButton.setOnClickListener(new View.OnClickListener(){
            public void onClick(View v){
                name = nameEditText.getText().toString();
                age = Integer.parseInt(ageEditText.getText().toString());
                feet = Integer.parseInt(feetEditText.getText().toString());
                inches = Integer.parseInt(inchesEditText.getText().toString());
                weight = Integer.parseInt(weightEditText.getText().toString());
                goalWeight = Integer.parseInt(goalWeightEditText.getText().toString());
                activity = activityLevelTextView.getText().toString();
                calculateCalories(age, feet, inches, weight, goalWeight, activity);

            }
        });

    }
    public void calculateCalories(Integer age, Integer feet, Integer inches, Integer weight, Integer goalWeight, String activity){
        
    }
}
