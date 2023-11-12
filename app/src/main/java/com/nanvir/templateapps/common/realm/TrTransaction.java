package com.nanvir.templateapps.common.realm;

import io.realm.RealmObject;
import io.realm.annotations.PrimaryKey;

public class TrTransaction extends RealmObject {

    @PrimaryKey
    private int id;

    public TrTransaction() {
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }
}
