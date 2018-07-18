package com.androidexamples.app.activity

import android.content.Intent
import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.support.v7.widget.Toolbar
import android.view.View

import com.androidexamples.app.R

class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        loadComponents()
    }

    private fun loadComponents() {
        setSupportActionBar(findViewById<View>(R.id.top_toolbar) as Toolbar)

        // Click events
        findViewById<View>(R.id.button_actionbar_toolbar).setOnClickListener(onButtonClickListener())
        findViewById<View>(R.id.button_animations).setOnClickListener(onButtonClickListener())
        findViewById<View>(R.id.button_another_views).setOnClickListener(onButtonClickListener())
        findViewById<View>(R.id.button_broadcast_receiver).setOnClickListener(onButtonClickListener())
        findViewById<View>(R.id.button_fragments).setOnClickListener(onButtonClickListener())
        findViewById<View>(R.id.button_gestures).setOnClickListener(onButtonClickListener())
        findViewById<View>(R.id.button_handler_async_task).setOnClickListener(onButtonClickListener())
        findViewById<View>(R.id.button_intents).setOnClickListener(onButtonClickListener())
        findViewById<View>(R.id.button_navigation_drawer).setOnClickListener(onButtonClickListener())
        findViewById<View>(R.id.button_notifications).setOnClickListener(onButtonClickListener())
        findViewById<View>(R.id.button_palette).setOnClickListener(onButtonClickListener())
        findViewById<View>(R.id.button_preference_screen).setOnClickListener(onButtonClickListener())
        findViewById<View>(R.id.button_save_instance_state).setOnClickListener(onButtonClickListener())
        findViewById<View>(R.id.button_simple_view_pager).setOnClickListener(onButtonClickListener())
    }

    private fun onButtonClickListener(): View.OnClickListener {
        return View.OnClickListener { v ->
            when (v.id) {
                R.id.button_animations -> startActivity(Intent(baseContext, AnimationsActivity::class.java))
                R.id.button_another_views -> startActivity(Intent(baseContext, AnotherViewsActivity::class.java))
                R.id.button_broadcast_receiver -> startActivity(Intent(baseContext, BroadcastReceiverActivity::class.java))
                R.id.button_fragments -> startActivity(Intent(baseContext, FragmentsActivity::class.java))
                R.id.button_gestures -> startActivity(Intent(baseContext, GesturesActivity::class.java))
                R.id.button_handler_async_task -> startActivity(Intent(baseContext, HandlerAsyncTaskActivity::class.java))
                R.id.button_intents -> startActivity(Intent(baseContext, IntentsActivity::class.java))
                R.id.button_save_instance_state -> startActivity(Intent(baseContext, SaveInstanceStateActivity::class.java))
                R.id.button_actionbar_toolbar -> startActivity(Intent(baseContext, ActionBarToolbarActivity::class.java))
                R.id.button_simple_view_pager -> startActivity(Intent(baseContext, ViewPagerActivity::class.java))
                R.id.button_palette -> startActivity(Intent(baseContext, PaletteActivity::class.java))
                R.id.button_navigation_drawer -> startActivity(Intent(baseContext, NavigationDrawerActivity::class.java))
                R.id.button_notifications -> startActivity(Intent(baseContext, NotificationsActivity::class.java))
                R.id.button_preference_screen -> startActivity(Intent(baseContext, PreferenceScreenActivity::class.java))
            }
        }
    }
}
