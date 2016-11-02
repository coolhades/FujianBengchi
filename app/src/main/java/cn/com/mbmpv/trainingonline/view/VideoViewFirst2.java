package cn.com.mbmpv.trainingonline.view;

import android.content.Context;
import android.content.Intent;
import android.util.Log;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;

import com.android.volley.AuthFailureError;
import com.android.volley.Request;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

import java.util.HashMap;
import java.util.Map;

import cn.com.mbmpv.trainingonline.R;
import cn.com.mbmpv.trainingonline.application.MyApplication;
import cn.com.mbmpv.trainingonline.bean.Jianjie;
import cn.com.mbmpv.trainingonline.bean.ResultJoin;
import cn.com.mbmpv.trainingonline.ui.VideoActivity;
import cn.com.mbmpv.trainingonline.ui.VideoActivityFirst;
import cn.com.mbmpv.trainingonline.utils.ConstantSet;
import cn.com.mbmpv.trainingonline.utils.Md5Utils;

import static android.view.View.inflate;

/**
 * Created by jiuzheyange on 2016/8/10.
 */
public class VideoViewFirst2 {

    Context mContext;
    View mView;

    TextView detailTitle;
    TextView teacherDate;
    TextView keshiText;
    TextView renqiText;
    TextView yuanjiaText;
    TextView xianJiaText;
    TextView gaishuText;

    Jianjie jianjie;

    TextView joinLesson;


    public VideoViewFirst2(Context mContext, Jianjie jianjie) {
        this.mContext = mContext;
        this.jianjie = jianjie;
    }

    public View getView() {
        mView = inflate(mContext, R.layout.video_view_first2, null);
        initView();
        initData();
        initEvent();

        return mView;

    }

    private void initView() {

        detailTitle = (TextView) mView.findViewById(R.id.detail_title);
        teacherDate = (TextView) mView.findViewById(R.id.teacher_and_date);
        keshiText = (TextView) mView.findViewById(R.id.keshi_text);
        renqiText = (TextView) mView.findViewById(R.id.renqi_text);
        yuanjiaText = (TextView) mView.findViewById(R.id.yuanjia_text);
        xianJiaText = (TextView) mView.findViewById(R.id.xianjia_text);
        gaishuText = (TextView) mView.findViewById(R.id.gaishu_text);

        detailTitle.setText(jianjie.getCourse_name());
        teacherDate.setText(jianjie.getTeacher_name());
        keshiText.setText(jianjie.getNum_hour() + "课时");
        renqiText.setText("人气" + jianjie.getNum_visit());
        
        if(jianjie.getCourse_price_original().equals("0"))
        { 
            yuanjiaText.setVisibility(View.INVISIBLE);
            
        }else {
            yuanjiaText.setText("￥" + jianjie.getCourse_price_original());
        }
        if (jianjie.getCourse_price().equals("0")) {
            xianJiaText.setText("免费");
        } else {
            xianJiaText.setText("￥" + jianjie.getCourse_price());
        }
        gaishuText.setText(jianjie.getCourse_desc_m());


        joinLesson = (TextView) mView.findViewById(R.id.jion_lesson);
        if ((jianjie.getClass_id().equals("0"))) {
            joinLesson.setVisibility(View.INVISIBLE);
        }

        if (jianjie.getIs_classmember().equals("1")) {
            //说明已经加入班级
            Log.i("TAGTAG", "调用了！");
            //去看视频

            mContext.startActivity(new Intent(mContext, VideoActivity.class));
            ((VideoActivityFirst) mContext).finish();
            //改版  我的课程 不显示继续学习
//            joinLesson.setVisibility(View.INVISIBLE);
//            joinLesson.setText("继续学习");
        }


    }

    private void initData() {
    }

    private void initEvent() {


        joinLesson.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                //加入班级
                if (jianjie.getIs_classmember().equals("0")) {

                    getData();
                    //多一个加入操作

                } else {
                    //去看视频  home 跳转
                    Intent i = new Intent(mContext, VideoActivity.class);
                    i.putExtra("My", "Home");
                    mContext.startActivity(i);

                    ((VideoActivityFirst) mContext).finish();
                }
            }
        });
    }


    public void getData() {
        StringRequest rq = new StringRequest(Request.Method.POST, ConstantSet.homeAddress + "course/joinclass?", new Response.Listener<String>() {

            @Override
            public void onResponse(String response) {
                // TODO Auto-generated method stub

                Log.d("Resopnse  ", response);
                // Toast.makeText(mContext,response,Toast.LENGTH_LONG).show();
                //showShortToast(response)
                Gson gson = new Gson();

                ResultJoin result = gson.fromJson(response, new TypeToken<ResultJoin>() {
                }.getType());

                if (result.getStatus().equals("1")) {
                    mContext.startActivity(new Intent(mContext, VideoActivity.class));

                    Toast.makeText(mContext,"加入成功",Toast.LENGTH_SHORT).show();
                    ((VideoActivityFirst) mContext).finish();
                } else {
                    Toast.makeText(mContext, "此课程无法加入", Toast.LENGTH_SHORT).show();
                }
            }
        }, new Response.ErrorListener() {

            @Override
            public void onErrorResponse(VolleyError error) {
                // TODO Auto-generated method stub
                Toast.makeText(mContext, "网络请求失败", Toast.LENGTH_SHORT).show();
            }
        }) {
            @Override
            protected Map<String, String> getParams() throws AuthFailureError {
                // TODO Auto-generated method stub
                Map<String, String> map = new HashMap<String, String>();
                map.put("user_id", ConstantSet.user.getUid());//后期改成course_id
                map.put("class_id", jianjie.getClass_id());
                map.put("okey", Md5Utils.md5("mooccoursejoinclass" + ConstantSet.user.getUid() + jianjie.getClass_id()));
                return map;
            }
        };

        MyApplication.getRq().add(rq);
    }
}
