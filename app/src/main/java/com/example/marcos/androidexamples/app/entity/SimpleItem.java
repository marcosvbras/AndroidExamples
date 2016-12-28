package com.example.marcos.androidexamples.app.entity;

/**
 * Created by marcos on 15/12/2016.
 */

public class SimpleItem {

    private int imageResource;
    private String name;

    public SimpleItem(String name, int imageResource) {
        this.imageResource = imageResource;
        this.name = name;
    }

    public int getImageResource() {
        return imageResource;
    }
    public void setImageResource(int imageResource) {
        this.imageResource = imageResource;
    }

    public String getName() {
        return name;
    }
    public void setName(String name) {
        this.name = name;
    }

    @Override
    public String toString() {
        return name;
    }
}
