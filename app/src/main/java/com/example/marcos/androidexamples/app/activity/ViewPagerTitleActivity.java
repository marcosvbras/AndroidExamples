package com.example.marcos.androidexamples.app.activity;

import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;

import com.example.marcos.androidexamples.R;
import com.example.marcos.androidexamples.app.adapter.ImageAdapter;
import com.example.marcos.androidexamples.app.entity.Image;

import java.util.ArrayList;
import java.util.List;

public class ViewPagerTitleActivity extends AppCompatActivity {

    private ViewPager viewPager;
    private ImageAdapter adapter;
    private List<Image> listImages;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_view_pager_title);
        preencherLista();
        LoadComponents();
    }

    private void preencherLista() {
        listImages = new ArrayList<>();
        listImages.add(new Image(R.drawable.natdormer, "Natalie Dormer"));
        listImages.add(new Image(R.drawable.dany1, "Emilia Clarke"));
        listImages.add(new Image(R.drawable.ygritte, "Rose Leslie"));
    }

    private void LoadComponents() {
        setSupportActionBar((Toolbar)findViewById(R.id.toolbar));
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        viewPager = (ViewPager)findViewById(R.id.viewPager);
        adapter = new ImageAdapter(this, listImages);
        viewPager.setAdapter(adapter);
    }
}
