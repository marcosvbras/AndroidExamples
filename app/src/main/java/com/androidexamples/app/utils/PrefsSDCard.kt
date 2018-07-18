package com.androidexamples.app.utils

import android.content.Context
import android.util.Log

import java.io.File

/**
 * Created by marcos on 20/01/2017.
 */
object PrefsSDCard {
    val PREF_ID = "prefs"
    private val TAG = "PrefsSDCard"

    fun setBoolean(context: Context, chave: String, on: Boolean) {
        setString(context, chave, if (on) "1" else "0")
    }

    fun getBoolean(context: Context, chave: String): Boolean {
        val s = getString(context, chave)
        return "1" == s
    }

    fun setInteger(context: Context, chave: String, valor: Int) {
        setString(context, chave, valor.toString())
    }

    fun getInteger(context: Context, chave: String): Int {
        val s = getString(context, chave)
        return if (s != null) {
            Integer.parseInt(s)
        } else 0
    }

    fun setString(context: Context, chave: String, valor: String) {
        val f = SDCardUtils.getPublicFile("prefs", "$chave.txt")
        //IOUtils.writeString(f, valor);
    }

    fun getString(context: Context, chave: String): String? {
        val f = SDCardUtils.getPublicFile("prefs", "$chave.txt")
        Log.d("livroandroid", "getString: $f")
        return IOUtils.readString(f)
    }
}
