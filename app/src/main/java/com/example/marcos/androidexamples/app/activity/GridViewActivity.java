package com.example.marcos.androidexamples.app.activity;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.widget.GridView;

import com.example.marcos.androidexamples.R;
import com.example.marcos.androidexamples.app.adapter.GotAdapter;

public class GridViewActivity extends AppCompatActivity {

    private GridView gridView;
    private GotAdapter adapter;
    private int[] images = {R.drawable.arryn, R.drawable.baratheon, R.drawable.greyjoy, R.drawable.lannister,
            R.drawable.martell, R.drawable.stark, R.drawable.targaryen, R.drawable.tully, R.drawable.tyrell};

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_grid_view);
        LoadComponents();
    }

    private void LoadComponents() {
        setSupportActionBar((Toolbar)findViewById(R.id.toolbar));
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        adapter = new GotAdapter(this, images);
        gridView = (GridView)findViewById(R.id.gridView);
        gridView.setAdapter(adapter);
    }
}
