package com.androidexamples.app.utils

import android.graphics.Bitmap
import android.graphics.BitmapFactory
import android.util.Log

import java.io.IOException
import java.io.InputStream
import java.io.OutputStream
import java.net.HttpURLConnection
import java.net.URL
import java.net.URLEncoder

/**
 * Created by marcos on 17/01/2017.
 */

class HttpHelper {

    private val TAG = "HttpHelper"
    val TIMEOUT_MILLIS = 15000
    var LOG_ON = false
    private var contentType: String? = null
    private var charsetToEncode: String? = null

    @Throws(IOException::class)
    fun doGet(url: String): String? {
        return doGet(url, null, "UTF-8")
    }

    @Throws(IOException::class)
    fun doGet(urlString: String, params: Map<String, String>?, charset: String): String? {
        var urlString = urlString
        val queryString = getQueryString(params)

        if (queryString != null && queryString.trim { it <= ' ' }.length > 0)
            urlString += "?$queryString"

        if (LOG_ON)
            Log.d(TAG, ">> Http.doGet: $urlString")

        val url = URL(urlString)
        var httpURLConnection: HttpURLConnection? = null
        var s: String? = null

        try {
            httpURLConnection = url.openConnection() as HttpURLConnection

            if (contentType != null)
                httpURLConnection.setRequestProperty("Content-TypeDB", contentType)

            httpURLConnection.requestMethod = "GET"
            httpURLConnection.connectTimeout = TIMEOUT_MILLIS
            httpURLConnection.readTimeout = TIMEOUT_MILLIS
            httpURLConnection.connect()
            var inputStream: InputStream? = null

            val status = httpURLConnection.responseCode

            if (status >= HttpURLConnection.HTTP_BAD_REQUEST) {
                Log.d(TAG, "Error code: $status")
                inputStream = httpURLConnection.errorStream
            } else {
                inputStream = httpURLConnection.inputStream
            }

//            s = IOUtils.inputStreamToString(inputStream, charset)

            if (LOG_ON)
                Log.d(TAG, "<< Http.doGet: " + s!!)

            inputStream!!.close()
        } catch (e: IOException) {
            throw e
        } finally {
            httpURLConnection?.disconnect()
        }

        return s
    }

    @Throws(IOException::class)
    fun doDelete(url: String): String? {
        return doDelete(url, null, "UTF-8")
    }

    @Throws(IOException::class)
    fun doDelete(urlString: String, params: Map<String, String>?, charset: String): String? {
        var urlString = urlString
        val queryString = getQueryString(params)

        if (queryString != null && queryString.trim { it <= ' ' }.length > 0)
            urlString += "?$queryString"

        if (LOG_ON)
            Log.d(TAG, ">> Http.doDelete: $urlString")

        val url = URL(urlString)
        var httpURLConnection: HttpURLConnection? = null
        var s: String? = null

        try {
            httpURLConnection = url.openConnection() as HttpURLConnection

            if (contentType != null)
                httpURLConnection.setRequestProperty("Content-TypeDB", contentType)

            httpURLConnection.requestMethod = "DELETE"
            httpURLConnection.connectTimeout = TIMEOUT_MILLIS
            httpURLConnection.readTimeout = TIMEOUT_MILLIS

            httpURLConnection.connect()
            val inputStream = httpURLConnection.inputStream
//            s = IOUtils.inputStreamToString(inputStream, charset)

            if (LOG_ON)
                Log.d(TAG, "<< Http.doGet: " + s!!)

            inputStream.close()
        } catch (e: IOException) {
            throw e
        } finally {
            httpURLConnection?.disconnect()
        }

        return s
    }

    @Throws(IOException::class)
    fun doPost(url: String, params: Map<String, String>?, charset: String): String? {
        val queryString = getQueryString(params)

        val bytes = if (params != null) queryString!!.toByteArray(charset(charset)) else null

        if (LOG_ON)
            Log.d(TAG, "Http.doPost: $url?$params")

        return doPost(url, bytes, charset)
    }

    @Throws(IOException::class)
    fun doPost(url: String, params: ByteArray?, charset: String): String? {
        if (LOG_ON)
            Log.d(TAG, ">> Http.doPost: $url")

        val u = URL(url)
        var httpURLConnection: HttpURLConnection? = null
        var s: String? = null

        try {
            httpURLConnection = u.openConnection() as HttpURLConnection

            if (contentType != null)
                httpURLConnection.setRequestProperty("Content-TypeDB", contentType)

            httpURLConnection.requestMethod = "POST"
            httpURLConnection.connectTimeout = TIMEOUT_MILLIS
            httpURLConnection.readTimeout = TIMEOUT_MILLIS
            httpURLConnection.doOutput = true
            httpURLConnection.doInput = true
            httpURLConnection.connect()

            if (params != null) {
                val out = httpURLConnection.outputStream
                out.write(params)
                out.flush()
                out.close()
            }

            var inputStream: InputStream? = null
            val status = httpURLConnection.responseCode

            if (status >= HttpURLConnection.HTTP_BAD_REQUEST) {
                Log.d(TAG, "Error code: $status")
                inputStream = httpURLConnection.errorStream
            } else {
                inputStream = httpURLConnection.inputStream
            }

//            s = IOUtils.inputStreamToString(inputStream, charset)

            if (LOG_ON)
                Log.d(TAG, "<< Http.doPost: " + s!!)

            inputStream!!.close()
        } catch (e: IOException) {
            throw e
        } finally {
            httpURLConnection?.disconnect()
        }
        return s
    }

    @Throws(IOException::class)
    fun doGetBitmap(urlString: String): Bitmap? {
        if (LOG_ON)
            Log.d(TAG, ">> Http.doGet: $urlString")

        val url = URL(urlString)
        val httpURLConnection = url.openConnection() as HttpURLConnection
        httpURLConnection.requestMethod = "GET"
        httpURLConnection.connectTimeout = TIMEOUT_MILLIS
        httpURLConnection.readTimeout = TIMEOUT_MILLIS
        httpURLConnection.doOutput = true
        httpURLConnection.doInput = true
        httpURLConnection.connect()
        val inputStream = httpURLConnection.inputStream

//        val bytes = IOUtils.inputStreamToBytes(inputStream)

//        if (LOG_ON)
//            Log.d(TAG, "<< Http.doGet: " + bytes!!)

        inputStream.close()
        httpURLConnection.disconnect()
        var bitmap: Bitmap? = null

//        if (bytes != null)
//            bitmap = BitmapFactory.decodeByteArray(bytes, 0, bytes!!.size)

        return bitmap
    }

    /**
     * Returns QueryString to 'GET'
     */
    @Throws(IOException::class)
    fun getQueryString(params: Map<String, String>?): String? {
        if (params == null || params.size == 0)
            return null

        var urlParams: String? = null

        for (chave in params.keys) {
            val objectValor = params[chave]

            if (objectValor != null) {
                var valor = objectValor.toString()

                if (charsetToEncode != null)
                    valor = URLEncoder.encode(valor, charsetToEncode)

                urlParams = if (urlParams == null) "" else "$urlParams&"
                urlParams += "$chave=$valor"
            }
        }

        return urlParams
    }

    fun setContentType(contentType: String) {
        this.contentType = contentType
    }

    fun setCharsetToEncode(encode: String) {
        this.charsetToEncode = encode
    }
}
