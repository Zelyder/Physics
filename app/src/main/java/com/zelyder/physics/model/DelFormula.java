package com.zelyder.physics.model;

import io.realm.RealmObject;

public class DelFormula extends RealmObject {

    private String parent;
    private String name;

    public String getParent() {
        return parent;
    }

    public void setParent(String parent) {
        this.parent = parent;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}
