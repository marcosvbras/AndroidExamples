package com.androidexamples.app.interfaces

import android.graphics.Bitmap

/**
 * Created by marco on 01/01/2017.
 */

interface ImageDownloadListener {

    fun onDownloadFinish(bitmap: List<Bitmap>)

}
