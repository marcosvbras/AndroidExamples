package com.androidexamples.app

import android.app.Application

/**
 * Created by marcos on 07/01/2017.
 *
 * This class must be referenced in Android Manifest
 */

class App : Application() {

    override fun onCreate() {
        super.onCreate()
        instance = this
    }

    companion object {
        var instance: App? = null
            private set
    }
}
