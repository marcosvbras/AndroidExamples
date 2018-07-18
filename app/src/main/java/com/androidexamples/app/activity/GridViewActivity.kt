package com.androidexamples.app.activity

import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.support.v7.widget.Toolbar
import android.view.View
import android.widget.GridView

import com.androidexamples.app.R
import com.androidexamples.app.adapter.GridAdapter
import com.androidexamples.app.domain.SimpleItem

import java.util.ArrayList

class GridViewActivity : AppCompatActivity() {

    private var gridView: GridView? = null
    private var adapter: GridAdapter? = null
    private var listItems: MutableList<SimpleItem>? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_grid_view)
        loadComponents()
    }

    private fun loadComponents() {
        setSupportActionBar(findViewById<View>(R.id.top_toolbar) as Toolbar)
        supportActionBar!!.setDisplayHomeAsUpEnabled(true)
        gridView = findViewById<View>(R.id.gridView) as GridView?
    }

    override fun onResume() {
        super.onResume()
        populateList()
        adapter = GridAdapter(this, listItems!!)
        gridView!!.adapter = adapter
    }

    private fun populateList() {
        listItems = ArrayList()
        listItems!!.add(SimpleItem("Arryn", R.drawable.arryn))
        listItems!!.add(SimpleItem("Baratheon", R.drawable.baratheon))
        listItems!!.add(SimpleItem("Greyjoy", R.drawable.greyjoy))
        listItems!!.add(SimpleItem("Lannister", R.drawable.lannister))
        listItems!!.add(SimpleItem("Martell", R.drawable.martell))
        listItems!!.add(SimpleItem("Stark", R.drawable.stark))
        listItems!!.add(SimpleItem("Targaryan", R.drawable.targaryen))
        listItems!!.add(SimpleItem("Tully", R.drawable.tully))
        listItems!!.add(SimpleItem("Tyrell", R.drawable.tyrell))
    }
}
