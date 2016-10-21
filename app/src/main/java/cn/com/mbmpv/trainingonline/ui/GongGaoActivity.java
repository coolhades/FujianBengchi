package cn.com.mbmpv.trainingonline.ui;

import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.Toast;

import com.android.volley.Request;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.google.gson.Gson;

import java.util.ArrayList;
import java.util.List;

import cn.com.mbmpv.trainingonline.BaseActivity;
import cn.com.mbmpv.trainingonline.R;
import cn.com.mbmpv.trainingonline.adapter.GongGaoListViewAdapter;
import cn.com.mbmpv.trainingonline.application.MyApplication;
import cn.com.mbmpv.trainingonline.bean.GongGaoBean;
import cn.com.mbmpv.trainingonline.utils.ConstantSet;
import cn.com.mbmpv.trainingonline.utils.ShowMaskDialogUtils;


//我的考试页面
public class GongGaoActivity extends BaseActivity {

    
    ListView GongGaoListView;
    
    List<GongGaoBean.DataBean> lists;
    
    GongGaoListViewAdapter adapter;
    
    ImageView back;
    ImageView guide_iv;
    
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_gonggao);
        initView();
        initData();
        initEvent();
        
    }

    @Override
    protected void initView() {

        GongGaoListView = (ListView) findViewById(R.id.gonggao_listview);

        back= (ImageView) findViewById(R.id.back);
        guide_iv = (ImageView) findViewById(R.id.guide_iv);

//        ShowMaskDialogUtils.showMaskDialog(GongGaoActivity.this,"myExamFile","myExam","Guide.Grade.Share");

//        SaveString save=new SaveString(ExamineActivity.this);
//        String videoFirst=save.getPhoneNumber("myExamFile","myExam");
//
//        if(videoFirst==null||videoFirst.equals("")) {
//            MaskDialog dialog1 = new MaskDialog(ExamineActivity.this,"myExamFile","myExam","Guide.Grade.Share");
//
//            Window window1 = dialog1.getWindow();
//            window1.setGravity(Gravity.CENTER);
//            dialog1.setCanceledOnTouchOutside(false);// 设置点击Dialog外部任意区域关闭Dialog
//            dialog1.show();
//        }
    }

    @Override
    protected void initData() {
        
        lists=new ArrayList<>();
        fetchGongGao();
    }

    @Override
    protected void initEvent() {
        


        back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                
                GongGaoActivity.this.finish();
            }
        });

        guide_iv.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                ShowMaskDialogUtils.showMaskDialog_Btn(GongGaoActivity.this,"myExamFile","myExam","Guide.Grade.Share");
            }
        });
    }

//获取考试列表
    public void fetchGongGao(){
        StringRequest rq=new StringRequest(Request.Method.GET, ConstantSet.homeAddress+"main/getnotice?", new Response.Listener<String>() {

            @Override
            public void onResponse(String response) {
                // TODO Auto-generated method stub

                Log.i("TAGTAG公告", response);
                Gson gson = new Gson();
                GongGaoBean bean = gson.fromJson(response, GongGaoBean.class);
                if (bean.getStatus() == 1) {
                    lists.clear();
                    lists = bean.getData();
                    adapter = new GongGaoListViewAdapter(lists, GongGaoActivity.this);
                    adapter.notifyDataSetChanged();
                    GongGaoListView.setAdapter(adapter);
                }
            }
        }, new Response.ErrorListener(){

            @Override
            public void onErrorResponse(VolleyError error) {
                // TODO Auto-generated method stub
                Toast.makeText(GongGaoActivity.this, "网络请求失败", Toast.LENGTH_SHORT).show();

            }});

        MyApplication.getRq().add(rq);
    }

}
