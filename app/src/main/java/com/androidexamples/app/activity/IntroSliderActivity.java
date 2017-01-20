package com.androidexamples.app.activity;

import android.content.Intent;
import android.graphics.Color;
import android.os.Build;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.Html;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.androidexamples.app.R;
import com.androidexamples.app.adapter.IntroAdapter;
import com.androidexamples.app.utils.PreferencesManager;

import java.util.ArrayList;
import java.util.List;

public class IntroSliderActivity extends AppCompatActivity {

    private ViewPager viewPager;
    private IntroAdapter introAdapter;
    private LinearLayout linearLayoutDots;
    private List<TextView> listTextViewDots;
    private List<Integer> listLayouts;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_intro_slider);

        // Checking for first time launch - before calling setContentView()
        if (!PreferencesManager.isFirstTimeLaunch(this)) {
            launchHomeScreen();
            finish();
        }

        // Making notification bar transparent
        if (Build.VERSION.SDK_INT >= 21)
            getWindow().getDecorView().setSystemUiVisibility(View.SYSTEM_UI_FLAG_LAYOUT_STABLE | View.SYSTEM_UI_FLAG_LAYOUT_FULLSCREEN);

        loadComponents();
    }

    private void loadComponents() {
        viewPager = (ViewPager) findViewById(R.id.view_pager);
        linearLayoutDots = (LinearLayout) findViewById(R.id.layoutDots);
        findViewById(R.id.button_skip).setOnClickListener(onButtonClick());
        findViewById(R.id.btn_next).setOnClickListener(onButtonClick());
    }

    private View.OnClickListener onButtonClick() {
        return new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                switch (v.getId()) {
                    case R.id.button_skip:
                        launchHomeScreen();
                        break;
                    case R.id.btn_next:
                        // if last page home screen will be launched
                        int current = getItem(+1);
                        if (current < listLayouts.size())
                            viewPager.setCurrentItem(current); // move to next screen
                        else
                            launchHomeScreen();
                        break;
                }
            }
        };
    }

    @Override
    protected void onResume() {
        super.onResume();
        // listLayouts of all welcome sliders
        // add few more listLayouts if you want
        listLayouts = new ArrayList<>();
        listLayouts.add(R.layout.welcome_slide1);
        listLayouts.add(R.layout.welcome_slide2);
        listLayouts.add(R.layout.welcome_slide3);
        listLayouts.add(R.layout.welcome_slide4);

        // adding bottom listTextViewDots
        addBottomDots(0);

        // making notification bar transparent
        changeStatusBarColor();

        introAdapter = new IntroAdapter(this, listLayouts);
        viewPager.setAdapter(introAdapter);
        viewPager.addOnPageChangeListener(onViewPagerPageChange());
    }

    private ViewPager.OnPageChangeListener onViewPagerPageChange() {
        return new ViewPager.OnPageChangeListener() {
            @Override
            public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {

            }

            @Override
            public void onPageSelected(int position) {
                addBottomDots(position);

                // changing the next button text 'NEXT' / 'GOT IT'
                if (position == listLayouts.size() - 1) {
                    // last page. make button text to GOT IT
                    ((Button)findViewById(R.id.btn_next)).setText(getString(R.string.got_it));
                    findViewById(R.id.button_skip).setVisibility(View.GONE);
                } else {
                    // still pages are left
                    ((Button)findViewById(R.id.btn_next)).setText(getString(R.string.next));
                    findViewById(R.id.button_skip).setVisibility(View.VISIBLE);
                }
            }

            @Override
            public void onPageScrollStateChanged(int state) {

            }
        };
    }

    private void addBottomDots(int currentPage) {
        listTextViewDots = new ArrayList<>();
        int[] colorsActive = getResources().getIntArray(R.array.array_dot_active);
        int[] colorsInactive = getResources().getIntArray(R.array.array_dot_inactive);

        linearLayoutDots.removeAllViews();
        for (int i = 0; i < listTextViewDots.size(); i++) {
            listTextViewDots.add(new TextView(this));
            listTextViewDots.get(i).setText(Html.fromHtml("&#8226;"));
            listTextViewDots.get(i).setTextSize(35);
            listTextViewDots.get(i).setTextColor(colorsInactive[currentPage]);
            linearLayoutDots.addView(listTextViewDots.get(i));
        }

        if (listTextViewDots.size() > 0)
            listTextViewDots.get(currentPage).setTextColor(colorsActive[currentPage]);
    }

    private int getItem(int i) {
        return viewPager.getCurrentItem() + i;
    }

    private void launchHomeScreen() {
        PreferencesManager.setFirstTimeLaunch(this, false);
        startActivity(new Intent(this, MainActivity.class));
        finish();
    }

    /**
     * Making notification bar transparent
     */
    private void changeStatusBarColor() {
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
            Window window = getWindow();
            window.addFlags(WindowManager.LayoutParams.FLAG_DRAWS_SYSTEM_BAR_BACKGROUNDS);
            window.setStatusBarColor(Color.TRANSPARENT);
        }
    }
}

