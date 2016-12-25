package com.example.marcos.androidexamples.app.entity;

/**
 * Created by marcos on 25/12/2016.
 */

public class BlueHair {

    private int id;
    private int drawableId;

    public BlueHair(int id, int drawableId) {
        this.id = id;
        this.drawableId = drawableId;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getDrawableId() {
        return drawableId;
    }

    public void setDrawableId(int drawableId) {
        this.drawableId = drawableId;
    }
}
