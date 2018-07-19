package com.androidexamples.app.flows

import android.graphics.drawable.BitmapDrawable
import android.support.v4.content.ContextCompat
import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.support.v7.graphics.Palette
import android.support.v7.widget.Toolbar
import android.view.View
import android.widget.ImageView
import android.widget.LinearLayout

import com.androidexamples.app.R

class PaletteActivity : AppCompatActivity() {

    // Views
    private var linearLayout1: LinearLayout? = null
    private var linearLayout2: LinearLayout? = null
    private var linearLayout3: LinearLayout? = null
    private var linearLayout4: LinearLayout? = null
    private var linearLayout5: LinearLayout? = null
    private var linearLayout6: LinearLayout? = null
    private var linearLayoutActivity: LinearLayout? = null
    private var imageView: ImageView? = null

    private val defauColor: Int
        get() = ContextCompat.getColor(this, R.color.colorPrimary)

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_palette)
        loadComponents()
    }

    private fun loadComponents() {
        setSupportActionBar(findViewById<View>(R.id.top_toolbar) as Toolbar)
        supportActionBar!!.setDisplayHomeAsUpEnabled(true)

        linearLayout1 = findViewById<View>(R.id.linearLayout1) as LinearLayout
        linearLayout2 = findViewById<View>(R.id.linearLayout2) as LinearLayout
        linearLayout3 = findViewById<View>(R.id.linearLayout3) as LinearLayout
        linearLayout4 = findViewById<View>(R.id.linearLayout4) as LinearLayout
        linearLayout5 = findViewById<View>(R.id.linearLayout5) as LinearLayout
        linearLayout6 = findViewById<View>(R.id.linearLayout6) as LinearLayout
        linearLayoutActivity = findViewById<View>(R.id.activity_palette) as LinearLayout
        imageView = findViewById<View>(R.id.imageView) as ImageView

        findViewById<View>(R.id.button_extract_colors).setOnClickListener(onExtracColorsButtonClick())
    }

    private fun onExtracColorsButtonClick(): View.OnClickListener {
        return View.OnClickListener {
            val bitmap = (imageView!!.drawable as BitmapDrawable).bitmap
            // Necessário inclusão de dependência no gradle
            Palette.from(bitmap).generate(onGenerateFinish())
        }
    }

    private fun onGenerateFinish(): Palette.PaletteAsyncListener {
        return Palette.PaletteAsyncListener { palette ->
            linearLayout1!!.setBackgroundColor(palette.getVibrantColor(defauColor))
            linearLayout2!!.setBackgroundColor(palette.getDarkVibrantColor(defauColor))
            linearLayout3!!.setBackgroundColor(palette.getLightVibrantColor(defauColor))
            linearLayout4!!.setBackgroundColor(palette.getMutedColor(defauColor))
            linearLayout5!!.setBackgroundColor(palette.getDarkMutedColor(defauColor))
            linearLayout6!!.setBackgroundColor(palette.getLightMutedColor(defauColor))
            linearLayoutActivity!!.setBackgroundColor(palette.getVibrantColor(defauColor))
        }
    }
}
