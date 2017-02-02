package com.androidexamples.app.domain;

/**
 * Created by marcosvbras on 29/01/17.
 */

public class ContextExample {

    private String name;
    private boolean selected;

    public ContextExample(String name, boolean selected) {
        this.name = name;
        this.selected = selected;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public boolean isSelected() {
        return selected;
    }

    public void setSelected(boolean selected) {
        this.selected = selected;
    }
}
