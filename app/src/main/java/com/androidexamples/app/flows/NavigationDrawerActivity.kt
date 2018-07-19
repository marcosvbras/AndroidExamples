package com.androidexamples.app.flows

import android.os.Bundle

import com.androidexamples.app.R
import com.androidexamples.app.flows.fragments.DefaultFragment

class NavigationDrawerActivity : BaseActivity() {

    // Para este exemplo, adicionar a dependência Android Support Library no Gradle
    // Para toda activity que necessita do NavigationDrawer, modificar o Theme da activity no Manifest para @style/AppTheme.NoActionBar.NavDrawer

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_navigation_drawer)
        loadComponents()
    }

    private fun loadComponents() {
        setUpToolbar()
        setupNavigationDrawer()
        selectSelectedItem(0)
        replaceFragment(DefaultFragment.newInstance(R.string.favorite, R.color.bg_screen1, android.R.color.white))
    }
}
