package cn.com.mbmpv.trainingonline.widget;

import android.os.Bundle;
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
import com.google.gson.reflect.TypeToken;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import cn.com.mbmpv.trainingonline.BaseActivity;
import cn.com.mbmpv.trainingonline.R;
import cn.com.mbmpv.trainingonline.adapter.DuihuaListViewAdapter;
import cn.com.mbmpv.trainingonline.application.MyApplication;
import cn.com.mbmpv.trainingonline.bean.QuestionReplyListBean;
import cn.com.mbmpv.trainingonline.utils.ConstantSet;
import cn.com.mbmpv.trainingonline.utils.LoadImgUtils;

public class JPushToDuihuaDialog extends BaseActivity {

    /*
     * author:qideli
       date:2016/1/23
       */
    public interface OnCustomDialogListener {
        public void back(String name);
    }

    /* TextView mCancel;
     TextView mOk;*/

    List<QuestionReplyListBean.DataBean.AnswerBean> lists;
    ListView listView;

    ImageView back;

    DuihuaListViewAdapter adapter;
    ImageView headImg;
    TextView userNick;
    TextView dateText;
    TextView question_title;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.duihua_layout);
        //  setTitle(name);

        initView();
        initData();
        initEvent();


    }

    protected void initView() {

        listView = (ListView) findViewById(R.id.list_view);
        back= (ImageView) findViewById(R.id.back);

        headImg= (ImageView) findViewById(R.id.head_img);
        userNick= (TextView) findViewById(R.id.user_nick);
        dateText= (TextView) findViewById(R.id.date);

        question_title = (TextView) findViewById(R.id.question_title);
    }

    protected void initData() {

        lists = new ArrayList<>();

        getData("1");
    }

    protected void initEvent() {
        back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                JPushToDuihuaDialog.this.finish();
            }
        });
    }

    public void getData(final String page) {
        StringRequest rq = new StringRequest(Request.Method.POST, ConstantSet.homeAddress + "comment/getanswerlist?", new Response.Listener<String>() {

            @Override
            public void onResponse(String response) {
                // TODO Auto-generated method stub
                try {
                    Gson gson = new Gson();
                    QuestionReplyListBean result = gson.fromJson(response, new TypeToken<QuestionReplyListBean>() {
                    }.getType());
                    if (result.getStatus() == 1) {

                        LoadImgUtils.setImage(JPushToDuihuaDialog.this,result.getData().getAnswer().get(0).getByReplyUserInfo().getAvatar(),headImg);
                        userNick.setText(result.getData().getAnswer().get(0).getByReplyUserInfo().getNickname());
                        dateText.setText(result.getData().getQuestions().getCdate());
                        question_title.setText(result.getData().getQuestions().getContent());


                        lists = result.getData().getAnswer();
                        adapter = new DuihuaListViewAdapter(lists, JPushToDuihuaDialog.this);
                        listView.setAdapter(adapter);
                    }
                }catch (Exception e){

                }

            }
        }, new Response.ErrorListener() {

            @Override
            public void onErrorResponse(VolleyError error) {
                // TODO Auto-generated method stub
                Toast.makeText(JPushToDuihuaDialog.this, "网络请求失败", Toast.LENGTH_SHORT).show();
            }
        }) {
            @Override
            protected Map<String, String> getParams() throws AuthFailureError {
                // TODO Auto-generated method stub
                Map<String, String> map = new HashMap<String, String>();
                map.put("obj_id",ConstantSet.Target_id);
                if(ConstantSet.user!=null) {
                    map.put("uid", ConstantSet.user.getUid());
                }
                map.put("page", page);
                return map;
            }
        };

        MyApplication.getRq().add(rq);
    }


}
