package com.androidexamples.app.activity;

import android.support.v4.app.Fragment;
import android.support.v4.content.ContextCompat;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;

import com.androidexamples.app.R;
import com.androidexamples.app.adapter.TabsAdapter;
import com.androidexamples.app.fragments.DefaultFragment;
import com.androidexamples.app.view.SlidingTabLayout;

import java.util.ArrayList;
import java.util.List;

public class SlidingTabLayoutActivity extends AppCompatActivity {

    // Views
    private ViewPager viewPager;
    private SlidingTabLayout slidingTabLayout;

    private List<Fragment> listFragment;
    private List<String> listTitles;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sliding_tab_layout);
        LoadComponents();
    }

    private void LoadComponents() {
        setSupportActionBar((Toolbar)findViewById(R.id.top_toolbar));
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        viewPager = (ViewPager)findViewById(R.id.view_pager);
        slidingTabLayout = (SlidingTabLayout)findViewById(R.id.sliding_tab_layout);
        populateList();
    }

    private void populateList() {
        listFragment = new ArrayList<>();

        listFragment.add(DefaultFragment.newInstance(R.string.tab_1, android.R.color.white, android.R.color.black));
        listFragment.add(DefaultFragment.newInstance(R.string.tab_2, android.R.color.white, android.R.color.black));
        listFragment.add(DefaultFragment.newInstance(R.string.tab_3, android.R.color.white, android.R.color.black));
        listFragment.add(DefaultFragment.newInstance(R.string.tab_4, android.R.color.white, android.R.color.black));

        listTitles = new ArrayList<>();
        listTitles.add(getString(R.string.tab_1));
        listTitles.add(getString(R.string.tab_2));
        listTitles.add(getString(R.string.tab_3));
        listTitles.add(getString(R.string.tab_4));
    }

    @Override
    protected void onResume() {
        super.onResume();
        // Mantem duas abas (que não estão sendo exibidas) à mais em memória
        viewPager.setOffscreenPageLimit(2);
        viewPager.setAdapter(new TabsAdapter(getSupportFragmentManager(), this, listFragment, listTitles));
        slidingTabLayout.setBackgroundColor(ContextCompat.getColor(this, R.color.colorPrimary));
        slidingTabLayout.setDistributeEvenly(true);
        slidingTabLayout.setSelectedIndicatorColors(ContextCompat.getColor(this, R.color.colorAccent), ContextCompat.getColor(this, R.color.colorAccent));
        slidingTabLayout.setTitleColor(ContextCompat.getColor(this, android.R.color.white));
        slidingTabLayout.setViewPager(viewPager);
    }
}
