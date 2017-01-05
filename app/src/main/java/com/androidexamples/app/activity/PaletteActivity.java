package com.androidexamples.app.activity;

import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.drawable.BitmapDrawable;
import android.support.v4.content.ContextCompat;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.graphics.Palette;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;

import com.androidexamples.app.R;

public class PaletteActivity extends AppCompatActivity {

    // Views
    private LinearLayout linearLayout1;
    private LinearLayout linearLayout2;
    private LinearLayout linearLayout3;
    private LinearLayout linearLayout4;
    private LinearLayout linearLayout5;
    private LinearLayout linearLayout6;
    private LinearLayout linearLayoutActivity;
    private ImageView imageView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_palette);
        loadComponents();
    }

    private void loadComponents() {
        setSupportActionBar((Toolbar)findViewById(R.id.toolbar));
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        linearLayout1 = (LinearLayout)findViewById(R.id.linearLayout1);
        linearLayout2 = (LinearLayout)findViewById(R.id.linearLayout2);
        linearLayout3 = (LinearLayout)findViewById(R.id.linearLayout3);
        linearLayout4 = (LinearLayout)findViewById(R.id.linearLayout4);
        linearLayout5 = (LinearLayout)findViewById(R.id.linearLayout5);
        linearLayout6 = (LinearLayout)findViewById(R.id.linearLayout6);
        linearLayoutActivity = (LinearLayout)findViewById(R.id.activity_palette);
        imageView = (ImageView)findViewById(R.id.imageView);

        findViewById(R.id.button_extract_colors).setOnClickListener(onExtracColorsButtonClick());
    }

    private View.OnClickListener onExtracColorsButtonClick() {
        return new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Bitmap bitmap = ((BitmapDrawable)imageView.getDrawable()).getBitmap();
                // Necessário inclusão de dependência no gradle
                Palette.from(bitmap).generate(onGenerateFinish());
            }
        };
    }

    private Palette.PaletteAsyncListener onGenerateFinish() {
        return new Palette.PaletteAsyncListener() {
            @Override
            public void onGenerated(Palette palette) {
                linearLayout1.setBackgroundColor(palette.getVibrantColor(getDefauColor()));
                linearLayout2.setBackgroundColor(palette.getDarkVibrantColor(getDefauColor()));
                linearLayout3.setBackgroundColor(palette.getLightVibrantColor(getDefauColor()));
                linearLayout4.setBackgroundColor(palette.getMutedColor(getDefauColor()));
                linearLayout5.setBackgroundColor(palette.getDarkMutedColor(getDefauColor()));
                linearLayout6.setBackgroundColor(palette.getLightMutedColor(getDefauColor()));
                linearLayoutActivity.setBackgroundColor(palette.getVibrantColor(getDefauColor()));
            }
        };
    }

    private int getDefauColor() {
        return ContextCompat.getColor(this, R.color.colorPrimary);
    }
}
