package com.androidexamples.app.flows.animations.viewAnimations

import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.support.v7.widget.Toolbar
import android.view.View
import android.view.animation.AlphaAnimation
import android.view.animation.Animation
import android.view.animation.AnimationSet
import android.view.animation.AnimationUtils
import android.view.animation.TranslateAnimation

import com.androidexamples.app.R

class AnimationSetActivity : AppCompatActivity() {

    private var animationSet: AnimationSet? = null
    private var animationStarted = false
    private var translateAnimationUp: TranslateAnimation? = null
    private var translateAnimationDown: TranslateAnimation? = null
    private var alphaAnimationFadeOut: AlphaAnimation? = null
    private var alphaAnimationFadeIn: AlphaAnimation? = null

    private val translateAnimationApi: Animation?
        get() = if (animationStarted) translateAnimationUp else translateAnimationDown

    private val alphaAnimationApi: Animation?
        get() = if (animationStarted) alphaAnimationFadeIn else alphaAnimationFadeOut

    private val animationSetXml: AnimationSet
        get() {
            val animationResource = if (animationStarted) R.anim.set_translate_up_fade_in else R.anim.set_translate_down_fade_out
            return AnimationUtils.loadAnimation(this, animationResource) as AnimationSet
        }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_anim_examples)
        loadComponents()
    }

    private fun loadComponents() {
        setSupportActionBar(findViewById<View>(R.id.top_toolbar) as Toolbar)
        supportActionBar!!.setDisplayHomeAsUpEnabled(true)
        findViewById<View>(R.id.button_animate_api).setOnClickListener(onAnimateApiButtonClick())
        findViewById<View>(R.id.button_animate_xml).setOnClickListener(onAnimateXmlButtonClick())
    }

    private fun onAnimateXmlButtonClick(): View.OnClickListener {
        return View.OnClickListener {
            animationSet = animationSetXml
            animationSet!!.fillAfter = true
            findViewById<View>(R.id.imageView).startAnimation(animationSet)
            animationStarted = !animationStarted
        }
    }

    private fun onAnimateApiButtonClick(): View.OnClickListener {
        return View.OnClickListener {
            animationSet = AnimationSet(true)
            animationSet!!.fillAfter = true
            animationSet!!.addAnimation(translateAnimationApi)
            animationSet!!.addAnimation(alphaAnimationApi)
            findViewById<View>(R.id.imageView).startAnimation(animationSet)
            animationStarted = !animationStarted
        }
    }

    override fun onResume() {
        super.onResume()
        translateAnimationUp = TranslateAnimation(Animation.RELATIVE_TO_SELF, 0.0f, Animation.RELATIVE_TO_SELF, 0.0f,
                Animation.RELATIVE_TO_SELF, 0.37f, Animation.RELATIVE_TO_SELF, 0.0f)
        translateAnimationUp!!.duration = 2000
        translateAnimationUp!!.fillAfter = true // Mantem o efeito no final da animação

        translateAnimationDown = TranslateAnimation(Animation.RELATIVE_TO_SELF, 0.0f, Animation.RELATIVE_TO_SELF, 0.0f,
                Animation.RELATIVE_TO_SELF, 0.0f, Animation.RELATIVE_TO_SELF, 0.37f)
        translateAnimationDown!!.duration = 2000
        translateAnimationDown!!.fillAfter = true // Mantem o efeito no final da animação

        alphaAnimationFadeOut = AlphaAnimation(1.0f, 0.0f) // Apaga a View de 100% de opacidade para 0
        alphaAnimationFadeOut!!.duration = 2000
        alphaAnimationFadeOut!!.fillAfter = true // Mantem o efeito no final da animação
        alphaAnimationFadeIn = AlphaAnimation(0.0f, 1.0f) // Exibe a View de 0 para 100% de opacidade
        alphaAnimationFadeIn!!.duration = 2000
        alphaAnimationFadeIn!!.fillAfter = true // Mantem o efeito no final da animação
    }
}
