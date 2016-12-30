package com.example.marcos.androidexamples.app.activity;

import android.graphics.drawable.Animatable;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.ImageView;

import com.example.marcos.androidexamples.R;

public class AnimationDrawableActivity extends AppCompatActivity {

    // Another Objects
    private Animatable animatable;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_animation_drawable);
        loadComponents();
    }

    private void loadComponents() {
        setSupportActionBar((Toolbar)findViewById(R.id.toolbar));
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        animatable = (Animatable)((ImageView)findViewById(R.id.imageView)).getDrawable();
        animatable.start();

        findViewById(R.id.button_start).setOnClickListener(onStarButtonClick());
        findViewById(R.id.button_stop).setOnClickListener(onStopButtonClick());
    }

    private View.OnClickListener onStopButtonClick() {
        return new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(animatable.isRunning())
                    animatable.stop();
            }
        };
    }

    private View.OnClickListener onStarButtonClick() {
        return new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(!animatable.isRunning())
                    animatable.start();
            }
        };
    }
}
