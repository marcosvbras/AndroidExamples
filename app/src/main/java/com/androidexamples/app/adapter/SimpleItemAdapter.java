package com.androidexamples.app.adapter;

import android.app.Activity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.androidexamples.app.R;
import com.androidexamples.app.entity.SimpleItem;

import java.util.List;

/**
 * Created by marcos on 14/12/2016.
 */

public class SimpleItemAdapter extends BaseAdapter {

    private Activity context;
    private List<SimpleItem> listItems;

    public SimpleItemAdapter(Activity context, List<SimpleItem> listItems) {
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
            view = LayoutInflater.from(context).inflate(R.layout.simple_item, viewGroup, false);
            myViewHolder = new MyViewHolder(view);
            view.setTag(myViewHolder);
        } else {
            view = convertView;
            myViewHolder = (MyViewHolder) view.getTag();
        }

        myViewHolder.imageView.setImageResource(item.getImageResource());
        myViewHolder.textView.setText(item.getName());

        return view;
    }

    public class MyViewHolder {
        final TextView textView;
        final ImageView imageView;

        public MyViewHolder(View view) {
            this.textView = (TextView)view.findViewById(R.id.textView);
            this.imageView = (ImageView)view.findViewById(R.id.imageView);
        }
    }
}
