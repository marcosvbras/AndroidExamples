package com.example.marcos.androidexamples.app.interfaces;

import android.view.View;

/**
 * Created by marco on 25/12/2016.
 */

public interface RecyclerViewTouchListener {
    public void onItemClickListener(View view, int position);
    public void onLongItemClickListener(View view, int position);
    public void onDoubleTapListener(View view, int position);
}
