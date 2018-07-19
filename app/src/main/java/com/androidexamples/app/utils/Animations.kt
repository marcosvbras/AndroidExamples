package com.androidexamples.app.utils

import android.content.Context
import android.view.View
import android.view.animation.Animation
import android.view.animation.AnimationUtils
import android.view.animation.ScaleAnimation

/**
 * Created by marcos on 25/12/2016.
 */

object Animations {

    fun setSlideInLeftAnimation(viewToAnimate: View, currentPosition: Int, lastLoadedViewPosition: Int, alwaysAnimate: Boolean, context: Context) {
        if (alwaysAnimate || currentPosition > lastLoadedViewPosition) {
            val animation = AnimationUtils.loadAnimation(context, android.R.anim.slide_in_left)
            viewToAnimate.startAnimation(animation)
        }
    }

    fun setSlideOutRightAnimation(viewToAnimate: View, currentPosition: Int, lastLoadedViewPosition: Int, alwaysAnimate: Boolean, context: Context) {
        if (alwaysAnimate || currentPosition > lastLoadedViewPosition) {
            val animation = AnimationUtils.loadAnimation(context, android.R.anim.slide_out_right)
            viewToAnimate.startAnimation(animation)
        }
    }

    fun setFadeInAnimation(viewToAnimate: View, currentPosition: Int, lastLoadedViewPosition: Int, alwaysAnimate: Boolean, context: Context) {
        if (alwaysAnimate || currentPosition > lastLoadedViewPosition) {
            val animation = AnimationUtils.loadAnimation(context, android.R.anim.fade_in)
            animation.duration = Constants.FADE_DURATION.toLong()
            viewToAnimate.startAnimation(animation)
        }
    }

    fun setFadeOutAnimation(viewToAnimate: View, currentPosition: Int, lastLoadedViewPosition: Int, alwaysAnimate: Boolean, context: Context) {
        if (alwaysAnimate || currentPosition > lastLoadedViewPosition) {
            val animation = AnimationUtils.loadAnimation(context, android.R.anim.fade_out)
            animation.duration = Constants.FADE_DURATION.toLong()
            viewToAnimate.startAnimation(animation)
        }
    }

    fun setScaleAnimation(viewToAnimate: View, currentPosition: Int, lastLoadedViewPosition: Int, alwaysAnimate: Boolean, context: Context) {
        if (alwaysAnimate || currentPosition > lastLoadedViewPosition) {
            val scaleAnimation = ScaleAnimation(0.0f, 1.0f, 0.0f, 1.0f, Animation.RELATIVE_TO_SELF, 0.5f, Animation.RELATIVE_TO_SELF, 0.5f)
            scaleAnimation.duration = Constants.SCALE_FADE_DURATION.toLong()
            viewToAnimate.startAnimation(scaleAnimation)
        }
    }
}
