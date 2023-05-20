package com.example.tourtz;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentPagerAdapter;

public class PagerAdapter extends FragmentPagerAdapter {

    private int tabsNumber;

    public PagerAdapter(@NonNull FragmentManager fm, int behavior, int tabs) {
        super(fm, behavior);
        this.tabsNumber = tabs;
    }

    @NonNull
    @Override
    public Fragment getItem(int position) {
        switch (position){
            case 0:
                return  new BeachesFragment();

            case 1:
                return  new LandscapesFragment();
            case 2:
                return  new MuseumsFragment();

            case 3:
                return  new ParkFragment();

            case 4:
                return  new ReservesFragment();

            case 5:
                return  new SitesFragment();
            default: return null;
        }

    }

    @Override
    public int getCount() {
        return tabsNumber;
    }
}
