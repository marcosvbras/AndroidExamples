package com.androidexamples.app.adapter;

import android.content.Context;
import android.support.v4.view.PagerAdapter;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import java.util.List;

/**
 * Created by marco on 19/01/2017.
 */

public class IntroAdapter extends PagerAdapter {

    private Context context;
    private List<Integer> listLayouts;

    public IntroAdapter(Context context, List<Integer> listLayouts) {
        this.context = context;
        this.listLayouts = listLayouts;
    }

    @Override
    public Object instantiateItem(ViewGroup container, int position) {
        View view = LayoutInflater.from(context).inflate(listLayouts.get(position), container, false);
        container.addView(view);
        return view;
    }

    @Override
    public int getCount() {
        return listLayouts.size();
    }

    @Override
    public boolean isViewFromObject(View view, Object object) {
        return view == object;
    }

    @Override
    public void destroyItem(ViewGroup container, int position, Object object) {
        View view = (View)object;
        container.removeView(view);
    }
}
