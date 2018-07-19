package com.androidexamples.app.utils

import android.content.Context
import android.content.res.AssetManager
import android.content.res.Resources

import java.io.File
import java.io.FileInputStream
import java.io.FileNotFoundException
import java.io.FileOutputStream
import java.io.IOException
import java.io.InputStream
import java.io.OutputStream

/**
 * Created by marcos on 17/01/2017.
 */

object FileUtils {

    /**
     * Read /res/raw folder
     *
     * @param context
     * @param raw
     * @return InputStream
     */
    fun readRawFile(context: Context, raw: Int): InputStream {
        val resources = context.resources
        return resources.openRawResource(raw)
    }

    /**
     * Read assets folder file
     *
     * @param context
     * @param fileName
     * @return InputStream
     * @throws IOException
     */
    @Throws(IOException::class)
    fun readAssetsFile(context: Context, fileName: String): InputStream {
        val assets = context.assets
        return assets.open(fileName)
    }
}