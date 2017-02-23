package com.androidexamples.app.activity;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.support.v4.content.LocalBroadcastManager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;

import com.androidexamples.app.R;

public class LocalBroadcastActivity extends AppCompatActivity {

    // Views
    private TextView textViewMessage;

    // Another objects
    private LocalBroadcastManager localBroadcastManager;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_local_broadcast);
        loadComponents();
        localBroadcastManager = LocalBroadcastManager.getInstance(this);
        // Registra o broadcast receiver de forma local
        localBroadcastManager.registerReceiver(dynamicReceiver, new IntentFilter("Katiuga"));
    }

    private void loadComponents() {
        setSupportActionBar((Toolbar)findViewById(R.id.top_toolbar));
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        findViewById(R.id.button_send_broadcast).setOnClickListener(onButtonClickListener());
        textViewMessage = (TextView)findViewById(R.id.text_view_message);
    }

    private View.OnClickListener onButtonClickListener() {
        return new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                switch(v.getId()) {
                    case R.id.button_send_broadcast:
                        localBroadcastManager.sendBroadcast(new Intent("Katiuga"));
                        Toast.makeText(getBaseContext(), "Intent sent", Toast.LENGTH_SHORT).show();
                        break;
                }
            }
        };
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        localBroadcastManager.unregisterReceiver(dynamicReceiver);
    }

    private BroadcastReceiver dynamicReceiver = new BroadcastReceiver() {
        @Override
        public void onReceive(Context context, Intent intent) {
            textViewMessage.setText(context.getString(R.string.message_arrived));
        }
    };
}
