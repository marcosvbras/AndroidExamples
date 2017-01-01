package com.example.marcos.androidexamples.app.activity;

import android.content.Intent;
import android.provider.SyncStateContract;
import android.support.v4.app.ActivityCompat;
import android.support.v4.app.ActivityOptionsCompat;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.view.View;

import com.example.marcos.androidexamples.R;
import com.example.marcos.androidexamples.app.util.Constants;

import junit.framework.Test;

public class ActivityAnimationsActivity extends AppCompatActivity {

    private Intent intent;
    private Bundle bundle;
    private ActivityOptionsCompat activityOptionsCompat;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_animations_activity);
        loadComponents();
    }

    private void loadComponents() {
        setSupportActionBar((Toolbar)findViewById(R.id.toolbar));
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        findViewById(R.id.button_fade).setOnClickListener(onFadeButtonClick());
        findViewById(R.id.button_slide_right).setOnClickListener(onSlideRightButtonClick());
        findViewById(R.id.button_slide_left).setOnClickListener(onSlideLeftButtonClick());
        findViewById(R.id.button_scale).setOnClickListener(onScaleButtonClick());
        intent = new Intent(getBaseContext(), TestActivity.class);
        intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
    }

    private View.OnClickListener onScaleButtonClick() {
        return new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                bundle = ActivityOptionsCompat.makeScaleUpAnimation(v, 0, 0, v.getWidth(), v.getHeight()).toBundle();
                ActivityCompat.startActivity(ActivityAnimationsActivity.this, intent, bundle);
            }
        };
    }

    private View.OnClickListener onSlideLeftButtonClick() {
        return new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                bundle = new Bundle();
                bundle.putInt(Constants.KEY_ANIMATION, Constants.ANIMATION_SLIDE_LEFT);
                intent.putExtras(bundle);

                activityOptionsCompat = ActivityOptionsCompat.makeCustomAnimation(getBaseContext(),
                        R.anim.slide_in_left, R.anim.slide_out_left);
                ActivityCompat.startActivity(getBaseContext(), intent, activityOptionsCompat.toBundle());
            }
        };
    }

    private View.OnClickListener onSlideRightButtonClick() {
        return new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                bundle = new Bundle();
                bundle.putInt(Constants.KEY_ANIMATION, Constants.ANIMATION_SLIDE_RIGHT);
                intent.putExtras(bundle);

                activityOptionsCompat = ActivityOptionsCompat.makeCustomAnimation(getBaseContext(),
                        R.anim.slide_in_right, R.anim.slide_out_right);
                ActivityCompat.startActivity(getBaseContext(), intent, activityOptionsCompat.toBundle());
            }
        };
    }

    private View.OnClickListener onFadeButtonClick() {
        return new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                bundle = new Bundle();
                bundle.putInt(Constants.KEY_ANIMATION, Constants.ANIMATION_FADE);
                intent.putExtras(bundle);

                activityOptionsCompat = ActivityOptionsCompat.makeCustomAnimation(getBaseContext(),
                        android.R.anim.fade_in, android.R.anim.fade_out);
                ActivityCompat.startActivity(getBaseContext(), intent, activityOptionsCompat.toBundle());
            }
        };
    }
}
