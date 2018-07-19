package com.androidexamples.app.view

import android.annotation.SuppressLint
import android.content.Context
import android.graphics.Canvas
import android.graphics.Color
import android.graphics.Paint
import android.graphics.drawable.Drawable
import android.util.AttributeSet
import android.view.MotionEvent
import android.view.View

import com.androidexamples.app.R

/**
 * Created by marcos on 16/12/2016.
 */

class DraggableView : View {

    private var drawableImage: Drawable? = null
    private var x: Int = 0
    private var y: Int = 0
    private var viewSelected: Boolean = false
    private var screenWidth: Int = 0
    private var screenHeight: Int = 0
    private var imageWidth: Int = 0
    private var imageHeight: Int = 0

    constructor(context: Context) : super(context, null) {
        drawableImage = context.resources.getDrawable(R.drawable.x_wing)
        imageWidth = drawableImage!!.intrinsicWidth
        imageHeight = drawableImage!!.intrinsicHeight
        // Set focus to receive keyboard events
        isFocusable = true
    }

    constructor(context: Context, attributeSet: AttributeSet) : super(context, attributeSet) {
        drawableImage = context.resources.getDrawable(R.drawable.x_wing)
        imageWidth = drawableImage!!.intrinsicWidth
        imageHeight = drawableImage!!.intrinsicHeight
        // Set focus to receive keyboard events
        isFocusable = true
    }

    // Called when screen is resized or started
    override fun onSizeChanged(currentWidth: Int, currentHeight: Int, oldWidth: Int, oldHeight: Int) {
        super.onSizeChanged(currentWidth, currentHeight, oldWidth, oldHeight)
        this.screenWidth = currentWidth
        this.screenHeight = currentHeight

        x = currentWidth / 2 - imageWidth / 2
        y = currentHeight / 2 - imageHeight / 2
    }

    /*
    * This method draw the screen
    * It is called on screen's creation and after onTouchEvent method to update it
    * */
    @SuppressLint("DrawAllocation")
    override fun onDraw(canvas: Canvas) {
        super.onDraw(canvas)
        // Creating white background on screen
        val paint = Paint()
        paint.color = Color.WHITE
        canvas.drawRect(0f, 0f, screenWidth.toFloat(), screenHeight.toFloat(), paint)
        // Set limits/points/draw area
        drawableImage!!.setBounds(x, y, x + imageWidth, y + imageHeight)
        // Draw image on screen
        drawableImage!!.draw(canvas)
    }

    // Move the image
    override fun onTouchEvent(event: MotionEvent): Boolean {
        val x = event.x
        val y = event.y

        when (event.action) {
            MotionEvent.ACTION_DOWN ->
                // Start the movement if image was pressed
                viewSelected = drawableImage!!.copyBounds().contains(x.toInt(), y.toInt())
            MotionEvent.ACTION_MOVE ->
                // Drag the image
                if (viewSelected) {
                    this.x = x.toInt() - imageWidth / 2
                    this.y = y.toInt() - imageHeight / 2
                }
            MotionEvent.ACTION_UP ->
                // Finish the movement
                viewSelected = false
        }

        // This will call OnDraw(canvas) method again
        invalidate()

        return true
    }
}
