package cn.com.mbmpv.trainingonline.ui;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;

import com.android.volley.AuthFailureError;
import com.android.volley.Request;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;

import cn.com.mbmpv.trainingonline.R;
import cn.com.mbmpv.trainingonline.application.MyApplication;
import cn.com.mbmpv.trainingonline.bean.MainAdressBean;
import cn.com.mbmpv.trainingonline.bean.Sign;
import cn.com.mbmpv.trainingonline.utils.ConstantSet;
import cn.com.mbmpv.trainingonline.utils.SaveUser;


public class StartupActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_startup);
        
        SaveUser save=new SaveUser(this);
        ConstantSet.user=(save.getData("userFile","user"));

//        getMain();

        new Thread(new Runnable() {
            @Override
            public void run() {
                try {
                    Thread.sleep(2000);
                    getMain();
//                    getData();
//                    getConfi();
                    //  getConfi();
                    Thread.sleep(2000);
//                    startActivity(new Intent(StartupActivity.this,LoginAndRegisterActivity.class));
//                    StartupActivity.this.finish();

                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        }).start();


        
//        new Thread(new Runnable() {
//            @Override
//            public void run() {
//                try {
//                    getData();
//                    getConfi();
//                  //  getConfi();
//                    Thread.sleep(3000);
//                    startActivity(new Intent(StartupActivity.this,LoginAndRegisterActivity.class));
//                    StartupActivity.this.finish();
//
//                } catch (InterruptedException e) {
//                    e.printStackTrace();
//                }
//            }
//        }).start();
        
    }

    //判断是否签到过
    public void getData()
    {
        StringRequest rq=new StringRequest(Request.Method.POST, ConstantSet.homeAddress+"ding/judgeding?", new Response.Listener<String>() {

            @Override
            public void onResponse(String response) {
                // TODO Auto-generated method stub


              
               // Toast.makeText(StartupActivity.this,response, Toast.LENGTH_SHORT).show();
                System.out.print("response 12312 "+response+"    "+response.length());
                if(response.length()>10) {

                    //Toast.makeText(StartupActivity.this,response+"222",Toast.LENGTH_SHORT).show();
                    Gson gson = new Gson();
                    Sign resultUser = gson.fromJson(response, new TypeToken<Sign>() {
                    }.getType());
                    SimpleDateFormat formatter=new SimpleDateFormat("yyyy-MM-dd hh:mm:ss");
                    String str=formatter.format(new Date());
                    if(resultUser.getData()!=null) {
                        ConstantSet.jifen = resultUser.getData().getIntegral_value();
                    }
                   
                    if(resultUser.getMessage().equals("今天未签到")) {
                        ConstantSet.sign = false;
                    }
                   
                    
                }
            }
        }, new Response.ErrorListener(){

            @Override
            public void onErrorResponse(VolleyError error) {
                // TODO Auto-generated method stub

            }}){
            @Override
            protected Map<String, String> getParams() throws AuthFailureError {
                // TODO Auto-generated method stub
                Map<String,String> map=new HashMap<String, String>();
                if(ConstantSet.user!=null) {
                    map.put("user_uid", ConstantSet.user.getUid());
                }

                return map;
            }
        };

        MyApplication.getRq().add(rq);
    }


    public static String getTime(String user_time) {
        String re_time = null;
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd hh:mm:ss");
        Date d;
        try {
            d = sdf.parse(user_time);
            long l = d.getTime();
            String str = String.valueOf(l);
            re_time = str.substring(0, 10);
        }catch (ParseException e) {
            // TODO Auto-generated catch block e.printStackTrace();
        }
        return re_time;
    }

    public static Date getFirstdayofThisMonth() {
        Calendar cal = Calendar.getInstance();
        cal.set(Calendar.HOUR, 0);
        cal.set(Calendar.MINUTE,0);
        cal.set(Calendar.SECOND,0);
        return cal.getTime();
    }


    public void getMain(){
        StringRequest rq=new StringRequest(Request.Method.POST, ConstantSet.newhomeAddress, new Response.Listener<String>() {

            @Override
            public void onResponse(String response) {
                // TODO Auto-generated method stub

                Log.i("TAGTAG", response);
                Gson gson = new Gson();
                MainAdressBean mainAdressBean = gson.fromJson(response, MainAdressBean.class);
                if (mainAdressBean.getStatus() == 1){
                    //获取成功 设置 url前缀
                    ConstantSet.homeAddress = "http://"+mainAdressBean.getData().trim()+"/";
                    Log.i("TAGTAG", ConstantSet.homeAddress);
                    getConfi();

                }
            }
        }, new Response.ErrorListener(){

            @Override
            public void onErrorResponse(VolleyError error) {
                // TODO Auto-generated method stub

            }}){
            @Override
            protected Map<String, String> getParams() throws AuthFailureError {
                // TODO Auto-generated method stub
                Map<String,String> map=new HashMap<String, String>();
                map.put("name","fjbc");
                return map;
            }
        };

        MyApplication.getRq().add(rq);
    }

    public void getConfi()
    {
        StringRequest rq=new StringRequest(Request.Method.POST, ConstantSet.homeAddress+"actions/apiconfig?", new Response.Listener<String>() {

            @Override
            public void onResponse(String response) {
                // TODO Auto-generated method stub
                Log.i("Config", response);

                if(response.length()>10) {

                    Gson gson = new Gson();
                    Map<String,String> confiMap=  gson.fromJson(response, new TypeToken<Map<String,String>>() {
                    }.getType());
               
                    ConstantSet.confiMap=confiMap;

//                    getData();
                    startActivity(new Intent(StartupActivity.this,LoginAndRegisterActivity.class));
                    Log.i("TAG!", "执行1");
                    StartupActivity.this.finish();
                }else {
                    //失败 跳转登录
                    startActivity(new Intent(StartupActivity.this,ErrorLoginActivity.class));
                    Log.i("TAG!",  "执行2");
                    StartupActivity.this.finish();
                }
            }
        }, new Response.ErrorListener(){

            @Override
            public void onErrorResponse(VolleyError error) {
                // TODO Auto-generated method stub

            }}){
            @Override
            protected Map<String, String> getParams() throws AuthFailureError {
                // TODO Auto-generated method stub
                Map<String,String> map=new HashMap<String, String>();
                map.put("timestamp","1471604755");
                return map;
            }
        };

        MyApplication.getRq().add(rq);
    }
    
}
