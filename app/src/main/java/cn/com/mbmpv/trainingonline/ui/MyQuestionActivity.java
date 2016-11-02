package cn.com.mbmpv.trainingonline.ui;

import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.ImageView;
import android.widget.ListView;
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
import cn.com.mbmpv.trainingonline.adapter.MyQuestionListViewAdapter;
import cn.com.mbmpv.trainingonline.application.MyApplication;
import cn.com.mbmpv.trainingonline.bean.MyQuestionBean;
import cn.com.mbmpv.trainingonline.bean.Question;
import cn.com.mbmpv.trainingonline.utils.ConstantSet;

public class MyQuestionActivity extends BaseActivity {


    ListView myQuestionListView;
    MyQuestionListViewAdapter adapter;
    List<Question> lists;
    TextView empty_layout;

    ImageView back;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_my_question);

        initView();
        initData();
        initEvent();
    }

    @Override
    protected void initView() {
        myQuestionListView = (ListView) findViewById(R.id.my_question_listview);
        back = (ImageView) findViewById(R.id.back);
        empty_layout = (TextView) findViewById(R.id.empty_layout);

    }

    @Override
    protected void initData() {

        lists = new ArrayList<Question>();
        getData();

    }

    @Override
    protected void initEvent() {
        back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                MyQuestionActivity.this.finish();
            }
        });
    }


    public void getData() {
        StringRequest rq = new StringRequest(Request.Method.POST, ConstantSet.homeAddress + "user/getmyquestionsnew?", new Response.Listener<String>() {

            @Override
            public void onResponse(String response) {
                // TODO Auto-generated method stub
                Log.i("MyQuestion", response);
                try {

                    Gson gson = new Gson();
                    MyQuestionBean resultUser = gson.fromJson(response, MyQuestionBean.class);
                    if (resultUser.getStatus() == 1) {
                        if (resultUser.getData().isEmpty()) {
                            empty_layout.setVisibility(View.VISIBLE);
                            myQuestionListView.setVisibility(View.INVISIBLE);
                        } else {
                            adapter = new MyQuestionListViewAdapter(resultUser.getData(), MyQuestionActivity.this);
                            myQuestionListView.setAdapter(adapter);
                        }
                    }
                } catch (Exception e) {
                    Toast.makeText(MyQuestionActivity.this, "网络请求失败", Toast.LENGTH_SHORT).show();
                }
            }
        }, new Response.ErrorListener() {

            @Override
            public void onErrorResponse(VolleyError error) {
                // TODO Auto-generated method stub
                Toast.makeText(MyQuestionActivity.this, "网络请求失败", Toast.LENGTH_SHORT).show();
            }
        }) {
            @Override
            protected Map<String, String> getParams() throws AuthFailureError {
                // TODO Auto-generated method stub
                Map<String, String> map = new HashMap<String, String>();
//                map.put("user_id", "FJBC-ONLINE-User-262");
                if (ConstantSet.user != null) {
                    map.put("user_id", ConstantSet.user.getUid());
                }
                return map;
            }
        };

        MyApplication.getRq().add(rq);
    }
}
