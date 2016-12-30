package com.example.marcos.androidexamples.app.activity;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.view.View;

import com.example.marcos.androidexamples.R;

public class ViewPagerActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_view_pager);
        LoadComponents();
    }

    private void LoadComponents() {
        setSupportActionBar((Toolbar)findViewById(R.id.toolbar));
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        findViewById(R.id.button_view_pager).setOnClickListener(onSimpleViewPagerButtonClick());
        findViewById(R.id.button_viewpager_title).setOnClickListener(onViewPagerTitleButtonClick());
        findViewById(R.id.button_intro_slider).setOnClickListener(onIntroSliderButtonClick());
    }

    private View.OnClickListener onIntroSliderButtonClick() {
        return new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(getBaseContext(), IntroSliderActivity.class));
            }
        };
    }

    private View.OnClickListener onViewPagerTitleButtonClick() {
        return new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(getBaseContext(), ViewPagerTitleActivity.class));
            }
        };
    }

    private View.OnClickListener onSimpleViewPagerButtonClick() {
        return new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(getBaseContext(), SimpleViewPagerActivity.class));
            }
        };
    }
}
