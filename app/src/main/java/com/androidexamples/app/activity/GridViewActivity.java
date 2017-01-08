package com.androidexamples.app.activity;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.widget.GridView;

import com.androidexamples.app.R;
import com.androidexamples.app.adapter.GridAdapter;
import com.androidexamples.app.domain.SimpleItem;

import java.util.ArrayList;
import java.util.List;

public class GridViewActivity extends AppCompatActivity {

    private GridView gridView;
    private GridAdapter adapter;
    private List<SimpleItem> listItems;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_grid_view);
        loadComponents();
    }

    private void loadComponents() {
        setSupportActionBar((Toolbar)findViewById(R.id.top_toolbar));
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        gridView = (GridView)findViewById(R.id.gridView);
    }

    @Override
    protected void onResume() {
        super.onResume();
        populateList();
        adapter = new GridAdapter(this, listItems);
        gridView.setAdapter(adapter);
    }

    private void populateList() {
        listItems = new ArrayList<>();
        listItems.add(new SimpleItem("Arryn", R.drawable.arryn));
        listItems.add(new SimpleItem("Baratheon", R.drawable.baratheon));
        listItems.add(new SimpleItem("Greyjoy", R.drawable.greyjoy));
        listItems.add(new SimpleItem("Lannister", R.drawable.lannister));
        listItems.add(new SimpleItem("Martell", R.drawable.martell));
        listItems.add(new SimpleItem("Stark", R.drawable.stark));
        listItems.add(new SimpleItem("Targaryan", R.drawable.targaryen));
        listItems.add(new SimpleItem("Tully", R.drawable.tully));
        listItems.add(new SimpleItem("Tyrell", R.drawable.tyrell));
    }
}
