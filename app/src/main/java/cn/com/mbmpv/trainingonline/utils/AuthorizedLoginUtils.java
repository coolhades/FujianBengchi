package cn.com.mbmpv.trainingonline.utils;

import android.content.Context;
import android.content.Intent;
import android.util.Log;
import android.widget.Toast;

import com.android.volley.AuthFailureError;
import com.android.volley.Request;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

import cn.com.mbmpv.trainingonline.application.MyApplication;
import cn.com.mbmpv.trainingonline.bean.ResultUser;
import cn.com.mbmpv.trainingonline.ui.HomeActivity;
import cn.com.mbmpv.trainingonline.ui.LoginAndRegisterActivity;

import java.util.HashMap;
import java.util.Map;

import cn.sharesdk.framework.Platform;
import cn.sharesdk.framework.PlatformActionListener;
import cn.sharesdk.framework.PlatformDb;
import cn.sharesdk.framework.ShareSDK;
import cn.sharesdk.sina.weibo.SinaWeibo;
import cn.sharesdk.tencent.qq.QQ;
import cn.sharesdk.wechat.friends.Wechat;

/**
 * Created by jiuzheyange on 2016/8/11.
 */
public class AuthorizedLoginUtils {

   static  PlatformDb db;
   
    public static void authorzedQQ(final Context context)
    {
        
        System.err.print("hahahha");
        final Platform plat = ShareSDK.getPlatform(QQ.NAME);
        if (plat == null) {
            return;
        }

        // 这里用于判断用户是否已经授权登陆过，如果已经授权过，则可以通过下面方法获取用户信息，并直接进入
//        if(plat.isAuthValid()) {
//            String userId = plat.getDb().getUserId();
//            if (userId != null) {
//                System.err.println("data  " +plat.getDb().getUserName());
//                return;
//            }
//        }

        plat.SSOSetting(false); // 设置false表示使用SSO授权方式
        plat.setPlatformActionListener(new PlatformActionListener() {

            @Override
            public void onError(Platform arg0, int arg1, Throwable arg2) {
                // TODO Auto-generated method stub
                Log.d("data","error");

               // Toast.makeText(context,"error",Toast.LENGTH_SHORT).show();
            }

            @Override
            public void onComplete(final Platform arg0, int arg1, HashMap<String, Object> arg2) {
                // TODO Auto-generated method stub
                Log.d("TAG", arg0.getDb().getUserName());
                // 第一次授权登陆成功后，在这里可以获取用户的基本信息。
                // arg2.get("key");
                // key只可以通过输出arg2进行查看
                // 授权成功后的回掉
                // mAuthorization=true;
                // 先这样写，登陆后跳到主界面
                //记录用户信息
               // Toast.makeText(context,"data",Toast.LENGTH_SHORT).show();
               //去后端注册

                StringRequest rq=new StringRequest(Request.Method.POST,ConstantSet.homeAddress+"user/loginthrid?", new Response.Listener<String>() {

                    @Override
                    public void onResponse(String response) {
                        // TODO Auto-generated method stub

                        System.out.print("response  "+response);
                        
                        Gson gson=new Gson();
                        ResultUser resultUser=gson.fromJson(response, new TypeToken<ResultUser>(){}.getType());
                        
                        if(resultUser.getStatus().equals("1"))
                        {
                            context.startActivity(new Intent(context, HomeActivity.class));
                            
                            ((LoginAndRegisterActivity)context).finish();
                        }
                       
                    }
                }, new Response.ErrorListener(){

                    @Override
                    public void onErrorResponse(VolleyError error) {
                        // TODO Auto-generated method stub
                        Toast.makeText(context, "网络请求失败", Toast.LENGTH_SHORT).show();

                    }}){
                    @Override
                    protected Map<String, String> getParams() throws AuthFailureError {
                        // TODO Auto-generated method stub
                        Map<String,String> map=new HashMap<String, String>();
                        map.put("obj_type","qq");
                        map.put("obj_id",arg0.getDb().getUserId());
                        map.put("obj_nickname",arg0.getDb().getUserName());
                        map.put("obj_avatar",arg0.getDb().getUserIcon());
                        map.put("okey",Md5Utils.md5("mooc"+"userloginthridqq"+arg0.getDb().getUserId()));

                        return map;
                    }
                };

                MyApplication.getRq().add(rq);
   
            }

            @Override
            public void onCancel(Platform arg0, int arg1) {
                // TODO Auto-generated method stub
                Log.d("data","cancel");
                //Toast.makeText(context,"Cancel",Toast.LENGTH_SHORT).show();
            }
        }); // 设置分享事件回调

        plat.authorize();

    }




    public static void authorzedWechat(final Context context)
    {
        final Platform plat = ShareSDK.getPlatform(Wechat.NAME);
        if (plat == null) {
            return;
        }

        // 这里用于判断用户是否已经授权登陆过，如果已经授权过，则可以通过下面方法获取用户信息，并直接进入
//    if(plat.isAuthValid()) {
//        String userId = plat.getDb().getUserId();
//        if (userId != null) {
//            Log.d("data","error");
//            return;
//        }
//    }

        plat.SSOSetting(false); // 设置false表示使用SSO授权方式
        plat.setPlatformActionListener(new PlatformActionListener() {

            @Override
            public void onError(Platform arg0, int arg1, Throwable arg2) {
                // TODO Auto-generated method stub
                Log.d("data","error");
            }

            @Override
            public void onComplete(final Platform arg0, int arg1, HashMap<String, Object> arg2) {
                // TODO Auto-generated method stub

                // 第一次授权登陆成功后，在这里可以获取用户的基本信息。
                // arg2.get("key");
                // key只可以通过输出arg2进行查看
                // 授权成功后的回掉
                // mAuthorization=true;
                // 先这样写，登陆后跳到主界面
                //记录用户信息

                Log.d("data",arg2+"");

                StringRequest rq=new StringRequest(Request.Method.POST,ConstantSet.homeAddress+"user/loginthrid?", new Response.Listener<String>() {

                    @Override
                    public void onResponse(String response) {
                        // TODO Auto-generated method stub

                        System.out.print("response  "+response);
                    }
                }, new Response.ErrorListener(){

                    @Override
                    public void onErrorResponse(VolleyError error) {
                        // TODO Auto-generated method stub
                        Toast.makeText(context, "网络请求失败", Toast.LENGTH_SHORT).show();

                    }}){
                    @Override
                    protected Map<String, String> getParams() throws AuthFailureError {
                        // TODO Auto-generated method stub
                        Map<String,String> map=new HashMap<String, String>();
                        map.put("obj_type","wechat");
                        map.put("obj_id",arg0.getDb().getUserId());
                        map.put("obj_nickname",arg0.getDb().getUserName());
                        map.put("obj_avatar",arg0.getDb().getUserIcon());
                        map.put("okey",Md5Utils.md5("mooc"+"userloginthridwechat"+arg0.getDb().getUserId()));

                        return map;
                    }
                };

                MyApplication.getRq().add(rq);

            }

            @Override
            public void onCancel(Platform arg0, int arg1) {
                // TODO Auto-generated method stub
                Log.d("data","error");
            }
        }); // 设置分享事件回调

        plat.authorize();

    }



    public static void authorzedSina(final Context context)
    {
        final Platform plat = ShareSDK.getPlatform(SinaWeibo.NAME);
        if (plat == null) {
            return;
        }

//        if(plat.isAuthValid()) {
//            String userId = plat.getDb().getUserId();
//            if (userId != null) {
//                System.err.println("data  " +plat.getDb().getUserName());
//                return;
//            }
//        }

        plat.SSOSetting(false); // 设置false表示使用SSO授权方式
        plat.setPlatformActionListener(new PlatformActionListener() {

            @Override
            public void onError(Platform arg0, int arg1, Throwable arg2) {
                // TODO Auto-generated method stub
                System.err.println("Error");
            }

            @Override
            public void onComplete(final Platform arg0, int arg1, HashMap<String, Object> arg2) {
                // TODO Auto-generated method stub

                // 第一次授权登陆成功后，在这里可以获取用户的基本信息。
                // arg2.get("key");
                // key只可以通过输出arg2进行查看
                // 授权成功后的回掉
                // mAuthorization=true;
                // 先这样写，登陆后跳到主界面
                //记录用户信息

                System.err.print("data  "+arg2);

                StringRequest rq=new StringRequest(Request.Method.POST,ConstantSet.homeAddress+"user/loginthrid?", new Response.Listener<String>() {

                    @Override
                    public void onResponse(String response) {
                        // TODO Auto-generated method stub

                        System.out.print("response  "+response);
                    }
                }, new Response.ErrorListener(){

                    @Override
                    public void onErrorResponse(VolleyError error) {
                        // TODO Auto-generated method stub
                        Toast.makeText(context, "网络请求失败", Toast.LENGTH_SHORT).show();

                    }}){
                    @Override
                    protected Map<String, String> getParams() throws AuthFailureError {
                        // TODO Auto-generated method stub
                        Map<String,String> map=new HashMap<String, String>();
                        map.put("obj_type","sina");
                        map.put("obj_id",arg0.getDb().getUserId());
                        map.put("obj_nickname",arg0.getDb().getUserName());
                        map.put("obj_avatar",arg0.getDb().getUserIcon());
                        map.put("okey",Md5Utils.md5("mooc"+"userloginthridsina"+arg0.getDb().getUserId()));

                        return map;
                    }
                };

                MyApplication.getRq().add(rq);

            }

            @Override
            public void onCancel(Platform arg0, int arg1) {
                // TODO Auto-generated method stub
                System.err.println("Cancel");
            }
        }); // 设置分享事件回调

        plat.authorize();

    }

}
    
