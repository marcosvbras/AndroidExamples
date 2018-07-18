package com.androidexamples.app.activity

import android.content.Intent
import android.graphics.Color
import android.os.Build
import android.support.v4.view.ViewPager
import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.text.Html
import android.view.View
import android.view.Window
import android.view.WindowManager
import android.widget.Button
import android.widget.LinearLayout
import android.widget.TextView

import com.androidexamples.app.R
import com.androidexamples.app.adapter.IntroAdapter
import com.androidexamples.app.utils.PreferencesManager

import java.util.ArrayList

class IntroSliderActivity : AppCompatActivity() {

    private var viewPager: ViewPager? = null
    private var introAdapter: IntroAdapter? = null
    private var linearLayoutDots: LinearLayout? = null
    private var listTextViewDots: MutableList<TextView>? = null
    private var listLayouts: MutableList<Int>? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_intro_slider)

        // Checking for first time launch - before calling setContentView()
        if (!PreferencesManager.isFirstTimeLaunch(this)) {
            launchHomeScreen()
            finish()
        }

        // Making notification bar transparent
        if (Build.VERSION.SDK_INT >= 21)
            window.decorView.systemUiVisibility = View.SYSTEM_UI_FLAG_LAYOUT_STABLE or View.SYSTEM_UI_FLAG_LAYOUT_FULLSCREEN

        loadComponents()
    }

    private fun loadComponents() {
        viewPager = findViewById<View>(R.id.view_pager) as ViewPager
        linearLayoutDots = findViewById<View>(R.id.layoutDots) as LinearLayout
        findViewById<View>(R.id.button_skip).setOnClickListener(onButtonClick())
        findViewById<View>(R.id.btn_next).setOnClickListener(onButtonClick())
    }

    private fun onButtonClick(): View.OnClickListener {
        return View.OnClickListener { v ->
            when (v.id) {
                R.id.button_skip -> launchHomeScreen()
                R.id.btn_next -> {
                    // if last page home screen will be launched
                    val current = getItem(+1)
                    if (current < listLayouts!!.size)
                        viewPager!!.currentItem = current // move to next screen
                    else
                        launchHomeScreen()
                }
            }
        }
    }

    override fun onResume() {
        super.onResume()
        // listLayouts of all welcome sliders
        // add few more listLayouts if you want
        listLayouts = ArrayList()
        listLayouts!!.add(R.layout.welcome_slide1)
        listLayouts!!.add(R.layout.welcome_slide2)
        listLayouts!!.add(R.layout.welcome_slide3)
        listLayouts!!.add(R.layout.welcome_slide4)

        // adding bottom listTextViewDots
        addBottomDots(0)

        // making notification bar transparent
        changeStatusBarColor()

        introAdapter = IntroAdapter(this, listLayouts!!)
        viewPager!!.adapter = introAdapter
        viewPager!!.addOnPageChangeListener(onViewPagerPageChange())
    }

    private fun onViewPagerPageChange(): ViewPager.OnPageChangeListener {
        return object : ViewPager.OnPageChangeListener {
            override fun onPageScrolled(position: Int, positionOffset: Float, positionOffsetPixels: Int) {

            }

            override fun onPageSelected(position: Int) {
                addBottomDots(position)

                // changing the next button text 'NEXT' / 'GOT IT'
                if (position == listLayouts!!.size - 1) {
                    // last page. make button text to GOT IT
                    (findViewById<View>(R.id.btn_next) as Button).text = getString(R.string.got_it)
                    findViewById<View>(R.id.button_skip).visibility = View.GONE
                } else {
                    // still pages are left
                    (findViewById<View>(R.id.btn_next) as Button).text = getString(R.string.next)
                    findViewById<View>(R.id.button_skip).visibility = View.VISIBLE
                }
            }

            override fun onPageScrollStateChanged(state: Int) {

            }
        }
    }

    private fun addBottomDots(currentPage: Int) {
        listTextViewDots = ArrayList()
        val colorsActive = resources.getIntArray(R.array.array_dot_active)
        val colorsInactive = resources.getIntArray(R.array.array_dot_inactive)

        linearLayoutDots!!.removeAllViews()
        for (i in listTextViewDots!!.indices) {
            listTextViewDots!!.add(TextView(this))
            listTextViewDots!![i].text = Html.fromHtml("&#8226;")
            listTextViewDots!![i].textSize = 35f
            listTextViewDots!![i].setTextColor(colorsInactive[currentPage])
            linearLayoutDots!!.addView(listTextViewDots!![i])
        }

        if (listTextViewDots!!.size > 0)
            listTextViewDots!![currentPage].setTextColor(colorsActive[currentPage])
    }

    private fun getItem(i: Int): Int {
        return viewPager!!.currentItem + i
    }

    private fun launchHomeScreen() {
        PreferencesManager.setFirstTimeLaunch(this, false)
        startActivity(Intent(this, MainActivity::class.java))
        finish()
    }

    /**
     * Making notification bar transparent
     */
    private fun changeStatusBarColor() {
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
            val window = window
            window.addFlags(WindowManager.LayoutParams.FLAG_DRAWS_SYSTEM_BAR_BACKGROUNDS)
            window.statusBarColor = Color.TRANSPARENT
        }
    }
}

