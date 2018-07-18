package com.androidexamples.app.activity

import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.support.v7.widget.Toolbar
import android.view.View
import android.view.animation.Animation
import android.view.animation.AnimationUtils
import android.view.animation.TranslateAnimation

import com.androidexamples.app.R

class TranslateActivity : AppCompatActivity() {

    private var translateAnimationUp: TranslateAnimation? = null
    private var translateAnimationDown: TranslateAnimation? = null
    private var animation: Animation? = null
    private var translated = false

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_anim_examples)
        loadComponents()
    }

    override fun onResume() {
        super.onResume()

        translateAnimationUp = TranslateAnimation(Animation.RELATIVE_TO_SELF, 0.0f, Animation.RELATIVE_TO_SELF, 0.0f,
                Animation.RELATIVE_TO_SELF, 0.37f, Animation.RELATIVE_TO_SELF, 0.0f)
        translateAnimationUp!!.duration = 1000
        translateAnimationUp!!.fillAfter = true

        translateAnimationDown = TranslateAnimation(Animation.RELATIVE_TO_SELF, 0.0f, Animation.RELATIVE_TO_SELF, 0.0f,
                Animation.RELATIVE_TO_SELF, 0.0f, Animation.RELATIVE_TO_SELF, 0.37f)
        translateAnimationDown!!.duration = 1000
        translateAnimationDown!!.fillAfter = true
    }

    private fun loadComponents() {
        setSupportActionBar(findViewById<View>(R.id.top_toolbar) as Toolbar)
        supportActionBar!!.setDisplayHomeAsUpEnabled(true)
        findViewById<View>(R.id.button_animate_xml).setOnClickListener(onAnimateXmlButtonClick())
        findViewById<View>(R.id.button_animate_api).setOnClickListener(onAnimateApiButtonClick())
    }

    private fun onAnimateApiButtonClick(): View.OnClickListener {
        return View.OnClickListener {
            animation = if (translated) translateAnimationUp else translateAnimationDown
            findViewById<View>(R.id.imageView).startAnimation(animation)
            translated = !translated
        }
    }

    private fun onAnimateXmlButtonClick(): View.OnClickListener {
        return View.OnClickListener {
            val animationResource = if (translated) R.anim.translate_up else R.anim.translate_down
            animation = AnimationUtils.loadAnimation(baseContext, animationResource)
            findViewById<View>(R.id.imageView).startAnimation(animation)
            translated = !translated
        }
    }
}
