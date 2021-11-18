package com.openclassrooms.entrevoisins.ui.neighbour_list.adapters;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;

import com.openclassrooms.entrevoisins.di.DI;
import com.openclassrooms.entrevoisins.service.NeighbourApiService;
import com.openclassrooms.entrevoisins.ui.neighbour_list.fragment.NeighbourFragment;


public class ListNeighbourPagerAdapter extends FragmentPagerAdapter {

    //Constructor
    public ListNeighbourPagerAdapter(FragmentManager fm) {
        super(fm);
    }

    /**
     * getItem is called to instantiate the fragment for the given page.
     * @param position
     * @return
     */
    @Override
    public Fragment getItem(int position) {
        switch (position){
            case 0:
                return NeighbourFragment.newInstance(false);
            case 1:
                return NeighbourFragment.newInstance(true);
            default:
                return null;
        }
    }

    /**
     * get the number of pages
     * @return
     */
    @Override
    public int getCount() {
        return 2;
    }
}