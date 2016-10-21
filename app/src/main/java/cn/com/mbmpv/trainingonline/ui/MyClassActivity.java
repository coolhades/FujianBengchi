package cn.com.mbmpv.trainingonline.ui;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
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

import cn.com.mbmpv.trainingonline.R;
import cn.com.mbmpv.trainingonline.adapter.MyClassListViewAdapter;
import cn.com.mbmpv.trainingonline.application.MyApplication;
import cn.com.mbmpv.trainingonline.bean.MyClass;
import cn.com.mbmpv.trainingonline.bean.ResultMyClass;
import cn.com.mbmpv.trainingonline.utils.ConstantSet;

public class MyClassActivity extends AppCompatActivity {


    ListView listView;
    List<MyClass> lists;
    MyClassListViewAdapter adapter;
    
    ImageView back;

    TextView empty_layout;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_my_class);

        initView();
        initData();
        initEvent();
    }

    private void initView() {

        listView = (ListView) findViewById(R.id.my_class_listview);
        back= (ImageView) findViewById(R.id.back);
        empty_layout = (TextView) findViewById(R.id.empty_layout);
    }


    private void initData() {

        lists = new ArrayList<MyClass>();
        getData();
    }

    private void initEvent() {

        back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                
                MyClassActivity.this.finish();
            }
        });
        
        
        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {

                ConstantSet.videoTitle=lists.get(i).getCourse_name();
                ConstantSet.course_id=lists.get(i).getImpower_id();

                Intent im = new Intent(MyClassActivity.this, VideoActivity.class);
                im.putExtra("My", "My");
                startActivity(im);
            }
        });
    }


    public void getData() {
        StringRequest rq = new StringRequest(Request.Method.POST, ConstantSet.homeAddress + "user/getmycourse?", new Response.Listener<String>() {

            @Override
            public void onResponse(String response) {
                // TODO Auto-generated method stub
                try {
                    Gson gson = new Gson();
                    ResultMyClass resultUser = gson.fromJson(response, new TypeToken<ResultMyClass>() {
                    }.getType());

                    if (resultUser.getStatus().equals("1")) {
                        lists = resultUser.getData();
                        if (lists.isEmpty()){
                            empty_layout.setVisibility(View.VISIBLE);
                            listView.setVisibility(View.INVISIBLE);
                        }else {
                            adapter = new MyClassListViewAdapter(lists, MyClassActivity.this);
                            listView.setAdapter(adapter);
                        }
                    }
                }catch (Exception e){

                }
            }
        }, new Response.ErrorListener() {

            @Override
            public void onErrorResponse(VolleyError error) {
                // TODO Auto-generated method stub
                Toast.makeText(MyClassActivity.this, "网络请求失败", Toast.LENGTH_SHORT).show();

            }
        }) {
            @Override
            protected Map<String, String> getParams() throws AuthFailureError {
                // TODO Auto-generated method stub
                Map<String, String> map = new HashMap<String, String>();
                map.put("user_id", ConstantSet.user.getUid());
                return map;
            }
        };

        MyApplication.getRq().add(rq);
    }

}
