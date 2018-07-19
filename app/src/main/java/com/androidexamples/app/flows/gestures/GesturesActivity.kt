package com.androidexamples.app.flows.gestures

import android.content.Intent
import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.support.v7.widget.Toolbar
import android.view.View

import com.androidexamples.app.R

class GesturesActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_gestures)
        LoadComponents()
    }

    private fun LoadComponents() {
        setSupportActionBar(findViewById<View>(R.id.top_toolbar) as Toolbar)
        supportActionBar!!.setDisplayHomeAsUpEnabled(true)
        findViewById<View>(R.id.button_zoomable_imageview).setOnClickListener(onZoomableImageViewButtonClick())
        findViewById<View>(R.id.button_drag_drop_image).setOnClickListener(onDragDropImageButtonClick())
    }

    private fun onDragDropImageButtonClick(): View.OnClickListener {
        return View.OnClickListener { startActivity(Intent(baseContext, DraggableViewActivity::class.java)) }
    }

    private fun onZoomableImageViewButtonClick(): View.OnClickListener {
        return View.OnClickListener { startActivity(Intent(baseContext, ZoomableImageActivity::class.java)) }
    }
}
