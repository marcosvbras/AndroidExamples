package com.androidexamples.app.interfaces;

import android.view.View;

/**
 * Created by marco on 25/12/2016.
 */

public interface RecyclerViewTouchListener {
    public void onItemClick(View view, int position);
    public void onLongItemClick(View view, int position);
}
