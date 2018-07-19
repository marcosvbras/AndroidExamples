package com.androidexamples.app.flows.views

import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.support.v7.widget.Toolbar
import android.view.View
import android.view.ViewGroup
import android.view.animation.AnimationUtils
import android.widget.ImageSwitcher
import android.widget.ImageView
import android.widget.ViewSwitcher

import com.androidexamples.app.R
import com.androidexamples.app.domain.SimpleItem
import com.androidexamples.app.utils.Constants

import java.util.ArrayList

class ImageSwitcherActivity : AppCompatActivity() {

    // Views
    private var imageSwitcher: ImageSwitcher? = null

    // Another Objects
    private var index: Int = 0
    private var listSimpleItems: MutableList<SimpleItem>? = null

    val viewFactory: ViewSwitcher.ViewFactory
        get() = ViewSwitcher.ViewFactory {
            val imageView = ImageView(baseContext)
            imageView.scaleType = ImageView.ScaleType.CENTER_INSIDE
            imageView.layoutParams = ViewGroup.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.MATCH_PARENT)
            imageView
        }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_image_switcher)
        loadComponents()
        index = 0
    }

    private fun loadComponents() {
        setSupportActionBar(findViewById<View>(R.id.top_toolbar) as Toolbar)
        supportActionBar!!.setDisplayHomeAsUpEnabled(true)
        imageSwitcher = findViewById<View>(R.id.imageSwitcher) as ImageSwitcher
        imageSwitcher!!.setFactory(viewFactory)
        imageSwitcher!!.inAnimation = AnimationUtils.loadAnimation(this, android.R.anim.slide_in_left)
        imageSwitcher!!.outAnimation = AnimationUtils.loadAnimation(this, android.R.anim.fade_out)
        findViewById<View>(R.id.button_previous).setOnClickListener(onControlButtonClick())
        findViewById<View>(R.id.button_next).setOnClickListener(onControlButtonClick())
    }

    private fun onControlButtonClick(): View.OnClickListener {
        return View.OnClickListener { v ->
            if (v.id == R.id.button_previous) {
                if (index == 0)
                    index = listSimpleItems!!.size - 1
                else
                    index--
            } else if (v.id == R.id.button_next) {
                if (index == listSimpleItems!!.size - 1)
                    index = 0
                else
                    index++
            }

            imageSwitcher!!.setImageResource(listSimpleItems!![index].imageResource!!)
        }
    }

    override fun onResume() {
        super.onResume()
        populateList()
        imageSwitcher!!.setImageResource(listSimpleItems!![index].imageResource!!)
    }

    private fun populateList() {
        listSimpleItems = ArrayList()
        listSimpleItems!!.add(SimpleItem("Akami ga Kill", R.drawable.akami_ga_kill))
        listSimpleItems!!.add(SimpleItem("Angel Beats!", R.drawable.angel_beats))
        listSimpleItems!!.add(SimpleItem("Attack On Titan", R.drawable.attack_on_titan))
        listSimpleItems!!.add(SimpleItem("Btooom!", R.drawable.btooom))
        listSimpleItems!!.add(SimpleItem("No Game, No Life!", R.drawable.no_game_no_life))
        listSimpleItems!!.add(SimpleItem("Noragami", R.drawable.noragami))
        listSimpleItems!!.add(SimpleItem("Tokyo Ghoul", R.drawable.tghoul))
        listSimpleItems!!.add(SimpleItem("Tokyo Ghoul Root A", R.drawable.tokyo))
        listSimpleItems!!.add(SimpleItem("To Love-Ru", R.drawable.toloveru))
    }

    override fun onSaveInstanceState(outState: Bundle) {
        super.onSaveInstanceState(outState)
        outState.putInt(Constants.KEY_COUNT, index)
    }

    override fun onRestoreInstanceState(savedInstanceState: Bundle?) {
        super.onRestoreInstanceState(savedInstanceState)

        if (savedInstanceState != null)
            index = savedInstanceState.getInt(Constants.KEY_COUNT)
    }
}
