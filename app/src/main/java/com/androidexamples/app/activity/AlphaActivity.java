package com.androidexamples.app.activity;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.view.animation.AlphaAnimation;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;

import com.androidexamples.app.R;

public class AlphaActivity extends AppCompatActivity {

    // Another objects
    private AlphaAnimation fadeOut;
    private AlphaAnimation fadeIn;
    private AlphaAnimation alphaAnimation;
    private boolean isVisible = true;
    private Animation animation;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_anim_examples);
        loadComponents();
    }

    private void loadComponents() {
        setSupportActionBar((Toolbar)findViewById(R.id.top_toolbar));
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        findViewById(R.id.button_animate_xml).setOnClickListener(onAnimateXmlButtonClick());
        findViewById(R.id.button_animate_api).setOnClickListener(onAnimateApiButtonClick());
    }

    @Override
    protected void onResume() {
        super.onResume();
        fadeOut = new AlphaAnimation(1.0f, 0.0f); // Apaga a View de 100% de opacidade para 0
        fadeIn = new AlphaAnimation(0.0f, 1.0f); // Exibe a View de 0 para 100% de opacidade
    }

    private View.OnClickListener onAnimateApiButtonClick() {
        return new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                alphaAnimation = isVisible ? fadeOut : fadeIn;
                alphaAnimation.setDuration(2000);
                alphaAnimation.setFillAfter(true); // Mantem o efeito no final da animação
                findViewById(R.id.imageView).startAnimation(alphaAnimation);
                isVisible = !isVisible;
            }
        };
    }

    private View.OnClickListener onAnimateXmlButtonClick() {
        return new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                int animationId = isVisible ? android.R.anim.fade_out : android.R.anim.fade_in;
                animation = AnimationUtils.loadAnimation(getBaseContext(), animationId);
                animation.setDuration(2000);
                animation.setFillAfter(true); // Mantem o efeito no final da animação
                findViewById(R.id.imageView).startAnimation(animation);
                isVisible = !isVisible;
            }
        };
    }
}
