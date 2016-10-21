package cn.com.mbmpv.trainingonline.ui;

import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.android.volley.AuthFailureError;
import com.android.volley.Request;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.google.gson.Gson;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import cn.com.mbmpv.trainingonline.BaseActivity;
import cn.com.mbmpv.trainingonline.R;
import cn.com.mbmpv.trainingonline.application.MyApplication;
import cn.com.mbmpv.trainingonline.bean.WorkInfoBean;
import cn.com.mbmpv.trainingonline.utils.ConstantSet;

/**
 * Created by Hades on 2016/10/19.
 */

public class WorkInfoActivity extends BaseActivity {

    TextView content;
    TextView content2;
    TextView content3;
    TextView content4;
    ImageView back;
    List<WorkInfoBean.DataBean> beanList;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.workinfo_layout);
        initView();
        initData();
        initEvent();
    }

    @Override
    protected void initView() {
        content = (TextView) findViewById(R.id.content);
        content2 = (TextView) findViewById(R.id.content2);
        content3 = (TextView) findViewById(R.id.content3);
        content4 = (TextView) findViewById(R.id.content4);
        back = (ImageView) findViewById(R.id.back);
        beanList = new ArrayList<>();
    }

    @Override
    protected void initData() {
        getWorkInfo();
    }

    private void getWorkInfo() {
        StringRequest rq = new StringRequest(Request.Method.POST, ConstantSet.homeAddress + "user/getworkinfo?", new Response.Listener<String>() {

            @Override
            public void onResponse(String response) {
                // TODO Auto-generated method stub
                try {
                    Gson gson = new Gson();
                    Log.d("TAG-WorkInfo", response);
                    WorkInfoBean workInfoBean = gson.fromJson(response, WorkInfoBean.class);
                    if (workInfoBean.getStatus() == 1){
                        beanList = workInfoBean.getData();
                        initContent(beanList);
                    }else {
                        content.setText(workInfoBean.getMessage());
                    }

                }catch (Exception e){

                }
            }
        }, new Response.ErrorListener() {

            @Override
            public void onErrorResponse(VolleyError error) {
                // TODO Auto-generated method stub
                Toast.makeText(WorkInfoActivity.this, "网络请求失败", Toast.LENGTH_SHORT).show();

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

    private void initContent(List<WorkInfoBean.DataBean> beanList) {
        if (beanList.isEmpty()){
            return;
        }
        for (int i = 0; i<beanList.size(); i++){

            String type = beanList.get(i).getDep_type();
            if (type.equalsIgnoreCase("1")){
                content.setText(beanList.get(i).getDep_name());
            }else if (type.equalsIgnoreCase("11")){
                content2.setText(beanList.get(i).getDep_name());
            }else if (type.equalsIgnoreCase("21")){
                content3.setText(beanList.get(i).getDep_name());
            }else if (type.equalsIgnoreCase("31")){
                content4.setText(beanList.get(i).getDep_name());
            }

        }
    }

    @Override
    protected void initEvent() {
        back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                WorkInfoActivity.this.finish();
            }
        });
    }
}
