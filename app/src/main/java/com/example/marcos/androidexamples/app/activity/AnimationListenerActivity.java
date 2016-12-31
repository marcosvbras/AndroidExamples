package com.example.marcos.androidexamples.app.activity;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.view.animation.ScaleAnimation;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.Toast;

import com.example.marcos.androidexamples.R;

public class AnimationListenerActivity extends AppCompatActivity {

    private ScaleAnimation animationRetract;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_animation_listener);
        loadComponents();
    }

    private void loadComponents() {
        setSupportActionBar((Toolbar)findViewById(R.id.toolbar));
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        findViewById(R.id.button_animate).setOnClickListener(onAnimateButtonClick());
    }

    private View.OnClickListener onAnimateButtonClick() {
        return new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                animationRetract = (ScaleAnimation) AnimationUtils.loadAnimation(getBaseContext(), R.anim.scale_retract);
                animationRetract.setAnimationListener(onAnimation());
                findViewById(R.id.linearLayoutAnimated).startAnimation(animationRetract);
            }
        };
    }

    private Animation.AnimationListener onAnimation() {
        return new Animation.AnimationListener() {
            @Override
            public void onAnimationStart(Animation animation) {

            }

            @Override
            public void onAnimationEnd(Animation animation) {
                findViewById(R.id.linearLayoutMessage).setVisibility(View.VISIBLE);
            }

            @Override
            public void onAnimationRepeat(Animation animation) {

            }
        };
    }
}
