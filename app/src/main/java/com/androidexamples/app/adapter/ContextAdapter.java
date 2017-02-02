package com.androidexamples.app.adapter;

import android.content.Context;
import android.support.v4.content.ContextCompat;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.androidexamples.app.R;
import com.androidexamples.app.domain.ContextExample;

import java.util.List;

/**
 * Created by marcosvbras on 22/01/17.
 */

public class ContextAdapter extends RecyclerView.Adapter {

    private List<ContextExample> listItems;
    private Context context;

    public ContextAdapter(List<ContextExample> listItems, Context context) {
        this.listItems = listItems;
        this.context = context;
    }

    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View itemView = LayoutInflater.from(context).inflate(R.layout.item_context, parent, false);
        MyViewHolder myViewHolder = new MyViewHolder(itemView);
        return myViewHolder;
    }

    @Override
    public void onBindViewHolder(RecyclerView.ViewHolder viewHolder, int position) {
        ContextExample item = listItems.get(position);
        MyViewHolder myViewHolder = (MyViewHolder)viewHolder;
        myViewHolder.textView.setText(item.getName());

        if(item.isSelected())
            myViewHolder.container.setBackgroundColor(ContextCompat.getColor(context, R.color.colorAccent));
        else
            myViewHolder.container.setBackgroundColor(ContextCompat.getColor(context, android.R.color.white));
    }

    @Override
    public int getItemCount() {
        return listItems.size();
    }

    public ContextExample getItemAtPosition(int position) {
        return listItems.get(position);
    }

    public class MyViewHolder extends RecyclerView.ViewHolder {

        TextView textView;
        LinearLayout container;

        public MyViewHolder(View itemView) {
            super(itemView);
            textView = (TextView)itemView.findViewById(R.id.textView);
            container = (LinearLayout)itemView.findViewById(R.id.container);
        }
    }
}
