package com.androidexamples.app.utils

import android.content.Context
import android.graphics.Canvas
import android.graphics.Color
import android.graphics.Paint
import android.util.AttributeSet
import android.util.TypedValue
import android.widget.LinearLayout

/**
 * Created by marcos on 08/01/2017.
 */

class SlidingTabStrip @JvmOverloads constructor(private val myContext: Context, attrs: AttributeSet? = null) : LinearLayout(myContext, attrs) {
    private var indicatorColors: IntArray? = null

    private val bottomBorderThickness: Int
    private val bottomBorderPaint: Paint

    private val selectedIndicatorThickness: Int
    private val selectedIndicatorPaint: Paint

    private var selectedPosition: Int = 0
    private var selectionOffset: Float = 0.toFloat()

    init {
        setWillNotDraw(false)
        val density = resources.displayMetrics.density

        val outValue = TypedValue()
        myContext.theme.resolveAttribute(android.R.attr.colorForeground, outValue, true)
        val themeForegroundColor = outValue.data
        val defaultBottomBorderColor = setColorAlpha(themeForegroundColor, DEFAULT_BOTTOM_BORDER_COLOR_ALPHA)
        setSelectedIndicatorColors(DEFAULT_SELECTED_INDICATOR_COLOR)

        bottomBorderThickness = (DEFAULT_BOTTOM_BORDER_THICKNESS_DIPS * density).toInt()
        bottomBorderPaint = Paint()
        bottomBorderPaint.color = defaultBottomBorderColor

        selectedIndicatorThickness = (SELECTED_INDICATOR_THICKNESS_DIPS * density).toInt()
        selectedIndicatorPaint = Paint()
    }

    fun setSelectedIndicatorColors(vararg colors: Int) {
        indicatorColors = colors
        invalidate()
    }

    fun onViewPagerPageChanged(position: Int, positionOffset: Float) {
        selectedPosition = position
        selectionOffset = positionOffset
        invalidate()
    }

    override fun onDraw(canvas: Canvas) {
        val height = height
        val childCount = childCount

        // Thick colored underline below the current selection
        if (childCount > 0) {
            val selectedTitle = getChildAt(selectedPosition)
            var left = selectedTitle.left
            var right = selectedTitle.right
            var color = getIndicatorColor(selectedPosition)

            if (selectionOffset > 0f && selectedPosition < getChildCount() - 1) {
                val nextColor = getIndicatorColor(selectedPosition + 1)

                if (color != nextColor)
                    color = blendColors(nextColor, color, selectionOffset)

                // Draw the selection partway between the tabs
                val nextTitle = getChildAt(selectedPosition + 1)
                left = (selectionOffset * nextTitle.left + (1.0f - selectionOffset) * left).toInt()
                right = (selectionOffset * nextTitle.right + (1.0f - selectionOffset) * right).toInt()
            }

            selectedIndicatorPaint.color = color
            canvas.drawRect(left.toFloat(), (height - selectedIndicatorThickness).toFloat(), right.toFloat(), height.toFloat(), selectedIndicatorPaint)
        }

        // Thin underline along the entire bottom edge
        canvas.drawRect(0f, (height - bottomBorderThickness).toFloat(), width.toFloat(), height.toFloat(), bottomBorderPaint)
    }

    fun getIndicatorColor(position: Int): Int {
        return indicatorColors!![position % indicatorColors!!.size]
    }

    companion object {

        private val DEFAULT_BOTTOM_BORDER_THICKNESS_DIPS = 0
        private val DEFAULT_BOTTOM_BORDER_COLOR_ALPHA: Byte = 0x26
        private val SELECTED_INDICATOR_THICKNESS_DIPS = 3
        private val DEFAULT_SELECTED_INDICATOR_COLOR = -0xcc4a1b

        /**
         * Set the alpha value of the `color` to be the given `alpha` value.
         */
        private fun setColorAlpha(color: Int, alpha: Byte): Int {
            return Color.argb(alpha.toInt(), Color.red(color), Color.green(color), Color.blue(color))
        }

        /**
         * Blend `color1` and `color2` using the given ratio.
         *
         * @param ratio of which to blend. 1.0 will return `color1`, 0.5 will give an even blend,
         * 0.0 will return `color2`.
         */
        private fun blendColors(color1: Int, color2: Int, ratio: Float): Int {
            val inverseRation = 1f - ratio
            val r = Color.red(color1) * ratio + Color.red(color2) * inverseRation
            val g = Color.green(color1) * ratio + Color.green(color2) * inverseRation
            val b = Color.blue(color1) * ratio + Color.blue(color2) * inverseRation
            return Color.rgb(r.toInt(), g.toInt(), b.toInt())
        }
    }
}