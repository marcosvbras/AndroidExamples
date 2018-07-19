package com.androidexamples.app.flows

import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import android.support.v7.preference.PreferenceFragmentCompat
import android.support.v7.widget.Toolbar
import android.view.View

import com.androidexamples.app.R

class PreferenceScreenActivity : AppCompatActivity() {

    // include 'com.android.support:preference-v7:23.0.1' on gradle
    // no style theme:
    // <item name="preferenceTheme">@style/PreferenceThemeOverlay</item>

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_preference_screen)
        loadComponents()
    }

    private fun loadComponents() {
        setSupportActionBar(findViewById<View>(R.id.top_toolbar) as Toolbar)
        supportActionBar!!.setDisplayHomeAsUpEnabled(true)
        val fragmentTransaction = supportFragmentManager.beginTransaction()
        fragmentTransaction.replace(R.id.container, PreferencesFragment())
        fragmentTransaction.commit()
    }

    class PreferencesFragment : PreferenceFragmentCompat() {

        override fun onCreatePreferences(savedInstanceState: Bundle, rootKey: String) {
            addPreferencesFromResource(R.xml.preferences)
        }
    }
}
