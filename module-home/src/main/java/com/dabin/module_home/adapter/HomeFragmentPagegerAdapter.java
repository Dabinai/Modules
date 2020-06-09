package com.dabin.module_home.adapter;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentPagerAdapter;

import java.util.ArrayList;
import java.util.List;

public class HomeFragmentPagegerAdapter extends FragmentPagerAdapter {

    private String[] table = {"发现", "推荐", "日报"};
    private List<Fragment> fragments;

    public HomeFragmentPagegerAdapter(@NonNull FragmentManager fm, int behavior) {
        super(fm, behavior);
    }

    public void setData(List<Fragment> fragment) {
        if (fragments == null) {
            fragments = new ArrayList<>();
        }
        fragments.addAll(fragment);
        notifyDataSetChanged();
    }

    @NonNull
    @Override
    public Fragment getItem(int position) {
        if (fragments != null && fragments.size() > 0) {
            fragments.get(position);
        }
        return null;
    }

    @Override
    public int getCount() {
        if (fragments != null && fragments.size() >0){
            return fragments.size();
        }
        return 0;
    }
}
