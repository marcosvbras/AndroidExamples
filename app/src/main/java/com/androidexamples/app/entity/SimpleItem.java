package com.androidexamples.app.entity;

/**
 * Created by marcos on 15/12/2016.
 */

public class SimpleItem {

    private int imageResource;
    private String name;
    private int colorResource;

    public SimpleItem(String name, int imageResource) {
        this.imageResource = imageResource;
        this.name = name;
    }

    public SimpleItem(String name, int imageResource, int colorResource) {
        this.imageResource = imageResource;
        this.name = name;
        this.colorResource = colorResource;
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

    public int getColorResource() {
        return colorResource;
    }

    public void setColorResource(int colorResource) {
        this.colorResource = colorResource;
    }

    @Override
    public String toString() {
        return name;
    }
}
