package com.erictossell.fitnesstracker;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.TableRow;
import android.widget.TextView;
import java.text.DecimalFormat;
import java.text.NumberFormat;

public class macroPlans extends AppCompatActivity {

    private Double maintenanceCalories;
    private Double hardCutCalories;
    private Double cutCalories;
    private Double cleanBulkCalories;
    private Double hardBulkCalories;

    private TableRow hardCut;
    private TableRow cut;
    private TableRow maintenance;
    private TableRow cleanBulk;
    private TableRow hardBulk;
    private TextView hardCutCaloriesTextView;
    private TextView cutCaloriesTextView;
    private TextView maintenanceCaloriesTextView;
    private TextView cleanBulkCaloriesTextView;
    private TextView hardBulkCaloriesTextView;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_macro_plans);


        maintenanceCalories = getIntent().getDoubleExtra("maintenanceCalories", 0.00);

        hardCut = (TableRow) findViewById(R.id.hardCut);
        cut = (TableRow) findViewById(R.id.cut);
        maintenance = (TableRow) findViewById(R.id.maintenance);
        cleanBulk = (TableRow) findViewById(R.id.cleanBulk);
        hardBulk = (TableRow) findViewById(R.id.hardBulk);
        hardCutCaloriesTextView = (TextView) findViewById(R.id.hardCutCalories);
        cutCaloriesTextView = (TextView) findViewById(R.id.cutCalories);
        maintenanceCaloriesTextView = (TextView) findViewById(R.id.maintenanceCalories);
        cleanBulkCaloriesTextView = (TextView) findViewById(R.id.cleanBulkCalories);
        hardBulkCaloriesTextView = (TextView) findViewById(R.id.hardBulkCalories);

        DecimalFormat formatter = new DecimalFormat("0");
        hardCutCalories = maintenanceCalories - 1000;
        cutCalories = maintenanceCalories - 500;
        cleanBulkCalories = maintenanceCalories + 500;
        hardBulkCalories = maintenanceCalories + 1000;

        hardCutCaloriesTextView.setText(formatter.format(hardCutCalories));
        cutCaloriesTextView.setText(formatter.format(cutCalories));
        maintenanceCaloriesTextView.setText(formatter.format(maintenanceCalories));
        cleanBulkCaloriesTextView.setText(formatter.format(cleanBulkCalories));
        hardBulkCaloriesTextView.setText(formatter.format(hardBulkCalories));

        hardCut.setOnClickListener(new View.OnClickListener(){
            public void onClick(View v){

            }
        });


    }
}
