package com.androidexamples.app.activity

import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.support.v7.widget.Toolbar
import android.view.View
import android.widget.ImageView

import com.androidexamples.app.R
import com.androidexamples.app.utils.RevealEffect

class RevealEffectActivity : AppCompatActivity() {

    // Views
    private var imageView: ImageView? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_reveal_effect)
        loadComponents()
    }

    private fun loadComponents() {
        setSupportActionBar(findViewById<View>(R.id.top_toolbar) as Toolbar)
        supportActionBar!!.setDisplayHomeAsUpEnabled(true)
        imageView = findViewById<View>(R.id.imageView) as ImageView
        findViewById<View>(R.id.button_show).setOnClickListener(onButtonClick())
        findViewById<View>(R.id.button_hide).setOnClickListener(onButtonClick())
    }

    private fun onButtonClick(): View.OnClickListener {
        return View.OnClickListener { v ->
            when (v.id) {
                R.id.button_show -> RevealEffect.show(imageView!!, 500)
                R.id.button_hide -> RevealEffect.hide(imageView!!, 500)
            }
        }
    }
}
