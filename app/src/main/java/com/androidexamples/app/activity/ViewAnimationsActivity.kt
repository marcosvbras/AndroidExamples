package com.androidexamples.app.activity

import android.content.Intent
import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.support.v7.widget.Toolbar
import android.view.View

import com.androidexamples.app.R

class ViewAnimationsActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_view_animations)
        loadComponents()
    }

    private fun loadComponents() {
        setSupportActionBar(findViewById<View>(R.id.top_toolbar) as Toolbar)
        supportActionBar!!.setDisplayHomeAsUpEnabled(true)

        findViewById<View>(R.id.button_animation_drawable).setOnClickListener(onAnimationsClick())
        findViewById<View>(R.id.button_alpha).setOnClickListener(onAnimationsClick())
        findViewById<View>(R.id.button_rotate).setOnClickListener(onAnimationsClick())
        findViewById<View>(R.id.button_scale).setOnClickListener(onAnimationsClick())
        findViewById<View>(R.id.button_translate).setOnClickListener(onAnimationsClick())
        findViewById<View>(R.id.button_animation_listener).setOnClickListener(onAnimationsClick())
        findViewById<View>(R.id.button_animation_set).setOnClickListener(onAnimationsClick())
        findViewById<View>(R.id.button_view_flipper).setOnClickListener(onAnimationsClick())
    }

    private fun onAnimationsClick(): View.OnClickListener {
        return View.OnClickListener { v ->
            when (v.id) {
                R.id.button_alpha -> startActivity(Intent(baseContext, AlphaActivity::class.java))
                R.id.button_rotate -> startActivity(Intent(baseContext, RotateActivity::class.java))
                R.id.button_animation_drawable -> startActivity(Intent(baseContext, AnimationDrawableActivity::class.java))
                R.id.button_scale -> startActivity(Intent(baseContext, ScaleActivity::class.java))
                R.id.button_translate -> startActivity(Intent(baseContext, TranslateActivity::class.java))
                R.id.button_animation_listener -> startActivity(Intent(baseContext, AnimationListenerActivity::class.java))
                R.id.button_animation_set -> startActivity(Intent(baseContext, AnimationSetActivity::class.java))
                R.id.button_view_flipper -> startActivity(Intent(baseContext, ViewFlipperActivity::class.java))
            }
        }
    }
}
