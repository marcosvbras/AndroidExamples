package com.example.marcos.androidexamples.app.adapter;

import android.app.Activity;
import android.support.v4.view.PagerAdapter;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import com.example.marcos.androidexamples.R;
import com.example.marcos.androidexamples.app.entity.Image;

import java.util.List;

/**
 * Created by marcos on 14/12/2016.
 */

public class ImageAdapter extends PagerAdapter {

    private Activity context;
    private List<Image> listImages;

    public ImageAdapter(Activity context, List<Image> listImages) {
        this.context = context;
        this.listImages = listImages;
    }

    @Override
    public int getCount() {
        return listImages != null ? listImages.size() : 0;
    }

    @Override
    public Object instantiateItem(ViewGroup container, int position) {
        Image imageItem = listImages.get(position);
        View view = context.getLayoutInflater().inflate(R.layout.got_item, container, false);
        ((ImageView)view.findViewById(R.id.imageView)).setImageResource(imageItem.getImageResource());
        container.addView(view);

        return view;
    }

    @Override
    public CharSequence getPageTitle(int position) {
        return listImages.get(position).getName();
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
