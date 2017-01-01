package com.example.marcos.androidexamples.app.activity;

import android.content.Intent;
import android.os.Handler;
import android.support.v4.app.ActivityCompat;
import android.support.v4.app.ActivityOptionsCompat;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.Window;
import android.view.WindowManager;
import android.widget.LinearLayout;

import com.example.marcos.androidexamples.R;

public class SplashScreenActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        requestWindowFeature(Window.FEATURE_NO_TITLE);
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN,
                WindowManager.LayoutParams.FLAG_FULLSCREEN);
        setContentView(R.layout.activity_splash_screen);
        loadComponents();
    }

    private void loadComponents() {
        new Handler().postDelayed(getRunnable(), 5000);
    }

    private Runnable getRunnable() {
        return new Runnable() {
            @Override
            public void run() {
                LinearLayout linearLayout = (LinearLayout) findViewById(R.id.activity_splash_screen);
                Intent intent = new Intent(getBaseContext(), TestActivity.class);
                intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
                Bundle scaleBundle = ActivityOptionsCompat.makeClipRevealAnimation(linearLayout, 0, 0, linearLayout.getWidth(), linearLayout.getHeight()).toBundle();
                ActivityCompat.startActivity(getBaseContext(), intent, scaleBundle);
                finish();
            }
        };
    }
}
