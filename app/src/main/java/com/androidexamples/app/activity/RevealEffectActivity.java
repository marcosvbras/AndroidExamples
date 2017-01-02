package com.androidexamples.app.activity;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.ImageView;

import com.androidexamples.app.R;
import com.androidexamples.app.util.RevealEffect;

public class RevealEffectActivity extends AppCompatActivity {

    // Views
    private ImageView imageView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_reveal_effect);
        loadComponents();
    }

    private void loadComponents() {
        setSupportActionBar((Toolbar)findViewById(R.id.toolbar));
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        imageView = (ImageView)findViewById(R.id.imageView);
        findViewById(R.id.button_show).setOnClickListener(onButtonClick());
        findViewById(R.id.button_hide).setOnClickListener(onButtonClick());
    }

    private View.OnClickListener onButtonClick() {
        return new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                switch(v.getId()) {
                    case R.id.button_show:
                        RevealEffect.show(imageView, 500);
                        break;
                    case R.id.button_hide:
                        RevealEffect.hide(imageView, 500);
                        break;
                }
            }
        };
    }
}
