package com.androidexamples.app.utils

import android.content.Context
import android.util.Log

import java.io.File
import java.io.FileOutputStream
import java.io.IOException

/**
 * <pre>
 * Salva arquvos no formato chave e valor.
 *
 * Se chamar o método setString("nome","Ricardo"), vai salvar o arquivo
 *
 * /data/data/app/files.nome.txt
 *
</pre> *
 */
object PrefsFile {
    val PREF_ID = "prefs"
    private val TAG = "PrefsFile"

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
        var chave = chave
        try {
            chave += ".txt"
            val file = context.getFileStreamPath(chave)
            val out = context.openFileOutput(chave, Context.MODE_PRIVATE)
            IOUtils.writeString(out, valor)
            Log.d(TAG, "PrefsFile.setString file: $file >  $valor")
        } catch (ex: IOException) {
            Log.e(TAG, ex.message, ex)
        }

    }

    fun getString(context: Context, chave: String): String? {
        var chave = chave
        chave += ".txt"
        val f = context.getFileStreamPath(chave)
        val s = IOUtils.readString(f)
        Log.d(TAG, "PrefsFile.getString file: $f >  $s")
        return s
    }
}


