package com.example.marcos.androidexamples.app.adapter;

/**
 * Created by marcosvbras on 28/12/16.
 */

import android.app.Activity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.Filter;
import android.widget.Filterable;
import android.widget.TextView;

import com.example.marcos.androidexamples.R;
import com.example.marcos.androidexamples.app.interfaces.FilterObserverListener;

import java.util.ArrayList;
import java.util.List;

public class FilterableAdapter extends BaseAdapter implements Filterable {

    private Activity context;
    private List<String> listItems;
    private List<String> listItemsOriginal;
    private FilterObserverListener filterObserverListener;

    public FilterableAdapter(Activity context, List<String> listItems) {
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

    public void setFilterObserverListener(FilterObserverListener filterObserverListener) {
        this.filterObserverListener = filterObserverListener;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup viewGroup) {
        View view;
        MyViewHolder myViewHolder;
        String item = listItems.get(position);

        if(convertView == null) {
            view = LayoutInflater.from(context).inflate(R.layout.item_filter, viewGroup, false);
            myViewHolder = new MyViewHolder(view);
            view.setTag(myViewHolder);
        } else {
            view = convertView;
            myViewHolder = (MyViewHolder)view.getTag();
        }

        myViewHolder.textView.setText(item);

        return view;
    }

    @Override
    public Filter getFilter() {

        final Filter filter = new Filter() {
            // Método que executa a filtragem
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
                        if(item.toLowerCase().contains(query)) {
                            listItemsFiltered.add(item);
                        }
                    }

                    filterResults.count = listItemsFiltered.size();
                    filterResults.values = listItemsFiltered;
                }

                return filterResults;
            }

            // Método que exibe o resultado da busca
            @Override
            protected void publishResults(CharSequence charSequence, FilterResults filterResults) {
                listItems = (ArrayList<String>)filterResults.values;
                notifyDataSetInvalidated();

                if(filterObserverListener != null)
                    filterObserverListener.onFinishFiltering(filterResults.count);
            }
        };
        return filter;
    }

    public class MyViewHolder {
        final TextView textView;

        public MyViewHolder(View view) {
            textView = (TextView) view.findViewById(R.id.textView);
        }
    }
}
