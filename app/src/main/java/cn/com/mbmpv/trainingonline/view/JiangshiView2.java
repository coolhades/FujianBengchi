package cn.com.mbmpv.trainingonline.view;

import android.content.Context;
import android.view.View;

import cn.com.mbmpv.trainingonline.R;


/**
 * Created by jiuzheyange on 2016/8/18.
 */
public class JiangshiView2 {
    
    Context mContext;
    View mView;

    public JiangshiView2(Context mContext) {
        this.mContext = mContext;
    }
    
    
    public View getView()
    {
        mView=View.inflate(mContext, R.layout.jiangshi_view2,null);
        initView();
        initData();
        initEvent();
        return mView;
    }

    private void initView() {
    }


    private void initData() {
    }

    private void initEvent() {
    }
}
