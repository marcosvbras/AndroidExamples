package com.androidexamples.app.broadcast;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.util.Log;
import android.widget.Toast;

import com.androidexamples.app.R;

/**
 * Created by marcosvbras on 22/02/2017.
 */

public class StaticBroadcastReceiver extends BroadcastReceiver {
    @Override
    public void onReceive(Context context, Intent intent) {
        Toast.makeText(context, "Message received!", Toast.LENGTH_SHORT).show();
        Log.v("Debug", context.getString(R.string.message_arrived));
    }
}
