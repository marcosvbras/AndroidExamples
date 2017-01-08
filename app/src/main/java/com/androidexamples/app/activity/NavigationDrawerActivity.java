package com.androidexamples.app.activity;

import android.os.Bundle;

import com.androidexamples.app.R;
import com.androidexamples.app.fragments.FavoriteFragment;

public class NavigationDrawerActivity extends BaseActivity {

    // Para este exemplo, adicionar a dependência Android Support Library no Gradle
    // Para toda activity que necessita do NavigationDrawer, modificar o Theme da activity no Manifest para @style/AppTheme.NoActionBar.NavDrawer

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_navigation_drawer);
        loadComponents();
    }

    private void loadComponents() {
        setUpToolbar();
        setupNavigationDrawer();
        selectSelectedItem(0);
        replaceFragment(new FavoriteFragment());
    }
}
