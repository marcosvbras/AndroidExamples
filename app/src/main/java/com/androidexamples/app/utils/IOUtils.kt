package com.androidexamples.app.utils

import android.util.Log

import java.io.ByteArrayOutputStream
import java.io.File
import java.io.FileInputStream
import java.io.FileNotFoundException
import java.io.IOException
import java.io.InputStream
import java.io.OutputStream
import java.nio.charset.Charset

/**
 * Created by marcos on 17/01/2017.
 */

object IOUtils {
    private val TAG = "IOUtils"

    /**
     * Convert InputStream to String
     *
     * @param inputStream
     * @param charset UTF-8 ou ISO-8859-1
     * @return String
     * @throws IOException
     */
//    @Throws(IOException::class)
//    fun inputStreamToString(inputStream: InputStream, charset: Charset): String {
//        val bytes = inputStreamToBytes(inputStream)
//        return String(bytes!!, charset)
//    }

    /**
     * Convert InputStream to byte array
     *
     * @param inputStream
     * @return byte[]
     */
//    fun inputStreamToBytes(inputStream: InputStream): ByteArray? {
//        val byteArrayOutputStream = ByteArrayOutputStream()
//
//        try {
//            val buffer = ByteArray(1024)
//            var len: Int
//
//            while ((len = inputStream.read(buffer)) > 0)
//                byteArrayOutputStream.write(buffer, 0, len)
//
//            return byteArrayOutputStream.toByteArray()
//        } catch (e: Exception) {
//            Log.e(TAG, e.message, e)
//            return null
//        } finally {
//            try {
//                byteArrayOutputStream.close()
//                inputStream.close()
//            } catch (e: IOException) {
//                Log.e(TAG, e.message, e)
//            }
//
//        }
//    }

    /**
     * Write String in OutputStream
     *
     * @param outputStream
     * @param string
     */
    fun writeString(outputStream: OutputStream, string: String) {
        writeBytes(outputStream, string.toByteArray())
    }

    /**
     * Write byte array in OutputStream
     *
     * @param outputStream
     * @param bytes
     */
    fun writeBytes(outputStream: OutputStream, bytes: ByteArray) {
        try {
            outputStream.write(bytes)
            outputStream.flush()
            outputStream.close()
        } catch (e: IOException) {
            Log.e(TAG, e.message, e)
        }

    }

//    fun readString(file: File?): String? {
//        try {
//            if (file == null || !file.exists()) {
//                return null
//            }
//            val `in` = FileInputStream(file)
//            return inputStreamToString(`in`, "UTF-8")
//        } catch (e: FileNotFoundException) {
//            return null
//        } catch (e: IOException) {
//            Log.e(TAG, e.message, e)
//            return null
//        }
//
//    }
}
