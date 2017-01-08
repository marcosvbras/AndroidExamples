package com.androidexamples.app.activity;

import android.content.Intent;
import android.support.v4.app.ActivityCompat;
import android.support.v4.app.ActivityOptionsCompat;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.view.Window;
import android.widget.ImageView;

import com.androidexamples.app.R;
import com.androidexamples.app.utils.Constants;

public class ActivityAnimationsActivity extends AppCompatActivity {

    // Views
    private ImageView imageView;

    // Another Objects
    private Intent intent;
    private Bundle bundle;
    private ActivityOptionsCompat activityOptionsCompat;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        getWindow().requestFeature(Window.FEATURE_CONTENT_TRANSITIONS);
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_animations_activity);
        loadComponents();
    }

    private void loadComponents() {
        setSupportActionBar((Toolbar)findViewById(R.id.top_toolbar));
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        imageView = (ImageView) findViewById(R.id.imageView);
        findViewById(R.id.button_fade).setOnClickListener(onFadeButtonClick());
        findViewById(R.id.button_slide_right).setOnClickListener(onSlideRightButtonClick());
        findViewById(R.id.button_slide_left).setOnClickListener(onSlideLeftButtonClick());
        findViewById(R.id.button_scale).setOnClickListener(onScaleButtonClick());
        findViewById(R.id.button_zoom).setOnClickListener(onZoomButtonClick());
        intent = new Intent(getBaseContext(), TestActivity.class);
        intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
    }

    private View.OnClickListener onZoomButtonClick() {
        return new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                ActivityOptionsCompat opts = ActivityOptionsCompat.makeCustomAnimation(getBaseContext(),
                        R.anim.zoom_enter, R.anim.zoom_enter);
                ActivityCompat.startActivity(getBaseContext(), intent, opts.toBundle());
            }
        };
    }

    private View.OnClickListener onScaleButtonClick() {
        return new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                bundle = ActivityOptionsCompat.makeScaleUpAnimation(v, 0, 0, v.getWidth(), v.getHeight()).toBundle();
                ActivityCompat.startActivity(getBaseContext(), intent, bundle);
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
