package com.example.marcos.androidexamples.app.activity;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.ViewFlipper;

import com.example.marcos.androidexamples.R;

public class ViewFlipperActivity extends AppCompatActivity {

    // Views
    private ViewFlipper viewFlipper;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_view_flipper);
        loadComponents();
    }

    private void loadComponents() {
        setSupportActionBar((Toolbar)findViewById(R.id.toolbar));
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        findViewById(R.id.button_flip).setOnClickListener(onFlipButtonClick());
        viewFlipper = (ViewFlipper)findViewById(R.id.view_flipper);
    }

    private View.OnClickListener onFlipButtonClick() {
        return new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // animação de saída da view atual
                Animation slideOutRight = AnimationUtils.loadAnimation(getBaseContext(), android.R.anim.slide_out_right);
                slideOutRight.setDuration(2000);
                // animação de entrada da próxima view
                Animation fadeIn = AnimationUtils.loadAnimation(getBaseContext(), android.R.anim.fade_in);
                fadeIn.setDuration(2000);
                // Configura a animação de entrada e saída
                viewFlipper.setInAnimation(fadeIn);
                viewFlipper.setOutAnimation(slideOutRight);
                viewFlipper.showNext();
            }
        };
    }
}
