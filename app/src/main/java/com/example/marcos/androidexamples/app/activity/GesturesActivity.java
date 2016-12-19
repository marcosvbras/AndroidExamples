package com.example.marcos.androidexamples.app.activity;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.view.View;

import com.example.marcos.androidexamples.R;

public class GesturesActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_gestures);
        LoadComponents();
    }

    private void LoadComponents() {
        setSupportActionBar((Toolbar)findViewById(R.id.toolbar));
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        findViewById(R.id.button_zoomable_imageview).setOnClickListener(onZoomableImageViewButtonClick());
        findViewById(R.id.button_drag_drop_image).setOnClickListener(onDragDropImageButtonClick());
    }

    private View.OnClickListener onDragDropImageButtonClick() {
        return new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(getBaseContext(), DragImageActivity.class));
            }
        };
    }

    private View.OnClickListener onZoomableImageViewButtonClick() {
        return new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(getBaseContext(), ZoomableImageActivity.class));
            }
        };
    }
}
