package com.androidexamples.app.adapter

import android.content.Context
import android.support.v4.app.Fragment
import android.support.v4.app.FragmentManager
import android.support.v4.app.FragmentPagerAdapter

/**
 * Created by marcos on 08/01/2017.
 */

class TabsAdapter(fragmentManager: FragmentManager, private val context: Context, private val listFragments: List<Fragment>, private val listTitle: List<String>) : FragmentPagerAdapter(fragmentManager) {

    override fun getItem(position: Int): Fragment {
        return listFragments[position]
    }

    override fun getPageTitle(position: Int): CharSequence? {
        return listTitle[position]
    }

    override fun getCount(): Int {
        return listFragments.size
    }
}
