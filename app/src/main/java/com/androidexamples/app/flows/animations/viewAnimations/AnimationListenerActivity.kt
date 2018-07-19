package com.androidexamples.app.flows.animations.viewAnimations

import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.support.v7.widget.Toolbar
import android.view.View
import android.view.animation.Animation
import android.view.animation.AnimationUtils
import android.view.animation.ScaleAnimation

import com.androidexamples.app.R

class AnimationListenerActivity : AppCompatActivity() {

    private var animationRetract: ScaleAnimation? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_animation_listener)
        loadComponents()
    }

    private fun loadComponents() {
        setSupportActionBar(findViewById<View>(R.id.top_toolbar) as Toolbar)
        supportActionBar!!.setDisplayHomeAsUpEnabled(true)
        findViewById<View>(R.id.button_animate).setOnClickListener(onAnimateButtonClick())
    }

    private fun onAnimateButtonClick(): View.OnClickListener {
        return View.OnClickListener {
            animationRetract = AnimationUtils.loadAnimation(baseContext, R.anim.scale_retract) as ScaleAnimation
            animationRetract!!.setAnimationListener(onAnimation())
            findViewById<View>(R.id.linearLayoutAnimated).startAnimation(animationRetract)
        }
    }

    private fun onAnimation(): Animation.AnimationListener {
        return object : Animation.AnimationListener {
            override fun onAnimationStart(animation: Animation) {

            }

            override fun onAnimationEnd(animation: Animation) {
                findViewById<View>(R.id.linearLayoutMessage).visibility = View.VISIBLE
            }

            override fun onAnimationRepeat(animation: Animation) {

            }
        }
    }
}
