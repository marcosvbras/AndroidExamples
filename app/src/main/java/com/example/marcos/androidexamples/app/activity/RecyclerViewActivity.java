package com.example.marcos.androidexamples.app.activity;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.view.MotionEvent;
import android.view.View;
import android.widget.Toast;

import com.example.marcos.androidexamples.R;
import com.example.marcos.androidexamples.app.adapter.BlueHairAdapter;
import com.example.marcos.androidexamples.app.entity.BlueHair;
import com.example.marcos.androidexamples.app.listener.RecyclerItemClickListener;

import java.util.ArrayList;
import java.util.List;

public class RecyclerViewActivity extends AppCompatActivity {

    private RecyclerView recyclerView;
    private BlueHairAdapter blueHairAdapter;
    private List<BlueHair> listBlueHair;
    private RecyclerView.LayoutManager layoutManager;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_recycler_view);
        loadComponents();
    }

    @Override
    protected void onResume() {
        super.onResume();
        populateList();
        blueHairAdapter = new BlueHairAdapter(listBlueHair, this);
        recyclerView.setAdapter(blueHairAdapter);
        layoutManager = new LinearLayoutManager(this);
        recyclerView.setLayoutManager(layoutManager);
        recyclerView.addOnItemTouchListener(onRecyclerItemClickListener());
        // Melhora a perfomance do RecycleView se o tamanho dos componentes forem fixos e não mudarão
        recyclerView.setHasFixedSize(true);

        // LinearLayoutManager
        // GridLayoutManager
        // StaggeredGridLayoutManager
        // LayoutManager layout = new LinearLayoutManager(this, LinearLayoutManager.VERTICAL, false));
    }

    private RecyclerItemClickListener onRecyclerItemClickListener() {
        return new RecyclerItemClickListener(getBaseContext(), new RecyclerItemClickListener.OnItemClickListener() {
            @Override public void onItemClick(View view, int position) {
                Toast.makeText(getBaseContext(), "Item " + blueHairAdapter.getItemAtPosition(position).getId() + " clicked!", Toast.LENGTH_SHORT).show();
            }
        });
    }

    private void populateList() {
        listBlueHair = new ArrayList<>();
        listBlueHair.add(new BlueHair(1, R.drawable.bh1));
        listBlueHair.add(new BlueHair(2, R.drawable.bh2));
        listBlueHair.add(new BlueHair(3, R.drawable.bh3));
        listBlueHair.add(new BlueHair(4, R.drawable.bh4));
        listBlueHair.add(new BlueHair(5, R.drawable.bh5));
        listBlueHair.add(new BlueHair(6, R.drawable.bh6));
        listBlueHair.add(new BlueHair(7, R.drawable.bh7));
        listBlueHair.add(new BlueHair(8, R.drawable.bh8));
        listBlueHair.add(new BlueHair(9, R.drawable.bh9));
        listBlueHair.add(new BlueHair(10, R.drawable.bh10));
        listBlueHair.add(new BlueHair(11, R.drawable.bh11));
        listBlueHair.add(new BlueHair(12, R.drawable.bh12));
        listBlueHair.add(new BlueHair(13, R.drawable.bh13));
        listBlueHair.add(new BlueHair(14, R.drawable.bh14));
        listBlueHair.add(new BlueHair(15, R.drawable.bh15));
    }

    private void loadComponents() {
        setSupportActionBar((Toolbar)findViewById(R.id.toolbar));
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        recyclerView = (RecyclerView)findViewById(R.id.recyclerView);
    }
}
