package com.nanvir.templateapps.common.realm;

import io.realm.RealmObject;
import io.realm.annotations.PrimaryKey;

public class MstItemStore extends RealmObject {

    @PrimaryKey
    private int id;

    public MstItemStore() {
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }
}
