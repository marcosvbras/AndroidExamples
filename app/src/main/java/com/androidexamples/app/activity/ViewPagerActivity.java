package com.androidexamples.app.activity;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.view.View;

import com.androidexamples.app.R;
import com.androidexamples.app.view.SlidingTabLayout;

public class ViewPagerActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_view_pager);
        LoadComponents();
    }

    private void LoadComponents() {
        setSupportActionBar((Toolbar)findViewById(R.id.top_toolbar));
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        findViewById(R.id.button_simple_view_pager).setOnClickListener(onButtonClick());
        findViewById(R.id.button_view_pager_title).setOnClickListener(onButtonClick());
        findViewById(R.id.button_intro_slider).setOnClickListener(onButtonClick());
        findViewById(R.id.button_sliding_tab_layout).setOnClickListener(onButtonClick());
    }

    private View.OnClickListener onButtonClick() {
        return new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                switch (v.getId()) {
                    case R.id.button_intro_slider:
                        startActivity(new Intent(getBaseContext(), IntroSliderActivity.class));
                        break;
                    case R.id.button_simple_view_pager:
                        startActivity(new Intent(getBaseContext(), SimpleViewPagerActivity.class));
                        break;
                    case R.id.button_sliding_tab_layout:
                        startActivity(new Intent(getBaseContext(), SlidingTabLayoutActivity.class));
                        break;
                    case R.id.button_view_pager_title:
                        startActivity(new Intent(getBaseContext(), ViewPagerTitleActivity.class));
                        break;
                }
            }
        };
    }
}
