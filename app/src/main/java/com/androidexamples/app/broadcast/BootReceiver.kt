package com.androidexamples.app.broadcast

import android.annotation.SuppressLint
import android.content.BroadcastReceiver
import android.content.Context
import android.content.Intent
import android.util.Log
import android.widget.Toast

/**
 * Created by marcosvbras on 22/02/2017.
 */

class BootReceiver : BroadcastReceiver() {

    /**
     * Requires permission in Android Manifest
     * <uses-permission android:name="android.permission.RECEIVE_BOOT_COMPLETED"></uses-permission>
     */

    @SuppressLint("UnsafeProtectedBroadcastReceiver")
    override fun onReceive(context: Context, intent: Intent) {
        Toast.makeText(context, "'Boot completed' received with Boot Receiver", Toast.LENGTH_SHORT).show()
        Log.v("Debug", "'Boot completed' received with Boot Receiver")
    }
}
