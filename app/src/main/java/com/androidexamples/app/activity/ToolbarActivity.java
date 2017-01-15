package com.androidexamples.app.activity;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.view.View;

import com.androidexamples.app.R;

public class ToolbarActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_toolbar);
        LoadComponents();
    }

    private void LoadComponents() {
        setSupportActionBar((Toolbar)findViewById(R.id.top_toolbar));
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        findViewById(R.id.button_search_view).setOnClickListener(onButtonClick());
        findViewById(R.id.button_stand_alone_toolbar).setOnClickListener(onButtonClick());
        findViewById(R.id.button_collapsing_toolbar).setOnClickListener(onButtonClick());
    }

    private View.OnClickListener onButtonClick() {
        return new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                switch (v.getId()) {
                    case R.id.button_search_view:
                        startActivity(new Intent(getBaseContext(), SearchViewActivity.class));
                        break;
                    case R.id.button_stand_alone_toolbar:
                        startActivity(new Intent(getBaseContext(), StandAloneToolbarActivity.class));
                        break;
                    case R.id.button_collapsing_toolbar:
                        startActivity(new Intent(getBaseContext(), CollapsingToolbarLayoutActivity.class));
                        break;
                }
            }
        };
    }
}
