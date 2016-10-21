package cn.com.mbmpv.trainingonline.view;

import android.content.Context;
import android.view.View;

import cn.com.mbmpv.trainingonline.R;


/**
 * Created by jiuzheyange on 2016/8/9.
 */
public class HomeFragmentFootView {
    
    Context mContext;
    View mView;
    

    public HomeFragmentFootView(Context mContext) {
        this.mContext = mContext;
    }
    
    public View getView()
    {
        mView=View.inflate(mContext, R.layout.homefragment_footview,null);
        initView();
        initData();
        initEvent();
        
        return mView;
    }



    protected void initView() {
    }


    protected void initData() {

    }

    protected void initEvent() {
        
    }
}
