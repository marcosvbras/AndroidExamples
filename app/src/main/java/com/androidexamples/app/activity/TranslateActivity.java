package com.androidexamples.app.activity;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.view.animation.TranslateAnimation;

import com.androidexamples.app.R;

public class TranslateActivity extends AppCompatActivity {

    private TranslateAnimation translateAnimationUp;
    private TranslateAnimation translateAnimationDown;
    private Animation animation;
    private boolean translated = false;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_anim_examples);
        loadComponents();
    }

    @Override
    protected void onResume() {
        super.onResume();

        translateAnimationUp = new TranslateAnimation(Animation.RELATIVE_TO_SELF, 0.0f, Animation.RELATIVE_TO_SELF, 0.0f,
                Animation.RELATIVE_TO_SELF, 0.37f, Animation.RELATIVE_TO_SELF, 0.0f);
        translateAnimationUp.setDuration(1000);
        translateAnimationUp.setFillAfter(true);

        translateAnimationDown = new TranslateAnimation(Animation.RELATIVE_TO_SELF, 0.0f, Animation.RELATIVE_TO_SELF, 0.0f,
                Animation.RELATIVE_TO_SELF, 0.0f, Animation.RELATIVE_TO_SELF, 0.37f);
        translateAnimationDown.setDuration(1000);
        translateAnimationDown.setFillAfter(true);
    }

    private void loadComponents() {
        setSupportActionBar((Toolbar)findViewById(R.id.top_toolbar));
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        findViewById(R.id.button_animate_xml).setOnClickListener(onAnimateXmlButtonClick());
        findViewById(R.id.button_animate_api).setOnClickListener(onAnimateApiButtonClick());
    }

    private View.OnClickListener onAnimateApiButtonClick() {
        return new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                animation = translated ? translateAnimationUp : translateAnimationDown;
                findViewById(R.id.imageView).startAnimation(animation);
                translated = !translated;
            }
        };
    }

    private View.OnClickListener onAnimateXmlButtonClick() {
        return new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                int animationResource = translated ?  R.anim.translate_up : R.anim.translate_down;
                animation = AnimationUtils.loadAnimation(getBaseContext(), animationResource);
                findViewById(R.id.imageView).startAnimation(animation);
                translated = !translated;
            }
        };
    }
}
