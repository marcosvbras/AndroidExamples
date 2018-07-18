package com.androidexamples.app.interfaces

import android.view.View

/**
 * Created by marcosvbras on 25/12/2016.
 */

interface RecyclerViewTouchListener {
    fun onItemClick(view: View, position: Int)
    fun onLongItemClick(view: View, position: Int)
}
