package com.androidexamples.app.flows.animations.activityAnimations

import android.content.Intent
import android.support.v4.app.ActivityCompat
import android.support.v4.app.ActivityOptionsCompat
import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.support.v7.widget.Toolbar
import android.view.View
import android.view.Window
import android.widget.ImageView

import com.androidexamples.app.R
import com.androidexamples.app.utils.Constants

class ActivityAnimationsActivity : AppCompatActivity() {

    // Views
    private var imageView: ImageView? = null

    // Another Objects
    private var newIntent: Intent? = null
    private var bundle: Bundle? = null
    private var activityOptionsCompat: ActivityOptionsCompat? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        window.requestFeature(Window.FEATURE_CONTENT_TRANSITIONS)
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_animations_activity)
        loadComponents()
    }

    private fun loadComponents() {
        setSupportActionBar(findViewById<View>(R.id.top_toolbar) as Toolbar)
        supportActionBar!!.setDisplayHomeAsUpEnabled(true)
        imageView = findViewById<View>(R.id.imageView) as ImageView
        findViewById<View>(R.id.button_fade).setOnClickListener(onFadeButtonClick())
        findViewById<View>(R.id.button_slide_right).setOnClickListener(onSlideRightButtonClick())
        findViewById<View>(R.id.button_slide_left).setOnClickListener(onSlideLeftButtonClick())
        findViewById<View>(R.id.button_scale).setOnClickListener(onScaleButtonClick())
        findViewById<View>(R.id.button_zoom).setOnClickListener(onZoomButtonClick())
        newIntent = Intent(baseContext, TestActivity::class.java)
        newIntent!!.flags = Intent.FLAG_ACTIVITY_NEW_TASK
    }

    private fun onZoomButtonClick(): View.OnClickListener {
        return View.OnClickListener {
            val opts = ActivityOptionsCompat.makeCustomAnimation(baseContext,
                    R.anim.zoom_enter, R.anim.zoom_enter)
            ActivityCompat.startActivity(baseContext, newIntent!!, opts.toBundle())
        }
    }

    private fun onScaleButtonClick(): View.OnClickListener {
        return View.OnClickListener { v ->
            bundle = ActivityOptionsCompat.makeScaleUpAnimation(v, 0, 0, v.width, v.height).toBundle()
            ActivityCompat.startActivity(baseContext, newIntent!!, bundle)
        }
    }

    private fun onSlideLeftButtonClick(): View.OnClickListener {
        return View.OnClickListener {
            bundle = Bundle()
            bundle!!.putInt(Constants.KEY_ANIMATION, Constants.ANIMATION_SLIDE_LEFT)
            newIntent!!.putExtras(bundle!!)

            activityOptionsCompat = ActivityOptionsCompat.makeCustomAnimation(baseContext,
                    R.anim.slide_in_left, R.anim.slide_out_left)
            ActivityCompat.startActivity(baseContext, newIntent!!, activityOptionsCompat!!.toBundle())
        }
    }

    private fun onSlideRightButtonClick(): View.OnClickListener {
        return View.OnClickListener {
            bundle = Bundle()
            bundle!!.putInt(Constants.KEY_ANIMATION, Constants.ANIMATION_SLIDE_RIGHT)
            newIntent!!.putExtras(bundle!!)

            activityOptionsCompat = ActivityOptionsCompat.makeCustomAnimation(baseContext,
                    R.anim.slide_in_right, R.anim.slide_out_right)
            ActivityCompat.startActivity(baseContext, newIntent!!, activityOptionsCompat!!.toBundle())
        }
    }

    private fun onFadeButtonClick(): View.OnClickListener {
        return View.OnClickListener {
            bundle = Bundle()
            bundle!!.putInt(Constants.KEY_ANIMATION, Constants.ANIMATION_FADE)
            newIntent!!.putExtras(bundle!!)

            activityOptionsCompat = ActivityOptionsCompat.makeCustomAnimation(baseContext,
                    android.R.anim.fade_in, android.R.anim.fade_out)
            ActivityCompat.startActivity(baseContext, newIntent!!, activityOptionsCompat!!.toBundle())
        }
    }
}
