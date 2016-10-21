package cn.com.mbmpv.trainingonline.view;

import android.content.Context;
import android.content.Intent;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;
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
import cn.com.mbmpv.trainingonline.adapter.JiangshiListAdapter;
import cn.com.mbmpv.trainingonline.application.MyApplication;
import cn.com.mbmpv.trainingonline.bean.ResultTeacher;
import cn.com.mbmpv.trainingonline.bean.Teacher;
import cn.com.mbmpv.trainingonline.ui.JiangshiActivity;
import cn.com.mbmpv.trainingonline.utils.ConstantSet;

/**
 * Created by jiuzheyange on 2016/8/18.
 */
public class KechengView2 {
    Context mContext;
    View mView;

    ListView listView2;

    List<Teacher> list2;


    JiangshiListAdapter adapter2;


    public KechengView2(Context mContext) {
        this.mContext = mContext;
    }

    public View getView() {
        mView = View.inflate(mContext, R.layout.kecheng_view2, null);
        initView();
        initData();
        initEvent();


        return mView;
    }

    private void initView() {
        listView2= (ListView) mView.findViewById(R.id.kecheng_listview2);
        listView2.addHeaderView(View.inflate(mContext,R.layout.mingshi_listview_head,null));
    }


    private void initData() {

        list2=new ArrayList<Teacher>();
        getDataMingshi("1","10");

    }


    private void initEvent() {

        listView2.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
                if(list2!=null&&list2.size()>0) {
                    ConstantSet.teacher_name=list2.get(i-1).getTeacher_name();
                    ConstantSet.teacher_url=list2.get(i-1).getTeacher_avatar();
                    ConstantSet.teacher_id = list2.get(i-1).getTeacher_id();
                }
                mContext.startActivity(new Intent(mContext, JiangshiActivity.class));
            }
        });
    }


    public void getDataMingshi(final String page, final String pagesize) {
        StringRequest rq = new StringRequest(Request.Method.POST, ConstantSet.homeAddress + "course/getallteacher?", new Response.Listener<String>() {

            @Override
            public void onResponse(String response) {
                // TODO Auto-generated method stub

                System.out.print("response  " + response + "    " + response.length());

                if (response.length() > 100) {

                    Gson gson = new Gson();
                    Toast.makeText(mContext,"老师"+response,Toast.LENGTH_LONG).show();

                    ResultTeacher resultTeacher = gson.fromJson(response, new TypeToken<ResultTeacher>() {
                    }.getType());

//                    list1=resultLessonKc.getData().getList();
//                    adapter1=new KechengListAdapter(list1,getActivity());
//                    listView1.setAdapter(adapter1);

                    list2 = resultTeacher.getData().getList();
                    adapter2 = new JiangshiListAdapter(list2, mContext);
                    listView2.setAdapter(adapter2);
                }
            }
        }, new Response.ErrorListener() {

            @Override
            public void onErrorResponse(VolleyError error) {
                // TODO Auto-generated method stub
                Toast.makeText(mContext, "网络请求失败1", Toast.LENGTH_SHORT).show();
            }
        }) {
            @Override
            protected Map<String, String> getParams() throws AuthFailureError {
                // TODO Auto-generated method stub
                Map<String, String> map = new HashMap<String, String>();
                map.put("page", page);
                map.put("pagesize", pagesize);

                return map;
            }
        };

        MyApplication.getRq().add(rq);
    }
}
