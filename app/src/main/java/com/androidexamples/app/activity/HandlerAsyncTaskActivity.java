package com.androidexamples.app.activity;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.view.View;

import com.androidexamples.app.R;

public class HandlerAsyncTaskActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_handler_async_task);
        loadComponents();
    }

    private void loadComponents() {
        setSupportActionBar((Toolbar)findViewById(R.id.top_toolbar));
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        findViewById(R.id.button_handler_message).setOnClickListener(onButtonClick());
        findViewById(R.id.button_redownload_image).setOnClickListener(onButtonClick());
        findViewById(R.id.button_splash_screen).setOnClickListener(onButtonClick());
        findViewById(R.id.button_async_task).setOnClickListener(onButtonClick());
    }

    private View.OnClickListener onButtonClick() {
        return new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                switch (v.getId()) {
                    case R.id.button_handler_message:
                        startActivity(new Intent(getBaseContext(), HandlerMessageActivity.class));
                        break;
                    case R.id.button_redownload_image:
                        startActivity(new Intent(getBaseContext(), ReDownloadImage.class));
                        break;
                    case R.id.button_splash_screen:
                        startActivity(new Intent(getBaseContext(), SplashScreenActivity.class));
                        break;
                    case R.id.button_async_task:
                        startActivity(new Intent(getBaseContext(), AsyncTaskActivity.class));
                        break;
                }
            }
        };
    }
}
