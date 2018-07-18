package com.androidexamples.app.view

import android.content.Context
import android.graphics.Bitmap
import android.graphics.Bitmap.Config
import android.graphics.Canvas
import android.graphics.Color
import android.graphics.Paint
import android.graphics.PorterDuff.Mode
import android.graphics.PorterDuffXfermode
import android.graphics.Rect
import android.graphics.drawable.BitmapDrawable
import android.graphics.drawable.Drawable
import android.util.AttributeSet
import android.view.View
import android.widget.ImageView

/**
 * Created by marcosvbras on 19/02/2017.
 */

class CircularImageView : ImageView {

    private var borderWidth: Int = 0
    private val paintBorder: Paint? = null

    constructor(context: Context) : super(context) {

        if (this.isInEditMode)
            return
    }

    constructor(context: Context, attrs: AttributeSet) : super(context, attrs) {

        if (this.isInEditMode)
            return
    }

    constructor(context: Context, attrs: AttributeSet, defStyle: Int) : super(context, attrs, defStyle) {}

    fun setBorderWidth(borderWidth: Int) {
        this.borderWidth = borderWidth
        this.requestLayout()
        this.invalidate()
    }

    fun setBorderColor(borderColor: Int) {
        if (paintBorder != null)
            paintBorder.color = borderColor

        this.invalidate()
    }

    fun addShadow() {
        setLayerType(View.LAYER_TYPE_SOFTWARE, paintBorder)
        paintBorder!!.setShadowLayer(4.0f, 0.0f, 2.0f, Color.BLACK)
    }

    override fun onDraw(canvas: Canvas) {
        val bitmap = drawableToBitmap(drawable) ?: return

        val width = width
        val height = height

        val roundBitmap = getCroppedBitmap(bitmap, width)
        canvas.drawBitmap(roundBitmap, 0f, 0f, null)
    }

    private fun drawableToBitmap(drawable: Drawable?): Bitmap? {
        if (drawable == null)
            return null
        else if (drawable is BitmapDrawable)
            return drawable.bitmap

        val bitmap = Bitmap.createBitmap(drawable.intrinsicWidth, drawable.intrinsicHeight, Config.ARGB_8888)
        val canvas = Canvas(bitmap)
        drawable.setBounds(0, 0, canvas.width, canvas.height)
        drawable.draw(canvas)
        return bitmap
    }

    companion object {

        fun getCroppedBitmap(originalBitmap: Bitmap, radius: Int): Bitmap {
            val sbmp: Bitmap

            if (originalBitmap.width != radius || originalBitmap.height != radius) {
                val smallest = Math.min(originalBitmap.width, originalBitmap.height).toFloat()
                val factor = smallest / radius
                sbmp = Bitmap.createScaledBitmap(originalBitmap, (originalBitmap.width / factor).toInt(), (originalBitmap.height / factor).toInt(), false)
            } else {
                sbmp = originalBitmap
            }

            val output = Bitmap.createBitmap(radius, radius, Config.ARGB_8888)
            val canvas = Canvas(output)

            val color = -0x5e688c
            val paint = Paint()
            val rect = Rect(0, 0, radius, radius)

            val rectSrc = Rect(sbmp.width / 2 - radius / 2, sbmp.height / 2 - radius / 2,
                    sbmp.width / 2 + radius / 2, sbmp.height / 2 + radius / 2
            )

            paint.isAntiAlias = true
            paint.isFilterBitmap = true
            paint.isDither = true
            canvas.drawARGB(0, 0, 0, 0)
            paint.color = Color.parseColor("#BAB399")
            canvas.drawCircle(radius / 2 + 0.7f, radius / 2 + 0.7f, radius / 2 + 0.1f, paint)
            paint.xfermode = PorterDuffXfermode(Mode.SRC_IN)
            canvas.drawBitmap(sbmp, rectSrc, rect, paint)

            return output
        }
    }
}
