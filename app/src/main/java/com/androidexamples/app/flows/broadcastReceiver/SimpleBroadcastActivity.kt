package com.androidexamples.app.flows.broadcastReceiver

import android.content.Intent
import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.support.v7.widget.Toolbar
import android.view.View
import android.widget.Toast

import com.androidexamples.app.R

class SimpleBroadcastActivity : AppCompatActivity() {

    /**
     * Necessário declarar o receiver no AndroidManifest.xml
     *
     */

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_static_broadcast)
        loadComponents()
    }

    private fun loadComponents() {
        setSupportActionBar(findViewById<View>(R.id.top_toolbar) as Toolbar)
        supportActionBar!!.setDisplayHomeAsUpEnabled(true)

        findViewById<View>(R.id.button_send_broadcast).setOnClickListener(onButtonClickListener())
    }

    private fun onButtonClickListener(): View.OnClickListener {
        return View.OnClickListener { v ->
            when (v.id) {
                R.id.button_send_broadcast -> {
                    sendBroadcast(Intent("Katiau"))
                    Toast.makeText(baseContext, "Intent sent", Toast.LENGTH_SHORT).show()
                }
            }
        }
    }
}
