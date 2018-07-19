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
import com.androidexamples.app.utils.Constants

/**
 * Created by marcos on 29/12/2016.
 */

class AnimalFragment : Fragment() {

    // Views
    private var textViewAnimals: TextView? = null
    private var imageViewAnimals: ImageView? = null
    private var linearLayoutAnimals: LinearLayout? = null

    override fun onCreateView(layoutInflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        return layoutInflater.inflate(R.layout.fragment_animals, container, false)
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)

        if (arguments != null) {
            textViewAnimals = view!!.findViewById<View>(R.id.textViewAnimals) as TextView
            imageViewAnimals = view!!.findViewById<View>(R.id.imageViewAnimals) as ImageView
            linearLayoutAnimals = view!!.findViewById<View>(R.id.linearLayoutAnimals) as LinearLayout
            val color = arguments!!.getInt(Constants.KEY_COLOR)
            val imageResource = arguments!!.getInt(Constants.KEY_RESOURCE)
            val text = arguments!!.getString(Constants.KEY_TEXT)
            setValues(color, imageResource, text)
        }

        retainInstance = true
    }

    private fun setValues(color: Int, imageResource: Int, text: String?) {
        linearLayoutAnimals!!.setBackgroundColor(ContextCompat.getColor(activity!!, color))
        imageViewAnimals!!.setImageResource(imageResource)
        textViewAnimals!!.text = text
    }
}
