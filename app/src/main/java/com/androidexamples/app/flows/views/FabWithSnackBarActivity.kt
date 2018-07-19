package com.androidexamples.app.flows.views

import android.support.design.widget.CoordinatorLayout
import android.support.design.widget.FloatingActionButton
import android.support.design.widget.Snackbar
import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.support.v7.widget.Toolbar
import android.view.View
import android.view.animation.Animation
import android.view.animation.AnimationUtils
import android.widget.Toast

import com.androidexamples.app.R

class FabWithSnackBarActivity : AppCompatActivity() {

    // Views
    private var coordinatorLayoutRoot: CoordinatorLayout? = null
    private var floatingActionButtonMini: FloatingActionButton? = null
    private var floatingActionButtonNormal: FloatingActionButton? = null
    private var floatingActionButtonBottom: FloatingActionButton? = null

    // Another Objects
    private var animation: Animation? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_fab_with_snackbar)
        loadComponents()
    }

    private fun loadComponents() {
        setSupportActionBar(findViewById<View>(R.id.top_toolbar) as Toolbar)
        supportActionBar!!.setDisplayHomeAsUpEnabled(true)
        animation = AnimationUtils.loadAnimation(this, R.anim.scale_up)
        coordinatorLayoutRoot = findViewById<View>(R.id.coordinatorLayoutRoot) as CoordinatorLayout
        floatingActionButtonNormal = findViewById<View>(R.id.fab_normal) as FloatingActionButton
        floatingActionButtonNormal!!.startAnimation(animation)
        floatingActionButtonNormal!!.setOnClickListener(onFabClick())
        floatingActionButtonMini = findViewById<View>(R.id.fab_mini) as FloatingActionButton
        floatingActionButtonMini!!.startAnimation(animation)
        floatingActionButtonMini!!.setOnClickListener(onFabClick())
        floatingActionButtonBottom = findViewById<View>(R.id.fab_bottom) as FloatingActionButton
        floatingActionButtonBottom!!.startAnimation(animation)
        floatingActionButtonBottom!!.setOnClickListener(onFabClick())
    }

    private fun onFabClick(): View.OnClickListener {
        return View.OnClickListener { v ->
            when (v.id) {
                R.id.fab_mini -> showSnackBar()
                R.id.fab_normal -> showSnackBar()
                R.id.fab_bottom -> showSnackBar()
            }
        }
    }

    private fun showSnackBar() {
        Snackbar.make(coordinatorLayoutRoot!!, resources.getString(R.string.this_is_snackbar), Snackbar.LENGTH_LONG)
                .setAction(resources.getString(R.string.ok)) { Toast.makeText(baseContext, resources.getString(R.string.ok), Toast.LENGTH_SHORT).show() }
                .show()
    }
}
