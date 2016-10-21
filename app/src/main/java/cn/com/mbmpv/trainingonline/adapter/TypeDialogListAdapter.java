package cn.com.mbmpv.trainingonline.adapter;

import android.content.Context;
import android.graphics.Color;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.List;

import cn.com.mbmpv.trainingonline.R;
import cn.com.mbmpv.trainingonline.bean.TypeBean;

/**
 * Created by jiuzheyange on 2016/8/9.
 */
public class TypeDialogListAdapter extends BaseAdapter {

    List<TypeBean> lists;
    Context mContext;
    List<TextView> list;

    public TypeDialogListAdapter(List<TypeBean> lists, Context mContext) {
        this.lists = lists;
        this.mContext = mContext;
        list=new ArrayList<TextView>();
    }

    @Override
    public int getCount() {
        
            return lists.size();
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
    public View getView(final int i, View view, ViewGroup viewGroup) {

        view = View.inflate(mContext, R.layout.search_listview_item, null);
        TextView text = (TextView) view.findViewById(R.id.title_name);
        text.setText(lists.get(i).getTag_item_name());
        if(i==0)
        {
            text.setBackgroundResource(R.drawable.login_bg);
            text.setTextColor(Color.parseColor("#FFFFFF"));
        }

        list.add(text);
        
        return view;
    }
    
    public void setNormal()
    {
        for(int i=0;i<list.size();i++)
        {
            list.get(i).setBackgroundColor(Color.parseColor("#FFFFFF"));
            list.get(i).setTextColor(Color.parseColor("#333333"));
        }
    }
    
}
