package com.androidexamples.app.adapter;

import android.app.Activity;
import android.support.v4.view.PagerAdapter;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import com.androidexamples.app.R;
import com.androidexamples.app.domain.SimpleItem;

import java.util.List;

/**
 * Created by marcos on 14/12/2016.
 */

public class ImageAdapter extends PagerAdapter {

    private Activity context;
    private List<SimpleItem> listSimpleItems;

    public ImageAdapter(Activity context, List<SimpleItem> listSimpleItems) {
        this.context = context;
        this.listSimpleItems = listSimpleItems;
    }

    @Override
    public int getCount() {
        return listSimpleItems != null ? listSimpleItems.size() : 0;
    }

    @Override
    public Object instantiateItem(ViewGroup container, int position) {
        SimpleItem simpleItemItem = listSimpleItems.get(position);
        View view = context.getLayoutInflater().inflate(R.layout.grid_item, container, false);
        ((ImageView)view.findViewById(R.id.imageView)).setImageResource(simpleItemItem.getImageResource());
        container.addView(view);

        return view;
    }

    @Override
    public CharSequence getPageTitle(int position) {
        return listSimpleItems.get(position).getName();
    }

    @Override
    public void destroyItem(ViewGroup container, int position, Object view) { // Remove view from container
        container.removeView((View)view);
    }

    @Override
    public boolean isViewFromObject(View view, Object object) {
        // Determina se a view informada é igual ao Object retornado pelo instantiateItem
        return view == object;
    }
}
