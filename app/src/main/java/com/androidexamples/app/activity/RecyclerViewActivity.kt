package com.androidexamples.app.activity

import android.content.DialogInterface
import android.support.v7.app.AlertDialog
import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.support.v7.widget.GridLayoutManager
import android.support.v7.widget.LinearLayoutManager
import android.support.v7.widget.RecyclerView
import android.support.v7.widget.StaggeredGridLayoutManager
import android.support.v7.widget.Toolbar
import android.view.LayoutInflater
import android.view.Menu
import android.view.MenuItem
import android.view.View
import android.widget.ArrayAdapter
import android.widget.RadioButton
import android.widget.Spinner
import android.widget.Toast

import com.androidexamples.app.R
import com.androidexamples.app.adapter.BlueHairAdapter
import com.androidexamples.app.domain.SimpleItem
import com.androidexamples.app.interfaces.RecyclerViewTouchListener
import com.androidexamples.app.listener.RecyclerTouchListener
import com.androidexamples.app.utils.Constants
import com.androidexamples.app.utils.RecyclerSettings

import java.util.ArrayList

class RecyclerViewActivity : AppCompatActivity(), RecyclerViewTouchListener {

    // View
    private var recyclerView: RecyclerView? = null
    private var viewDialog: View? = null
    private var spinnerAnimation: Spinner? = null
    private var spinnerLayoutManager: Spinner? = null
    private var radioButtonAlways: RadioButton? = null
    private var radioButtonNotLoaded: RadioButton? = null

    // Outros objetos
    private var dialogSettings: AlertDialog? = null
    private var blueHairAdapter: BlueHairAdapter? = null
    private var listSimpleItem: MutableList<SimpleItem>? = null
    private var layoutManager: RecyclerView.LayoutManager? = null
    private var linearLayoutManager: LinearLayoutManager? = null
    private var gridLayoutManager: GridLayoutManager? = null
    private var staggeredGridLayoutManager: StaggeredGridLayoutManager? = null
    private var recyclerSettings: RecyclerSettings? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_recycler_view)
        loadComponents()
        loadLayoutManager()
        loadDefaultSettings()
    }

    private fun loadComponents() {
        setSupportActionBar(findViewById<View>(R.id.top_toolbar) as Toolbar)
        supportActionBar!!.setDisplayHomeAsUpEnabled(true)
        recyclerView = findViewById<View>(R.id.recyclerView) as RecyclerView
    }

    private fun loadLayoutManager() {
        linearLayoutManager = LinearLayoutManager(this, LinearLayoutManager.VERTICAL, false)
        linearLayoutManager!!.stackFromEnd = false
        gridLayoutManager = GridLayoutManager(this, 3, GridLayoutManager.VERTICAL, false)
        staggeredGridLayoutManager = StaggeredGridLayoutManager(3, StaggeredGridLayoutManager.VERTICAL)
        staggeredGridLayoutManager!!.reverseLayout = false
    }

    private fun loadDefaultSettings() {
        recyclerSettings = RecyclerSettings()
        recyclerSettings!!.isAlwaysAnimate = false
        recyclerSettings!!.animationConstant = Constants.ANIMATION_SLIDE_IN_LEFT
        recyclerSettings!!.positionForAnimationSpinner = 1
        recyclerSettings!!.layoutManager = linearLayoutManager
        recyclerSettings!!.positionForLayoutSpinner = 0
        layoutManager = recyclerSettings!!.layoutManager
    }

    override fun onResume() {
        super.onResume()
        populateList()
        blueHairAdapter = BlueHairAdapter(listSimpleItem!!, this, recyclerSettings)
        recyclerView!!.adapter = blueHairAdapter
        recyclerView!!.layoutManager = layoutManager
        recyclerView!!.addOnItemTouchListener(RecyclerTouchListener(baseContext, recyclerView!!, this))
        // Melhora a perfomance do RecycleView se o tamanho dos componentes forem fixos e não mudarão
        recyclerView!!.setHasFixedSize(true)
        setDialog()
    }

    private fun populateList() {
        listSimpleItem = ArrayList()
        listSimpleItem!!.add(SimpleItem(1.toString(), R.drawable.bh1))
        listSimpleItem!!.add(SimpleItem(2.toString(), R.drawable.bh2))
        listSimpleItem!!.add(SimpleItem(3.toString(), R.drawable.bh3))
        listSimpleItem!!.add(SimpleItem(4.toString(), R.drawable.bh4))
        listSimpleItem!!.add(SimpleItem(5.toString(), R.drawable.bh5))
        listSimpleItem!!.add(SimpleItem(6.toString(), R.drawable.bh6))
        listSimpleItem!!.add(SimpleItem(7.toString(), R.drawable.bh7))
        listSimpleItem!!.add(SimpleItem(8.toString(), R.drawable.bh8))
        listSimpleItem!!.add(SimpleItem(9.toString(), R.drawable.bh9))
        listSimpleItem!!.add(SimpleItem(10.toString(), R.drawable.bh10))
        listSimpleItem!!.add(SimpleItem(11.toString(), R.drawable.bh11))
        listSimpleItem!!.add(SimpleItem(12.toString(), R.drawable.bh12))
        listSimpleItem!!.add(SimpleItem(13.toString(), R.drawable.bh13))
        listSimpleItem!!.add(SimpleItem(14.toString(), R.drawable.bh14))
        listSimpleItem!!.add(SimpleItem(15.toString(), R.drawable.bh15))
    }

    private fun setDialog() {
        val alertDialogBuilder = AlertDialog.Builder(this)
        alertDialogBuilder.setCancelable(true)
        alertDialogBuilder.setTitle(R.string.recycle_settings)
        alertDialogBuilder.setPositiveButton(R.string.save_changes, onDialogPositiveButtonClick())
        alertDialogBuilder.setNegativeButton(R.string.cancel, null)
        viewDialog = LayoutInflater.from(this).inflate(R.layout.dialog_recycle_settings, null, false)

        val animations = arrayOf(resources.getString(R.string.none), resources.getString(R.string.slide_in_left), resources.getString(R.string.slide_out_right), resources.getString(R.string.fade_in), resources.getString(R.string.fade_out), resources.getString(R.string.scale))
        val layouts = arrayOf(resources.getString(R.string.linear_manager_vertical), resources.getString(R.string.linear_manager_horizontal), resources.getString(R.string.grid_manager_vertical), resources.getString(R.string.grid_manager_horizontal), resources.getString(R.string.staggered_manager_vertical), resources.getString(R.string.staggered_manager_horizontal))

        spinnerAnimation = viewDialog!!.findViewById<View>(R.id.spinnerAnimation) as Spinner
        val adapterAnimations = ArrayAdapter(this, android.R.layout.simple_dropdown_item_1line, animations)
        spinnerAnimation!!.adapter = adapterAnimations

        spinnerLayoutManager = viewDialog!!.findViewById<View>(R.id.spinnerLayoutManager) as Spinner
        val adapterLayouts = ArrayAdapter(this, android.R.layout.simple_dropdown_item_1line, layouts)
        spinnerLayoutManager!!.adapter = adapterLayouts

        radioButtonAlways = viewDialog!!.findViewById<View>(R.id.radioButtonAlways) as RadioButton
        radioButtonNotLoaded = viewDialog!!.findViewById<View>(R.id.radioButtonOnlyNotLoaded) as RadioButton
        alertDialogBuilder.setView(viewDialog)
        dialogSettings = alertDialogBuilder.create()
    }

    private fun loadSettingsOnDialog() {
        spinnerAnimation!!.setSelection(recyclerSettings!!.positionForAnimationSpinner)
        spinnerLayoutManager!!.setSelection(recyclerSettings!!.positionForLayoutSpinner)

        if (recyclerSettings!!.isAlwaysAnimate)
            radioButtonAlways!!.isChecked = true
        else
            radioButtonNotLoaded!!.isChecked = true
    }

    private fun onDialogPositiveButtonClick(): DialogInterface.OnClickListener {
        return DialogInterface.OnClickListener { dialog, which ->
            if (which == DialogInterface.BUTTON_POSITIVE)
                updateSettings()
        }
    }

    private fun updateSettings() {
        recyclerSettings!!.positionForAnimationSpinner = spinnerAnimation!!.selectedItemPosition

        when (recyclerSettings!!.positionForAnimationSpinner) {
            0 -> recyclerSettings!!.animationConstant = Constants.NONE
            1 -> recyclerSettings!!.animationConstant = Constants.ANIMATION_SLIDE_IN_LEFT
            2 -> recyclerSettings!!.animationConstant = Constants.ANIMATION_SLIDE_OUT_RIGHT
            3 -> recyclerSettings!!.animationConstant = Constants.ANIMATION_FADE_IN
            4 -> recyclerSettings!!.animationConstant = Constants.ANIMATION_FADE_OUT
            5 -> recyclerSettings!!.animationConstant = Constants.ANIMATION_SCALE
        }

        blueHairAdapter!!.setLastLoadedViewPosition(0)
        recyclerSettings!!.positionForLayoutSpinner = spinnerLayoutManager!!.selectedItemPosition

        when (recyclerSettings!!.positionForLayoutSpinner) {
            0 -> {
                linearLayoutManager!!.orientation = LinearLayoutManager.VERTICAL
                layoutManager = linearLayoutManager
            }
            1 -> {
                linearLayoutManager!!.orientation = LinearLayoutManager.HORIZONTAL
                layoutManager = linearLayoutManager
            }
            2 -> {
                gridLayoutManager!!.orientation = GridLayoutManager.VERTICAL
                layoutManager = gridLayoutManager
            }
            3 -> {
                gridLayoutManager!!.orientation = GridLayoutManager.HORIZONTAL
                layoutManager = gridLayoutManager
            }
            4 -> {
                staggeredGridLayoutManager!!.orientation = StaggeredGridLayoutManager.VERTICAL
                staggeredGridLayoutManager!!.gapStrategy = StaggeredGridLayoutManager.GAP_HANDLING_MOVE_ITEMS_BETWEEN_SPANS
                layoutManager = staggeredGridLayoutManager
            }
            5 -> {
                staggeredGridLayoutManager!!.orientation = StaggeredGridLayoutManager.HORIZONTAL
                layoutManager = staggeredGridLayoutManager
            }
        }

        recyclerSettings!!.layoutManager = layoutManager
        recyclerSettings!!.isAlwaysAnimate = radioButtonAlways!!.isChecked
        blueHairAdapter!!.setRecyclerSettings(recyclerSettings!!)
        recyclerView!!.layoutManager = recyclerSettings!!.layoutManager
        Toast.makeText(this, R.string.scroll_to_see, Toast.LENGTH_SHORT).show()
    }

    override fun onCreateOptionsMenu(menu: Menu): Boolean {
        menuInflater.inflate(R.menu.menu_recycle, menu)
        return super.onCreateOptionsMenu(menu)
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {

        when (item.itemId) {
            R.id.menu_recycle_settings -> {
                loadSettingsOnDialog()
                dialogSettings!!.show()
            }
        }

        return super.onOptionsItemSelected(item)
    }

    override fun onItemClick(view: View, position: Int) {
        Toast.makeText(baseContext, "Item " + blueHairAdapter!!.getItemAtPosition(position).name + " single click", Toast.LENGTH_SHORT).show()
    }

    override fun onLongItemClick(view: View, position: Int) {
        Toast.makeText(baseContext, "Item " + blueHairAdapter!!.getItemAtPosition(position).name + " long click", Toast.LENGTH_SHORT).show()
    }
}
