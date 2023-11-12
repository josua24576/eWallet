package com.nanvir.templateapps.common.app;

import androidx.annotation.NonNull;

import java.util.Objects;

import io.realm.DynamicRealm;
import io.realm.FieldAttribute;
import io.realm.RealmMigration;
import io.realm.RealmSchema;

public class AppMigration implements RealmMigration {
    @Override
    public void migrate(@NonNull DynamicRealm realm, long oldVersion, long newVersion) {
        RealmSchema schema = realm.getSchema();

        if (oldVersion < 2) {

            oldVersion++;
        }
    }
}
