package com.androidexamples.app.activity;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.view.animation.ScaleAnimation;
import android.widget.ImageView;

import com.androidexamples.app.R;

public class ScaleActivity extends AppCompatActivity {

    // Views
    private ImageView imageView;

    // Another objects
    private ScaleAnimation animationRetract;
    private boolean dead = true;
    private int pivotXType;
    private int pivotYType;
    private float pivotXValue;
    private float pivotYValue;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_anim_examples);
        loadComponents();
    }

    private void loadComponents() {
        setSupportActionBar((Toolbar)findViewById(R.id.toolbar));
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        imageView = (ImageView)findViewById(R.id.imageView);
        imageView.setImageResource(R.drawable.heart);
        findViewById(R.id.button_animate_api).setOnClickListener(onAnimateApiButtonClick());
        findViewById(R.id.button_animate_xml).setOnClickListener(onAnimateXmlButtonClick());
    }

    @Override
    protected void onResume() {
        super.onResume();
        pivotXType = Animation.RELATIVE_TO_SELF;
        pivotXValue = 0.5f;
        pivotYType = Animation.RELATIVE_TO_SELF;
        pivotYValue = 0.5f;
        animationRetract = new ScaleAnimation(1.0f, 0.6f, 1.0f, 0.6f, pivotXType, pivotXValue, pivotYType, pivotYValue);
        animationRetract.setDuration(500);
        animationRetract.setRepeatCount(Animation.INFINITE);
        animationRetract.setRepeatMode(Animation.RESTART);
    }

    private View.OnClickListener onAnimateApiButtonClick() {
        return new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(dead)
                    imageView.startAnimation(animationRetract);
                else
                    imageView.getAnimation().cancel();

                dead = !dead;
            }
        };
    }

    private View.OnClickListener onAnimateXmlButtonClick() {
        return new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(dead)
                    imageView.startAnimation(AnimationUtils.loadAnimation(getBaseContext(), R.anim.scale_heart_beat));
                else
                    imageView.getAnimation().cancel();

                dead = !dead;
            }
        };
    }
}
