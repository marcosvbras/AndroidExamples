package com.androidexamples.app.activity;

import android.os.Bundle;

import com.androidexamples.app.R;

public class CollapsingToolbarLayoutActivity extends BaseActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_collapsing_toolbar_layout);
        loadComponents();
    }

    private void loadComponents() {
        setUpToolbar();
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
    }
}
