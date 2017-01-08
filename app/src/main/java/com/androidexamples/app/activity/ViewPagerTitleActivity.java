package com.androidexamples.app.activity;

import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;

import com.androidexamples.app.R;
import com.androidexamples.app.adapter.ImageAdapter;
import com.androidexamples.app.domain.SimpleItem;

import java.util.ArrayList;
import java.util.List;

public class ViewPagerTitleActivity extends AppCompatActivity {

    private ViewPager viewPager;
    private ImageAdapter adapter;
    private List<SimpleItem> listSimpleItems;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_view_pager_title);
        preencherLista();
        LoadComponents();
    }

    private void preencherLista() {
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

    private void LoadComponents() {
        setSupportActionBar((Toolbar)findViewById(R.id.top_toolbar));
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        viewPager = (ViewPager)findViewById(R.id.viewPager);
        adapter = new ImageAdapter(this, listSimpleItems);
        viewPager.setAdapter(adapter);
    }
}
