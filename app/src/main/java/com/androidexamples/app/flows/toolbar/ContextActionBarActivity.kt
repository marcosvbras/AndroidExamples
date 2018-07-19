package com.androidexamples.app.flows.toolbar

import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.support.v7.view.ActionMode
import android.support.v7.widget.LinearLayoutManager
import android.support.v7.widget.RecyclerView
import android.support.v7.widget.Toolbar
import android.view.Menu
import android.view.MenuItem
import android.view.View
import android.widget.Toast

import com.androidexamples.app.R
import com.androidexamples.app.adapter.ContextAdapter
import com.androidexamples.app.domain.ContextExample
import com.androidexamples.app.interfaces.RecyclerViewTouchListener
import com.androidexamples.app.listener.RecyclerTouchListener

import java.util.ArrayList

class ContextActionBarActivity : AppCompatActivity(), RecyclerViewTouchListener {

    /**
     * Necessita adicionar no tema:
     * <item name="actionModeBackground">@color/colorPrimary</item>
     * <item name="windowActionModeOverlay">true</item>
     */

    // Views
    private var recyclerView: RecyclerView? = null

    // Another objects
    private var contextAdapter: ContextAdapter? = null
    private var listExamples: MutableList<ContextExample>? = null
    private var actionMode: ActionMode? = null

    // Infla o menu
    // Encerra o ActionMode
    // Limpa o estado
    val actionModeCallBack: ActionMode.Callback
        get() = object : ActionMode.Callback {
            override fun onCreateActionMode(mode: ActionMode, menu: Menu): Boolean {
                val menuInflater = menuInflater
                menuInflater.inflate(R.menu.menu_context, menu)
                return true
            }

            override fun onPrepareActionMode(mode: ActionMode, menu: Menu): Boolean {
                return true
            }

            override fun onActionItemClicked(mode: ActionMode, menuItem: MenuItem): Boolean {
                if (menuItem.itemId == R.id.menu_item_delete)
                    Toast.makeText(baseContext, "Click", Toast.LENGTH_SHORT).show()
                mode.finish()
                return true
            }

            override fun onDestroyActionMode(mode: ActionMode) {
                actionMode = null

                for (item in listExamples!!)
                    item.isSelected = false

                contextAdapter!!.notifyDataSetChanged()
            }
        }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_context_action_bar)
        loadComponents()
    }

    private fun loadComponents() {
        setSupportActionBar(findViewById<View>(R.id.top_toolbar) as Toolbar)
        supportActionBar!!.setDisplayHomeAsUpEnabled(true)
        recyclerView = findViewById<View>(R.id.recyclerView) as RecyclerView
        recyclerView!!.addOnItemTouchListener(RecyclerTouchListener(this, recyclerView!!, this))
        recyclerView!!.setHasFixedSize(true)
    }

    private fun populateList() {
        listExamples = ArrayList()

        for (i in 1..19)
            listExamples!!.add(ContextExample("Item $i", false))
    }

    override fun onResume() {
        super.onResume()

        populateList()
        contextAdapter = ContextAdapter(listExamples, this)
        recyclerView!!.layoutManager = LinearLayoutManager(this, LinearLayoutManager.VERTICAL, false)
        recyclerView!!.adapter = contextAdapter
    }

    override fun onItemClick(view: View, position: Int) {
        if (actionMode != null) {
            val contextExample = contextAdapter!!.getItemAtPosition(position)
            contextExample.isSelected = !contextExample.isSelected
            contextAdapter!!.notifyDataSetChanged()
            updateActionModeTitle()
        }
    }

    override fun onLongItemClick(view: View, position: Int) {
        // Só o action mode já existe,
        if (actionMode != null)
            return

        actionMode = startSupportActionMode(actionModeCallBack)

        val contextExample = contextAdapter!!.getItemAtPosition(position)
        contextExample.isSelected = true
        contextAdapter!!.notifyDataSetChanged()
        updateActionModeTitle()
    }

    private fun updateActionModeTitle() {
        if (actionMode != null) {
            var count = 0

            for (item in listExamples!!) {
                if (item.isSelected)
                    count++
            }

            if (count > 0) {
                actionMode!!.subtitle = null
                actionMode!!.title = count.toString() + " selected items"
            } else {
                actionMode!!.finish()
                actionMode = null
            }
        }
    }
}
