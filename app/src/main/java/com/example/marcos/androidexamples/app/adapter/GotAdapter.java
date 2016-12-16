package com.example.marcos.androidexamples.app.adapter;

import android.app.Activity;
import android.content.Context;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;

import com.example.marcos.androidexamples.R;

/**
 * Created by marco on 14/12/2016.
 */

public class GotAdapter extends BaseAdapter {

    private int[] images;
    private Activity context;

    public GotAdapter(Activity context, int[] images) {
        this.context = context;
        this.images = images;
    }

    @Override
    public int getCount() {
        return images.length;
    }

    @Override
    public Object getItem(int position) {
        return images[position];
    }

    @Override
    public long getItemId(int i) {
        return i;
    }

    @Override
    public View getView(int position, View view, ViewGroup viewGroup) {
        int item = images[position];
        View layout;

        if(view == null)
            layout = context.getLayoutInflater().inflate(R.layout.got_item, viewGroup, false);
        else
            layout = view;

        ((ImageView)layout.findViewById(R.id.imageView)).setImageResource(item);

        return layout;
    }
}
