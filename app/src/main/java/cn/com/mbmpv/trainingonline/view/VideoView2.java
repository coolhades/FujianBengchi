package cn.com.mbmpv.trainingonline.view;

import android.content.Context;
import android.view.View;
import android.widget.TextView;

import cn.com.mbmpv.trainingonline.R;
import cn.com.mbmpv.trainingonline.bean.Jianjie;

import static android.view.View.inflate;

/**
 * Created by jiuzheyange on 2016/8/10.
 */
public class VideoView2 {

    Context mContext;
    View mView;

   // TextView detailTitle;
   // TextView teacherDate;
   // TextView keshiText;
   // TextView renqiText;
   // TextView yuanjiaText;
    //TextView xianJiaText;
    TextView gaishuText;

    Jianjie jianjie;
    





    public VideoView2(Context mContext,Jianjie jianjie) {
        this.mContext = mContext;
        this.jianjie=jianjie;
    }

    public View getView()
    {
        mView= inflate(mContext, R.layout.video_view2,null);
        initView();
        initData();
        initEvent();

        return mView;

    }

    private void initView() {

//        detailTitle= (TextView) mView.findViewById(R.id.detail_title);
//        teacherDate= (TextView) mView.findViewById(R.id.teacher_and_date);
//        keshiText= (TextView) mView.findViewById(R.id.keshi_text);
//        renqiText= (TextView) mView.findViewById(R.id.renqi_text);
//        yuanjiaText= (TextView) mView.findViewById(R.id.yuanjia_text);
//        xianJiaText= (TextView) mView.findViewById(R.id.xianjia_text);
        gaishuText= (TextView) mView.findViewById(R.id.gaishu_text);

//        detailTitle.setText(jianjie.getCourse_name());
//        teacherDate.setText(jianjie.getTeacher_name()+"/"+jianjie.getDate_start()+" "+jianjie.getDate_end());
//        keshiText.setText(jianjie.getNum_hour()+"课时");
//        renqiText.setText("人气"+jianjie.getNum_visit());
//        yuanjiaText.setText("￥"+jianjie.getCourse_price_original());
//        xianJiaText.setText("￥"+jianjie.getCourse_price());
        gaishuText.setText(jianjie.getCourse_desc_m());
        
    }

    private void initData() {
    }

    private void initEvent() {
        
    }
}
