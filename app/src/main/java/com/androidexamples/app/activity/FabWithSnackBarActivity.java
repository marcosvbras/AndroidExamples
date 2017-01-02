package com.androidexamples.app.activity;

import android.support.design.widget.CoordinatorLayout;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.Toast;

import com.androidexamples.app.R;

public class FabWithSnackBarActivity extends AppCompatActivity {

    // Views
    private CoordinatorLayout coordinatorLayoutRoot;
    private FloatingActionButton floatingActionButtonMini;
    private FloatingActionButton floatingActionButtonNormal;
    private FloatingActionButton floatingActionButtonBottom;

    // Another Objects
    private Animation animation;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_fab_with_snackbar);
        loadComponents();
    }

    private void loadComponents() {
        setSupportActionBar((Toolbar)findViewById(R.id.toolbar));
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        animation = AnimationUtils.loadAnimation(this, R.anim.scale_up);
        coordinatorLayoutRoot = (CoordinatorLayout) findViewById(R.id.coordinatorLayoutRoot);
        floatingActionButtonNormal = (FloatingActionButton)findViewById(R.id.fab_normal);
        floatingActionButtonNormal.startAnimation(animation);
        floatingActionButtonNormal.setOnClickListener(onFabClick());
        floatingActionButtonMini = (FloatingActionButton)findViewById(R.id.fab_mini);
        floatingActionButtonMini.startAnimation(animation);
        floatingActionButtonMini.setOnClickListener(onFabClick());
        floatingActionButtonBottom = (FloatingActionButton)findViewById(R.id.fab_bottom);
        floatingActionButtonBottom.startAnimation(animation);
        floatingActionButtonBottom.setOnClickListener(onFabClick());
    }

    private View.OnClickListener onFabClick() {
        return new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                switch (v.getId()) {
                    case R.id.fab_mini:
                        showSnackBar();
                        break;
                    case R.id.fab_normal:
                        showSnackBar();
                        break;
                    case R.id.fab_bottom:
                        showSnackBar();
                        break;
                }
            }
        };
    }

    private void showSnackBar() {
        Snackbar.make(coordinatorLayoutRoot, getResources().getString(R.string.this_is_snackbar), Snackbar.LENGTH_LONG)
                .setAction(getResources().getString(R.string.ok), new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        Toast.makeText(getBaseContext(), getResources().getString(R.string.ok), Toast.LENGTH_SHORT).show();
                    }
                })
                .show();
    }
}
