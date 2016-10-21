package cn.com.mbmpv.trainingonline.adapter;

import android.content.Context;
import android.support.v4.view.PagerAdapter;
import android.view.View;
import android.view.ViewGroup;

import java.util.List;

/**
 * Created by jiuzheyange on 2016/8/16.
 */
public class MaskDialogViewPagerAdapter extends PagerAdapter {

    Context mContext;
    List<View> lists;

    public MaskDialogViewPagerAdapter(Context mContext, List<View> lists) {
        this.mContext = mContext;
        this.lists = lists;
    }

    @Override
    public int getCount() {
        return lists.size();
    }

    @Override
    public boolean isViewFromObject(View view, Object object) {
        return view==object;
    }

    @Override
    public Object instantiateItem(ViewGroup container, int position) {
        container.addView(lists.get(position));
        return lists.get(position);
    }

    @Override
    public void destroyItem(ViewGroup container, int position, Object object) {
       // super.destroyItem(container, position, object);
        container.removeView(lists.get(position));
        
        
    }
}
