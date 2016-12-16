package com.example.marcos.androidexamples.app.activity;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.view.ViewGroup;
import android.view.animation.AnimationUtils;
import android.widget.ImageSwitcher;
import android.widget.ImageView;
import android.widget.ViewSwitcher;

import com.example.marcos.androidexamples.R;

public class ImageSwitcherActivity extends AppCompatActivity {

    private int[] images = { R.drawable.toloveru, R.drawable.angel_beats, R.drawable.noragami, R.drawable.tghoul };
    private ImageSwitcher imageSwitcher;
    private int index = 0;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_image_switcher);
        LoadComponents();
    }

    private void LoadComponents() {
        setSupportActionBar((Toolbar)findViewById(R.id.toolbar));
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        imageSwitcher = (ImageSwitcher)findViewById(R.id.imageSwitcher);
        imageSwitcher.setFactory(getViewFactory());
        imageSwitcher.setInAnimation(AnimationUtils.loadAnimation(this, android.R.anim.fade_in));
        imageSwitcher.setOutAnimation(AnimationUtils.loadAnimation(this, android.R.anim.fade_out));
        findViewById(R.id.button_next).setOnClickListener(onNextButtonClick());
    }

    private View.OnClickListener onNextButtonClick() {
        return new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if(index == images.length)
                    index = 0;
                imageSwitcher.setImageResource(images[index++]);
            }
        };
    }

    public ViewSwitcher.ViewFactory getViewFactory() {
        return new ViewSwitcher.ViewFactory() {
            @Override
            public View makeView() {
                ImageView imageView = new ImageView(getBaseContext());
                imageView.setScaleType(ImageView.ScaleType.FIT_CENTER);
                imageView.setLayoutParams(new ImageSwitcher.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.MATCH_PARENT));
                return imageView;
            }
        };
    }
}
