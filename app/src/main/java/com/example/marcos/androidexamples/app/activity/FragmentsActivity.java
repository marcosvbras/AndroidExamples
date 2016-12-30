package com.example.marcos.androidexamples.app.activity;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import com.example.marcos.androidexamples.R;
import com.example.marcos.androidexamples.app.entity.SimpleItem;
import com.example.marcos.androidexamples.app.fragment.AnimalFragment;
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
    private StarWarsFragment starWarsFragment;
    private FoodFragment foodFragment;
    private AnimalFragment animalFragment;
    private int countStarWarsFrag;
    private int countFoodFrag;
    private Bundle bundle;
    private List<SimpleItem> listStarWars;
    private List<SimpleItem> listFood;
    private SimpleItem simpleItemAnimal;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_fragments);
        loadComponents();
        populateLists();
    }

    private void loadComponents() {
        setSupportActionBar((Toolbar)findViewById(R.id.toolbar));
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        buttonAddSWFrag = (Button) findViewById(R.id.button_add_sw_frag);
        buttonAddSWFrag.setOnClickListener(onStarWarsFragControlClick());
        buttonAddFoodFrag = (Button) findViewById(R.id.button_add_food_frag);
        buttonAddFoodFrag.setOnClickListener(onFoodFragControlClick());
        findViewById(R.id.button_replace_all).setOnClickListener(onReplaceAllButtonClick());
        findViewById(R.id.button_remove_current).setOnClickListener(onRemoveControlsClick());
        findViewById(R.id.button_remove_all).setOnClickListener(onRemoveControlsClick());
        findViewById(R.id.button_remove_all_sw).setOnClickListener(onRemoveControlsClick());
        findViewById(R.id.button_remove_all_food).setOnClickListener(onRemoveControlsClick());
        countStarWarsFrag = 0;
        countFoodFrag = 0;
    }

    private View.OnClickListener onRemoveControlsClick() {
        return new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                fragmentManager = getSupportFragmentManager();
                FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();
                List<Fragment> listFragments = fragmentManager.getFragments();

                if(v.getId() == R.id.button_remove_all) {
                    while (fragmentManager.getBackStackEntryCount() > 0)
                        fragmentManager.popBackStackImmediate(); // Remove o último fragment da pilha
                } else if(v.getId() == R.id.button_remove_all_sw) {
                    for(Fragment fragment : listFragments) {
                        if(fragment.getTag().equals(Constants.TAG_STAR_WARS_FRAG)) {
                            fragmentTransaction.remove(fragment);

                            if(getLastFragment(listFragments).getTag().equals(fragment.getTag()))
                                fragmentManager.popBackStack();
                        }
                    }

                    fragmentTransaction.commit();
                } else if(v.getId() == R.id.button_remove_all_food) {
                    for(Fragment fragment : listFragments) {
                        if(fragment.getTag().equals(Constants.TAG_FOOD_FRAG)) {
                            fragmentTransaction.remove(fragment);

                            if(getLastFragment(listFragments).getTag().equals(fragment.getTag()))
                                fragmentManager.popBackStack();
                        }
                    }

                    fragmentTransaction.commit();
                } else if(v.getId() == R.id.button_remove_current) {
                    fragmentManager.popBackStack();
                }
            }
        };
    }

    private Fragment getLastFragment(List<Fragment> list) {
        return list.get(list.size() - 1);
    }

//    private Fragment getLastFragmentAdded() {
//        int index = getFragmentManager().getBackStackEntryCount() - 1;
//        FragmentManager.BackStackEntry backEntry = getSupportFragmentManager().getBackStackEntryAt(index);
//        String tag = backEntry.getName();
//        return getSupportFragmentManager().findFragmentByTag(tag);
//    }

    private View.OnClickListener onReplaceAllButtonClick() {
        return new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // Criação do fragment
                fragmentManager = getSupportFragmentManager();
                FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();
                animalFragment = new AnimalFragment();
                bundle = new Bundle();
                bundle.putInt(Constants.KEY_COLOR, simpleItemAnimal.getColorResource());
                bundle.putInt(Constants.KEY_RESOURCE, simpleItemAnimal.getImageResource());
                bundle.putString(Constants.KEY_TEXT, getResources().getString(R.string.animal_frag));
                animalFragment.setArguments(bundle);
                // Transação do fragment
                fragmentTransaction.replace(R.id.conteinerFrag, animalFragment, Constants.TAG_ANIMAL_FRAG);
                fragmentTransaction.addToBackStack(Constants.TAG_ANIMAL_FRAG); // Adiciona o fragment à pilha para que ele seja considerado no OnBackPressed
                fragmentTransaction.setTransition(FragmentTransaction.TRANSIT_FRAGMENT_OPEN);
                fragmentTransaction.commit();
            }
        };
    }

    private View.OnClickListener onStarWarsFragControlClick() {
        return new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                fragmentManager = getSupportFragmentManager();
                FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();

                if(v.getId() == R.id.button_add_sw_frag) {
                    if(countStarWarsFrag < listStarWars.size()) {
                        buttonAddSWFrag.setEnabled(true);
                        starWarsFragment = new StarWarsFragment();
                        bundle = new Bundle();
                        bundle.putInt(Constants.KEY_RESOURCE, listStarWars.get(countStarWarsFrag).getImageResource());
                        bundle.putInt(Constants.KEY_COLOR, listStarWars.get(countStarWarsFrag).getColorResource());
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
                        fragmentTransaction.addToBackStack(Constants.TAG_STAR_WARS_FRAG); // Adiciona o fragment à pilha para que ele seja considerado no OnBackPressed
                        fragmentTransaction.setTransition(FragmentTransaction.TRANSIT_FRAGMENT_OPEN);
                        fragmentTransaction.commit();
                        countStarWarsFrag++;
                    } else {
                        buttonAddSWFrag.setEnabled(false);
                    }
                }
            }
        };
    }

    private View.OnClickListener onFoodFragControlClick() {
        return new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                fragmentManager = getSupportFragmentManager();
                FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();

                if(v.getId() == R.id.button_add_food_frag) {
                    if(countFoodFrag < listFood.size()) {
                        buttonAddFoodFrag.setEnabled(true);
                        foodFragment = new FoodFragment();
                        bundle = new Bundle();
                        bundle.putInt(Constants.KEY_RESOURCE, listFood.get(countFoodFrag).getImageResource());
                        bundle.putInt(Constants.KEY_COLOR, listFood.get(countFoodFrag).getColorResource());
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
                        fragmentTransaction.addToBackStack(Constants.TAG_FOOD_FRAG); // Adiciona o fragment à pilha para que ele seja considerado no OnBackPressed
                        fragmentTransaction.setTransition(FragmentTransaction.TRANSIT_FRAGMENT_OPEN);
                        fragmentTransaction.commit();
                        countFoodFrag++;
                    } else {
                        buttonAddFoodFrag.setEnabled(false);
                    }
                }
            }
        };
    }

    private void populateLists() {
        listStarWars = new ArrayList<>();
        listStarWars.add(new SimpleItem(null, R.drawable.white_c3po, R.color.bg_screen5));
        listStarWars.add(new SimpleItem(null, R.drawable.white_chewbacca, R.color.bg_screen5));
        listStarWars.add(new SimpleItem(null, R.drawable.white_death_star, android.R.color.black));
        listStarWars.add(new SimpleItem(null, R.drawable.white_darth_vader, android.R.color.black));
        listStarWars.add(new SimpleItem(null, R.drawable.white_princess_leia, R.color.bg_screen1));
        listStarWars.add(new SimpleItem(null, R.drawable.white_jango_fett, R.color.bg_screen6));
        listStarWars.add(new SimpleItem(null, R.drawable.white_r2d2, R.color.bg_screen3));
        listStarWars.add(new SimpleItem(null, R.drawable.white_stormtrooper, android.R.color.black));

        listFood = new ArrayList<>();
        listFood.add(new SimpleItem(null, R.drawable.white_humburger, R.color.bg_screen7));
        listFood.add(new SimpleItem(null, R.drawable.white_pizza, R.color.bg_screen7));
        listFood.add(new SimpleItem(null, R.drawable.white_banana_split, R.color.bg_screen5));
        listFood.add(new SimpleItem(null, R.drawable.white_bread, R.color.bg_screen5));
        listFood.add(new SimpleItem(null, R.drawable.white_hot_dog, R.color.bg_screen7));
        listFood.add(new SimpleItem(null, R.drawable.white_cake, R.color.bg_screen1));

        simpleItemAnimal = new SimpleItem(null, R.drawable.white_fish, R.color.bg_screen3);
    }

    @Override
    protected void onSaveInstanceState(Bundle outState) {
        super.onSaveInstanceState(outState);
        outState.putInt(Constants.KEY_COUNT_SW, countStarWarsFrag);
        outState.putInt(Constants.KEY_COUNT_FOOD, countFoodFrag);
    }

    @Override
    protected void onRestoreInstanceState(Bundle savedInstanceState) {
        super.onRestoreInstanceState(savedInstanceState);

        if(savedInstanceState != null) {
            countStarWarsFrag = savedInstanceState.getInt(Constants.KEY_COUNT_SW);
            if(countStarWarsFrag >= listStarWars.size())
                buttonAddSWFrag.setEnabled(false);

            countFoodFrag = savedInstanceState.getInt(Constants.KEY_COUNT_FOOD);
            if(countFoodFrag >= listFood.size())
                buttonAddFoodFrag.setEnabled(false);
        }
    }

    private void showMessage(String text) {
        Toast.makeText(this, text, Toast.LENGTH_SHORT).show();
    }
}
