package com.androidexamples.app.flows.animations.viewAnimations

import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.support.v7.widget.Toolbar
import android.view.View
import android.view.animation.AnimationUtils
import android.widget.ViewFlipper

import com.androidexamples.app.R

class ViewFlipperActivity : AppCompatActivity() {

    // Views
    private var viewFlipper: ViewFlipper? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_view_flipper)
        loadComponents()
    }

    private fun loadComponents() {
        setSupportActionBar(findViewById<View>(R.id.top_toolbar) as Toolbar)
        supportActionBar!!.setDisplayHomeAsUpEnabled(true)
        findViewById<View>(R.id.button_flip).setOnClickListener(onFlipButtonClick())
        viewFlipper = findViewById<View>(R.id.view_flipper) as ViewFlipper
    }

    private fun onFlipButtonClick(): View.OnClickListener {
        return View.OnClickListener {
            // animação de saída da view atual
            val slideOutRight = AnimationUtils.loadAnimation(baseContext, android.R.anim.slide_out_right)
            slideOutRight.duration = 2000
            // animação de entrada da próxima view
            val fadeIn = AnimationUtils.loadAnimation(baseContext, android.R.anim.fade_in)
            fadeIn.duration = 2000
            // Configura a animação de entrada e saída
            viewFlipper!!.inAnimation = fadeIn
            viewFlipper!!.outAnimation = slideOutRight
            viewFlipper!!.showNext()
        }
    }
}
