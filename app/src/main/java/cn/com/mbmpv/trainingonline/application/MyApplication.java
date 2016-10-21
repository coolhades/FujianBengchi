package cn.com.mbmpv.trainingonline.application;

import android.app.Application;
import android.content.Context;
import android.view.WindowManager;

import com.android.volley.RequestQueue;
import com.android.volley.toolbox.Volley;

import java.util.ArrayList;
import java.util.List;

import cn.jpush.android.api.JPushInterface;
import cn.sharesdk.framework.ShareSDK;

/**
 * Created by jiuzheyange on 2016/8/8.
 */
public class MyApplication extends Application {

    private static RequestQueue rq;
    static int screenHight;
    static int screenWidth;


    //记录搜索历史
    private static List<String> historyLists;

    @Override
    public void onCreate() {
        super.onCreate();

        JPushInterface.init(this);
        
        //测试推送
//        JPushInterface.setAlias(getApplicationContext(), "1A0052A841F2F904046485BAAD3DF89E", new TagAliasCallback() {
//            @Override
//            public void gotResult(int i, String s, Set<String> set) {
//
//            }
//        });

        //Volley 请求队列的初始化

        rq = Volley.newRequestQueue(getApplicationContext());

        historyLists=new ArrayList<String>();
        ShareSDK.initSDK(getApplicationContext(), "15e5b1ccdf764");
        WindowManager wm = (WindowManager) this.getSystemService(Context.WINDOW_SERVICE);
        screenWidth = wm.getDefaultDisplay().getWidth();
        screenHight = wm.getDefaultDisplay().getHeight();

    }


    public static int getScreenHight() {
        return screenHight;
    }

    public static void setScreenHight(int screenHight) {
        MyApplication.screenHight = screenHight;
    }

    public static int getScreenWidth() {
        return screenWidth;
    }

    public static void setScreenWidth(int screenWidth) {
        MyApplication.screenWidth = screenWidth;
    }


    public static RequestQueue getRq() {
        return rq;
    }

    public static List<String> getHistoryLists() {
        return historyLists;
    }

    public static void setHistoryLists(List<String> historyLists) {
        MyApplication.historyLists = historyLists;
    }
}
