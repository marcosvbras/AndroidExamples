package com.androidexamples.app.adapter;

import android.app.Activity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;

import com.androidexamples.app.R;
import com.androidexamples.app.domain.SimpleItem;

import java.util.List;

/**
 * Created by marcos on 14/12/2016.
 */

public class GridAdapter extends BaseAdapter {

    private Activity context;
    private List<SimpleItem> listItems;

    public GridAdapter(Activity context, List<SimpleItem> listItems) {
        this.context = context;
        this.listItems = listItems;
    }

    @Override
    public int getCount() {
        return listItems.size();
    }

    @Override
    public Object getItem(int position) {
        return listItems.get(position);
    }

    @Override
    public long getItemId(int i) {
        return i;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup viewGroup) {
        View view;
        SimpleItem item = listItems.get(position);
        MyViewHolder myViewHolder;

        if(convertView == null) {
            view = LayoutInflater.from(context).inflate(R.layout.grid_item, viewGroup, false);
            myViewHolder = new MyViewHolder(view);
            view.setTag(myViewHolder);
        } else {
            view = convertView;
            myViewHolder = (MyViewHolder) view.getTag();
        }

        myViewHolder.imageView.setImageResource(item.getImageResource());

        return view;
    }

    public class MyViewHolder {
        final ImageView imageView;

        public MyViewHolder(View view) {
            this.imageView = (ImageView)view.findViewById(R.id.imageView);
        }
    }
}
