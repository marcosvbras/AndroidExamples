package com.androidexamples.app.flows.broadcastReceiver

import android.content.BroadcastReceiver
import android.content.Context
import android.content.Intent
import android.content.IntentFilter
import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.support.v7.widget.Toolbar
import android.view.View
import android.widget.TextView
import android.widget.Toast

import com.androidexamples.app.R

class DynamicBroadcastActivity : AppCompatActivity() {

    // Views
    private var textViewMessage: TextView? = null

    private val dynamicReceiver = object : BroadcastReceiver() {
        override fun onReceive(context: Context, intent: Intent) {
            textViewMessage!!.text = context.getString(R.string.message_arrived)
        }
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_dynamic_broadcast)
        loadComponents()
        registerReceiver(dynamicReceiver, IntentFilter("Katiuga"))
    }

    private fun loadComponents() {
        setSupportActionBar(findViewById<View>(R.id.top_toolbar) as Toolbar)
        supportActionBar!!.setDisplayHomeAsUpEnabled(true)

        findViewById<View>(R.id.button_send_broadcast).setOnClickListener(onButtonClickListener())
        textViewMessage = findViewById<View>(R.id.text_view_message) as TextView
    }

    override fun onDestroy() {
        super.onDestroy()
        unregisterReceiver(dynamicReceiver)
    }

    private fun onButtonClickListener(): View.OnClickListener {
        return View.OnClickListener { v ->
            when (v.id) {
                R.id.button_send_broadcast -> {
                    sendBroadcast(Intent("Katiuga"))
                    Toast.makeText(baseContext, "Intent sent", Toast.LENGTH_SHORT).show()
                }
            }
        }
    }
}
