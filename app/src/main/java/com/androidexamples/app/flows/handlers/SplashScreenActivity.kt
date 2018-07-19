package com.androidexamples.app.flows.handlers

import android.content.Intent
import android.os.Handler
import android.support.v4.app.ActivityCompat
import android.support.v4.app.ActivityOptionsCompat
import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.view.Window
import android.view.WindowManager
import android.widget.LinearLayout

import com.androidexamples.app.R
import com.androidexamples.app.flows.animations.activityAnimations.TestActivity

class SplashScreenActivity : AppCompatActivity() {

    private val runnable: Runnable
        get() = Runnable {
            val linearLayout = findViewById<View>(R.id.activity_splash_screen) as LinearLayout
            val intent = Intent(baseContext, TestActivity::class.java)
            intent.flags = Intent.FLAG_ACTIVITY_NEW_TASK
            val scaleBundle = ActivityOptionsCompat.makeClipRevealAnimation(linearLayout, 0, 0, linearLayout.width, linearLayout.height).toBundle()
            ActivityCompat.startActivity(baseContext, intent, scaleBundle)
            finish()
        }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        requestWindowFeature(Window.FEATURE_NO_TITLE)
        window.setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN,
                WindowManager.LayoutParams.FLAG_FULLSCREEN)
        setContentView(R.layout.activity_splash_screen)
        loadComponents()
    }

    private fun loadComponents() {
        Handler().postDelayed(runnable, 5000)
    }
}
