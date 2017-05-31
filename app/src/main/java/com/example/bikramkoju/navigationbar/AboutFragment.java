package com.example.bikramkoju.navigationbar;

import android.content.Context;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentActivity;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentStatePagerAdapter;
import android.support.v4.view.PagerAdapter;
import android.support.v4.view.PagerTabStrip;
import android.support.v4.view.ViewPager;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.astuetz.PagerSlidingTabStrip;

/**
 * Created by Bikramkoju on 3/28/2017.
 */

public class AboutFragment extends Fragment {
    PagerSlidingTabStrip pagertab;
    ViewPager viewpage;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View v=inflater.inflate(R.layout.aboutfragment_layout,container,false);

        return v;
    }

    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        pagertab=(PagerSlidingTabStrip)view.findViewById(R.id.pager_tabs);
        viewpage=(ViewPager)view.findViewById(R.id.viewpager);

        FragmentManager fragmentManager=getChildFragmentManager();
        viewpage.setAdapter(new MyAdapter(getActivity(),fragmentManager));
        pagertab.setViewPager(viewpage);
    }

    private class MyAdapter extends FragmentStatePagerAdapter {
        Context c;
        public MyAdapter(FragmentActivity activity, FragmentManager fragmentManager) {
            super(fragmentManager);
            c=activity;
        }

        @Override
        public Fragment getItem(int position) {
            switch (position){
                case 0:
                    return new SongsFragment();
                case 1:
                    return new PicturesFragment();
                case 2:
                    return new VideoFragment();
            }
            return null;
        }

        @Override
        public int getCount() {
            return 3;
        }

        @Override
        public CharSequence getPageTitle(int position) {
            switch (position){
                case 0:
                    return "Songs";
                case 1:
                    return "Photos";
                case 2:
                    return "Videos";
            }
            return super.getPageTitle(position);
        }
    }
}
