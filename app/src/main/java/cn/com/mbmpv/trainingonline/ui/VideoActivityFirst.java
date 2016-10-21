package cn.com.mbmpv.trainingonline.ui;

import android.graphics.Color;
import android.os.Bundle;
import android.support.v4.view.ViewPager;
import android.util.Log;
import android.view.Gravity;
import android.view.View;
import android.view.Window;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.android.volley.AuthFailureError;
import com.android.volley.Request;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import cn.com.mbmpv.trainingonline.BaseActivity;
import cn.com.mbmpv.trainingonline.R;
import cn.com.mbmpv.trainingonline.adapter.VideoViewPagerAdapterFirst;
import cn.com.mbmpv.trainingonline.application.MyApplication;
import cn.com.mbmpv.trainingonline.bean.ResultDetail;
import cn.com.mbmpv.trainingonline.utils.ConstantSet;
import cn.com.mbmpv.trainingonline.utils.LoadImgUtils;
import cn.com.mbmpv.trainingonline.utils.Md5Utils;
import cn.com.mbmpv.trainingonline.view.VideoViewFirst1;
import cn.com.mbmpv.trainingonline.view.VideoViewFirst2;
import cn.com.mbmpv.trainingonline.view.VideoViewFirst3;
import cn.com.mbmpv.trainingonline.widget.ProgressDialog;

public class VideoActivityFirst extends BaseActivity {

    ViewPager viewPager;
    VideoViewPagerAdapterFirst adapter;
    List<View> lists;
    
    ImageView videoImg;
    
    TextView jianjieBt;
    TextView muluBt;
    TextView tiwenBt;
    
    ImageView img1;
    ImageView img2;
    ImageView img3;
    
    
    ImageView back;

    ProgressDialog dialog;
    
    TextView videoTitle;

    
    
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_video_first);
        initView();
        initData();
//        initEvent();

    }

    @Override
    protected void initView() {
        
        viewPager= (ViewPager) findViewById(R.id.video_viewpager);
        videoImg= (ImageView) findViewById(R.id.video_img);

      /*  LinearLayout.LayoutParams params= (LinearLayout.LayoutParams) videoImg.getLayoutParams();
        params.height=MyApplication.getScreenWidth()*2/5;
        videoImg.setLayoutParams(params);*/
        
        jianjieBt= (TextView) findViewById(R.id.jianjie_bt);
        muluBt= (TextView) findViewById(R.id.mulu_bt);
        tiwenBt= (TextView) findViewById(R.id.tiwen_bt);
        
        img1= (ImageView) findViewById(R.id.img1);
        img2= (ImageView) findViewById(R.id.img2);
        img3= (ImageView) findViewById(R.id.img3);

        back= (ImageView) findViewById(R.id.back);

        videoTitle= (TextView) findViewById(R.id.video_title);

        //videoTitle.setText(ConstantSet.videoTitle);

        dialog = new ProgressDialog(this);

        Window window = dialog.getWindow();
        window.setGravity(Gravity.CENTER);
        dialog.setCanceledOnTouchOutside(false);// 设置点击Dialog外部任意区域关闭Dialog
        dialog.show();

        if(ConstantSet.confiMap!=null) {
            if (ConstantSet.confiMap.get("App.Switch.Course.Evaluate.Show").equals("0")) {
                tiwenBt.setVisibility(View.GONE);
                img3.setVisibility(View.GONE);
            }
        }
        
    }

    @Override
    protected void initData() {
        
        lists=new ArrayList<View>();

        getData();

        

    }

    @Override
    protected void initEvent() {

        jianjieBt.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                
                jianjieBt.setTextColor(Color.parseColor("#333333"));
                muluBt.setTextColor(Color.parseColor("#C4C4C4"));
                tiwenBt.setTextColor(Color.parseColor("#C4C4C4"));
                
                img1.setImageResource(R.mipmap.heixian_img);
                img2.setImageResource(R.mipmap.touming_img);
                img3.setImageResource(R.mipmap.touming_img);
                
                
                viewPager.setCurrentItem(0);
                
            }
        });
        
        
        muluBt.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                jianjieBt.setTextColor(Color.parseColor("#C4C4C4"));
                muluBt.setTextColor(Color.parseColor("#333333"));
                tiwenBt.setTextColor(Color.parseColor("#C4C4C4"));
                img1.setImageResource(R.mipmap.touming_img);
                img2.setImageResource(R.mipmap.heixian_img);
                img3.setImageResource(R.mipmap.touming_img);
                
                viewPager.setCurrentItem(1);
            }
        });
        
        
        tiwenBt.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                jianjieBt.setTextColor(Color.parseColor("#C4C4C4"));
                muluBt.setTextColor(Color.parseColor("#C4C4C4"));
                tiwenBt.setTextColor(Color.parseColor("#333333"));

                img1.setImageResource(R.mipmap.touming_img);
                img2.setImageResource(R.mipmap.touming_img);
                img3.setImageResource(R.mipmap.heixian_img);
                
                viewPager.setCurrentItem(2);
            }
        });


        viewPager.setOnPageChangeListener(new ViewPager.OnPageChangeListener() {
            @Override
            public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {
                
            }

            @Override
            public void onPageSelected(int position) {

                if(position==0)
                {
                    jianjieBt.setTextColor(Color.parseColor("#333333"));
                    muluBt.setTextColor(Color.parseColor("#C4C4C4"));
                    tiwenBt.setTextColor(Color.parseColor("#C4C4C4"));

                    img1.setImageResource(R.mipmap.heixian_img);
                    img2.setImageResource(R.mipmap.touming_img);
                    img3.setImageResource(R.mipmap.touming_img); 
                }

                else if(position==1)
                {
                    jianjieBt.setTextColor(Color.parseColor("#C4C4C4"));
                    muluBt.setTextColor(Color.parseColor("#333333"));
                    tiwenBt.setTextColor(Color.parseColor("#C4C4C4"));
                    img1.setImageResource(R.mipmap.touming_img);
                    img2.setImageResource(R.mipmap.heixian_img);
                    img3.setImageResource(R.mipmap.touming_img);
                }else 
                {
                    jianjieBt.setTextColor(Color.parseColor("#C4C4C4"));
                    muluBt.setTextColor(Color.parseColor("#C4C4C4"));
                    tiwenBt.setTextColor(Color.parseColor("#333333"));

                    img1.setImageResource(R.mipmap.touming_img);
                    img2.setImageResource(R.mipmap.touming_img);
                    img3.setImageResource(R.mipmap.heixian_img);
                }
            }

            @Override
            public void onPageScrollStateChanged(int state) {

            }
        });




        back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                
                VideoActivityFirst.this.finish();
                
            }
        });
        
        
    }
    
    public void getData()
    {
        StringRequest rq = new StringRequest(Request.Method.POST, ConstantSet.homeAddress + "course/getdetail?", new Response.Listener<String>() {

            @Override
            public void onResponse(String response) {
                // TODO Auto-generated method stub
                Log.i("impwer_id  ",response);
               // showLongToast(response);
                if (response.length() > 350) {

                    Gson gson=new Gson();

                    ResultDetail resultLessonKc = gson.fromJson(response, new TypeToken<ResultDetail>() {
                    }.getType());

                    videoTitle.setText(resultLessonKc.getData().getInfo().getCourse_name());


                    ConstantSet.impower_id = resultLessonKc.getData().getInfo().getImpower_id();
                    ConstantSet.home_course_id = resultLessonKc.getData().getInfo().getCourse_id();
                    lists.add(new VideoViewFirst2(VideoActivityFirst.this,resultLessonKc.getData().getInfo()).getView());
                    lists.add(new VideoViewFirst1(VideoActivityFirst.this,resultLessonKc.getData().getSection()).getView());

                    if(ConstantSet.confiMap!=null) {
                        if (ConstantSet.confiMap.get("App.Switch.Course.Evaluate.Show").equals("0")) {
                            lists.add(new VideoViewFirst3(VideoActivityFirst.this, resultLessonKc.getData().getInfo()).getView());
                        }
                    }
                    if(adapter==null)
                    {
                        dialog.colseDialog();
                    }
                    
                    adapter=new VideoViewPagerAdapterFirst(lists,VideoActivityFirst.this);
                    viewPager.setAdapter(adapter);


                    LoadImgUtils.setImage(VideoActivityFirst.this,resultLessonKc.getData().getInfo().getCourse_album(),videoImg);
                    
                    tiwenBt.setText("提问（"+resultLessonKc.getData().getNum_comment()+")");
                    
//
//                    list1=resultLessonKc.getData().getList();
//                    adapter1=new KechengListAdapter(list1,getActivity());
//                    listView1.setAdapter(adapter1);

                }
            }
        }, new Response.ErrorListener() {

            @Override
            public void onErrorResponse(VolleyError error) {
                // TODO Auto-generated method stub
                Toast.makeText(VideoActivityFirst.this, "网络请求失败1", Toast.LENGTH_SHORT).show();
            }
        }) {
            @Override
            protected Map<String, String> getParams() throws AuthFailureError {
                // TODO Auto-generated method stub
                Map<String,String> map=new HashMap<String, String>();
                map.put("course_id",ConstantSet.course_id);//后期改成course_id
                map.put("okey", Md5Utils.md5("mooccoursegetdetail"+ConstantSet.course_id));
                map.put("user_id",ConstantSet.user.getUid());
                return map;
            }
        };

        MyApplication.getRq().add(rq);
    }
    
}
