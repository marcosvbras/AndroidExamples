package com.androidexamples.app.activity;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.view.animation.AnimationSet;

import com.androidexamples.app.R;

public class AnimationsActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_animations);
        loadComponents();
    }

    private void loadComponents() {
        setSupportActionBar((Toolbar)findViewById(R.id.toolbar));
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        findViewById(R.id.button_view_animations).setOnClickListener(onButtonClick());
        findViewById(R.id.button_activity_animations).setOnClickListener(onButtonClick());
        findViewById(R.id.button_reveal_effect).setOnClickListener(onButtonClick());
    }

    private View.OnClickListener onButtonClick() {
        return new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                switch (v.getId()) {
                    case R.id.button_view_animations:
                        startActivity(new Intent(getBaseContext(), ViewAnimationsActivity.class));
                        break;
                    case R.id.button_activity_animations:
                        startActivity(new Intent(getBaseContext(), ActivityAnimationsActivity.class));
                        break;
                    case R.id.button_reveal_effect:
                        startActivity(new Intent(getBaseContext(), RevealEffectActivity.class));
                        break;
                }
            }
        };
    }
}
