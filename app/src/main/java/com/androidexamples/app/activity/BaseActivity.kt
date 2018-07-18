package com.androidexamples.app.activity

import android.support.design.widget.NavigationView
import android.support.v4.app.Fragment
import android.support.v4.view.GravityCompat
import android.support.v4.widget.DrawerLayout
import android.support.v7.app.ActionBar
import android.support.v7.app.AppCompatActivity
import android.support.v7.widget.Toolbar
import android.view.MenuItem
import android.view.View
import android.widget.ImageView

import com.androidexamples.app.R
import com.androidexamples.app.fragments.DefaultFragment
import com.androidexamples.app.utils.Constants

open class BaseActivity : AppCompatActivity() {

    protected var drawerLayout: DrawerLayout? = null
    protected var navigationView: NavigationView? = null

    protected fun setUpToolbar() {
        val toolbar = findViewById<View>(R.id.top_toolbar) as Toolbar
        if (toolbar != null)
            setSupportActionBar(toolbar)
    }

    protected fun setupNavigationDrawer() {
        val actionBar = supportActionBar

        // Ícone do menu da Nav Drawer
        actionBar!!.setHomeAsUpIndicator(R.drawable.ic_menu_white)
        actionBar.setDisplayHomeAsUpEnabled(true)
        drawerLayout = findViewById<View>(R.id.drawer_layout) as DrawerLayout
        navigationView = findViewById<View>(R.id.navigation_view) as NavigationView

        if (navigationView != null && drawerLayout != null) {
            // Atualiza imagens e textos da header
            setNavViewValues(navigationView!!, R.drawable.jack_sparrow_ptc)

            // Trata o evento de clique do menu
            navigationView!!.setNavigationItemSelectedListener(onNavigationItemSelected())
        }
    }

    protected fun selectSelectedItem(index: Int) {
        if (navigationView != null) {
            navigationView!!.menu.getItem(index).isChecked = true
        }
    }

    private fun onNavigationItemSelected(): NavigationView.OnNavigationItemSelectedListener {
        return NavigationView.OnNavigationItemSelectedListener { menuItem ->
            // Seleciona a linha
            menuItem.isChecked = true
            // Fecha o menu
            drawerLayout!!.closeDrawers()
            // Trata o evento do menu
            onNavDrawerItemSelected(menuItem)

            true
        }
    }

    // Trata o evento do menu lateral
    private fun onNavDrawerItemSelected(menuItem: MenuItem) {
        when (menuItem.itemId) {
            R.id.nav_item_favorite -> replaceFragment(DefaultFragment.newInstance(R.string.favorite, R.color.bg_screen1, android.R.color.white))
            R.id.nav_item_settings -> replaceFragment(DefaultFragment.newInstance(R.string.settings, R.color.bg_screen3, android.R.color.white))
            R.id.nav_item_rate -> replaceFragment(DefaultFragment.newInstance(R.string.rate, R.color.bg_screen5, android.R.color.white))
        }
    }

    protected fun replaceFragment(fragment: Fragment) {
        supportFragmentManager.beginTransaction().replace(R.id.container, fragment, Constants.TAG_FAVORITE_FRAG).commit()
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        when (item.itemId) {
            android.R.id.home ->
                // Trata o clique no botão que abre o menu
                if (drawerLayout != null) {
                    openDrawer()
                    return true
                }
        }

        return super.onOptionsItemSelected(item)
    }

    // Abre o NavigationDrawer
    protected fun openDrawer() {
        if (drawerLayout != null)
            drawerLayout!!.openDrawer(GravityCompat.START)
    }

    // Fecha o NavigationDrawer
    protected fun closeDrawer() {
        if (drawerLayout != null)
            drawerLayout!!.closeDrawer(GravityCompat.START)
    }

    companion object {

        // Atualiza os dados da header do Navigation Viewpublic
        fun setNavViewValues(navigationView: NavigationView, imageResource: Int) {
            val headerView = navigationView.getHeaderView(0)
            val imageView = headerView.findViewById<View>(R.id.imageViewHeader) as ImageView
            imageView.setImageResource(imageResource)
        }
    }
}
