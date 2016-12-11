package com.example.marcos.androidexamples.app.activity;

import android.graphics.BitmapFactory;
import android.graphics.Color;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;

import com.example.marcos.androidexamples.R;
import com.example.marcos.androidexamples.app.util.CustomImageView;

public class PinchZoomImageActivity extends AppCompatActivity {

    private Toolbar toolbar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_pinch_zoom_image);
        LoadComponents();
    }

    private void LoadComponents() {
        toolbar = (Toolbar)findViewById(R.id.toobar);
        toolbar.setTitleTextColor(Color.WHITE);
        setSupportActionBar(toolbar);
        CustomImageView mImageView = (CustomImageView)findViewById(R.id.customImageView);
        mImageView.setBitmap(BitmapFactory.decodeResource(getResources(), R.drawable.katiau));
        //ZoomableImageView touch = (ZoomableImageView)findViewById(R.id.zoomImage);
        //touch.setImageBitmap(BitmapFactory.decodeResource(getResources(), R.drawable.katiau));
    }
}
