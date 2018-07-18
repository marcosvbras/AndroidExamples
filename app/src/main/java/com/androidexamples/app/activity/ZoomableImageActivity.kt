package com.androidexamples.app.activity

import android.graphics.BitmapFactory
import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.support.v7.widget.Toolbar
import android.view.View

import com.androidexamples.app.R
import com.androidexamples.app.view.ZoomableImageView

class ZoomableImageActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_zoomable_imageview)
        loadComponents()
    }

    private fun loadComponents() {
        setSupportActionBar(findViewById<View>(R.id.top_toolbar) as Toolbar)
        supportActionBar!!.setDisplayHomeAsUpEnabled(true)
        val zoomableImageView = findViewById<View>(R.id.zoomableImageView) as ZoomableImageView
        zoomableImageView.setImageBitmap(BitmapFactory.decodeResource(resources, R.drawable.tghoul))
    }
}
