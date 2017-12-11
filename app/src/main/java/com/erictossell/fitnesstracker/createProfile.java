package com.erictossell.fitnesstracker;

import com.erictossell.fitnesstracker.Database.AppDatabase;
import com.erictossell.fitnesstracker.Util;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.view.animation.Interpolator;
import android.widget.Button;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.SeekBar;
import android.widget.TextView;
import android.content.Intent;


import org.w3c.dom.Text;

public class createProfile extends AppCompatActivity {

    private AppDatabase database;
    private TextView activityLevelTextView;
    private TextView caloriesTextView;
    private EditText nameEditText;
    private EditText ageEditText;
    private EditText feetEditText;
    private EditText inchesEditText;
    private EditText weightEditText;
    private SeekBar activitySeekBar;
    private Button calculateButton;
    private RadioButton maleRadioButton;
    private RadioButton femaleRadioButton;

    private Double age;
    private Double feet;
    private Double inches;
    private Double weight;
    private Double goalWeight;
    private Double calories;
    private Integer caloriesInt;
    private String name;
    private String gender;
    private Double activity;

    private String email;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_create_profile);
        email = getIntent().getStringExtra("username");
        database = AppDatabase.getDatabase(getApplicationContext());
        initialize();
    }
    public String activityLevel(Integer progress){
        String result = "";
        if (progress == 0){
            result = "Sedentary, little or no exercise";
        }
        if (progress == 1){
            result = "Light activity, light exercise 1-3 days a week";
        }
        if (progress == 2){
            result = "Moderate activity, exercise 3-5 days a week";
        }
        if (progress == 3){
            result = "Very active, hard exercise 6-7 days a week";
        }
        if (progress == 4) {
            result = "Extra active, 2x training daily";
        }
        return result;
    }
    public void initialize(){
        Button calculateButton = (Button) findViewById(R.id.calculateCaloriesButton);
        calculateButton.setOnClickListener(new View.OnClickListener(){
            public void onClick(View v){
                if(maleRadioButton.isChecked()){
                    gender = "male";
                }
                else {
                    gender = "female";
                }
                name = nameEditText.getText().toString();
                age = Double.parseDouble(ageEditText.getText().toString());
                feet = Double.parseDouble(feetEditText.getText().toString());
                inches = Double.parseDouble(inchesEditText.getText().toString());
                weight = Double.parseDouble(weightEditText.getText().toString());

                Integer progress = activitySeekBar.getProgress();
                activity = Double.parseDouble(progress.toString());
                Util util = new Util();
                calories = util.calculateCalories(age, feet, inches, weight, activity, gender);
                Intent intent = new Intent(createProfile.this, macroPlans.class);
                //TODO Pass email to verify user logged in
                intent.putExtra("maintenanceCalories", calories);
                startActivity(intent);
            }
        });
        maleRadioButton = (RadioButton) findViewById(R.id.maleRadioButton);
        femaleRadioButton = (RadioButton) findViewById(R.id.femaleRadioButton);
        caloriesTextView = (TextView) findViewById(R.id.caloriesTextView);
        ageEditText = (EditText) findViewById(R.id.ageEditText);
        feetEditText = (EditText) findViewById(R.id.feetEditText);
        inchesEditText = (EditText) findViewById(R.id.inchesEditText);
        weightEditText = (EditText) findViewById(R.id.weightEditText);
        ageEditText = (EditText) findViewById(R.id.ageEditText);
        activityLevelTextView = (TextView) findViewById(R.id.activityLevelTextView);
        activitySeekBar = (SeekBar) findViewById(R.id.activitySeekBar);
        activitySeekBar.setProgress(0);
        activitySeekBar.setMax(4);
        activitySeekBar.setOnSeekBarChangeListener(new SeekBar.OnSeekBarChangeListener(){
            @Override
            public void onProgressChanged(SeekBar seekBar, int progress, boolean fromUser){

                activityLevelTextView.setText(activityLevel(progress));
            }
            @Override
            public void onStartTrackingTouch(SeekBar seekBar){}
            @Override
            public void onStopTrackingTouch(SeekBar seekBar){}
        });
    }

}
