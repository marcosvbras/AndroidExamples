package com.androidexamples.app.flows.toolbar

import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.support.v7.widget.Toolbar
import android.view.View
import android.widget.Toast

import com.androidexamples.app.R

class StandAloneToolbarActivity : AppCompatActivity() {

    // Views
    private var toolbarBottom: Toolbar? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_stand_alone_toolbar)
        loadComponents()
    }

    private fun loadComponents() {
        setSupportActionBar(findViewById<View>(R.id.top_toolbar) as Toolbar)

        toolbarBottom = findViewById<View>(R.id.toolbar_bottom) as Toolbar?
        toolbarBottom!!.setOnMenuItemClickListener(onMenuItemClickListener())
        toolbarBottom!!.inflateMenu(R.menu.menu_bottom_toolbar)
    }

    private fun onMenuItemClickListener(): Toolbar.OnMenuItemClickListener {
        return Toolbar.OnMenuItemClickListener {
            Toast.makeText(baseContext, getString(R.string.click), Toast.LENGTH_SHORT).show()
            true
        }
    }
}
