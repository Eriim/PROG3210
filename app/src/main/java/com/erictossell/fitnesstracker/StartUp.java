package com.erictossell.fitnesstracker;

import android.app.Application;
import android.content.Context;
import android.content.Intent;
import android.icu.text.DateFormat;

import java.text.ParseException;
import java.text.SimpleDateFormat;

import com.erictossell.fitnesstracker.Database.SaveSharedPreference;
import com.facebook.stetho.Stetho;

import java.util.Date;
import java.util.Locale;

/**
 * Created by erict on 12/10/2017.
 */
// runs on application start
public class StartUp extends Application {
    @Override
    public void onCreate(){
        super.onCreate();
        Context context = this;
        // initializes external view to database via Chrome://inspect
        Stetho.InitializerBuilder initializerBuilder = Stetho.newInitializerBuilder(this);
        initializerBuilder.enableWebKitInspector(Stetho.defaultInspectorModulesProvider(this));
        initializerBuilder.enableDumpapp(Stetho.defaultDumperPluginsProvider(context));
        Stetho.Initializer initializer = initializerBuilder.build();
        Stetho.initialize(initializer);
        // if they haven't logged in before display calorie tracker page
        if(SaveSharedPreference.getUserName(context).length() == 0){
            // reset daily macros once a day
            SimpleDateFormat sdf = new SimpleDateFormat("yyyy/MM/dd HH:mm", Locale.CANADA);
            String lastReset = SaveSharedPreference.getLastResetDate(context);
                try {
                    Date last = sdf.parse(lastReset);
                } catch (ParseException e) {
                    e.printStackTrace();
                }
                Date dayOfYear = new Date();



            Intent intent = new Intent(context, splashscreen.class);
            startActivity(intent);
        }
        // if they have logged in before
        else {
            Intent intent = new Intent(context, calorieTracker.class);
            startActivity(intent);
        }
    }
}
