package com.androidexamples.app.activity;

import android.support.design.widget.TabLayout;
import android.support.v4.app.Fragment;
import android.support.v4.content.ContextCompat;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;

import com.androidexamples.app.R;
import com.androidexamples.app.adapter.TabsAdapter;
import com.androidexamples.app.fragments.DefaultFragment;

import java.util.ArrayList;
import java.util.List;

public class ToolbarWithTabsActivity extends AppCompatActivity {

    // Views
    private ViewPager viewPager;
    private TabLayout tabLayout;

    private List<Fragment> listFragment;
    private List<String> listTitles;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_toolbar_with_tabs);
        LoadComponents();
    }

    private void LoadComponents() {
        setSupportActionBar((Toolbar)findViewById(R.id.top_toolbar));
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setElevation(0);
        viewPager = (ViewPager)findViewById(R.id.view_pager);
        tabLayout = (TabLayout)findViewById(R.id.tab_layout);
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
        tabLayout.setupWithViewPager(viewPager);
        tabLayout.setTabTextColors(ContextCompat.getColor(this, android.R.color.white), ContextCompat.getColor(this, android.R.color.white));
    }
}
