package com.androidexamples.app.util;

import android.content.Context;
import android.view.View;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.view.animation.ScaleAnimation;

/**
 * Created by marcos on 25/12/2016.
 */

public class Animations {

    public static void setSlideInLeftAnimation(View viewToAnimate, int currentPosition, int lastLoadedViewPosition, boolean alwaysAnimate, Context context) {
        if(alwaysAnimate || currentPosition > lastLoadedViewPosition) {
            Animation animation = AnimationUtils.loadAnimation(context, android.R.anim.slide_in_left);
            viewToAnimate.startAnimation(animation);
        }
    }

    public static void setSlideOutRightAnimation(View viewToAnimate, int currentPosition, int lastLoadedViewPosition, boolean alwaysAnimate, Context context) {
        if(alwaysAnimate || currentPosition > lastLoadedViewPosition) {
            Animation animation = AnimationUtils.loadAnimation(context, android.R.anim.slide_out_right);
            viewToAnimate.startAnimation(animation);
        }
    }

    public static void setFadeInAnimation(View viewToAnimate, int currentPosition, int lastLoadedViewPosition, boolean alwaysAnimate, Context context) {
        if(alwaysAnimate || currentPosition > lastLoadedViewPosition) {
            Animation animation = AnimationUtils.loadAnimation(context, android.R.anim.fade_in);
            animation.setDuration(Constants.FADE_DURATION);
            viewToAnimate.startAnimation(animation);
        }
    }

    public static void setFadeOutAnimation(View viewToAnimate, int currentPosition, int lastLoadedViewPosition, boolean alwaysAnimate, Context context) {
        if(alwaysAnimate || currentPosition > lastLoadedViewPosition) {
            Animation animation = AnimationUtils.loadAnimation(context, android.R.anim.fade_out);
            animation.setDuration(Constants.FADE_DURATION);
            viewToAnimate.startAnimation(animation);
        }
    }

    public static void setScaleAnimation(View viewToAnimate, int currentPosition, int lastLoadedViewPosition, boolean alwaysAnimate, Context context) {
        if(alwaysAnimate || currentPosition > lastLoadedViewPosition) {
            ScaleAnimation scaleAnimation = new ScaleAnimation(0.0f, 1.0f, 0.0f, 1.0f, Animation.RELATIVE_TO_SELF, 0.5f, Animation.RELATIVE_TO_SELF, 0.5f);
            scaleAnimation.setDuration(Constants.SCALE_FADE_DURATION);
            viewToAnimate.startAnimation(scaleAnimation);
        }
    }
}
