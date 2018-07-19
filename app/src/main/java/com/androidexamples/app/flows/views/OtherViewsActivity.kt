package com.androidexamples.app.flows.views

import android.content.Intent
import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.support.v7.widget.Toolbar
import android.view.View

import com.androidexamples.app.R

class OtherViewsActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_other_views)
        loadComponents()
    }

    private fun loadComponents() {
        setSupportActionBar(findViewById<View>(R.id.top_toolbar) as Toolbar)
        supportActionBar!!.setDisplayHomeAsUpEnabled(true)

        findViewById<View>(R.id.button_webview).setOnClickListener(onButtonClick())
        findViewById<View>(R.id.button_gridview).setOnClickListener(onButtonClick())
        findViewById<View>(R.id.button_xml_selectors).setOnClickListener(onButtonClick())
        findViewById<View>(R.id.button_drawing_shapes).setOnClickListener(onButtonClick())
        findViewById<View>(R.id.button_image_switcher).setOnClickListener(onButtonClick())
        findViewById<View>(R.id.button_recycler_view).setOnClickListener(onButtonClick())
        findViewById<View>(R.id.button_search_view).setOnClickListener(onButtonClick())
        findViewById<View>(R.id.button_spinner_complete_check).setOnClickListener(onButtonClick())
        findViewById<View>(R.id.button_fab_with_snackbar).setOnClickListener(onButtonClick())
    }

    private fun onButtonClick(): View.OnClickListener {
        return View.OnClickListener { v ->
            when (v.id) {
                R.id.button_drawing_shapes -> startActivity(Intent(baseContext, DrawingShapesActivity::class.java))
                R.id.button_fab_with_snackbar -> startActivity(Intent(baseContext, FabWithSnackBarActivity::class.java))
                R.id.button_gridview -> startActivity(Intent(baseContext, GridViewActivity::class.java))
                R.id.button_image_switcher -> startActivity(Intent(baseContext, ImageSwitcherActivity::class.java))
                R.id.button_webview -> startActivity(Intent(baseContext, WebViewSwipeRefreshActivity::class.java))
                R.id.button_recycler_view -> startActivity(Intent(baseContext, RecyclerViewActivity::class.java))
                R.id.button_search_view -> startActivity(Intent(baseContext, SearchViewActivity::class.java))
                R.id.button_spinner_complete_check -> startActivity(Intent(baseContext, SpinnerCompleteCheckActivity::class.java))
                R.id.button_xml_selectors -> startActivity(Intent(baseContext, XmlSelectorsActivity::class.java))
            }
        }
    }
}
