package com.example.marcos.androidexamples.app.activity;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;

import com.example.marcos.androidexamples.R;
import com.example.marcos.androidexamples.app.util.TouchScreenView;

public class DragAndDropImageActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(new TouchScreenView(this));
        LoadComponents();
    }

    private void LoadComponents() {
//        setSupportActionBar((Toolbar)findViewById(R.id.toolbar));
//        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
    }
}
