package com.androidexamples.app.flows.toolbar

import android.content.Intent
import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.support.v7.widget.Toolbar
import android.view.View

import com.androidexamples.app.R

class ActionBarToolbarActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_actionbar_toolbar)
        LoadComponents()
    }

    private fun LoadComponents() {
        setSupportActionBar(findViewById<View>(R.id.top_toolbar) as Toolbar)
        supportActionBar!!.setDisplayHomeAsUpEnabled(true)

        findViewById<View>(R.id.button_context_action_bar).setOnClickListener(onButtonClick())
        findViewById<View>(R.id.button_stand_alone_toolbar).setOnClickListener(onButtonClick())
        findViewById<View>(R.id.button_collapsing_toolbar).setOnClickListener(onButtonClick())
    }

    private fun onButtonClick(): View.OnClickListener {
        return View.OnClickListener { v ->
            when (v.id) {
                R.id.button_context_action_bar -> startActivity(Intent(baseContext, ContextActionBarActivity::class.java))
                R.id.button_stand_alone_toolbar -> startActivity(Intent(baseContext, StandAloneToolbarActivity::class.java))
                R.id.button_collapsing_toolbar -> startActivity(Intent(baseContext, CollapsingToolbarLayoutActivity::class.java))
            }
        }
    }
}
