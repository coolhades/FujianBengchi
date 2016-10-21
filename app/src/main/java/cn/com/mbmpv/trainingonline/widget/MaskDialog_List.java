package cn.com.mbmpv.trainingonline.widget;

import android.app.Dialog;
import android.content.Context;
import android.os.Bundle;
import android.support.v4.view.ViewPager;
import android.util.Log;
import android.view.View;
import android.widget.ImageView;
import android.widget.Toast;

import com.android.volley.AuthFailureError;
import com.android.volley.Request;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import com.wang.avi.AVLoadingIndicatorView;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import cn.com.mbmpv.trainingonline.R;
import cn.com.mbmpv.trainingonline.adapter.MaskDialogViewPagerAdapter;
import cn.com.mbmpv.trainingonline.application.MyApplication;
import cn.com.mbmpv.trainingonline.bean.ResultGuide;
import cn.com.mbmpv.trainingonline.utils.ConstantSet;
import cn.com.mbmpv.trainingonline.utils.LoadImgUtils;
import cn.com.mbmpv.trainingonline.utils.SaveString;
import me.relex.circleindicator.CircleIndicator;

public class MaskDialog_List extends Dialog {

    /*
     * author:qideli
       date:2016/1/23
       */

   //使用方式
    /*SharedDialog dialog = new SharedDialog(mContext, lists.get(position * 3 -3));

    Window window = dialog.getWindow();
    window.setGravity(Gravity.BOTTOM);
    dialog.setCanceledOnTouchOutside(true);// 设置点击Dialog外部任意区域关闭Dialog
    dialog.show();*/
    public interface OnCustomDialogListener {
        public void back(String name);
    }

    /* TextView mCancel;
     TextView mOk;*/
    Context context;

    List<View> lists;

    //first dialog's data

    ViewPager viewpager;
    ProgressDialog dialog;
    ImageView closeDialo1;

    SaveString save;

//    String fileName;
//    String key;
    String question;
    CircleIndicator indicator;

    AVLoadingIndicatorView loadingIndicatorView;

    MaskDialogViewPagerAdapter adapter;

    public MaskDialog_List(Context context, String question) {
        super(context, R.style.CustomDialogStyle);
//        this.fileName=fileName;
//        this.key=key;
        this.context = context;
        this.question=question;
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.mask_dialog_layout);

        loadingIndicatorView= (AVLoadingIndicatorView) findViewById(R.id.avloadingIndicatorView_BallClipRotatePulse);
//        
        dialog = new  ProgressDialog(context);
//
//        Window window = dialog.getWindow();
//        window.setGravity(Gravity.CENTER);
//        dialog.setCanceledOnTouchOutside(false);// 设置点击Dialog外部任意区域关闭Dialog
        save=new SaveString(context);
//        dialog.show();
        initView();
        initData();
        initEvent();

    }


    public void colseDialog() {
        //往后端保存
//        saveGuideStauts();
        closeDialog2();
    }

    public void closeDialog2(){
//        SaveString save=new SaveString(context);
//        String videoFirst=save.getPhoneNumber(fileName,key);

        MaskDialog_List.this.dismiss();
    }

    /**
    * 创建时间 16/9/1
    * auther Hades
    * 描述  往后端保存新手引导状态
    **/
//    private void saveGuideStauts() {
//        StringRequest rq = new StringRequest(Request.Method.POST, ConstantSet.homeAddress + "main/saveguide?", new Response.Listener<String>() {
//
//            @Override
//            public void onResponse(String response) {
//                // TODO Auto-generated method stub
//                Log.i("SaveGuide", response);
//                //  Toast.makeText(context,response,Toast.LENGTH_SHORT).show();
//                Gson gson = new Gson();
//                ResultGuide guide = gson.fromJson(response, ResultGuide.class);
//                if (guide.getStatus().equalsIgnoreCase("1")){
//                    //保存成功 关闭Dialog
//
//
//                    closeDialog2();
//                }
//
//            }
//        }, new Response.ErrorListener() {
//
//            @Override
//            public void onErrorResponse(VolleyError error) {
//                // TODO Auto-generated method stub
//                Toast.makeText(context, "网络请求失败", Toast.LENGTH_SHORT).show();
//            }
//        }) {
//            @Override
//            protected Map<String, String> getParams() throws AuthFailureError {
//                // TODO Auto-generated method stub
//                Map<String, String> map = new HashMap<String, String>();
//                map.put("guide_code",question);
//                if(ConstantSet.user!=null) {
//                    map.put("user_id", ConstantSet.user.getUid());
//                }
//                map.put("okey", Md5Utils.md5("moocmainsaveguide" +question+ConstantSet.user.getUid() ));
//                return map;
//            }
//        };
//
//
//        MyApplication.getRq().add(rq);
//    }


    private void initView() {

        viewpager = (ViewPager) findViewById(R.id.mask_viewpager);
        closeDialo1= (ImageView)findViewById(R.id.i_know);
    }


    private void initData() {

        getData(question);
    }


    private void initEvent() {

        closeDialo1.setVisibility(View.INVISIBLE);

        closeDialo1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                //强制新手引导
                colseDialog();
            }
        });

        viewpager.setOnPageChangeListener(new ViewPager.OnPageChangeListener() {
            @Override
            public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {
                
            }

            @Override
            public void onPageSelected(int position) {

                if(position==lists.size()-1)
                {
                    closeDialo1.setVisibility(View.VISIBLE);
                    indicator.setVisibility(View.INVISIBLE);
                }else{
                    closeDialo1.setVisibility(View.INVISIBLE);
                    indicator.setVisibility(View.VISIBLE);
                }
                
            }

            @Override
            public void onPageScrollStateChanged(int state) {

            }
        });
    }


    public void getData(final String question) {
        StringRequest rq = new StringRequest(Request.Method.POST, ConstantSet.homeAddress + "main/getguideforce?", new Response.Listener<String>() {
            
            @Override
            public void onResponse(String response) {
                // TODO Auto-generated method stub
                Log.i("Guide", response);
              //  Toast.makeText(context,response,Toast.LENGTH_SHORT).show();
                Log.i("TAG-Guide", ConstantSet.homeAddress + "main/getguide?");
                    try {
                        Gson gson = new Gson();
                        // dialog.colseDialog();
                        ResultGuide result = gson.fromJson(response, new TypeToken<ResultGuide>() {
                        }.getType());

                        if (result.getStatus().equalsIgnoreCase("1")){
                            loadingIndicatorView.setVisibility(View.INVISIBLE);
                            lists=new ArrayList<View>();
                            for(int i=0;i<result.getData().getPlan().size();i++)
                            {
                                View view=View.inflate(context, R.layout.dialog_viewpager_item_bili,null);
                                ImageView img= (ImageView) view.findViewById(R.id.img);

                                LoadImgUtils.setImage(context,result.getData().getPlan().get(i).getStep_image(),img);

                                lists.add(view);

                            }
//                            save.savePhoneNumber(fileName,key,"1");
                            adapter=new MaskDialogViewPagerAdapter(context,lists);

                            viewpager.setAdapter(adapter);

                            indicator = (CircleIndicator)findViewById(R.id.indictor);
                            indicator.setViewPager(viewpager);
                            adapter.registerDataSetObserver(indicator.getDataSetObserver());
                        }

                        else {
                            //新手引导已做过
                            loadingIndicatorView.setVisibility(View.INVISIBLE);
                            viewpager.setVisibility(View.INVISIBLE);
                           closeDialog2();
                        }

                    }catch (Exception e){

                    }
            }
        }, new Response.ErrorListener() {

            @Override
            public void onErrorResponse(VolleyError error) {
                // TODO Auto-generated method stub
                Toast.makeText(context, "网络请求失败", Toast.LENGTH_SHORT).show();
            }
        }) {
            @Override
            protected Map<String, String> getParams() throws AuthFailureError {
                // TODO Auto-generated method stub
                Map<String, String> map = new HashMap<String, String>();
                map.put("guide_code",question);
                map.put("length_radio",((float) MyApplication.getScreenHight()/(float)MyApplication.getScreenWidth())+"");
                if(ConstantSet.user!=null) {
                    map.put("user_id", ConstantSet.user.getUid());
                }
                return map;
            }
        };


        MyApplication.getRq().add(rq);
    }
    
    
}
