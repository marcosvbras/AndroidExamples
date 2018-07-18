package com.androidexamples.app.utils

import android.content.Context
import android.os.Environment
import android.util.Log

import java.io.File

/**
 * Created by marcos on 20/01/2017.
 */
object SDCardUtils {
    private val TAG = SDCardUtils::class.java.name

    /**
     * Cria um arquivo público na raiz do sdcard
     */
    fun getPublicFile(fileName: String): File {
        val sdCardDir = Environment.getExternalStorageDirectory()
        return createFile(sdCardDir, fileName)
    }

    /**
     * Cria um arquivo público na raiz do sdcard
     *
     * @param type DIRECTORY_MUSIC, DIRECTORY_PODCASTS, DIRECTORY_RINGTONES, DIRECTORY_ALARMS, DIRECTORY_NOTIFICATIONS, DIRECTORY_PICTURES, DIRECTORY_MOVIES, DIRECTORY_DOWNLOADS, ou DIRECTORY_DCIM
     * @return
     */
    fun getPublicFile(fileName: String, type: String): File {
        val sdCardDir = Environment.getExternalStoragePublicDirectory(type)
        return createFile(sdCardDir, fileName)
    }

    /**
     * Cria um arquivo privado na raiz do sdcard
     *
     * @param context
     * @param fileName
     * @return
     */
    fun getPrivateFile(context: Context, fileName: String): File {
        val sdCardDir = context.getExternalFilesDir(null)
        return createFile(sdCardDir!!, fileName)
    }

    /**
     * Cria um arquivo privado na raiz do sdcard
     *
     * @param context
     * @param fileName
     * @param type     @param type DIRECTORY_MUSIC, DIRECTORY_PODCASTS, DIRECTORY_RINGTONES, DIRECTORY_ALARMS, DIRECTORY_NOTIFICATIONS, DIRECTORY_PICTURES, DIRECTORY_MOVIES, DIRECTORY_DOWNLOADS
     * @return
     */
    fun getPrivateFile(context: Context, fileName: String, type: String): File {
        val sdCardDir = context.getExternalFilesDir(type)
        return createFile(sdCardDir!!, fileName)
    }

    /**
     * Cria o arquivo no SDCard na pasta informada.
     *
     * @param sdCardDir Pasta do sdcard
     * @param fileName  Nome do arquivo
     * @return
     */
    private fun createFile(sdCardDir: File, fileName: String): File {
        if (!sdCardDir.exists()) {
            sdCardDir.mkdir() // Cria o diretório se não existe
        }
        // Retorna o arquivo para ler ou salvar no sd card
        return File(sdCardDir, fileName)
    }

    /**
     * Retorna a pasta montada no /mnt/sdcard
     *
     * @param context
     * @param preferedDir
     * @return
     */
    fun getSdCardDir(context: Context, preferedDir: String): File {
        var dir: File? = null
        if (android.os.Environment.getExternalStorageState() == android.os.Environment.MEDIA_MOUNTED) {
            dir = File(android.os.Environment.getExternalStorageDirectory(), preferedDir)
        } else {
            dir = context.cacheDir
        }
        if (!dir!!.exists()) {
            dir.mkdirs()
        }
        return dir
    }

    /**
     * Retorna um File do /mnt/sdcard/${folderName}/${fileName}
     *
     * @param context
     * @param folderName
     * @param fileName
     * @return
     */
    fun getSdCardFile(context: Context, folderName: String, fileName: String): File {
        val sdcard = getSdCardDir(context, folderName)
        val f = File(sdcard, fileName)
        Log.d(TAG, "<< getSdCardFile > $f")
        return f
    }
}
