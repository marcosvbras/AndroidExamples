package com.androidexamples.app.activity;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.view.View;

import com.androidexamples.app.R;

public class NotificationsActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_notifications);
        loadComponents();
    }

    private void loadComponents() {
        setSupportActionBar((Toolbar)findViewById(R.id.top_toolbar));
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        findViewById(R.id.button_simple_notification).setOnClickListener(onSimpleNotificationClick());
        findViewById(R.id.button_heads_up_notification).setOnClickListener(onHeadsUpNotificationClick());
        findViewById(R.id.button_big_view_notification).setOnClickListener(onBigViewNotificationClick());
        findViewById(R.id.button_notification_with_action).setOnClickListener(onNotificationActionClick());
    }

    private View.OnClickListener onNotificationActionClick() {
        return new View.OnClickListener() {
            @Override
            public void onClick(View v) {

            }
        };
    }

    private View.OnClickListener onBigViewNotificationClick() {
        return new View.OnClickListener() {
            @Override
            public void onClick(View v) {

            }
        };
    }

    private View.OnClickListener onHeadsUpNotificationClick() {
        return new View.OnClickListener() {
            @Override
            public void onClick(View v) {

            }
        };
    }

    private View.OnClickListener onSimpleNotificationClick() {
        return new View.OnClickListener() {
            @Override
            public void onClick(View v) {

            }
        };
    }
}
