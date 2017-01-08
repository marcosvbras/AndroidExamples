package com.androidexamples.app.activity;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.view.ViewGroup;
import android.view.animation.AnimationUtils;
import android.widget.ImageSwitcher;
import android.widget.ImageView;
import android.widget.ViewSwitcher;

import com.androidexamples.app.R;
import com.androidexamples.app.domain.SimpleItem;
import com.androidexamples.app.utils.Constants;

import java.util.ArrayList;
import java.util.List;

public class ImageSwitcherActivity extends AppCompatActivity {

    // Views
    private ImageSwitcher imageSwitcher;

    // Another Objects
    private int index;
    private List<SimpleItem> listSimpleItems;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_image_switcher);
        loadComponents();
        index = 0;
    }

    private void loadComponents() {
        setSupportActionBar((Toolbar)findViewById(R.id.top_toolbar));
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        imageSwitcher = (ImageSwitcher)findViewById(R.id.imageSwitcher);
        imageSwitcher.setFactory(getViewFactory());
        imageSwitcher.setInAnimation(AnimationUtils.loadAnimation(this, android.R.anim.slide_in_left));
        imageSwitcher.setOutAnimation(AnimationUtils.loadAnimation(this, android.R.anim.fade_out));
        findViewById(R.id.button_previous).setOnClickListener(onControlButtonClick());
        findViewById(R.id.button_next).setOnClickListener(onControlButtonClick());
    }

    private View.OnClickListener onControlButtonClick() {
        return new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(v.getId() == R.id.button_previous) {
                    if(index == 0)
                        index = listSimpleItems.size() - 1;
                    else
                        index--;
                } else if(v.getId() == R.id.button_next) {
                    if(index == listSimpleItems.size() - 1)
                        index = 0;
                    else
                        index++;
                }

                imageSwitcher.setImageResource(listSimpleItems.get(index).getImageResource());
            }
        };
    }

    @Override
    protected void onResume() {
        super.onResume();
        populateList();
        imageSwitcher.setImageResource(listSimpleItems.get(index).getImageResource());
    }

    private void populateList() {
        listSimpleItems = new ArrayList<>();
        listSimpleItems.add(new SimpleItem("Akami ga Kill", R.drawable.akami_ga_kill));
        listSimpleItems.add(new SimpleItem("Angel Beats!", R.drawable.angel_beats));
        listSimpleItems.add(new SimpleItem("Attack On Titan", R.drawable.attack_on_titan));
        listSimpleItems.add(new SimpleItem("Btooom!", R.drawable.btooom));
        listSimpleItems.add(new SimpleItem("No Game, No Life!", R.drawable.no_game_no_life));
        listSimpleItems.add(new SimpleItem("Noragami", R.drawable.noragami));
        listSimpleItems.add(new SimpleItem("Tokyo Ghoul", R.drawable.tghoul));
        listSimpleItems.add(new SimpleItem("Tokyo Ghoul Root A", R.drawable.tokyo));
        listSimpleItems.add(new SimpleItem("To Love-Ru", R.drawable.toloveru));
    }

    public ViewSwitcher.ViewFactory getViewFactory() {
        return new ViewSwitcher.ViewFactory() {
            @Override
            public View makeView() {
                ImageView imageView = new ImageView(getBaseContext());
                imageView.setScaleType(ImageView.ScaleType.CENTER_INSIDE);
                imageView.setLayoutParams(new ImageSwitcher.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.MATCH_PARENT));
                return imageView;
            }
        };
    }

    @Override
    protected void onSaveInstanceState(Bundle outState) {
        super.onSaveInstanceState(outState);
        outState.putInt(Constants.KEY_COUNT, index);
    }

    @Override
    protected void onRestoreInstanceState(Bundle savedInstanceState) {
        super.onRestoreInstanceState(savedInstanceState);

        if(savedInstanceState != null)
            index = savedInstanceState.getInt(Constants.KEY_COUNT);
    }
}
