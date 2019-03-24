package com.zelyder.physics.help;

import io.realm.DynamicRealm;
import io.realm.RealmMigration;
import io.realm.RealmSchema;


public class MyMigration implements RealmMigration {
    @Override
    public void migrate(DynamicRealm realm, long oldVersion, long newVersion) {
        RealmSchema schema = realm.getSchema();

        if(oldVersion == 0){
            schema.create("Favor")
                    .addField("section", String.class)
                    .addField("name", String.class)
                    .addField("formula", String.class);
            oldVersion++;
        }
        if(oldVersion == 1){
//            schema.create("Favor")
//                    .addField("section", String.class)
//                    .addField("name", String.class)
//                    .addField("formula", String.class);

            schema.create("DelFormula")
                    .addField("parent", String.class)
                    .addField("name", String.class);
            oldVersion++;
        }
    }
}
