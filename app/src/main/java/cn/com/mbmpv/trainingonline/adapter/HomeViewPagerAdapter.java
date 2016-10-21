package cn.com.mbmpv.trainingonline.adapter;


import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentStatePagerAdapter;

import java.util.List;

/**
 * Created by jiuzheyange on 2016/8/8.
 */
public class HomeViewPagerAdapter extends FragmentStatePagerAdapter {
    
    List<Fragment> lists;

    public HomeViewPagerAdapter(FragmentManager fm, List<Fragment> lists) {
        super(fm);
        this.lists = lists;
    }

    @Override
    public Fragment getItem(int position) {
        return lists.get(position);
    }

    @Override
    public int getCount() {
        return lists.size();
    }
}
