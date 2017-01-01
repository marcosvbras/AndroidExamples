package com.example.marcos.androidexamples.app.activity;

import android.content.DialogInterface;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.StaggeredGridLayoutManager;
import android.support.v7.widget.Toolbar;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.RadioButton;
import android.widget.Spinner;
import android.widget.Toast;

import com.example.marcos.androidexamples.R;
import com.example.marcos.androidexamples.app.adapter.BlueHairAdapter;
import com.example.marcos.androidexamples.app.entity.SimpleItem;
import com.example.marcos.androidexamples.app.listener.RecyclerItemClickListener;
import com.example.marcos.androidexamples.app.interfaces.RecyclerViewTouchListener;
import com.example.marcos.androidexamples.app.util.Constants;
import com.example.marcos.androidexamples.app.util.RecyclerSettings;

import java.util.ArrayList;
import java.util.List;

public class RecyclerViewActivity extends AppCompatActivity implements RecyclerViewTouchListener {

    // View
    private RecyclerView recyclerView;
    private View viewDialog;
    private Spinner spinnerAnimation;
    private Spinner spinnerLayoutManager;
    private RadioButton radioButtonAlways;
    private RadioButton radioButtonNotLoaded;

    // Outros objetos
    private AlertDialog dialogSettings;
    private BlueHairAdapter blueHairAdapter;
    private List<SimpleItem> listSimpleItem;
    private RecyclerView.LayoutManager layoutManager;
    private LinearLayoutManager linearLayoutManager;
    private GridLayoutManager gridLayoutManager;
    private StaggeredGridLayoutManager staggeredGridLayoutManager;
    private RecyclerSettings recyclerSettings;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_recycler_view);
        loadComponents();
        loadLayoutManager();
        loadDefaultSettings();
    }

    private void loadComponents() {
        setSupportActionBar((Toolbar)findViewById(R.id.toolbar));
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        recyclerView = (RecyclerView)findViewById(R.id.recyclerView);
    }

    private void loadLayoutManager() {
        linearLayoutManager = new LinearLayoutManager(this, LinearLayoutManager.VERTICAL, false);
        linearLayoutManager.setStackFromEnd(false);
        gridLayoutManager = new GridLayoutManager(this, 3, GridLayoutManager.VERTICAL, false);
        staggeredGridLayoutManager = new StaggeredGridLayoutManager(3, StaggeredGridLayoutManager.VERTICAL);
        staggeredGridLayoutManager.setReverseLayout(false);
    }

    private void loadDefaultSettings() {
        recyclerSettings = new RecyclerSettings();
        recyclerSettings.setAlwaysAnimate(false);
        recyclerSettings.setAnimationConstant(Constants.ANIMATION_SLIDE_IN_LEFT);
        recyclerSettings.setPositionForAnimationSpinner(1);
        recyclerSettings.setLayoutManager(linearLayoutManager);
        recyclerSettings.setPositionForLayoutSpinner(0);
        layoutManager = recyclerSettings.getLayoutManager();
    }

    @Override
    protected void onResume() {
        super.onResume();
        populateList();
        blueHairAdapter = new BlueHairAdapter(listSimpleItem, this, recyclerSettings);
        recyclerView.setAdapter(blueHairAdapter);
        recyclerView.setLayoutManager(layoutManager);
        recyclerView.addOnItemTouchListener(new RecyclerItemClickListener(getBaseContext(), recyclerView, this));
        // Melhora a perfomance do RecycleView se o tamanho dos componentes forem fixos e não mudarão
        recyclerView.setHasFixedSize(true);
        setDialog();
    }

    private void populateList() {
        listSimpleItem = new ArrayList<>();
        listSimpleItem.add(new SimpleItem(String.valueOf(1), R.drawable.bh1));
        listSimpleItem.add(new SimpleItem(String.valueOf(2), R.drawable.bh2));
        listSimpleItem.add(new SimpleItem(String.valueOf(3), R.drawable.bh3));
        listSimpleItem.add(new SimpleItem(String.valueOf(4), R.drawable.bh4));
        listSimpleItem.add(new SimpleItem(String.valueOf(5), R.drawable.bh5));
        listSimpleItem.add(new SimpleItem(String.valueOf(6), R.drawable.bh6));
        listSimpleItem.add(new SimpleItem(String.valueOf(7), R.drawable.bh7));
        listSimpleItem.add(new SimpleItem(String.valueOf(8), R.drawable.bh8));
        listSimpleItem.add(new SimpleItem(String.valueOf(9), R.drawable.bh9));
        listSimpleItem.add(new SimpleItem(String.valueOf(10), R.drawable.bh10));
        listSimpleItem.add(new SimpleItem(String.valueOf(11), R.drawable.bh11));
        listSimpleItem.add(new SimpleItem(String.valueOf(12), R.drawable.bh12));
        listSimpleItem.add(new SimpleItem(String.valueOf(13), R.drawable.bh13));
        listSimpleItem.add(new SimpleItem(String.valueOf(14), R.drawable.bh14));
        listSimpleItem.add(new SimpleItem(String.valueOf(15), R.drawable.bh15));
    }

    private void setDialog() {
        AlertDialog.Builder alertDialogBuilder = new AlertDialog.Builder(this);
        alertDialogBuilder.setCancelable(true);
        alertDialogBuilder.setTitle(R.string.recycle_settings);
        alertDialogBuilder.setPositiveButton(R.string.save_changes, onDialogPositiveButtonClick());
        alertDialogBuilder.setNegativeButton(R.string.cancel, null);
        viewDialog = LayoutInflater.from(this).inflate(R.layout.dialog_recycle_settings, null, false);

        String[] animations = {getResources().getString(R.string.none), getResources().getString(R.string.slide_in_left),
                getResources().getString(R.string.slide_out_right), getResources().getString(R.string.fade_in), getResources().getString(R.string.fade_out),
                getResources().getString(R.string.scale)};
        String[] layouts = {getResources().getString(R.string.linear_manager_vertical), getResources().getString(R.string.linear_manager_horizontal),
                getResources().getString(R.string.grid_manager_vertical), getResources().getString(R.string.grid_manager_horizontal),
                getResources().getString(R.string.staggered_manager_vertical), getResources().getString(R.string.staggered_manager_horizontal)};

        spinnerAnimation = (Spinner) viewDialog.findViewById(R.id.spinnerAnimation);
        ArrayAdapter<String> adapterAnimations = new ArrayAdapter<>(this, android.R.layout.simple_dropdown_item_1line, animations);
        spinnerAnimation.setAdapter(adapterAnimations);

        spinnerLayoutManager = (Spinner) viewDialog.findViewById(R.id.spinnerLayoutManager);
        ArrayAdapter<String> adapterLayouts = new ArrayAdapter<>(this, android.R.layout.simple_dropdown_item_1line, layouts);
        spinnerLayoutManager.setAdapter(adapterLayouts);

        radioButtonAlways = (RadioButton) viewDialog.findViewById(R.id.radioButtonAlways);
        radioButtonNotLoaded = (RadioButton) viewDialog.findViewById(R.id.radioButtonOnlyNotLoaded);
        alertDialogBuilder.setView(viewDialog);
        dialogSettings = alertDialogBuilder.create();
    }

    private void loadSettingsOnDialog() {
        spinnerAnimation.setSelection(recyclerSettings.getPositionForAnimationSpinner());
        spinnerLayoutManager.setSelection(recyclerSettings.getPositionForLayoutSpinner());

        if(recyclerSettings.isAlwaysAnimate())
            radioButtonAlways.setChecked(true);
        else
            radioButtonNotLoaded.setChecked(true);
    }

    private DialogInterface.OnClickListener onDialogPositiveButtonClick() {
        return new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                if(which == DialogInterface.BUTTON_POSITIVE)
                    updateSettings();
            }
        };
    }

    private void updateSettings() {
        recyclerSettings.setPositionForAnimationSpinner(spinnerAnimation.getSelectedItemPosition());

        switch (recyclerSettings.getPositionForAnimationSpinner()) {
            case 0:
                recyclerSettings.setAnimationConstant(Constants.NONE);
                break;
            case 1:
                recyclerSettings.setAnimationConstant(Constants.ANIMATION_SLIDE_IN_LEFT);
                break;
            case 2:
                recyclerSettings.setAnimationConstant(Constants.ANIMATION_SLIDE_OUT_RIGHT);
                break;
            case 3:
                recyclerSettings.setAnimationConstant(Constants.ANIMATION_FADE_IN);
                break;
            case 4:
                recyclerSettings.setAnimationConstant(Constants.ANIMATION_FADE_OUT);
                break;
            case 5:
                recyclerSettings.setAnimationConstant(Constants.ANIMATION_SCALE);
                break;
        }

        blueHairAdapter.setLastLoadedViewPosition(0);
        recyclerSettings.setPositionForLayoutSpinner(spinnerLayoutManager.getSelectedItemPosition());

        switch (recyclerSettings.getPositionForLayoutSpinner()) {
            case 0:
                linearLayoutManager.setOrientation(LinearLayoutManager.VERTICAL);
                layoutManager = linearLayoutManager;
                break;
            case 1:
                linearLayoutManager.setOrientation(LinearLayoutManager.HORIZONTAL);
                layoutManager = linearLayoutManager;
                break;
            case 2:
                gridLayoutManager.setOrientation(GridLayoutManager.VERTICAL);
                layoutManager = gridLayoutManager;
                break;
            case 3:
                gridLayoutManager.setOrientation(GridLayoutManager.HORIZONTAL);
                layoutManager = gridLayoutManager;
                break;
            case 4:
                staggeredGridLayoutManager.setOrientation(StaggeredGridLayoutManager.VERTICAL);
                staggeredGridLayoutManager.setGapStrategy(StaggeredGridLayoutManager.GAP_HANDLING_MOVE_ITEMS_BETWEEN_SPANS);
                layoutManager = staggeredGridLayoutManager;
                break;
            case 5:
                staggeredGridLayoutManager.setOrientation(StaggeredGridLayoutManager.HORIZONTAL);
                layoutManager = staggeredGridLayoutManager;
                break;
        }

        recyclerSettings.setLayoutManager(layoutManager);
        recyclerSettings.setAlwaysAnimate(radioButtonAlways.isChecked());
        blueHairAdapter.setRecyclerSettings(recyclerSettings);
        recyclerView.setLayoutManager(recyclerSettings.getLayoutManager());
        Toast.makeText(this, R.string.scroll_to_see, Toast.LENGTH_SHORT).show();
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu_recycle, menu);
        return super.onCreateOptionsMenu(menu);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {

        switch(item.getItemId()) {
            case R.id.menu_recycle_settings:
                loadSettingsOnDialog();
                dialogSettings.show();
                break;
        }

        return super.onOptionsItemSelected(item);
    }

    @Override
    public void onItemClickListener(View view, int position) {
        Toast.makeText(getBaseContext(), "Item " + blueHairAdapter.getItemAtPosition(position).getName() + " single click", Toast.LENGTH_SHORT).show();
    }

    @Override
    public void onLongItemClickListener(View view, int position) {
        Toast.makeText(getBaseContext(), "Item " + blueHairAdapter.getItemAtPosition(position).getName() + " long click", Toast.LENGTH_SHORT).show();
    }

    @Override
    public void onDoubleTapListener(View view, int position) {
        Toast.makeText(getBaseContext(), "Item " + blueHairAdapter.getItemAtPosition(position).getName() + " double click", Toast.LENGTH_SHORT).show();
    }
}
