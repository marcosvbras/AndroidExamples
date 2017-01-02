package com.androidexamples.app.activity;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.view.animation.AlphaAnimation;
import android.view.animation.Animation;
import android.view.animation.AnimationSet;
import android.view.animation.AnimationUtils;
import android.view.animation.TranslateAnimation;

import com.androidexamples.app.R;

public class AnimationSetActivity extends AppCompatActivity {

    private AnimationSet animationSet;
    private boolean animationStarted = false;
    private TranslateAnimation translateAnimationUp;
    private TranslateAnimation translateAnimationDown;
    private AlphaAnimation alphaAnimationFadeOut;
    private AlphaAnimation alphaAnimationFadeIn;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_anim_examples);
        loadComponents();
    }

    private void loadComponents() {
        setSupportActionBar((Toolbar)findViewById(R.id.toolbar));
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        findViewById(R.id.button_animate_api).setOnClickListener(onAnimateApiButtonClick());
        findViewById(R.id.button_animate_xml).setOnClickListener(onAnimateXmlButtonClick());
    }

    private View.OnClickListener onAnimateXmlButtonClick() {
        return new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                animationSet = getAnimationSetXml();
                animationSet.setFillAfter(true);
                findViewById(R.id.imageView).startAnimation(animationSet);
                animationStarted = !animationStarted;
            }
        };
    }

    private View.OnClickListener onAnimateApiButtonClick() {
        return new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                animationSet = new AnimationSet(true);
                animationSet.setFillAfter(true);
                animationSet.addAnimation(getTranslateAnimationApi());
                animationSet.addAnimation(getAlphaAnimationApi());
                findViewById(R.id.imageView).startAnimation(animationSet);
                animationStarted = !animationStarted;
            }
        };
    }

    @Override
    protected void onResume() {
        super.onResume();
        translateAnimationUp = new TranslateAnimation(Animation.RELATIVE_TO_SELF, 0.0f, Animation.RELATIVE_TO_SELF, 0.0f,
                Animation.RELATIVE_TO_SELF, 0.37f, Animation.RELATIVE_TO_SELF, 0.0f);
        translateAnimationUp.setDuration(2000);
        translateAnimationUp.setFillAfter(true); // Mantem o efeito no final da animação

        translateAnimationDown = new TranslateAnimation(Animation.RELATIVE_TO_SELF, 0.0f, Animation.RELATIVE_TO_SELF, 0.0f,
                Animation.RELATIVE_TO_SELF, 0.0f, Animation.RELATIVE_TO_SELF, 0.37f);
        translateAnimationDown.setDuration(2000);
        translateAnimationDown.setFillAfter(true); // Mantem o efeito no final da animação

        alphaAnimationFadeOut = new AlphaAnimation(1.0f, 0.0f); // Apaga a View de 100% de opacidade para 0
        alphaAnimationFadeOut.setDuration(2000);
        alphaAnimationFadeOut.setFillAfter(true); // Mantem o efeito no final da animação
        alphaAnimationFadeIn = new AlphaAnimation(0.0f, 1.0f); // Exibe a View de 0 para 100% de opacidade
        alphaAnimationFadeIn.setDuration(2000);
        alphaAnimationFadeIn.setFillAfter(true); // Mantem o efeito no final da animação
    }

    private Animation getTranslateAnimationApi() {
        return animationStarted ? translateAnimationUp : translateAnimationDown;
    }

    private Animation getAlphaAnimationApi() {
         return animationStarted ? alphaAnimationFadeIn : alphaAnimationFadeOut;
    }

    private AnimationSet getAnimationSetXml() {
        int animationResource = animationStarted ? R.anim.set_translate_up_fade_in : R.anim.set_translate_down_fade_out;
        return (AnimationSet)AnimationUtils.loadAnimation(this, animationResource);
    }
}
