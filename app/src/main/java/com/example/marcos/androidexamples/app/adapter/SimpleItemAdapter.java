package com.example.marcos.androidexamples.app.adapter;

import android.app.Activity;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.Filter;
import android.widget.Filterable;
import android.widget.TextView;
import android.widget.Toast;

import com.example.marcos.androidexamples.R;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by marcos on 11/12/2016.
 */

public class SimpleItemAdapter extends BaseAdapter implements Filterable {

    private Activity context;
    private List<String> listItems;
    private List<String> listItemsOriginal;

    public SimpleItemAdapter(Activity context, List<String> listItems) {
        this.context = context;
        this.listItemsOriginal = listItems;
        this.listItems = listItems;
    }

    @Override
    public int getCount() {
        return listItems.size();
    }

    @Override
    public Object getItem(int i) {
        return this.listItems.get(i);
    }

    @Override
    public long getItemId(int i) {
        return i;
    }

    @Override
    public View getView(int position, View view, ViewGroup viewGroup) {
        String item = listItems.get(position);;

        View layout;

        if(view == null)
            layout = context.getLayoutInflater().inflate(R.layout.simple_item, null);
        else
            layout = view;

        TextView textViewItem = (TextView) layout.findViewById(R.id.textViewItem);
        textViewItem.setText(item);

        return layout;
    }

    @Override
    public Filter getFilter() {

        final Filter filter = new Filter() {
            @Override
            protected FilterResults performFiltering(CharSequence query) {
                FilterResults filterResults = new FilterResults();

                if(query == null || query.length() == 0) {
                    filterResults.count = listItemsOriginal.size();
                    filterResults.values = listItemsOriginal;
                } else {
                    List<String> listItemsFiltered = new ArrayList<>();
                    query = query.toString().toLowerCase();

                    for(String item : listItemsOriginal){
                        if(item.toLowerCase().equals(query)) {
                            listItemsFiltered.add(item);
                        }
                    }

                    filterResults.count = listItemsFiltered.size();
                    filterResults.values = listItemsFiltered;
                }

                return filterResults;
            }

            @Override
            protected void publishResults(CharSequence charSequence, FilterResults filterResults) {
                if(filterResults.count == 0)
                    notifyDataSetInvalidated();
                else
                    listItems = (ArrayList<String>)filterResults.values;
                    notifyDataSetChanged();
            }
        };
        return filter;
    }
}
