package com.androidexamples.app.broadcast;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.util.Log;
import android.widget.Toast;

/**
 * Created by marcosvbras on 22/02/2017.
 */

public class BootReceiver extends BroadcastReceiver {

    /**
     * Necessita declaração da seguinte permissão no AndroidManifest.xml
     * <uses-permission android:name="android.permission.RECEIVE_BOOT_COMPLETED"/>
     * */

    @Override
    public void onReceive(Context context, Intent intent) {
        Toast.makeText(context, "'Boot completed' received with Boot Receiver", Toast.LENGTH_SHORT).show();
        Log.v("Debug", "'Boot completed' received with Boot Receiver");
    }
}
