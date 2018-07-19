package com.androidexamples.app.flows.viewpager

import android.support.v4.app.Fragment
import android.support.v4.content.ContextCompat
import android.support.v4.view.ViewPager
import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.support.v7.widget.Toolbar
import android.view.View

import com.androidexamples.app.R
import com.androidexamples.app.adapter.TabsAdapter
import com.androidexamples.app.flows.fragments.DefaultFragment
import com.androidexamples.app.view.SlidingTabLayout

import java.util.ArrayList

class SlidingTabLayoutActivity : AppCompatActivity() {

    // Views
    private var viewPager: ViewPager? = null
    private var slidingTabLayout: SlidingTabLayout? = null

    private var listFragment: MutableList<Fragment>? = null
    private var listTitles: MutableList<String>? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_sliding_tab_layout)
        LoadComponents()
    }

    private fun LoadComponents() {
        setSupportActionBar(findViewById<View>(R.id.top_toolbar) as Toolbar)
        supportActionBar!!.setDisplayHomeAsUpEnabled(true)
        viewPager = findViewById<View>(R.id.view_pager) as ViewPager?
        slidingTabLayout = findViewById<View>(R.id.sliding_tab_layout) as SlidingTabLayout?
        populateList()
    }

    private fun populateList() {
        listFragment = ArrayList()

        listFragment!!.add(DefaultFragment.newInstance(R.string.tab_1, android.R.color.white, android.R.color.black))
        listFragment!!.add(DefaultFragment.newInstance(R.string.tab_2, android.R.color.white, android.R.color.black))
        listFragment!!.add(DefaultFragment.newInstance(R.string.tab_3, android.R.color.white, android.R.color.black))
        listFragment!!.add(DefaultFragment.newInstance(R.string.tab_4, android.R.color.white, android.R.color.black))

        listTitles = ArrayList()
        listTitles!!.add(getString(R.string.tab_1))
        listTitles!!.add(getString(R.string.tab_2))
        listTitles!!.add(getString(R.string.tab_3))
        listTitles!!.add(getString(R.string.tab_4))
    }

    override fun onResume() {
        super.onResume()
        // Mantem duas abas (que não estão sendo exibidas) à mais em memória
        viewPager!!.offscreenPageLimit = 2
        viewPager!!.adapter = TabsAdapter(supportFragmentManager, this, listFragment!!, listTitles!!)
        slidingTabLayout!!.setBackgroundColor(ContextCompat.getColor(this, R.color.colorPrimary))
        slidingTabLayout!!.isDistributeEvenly = true
        slidingTabLayout!!.setSelectedIndicatorColors(ContextCompat.getColor(this, R.color.colorAccent), ContextCompat.getColor(this, R.color.colorAccent))
        slidingTabLayout!!.titleColor = ContextCompat.getColor(this, android.R.color.white)
        slidingTabLayout!!.setViewPager(viewPager)
    }
}
