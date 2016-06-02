package com.larin92.testtasks.internship.ui.adapters;

import android.content.Context;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;

import com.larin92.testtasks.internship.R;
import com.larin92.testtasks.internship.ui.NavigationFragment;

/**
 * Created by larin92 on 18.04.2016.
 */
public class CardsFragmentPagerAdapter extends FragmentPagerAdapter {

    private Context mContext;
    private final int tabsAmount = 3;

    public CardsFragmentPagerAdapter(Context context, FragmentManager fm) {
        super(fm);
        mContext = context;
    }

    @Override
    public Fragment getItem(int position) {
        return NavigationFragment.newInstance(position);
    }

    @Override
    public int getCount() {
        return tabsAmount;
    }

    @Override
    public CharSequence getPageTitle(int position) {
        switch (position) {
            case 0:
                return mContext.getString(R.string.status0); // in work
            case 1:
                return mContext.getString(R.string.status1); // done
            case 2:
                return mContext.getString(R.string.status2); // waiting
        }
        return super.getPageTitle(position);
    }
}
