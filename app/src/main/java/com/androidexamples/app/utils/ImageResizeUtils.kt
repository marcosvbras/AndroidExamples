package com.androidexamples.app.utils

import android.content.res.Resources
import android.graphics.Bitmap
import android.graphics.BitmapFactory
import android.net.Uri

/**
 * Created by marcosvbras on 17/01/2017.
 */

object ImageResizeUtils {

    private val TAG = ImageResizeUtils::class.java.name

    fun calculateInSampleSize(originalWidth: Int, originalHeight: Int, requiredWidth: Int, requiredHeight: Int): Int {
        // Raw height and width of image
        var inSampleSize = 1

        if (originalHeight > requiredHeight || originalWidth > requiredWidth) {
            val halfHeight = originalHeight / 2
            val halfWidth = originalWidth / 2

            // Calculate the largest inSampleSize value that is a power of 2 and keeps both
            // height and width larger than the requested height and width.
            while (halfHeight / inSampleSize >= requiredHeight && halfWidth / inSampleSize >= requiredWidth) {
                inSampleSize *= 2
            }
        }

        return inSampleSize
    }

    /**
     * Resize a large Bitmap to scaled Bitmap
     * @param bitmapOriginal Large bitmap to be scaled.
     * @param maxSize The max size for scale.
     * @return The scaled bitmap, or null if the large bitmap could not be scaled, or,
     * if bitmapOriginal is non-null. If maxSize is less than or equal to the original bitmap size, the original bitmap will be returned.
     */
    fun resizeBitmap(bitmapOriginal: Bitmap?, maxSize: Int): Bitmap? {
        var bitmapOriginal: Bitmap? = bitmapOriginal ?: return null

        var originalWidth = bitmapOriginal!!.width
        var originalHeight = bitmapOriginal.height

        val bitmapRatio = originalWidth.toFloat() / originalHeight.toFloat()

        if (bitmapRatio == 1f && originalWidth > maxSize) {
            bitmapOriginal = Bitmap.createScaledBitmap(bitmapOriginal, maxSize, maxSize, true)
        } else if (bitmapRatio > 1 && originalHeight > maxSize) {
            originalWidth = originalWidth * maxSize / originalHeight
            originalHeight = maxSize
            bitmapOriginal = Bitmap.createScaledBitmap(bitmapOriginal, originalWidth, originalHeight, true)
        } else if (bitmapRatio < 1 && originalWidth > maxSize) {
            originalHeight = originalHeight * maxSize / originalWidth
            originalWidth = maxSize
            bitmapOriginal = Bitmap.createScaledBitmap(bitmapOriginal, originalWidth, originalHeight, true)
        }

        return bitmapOriginal
    }

    /**
     * Resize a large resource to scaled Bitmap
     * @param resources The resources object containing the image data.
     * @param drawableId The resource id of the image data.
     * @param maxSize The max size for scale.
     * @return The scaled bitmap. If maxSize is less than or equal to the original resource size, the original bitmap will be returned.
     */
    fun resizeResource(resources: Resources, drawableId: Int, maxSize: Int): Bitmap? {
        val options = BitmapFactory.Options()
        options.inJustDecodeBounds = true
        BitmapFactory.decodeResource(resources, drawableId, options)
        options.inJustDecodeBounds = false

        return resizeBitmap(BitmapFactory.decodeResource(resources, drawableId, options), maxSize)
    }

    fun getResizedImage(uriFile: Uri, maxSize: Int): Bitmap? {
        val options = BitmapFactory.Options()
        options.inJustDecodeBounds = false
        return resizeBitmap(BitmapFactory.decodeFile(uriFile.path, options), maxSize)
    }
}
