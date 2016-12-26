package com.example.marcos.androidexamples.app.util;

import android.support.v7.widget.RecyclerView;

/**
 * Created by marcos on 25/12/2016.
 */

public class RecyclerSettings {

    private boolean alwaysAnimate;
    private int animationConstant;
    private int positionForAnimationSpinner;
    private int positionForLayoutSpinner;
    private RecyclerView.LayoutManager layoutManager;

    public boolean isAlwaysAnimate() {
        return alwaysAnimate;
    }

    public void setAlwaysAnimate(boolean alwaysAnimate) {
        this.alwaysAnimate = alwaysAnimate;
    }

    public int getAnimationConstant() {
        return animationConstant;
    }

    public void setAnimationConstant(int animation) {
        this.animationConstant = animation;
    }

    public RecyclerView.LayoutManager getLayoutManager() {
        return layoutManager;
    }

    public void setLayoutManager(RecyclerView.LayoutManager layoutManager) {
        this.layoutManager = layoutManager;
    }

    public int getPositionForAnimationSpinner() {
        return positionForAnimationSpinner;
    }

    public void setPositionForAnimationSpinner(int positionForAnimationSpinner) {
        this.positionForAnimationSpinner = positionForAnimationSpinner;
    }

    public int getPositionForLayoutSpinner() {
        return positionForLayoutSpinner;
    }

    public void setPositionForLayoutSpinner(int positionForLayoutSpinner) {
        this.positionForLayoutSpinner = positionForLayoutSpinner;
    }
}
