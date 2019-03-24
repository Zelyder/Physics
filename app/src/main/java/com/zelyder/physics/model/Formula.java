package com.zelyder.physics.model;

public class Formula {
    private String title;
    private String formula;

    public Formula(String title, String formula) {
        this.title = title;
        this.formula = formula;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getFormula() {
        return formula;
    }

    public void setFormula(String formula) {
        this.formula = formula;
    }
}
