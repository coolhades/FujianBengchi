package cn.com.mbmpv.trainingonline.view;

import android.content.Context;
import android.view.View;
import android.widget.ExpandableListView;

import java.util.ArrayList;
import java.util.List;

import cn.com.mbmpv.trainingonline.R;
import cn.com.mbmpv.trainingonline.adapter.FirstVideoView1ListViewAdapter;
import cn.com.mbmpv.trainingonline.bean.JieBean;
import cn.com.mbmpv.trainingonline.bean.ZhangBean;

import static android.view.View.inflate;

/**
 * Created by jiuzheyange on 2016/8/10.
 */
public class VideoViewFirst1 {
    
    Context mContext;
    View mView;

    List<ZhangBean> parentLists;
    List<List<JieBean>>  childLists;
    FirstVideoView1ListViewAdapter adapter;

    ExpandableListView listView;
    
    List<ZhangBean> zhangBeanList;
    
    

    public VideoViewFirst1(Context mContext,List<ZhangBean> zhangBean) {
        this.mContext = mContext;
        this.zhangBeanList=zhangBean;
    }
    
    public View getView()
    {
        mView= inflate(mContext, R.layout.video_view1,null);
        initView();
        initData();
        initEvent();
        
        return mView;
        
    }

    private void initView() {
        
        listView= (ExpandableListView) mView.findViewById(R.id.view1_listview);
        listView.setGroupIndicator(null);
    }

    private void initData() {

        parentLists=new ArrayList<ZhangBean>();
        childLists=new  ArrayList<List<JieBean>>();
       
        parentLists=zhangBeanList;

        for(int i=0;i<parentLists.size();i++)
        {
            childLists.add(zhangBeanList.get(i).getChild());
        }

        if(parentLists!=null&&parentLists.size()>0) {

            adapter = new FirstVideoView1ListViewAdapter(mContext, parentLists, childLists);

            listView.setAdapter(adapter);

            for (int i = 0; i < parentLists.size(); i++) {
                listView.expandGroup(i);
            }
        }
    }

    private void initEvent() {

        listView.setOnGroupClickListener(new ExpandableListView.OnGroupClickListener() {

            @Override
            public boolean onGroupClick(ExpandableListView parent, View v,
                                        int groupPosition, long id) {
                // TODO Auto-generated method stub
                return true;
            }
        });
    }
}
