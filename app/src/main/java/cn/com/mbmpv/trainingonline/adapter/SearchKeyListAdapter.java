package cn.com.mbmpv.trainingonline.adapter;

import android.content.Context;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;

import java.util.List;

import cn.com.mbmpv.trainingonline.R;

/**
 * Created by jiuzheyange on 2016/8/9.
 */
public class SearchKeyListAdapter extends BaseAdapter {

    List<String> lists;
    Context mContext;

    public SearchKeyListAdapter(List<String> lists, Context mContext) {
        this.lists = lists;
        this.mContext = mContext;
    }

    @Override
    public int getCount() {

        if (lists.size() < 6) {
            return lists.size();
        } else {
            return 6;
        }

    }

    @Override
    public Object getItem(int i) {
        return lists.get(i);
    }

    @Override
    public long getItemId(int i) {
        return i;
    }

    @Override
    public View getView(int i, View view, ViewGroup viewGroup) {

        view = View.inflate(mContext, R.layout.search_listview_item, null);
        return view;
    }
}
