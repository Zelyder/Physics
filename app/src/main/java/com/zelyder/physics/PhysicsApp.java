package com.zelyder.physics;

import android.app.Application;

import com.google.firebase.FirebaseApp;
import com.google.firebase.database.FirebaseDatabase;
import com.zelyder.physics.help.MyMigration;
import com.zelyder.physics.help.MyMigrationForDel;

import io.realm.Realm;
import io.realm.RealmConfiguration;


public class PhysicsApp extends Application{

    private static RealmConfiguration configForDelFormulas;
    private static RealmConfiguration configForFavorites;

    @Override
    public void onCreate() {
        super.onCreate();
        FirebaseApp.initializeApp(this);
        Realm.init(this);
        configForFavorites = new RealmConfiguration.Builder().name("favorites.realm")
                .schemaVersion(2)
                .migration(new MyMigration())
                .build();

        configForDelFormulas = new RealmConfiguration.Builder()
                .name("delFormulas.realm")
                .schemaVersion(1)
                .migration(new MyMigrationForDel())
                .build();

        //Realm.setDefaultConfiguration(config);

        FirebaseDatabase.getInstance().setPersistenceEnabled(true);
    }

    public static RealmConfiguration getConfigForDelFormulas(){
        return configForDelFormulas;
    }

    public static RealmConfiguration getConfigForFavorites(){
        return configForFavorites;
    }
}
