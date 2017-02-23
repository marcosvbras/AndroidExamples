package com.androidexamples.app.activity;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.view.View;

import com.androidexamples.app.R;

public class BroadcastReceiverActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_broadcast_receiver);
        loadComponents();
    }

    private void loadComponents() {
        setSupportActionBar((Toolbar)findViewById(R.id.top_toolbar));
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        findViewById(R.id.button_static_broadcast).setOnClickListener(onButtonClickListener());
        findViewById(R.id.button_dynamic_broadcast).setOnClickListener(onButtonClickListener());
        findViewById(R.id.button_local_broadcast).setOnClickListener(onButtonClickListener());
    }

    private View.OnClickListener onButtonClickListener() {
        return new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                switch(v.getId()) {
                    case R.id.button_static_broadcast:
                        startActivity(new Intent(getBaseContext(), SimpleBroadcastActivity.class));
                        break;
                    case R.id.button_dynamic_broadcast:
                        startActivity(new Intent(getBaseContext(), DynamicBroadcastActivity.class));
                        break;
                    case R.id.button_local_broadcast:
                        startActivity(new Intent(getBaseContext(), LocalBroadcastActivity.class));
                        break;
                }
            }
        };
    }
}
