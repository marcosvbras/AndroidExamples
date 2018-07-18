package com.androidexamples.app.activity

import android.content.Intent
import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.support.v7.widget.Toolbar
import android.view.View

import com.androidexamples.app.R

class HandlerAsyncTaskActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_handler_async_task)
        loadComponents()
    }

    private fun loadComponents() {
        setSupportActionBar(findViewById<View>(R.id.top_toolbar) as Toolbar)
        supportActionBar!!.setDisplayHomeAsUpEnabled(true)
        findViewById<View>(R.id.button_handler_message).setOnClickListener(onButtonClick())
        findViewById<View>(R.id.button_redownload_image).setOnClickListener(onButtonClick())
        findViewById<View>(R.id.button_splash_screen).setOnClickListener(onButtonClick())
        findViewById<View>(R.id.button_async_task).setOnClickListener(onButtonClick())
    }

    private fun onButtonClick(): View.OnClickListener {
        return View.OnClickListener { v ->
            when (v.id) {
                R.id.button_handler_message -> startActivity(Intent(baseContext, HandlerMessageActivity::class.java))
                R.id.button_redownload_image -> startActivity(Intent(baseContext, ReDownloadImage::class.java))
                R.id.button_splash_screen -> startActivity(Intent(baseContext, SplashScreenActivity::class.java))
                R.id.button_async_task -> startActivity(Intent(baseContext, AsyncTaskActivity::class.java))
            }
        }
    }
}
