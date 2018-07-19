package com.androidexamples.app.flows.views

import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.support.v7.widget.Toolbar
import android.view.View
import android.widget.ImageButton

import com.androidexamples.app.R

class XmlSelectorsActivity : AppCompatActivity() {

    private var imageButton: ImageButton? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_xml_selectors)
        LoadComponents()
    }

    private fun LoadComponents() {
        setSupportActionBar(findViewById<View>(R.id.top_toolbar) as Toolbar)
        supportActionBar!!.setDisplayHomeAsUpEnabled(true)
        imageButton = findViewById<View>(R.id.imageButton) as ImageButton
        imageButton!!.setOnClickListener(onButtonClick())
    }

    private fun onButtonClick(): View.OnClickListener {
        return View.OnClickListener { }
    }
}
