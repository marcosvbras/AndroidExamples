package com.androidexamples.app.activity;

import android.support.annotation.NonNull;
import android.support.design.widget.NavigationView;
import android.support.v4.app.Fragment;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.MenuItem;
import android.view.View;
import android.widget.ImageView;
import android.widget.Toast;

import com.androidexamples.app.R;
import com.androidexamples.app.fragments.FavoriteFragment;
import com.androidexamples.app.fragments.RateFragment;
import com.androidexamples.app.fragments.SettingsFragment;
import com.androidexamples.app.utils.Constants;

public class BaseActivity extends AppCompatActivity {

    protected DrawerLayout drawerLayout;
    protected NavigationView navigationView;

    protected void setUpToolbar() {
        Toolbar toolbar = (Toolbar)findViewById(R.id.top_toolbar);
        if(toolbar != null)
            setSupportActionBar(toolbar);
    }

    protected void setupNavigationDrawer() {
        final ActionBar actionBar = getSupportActionBar();

        // Ícone do menu da Nav Drawer
        actionBar.setHomeAsUpIndicator(R.drawable.ic_menu_white);
        actionBar.setDisplayHomeAsUpEnabled(true);
        drawerLayout = (DrawerLayout)findViewById(R.id.drawer_layout);
        navigationView = (NavigationView)findViewById(R.id.navigation_view);

        if(navigationView != null && drawerLayout != null) {
            // Atualiza imagens e textos da header
            setNavViewValues(navigationView, R.drawable.jack_sparrow_ptc);

            // Trata o evento de clique do menu
            navigationView.setNavigationItemSelectedListener(onNavigationItemSelected());
        }
    }

    protected void selectSelectedItem(int index) {
        if(navigationView != null) {
            navigationView.getMenu().getItem(index).setChecked(true);
        }
    }

    // Atualiza os dados da header do Navigation Viewpublic
    public static void setNavViewValues(NavigationView navigationView, int imageResource) {
        View headerView = navigationView.getHeaderView(0);
        ImageView imageView = (ImageView)headerView.findViewById(R.id.imageViewHeader);
        imageView.setImageResource(imageResource);
    }

    private NavigationView.OnNavigationItemSelectedListener onNavigationItemSelected() {
        return new NavigationView.OnNavigationItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected(@NonNull MenuItem menuItem) {
                // Seleciona a linha
                menuItem.setChecked(true);
                // Fecha o menu
                drawerLayout.closeDrawers();
                // Trata o evento do menu
                onNavDrawerItemSelected(menuItem);

                return true;
            }
        };
    }

    // Trata o evento do menu lateral
    private void onNavDrawerItemSelected(MenuItem menuItem) {
        switch(menuItem.getItemId()) {
            case R.id.nav_item_favorite:
                replaceFragment(new FavoriteFragment());
                break;
            case R.id.nav_item_settings:
                replaceFragment(new SettingsFragment());
                break;
            case R.id.nav_item_rate:
                replaceFragment(new RateFragment());
                break;
        }
    }

    protected void replaceFragment(Fragment fragment) {
        getSupportFragmentManager().beginTransaction().replace(R.id.container, fragment, Constants.TAG_FAVORITE_FRAG).commit();
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case android.R.id.home:
                // Trata o clique no botão que abre o menu
                if(drawerLayout != null) {
                    openDrawer();
                    return true;
                }
        }

        return super.onOptionsItemSelected(item);
    }

    // Abre o NavigationDrawer
    protected void openDrawer() {
        if(drawerLayout != null)
            drawerLayout.openDrawer(GravityCompat.START);
    }

    // Fecha o NavigationDrawer
    protected void closeDrawer() {
        if(drawerLayout != null)
            drawerLayout.closeDrawer(GravityCompat.START);
    }
}
