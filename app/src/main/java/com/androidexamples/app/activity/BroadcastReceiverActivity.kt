package com.androidexamples.app.activity

import android.content.Intent
import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.support.v7.widget.Toolbar
import android.view.View

import com.androidexamples.app.R

class BroadcastReceiverActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_broadcast_receiver)
        loadComponents()
    }

    private fun loadComponents() {
        setSupportActionBar(findViewById<View>(R.id.top_toolbar) as Toolbar)
        supportActionBar!!.setDisplayHomeAsUpEnabled(true)

        findViewById<View>(R.id.button_static_broadcast).setOnClickListener(onButtonClickListener())
        findViewById<View>(R.id.button_dynamic_broadcast).setOnClickListener(onButtonClickListener())
        findViewById<View>(R.id.button_local_broadcast).setOnClickListener(onButtonClickListener())
    }

    private fun onButtonClickListener(): View.OnClickListener {
        return View.OnClickListener { v ->
            when (v.id) {
                R.id.button_static_broadcast -> startActivity(Intent(baseContext, SimpleBroadcastActivity::class.java))
                R.id.button_dynamic_broadcast -> startActivity(Intent(baseContext, DynamicBroadcastActivity::class.java))
                R.id.button_local_broadcast -> startActivity(Intent(baseContext, LocalBroadcastActivity::class.java))
            }
        }
    }
}
