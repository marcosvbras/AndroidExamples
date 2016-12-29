package com.example.marcos.androidexamples.app.activity;

import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import com.example.marcos.androidexamples.R;
import com.example.marcos.androidexamples.app.fragment.StarWarsFragment;
import com.example.marcos.androidexamples.app.fragment.FoodFragment;
import com.example.marcos.androidexamples.app.interfaces.OnDetachFragmentListener;
import com.example.marcos.androidexamples.app.util.Constants;

import java.util.ArrayList;
import java.util.List;

public class FragmentsActivity extends AppCompatActivity {

    // Views
    private Button buttonAddSWFrag;
    private Button buttonAddFoodFrag;

    // Another objects
    private FragmentManager fragmentManager;
    private FragmentTransaction fragmentTransaction;
    private StarWarsFragment starWarsFragment;
    private FoodFragment foodFragment;
    private int countStarWarsFrag;
    private int countFoodFrag;
    private int countAnimalsFrag;
    private Bundle bundle;
    private List<Integer> listStarWars;
    private List<Integer> listFood;
    private List<Integer> listAnimals;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_fragments);
        loadComponents();
        fragmentManager = getSupportFragmentManager();
    }

    private void loadComponents() {
        setSupportActionBar((Toolbar)findViewById(R.id.toolbar));
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        buttonAddSWFrag = (Button) findViewById(R.id.button_add_sw_frag);
        buttonAddSWFrag.setOnClickListener(onFrag1ControlClick());
        findViewById(R.id.button_remove_frag1).setOnClickListener(onFrag1ControlClick());
        buttonAddFoodFrag = (Button) findViewById(R.id.button_add_frag2);
        buttonAddFoodFrag.setOnClickListener(onFrag2ControlClick());
        findViewById(R.id.button_remove_frag2).setOnClickListener(onFrag2ControlClick());
        countStarWarsFrag = 0;
        countFoodFrag = 0;
        countAnimalsFrag = 0;
    }

    @Override
    protected void onResume() {
        super.onResume();
        populateLists();
    }

    private void populateLists() {
        listStarWars = new ArrayList<>();
        listStarWars.add(R.drawable.white_c3po);
        listStarWars.add(R.drawable.white_chewbacca);
        listStarWars.add(R.drawable.white_death_star);
        listStarWars.add(R.drawable.white_darth_vader);
        listStarWars.add(R.drawable.white_princess_leia);
        listStarWars.add(R.drawable.white_jango_fett);
        listStarWars.add(R.drawable.white_r2d2);
        listStarWars.add(R.drawable.white_stormtrooper);

        listFood = new ArrayList<>();
        listFood.add(R.drawable.white_humburger);
        listFood.add(R.drawable.white_pizza);
        listFood.add(R.drawable.white_banana_split);
        listFood.add(R.drawable.white_bread);
        listFood.add(R.drawable.white_hot_dog);
        listFood.add(R.drawable.white_cake);

        listAnimals = new ArrayList<>();
        listAnimals.add(R.drawable.white_fish);
        listAnimals.add(R.drawable.white_rabbit);
        listAnimals.add(R.drawable.white_duck);
        listAnimals.add(R.drawable.white_pig);
    }

    private View.OnClickListener onFrag1ControlClick() {
        return new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                fragmentManager = getSupportFragmentManager();
                fragmentTransaction = fragmentManager.beginTransaction();

                if(v.getId() == R.id.button_add_sw_frag) {
                    if(countStarWarsFrag < listStarWars.size()) {
                        buttonAddSWFrag.setEnabled(true);
                        starWarsFragment = new StarWarsFragment();
                        bundle = new Bundle();
                        bundle.putInt(Constants.KEY_RESOURCE, listStarWars.get(countStarWarsFrag));
                        bundle.putString(Constants.KEY_TEXT, getResources().getString(R.string.star_wars_frag) + " (x" + (countStarWarsFrag + 1) + ")");
                        starWarsFragment.setArguments(bundle);
                        starWarsFragment.setOnDetachFragmentListener(new OnDetachFragmentListener() {
                            @Override
                            public void onDetachFragment(String tag) {
                                countStarWarsFrag--;
                                buttonAddSWFrag.setEnabled(true);
                            }
                        });
                        fragmentTransaction.add(R.id.conteinerFrag, starWarsFragment, Constants.TAG_STAR_WARS_FRAG);
                        fragmentTransaction.addToBackStack(null);
                        fragmentTransaction.setTransition(FragmentTransaction.TRANSIT_FRAGMENT_OPEN);
                        fragmentTransaction.commit();
                        countStarWarsFrag++;
                    } else {
                        buttonAddSWFrag.setEnabled(false);
                    }
                } else if(v.getId() == R.id.button_remove_frag1) {
                    starWarsFragment = (StarWarsFragment)fragmentManager.findFragmentByTag(Constants.TAG_STAR_WARS_FRAG);

                    if(starWarsFragment != null) {
                        fragmentTransaction.remove(starWarsFragment);
                        fragmentTransaction.setTransition(FragmentTransaction.TRANSIT_FRAGMENT_CLOSE);
                        fragmentTransaction.commit();
                        fragmentManager.popBackStack();
                        showMessage("'" + Constants.TAG_STAR_WARS_FRAG + "' " + getResources().getString(R.string.frag_removed));
                    } else {
                        showMessage("'" + Constants.TAG_STAR_WARS_FRAG + "' " + getResources().getString(R.string.frag_not_added));
                    }
                }
            }
        };
    }

    private View.OnClickListener onFrag2ControlClick() {
        return new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                fragmentTransaction = fragmentManager.beginTransaction();

                if(v.getId() == R.id.button_add_frag2) {
                    if(countFoodFrag < listFood.size()) {
                        buttonAddFoodFrag.setEnabled(true);
                        foodFragment = new FoodFragment();
                        bundle = new Bundle();
                        bundle.putInt(Constants.KEY_RESOURCE, listFood.get(countFoodFrag));
                        bundle.putString(Constants.KEY_TEXT, getResources().getString(R.string.food_frag) + " (x" + (countFoodFrag + 1) + ")");
                        foodFragment.setArguments(bundle);
                        foodFragment.setOnDetachFragmentListener(new OnDetachFragmentListener() {
                            @Override
                            public void onDetachFragment(String tag) {
                                countFoodFrag--;
                                buttonAddFoodFrag.setEnabled(true);
                            }
                        });
                        fragmentTransaction.add(R.id.conteinerFrag, foodFragment, Constants.TAG_FOOD_FRAG);
                        fragmentTransaction.addToBackStack(null);
                        fragmentTransaction.setTransition(FragmentTransaction.TRANSIT_FRAGMENT_OPEN);
                        fragmentTransaction.commit();
                        countFoodFrag++;
                    } else {
                        buttonAddFoodFrag.setEnabled(false);
                    }
                } else if(v.getId() == R.id.button_remove_frag2) {
                    foodFragment = (FoodFragment) fragmentManager.findFragmentByTag(Constants.TAG_FOOD_FRAG);

                    if(foodFragment != null) {
                        fragmentTransaction.remove(foodFragment);
                        fragmentTransaction.commit();
                        fragmentTransaction.setTransition(FragmentTransaction.TRANSIT_FRAGMENT_CLOSE);
                        fragmentManager.popBackStack();
                        showMessage("'" + Constants.TAG_FOOD_FRAG + "' " + getResources().getString(R.string.frag_removed));
                    } else {
                        showMessage("'" + Constants.TAG_FOOD_FRAG + "' " + getResources().getString(R.string.frag_not_added));
                    }
                }
            }
        };
    }

    private void showMessage(String text) {
        Toast.makeText(this, text, Toast.LENGTH_SHORT).show();
    }
}
