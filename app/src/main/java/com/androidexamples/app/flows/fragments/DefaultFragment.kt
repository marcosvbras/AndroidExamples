package com.androidexamples.app.flows.fragments

import android.os.Bundle
import android.support.v4.content.ContextCompat
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.FrameLayout
import android.widget.TextView

import com.androidexamples.app.R
import com.androidexamples.app.utils.Constants

/**
 * Created by marcos on 08/01/2017.
 */

class DefaultFragment : BaseFragment() {

    // Views
    private var textView: TextView? = null
    private var container: FrameLayout? = null

    private var stringResource: Int = 0
    private var colorResource: Int = 0
    private var textColorResource: Int = 0

    override fun onCreateView(layoutInflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        return layoutInflater.inflate(R.layout.fragment_default, container, false)
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)

        if (arguments != null) {
            stringResource = arguments!!.getInt(Constants.KEY_TEXT)
            colorResource = arguments!!.getInt(Constants.KEY_COLOR)
            textColorResource = arguments!!.getInt(Constants.KEY_TEXT_COLOR)
            textView = view!!.findViewById<View>(R.id.textView) as TextView
            container = view!!.findViewById<View>(R.id.container_root) as FrameLayout
            setValues()
        }
    }

    private fun setValues() {
        textView!!.text = getString(stringResource)
        textView!!.setTextColor(ContextCompat.getColor(activity!!, textColorResource))
        container!!.setBackgroundColor(ContextCompat.getColor(activity!!, colorResource))
    }

    companion object {

        fun newInstance(stringResource: Int, colorResource: Int, textColorResource: Int): DefaultFragment {
            val bundle = Bundle()
            bundle.putInt(Constants.KEY_COLOR, colorResource)
            bundle.putInt(Constants.KEY_TEXT, stringResource)
            bundle.putInt(Constants.KEY_TEXT_COLOR, textColorResource)
            val defaultFragment = DefaultFragment()
            defaultFragment.arguments = bundle
            return defaultFragment
        }
    }
}
