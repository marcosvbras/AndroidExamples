package com.example.marcos.androidexamples.app.activity;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.util.Log;
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
    private FragmentTransaction fragmentTransaction;
    private StarWarsFragment starWarsFragment;
    private FoodFragment foodFragment;
    private AnimalFragment animalFragment;
    private int countStarWarsFrag;
    private int countFoodFrag;
    private int countAnimalsFrag;
    private Bundle bundle;
    private List<SimpleItem> listStarWars;
    private List<SimpleItem> listFood;
    private SimpleItem simpleItemAnimal;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_fragments);
        loadComponents();
    }

    private void loadComponents() {
        setSupportActionBar((Toolbar)findViewById(R.id.toolbar));
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        buttonAddSWFrag = (Button) findViewById(R.id.button_add_sw_frag);
        buttonAddSWFrag.setOnClickListener(onStarWarsFragControlClick());
        findViewById(R.id.button_remove_sw_frag).setOnClickListener(onStarWarsFragControlClick());
        buttonAddFoodFrag = (Button) findViewById(R.id.button_add_food_frag);
        buttonAddFoodFrag.setOnClickListener(onFoodFragControlClick());
        findViewById(R.id.button_remove_food_frag).setOnClickListener(onFoodFragControlClick());
        findViewById(R.id.button_replace_all).setOnClickListener(onReplaceAllButtonClick());
        findViewById(R.id.button_remove_animal).setOnClickListener(onRemoveAnimalFragButtonClick());
        findViewById(R.id.button_remove_all).setOnClickListener(onRemoveAllButtonClick());
        findViewById(R.id.button_remove_all_sw).setOnClickListener(onRemoveAllButtonClick());
        findViewById(R.id.button_remove_all_food).setOnClickListener(onRemoveAllButtonClick());
        countStarWarsFrag = 0;
        countFoodFrag = 0;
        countAnimalsFrag = 0;
    }

    private View.OnClickListener onRemoveAllButtonClick() {
        return new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                fragmentManager = getSupportFragmentManager();
                fragmentTransaction = fragmentManager.beginTransaction();
                List<Fragment> listFragments = fragmentManager.getFragments();

                if(v.getId() == R.id.button_remove_all) {
                    while (fragmentManager.getBackStackEntryCount() > 0)
                        fragmentManager.popBackStackImmediate(); // Remove o último fragment da pilha
                } else if(v.getId() == R.id.button_remove_all_sw) {
                    for(Fragment fragment : listFragments) {
                        if(fragment.getTag().equals(Constants.TAG_STAR_WARS_FRAG)) {
                            fragmentTransaction.remove(fragment);

//                            if(getLastFragment(listFragments).getTag().equals(fragment.getTag()))
//                                fragmentManager.popBackStack();
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

    private View.OnClickListener onRemoveAnimalFragButtonClick() {
        return new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                fragmentManager = getSupportFragmentManager();
                animalFragment = (AnimalFragment)fragmentManager.findFragmentByTag(Constants.TAG_ANIMAL_FRAG);

                if(animalFragment != null) {
                    fragmentTransaction.remove(animalFragment);
                    fragmentTransaction.setTransition(FragmentTransaction.TRANSIT_FRAGMENT_CLOSE);
                    fragmentTransaction.commit();
                    showMessage("'" + Constants.TAG_ANIMAL_FRAG + "' " + getResources().getString(R.string.frag_removed));
                } else {
                    showMessage("'" + Constants.TAG_ANIMAL_FRAG + "' " + getResources().getString(R.string.frag_not_added));
                }
            }
        };
    }

    private View.OnClickListener onReplaceAllButtonClick() {
        return new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // Criação do fragment
                fragmentManager = getSupportFragmentManager();
                fragmentTransaction = fragmentManager.beginTransaction();
                animalFragment = new AnimalFragment();
                bundle = new Bundle();
                bundle.putInt(Constants.KEY_COLOR, simpleItemAnimal.getColorResource());
                bundle.putInt(Constants.KEY_RESOURCE, simpleItemAnimal.getImageResource());
                bundle.putString(Constants.KEY_TEXT, getResources().getString(R.string.animal_frag));
                animalFragment.setArguments(bundle);
                // Transação do fragment
                fragmentTransaction.replace(R.id.conteinerFrag, animalFragment, Constants.TAG_ANIMAL_FRAG);
                fragmentTransaction.addToBackStack(null);
                fragmentTransaction.setTransition(FragmentTransaction.TRANSIT_FRAGMENT_OPEN);
                fragmentTransaction.commit();
            }
        };
    }

    @Override
    protected void onResume() {
        super.onResume();
        populateLists();
    }

    private View.OnClickListener onStarWarsFragControlClick() {
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
                        fragmentTransaction.addToBackStack(null);
                        fragmentTransaction.setTransition(FragmentTransaction.TRANSIT_FRAGMENT_OPEN);
                        fragmentTransaction.commit();
                        countStarWarsFrag++;
                    } else {
                        buttonAddSWFrag.setEnabled(false);
                    }
                } else if(v.getId() == R.id.button_remove_sw_frag) {
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

    private View.OnClickListener onFoodFragControlClick() {
        return new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                fragmentManager = getSupportFragmentManager();
                fragmentTransaction = fragmentManager.beginTransaction();

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
                        fragmentTransaction.addToBackStack(null);
                        fragmentTransaction.setTransition(FragmentTransaction.TRANSIT_FRAGMENT_OPEN);
                        fragmentTransaction.commit();
                        countFoodFrag++;
                    } else {
                        buttonAddFoodFrag.setEnabled(false);
                    }
                } else if(v.getId() == R.id.button_remove_food_frag) {
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

    private void showMessage(String text) {
        Toast.makeText(this, text, Toast.LENGTH_SHORT).show();
    }
}
