package com.androidexamples.app.listener

import android.content.Context
import android.support.v7.widget.RecyclerView
import android.view.GestureDetector
import android.view.MotionEvent
import com.androidexamples.app.interfaces.RecyclerViewTouchListener

class RecyclerTouchListener(context: Context, private val recyclerView: RecyclerView, private val recyclerViewTouchListener: RecyclerViewTouchListener?) : RecyclerView.OnItemTouchListener {
    private val gestureDetector: GestureDetector

    init {
        gestureDetector = GestureDetector(context, onSimpleGestureListener())
    }

    private fun onSimpleGestureListener(): GestureDetector.SimpleOnGestureListener {
        return object : GestureDetector.SimpleOnGestureListener() {
            override fun onSingleTapUp(motionEvent: MotionEvent): Boolean {
                val clickedView = recyclerView.findChildViewUnder(motionEvent.x, motionEvent.y)

                if (clickedView != null && recyclerViewTouchListener != null)
                    recyclerViewTouchListener.onItemClick(clickedView, recyclerView.getChildAdapterPosition(clickedView))

                return true
            }

            override fun onLongPress(motionEvent: MotionEvent) {
                val clickedView = recyclerView.findChildViewUnder(motionEvent.x, motionEvent.y)

                if (clickedView != null && recyclerViewTouchListener != null)
                    recyclerViewTouchListener.onLongItemClick(clickedView, recyclerView.getChildAdapterPosition(clickedView))
            }
        }
    }

    override fun onInterceptTouchEvent(recycleView: RecyclerView, motionEvent: MotionEvent): Boolean {
        gestureDetector.onTouchEvent(motionEvent)
        return false
    }

    override fun onTouchEvent(view: RecyclerView, motionEvent: MotionEvent) {}

    override fun onRequestDisallowInterceptTouchEvent(disallowIntercept: Boolean) {}
}