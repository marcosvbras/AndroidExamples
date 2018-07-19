package com.androidexamples.app.flows.notifications

import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.support.v7.widget.Toolbar
import android.view.View

import com.androidexamples.app.R

class NotificationsActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_notifications)
        loadComponents()
    }

    private fun loadComponents() {
        setSupportActionBar(findViewById<View>(R.id.top_toolbar) as Toolbar)
        supportActionBar!!.setDisplayHomeAsUpEnabled(true)

        findViewById<View>(R.id.button_simple_notification).setOnClickListener(onSimpleNotificationClick())
        findViewById<View>(R.id.button_heads_up_notification).setOnClickListener(onHeadsUpNotificationClick())
        findViewById<View>(R.id.button_big_view_notification).setOnClickListener(onBigViewNotificationClick())
        findViewById<View>(R.id.button_notification_with_action).setOnClickListener(onNotificationActionClick())
    }

    private fun onNotificationActionClick(): View.OnClickListener {
        return View.OnClickListener { }
    }

    private fun onBigViewNotificationClick(): View.OnClickListener {
        return View.OnClickListener { }
    }

    private fun onHeadsUpNotificationClick(): View.OnClickListener {
        return View.OnClickListener { }
    }

    private fun onSimpleNotificationClick(): View.OnClickListener {
        return View.OnClickListener { }
    }
}
