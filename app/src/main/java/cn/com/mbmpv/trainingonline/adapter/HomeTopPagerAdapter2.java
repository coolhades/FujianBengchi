package cn.com.mbmpv.trainingonline.adapter;


import android.content.Context;
import android.support.v4.view.PagerAdapter;
import android.view.View;
import android.view.ViewGroup;

import cn.com.mbmpv.trainingonline.utils.ConstantSet;

import java.util.List;

/**
 * Created by jiuzheyange on 2016/8/8.
 */
public class HomeTopPagerAdapter2 extends PagerAdapter {

    List<View> lists;
    Context mContext;

    public HomeTopPagerAdapter2(List<View> lists, Context mContext) {
        this.lists = lists;
        this.mContext = mContext;
    }

    @Override
    public int getCount() {
        if (ConstantSet.confiMap != null) {
            if (ConstantSet.confiMap.get("App.Switch.Teacher.Show").equals("0")) {
                return 1;
            }
            else {
                return lists.size();
            }
        } else {
            return lists.size();
        }
    }

    @Override
    public boolean isViewFromObject(View view, Object object) {
        return view == object;
    }

    @Override
    public Object instantiateItem(ViewGroup container, int position) {
        container.addView(lists.get(position));

        return lists.get(position);
    }

    @Override
    public void destroyItem(ViewGroup container, int position, Object object) {
        //super.destroyItem(container, position, object);
        container.removeView(lists.get(position));
    }
}
