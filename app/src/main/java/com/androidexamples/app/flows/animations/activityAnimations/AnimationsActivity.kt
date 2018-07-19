package com.androidexamples.app.flows.animations.activityAnimations

import android.content.Intent
import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.support.v7.widget.Toolbar
import android.view.View

import com.androidexamples.app.R
import com.androidexamples.app.flows.animations.viewAnimations.ViewAnimationsActivity

class AnimationsActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_animations)
        loadComponents()
    }

    private fun loadComponents() {
        setSupportActionBar(findViewById<View>(R.id.top_toolbar) as Toolbar)
        supportActionBar!!.setDisplayHomeAsUpEnabled(true)
        findViewById<View>(R.id.button_view_animations).setOnClickListener(onButtonClick())
        findViewById<View>(R.id.button_activity_animations).setOnClickListener(onButtonClick())
        findViewById<View>(R.id.button_reveal_effect).setOnClickListener(onButtonClick())
    }

    private fun onButtonClick(): View.OnClickListener {
        return View.OnClickListener { v ->
            when (v.id) {
                R.id.button_view_animations -> startActivity(Intent(baseContext, ViewAnimationsActivity::class.java))
                R.id.button_activity_animations -> startActivity(Intent(baseContext, ActivityAnimationsActivity::class.java))
                R.id.button_reveal_effect -> startActivity(Intent(baseContext, RevealEffectActivity::class.java))
            }
        }
    }
}
