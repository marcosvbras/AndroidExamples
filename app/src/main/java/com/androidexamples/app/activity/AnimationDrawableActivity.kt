package com.androidexamples.app.activity

import android.graphics.drawable.Animatable
import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.support.v7.widget.Toolbar
import android.view.View
import android.widget.ImageView

import com.androidexamples.app.R

class AnimationDrawableActivity : AppCompatActivity() {

    // Another Objects
    private var animatable: Animatable? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_animation_drawable)
        loadComponents()
    }

    private fun loadComponents() {
        setSupportActionBar(findViewById<View>(R.id.top_toolbar) as Toolbar)
        supportActionBar!!.setDisplayHomeAsUpEnabled(true)

        animatable = (findViewById<View>(R.id.imageView) as ImageView).drawable as Animatable
        animatable!!.start()

        findViewById<View>(R.id.button_start).setOnClickListener(onStarButtonClick())
        findViewById<View>(R.id.button_stop).setOnClickListener(onStopButtonClick())
    }

    private fun onStopButtonClick(): View.OnClickListener {
        return View.OnClickListener {
            if (animatable!!.isRunning)
                animatable!!.stop()
        }
    }

    private fun onStarButtonClick(): View.OnClickListener {
        return View.OnClickListener {
            if (!animatable!!.isRunning)
                animatable!!.start()
        }
    }
}
