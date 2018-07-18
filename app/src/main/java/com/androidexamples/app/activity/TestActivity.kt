package com.androidexamples.app.activity

import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.support.v7.widget.Toolbar
import android.view.MenuItem
import android.view.View
import android.view.Window

import com.androidexamples.app.R
import com.androidexamples.app.utils.Constants

class TestActivity : AppCompatActivity() {

    private var animation: Int = 0

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        window.requestFeature(Window.FEATURE_CONTENT_TRANSITIONS)
        setContentView(R.layout.activity_test)
        loadComponents()
    }

    private fun loadComponents() {
        setSupportActionBar(findViewById<View>(R.id.top_toolbar) as Toolbar)
        supportActionBar!!.setDisplayHomeAsUpEnabled(true)

        if (intent.extras != null)
            animation = intent.extras!!.getInt(Constants.KEY_ANIMATION)
    }

    override fun finish() {
        super.finish()
        // Customiza a animação ao fechar a activity
        if (animation == Constants.ANIMATION_FADE)
            overridePendingTransition(android.R.anim.fade_in, android.R.anim.fade_out)
        else if (animation == Constants.ANIMATION_SLIDE_RIGHT)
            overridePendingTransition(R.anim.slide_in_right, R.anim.slide_out_right)
        else if (animation == Constants.ANIMATION_SLIDE_LEFT)
            overridePendingTransition(R.anim.slide_in_left, R.anim.slide_out_left)
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        when (item.itemId) {
        // Up Navigation - voltando com animação
            android.R.id.home -> {
                supportFinishAfterTransition()
                return true
            }
        }
        return super.onOptionsItemSelected(item)
    }
}
