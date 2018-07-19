package com.androidexamples.app.flows.fragments

import android.os.Bundle
import android.support.v4.app.Fragment
import android.support.v4.content.ContextCompat
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.LinearLayout
import android.widget.TextView

import com.androidexamples.app.R
import com.androidexamples.app.interfaces.OnDetachFragmentListener
import com.androidexamples.app.utils.Constants

/**
 * Created by marcos on 29/12/2016.
 */

class FoodFragment : Fragment() {

    // Views
    private var textViewFood: TextView? = null
    private var imageViewFood: ImageView? = null
    private var linearLayoutFood: LinearLayout? = null

    // Another objects
    private var onDetachFragmentListener: OnDetachFragmentListener? = null

    override fun onCreateView(layoutInflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        return layoutInflater.inflate(R.layout.fragment_food, container, false)
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)

        if (arguments != null) {
            textViewFood = view!!.findViewById<View>(R.id.textViewFood) as TextView
            imageViewFood = view!!.findViewById<View>(R.id.imageViewFood) as ImageView
            linearLayoutFood = view!!.findViewById<View>(R.id.linearLayoutFood) as LinearLayout
            val resource = arguments!!.getInt(Constants.KEY_RESOURCE)
            val text = arguments!!.getString(Constants.KEY_TEXT)
            val color = arguments!!.getInt(Constants.KEY_COLOR)
            setValues(resource, text, color)
        }

        retainInstance = true
    }

    private fun setValues(resource: Int, text: String?, color: Int) {
        linearLayoutFood!!.setBackgroundColor(ContextCompat.getColor(activity!!, color))
        imageViewFood!!.setImageResource(resource)
        textViewFood!!.text = text
    }

    fun setOnDetachFragmentListener(onDetachFragmentListener: OnDetachFragmentListener) {
        this.onDetachFragmentListener = onDetachFragmentListener
    }

    override fun onDetach() {
        super.onDetach()

        if (onDetachFragmentListener != null)
            onDetachFragmentListener!!.onDetachFragment(tag!!)
    }
}
