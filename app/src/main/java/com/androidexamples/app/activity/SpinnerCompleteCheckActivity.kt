package com.androidexamples.app.activity

import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.support.v7.widget.Toolbar
import android.view.View
import android.widget.AdapterView
import android.widget.ArrayAdapter
import android.widget.AutoCompleteTextView
import android.widget.CheckBox
import android.widget.Spinner
import android.widget.Toast

import com.androidexamples.app.R
import com.androidexamples.app.domain.SimpleItem

import java.util.ArrayList

class SpinnerCompleteCheckActivity : AppCompatActivity() {

    // Views
    private var autoCompleteTextView: AutoCompleteTextView? = null
    private var check1: CheckBox? = null
    private var check2: CheckBox? = null
    private var check3: CheckBox? = null
    private var check4: CheckBox? = null
    private var check5: CheckBox? = null
    private var spinner: Spinner? = null

    // Outros Objetos
    private var listSimpleItems: MutableList<SimpleItem>? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_spinner_complete_check)
        loadComponents()
    }

    override fun onResume() {
        super.onResume()
        populateList()
        val adapter = ArrayAdapter(this, android.R.layout.simple_dropdown_item_1line, listSimpleItems!!)
        spinner!!.adapter = adapter
        autoCompleteTextView!!.setAdapter(adapter)
        autoCompleteTextView!!.clearFocus()
    }

    private fun loadComponents() {
        setSupportActionBar(findViewById<View>(R.id.top_toolbar) as Toolbar)
        supportActionBar!!.setDisplayHomeAsUpEnabled(true)
        autoCompleteTextView = findViewById<View>(R.id.autoComplete) as AutoCompleteTextView
        autoCompleteTextView!!.onItemClickListener = onAutoCompleteTextViewItemClick()
        check1 = findViewById<View>(R.id.checkbox1) as CheckBox
        check1!!.setOnClickListener(onCheckStar())
        check2 = findViewById<View>(R.id.checkbox2) as CheckBox
        check2!!.setOnClickListener(onCheckStar())
        check3 = findViewById<View>(R.id.checkbox3) as CheckBox
        check3!!.setOnClickListener(onCheckStar())
        check4 = findViewById<View>(R.id.checkbox4) as CheckBox
        check4!!.setOnClickListener(onCheckStar())
        check5 = findViewById<View>(R.id.checkbox5) as CheckBox
        check5!!.setOnClickListener(onCheckStar())
        spinner = findViewById<View>(R.id.spinner) as Spinner
        spinner!!.onItemSelectedListener = onSpinnerItemSelected()
    }

    private fun populateList() {
        listSimpleItems = ArrayList()
        listSimpleItems!!.add(SimpleItem("Arryn", R.drawable.arryn))
        listSimpleItems!!.add(SimpleItem("Baratheon", R.drawable.baratheon))
        listSimpleItems!!.add(SimpleItem("Greyjoy", R.drawable.greyjoy))
        listSimpleItems!!.add(SimpleItem("Lannister", R.drawable.lannister))
        listSimpleItems!!.add(SimpleItem("Martell", R.drawable.martell))
        listSimpleItems!!.add(SimpleItem("Stark", R.drawable.stark))
        listSimpleItems!!.add(SimpleItem("Targaryan", R.drawable.targaryen))
        listSimpleItems!!.add(SimpleItem("Tully", R.drawable.tully))
        listSimpleItems!!.add(SimpleItem("Tyrell", R.drawable.tyrell))
    }

    private fun onCheckStar(): View.OnClickListener {
        return View.OnClickListener { view ->
            val checkBox = view as CheckBox

            if (checkBox.id == R.id.checkbox1) {
                check1!!.isChecked = true
                check2!!.isChecked = false
                check3!!.isChecked = false
                check4!!.isChecked = false
                check5!!.isChecked = false
            } else if (checkBox.id == R.id.checkbox2) {
                check1!!.isChecked = true
                check2!!.isChecked = true
                check3!!.isChecked = false
                check4!!.isChecked = false
                check5!!.isChecked = false
            } else if (checkBox.id == R.id.checkbox3) {
                check1!!.isChecked = true
                check2!!.isChecked = true
                check3!!.isChecked = true
                check4!!.isChecked = false
                check5!!.isChecked = false
            } else if (checkBox.id == R.id.checkbox4) {
                check1!!.isChecked = true
                check2!!.isChecked = true
                check3!!.isChecked = true
                check4!!.isChecked = true
                check5!!.isChecked = false
            } else if (checkBox.id == R.id.checkbox5) {
                check1!!.isChecked = true
                check2!!.isChecked = true
                check3!!.isChecked = true
                check4!!.isChecked = true
                check5!!.isChecked = true
            }
        }
    }

    private fun onAutoCompleteTextViewItemClick(): AdapterView.OnItemClickListener {
        return AdapterView.OnItemClickListener { adapterView, view, position, l ->
            val simpleItem = adapterView.selectedItem as SimpleItem
            Toast.makeText(baseContext, simpleItem.name!! + " selected!", Toast.LENGTH_SHORT).show()
        }
    }

    private fun onSpinnerItemSelected(): AdapterView.OnItemSelectedListener {
        return object : AdapterView.OnItemSelectedListener {
            override fun onItemSelected(adapterView: AdapterView<*>, view: View, position: Int, l: Long) {
                val simpleItem = adapterView.selectedItem as SimpleItem
                Toast.makeText(baseContext, simpleItem.name!! + " selected!", Toast.LENGTH_SHORT).show()
            }

            override fun onNothingSelected(adapterView: AdapterView<*>) {}
        }
    }
}
