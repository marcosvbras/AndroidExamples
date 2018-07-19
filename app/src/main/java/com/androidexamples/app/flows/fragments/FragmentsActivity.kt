package com.androidexamples.app.flows.fragments

import android.support.v4.app.Fragment
import android.support.v4.app.FragmentManager
import android.support.v4.app.FragmentTransaction
import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.support.v7.widget.Toolbar
import android.view.View
import android.widget.Button
import android.widget.Toast

import com.androidexamples.app.R
import com.androidexamples.app.domain.SimpleItem
import com.androidexamples.app.interfaces.OnDetachFragmentListener
import com.androidexamples.app.utils.Constants

import java.util.ArrayList

class FragmentsActivity : AppCompatActivity() {

    // Views
    private var buttonAddSWFrag: Button? = null
    private var buttonAddFoodFrag: Button? = null

    // Another objects
    private var fragmentManager: FragmentManager? = null
    private var starWarsFragment: StarWarsFragment? = null
    private var foodFragment: FoodFragment? = null
    private var animalFragment: AnimalFragment? = null
    private var countStarWarsFrag: Int = 0
    private var countFoodFrag: Int = 0
    private var bundle: Bundle? = null
    private var listStarWars: MutableList<SimpleItem>? = null
    private var listFood: MutableList<SimpleItem>? = null
    private var simpleItemAnimal: SimpleItem? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_fragments)
        loadComponents()
        populateLists()
    }

    private fun loadComponents() {
        setSupportActionBar(findViewById<View>(R.id.top_toolbar) as Toolbar)
        supportActionBar!!.setDisplayHomeAsUpEnabled(true)
        buttonAddSWFrag = findViewById<View>(R.id.button_add_sw_frag) as Button
        buttonAddSWFrag!!.setOnClickListener(onStarWarsFragControlClick())
        buttonAddFoodFrag = findViewById<View>(R.id.button_add_food_frag) as Button
        buttonAddFoodFrag!!.setOnClickListener(onFoodFragControlClick())
        findViewById<View>(R.id.button_replace_all).setOnClickListener(onReplaceAllButtonClick())
        findViewById<View>(R.id.button_remove_current).setOnClickListener(onRemoveControlsClick())
        findViewById<View>(R.id.button_remove_all).setOnClickListener(onRemoveControlsClick())
        findViewById<View>(R.id.button_remove_all_sw).setOnClickListener(onRemoveControlsClick())
        findViewById<View>(R.id.button_remove_all_food).setOnClickListener(onRemoveControlsClick())
        countStarWarsFrag = 0
        countFoodFrag = 0
    }

    private fun onRemoveControlsClick(): View.OnClickListener {
        return View.OnClickListener { v ->
            fragmentManager = supportFragmentManager
            val fragmentTransaction = fragmentManager!!.beginTransaction()
            val listFragments = fragmentManager!!.fragments

            if (v.id == R.id.button_remove_all) {
                while (fragmentManager!!.backStackEntryCount > 0)
                    fragmentManager!!.popBackStackImmediate() // Remove o último fragment da pilha
            } else if (v.id == R.id.button_remove_all_sw) {
                for (fragment in listFragments) {
                    if (fragment.tag == Constants.TAG_STAR_WARS_FRAG) {
                        fragmentTransaction.remove(fragment)

                        if (getLastFragment(listFragments).tag == fragment.tag)
                            fragmentManager!!.popBackStack()
                    }
                }

                fragmentTransaction.commit()
            } else if (v.id == R.id.button_remove_all_food) {
                for (fragment in listFragments) {
                    if (fragment.tag == Constants.TAG_FOOD_FRAG) {
                        fragmentTransaction.remove(fragment)

                        if (getLastFragment(listFragments).tag == fragment.tag)
                            fragmentManager!!.popBackStack()
                    }
                }

                fragmentTransaction.commit()
            } else if (v.id == R.id.button_remove_current) {
                fragmentManager!!.popBackStack()
            }
        }
    }

    private fun getLastFragment(list: List<Fragment>): Fragment {
        return list[list.size - 1]
    }

    //    private Fragment getLastFragmentAdded() {
    //        int index = getFragmentManager().getBackStackEntryCount() - 1;
    //        FragmentManager.BackStackEntry backEntry = getSupportFragmentManager().getBackStackEntryAt(index);
    //        String tag = backEntry.getName();
    //        return getSupportFragmentManager().findFragmentByTag(tag);
    //    }

    private fun onReplaceAllButtonClick(): View.OnClickListener {
        return View.OnClickListener {
            // Criação do fragment
            fragmentManager = supportFragmentManager
            val fragmentTransaction = fragmentManager!!.beginTransaction()
            animalFragment = AnimalFragment()
            bundle = Bundle()
            bundle!!.putInt(Constants.KEY_COLOR, simpleItemAnimal!!.colorResource!!)
            bundle!!.putInt(Constants.KEY_RESOURCE, simpleItemAnimal!!.imageResource!!)
            bundle!!.putString(Constants.KEY_TEXT, resources.getString(R.string.animal_frag))
            animalFragment!!.arguments = bundle
            // Transação do fragment
            fragmentTransaction.replace(R.id.conteinerFrag, animalFragment, Constants.TAG_ANIMAL_FRAG)
            fragmentTransaction.addToBackStack(Constants.TAG_ANIMAL_FRAG) // Adiciona o fragment à pilha para que ele seja considerado no OnBackPressed
            fragmentTransaction.setTransition(FragmentTransaction.TRANSIT_FRAGMENT_OPEN)
            fragmentTransaction.commit()
        }
    }

    private fun onStarWarsFragControlClick(): View.OnClickListener {
        return View.OnClickListener { v ->
            fragmentManager = supportFragmentManager
            val fragmentTransaction = fragmentManager!!.beginTransaction()

            if (v.id == R.id.button_add_sw_frag) {
                if (countStarWarsFrag < listStarWars!!.size) {
                    buttonAddSWFrag!!.isEnabled = true
                    starWarsFragment = StarWarsFragment()
                    bundle = Bundle()
                    bundle!!.putInt(Constants.KEY_RESOURCE, listStarWars!![countStarWarsFrag].imageResource!!)
                    bundle!!.putInt(Constants.KEY_COLOR, listStarWars!![countStarWarsFrag].colorResource!!)
                    bundle!!.putString(Constants.KEY_TEXT, resources.getString(R.string.star_wars_frag) + " (x" + (countStarWarsFrag + 1) + ")")
                    starWarsFragment!!.arguments = bundle
                    starWarsFragment!!.setOnDetachFragmentListener(object : OnDetachFragmentListener {
                        override fun onDetachFragment(tag: String) {
                            countStarWarsFrag--
                            buttonAddSWFrag!!.isEnabled = true
                        }
                    })
                    fragmentTransaction.add(R.id.conteinerFrag, starWarsFragment, Constants.TAG_STAR_WARS_FRAG)
                    fragmentTransaction.addToBackStack(Constants.TAG_STAR_WARS_FRAG) // Adiciona o fragment à pilha para que ele seja considerado no OnBackPressed
                    fragmentTransaction.setTransition(FragmentTransaction.TRANSIT_FRAGMENT_OPEN)
                    fragmentTransaction.commit()
                    countStarWarsFrag++
                } else {
                    buttonAddSWFrag!!.isEnabled = false
                }
            }
        }
    }

    private fun onFoodFragControlClick(): View.OnClickListener {
        return View.OnClickListener { v ->
            fragmentManager = supportFragmentManager
            val fragmentTransaction = fragmentManager!!.beginTransaction()

            if (v.id == R.id.button_add_food_frag) {
                if (countFoodFrag < listFood!!.size) {
                    buttonAddFoodFrag!!.isEnabled = true
                    foodFragment = FoodFragment()
                    bundle = Bundle()
                    bundle!!.putInt(Constants.KEY_RESOURCE, listFood!![countFoodFrag].imageResource!!)
                    bundle!!.putInt(Constants.KEY_COLOR, listFood!![countFoodFrag].colorResource!!)
                    bundle!!.putString(Constants.KEY_TEXT, resources.getString(R.string.food_frag) + " (x" + (countFoodFrag + 1) + ")")
                    foodFragment!!.arguments = bundle
                    foodFragment!!.setOnDetachFragmentListener(object : OnDetachFragmentListener {
                        override fun onDetachFragment(tag: String) {
                            countFoodFrag--
                            buttonAddFoodFrag!!.isEnabled = true
                        }
                    })
                    fragmentTransaction.add(R.id.conteinerFrag, foodFragment, Constants.TAG_FOOD_FRAG)
                    fragmentTransaction.addToBackStack(Constants.TAG_FOOD_FRAG) // Adiciona o fragment à pilha para que ele seja considerado no OnBackPressed
                    fragmentTransaction.setTransition(FragmentTransaction.TRANSIT_FRAGMENT_OPEN)
                    fragmentTransaction.commit()
                    countFoodFrag++
                } else {
                    buttonAddFoodFrag!!.isEnabled = false
                }
            }
        }
    }

    private fun populateLists() {
        listStarWars = ArrayList()
        listStarWars!!.add(SimpleItem(null, R.drawable.white_c3po, R.color.bg_screen5))
        listStarWars!!.add(SimpleItem(null, R.drawable.white_chewbacca, R.color.bg_screen5))
        listStarWars!!.add(SimpleItem(null, R.drawable.white_death_star, android.R.color.black))
        listStarWars!!.add(SimpleItem(null, R.drawable.white_darth_vader, android.R.color.black))
        listStarWars!!.add(SimpleItem(null, R.drawable.white_princess_leia, R.color.bg_screen1))
        listStarWars!!.add(SimpleItem(null, R.drawable.white_jango_fett, R.color.bg_screen6))
        listStarWars!!.add(SimpleItem(null, R.drawable.white_r2d2, R.color.bg_screen3))
        listStarWars!!.add(SimpleItem(null, R.drawable.white_stormtrooper, android.R.color.black))

        listFood = ArrayList()
        listFood!!.add(SimpleItem(null, R.drawable.white_humburger, R.color.bg_screen7))
        listFood!!.add(SimpleItem(null, R.drawable.white_pizza, R.color.bg_screen7))
        listFood!!.add(SimpleItem(null, R.drawable.white_banana_split, R.color.bg_screen5))
        listFood!!.add(SimpleItem(null, R.drawable.white_bread, R.color.bg_screen5))
        listFood!!.add(SimpleItem(null, R.drawable.white_hot_dog, R.color.bg_screen7))
        listFood!!.add(SimpleItem(null, R.drawable.white_cake, R.color.bg_screen1))

        simpleItemAnimal = SimpleItem(null, R.drawable.white_fish, R.color.bg_screen3)
    }

    override fun onSaveInstanceState(outState: Bundle) {
        super.onSaveInstanceState(outState)
        outState.putInt(Constants.KEY_COUNT_SW, countStarWarsFrag)
        outState.putInt(Constants.KEY_COUNT_FOOD, countFoodFrag)
    }

    override fun onRestoreInstanceState(savedInstanceState: Bundle?) {
        super.onRestoreInstanceState(savedInstanceState)

        if (savedInstanceState != null) {
            countStarWarsFrag = savedInstanceState.getInt(Constants.KEY_COUNT_SW)
            if (countStarWarsFrag >= listStarWars!!.size)
                buttonAddSWFrag!!.isEnabled = false

            countFoodFrag = savedInstanceState.getInt(Constants.KEY_COUNT_FOOD)
            if (countFoodFrag >= listFood!!.size)
                buttonAddFoodFrag!!.isEnabled = false
        }
    }

    private fun showMessage(text: String) {
        Toast.makeText(this, text, Toast.LENGTH_SHORT).show()
    }
}
