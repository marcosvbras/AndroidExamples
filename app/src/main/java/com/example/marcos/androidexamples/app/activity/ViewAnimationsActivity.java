package com.example.marcos.androidexamples.app.activity;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.view.View;

import com.example.marcos.androidexamples.R;

public class ViewAnimationsActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_view_animations);
        loadComponents();
    }

    private void loadComponents() {
        setSupportActionBar((Toolbar)findViewById(R.id.toolbar));
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        //findViewById(R.id.button_animation_drawable).setOnClickListener(onAnimationsClick());
        findViewById(R.id.button_alpha).setOnClickListener(onAnimationsClick());
        findViewById(R.id.button_rotate).setOnClickListener(onAnimationsClick());
        findViewById(R.id.button_scale).setOnClickListener(onAnimationsClick());
        findViewById(R.id.button_translate).setOnClickListener(onAnimationsClick());
        findViewById(R.id.button_animation_listener).setOnClickListener(onAnimationsClick());
        findViewById(R.id.button_animation_set).setOnClickListener(onAnimationsClick());
    }

    private View.OnClickListener onAnimationsClick() {
        return new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                switch (v.getId()) {
                    case R.id.button_alpha:
                        startActivity(new Intent(getBaseContext(), AlphaActivity.class));
                        break;
                    case R.id.button_rotate:
                        startActivity(new Intent(getBaseContext(), RotateActivity.class));
                        break;
//                    case R.id.button_animation_drawable:
//                        startActivity(new Intent(getBaseContext(), AnimationDrawableActivity.class));
//                        break;
                    case R.id.button_scale:
                        startActivity(new Intent(getBaseContext(), ScaleActivity.class));
                        break;
                    case R.id.button_translate:
                        startActivity(new Intent(getBaseContext(), TranslateActivity.class));
                        break;
                    case R.id.button_animation_listener:
                        startActivity(new Intent(getBaseContext(), AnimationListenerActivity.class));
                        break;
                    case R.id.button_animation_set:
                        startActivity(new Intent(getBaseContext(), AnimationSetActivity.class));
                        break;
                }
            }
        };
    }
}
