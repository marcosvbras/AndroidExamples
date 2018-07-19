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

class StarWarsFragment : Fragment() {

    private var textViewFrag1: TextView? = null
    private var imageViewStarWars: ImageView? = null
    private var onDetachFragmentListener: OnDetachFragmentListener? = null
    private var linearLayoutStarWars: LinearLayout? = null

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        return inflater.inflate(R.layout.fragment_star_wars, container, false)
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)

        if (arguments != null) {
            val text = arguments!!.getString(Constants.KEY_TEXT)
            val resource = arguments!!.getInt(Constants.KEY_RESOURCE)
            val color = arguments!!.getInt(Constants.KEY_COLOR)
            textViewFrag1 = view!!.findViewById<View>(R.id.textViewStarWars) as TextView
            imageViewStarWars = view!!.findViewById<View>(R.id.imageViewStarWars) as ImageView
            linearLayoutStarWars = view!!.findViewById<View>(R.id.linearLayoutStarWars) as LinearLayout
            setValues(resource, text, color)
        }

        retainInstance = true
    }

    fun setValues(resource: Int, text: String?, color: Int) {
        imageViewStarWars!!.setImageResource(resource)
        textViewFrag1!!.text = text
        linearLayoutStarWars!!.setBackgroundColor(ContextCompat.getColor(activity!!, color))
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
