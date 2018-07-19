package com.androidexamples.app.utils

import android.content.Context
import java.io.ByteArrayOutputStream
import java.io.IOException
import java.io.InputStream

/**
 * Created by marcos on 17/12/2016.
 */

object Converter {

    /**
     * Convert DIP to Pixels
     * @param context
     * @param dip
     * @return float
     */
    fun dipToPixels(context: Context, dip: Float): Float {
        val density = context.resources.displayMetrics.density
        return (dip * density + 0.5f).toInt().toFloat()
    }

    /**
     * Convert Pixels to DIP
     * @param context
     * @param pixels
     * @return float
     */
    fun pixelsToDip(context: Context, pixels: Float): Float {
        val density = context.resources.displayMetrics.density
        return (pixels / density + 0.5f).toInt().toFloat()
    }
}
