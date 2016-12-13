package com.example.marcos.androidexamples.app.activity;

import android.content.Intent;
import android.graphics.Color;
import android.support.v4.view.MenuCompat;
import android.support.v4.view.MenuItemCompat;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.SearchView;
import android.support.v7.widget.ShareActionProvider;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;
import android.widget.Toast;

import com.example.marcos.androidexamples.R;
import com.example.marcos.androidexamples.app.adapter.SimpleItemAdapter;

import java.util.ArrayList;
import java.util.List;

public class SearchViewActivity extends AppCompatActivity {

    // Views
    private ListView listView;

    // Another objects
    private SimpleItemAdapter adapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_search_view);
        LoadComponents();
    }

    private void LoadComponents() {
        setSupportActionBar((Toolbar)findViewById(R.id.toobar));
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        listView = (ListView)findViewById(R.id.listView);
        listView.setOnItemClickListener(onItemClick());
    }

    private AdapterView.OnItemClickListener onItemClick() {
        return new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
                String item = (String)adapter.getItem(i);
                Toast.makeText(getBaseContext(), item + " clicked!", Toast.LENGTH_SHORT).show();
            }
        };
    }

    @Override
    protected void onResume() {
        super.onResume();

        List<String> listItems = new ArrayList<>();

        for(int i = 1; i <= 20; i++)
            listItems.add("Item " + i);

        adapter = new SimpleItemAdapter(this, listItems);
        listView.setAdapter(adapter);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu_search, menu);

        // SearchView
        MenuItem menuItemSearch = menu.findItem(R.id.search);
        SearchView searchView = (SearchView) menuItemSearch.getActionView();
        searchView.setOnQueryTextListener(onSearch());
        searchView.setOnCloseListener(onCloseSearch());

        // Action Provider
        MenuItem menuItemShare = menu.findItem(R.id.share);
        ShareActionProvider shareAction = (ShareActionProvider) MenuItemCompat.getActionProvider(menuItemShare);
        shareAction.setShareIntent(getDefaultIntent());

        return super.onCreateOptionsMenu(menu);
    }

    private SearchView.OnCloseListener onCloseSearch() {
        return new SearchView.OnCloseListener() {
            @Override
            public boolean onClose() {
                adapter.getFilter().filter(null);
                return false;
            }
        };
    }

    private Intent getDefaultIntent() {
        Intent intent = new Intent(Intent.ACTION_SEND);
        intent.setType("text/*");
        intent.putExtra(Intent.EXTRA_TEXT, "Texto para compartilhar");
        return intent;
    }

    private SearchView.OnQueryTextListener onSearch() {
        return new SearchView.OnQueryTextListener() {
            @Override
            public boolean onQueryTextSubmit(String query) {
                adapter.getFilter().filter(query);
                return false;
            }

            @Override
            public boolean onQueryTextChange(String newText) {
                return false;
            }
        };
    }
}
