package com.androidexamples.app.activity;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.view.MenuItem;
import android.widget.Toast;

import com.androidexamples.app.R;

public class StandAloneToolbarActivity extends AppCompatActivity {

    // Views
    private Toolbar toolbarBottom;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_stand_alone_toolbar);
        loadComponents();
    }

    private void loadComponents() {
        setSupportActionBar((Toolbar)findViewById(R.id.top_toolbar));

        toolbarBottom = (Toolbar)findViewById(R.id.toolbar_bottom);
        toolbarBottom.setOnMenuItemClickListener(onMenuItemClickListener());
        toolbarBottom.inflateMenu(R.menu.menu_bottom_toolbar);
    }

    private Toolbar.OnMenuItemClickListener onMenuItemClickListener() {
        return new Toolbar.OnMenuItemClickListener() {
            @Override
            public boolean onMenuItemClick(MenuItem item) {
                Toast.makeText(getBaseContext(), getString(R.string.click), Toast.LENGTH_SHORT).show();
                return true;
            }
        };
    }
}
