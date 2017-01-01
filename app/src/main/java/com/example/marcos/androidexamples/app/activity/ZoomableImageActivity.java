package com.example.marcos.androidexamples.app.activity;

import android.graphics.BitmapFactory;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;

import com.example.marcos.androidexamples.R;
import com.example.marcos.androidexamples.app.view.ZoomableImageView;

public class ZoomableImageActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_zoomable_imageview);
        loadComponents();
    }

    private void loadComponents() {
        setSupportActionBar((Toolbar)findViewById(R.id.toolbar));
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        ZoomableImageView zoomableImageView = (ZoomableImageView) findViewById(R.id.zoomableImageView);
        zoomableImageView.setImageBitmap(BitmapFactory.decodeResource(getResources(), R.drawable.tghoul));
    }
}
