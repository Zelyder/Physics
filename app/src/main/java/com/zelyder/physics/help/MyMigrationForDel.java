package com.zelyder.physics.help;

import io.realm.DynamicRealm;
import io.realm.RealmMigration;
import io.realm.RealmSchema;

public class MyMigrationForDel implements RealmMigration {
    @Override
    public void migrate(DynamicRealm realm, long oldVersion, long newVersion) {
        RealmSchema schema = realm.getSchema();

        if(oldVersion == 0){
            schema.create("DelFormula")
                    .addField("parent", String.class)
                    .addField("name", String.class);
            oldVersion++;
        }

    }
}
