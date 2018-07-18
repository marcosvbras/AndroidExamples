package com.androidexamples.app.activity

import android.content.Intent
import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.support.v7.widget.Toolbar
import android.view.View

import com.androidexamples.app.R
import com.androidexamples.app.view.SlidingTabLayout

class ViewPagerActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_view_pager)
        LoadComponents()
    }

    private fun LoadComponents() {
        setSupportActionBar(findViewById<View>(R.id.top_toolbar) as Toolbar)
        supportActionBar!!.setDisplayHomeAsUpEnabled(true)
        findViewById<View>(R.id.button_simple_view_pager).setOnClickListener(onButtonClick())
        findViewById<View>(R.id.button_view_pager_title).setOnClickListener(onButtonClick())
        findViewById<View>(R.id.button_intro_slider).setOnClickListener(onButtonClick())
        findViewById<View>(R.id.button_sliding_tab_layout).setOnClickListener(onButtonClick())
    }

    private fun onButtonClick(): View.OnClickListener {
        return View.OnClickListener { v ->
            when (v.id) {
                R.id.button_intro_slider -> startActivity(Intent(baseContext, IntroSliderActivity::class.java))
                R.id.button_simple_view_pager -> startActivity(Intent(baseContext, SimpleViewPagerActivity::class.java))
                R.id.button_sliding_tab_layout -> startActivity(Intent(baseContext, SlidingTabLayoutActivity::class.java))
                R.id.button_view_pager_title -> startActivity(Intent(baseContext, ViewPagerTitleActivity::class.java))
            }
        }
    }
}
