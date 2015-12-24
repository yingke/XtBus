package com.xtside.xtbus.xtbus.adapter;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;

import java.util.ArrayList;

/**
 * Created by YK on 2015/12/19.
 */
public class SectionsPagerAdapter extends FragmentPagerAdapter {
    private ArrayList<Fragment> fragments;

    public SectionsPagerAdapter(FragmentManager fm) {
        super(fm);
    }

    public SectionsPagerAdapter(FragmentManager fm,ArrayList<Fragment> fragments) {
        super(fm);
        this.fragments=fragments;
    }

    @Override
    public Fragment getItem(int position) {
        // getItem is called to instantiate the fragment for the given page.
        // Return a PlaceholderFragment (defined as a static inner class below).
        return fragments.get(position);
    }

    @Override
    public int getCount() {
        // Show 3 total pages.
        return 1;
    }

    @Override
    public CharSequence getPageTitle(int position) {
        switch (position) {
            case 0:
                return "查看到站距离";
            /*case 1:
                return "换乘";
            case 2:
                return "线路";
            case 3:
                return  "站点" ;*/
        }
        return null;
    }
}
