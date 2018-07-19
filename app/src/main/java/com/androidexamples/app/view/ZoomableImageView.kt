package com.androidexamples.app.view

import android.content.Context
import android.graphics.Bitmap
import android.graphics.Matrix
import android.graphics.PointF
import android.util.AttributeSet
import android.view.MotionEvent
import android.view.ScaleGestureDetector
import android.view.View
import android.widget.ImageView

/**
 * Created by marcos on 11/12/2016.
 */

class ZoomableImageView(private val imageContext: Context, attr: AttributeSet) : ImageView(imageContext, attr), View.OnTouchListener {

    private val theMatrix = Matrix()
    private var mode = NONE_MODE

    // Points
    private val lastPoint = PointF()
    private val startPoint = PointF()

    private val minScale = 1f
    private var maxScale = 4f
    private val matrixValues: FloatArray

    private var redundantXSpace: Float = 0.toFloat()
    private var redundantYSpace: Float = 0.toFloat()
    private var width: Float = 0.toFloat()
    private var height: Float = 0.toFloat()
    private var saveScale = 1f
    private var right: Float = 0.toFloat()
    private var bottom: Float = 0.toFloat()
    private var origWidth: Float = 0.toFloat()
    private var origHeight: Float = 0.toFloat()
    private var bitmapWidth: Float = 0.toFloat()
    private var bitmapHeight: Float = 0.toFloat()

    private val scaleGestureDetector: ScaleGestureDetector

    init {
        super.setClickable(true)
        scaleGestureDetector = ScaleGestureDetector(imageContext, onScaleGesture())
        theMatrix.setTranslate(1f, 1f)
        matrixValues = FloatArray(9)
        imageMatrix = theMatrix
        scaleType = ImageView.ScaleType.MATRIX
        setOnTouchListener(this)
    }

    private fun onScaleGesture(): ScaleGestureDetector.OnScaleGestureListener {
        return object : ScaleGestureDetector.OnScaleGestureListener {
            override fun onScale(scaleGestureDetector: ScaleGestureDetector): Boolean {
                var mScaleFactor = scaleGestureDetector.scaleFactor
                val origScale = saveScale
                saveScale *= mScaleFactor
                if (saveScale > maxScale) {
                    saveScale = maxScale
                    mScaleFactor = maxScale / origScale
                } else if (saveScale < minScale) {
                    saveScale = minScale
                    mScaleFactor = minScale / origScale
                }

                right = width * saveScale - width - 2f * redundantXSpace * saveScale
                bottom = height * saveScale - height - 2f * redundantYSpace * saveScale

                if (origWidth * saveScale <= width || origHeight * saveScale <= height) {
                    theMatrix.postScale(mScaleFactor, mScaleFactor, width / 2, height / 2)
                    if (mScaleFactor < 1) {
                        theMatrix.getValues(matrixValues)
                        val x = matrixValues[Matrix.MTRANS_X]
                        val y = matrixValues[Matrix.MTRANS_Y]
                        if (mScaleFactor < 1) {
                            if (Math.round(origWidth * saveScale) < width) {
                                if (y < -bottom)
                                    theMatrix.postTranslate(0f, -(y + bottom))
                                else if (y > 0)
                                    theMatrix.postTranslate(0f, -y)
                            } else {
                                if (x < -right)
                                    theMatrix.postTranslate(-(x + right), 0f)
                                else if (x > 0)
                                    theMatrix.postTranslate(-x, 0f)
                            }
                        }
                    }
                } else {
                    theMatrix.postScale(mScaleFactor, mScaleFactor, scaleGestureDetector.focusX, scaleGestureDetector.focusY)
                    theMatrix.getValues(matrixValues)
                    val x = matrixValues[Matrix.MTRANS_X]
                    val y = matrixValues[Matrix.MTRANS_Y]
                    if (mScaleFactor < 1) {
                        if (x < -right)
                            theMatrix.postTranslate(-(x + right), 0f)
                        else if (x > 0)
                            theMatrix.postTranslate(-x, 0f)
                        if (y < -bottom)
                            theMatrix.postTranslate(0f, -(y + bottom))
                        else if (y > 0)
                            theMatrix.postTranslate(0f, -y)
                    }
                }
                return true
            }

            override fun onScaleBegin(scaleGestureDetector: ScaleGestureDetector): Boolean {
                mode = ZOOM_MODE
                return true
            }

            override fun onScaleEnd(scaleGestureDetector: ScaleGestureDetector) {

            }
        }
    }

    override fun setImageBitmap(bitmap: Bitmap) {
        super.setImageBitmap(bitmap)
        // Getting bitmap metrics
        bitmapWidth = bitmap.width.toFloat()
        bitmapHeight = bitmap.height.toFloat()
    }

    fun setMaxZoom(x: Float) {
        maxScale = x
    }

    override fun onTouch(view: View, motionEvent: MotionEvent): Boolean {
        scaleGestureDetector.onTouchEvent(motionEvent)

        theMatrix.getValues(matrixValues)
        val x = matrixValues[Matrix.MTRANS_X]
        val y = matrixValues[Matrix.MTRANS_Y]
        val currentPoint = PointF(motionEvent.x, motionEvent.y)

        val actionEvent = motionEvent.action

        if (actionEvent == MotionEvent.ACTION_DOWN) {
            // When one finger is touchin, set the mode to DRAG_MODE
            lastPoint.set(motionEvent.x, motionEvent.y)
            startPoint.set(lastPoint)
            mode = DRAG_MODE
        } else if (actionEvent == MotionEvent.ACTION_POINTER_DOWN) {
            // When two fingers are touchin, set the mode to ZOOM_MODE
            lastPoint.set(motionEvent.x, motionEvent.y)
            startPoint.set(lastPoint)
            mode = ZOOM_MODE
        } else if (actionEvent == MotionEvent.ACTION_MOVE) {
            // if the mode is ZOOM_MODE or if the mode is DRAG_MODE and already zoomed
            if (mode == ZOOM_MODE || mode == DRAG_MODE && saveScale > minScale) {
                var deltaX = currentPoint.x - lastPoint.x// x difference
                var deltaY = currentPoint.y - lastPoint.y// y difference
                val scaleWidth = Math.round(origWidth * saveScale).toFloat()// width after applying current scale
                val scaleHeight = Math.round(origHeight * saveScale).toFloat()// height after applying current scale
                //if scaleWidth is smaller than the views width
                //in other words if the image width fits in the view
                //limit left and right movement
                if (scaleWidth < width) {
                    deltaX = 0f

                    if (y + deltaY > 0)
                        deltaY = -y
                    else if (y + deltaY < -bottom)
                        deltaY = -(y + bottom)
                } else if (scaleHeight < height) {
                    deltaY = 0f
                    if (x + deltaX > 0)
                        deltaX = -x
                    else if (x + deltaX < -right)
                        deltaX = -(x + right)
                } else {
                    if (x + deltaX > 0)
                        deltaX = -x
                    else if (x + deltaX < -right)
                        deltaX = -(x + right)

                    if (y + deltaY > 0)
                        deltaY = -y
                    else if (y + deltaY < -bottom)
                        deltaY = -(y + bottom)
                }//if the image doesnt fit in the width or height
                //limit both up and down and left and right
                //if scaleHeight is smaller than the views height
                //in other words if the image height fits in the view
                //limit up and down movement
                // Move the image with the theMatrix
                theMatrix.postTranslate(deltaX, deltaY)
                // Set the lastPoint touch location to the current
                lastPoint.set(currentPoint.x, currentPoint.y)
            }
        } else if (actionEvent == MotionEvent.ACTION_UP) {
            // first finger is lifted
            mode = NONE_MODE
            val xDiff = Math.abs(currentPoint.x - startPoint.x).toInt()
            val yDiff = Math.abs(currentPoint.y - startPoint.y).toInt()
            if (xDiff < CLICK_MODE && yDiff < CLICK_MODE)
                performClick()
        } else if (actionEvent == MotionEvent.ACTION_POINTER_UP) {
            // second finger is lifted
            mode = NONE_MODE
        }

        imageMatrix = theMatrix
        invalidate()
        return true
    }

    override fun onMeasure(widthMeasureSpec: Int, heightMeasureSpec: Int) {
        super.onMeasure(widthMeasureSpec, heightMeasureSpec)
        width = View.MeasureSpec.getSize(widthMeasureSpec).toFloat()
        height = View.MeasureSpec.getSize(heightMeasureSpec).toFloat()
        //Fit to screen.
        val scale: Float
        val scaleX = width / bitmapWidth
        val scaleY = height / bitmapHeight
        scale = Math.min(scaleX, scaleY)
        theMatrix.setScale(scale, scale)
        imageMatrix = theMatrix
        saveScale = 1f

        // Center the image
        redundantYSpace = height - scale * bitmapHeight
        redundantXSpace = width - scale * bitmapWidth
        redundantYSpace /= 2f
        redundantXSpace /= 2f

        theMatrix.postTranslate(redundantXSpace, redundantYSpace)

        origWidth = width - 2 * redundantXSpace
        origHeight = height - 2 * redundantYSpace
        right = width * saveScale - width - 2f * redundantXSpace * saveScale
        bottom = height * saveScale - height - 2f * redundantYSpace * saveScale
        imageMatrix = theMatrix
    }

    companion object {
        // Constant modes
        const val NONE_MODE = 0
        const val DRAG_MODE = 1
        const val ZOOM_MODE = 2
        const val CLICK_MODE = 3
    }
}
