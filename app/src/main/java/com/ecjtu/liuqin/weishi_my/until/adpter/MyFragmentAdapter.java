package com.ecjtu.liuqin.weishi_my.until.adpter;

import android.content.Context;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;

import java.util.List;

/**
 * Created by liuqin on 2016/5/25.
 */
public class MyFragmentAdapter extends FragmentPagerAdapter {
    private Context mContext;
    private String[] mTabTitle;
    private List<Fragment> mFragmentsList;

    public MyFragmentAdapter(FragmentManager fm, List<Fragment> fragments
    , Context context, String[] tabTitle) {
        super(fm);
        this.mContext=context;
        this.mFragmentsList=fragments;
        this.mTabTitle=tabTitle;
    }

    @Override
    public Fragment getItem(int position) {
        return mFragmentsList.get(position);
    }

    @Override
    public int getCount() {
        return mFragmentsList.size();
    }

    @Override
    public CharSequence getPageTitle(int position) {
        return mTabTitle[position];
    }
}
