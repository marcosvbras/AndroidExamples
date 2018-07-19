package com.androidexamples.app.flows.animations.viewAnimations

import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import android.support.v7.widget.Toolbar
import android.view.View
import android.view.animation.Animation
import android.view.animation.AnimationUtils
import android.view.animation.ScaleAnimation
import android.widget.ImageView

import com.androidexamples.app.R

class ScaleActivity : AppCompatActivity() {

    // Views
    private var imageView: ImageView? = null

    // Another objects
    private var animationRetract: ScaleAnimation? = null
    private var dead = true
    private var pivotXType: Int = 0
    private var pivotYType: Int = 0
    private var pivotXValue: Float = 0.toFloat()
    private var pivotYValue: Float = 0.toFloat()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_anim_examples)
        loadComponents()
    }

    private fun loadComponents() {
        setSupportActionBar(findViewById<View>(R.id.top_toolbar) as Toolbar)
        supportActionBar!!.setDisplayHomeAsUpEnabled(true)

        imageView = findViewById<View>(R.id.imageView) as ImageView
        imageView!!.setImageResource(R.drawable.heart)
        findViewById<View>(R.id.button_animate_api).setOnClickListener(onAnimateApiButtonClick())
        findViewById<View>(R.id.button_animate_xml).setOnClickListener(onAnimateXmlButtonClick())
    }

    override fun onResume() {
        super.onResume()
        pivotXType = Animation.RELATIVE_TO_SELF
        pivotXValue = 0.5f
        pivotYType = Animation.RELATIVE_TO_SELF
        pivotYValue = 0.5f
        animationRetract = ScaleAnimation(1.0f, 0.6f, 1.0f, 0.6f, pivotXType, pivotXValue, pivotYType, pivotYValue)
        animationRetract!!.duration = 500
        animationRetract!!.repeatCount = Animation.INFINITE
        animationRetract!!.repeatMode = Animation.RESTART
    }

    private fun onAnimateApiButtonClick(): View.OnClickListener {
        return View.OnClickListener {
            if (dead)
                imageView!!.startAnimation(animationRetract)
            else
                imageView!!.animation.cancel()

            dead = !dead
        }
    }

    private fun onAnimateXmlButtonClick(): View.OnClickListener {
        return View.OnClickListener {
            if (dead)
                imageView!!.startAnimation(AnimationUtils.loadAnimation(baseContext, R.anim.scale_heart_beat))
            else
                imageView!!.animation.cancel()

            dead = !dead
        }
    }
}
