package com.androidexamples.app.activity;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.view.MenuItem;
import android.view.Window;

import com.androidexamples.app.R;
import com.androidexamples.app.util.Constants;

public class TestActivity extends AppCompatActivity {

    private int animation;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        getWindow().requestFeature(Window.FEATURE_CONTENT_TRANSITIONS);
        setContentView(R.layout.activity_test);
        loadComponents();
    }

    private void loadComponents() {
        setSupportActionBar((Toolbar)findViewById(R.id.toolbar));
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        if(getIntent().getExtras() != null)
            animation = getIntent().getExtras().getInt(Constants.KEY_ANIMATION);
    }

    @Override
    public void finish() {
        super.finish();
        // Customiza a animação ao fechar a activity
        if(animation == Constants.ANIMATION_FADE)
            overridePendingTransition(android.R.anim.fade_in, android.R.anim.fade_out);
        else if(animation == Constants.ANIMATION_SLIDE_RIGHT)
            overridePendingTransition(R.anim.slide_in_right, R.anim.slide_out_right);
        else if(animation == Constants.ANIMATION_SLIDE_LEFT)
            overridePendingTransition(R.anim.slide_in_left, R.anim.slide_out_left);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            // Up Navigation - voltando com animação
            case android.R.id.home:
                supportFinishAfterTransition();
                return true;
        }
        return super.onOptionsItemSelected(item);
    }
}
