package com.erictossell.fitnesstracker;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.TableLayout;
import android.widget.TableRow;
import android.widget.TextView;

import com.erictossell.fitnesstracker.Database.AppDatabase;
import com.erictossell.fitnesstracker.Database.MacroPlan;
import com.erictossell.fitnesstracker.Database.SaveSharedPreference;

import java.text.DecimalFormat;

public class macroPlans extends AppCompatActivity {

    // variable declaration
    private AppDatabase database;
    private Double maintenanceCalories;
    private Double hardCutCalories;
    private Double cutCalories;
    private Double cleanBulkCalories;
    private Double hardBulkCalories;

    private MacroPlan dailyMacro;
    private String email;
    private long id;
    private Double weight;
    private String maintenanceMacros;
    private String hardCutMacros;
    private String cutMacros;
    private String cleanBulkMacros;
    private String hardBulkMacros;

    private TableLayout tableLayout;
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
    private TextView hardCutMacrosTextView;
    private TextView cutMacrosTextView;
    private TextView maintenanceMacrosTextView;
    private TextView cleanBulkMacrosTextView;
    private TextView hardBulkMacrosTextView;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_macro_plans);
        // initialize database
        database = AppDatabase.getDatabase(getApplicationContext());
        // get user email
        email = SaveSharedPreference.getUserName(getApplicationContext());
        // get user id with email
        id = database.userDao().getUserId(email);
        // need weight to determine macroNutrients
        weight = getIntent().getDoubleExtra("weight", 0.00);
        // calories as calculated on previous page
        maintenanceCalories = getIntent().getDoubleExtra("maintenanceCalories", 0.00);

        // initialize view elements
        tableLayout = (TableLayout) findViewById(R.id.tableLayout);
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
        hardCutMacrosTextView = (TextView) findViewById(R.id.hardCutMacros);
        cutMacrosTextView = (TextView) findViewById(R.id.cutMacros);
        maintenanceMacrosTextView = (TextView) findViewById(R.id.maintenanceMacros);
        cleanBulkMacrosTextView = (TextView) findViewById(R.id.cleanBulkMacros);
        hardBulkMacrosTextView = (TextView) findViewById(R.id.hardBulkMacros);


        // determine calories for each plan
        DecimalFormat formatter = new DecimalFormat("0");
        hardCutCalories = maintenanceCalories - 1000;
        cutCalories = maintenanceCalories - 500;
        cleanBulkCalories = maintenanceCalories + 500;
        hardBulkCalories = maintenanceCalories + 1000;
        Util util = new Util();
        // determine macros for first plan
        final MacroPlan hardCutMacroPlan = util.calculateMacro(id, hardCutCalories, weight, 1);
        hardCutMacros = formatter.format(hardCutMacroPlan.getProtein()) +"P/" + formatter.format(hardCutMacroPlan.getFat()) + "F/" + formatter.format(hardCutMacroPlan.getCarb()) + "C";
        hardCutMacrosTextView.setText(hardCutMacros);

        // determine macros for second plan
        final MacroPlan cutMacroPlan = util.calculateMacro(id, cutCalories, weight, 2);
        cutMacros = formatter.format(cutMacroPlan.getProtein()) +"P/" + formatter.format(cutMacroPlan.getFat()) + "F/" + formatter.format(cutMacroPlan.getCarb()) + "C";
        cutMacrosTextView.setText(cutMacros);

        // determine macros for third plan
        final MacroPlan maintenanceMacroPlan = util.calculateMacro(id, maintenanceCalories, weight, 3);
        maintenanceMacros = formatter.format(maintenanceMacroPlan.getProtein()) +"P/" + formatter.format(maintenanceMacroPlan.getFat()) + "F/" + formatter.format(maintenanceMacroPlan.getCarb()) + "C";
        maintenanceMacrosTextView.setText(maintenanceMacros);

        // determine macros for fourth plan
        final MacroPlan cleanBulkMacroPlan = util.calculateMacro(id, cleanBulkCalories, weight, 4);
        cleanBulkMacros = formatter.format(cleanBulkMacroPlan.getProtein()) +"P/" + formatter.format(cleanBulkMacroPlan.getFat()) + "F/" + formatter.format(cleanBulkMacroPlan.getCarb()) + "C";
        cleanBulkMacrosTextView.setText(cleanBulkMacros);

        // determine macros for fifth plan
        final MacroPlan hardBulkMacroPlan = util.calculateMacro(id, hardBulkCalories, weight, 5);
        hardBulkMacros = formatter.format(hardBulkMacroPlan.getProtein()) +"P/" + formatter.format(hardBulkMacroPlan.getFat()) + "F/" + formatter.format(hardBulkMacroPlan.getCarb()) + "C";
        hardBulkMacrosTextView.setText(hardBulkMacros);

        // set calorie textviews
        hardCutCaloriesTextView.setText(formatter.format(hardCutCalories));
        cutCaloriesTextView.setText(formatter.format(cutCalories));
        maintenanceCaloriesTextView.setText(formatter.format(maintenanceCalories));
        cleanBulkCaloriesTextView.setText(formatter.format(cleanBulkCalories));
        hardBulkCaloriesTextView.setText(formatter.format(hardBulkCalories));

        // on click listenener for first plan
        hardCut.setOnClickListener(new View.OnClickListener(){
            public void onClick(View v){
                database.macroPlanDao().addMacro(hardCutMacroPlan);
                Intent intent = new Intent(macroPlans.this, calorieTracker.class);
                intent.putExtra("calories", hardCutCalories);
                startActivity(intent);
            }
        });
        // on click listener for second plan
        cut.setOnClickListener(new View.OnClickListener(){
            public void onClick(View v){
                database.macroPlanDao().addMacro(cutMacroPlan);
                Intent intent = new Intent(macroPlans.this, calorieTracker.class);
                intent.putExtra("calories", cutCalories);
                startActivity(intent);
            }
        });
        // on click listener for third plan
        maintenance.setOnClickListener(new View.OnClickListener(){
            public void onClick(View v){
                database.macroPlanDao().addMacro(maintenanceMacroPlan);
                Intent intent = new Intent(macroPlans.this, calorieTracker.class);
                intent.putExtra("calories", maintenanceCalories);
                startActivity(intent);
            }
        });
        // on click listener for fourth plan
        cleanBulk.setOnClickListener(new View.OnClickListener(){
            public void onClick(View v){
                database.macroPlanDao().addMacro(cleanBulkMacroPlan);
                Intent intent = new Intent(macroPlans.this, calorieTracker.class);
                intent.putExtra("calories", cleanBulkCalories);
                startActivity(intent);
            }
        });
        // on click listener for fifth plan
        hardBulk.setOnClickListener(new View.OnClickListener(){
            public void onClick(View v){
                database.macroPlanDao().addMacro(hardBulkMacroPlan);
                Intent intent = new Intent(macroPlans.this, calorieTracker.class);
                intent.putExtra("calories", hardBulkCalories);
                startActivity(intent);
            }
        });




    }
}
