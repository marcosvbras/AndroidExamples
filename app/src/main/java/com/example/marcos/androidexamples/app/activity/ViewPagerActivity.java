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

public class ViewPagerActivity extends AppCompatActivity {

    private ViewPager viewPager;
    private List<Image> listImages;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_view_pager);
        preencherLista();
        LoadComponents();
    }

    private void preencherLista() {
        listImages = new ArrayList<>();
        listImages.add(new Image(R.drawable.natdormer, "Natalie Dormer"));
        listImages.add(new Image(R.drawable.dany1, "Emilia Clarke"));
        listImages.add(new Image(R.drawable.dany2, "Emilia Clarke"));
        listImages.add(new Image(R.drawable.ygritte, "Rose Leslie"));
    }

    private void LoadComponents() {
        setSupportActionBar((Toolbar)findViewById(R.id.toolbar));
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        viewPager = (ViewPager)findViewById(R.id.viewPager);
        viewPager.setAdapter(new ImageAdapter(this, listImages));
        viewPager.setOnPageChangeListener(onPageChange());
    }

    private ViewPager.OnPageChangeListener onPageChange() {
        return new ViewPager.OnPageChangeListener() {
            @Override
            public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {

            }

            @Override
            public void onPageSelected(int position) {

            }

            @Override
            public void onPageScrollStateChanged(int state) {

            }
        };
    }
}
