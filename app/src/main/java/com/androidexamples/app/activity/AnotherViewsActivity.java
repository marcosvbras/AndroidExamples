package com.androidexamples.app.activity;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.view.View;

import com.androidexamples.app.R;

public class AnotherViewsActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_another_views);
        loadComponents();
    }

    private void loadComponents() {
        setSupportActionBar((Toolbar)findViewById(R.id.top_toolbar));
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        findViewById(R.id.button_webview).setOnClickListener(onButtonClick());
        findViewById(R.id.button_gridview).setOnClickListener(onButtonClick());
        findViewById(R.id.button_xml_selectors).setOnClickListener(onButtonClick());
        findViewById(R.id.button_drawing_shapes).setOnClickListener(onButtonClick());
        findViewById(R.id.button_image_switcher).setOnClickListener(onButtonClick());
        findViewById(R.id.button_recycler_view).setOnClickListener(onButtonClick());
        findViewById(R.id.button_spinner_complete_check).setOnClickListener(onButtonClick());
        findViewById(R.id.button_fab_with_snackbar).setOnClickListener(onButtonClick());
    }

    private View.OnClickListener onButtonClick() {
        return new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                switch (v.getId()) {
                    case R.id.button_drawing_shapes:
                        startActivity(new Intent(getBaseContext(), DrawingShapesActivity.class));
                        break;
                    case R.id.button_fab_with_snackbar:
                        startActivity(new Intent(getBaseContext(), FabWithSnackBarActivity.class));
                        break;
                    case R.id.button_gridview:
                        startActivity(new Intent(getBaseContext(), GridViewActivity.class));
                        break;
                    case R.id.button_image_switcher:
                        startActivity(new Intent(getBaseContext(), ImageSwitcherActivity.class));
                        break;
                    case R.id.button_webview:
                        startActivity(new Intent(getBaseContext(), WebViewActivity.class));
                        break;
                    case R.id.button_recycler_view:
                        startActivity(new Intent(getBaseContext(), RecyclerViewActivity.class));
                        break;
                    case R.id.button_spinner_complete_check:
                        startActivity(new Intent(getBaseContext(), SpinnerCompleteCheckActivity.class));
                        break;
                    case R.id.button_xml_selectors:
                        startActivity(new Intent(getBaseContext(), XmlSelectorsActivity.class));
                        break;
                }
            }
        };
    }
}
