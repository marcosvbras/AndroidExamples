package com.androidexamples.app.activity

import android.os.Bundle

import com.androidexamples.app.R

class CollapsingToolbarLayoutActivity : BaseActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_collapsing_toolbar_layout)
        loadComponents()
    }

    private fun loadComponents() {
        setUpToolbar()
        supportActionBar!!.setDisplayHomeAsUpEnabled(true)
    }
}
