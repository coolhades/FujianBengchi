package cn.com.mbmpv.trainingonline.view;

import android.content.Context;
import android.content.Intent;
import android.graphics.Color;
import android.os.Handler;
import android.os.Message;
import android.util.Log;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;
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

import cn.com.mbmpv.trainingonline.R;
import cn.com.mbmpv.trainingonline.application.MyApplication;
import cn.com.mbmpv.trainingonline.bean.BaiFen;
import cn.com.mbmpv.trainingonline.bean.ClassResultDetail;
import cn.com.mbmpv.trainingonline.bean.MyExam;
import cn.com.mbmpv.trainingonline.bean.ResultMyExam;
import cn.com.mbmpv.trainingonline.bean.ResultPersonal;
import cn.com.mbmpv.trainingonline.bean.ResultStudy;
import cn.com.mbmpv.trainingonline.bean.ZhangBean;
import cn.com.mbmpv.trainingonline.ui.ExamineActivity;
import cn.com.mbmpv.trainingonline.ui.StudyDataActivity;
import cn.com.mbmpv.trainingonline.utils.ConstantSet;

/**
 * Created by jiuzheyange on 2016/8/24.
 */
public class VideoView4 {
    
    View mView;
    Context mContext;
    TextView baiFenText;
    TextView zongFenText;
    TextView num1;
    TextView num2;
    TextView num3;
    LinearLayout kaoshiBt;

    TextView kaoshi_num;
    TextView goin_tv;
    ImageView goin_iv;

    LinearLayout ziliao_ly;

    TextView studydata;
    TextView gotoziliao_tv;
    ImageView gotoziliao_iv;

    public Handler mhandler = new Handler() {
        @Override
        public void handleMessage(Message msg) {
            super.handleMessage(msg);
            if (msg.arg1 == 10100) {
                studydata.setText("无课程资料");
                gotoziliao_tv.setVisibility(View.INVISIBLE);
                gotoziliao_iv.setVisibility(View.INVISIBLE);
                ziliao_ly.setClickable(false);
            }

        }
    };

    public VideoView4(Context mContext) {
        this.mContext = mContext;
    }

    public View getView()
    {
        mView=View.inflate(mContext, R.layout.video_view4_layout,null);
        
        initView();
        initData();
        initEvent();
        
        return  mView;
    }

    private void initView() {

        baiFenText= (TextView) mView.findViewById(R.id.baifen);
        zongFenText= (TextView) mView.findViewById(R.id.zongfen);
        num1= (TextView) mView.findViewById(R.id.num1);
        num2= (TextView) mView.findViewById(R.id.num2);
        num3= (TextView) mView.findViewById(R.id.num3);
        kaoshiBt= (LinearLayout) mView.findViewById(R.id.kaoshi);
        kaoshi_num = (TextView) mView.findViewById(R.id.kaoshi_num);

        goin_tv = (TextView) mView.findViewById(R.id.goin_tv);
        goin_iv = (ImageView) mView.findViewById(R.id.goin_iv);

        ziliao_ly = (LinearLayout) mView.findViewById(R.id.ziliao_ly);
        studydata = (TextView) mView.findViewById(R.id.studydata);

        gotoziliao_tv = (TextView) mView.findViewById(R.id.gotoziliao_tv);
        gotoziliao_iv = (ImageView) mView.findViewById(R.id.gotoziliao_iv);
        getExamCount();
        getData();
        getBaiFen();

    }


    private void initData() {

    }


    private void initEvent() {


        kaoshiBt.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent i = new Intent(mContext, ExamineActivity.class);
                i.putExtra("Learn", "Learn");
                i.putExtra("course_id", ConstantSet.course_id);
                mContext.startActivity(i);
            }
        });

        ziliao_ly.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                mContext.startActivity(new Intent(mContext, StudyDataActivity.class));


                //测试 直接加载webview  测试OK
//                WebViewDialog dialog = new WebViewDialog(mContext, "http://fjbc.auto-mooc.com/pdfjs/web/viewer.html?file=http://fjbc.auto-mooc.com/compressed.tracemonkey-pldi-09.pdf",
//                        "学习档案", 0, null, null, "");
//
//                Window window = dialog.getWindow();
//                window.setGravity(Gravity.BOTTOM);
//                dialog.setCanceledOnTouchOutside(true);// 设置点击Dialog外部任意区域关闭Dialog
//                dialog.show();

            }
        });

    }

    public void getData()
    {
        StringRequest rq=new StringRequest(Request.Method.POST, ConstantSet.homeAddress+"user/getuserscore?", new Response.Listener<String>() {

            @Override
            public void onResponse(String response) {
                // TODO Auto-generated method stub

                //System.out.print("responsexuexi  "+response+"    "+response.length());
               // Toast.makeText(mContext,response,Toast.LENGTH_SHORT).show();
                if(response.length()>10) {

                    Gson gson = new Gson();
                    ResultStudy resultUser = gson.fromJson(response, new TypeToken<ResultStudy>() {
                    }.getType());

                    if (resultUser.getStatus().equals("1")) {

                        Log.i("TAGTAG", response);
                        
                        zongFenText.setText(resultUser.getData().getTotal());
                        num1.setText(resultUser.getData().getLearn());
                        num2.setText(resultUser.getData().getPractice());
                        num3.setText(resultUser.getData().getExam());
                    }
                }
               

            }
        }, new Response.ErrorListener(){

            @Override
            public void onErrorResponse(VolleyError error) {
                // TODO Auto-generated method stub
                Toast.makeText(mContext, "网络请求失败", Toast.LENGTH_SHORT).show();

            }}){
            @Override
            protected Map<String, String> getParams() throws AuthFailureError {
                // TODO Auto-generated method stub
                Map<String,String> map=new HashMap<String, String>();
                if(ConstantSet.user!=null) {
                    map.put("user_id", ConstantSet.user.getUid());
                }
                map.put("class_id",ConstantSet.class_id);

                return map;
            }
        };

        MyApplication.getRq().add(rq);
    }

    List<MyExam> lists = new ArrayList<>();
    //获取某一class中考试 遍历确认考试次数
    // 获取某一课程下考试列表
    public void getExamCount()
    {
        StringRequest rq=new StringRequest(Request.Method.POST, ConstantSet.homeAddress+"user/getmyexam?", new Response.Listener<String>() {

            @Override
            public void onResponse(String response) {
                // TODO Auto-generated method stub
                Log.i("TAGTAG考试列表", response);
                // showShortToast(response);
                if(response.length()>35) {
                    Gson gson = new Gson();
                    ResultMyExam resultUser = gson.fromJson(response, new TypeToken<ResultMyExam>() {
                    }.getType());
                    int count = 0;
                    lists=resultUser.getData();
                    if (!lists.isEmpty()){
                        count = lists.size();
                        kaoshi_num.setText(" "+count+"场考试");
                    }else {
                        kaoshi_num.setText("暂无考试");
                    }


                    if (count == 0){
                        kaoshiBt.setClickable(false);
                        kaoshi_num.setTextColor(Color.parseColor("#939393"));
                        goin_tv.setVisibility(View.INVISIBLE);
                        goin_iv.setVisibility(View.INVISIBLE);
                    }else {

                        kaoshi_num.setTextColor(Color.parseColor("#2E9DE2"));
                    }

                }

            }
        }, new Response.ErrorListener(){

            @Override
            public void onErrorResponse(VolleyError error) {
                // TODO Auto-generated method stub
                Toast.makeText(mContext, "网络请求失败", Toast.LENGTH_SHORT).show();

            }}){
            @Override
            protected Map<String, String> getParams() throws AuthFailureError {
                // TODO Auto-generated method stub
                Map<String,String> map=new HashMap<String, String>();
                if(ConstantSet.user!=null) {
                    map.put("user_id", ConstantSet.user.getUid());
                }
                map.put("class_id", ConstantSet.class_id);
                return map;
            }
        };

        MyApplication.getRq().add(rq);
    }
    ClassResultDetail resultLessonKc;
    List<ZhangBean> zhangBeanList;
    //获取对应课程资料
    public void getClassStudyData(String url) {
        StringRequest rq = new StringRequest(Request.Method.GET, url, new Response.Listener<String>() {

            @Override
            public void onResponse(String response) {
                // TODO Auto-generated method stub
                try{
//                    Log.i("Class_id-result", "  response= "+ConstantSet.homeAddress + "course/getdownload?class_id="+ConstantSet.class_id);
                    Log.i("Class_id-result", "  response= "+response);
                    Gson gson = new Gson();
                    resultLessonKc = gson.fromJson(response, new TypeToken<ClassResultDetail>() {
                    }.getType());

                    if (resultLessonKc.getStatus() == 1){
                        zhangBeanList = resultLessonKc.getData();

                        int size = zhangBeanList.size();
                        int k = 0;
                        for (int i = 0; i<size; i++){
                            if (zhangBeanList.get(i).getSource().isEmpty()){
                                k++;
                            }
                        }
                        if (k == size){
                            Log.i("Video_info", "无课程资料");
                            Message msg = Message.obtain();
                            msg.arg1 = 10100;
                            mhandler.sendMessage(msg);
                        }

                    }

                }catch (Exception e){

                }

            }
        }, new Response.ErrorListener() {

            @Override
            public void onErrorResponse(VolleyError error) {
                // TODO Auto-generated method stub
                Toast.makeText(mContext, "网络请求失败1", Toast.LENGTH_SHORT).show();
            }
        });
//        rq.setTag("adcGet");
        MyApplication.getRq().add(rq);
    }

    //此处显示的考试次数是所有考试的
    public void getkaoshiData() {
        StringRequest rq = new StringRequest(Request.Method.POST, ConstantSet.homeAddress + "user/getcount?", new Response.Listener<String>() {

            @Override
            public void onResponse(String response) {
                // TODO Auto-generated method stub

                // showShortToast("me  " + response);
                if (response.length() > 80) {

                    Gson gson = new Gson();

                    ResultPersonal result = gson.fromJson(response, new TypeToken<ResultPersonal>() {
                    }.getType());


                    kaoshi_num.setText("考试"+result.getData().getNum_exam()+"次");



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
                if (ConstantSet.user != null) {
                    map.put("user_id", ConstantSet.user.getUid());
                }
                return map;
            }
        };

        MyApplication.getRq().add(rq);
    }




    public void getBaiFen()
    {
        StringRequest rq=new StringRequest(Request.Method.POST, ConstantSet.homeAddress+"user/getlearnprogress?", new Response.Listener<String>() {

            @Override
            public void onResponse(String response) {
                // TODO Auto-generated method stub

                //System.out.print("responsexuexi  "+response+"    "+response.length());
                //showShortToast(response);
                if(response.length()>10) {

                    Gson gson = new Gson();
                    BaiFen resultUser = gson.fromJson(response, new TypeToken<BaiFen>() {
                    }.getType());

                    if (resultUser.getStatus().equals("1")) {

                        baiFenText.setText(resultUser.getData()+"%");
                    }
                }


            }
        }, new Response.ErrorListener(){

            @Override
            public void onErrorResponse(VolleyError error) {
                // TODO Auto-generated method stub
                Toast.makeText(mContext, "网络请求失败", Toast.LENGTH_SHORT).show();

            }}){
            @Override
            protected Map<String, String> getParams() throws AuthFailureError {
                // TODO Auto-generated method stub
                Map<String,String> map=new HashMap<String, String>();
                if(ConstantSet.user!=null) {
                    map.put("user_id", ConstantSet.user.getUid());
                }
                map.put("class_id",ConstantSet.class_id);

                return map;
            }
        };

        MyApplication.getRq().add(rq);
    }
    
}
