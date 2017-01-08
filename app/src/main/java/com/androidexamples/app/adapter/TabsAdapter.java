package com.androidexamples.app.adapter;

import android.content.Context;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;

import java.util.List;

/**
 * Created by marcos on 08/01/2017.
 */

public class TabsAdapter extends FragmentPagerAdapter {

    private Context context;
    private List<Fragment> listFragments;
    private List<String> listTitle;

    public TabsAdapter(FragmentManager fragmentManager, Context context, List<Fragment> listFragments, List<String> listTitle) {
        super(fragmentManager);
        this.context = context;
        this.listFragments = listFragments;
        this.listTitle = listTitle;
    }

    @Override
    public Fragment getItem(int position) {
        return listFragments.get(position);
    }

    @Override
    public CharSequence getPageTitle(int position) {
        return listTitle.get(position);
    }

    @Override
    public int getCount() {
        return listFragments.size();
    }
}
