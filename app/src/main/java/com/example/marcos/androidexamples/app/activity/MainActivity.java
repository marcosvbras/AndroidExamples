package com.example.marcos.androidexamples.app.activity;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.Toast;

import com.example.marcos.androidexamples.R;

public class MainActivity extends AppCompatActivity {

    private Toolbar toolbar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        LoadComponents();
    }

    private void LoadComponents() {
        toolbar = (Toolbar)findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        // Click events
        findViewById(R.id.button_gestures).setOnClickListener(onGesturesButtonClick());
        findViewById(R.id.button_views).setOnClickListener(onViewsButtonClick());
        findViewById(R.id.button_view_pager).setOnClickListener(onViewPagerButtonClick());
        findViewById(R.id.button_image_switcher).setOnClickListener(onImageSwitcherButtonClick());
        findViewById(R.id.button_toolbar).setOnClickListener(onToolbarButtonClick());
    }

    private View.OnClickListener onGesturesButtonClick() {
        return new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(getBaseContext(), GesturesActivity.class));
            }
        };
    }

    private View.OnClickListener onToolbarButtonClick() {
        return new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(getBaseContext(), ToolbarActivity.class));
            }
        };
    }

    private View.OnClickListener onImageSwitcherButtonClick() {
        return new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(getBaseContext(), ImageSwitcherActivity.class));
            }
        };
    }

    private View.OnClickListener onViewPagerButtonClick() {
        return new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(getBaseContext(), ViewPagerTypesActivity.class));
            }
        };
    }

    private View.OnClickListener onViewsButtonClick() {
        return new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(getBaseContext(), ViewsLayoutActivity.class));
            }
        };
    }

    private void showMessage(String message) {
        Toast.makeText(this, message, Toast.LENGTH_SHORT).show();
    }
}
