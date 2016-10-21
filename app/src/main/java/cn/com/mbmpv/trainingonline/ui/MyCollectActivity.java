package cn.com.mbmpv.trainingonline.ui;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
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
import com.google.gson.reflect.TypeToken;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import cn.com.mbmpv.trainingonline.BaseActivity;
import cn.com.mbmpv.trainingonline.R;
import cn.com.mbmpv.trainingonline.adapter.MyCollectListViewAdapter;
import cn.com.mbmpv.trainingonline.application.MyApplication;
import cn.com.mbmpv.trainingonline.bean.MyCollect;
import cn.com.mbmpv.trainingonline.bean.ResultMycollect;
import cn.com.mbmpv.trainingonline.utils.ConstantSet;

public class MyCollectActivity extends BaseActivity {


    ListView myCollectlistView;

    //公用Type

    MyCollectListViewAdapter adapter;
    List<MyCollect> lists;
    TextView empty_layout;

    ImageView back;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_my_collect);

        initView();
        initData();
        initEvent();
    }

    @Override
    protected void initView() {

        myCollectlistView = (ListView) findViewById(R.id.my_collect_listview);
        empty_layout = (TextView) findViewById(R.id.empty_layout);

        back = (ImageView) findViewById(R.id.back);
    }

    @Override
    protected void initData() {

        lists = new ArrayList<MyCollect>();

        getData();

        // adapter=new TypeListAdapter(lists,this);

        //myCollectlistView.setAdapter(adapter);

    }

    @Override
    protected void initEvent() {

        back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                MyCollectActivity.this.finish();
            }
        });


        myCollectlistView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {

                ConstantSet.videoTitle = lists.get(i).getCourse_name();
                ConstantSet.course_id = lists.get(i).getImpower_id();

                startActivity(new Intent(MyCollectActivity.this, VideoActivity.class));
            }
        });
    }


    public void getData() {
        StringRequest rq = new StringRequest(Request.Method.POST, ConstantSet.homeAddress + "user/getmyfollowcourse?", new Response.Listener<String>() {

            @Override
            public void onResponse(String response) {
                // TODO Auto-generated method stub

                System.out.print("response  " + response + "    " + response.length());
                //  showShortToast(response);
                try {

                    Gson gson = new Gson();
                    ResultMycollect resultUser = gson.fromJson(response, new TypeToken<ResultMycollect>() {
                    }.getType());

                    if (resultUser.getStatus().equalsIgnoreCase("1")) {
                        lists = resultUser.getData();
                        if (lists.isEmpty()) {
                            empty_layout.setVisibility(View.VISIBLE);
                            myCollectlistView.setVisibility(View.INVISIBLE);
                        }else {
                            adapter = new MyCollectListViewAdapter(lists, MyCollectActivity.this);
                            myCollectlistView.setAdapter(adapter);
                        }
                    }
                } catch (Exception e) {

                }

            }
        }, new Response.ErrorListener() {

            @Override
            public void onErrorResponse(VolleyError error) {
                // TODO Auto-generated method stub
                Toast.makeText(MyCollectActivity.this, "网络请求失败", Toast.LENGTH_SHORT).show();

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
}
