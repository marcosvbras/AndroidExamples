package com.androidexamples.app.activity

import android.content.Intent
import android.support.v4.view.MenuItemCompat
import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.support.v7.widget.SearchView
import android.support.v7.widget.ShareActionProvider
import android.support.v7.widget.Toolbar
import android.view.Menu
import android.view.MenuItem
import android.view.View
import android.widget.AdapterView
import android.widget.ListView
import android.widget.TextView
import android.widget.Toast

import com.androidexamples.app.R
import com.androidexamples.app.adapter.FilterableAdapter
import com.androidexamples.app.interfaces.FilterObserverListener

import java.util.ArrayList

class SearchViewActivity : AppCompatActivity(), FilterObserverListener {

    // Views
    private var listView: ListView? = null
    private var searchView: SearchView? = null

    // Another objects
    private var adapter: FilterableAdapter? = null

    private val defaultIntent: Intent
        get() {
            val intent = Intent(Intent.ACTION_SEND)
            intent.type = "text/*"
            intent.putExtra(Intent.EXTRA_TEXT, "Texto para compartilhar")
            return intent
        }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_search_view)
        loadComponents()
    }

    private fun loadComponents() {
        setSupportActionBar(findViewById<View>(R.id.top_toolbar) as Toolbar)
        supportActionBar!!.setDisplayHomeAsUpEnabled(true)
        listView = findViewById<View>(R.id.listView) as ListView
        listView!!.onItemClickListener = onItemClick()
    }

    private fun onItemClick(): AdapterView.OnItemClickListener {
        return AdapterView.OnItemClickListener { adapterView, view, i, l ->
            val item = adapter!!.getItem(i) as String
            Toast.makeText(baseContext, "$item clicked!", Toast.LENGTH_SHORT).show()
        }
    }

    override fun onResume() {
        super.onResume()

        val listItems = ArrayList<String>()

        for (i in 1..20)
            listItems.add("Item $i")

        adapter = FilterableAdapter(this, listItems)
        adapter!!.setFilterObserverListener(this)
        listView!!.adapter = adapter
    }

    override fun onCreateOptionsMenu(menu: Menu): Boolean {
        menuInflater.inflate(R.menu.menu_search, menu)

        // SearchView
        val menuItemSearch = menu.findItem(R.id.search)
        searchView = menuItemSearch.actionView as SearchView
        searchView!!.setOnQueryTextListener(onSearch())
        searchView!!.setOnCloseListener(onCloseSearch())

        // Action Provider
        val menuItemShare = menu.findItem(R.id.share)
        val shareAction = MenuItemCompat.getActionProvider(menuItemShare) as ShareActionProvider
        shareAction.setShareIntent(defaultIntent)

        return super.onCreateOptionsMenu(menu)
    }

    private fun onCloseSearch(): SearchView.OnCloseListener {
        return SearchView.OnCloseListener {
            adapter!!.filter.filter(null)
            false
        }
    }

    private fun onSearch(): SearchView.OnQueryTextListener {
        return object : SearchView.OnQueryTextListener {
            override fun onQueryTextSubmit(query: String): Boolean {
                adapter!!.filter.filter(query)
                return true
            }

            override fun onQueryTextChange(newText: String): Boolean {
                return false
            }
        }
    }

    override fun onFinishFiltering(countItems: Int) {
        if (countItems > 0)
            listView!!.visibility = View.VISIBLE
        else {
            listView!!.visibility = View.GONE
            (findViewById<View>(R.id.textViewMessage) as TextView).text = (resources.getString(R.string.no_results_for)
                    + " '" + searchView!!.query.toString() + "'")
        }
    }
}
