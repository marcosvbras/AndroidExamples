package com.androidexamples.app.activity;

import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;

import com.androidexamples.app.R;
import com.androidexamples.app.adapter.ImageAdapter;
import com.androidexamples.app.entity.SimpleItem;

import java.util.ArrayList;
import java.util.List;

public class SimpleViewPagerActivity extends AppCompatActivity {

    private ViewPager viewPager;
    private List<SimpleItem> listSimpleItems;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_simple_view_pager);
        populateList();
        loadComponents();
    }

    private void populateList() {
        listSimpleItems = new ArrayList<>();
        listSimpleItems.add(new SimpleItem("Akami ga Kill", R.drawable.akami_ga_kill));
        listSimpleItems.add(new SimpleItem("Angel Beats!", R.drawable.angel_beats));
        listSimpleItems.add(new SimpleItem("Attack On Titan", R.drawable.attack_on_titan));
        listSimpleItems.add(new SimpleItem("Btooom!", R.drawable.btooom));
        listSimpleItems.add(new SimpleItem("No Game, No Life!", R.drawable.no_game_no_life));
        listSimpleItems.add(new SimpleItem("Noragami", R.drawable.noragami));
        listSimpleItems.add(new SimpleItem("Tokyo Ghoul", R.drawable.tghoul));
        listSimpleItems.add(new SimpleItem("Tokyo Ghoul Root A", R.drawable.tokyo));
        listSimpleItems.add(new SimpleItem("To Love-Ru", R.drawable.toloveru));
    }

    private void loadComponents() {
        setSupportActionBar((Toolbar)findViewById(R.id.toolbar));
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        viewPager = (ViewPager)findViewById(R.id.viewPager);
        viewPager.setAdapter(new ImageAdapter(this, listSimpleItems));
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
