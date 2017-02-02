package com.androidexamples.app.activity;

import android.support.v4.content.ContextCompat;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.view.ActionMode;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.Toast;

import com.androidexamples.app.R;
import com.androidexamples.app.adapter.ContextAdapter;
import com.androidexamples.app.domain.ContextExample;
import com.androidexamples.app.interfaces.RecyclerViewTouchListener;
import com.androidexamples.app.listener.RecyclerItemClickListener;

import java.util.ArrayList;
import java.util.List;

public class ContextActionBarActivity extends AppCompatActivity implements RecyclerViewTouchListener {

    /**
     * Necessita adicionar no tema:
     * <item name="actionModeBackground">@color/colorPrimary</item>
     * <item name="windowActionModeOverlay">true</item>
     * */

    // Views
    private RecyclerView recyclerView;

    // Another objects
    private ContextAdapter contextAdapter;
    private List<ContextExample> listExamples;
    private ActionMode actionMode;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_context_action_bar);
        loadComponents();
    }

    private void loadComponents() {
        setSupportActionBar((Toolbar)findViewById(R.id.top_toolbar));
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        recyclerView = (RecyclerView) findViewById(R.id.recyclerView);
        recyclerView.addOnItemTouchListener(new RecyclerItemClickListener(this, recyclerView, this));
        recyclerView.setHasFixedSize(true);
    }

    private void populateList() {
        listExamples = new ArrayList<>();

        for(int i = 1; i < 20; i++)
            listExamples.add(new ContextExample("Item " + i, false));
    }

    @Override
    protected void onResume() {
        super.onResume();

        populateList();
        contextAdapter = new ContextAdapter(listExamples, this);
        recyclerView.setLayoutManager(new LinearLayoutManager(this, LinearLayoutManager.VERTICAL, false));
        recyclerView.setAdapter(contextAdapter);
    }

    @Override
    public void onItemClickListener(View view, int position) {
        if(actionMode != null) {
            ContextExample contextExample = contextAdapter.getItemAtPosition(position);
            contextExample.setSelected(!contextExample.isSelected());
            contextAdapter.notifyDataSetChanged();
            updateActionModeTitle();
        }
    }

    @Override
    public void onLongItemClickListener(View view, int position) {
        // Só o action mode já existe,
        if(actionMode != null)
            return;

        actionMode = startSupportActionMode(getActionModeCallBack());

        ContextExample contextExample = contextAdapter.getItemAtPosition(position);
        contextExample.setSelected(true);
        contextAdapter.notifyDataSetChanged();
        updateActionModeTitle();
    }

    private void updateActionModeTitle() {
        if(actionMode != null) {
            int count = 0;

            for(ContextExample item : listExamples) {
                if(item.isSelected())
                    count++;
            }

            if(count > 0) {
                actionMode.setSubtitle(null);
                actionMode.setTitle(count + " selected items");
            } else {
                actionMode.finish();
                actionMode = null;
            }
        }
    }

    public ActionMode.Callback getActionModeCallBack() {
        return new ActionMode.Callback() {
            @Override
            public boolean onCreateActionMode(ActionMode mode, Menu menu) {
                // Infla o menu
                MenuInflater menuInflater = getMenuInflater();
                menuInflater.inflate(R.menu.menu_context, menu);
                return true;
            }

            @Override
            public boolean onPrepareActionMode(ActionMode mode, Menu menu) {
                return true;
            }

            @Override
            public boolean onActionItemClicked(ActionMode mode, MenuItem menuItem) {
                if(menuItem.getItemId() == R.id.menu_item_delete)
                    Toast.makeText(getBaseContext(), "Click", Toast.LENGTH_SHORT).show();

                // Encerra o ActionMode
                mode.finish();
                return true;
            }

            @Override
            public void onDestroyActionMode(ActionMode mode) {
                // Limpa o estado
                actionMode = null;

                for(ContextExample item : listExamples)
                    item.setSelected(false);

                contextAdapter.notifyDataSetChanged();
            }
        };
    }
}
