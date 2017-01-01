package com.example.marcos.androidexamples.app.activity;

import android.app.ProgressDialog;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.Toast;

import com.example.marcos.androidexamples.R;

import java.io.IOException;
import java.io.InputStream;
import java.net.URL;

public class AnotherViewsActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_another_views);
        loadComponents();
    }

    private void loadComponents() {
        setSupportActionBar((Toolbar)findViewById(R.id.toolbar));
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        findViewById(R.id.button_webview).setOnClickListener(onWebViewButtonClick());
        findViewById(R.id.button_gridview).setOnClickListener(onGridViewButtonClick());
        findViewById(R.id.button_xml_selectors).setOnClickListener(onXmlSelectorsClick());
        findViewById(R.id.button_drawing_shapes).setOnClickListener(onDrawingShapesButtonClick());
        findViewById(R.id.button_image_switcher).setOnClickListener(onImageSwitcherButtonClick());
        findViewById(R.id.button_recycler_view).setOnClickListener(onRecyclerViewButtonClick());
        findViewById(R.id.button_spinner_complete_check).setOnClickListener(onSpinnerCompleteCheckButtonClick());
    }

    private View.OnClickListener onSpinnerCompleteCheckButtonClick() {
        return new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(getBaseContext(), SpinnerCompleteCheckActivity.class));
            }
        };
    }

    private View.OnClickListener onRecyclerViewButtonClick() {
        return new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(getBaseContext(), RecyclerViewActivity.class));
            }
        };
    }

    private View.OnClickListener onImageSwitcherButtonClick() {
        return new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(getBaseContext(), ImageSwitcherActivity.class));
            }
        };
    }

    private View.OnClickListener onDrawingShapesButtonClick() {
        return new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(getBaseContext(), DrawingShapesActivity.class));
            }
        };
    }

    private View.OnClickListener onWebViewButtonClick() {
        return new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(getBaseContext(), WebViewActivity.class));
            }
        };
    }

    private View.OnClickListener onGridViewButtonClick() {
        return new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(getBaseContext(), GridViewActivity.class));
            }
        };
    }

    private View.OnClickListener onXmlSelectorsClick() {
        return new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(getBaseContext(), XmlSelectorsActivity.class));
            }
        };
    }
}
