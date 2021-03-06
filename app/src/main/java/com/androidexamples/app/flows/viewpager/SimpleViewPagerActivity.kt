package com.androidexamples.app.flows.viewpager

import android.support.v4.view.ViewPager
import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.support.v7.widget.Toolbar
import android.view.View

import com.androidexamples.app.R
import com.androidexamples.app.adapter.ImageAdapter
import com.androidexamples.app.domain.SimpleItem

import java.util.ArrayList

class SimpleViewPagerActivity : AppCompatActivity() {

    private var viewPager: ViewPager? = null
    private var listSimpleItems: MutableList<SimpleItem>? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_simple_view_pager)
        populateList()
        loadComponents()
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

    private fun loadComponents() {
        setSupportActionBar(findViewById<View>(R.id.top_toolbar) as Toolbar)
        supportActionBar!!.setDisplayHomeAsUpEnabled(true)
        viewPager = findViewById<View>(R.id.viewPager) as ViewPager?
        viewPager!!.adapter = ImageAdapter(this, listSimpleItems)
        viewPager!!.setOnPageChangeListener(onPageChange())
    }

    private fun onPageChange(): ViewPager.OnPageChangeListener {
        return object : ViewPager.OnPageChangeListener {
            override fun onPageScrolled(position: Int, positionOffset: Float, positionOffsetPixels: Int) {

            }

            override fun onPageSelected(position: Int) {

            }

            override fun onPageScrollStateChanged(state: Int) {

            }
        }
    }
}
