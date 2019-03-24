package com.zelyder.physics.model;


import io.realm.RealmObject;

public class Favorite extends RealmObject{
   // @PrimaryKey
    //private long id;
    private String section;
    private String name;
    private String formula;

    public String getSection() {
        return section;
    }

    public void setSection(String section) {
        this.section = section;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getFormula() {
        return formula;
    }

    public void setFormula(String formula) {
        this.formula = formula;
    }
}
