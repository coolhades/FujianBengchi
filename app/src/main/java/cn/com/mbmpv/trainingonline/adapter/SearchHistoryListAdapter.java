package cn.com.mbmpv.trainingonline.adapter;

import android.content.Context;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import java.util.List;

import cn.com.mbmpv.trainingonline.R;

/**
 * Created by jiuzheyange on 2016/8/9.
 */
public class SearchHistoryListAdapter extends BaseAdapter{
    
    List<String> lists;
    Context mContext;

    public SearchHistoryListAdapter(List<String> lists, Context mContext) {
        this.lists = lists;
        this.mContext = mContext;
    }

    @Override
    public int getCount() {
        if(lists==null)
        {
            return 0;
        }
        else
        {
        if(lists.size()<3)
        {
            return lists.size();
        }
        return 3;}
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
        
        view=View.inflate(mContext, R.layout.search_listview_item_2,null);
        TextView titleText= (TextView) view.findViewById(R.id.title_text);

        titleText.setText(lists.get(i));
        
        return view;
    }
}
