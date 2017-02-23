package com.androidexamples.app.activity;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.Toast;

import com.androidexamples.app.R;

public class SimpleBroadcastActivity extends AppCompatActivity {

    /**
     * Necessário declarar o receiver no AndroidManifest.xml
     *
     * */

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_static_broadcast);
        loadComponents();
    }

    private void loadComponents() {
        setSupportActionBar((Toolbar)findViewById(R.id.top_toolbar));
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        findViewById(R.id.button_send_broadcast).setOnClickListener(onButtonClickListener());
    }

    private View.OnClickListener onButtonClickListener() {
        return new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                switch (v.getId()){
                    case R.id.button_send_broadcast:
                        sendBroadcast(new Intent("Katiau"));
                        Toast.makeText(getBaseContext(), "Intent sent", Toast.LENGTH_SHORT).show();
                        break;
                }
            }
        };
    }
}
