package com.erictossell.fitnesstracker;

import android.app.Application;
import android.content.Context;
import android.content.Intent;

import com.erictossell.fitnesstracker.Database.SaveSharedPreference;
import com.facebook.stetho.Stetho;

/**
 * Created by erict on 12/10/2017.
 */

public class StartUp extends Application {
    @Override
    public void onCreate(){
        super.onCreate();
        Context context = this;
        Stetho.InitializerBuilder initializerBuilder = Stetho.newInitializerBuilder(this);
        initializerBuilder.enableWebKitInspector(Stetho.defaultInspectorModulesProvider(this));
        initializerBuilder.enableDumpapp(Stetho.defaultDumperPluginsProvider(context));
        Stetho.Initializer initializer = initializerBuilder.build();
        Stetho.initialize(initializer);
        if(SaveSharedPreference.getUserName(context).length() == 0){
            Intent intent = new Intent(context, splashscreen.class);
            startActivity(intent);
        }
        else {
            Intent intent = new Intent(context, calorieTracker.class);
            startActivity(intent);
        }
    }
}
