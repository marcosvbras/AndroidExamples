package com.example.marcos.androidexamples.app.activity;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.Toast;

import com.example.marcos.androidexamples.R;

public class MainActivity extends AppCompatActivity {

    private Toolbar toolbar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        LoadComponents();
    }

    private void LoadComponents() {
        toolbar = (Toolbar)findViewById(R.id.toobar);
        setSupportActionBar(toolbar);

        // Click events
        findViewById(R.id.button_pinch_zoom).setOnClickListener(onPinchZoomClick());
        findViewById(R.id.button_search_view).setOnClickListener(onSearchViewClick());
        findViewById(R.id.button_toolbar_tabs).setOnClickListener(onToolbarTabsClick());
        findViewById(R.id.button_xml_selectors).setOnClickListener(onXmlSelectorsClick());
    }

    private View.OnClickListener onXmlSelectorsClick() {
        return new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(getBaseContext(), XmlSelectorsActivity.class));
            }
        };
    }

    private View.OnClickListener onToolbarTabsClick() {
        return new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(getBaseContext(), ToolbarWithTabsActivity.class));
            }
        };
    }

    private View.OnClickListener onSearchViewClick() {
        return new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(getBaseContext(), SearchViewActivity.class));
            }
        };
    }

    private View.OnClickListener onPinchZoomClick() {
        return new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(getBaseContext(), PinchZoomImageActivity.class));
            }
        };
    }

    private void showMessage(String message) {
        Toast.makeText(this, message, Toast.LENGTH_SHORT).show();
    }
}
