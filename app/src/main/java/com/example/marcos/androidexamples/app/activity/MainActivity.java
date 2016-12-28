package com.example.marcos.androidexamples.app.activity;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.view.View;

import com.example.marcos.androidexamples.R;

public class MainActivity extends AppCompatActivity {

    private Toolbar toolbar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        loadComponents();
    }

    private void loadComponents() {
        toolbar = (Toolbar)findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        // Click events
        findViewById(R.id.button_gestures).setOnClickListener(onGesturesButtonClick());
        findViewById(R.id.button_views).setOnClickListener(onAnotherViewsButtonClick());
        findViewById(R.id.button_simple_view_pager).setOnClickListener(onViewPagerButtonClick());
        findViewById(R.id.button_toolbar).setOnClickListener(onToolbarButtonClick());
        findViewById(R.id.button_fragments).setOnClickListener(onFragmentsButtonClick());
        findViewById(R.id.button_save_instance_state).setOnClickListener(onSaveInstanceStateButtonClick());
    }

    private View.OnClickListener onSaveInstanceStateButtonClick() {
        return new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(getBaseContext(), SaveInstanceStateActivity.class));
            }
        };
    }

    private View.OnClickListener onFragmentsButtonClick() {
        return new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(getBaseContext(), FragmentsActivity.class));
            }
        };
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

    private View.OnClickListener onViewPagerButtonClick() {
        return new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(getBaseContext(), ViewPagerActivity.class));
            }
        };
    }

    private View.OnClickListener onAnotherViewsButtonClick() {
        return new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(getBaseContext(), AnotherViewsActivity.class));
            }
        };
    }
}
