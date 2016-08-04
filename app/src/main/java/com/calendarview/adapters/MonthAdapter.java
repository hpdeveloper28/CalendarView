package com.calendarview.adapters;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;

import com.calendarview.fragments.CalendarFragment;
import com.calendarview.fragments.DateFragment;

import java.util.ArrayList;

/**
 * MonthAdapter holds 4 fragments, which provides fragment for current
 * month, previous month and next month. The extra fragment helps for recycle
 * fragments.
 *
 * @author thomasdao
 */
public class MonthAdapter extends FragmentPagerAdapter {

    private ArrayList<DateFragment> fragments;

    // Lazily create the fragments
    public ArrayList<DateFragment> getFragments() {
        if (fragments == null) {
            fragments = new ArrayList<DateFragment>();
            for (int i = 0; i < getCount(); i++) {
                fragments.add(new DateFragment());
            }
        }
        return fragments;
    }

    public void setFragments(ArrayList<DateFragment> fragments) {
        this.fragments = fragments;
    }

    public MonthAdapter(FragmentManager fm) {
        super(fm);
    }

    @Override
    public Fragment getItem(int position) {
        DateFragment fragment = getFragments().get(position);
        return fragment;
    }

    @Override
    public int getCount() {
        // We need 4 gridviews for previous month, current month and next month,
        // and 1 extra fragment for fragment recycle
        return CalendarFragment.NUMBER_OF_PAGES;
    }

}
