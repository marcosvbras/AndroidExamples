package com.androidexamples.app.activity

import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.support.v7.widget.Toolbar
import android.view.View
import android.widget.Button

import com.androidexamples.app.R
import com.androidexamples.app.utils.Constants

class SaveInstanceStateActivity : AppCompatActivity() {

    // Views
    private var button: Button? = null

    // Outros objetos
    private var count: Int = 0

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_save_instance_state)
        loadComponents()
    }

    override fun onResume() {
        super.onResume()
        button!!.text = count.toString()
    }

    override fun onSaveInstanceState(outState: Bundle) {
        super.onSaveInstanceState(outState)
        outState.putInt(Constants.KEY_COUNT, count)
    }

    override fun onRestoreInstanceState(savedInstanceState: Bundle?) {
        super.onRestoreInstanceState(savedInstanceState)

        if (savedInstanceState != null)
            count = savedInstanceState.getInt(Constants.KEY_COUNT, count)
        else
            count = 0

    }

    private fun loadComponents() {
        setSupportActionBar(findViewById<View>(R.id.top_toolbar) as Toolbar)
        supportActionBar!!.setDisplayHomeAsUpEnabled(true)
        button = findViewById<View>(R.id.button_send_message_handler) as Button
        button!!.setOnClickListener(onButtonClick())
    }

    private fun onButtonClick(): View.OnClickListener {
        return View.OnClickListener { button!!.text = (++count).toString() }
    }
}
