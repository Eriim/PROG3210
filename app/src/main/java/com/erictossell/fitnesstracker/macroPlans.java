package com.erictossell.fitnesstracker;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.TableLayout;
import android.widget.TableRow;
import android.widget.TextView;

import com.erictossell.fitnesstracker.Database.AppDatabase;
import com.erictossell.fitnesstracker.Database.Macro;
import com.erictossell.fitnesstracker.Database.SaveSharedPreference;

import java.text.DecimalFormat;
import java.text.NumberFormat;

public class macroPlans extends AppCompatActivity {

    private AppDatabase database;
    private Double maintenanceCalories;
    private Double hardCutCalories;
    private Double cutCalories;
    private Double cleanBulkCalories;
    private Double hardBulkCalories;

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
        database = AppDatabase.getDatabase(getApplicationContext());
        email = SaveSharedPreference.getUserName(getApplicationContext());
        id = database.userDao().getUserId(email);
        weight = getIntent().getDoubleExtra("weight", 0.00);
        maintenanceCalories = getIntent().getDoubleExtra("maintenanceCalories", 0.00);

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

        DecimalFormat formatter = new DecimalFormat("0");
        hardCutCalories = maintenanceCalories - 1000;
        cutCalories = maintenanceCalories - 500;
        cleanBulkCalories = maintenanceCalories + 500;
        hardBulkCalories = maintenanceCalories + 1000;
        Util util = new Util();
        final Macro hardCutMacro = util.calculateMacro(id, hardCutCalories, weight, 1);
        hardCutMacros = formatter.format(hardCutMacro.getProtein()) +"P/" + formatter.format(hardCutMacro.getFat()) + "F/" + formatter.format(hardCutMacro.getCarb()) + "C";
        hardCutMacrosTextView.setText(hardCutMacros);

        final Macro cutMacro = util.calculateMacro(id, cutCalories, weight, 2);
        cutMacros = formatter.format(cutMacro.getProtein()) +"P/" + formatter.format(cutMacro.getFat()) + "F/" + formatter.format(cutMacro.getCarb()) + "C";
        cutMacrosTextView.setText(cutMacros);

        final Macro maintenanceMacro = util.calculateMacro(id, maintenanceCalories, weight, 3);
        maintenanceMacros = formatter.format(maintenanceMacro.getProtein()) +"P/" + formatter.format(maintenanceMacro.getFat()) + "F/" + formatter.format(maintenanceMacro.getCarb()) + "C";
        maintenanceMacrosTextView.setText(maintenanceMacros);

        final Macro cleanBulkMacro = util.calculateMacro(id, cleanBulkCalories, weight, 4);
        cleanBulkMacros = formatter.format(cleanBulkMacro.getProtein()) +"P/" + formatter.format(cleanBulkMacro.getFat()) + "F/" + formatter.format(cleanBulkMacro.getCarb()) + "C";
        cleanBulkMacrosTextView.setText(cleanBulkMacros);

        final Macro hardBulkMacro = util.calculateMacro(id, hardBulkCalories, weight, 5);
        hardBulkMacros = formatter.format(hardBulkMacro.getProtein()) +"P/" + formatter.format(hardBulkMacro.getFat()) + "F/" + formatter.format(hardBulkMacro.getCarb()) + "C";
        hardBulkMacrosTextView.setText(hardBulkMacros);

        Macro macro = util.calculateMacro(id, hardCutCalories, weight, 1);
        hardCutMacros = formatter.format(macro.getProtein()) +"P/" + formatter.format(macro.getFat()) + "F/" + formatter.format(macro.getCarb()) + "C";
        hardCutMacrosTextView.setText(hardCutMacros);
        hardCutCaloriesTextView.setText(formatter.format(hardCutCalories));
        cutCaloriesTextView.setText(formatter.format(cutCalories));
        maintenanceCaloriesTextView.setText(formatter.format(maintenanceCalories));
        cleanBulkCaloriesTextView.setText(formatter.format(cleanBulkCalories));
        hardBulkCaloriesTextView.setText(formatter.format(hardBulkCalories));

        hardCut.setOnClickListener(new View.OnClickListener(){
            public void onClick(View v){
                database.macroDao().addMacro(hardCutMacro);
                Intent intent = new Intent(macroPlans.this, calorieTracker.class);
                intent.putExtra("calories", hardCutCalories);
                startActivity(intent);
            }
        });
        cut.setOnClickListener(new View.OnClickListener(){
            public void onClick(View v){
                database.macroDao().addMacro(cutMacro);
                Intent intent = new Intent(macroPlans.this, calorieTracker.class);
                intent.putExtra("calories", cutCalories);
                startActivity(intent);
            }
        });
        maintenance.setOnClickListener(new View.OnClickListener(){
            public void onClick(View v){
                database.macroDao().addMacro(maintenanceMacro);
                Intent intent = new Intent(macroPlans.this, calorieTracker.class);
                intent.putExtra("calories", maintenanceCalories);
                startActivity(intent);
            }
        });
        cleanBulk.setOnClickListener(new View.OnClickListener(){
            public void onClick(View v){
                database.macroDao().addMacro(cleanBulkMacro);
                Intent intent = new Intent(macroPlans.this, calorieTracker.class);
                intent.putExtra("calories", cleanBulkCalories);
                startActivity(intent);
            }
        });
        hardBulk.setOnClickListener(new View.OnClickListener(){
            public void onClick(View v){
                database.macroDao().addMacro(hardBulkMacro);
                Intent intent = new Intent(macroPlans.this, calorieTracker.class);
                intent.putExtra("calories", hardBulkCalories);
                startActivity(intent);
            }
        });



    }
}
