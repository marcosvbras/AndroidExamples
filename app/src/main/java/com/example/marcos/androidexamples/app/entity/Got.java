package com.example.marcos.androidexamples.app.entity;

/**
 * Created by marco on 25/12/2016.
 */

public class Got {

    private String name;
    private int drawableImage;

    public Got(String name, int drawableImage) {
        this.name = name;
        this.drawableImage = drawableImage;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getDrawableImage() {
        return drawableImage;
    }

    public void setDrawableImage(int drawableImage) {
        this.drawableImage = drawableImage;
    }

    @Override
    public String toString() {
        return name;
    }
}
