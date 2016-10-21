package cn.com.mbmpv.trainingonline.view;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.StaggeredGridLayoutManager;
import android.view.View;
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
import cn.com.mbmpv.trainingonline.adapter.TypeListAdapter;
import cn.com.mbmpv.trainingonline.application.MyApplication;
import cn.com.mbmpv.trainingonline.bean.ResultSearchLesson;
import cn.com.mbmpv.trainingonline.bean.SearchLesson;
import cn.com.mbmpv.trainingonline.utils.ConstantSet;

/**
 * Created by jiuzheyange on 2016/8/18.
 */
public class JiangshiView1 {
    
    Context mContext;
    View mView;
    
    List<SearchLesson> lists;
    TypeListAdapter kedanAdapter;


    RecyclerView listView;

    public JiangshiView1(Context mContext) {
        this.mContext = mContext;
    }
    
    
    public View getView()
    {
        mView=View.inflate(mContext, R.layout.jiangshi_view1,null);
        initView();
        initData();
        initEvent();
        return mView;
    }

    private void initView() {

        lists=new ArrayList<SearchLesson>();
        listView= (RecyclerView) mView.findViewById(R.id.list_view);
    }


    private void initData() {

        getData();
    }

    private void initEvent() {
    }


    public void getData()
    {
        StringRequest rq=new StringRequest(Request.Method.POST, ConstantSet.homeAddress+"course/getteacheritem?", new Response.Listener<String>() {

            @Override
            public void onResponse(String response) {
                // TODO Auto-generated method stub

                System.out.print("response  "+response+"    "+response.length());
                if(response.length()>40) {

                    Gson gson = new Gson();
                    ResultSearchLesson result = gson.fromJson(response, new TypeToken<ResultSearchLesson>() {
                    }.getType());
                    if (result.getStatus().equals("1")) {
                        
                        lists=result.getData().getList();
                        kedanAdapter=new TypeListAdapter(mContext,lists);
                        
                       listView.setAdapter(kedanAdapter);
                        listView.setLayoutManager(new StaggeredGridLayoutManager(1, StaggeredGridLayoutManager.VERTICAL));
                        
                        
                    }
                }
                else
                {
                    //showShortToast("账户或密码错误");
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
                map.put("teacher_id","8DCE9F20F6D74B2A8748BA3F16DD2057");
                return map;
            }
        };

        MyApplication.getRq().add(rq);
    }
}
