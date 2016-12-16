package com.example.marcos.androidexamples.app.activity;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;

import com.example.marcos.androidexamples.R;
import com.example.marcos.androidexamples.app.util.CustomImageView;

public class PinchZoomImageActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_pinch_zoom_image);
        LoadComponents();
    }

    private void LoadComponents() {
        setSupportActionBar((Toolbar)findViewById(R.id.toolbar));
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        CustomImageView mImageView = (CustomImageView)findViewById(R.id.customImageView);
        //mImageView.setBitmap(BitmapFactory.decodeResource(getResources(), R.drawable.katiau));
        //ZoomableImageView touch = (ZoomableImageView)findViewById(R.id.zoomImage);
        //touch.setImageBitmap(BitmapFactory.decodeResource(getResources(), R.drawable.katiau));
    }
}
