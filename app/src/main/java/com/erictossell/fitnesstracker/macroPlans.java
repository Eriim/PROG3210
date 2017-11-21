package com.erictossell.fitnesstracker;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.TableRow;
import android.widget.TextView;

public class macroPlans extends AppCompatActivity {

    private Integer maintenanceCalories;
    private Integer hardCutCalories;
    private Integer cutCalories;
    private Integer cleanBulkCalories;
    private Integer hardBulkCalories;

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

        maintenanceCalories = getIntent().getIntExtra("maintenanceCalories", 0);

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

        hardCutCalories = maintenanceCalories - 1000;
        cutCalories = maintenanceCalories -500;
        cleanBulkCalories = maintenanceCalories + 500;
        hardBulkCalories = maintenanceCalories + 1000;

        hardCutCaloriesTextView.setText(hardCutCalories.toString());
        cutCaloriesTextView.setText(cutCalories.toString());
        maintenanceCaloriesTextView.setText(maintenanceCalories.toString());
        cleanBulkCaloriesTextView.setText(cleanBulkCalories.toString());
        hardBulkCaloriesTextView.setText(hardBulkCalories.toString());



    }
}
