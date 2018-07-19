package com.androidexamples.app.flows.animations.viewAnimations

import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.support.v7.widget.Toolbar
import android.view.View
import android.view.animation.AlphaAnimation
import android.view.animation.Animation
import android.view.animation.AnimationUtils

import com.androidexamples.app.R

class AlphaActivity : AppCompatActivity() {

    // Another objects
    private var fadeOut: AlphaAnimation? = null
    private var fadeIn: AlphaAnimation? = null
    private var alphaAnimation: AlphaAnimation? = null
    private var isViewVisible = true
    private var animation: Animation? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_anim_examples)
        loadComponents()
    }

    private fun loadComponents() {
        setSupportActionBar(findViewById<View>(R.id.top_toolbar) as Toolbar)
        supportActionBar!!.setDisplayHomeAsUpEnabled(true)
        findViewById<View>(R.id.button_animate_xml).setOnClickListener(onAnimateXmlButtonClick())
        findViewById<View>(R.id.button_animate_api).setOnClickListener(onAnimateApiButtonClick())
    }

    override fun onResume() {
        super.onResume()
        fadeOut = AlphaAnimation(1.0f, 0.0f) // Apaga a View de 100% de opacidade para 0
        fadeIn = AlphaAnimation(0.0f, 1.0f) // Exibe a View de 0 para 100% de opacidade
    }

    private fun onAnimateApiButtonClick(): View.OnClickListener {
        return View.OnClickListener {
            alphaAnimation = if (isViewVisible) fadeOut else fadeIn
            alphaAnimation!!.duration = 2000
            alphaAnimation!!.fillAfter = true // Mantem o efeito no final da animação
            findViewById<View>(R.id.imageView).startAnimation(alphaAnimation)
            isViewVisible = !isViewVisible
        }
    }

    private fun onAnimateXmlButtonClick(): View.OnClickListener {
        return View.OnClickListener {
            val animationId = if (isViewVisible) android.R.anim.fade_out else android.R.anim.fade_in
            animation = AnimationUtils.loadAnimation(baseContext, animationId)
            animation!!.duration = 2000
            animation!!.fillAfter = true // Mantem o efeito no final da animação
            findViewById<View>(R.id.imageView).startAnimation(animation)
            isViewVisible = !isViewVisible
        }
    }
}
