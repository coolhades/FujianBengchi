package cn.com.mbmpv.trainingonline.ui;

import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.StaggeredGridLayoutManager;
import android.view.Gravity;
import android.view.View;
import android.view.Window;
import android.widget.AdapterView;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import com.android.volley.AuthFailureError;
import com.android.volley.Request;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.cjj.MaterialRefreshLayout;
import com.cjj.MaterialRefreshListener;
import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import cn.com.mbmpv.trainingonline.R;
import cn.com.mbmpv.trainingonline.adapter.TypeDialogListAdapter;
import cn.com.mbmpv.trainingonline.adapter.TypeListAdapter;
import cn.com.mbmpv.trainingonline.application.MyApplication;
import cn.com.mbmpv.trainingonline.bean.ResultSearchLesson;
import cn.com.mbmpv.trainingonline.bean.ResultTypeBean;
import cn.com.mbmpv.trainingonline.bean.SearchLesson;
import cn.com.mbmpv.trainingonline.bean.TypeBean;
import cn.com.mbmpv.trainingonline.utils.ConstantSet;
import cn.com.mbmpv.trainingonline.widget.ProgressDialog;

public class TypeActivity extends AppCompatActivity {

    RecyclerView listView;
    List<SearchLesson> lists;

    ListView dialogListView;

    TypeListAdapter adapter;

    List<TypeBean> dialogList;
    TypeDialogListAdapter dialogAdapter;

    LinearLayout typeDialog;
    TextView titleText;
    ImageView titleImg;
    ImageView closeDialog;

    ImageView backBt;
    ProgressDialog dialog;
    MaterialRefreshLayout materialRefreshLayout;
    
    TextView renqiTextView;
    TextView shijianTextView;
    TextView jiageTextView;

    ImageView renqiImg;
    ImageView shijianImg;
    ImageView jiageImg;


    boolean flag1=true;
    boolean flag2=true;
    boolean flag3=true;
    
    String order_type="visit";
    String order_sort="desc";
    
    ImageView mSearch;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_type);

        initView();
        initData();
        initEvent();
    }

    private void initView() {

        listView = (RecyclerView) findViewById(R.id.type_listview);
        dialogListView = (ListView) findViewById(R.id.type_dialog_listview);

        typeDialog = (LinearLayout) findViewById(R.id.type_dialog);

        titleText = (TextView) findViewById(R.id.title_text);
        titleImg = (ImageView) findViewById(R.id.title_img);
        closeDialog = (ImageView) findViewById(R.id.type_dialog_close);

        backBt = (ImageView) findViewById(R.id.back);
        
        renqiTextView= (TextView) findViewById(R.id.renqi_text);
        shijianTextView= (TextView) findViewById(R.id.shijian_text);
        jiageTextView= (TextView) findViewById(R.id.jiage_text);

        renqiImg= (ImageView) findViewById(R.id.renqi_img);
        shijianImg= (ImageView) findViewById(R.id.shijian_img);
        jiageImg= (ImageView) findViewById(R.id.jiage_img);

        mSearch= (ImageView) findViewById(R.id.search_bt);
        

        materialRefreshLayout = (MaterialRefreshLayout) findViewById(R.id.refresh);
        materialRefreshLayout.setWaveColor(Color.parseColor("#55FFFFFF"));
        materialRefreshLayout.setIsOverLay(true);
        materialRefreshLayout.setWaveShow(true);
        

        if (ConstantSet.cancel.equals("search")) {
            titleImg.setVisibility(View.GONE);
            titleText.setEnabled(false);
            titleText.setText("搜索结果");
            if(ConstantSet.keyWord.equals(""))
            {
                titleText.setText("全部");
            }
        }

        if(ConstantSet.cancel.equals("kecheng"))
        {
            titleText.setText(ConstantSet.keyWord);
        }

        dialog = new ProgressDialog(this);

        Window window = dialog.getWindow();
        window.setGravity(Gravity.CENTER);
        dialog.setCanceledOnTouchOutside(false);// 设置点击Dialog外部任意区域关闭Dialog
        dialog.show();
    }


    private void initData() {

        dialogList = new ArrayList<TypeBean>();

        lists = new ArrayList<SearchLesson>();
        getData(ConstantSet.keyWord, "1", "visit", "asc", ConstantSet.tag);

        setDialogData();

    }

    private void initEvent() {


        titleText.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                typeDialog.setVisibility(View.VISIBLE);
            }
        });

        titleImg.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                typeDialog.setVisibility(View.VISIBLE);
            }
        });

        closeDialog.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                typeDialog.setVisibility(View.INVISIBLE);
            }
        });


        backBt.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {


                TypeActivity.this.finish();
            }
        });


        dialogListView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {

                View view1 = dialogListView.getChildAt(i);
                TextView text = (TextView) view1.findViewById(R.id.title_name);
                dialogAdapter.setNormal();
                text.setBackgroundResource(R.drawable.login_bg);
                text.setTextColor(Color.parseColor("#FFFFFF"));
                
                ConstantSet.cancel="kecheng";
                ConstantSet.tag = dialogList.get(i).getTag_item_id();
                getData("","1", "visit", "asc",dialogList.get(i).getTag_item_id());
                titleText.setText(dialogList.get(i).getTag_item_name());

                typeDialog.setVisibility(View.INVISIBLE);
                
                
            }
        });

        materialRefreshLayout.setMaterialRefreshListener(new MaterialRefreshListener() {
            @Override
            public void onRefresh(MaterialRefreshLayout materialRefreshLayout) {
               // Data(final String keyword, final String page, final String order_type, final String order_sort, final String tag) {
                getData(ConstantSet.keyWord,"1",order_type,order_sort,ConstantSet.tag);
                
                
            }

            @Override
            public void onRefreshLoadMore(MaterialRefreshLayout materialRefreshLayout) {
                //load more refreshing...
            }
        });
        
        
        renqiTextView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if(flag1==true)
                {
                    flag1=false;
                    order_sort="asc";
                    
                    renqiImg.setImageResource(R.mipmap.up_img);
                }
                else
                {
                    flag1=true;
                    order_sort="desc";

                    renqiImg.setImageResource(R.mipmap.down_img);
                }

                order_type="visit";
                renqiTextView.setTextColor(Color.parseColor("#333333"));
                shijianTextView.setTextColor(Color.parseColor("#AEAEAE"));
                jiageTextView.setTextColor(Color.parseColor("#AEAEAE"));

                getData(ConstantSet.keyWord,"1", order_type, order_sort,ConstantSet.tag);

            }
        });


        shijianTextView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                if(flag2==true)
                {
                    flag2=false;
                    order_sort="asc";
                    shijianImg.setImageResource(R.mipmap.up_img);
                }
                else
                {
                    flag2=true;
                    order_sort="desc";
                    shijianImg.setImageResource(R.mipmap.down_img);
                }

                order_type="time";

                renqiTextView.setTextColor(Color.parseColor("#AEAEAE"));
                shijianTextView.setTextColor(Color.parseColor("#333333"));
                jiageTextView.setTextColor(Color.parseColor("#AEAEAE"));

                getData(ConstantSet.keyWord,"1", order_type, order_sort,ConstantSet.tag);
            }
        });


        jiageTextView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                if(flag3==true)
                {
                    flag3=false;
                    order_sort="asc";
                    jiageImg.setImageResource(R.mipmap.up_img);
                }
                else
                {
                    flag3=true;
                    order_sort="desc";
                    jiageImg.setImageResource(R.mipmap.down_img);
                }

                order_type= "price";

                jiageTextView.setTextColor(Color.parseColor("#333333"));
                renqiTextView.setTextColor(Color.parseColor("#AEAEAE"));
                shijianTextView.setTextColor(Color.parseColor("#AEAEAE"));

                getData(ConstantSet.keyWord,"1", order_type, order_sort,ConstantSet.tag);
            }
        });


        mSearch.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(TypeActivity.this,SearchActivity.class));
                
                TypeActivity.this.finish();
            }
        });

// refresh complet
// materialRefreshLayout.finishRefresh();

// load more refresh complete 
       // materialRefreshLayout.finishRefreshLoadMore();

    }

    //排序
    public void getData(final String keyword, final String page, final String order_type, final String order_sort, final String tag) {
        StringRequest rq = new StringRequest(Request.Method.POST, ConstantSet.homeAddress + "course/search?", new Response.Listener<String>() {

            @Override
            public void onResponse(String response) {
                // TODO Auto-generated method stub

                // System.out.print("response  "+response+"    "+response.length());
                // Toast.makeText(TypeActivity.this,response+"",Toast.LENGTH_LONG).show();
                if (response.length() > 50) {

                    // listView.refreshComplete();
                    Gson gson = new Gson();
                    ResultSearchLesson result = gson.fromJson(response, new TypeToken<ResultSearchLesson>() {
                    }.getType());
                    lists.clear();
                    lists.addAll(result.getData().getList());
                    if (adapter == null) {
                        dialog.colseDialog();
                        adapter = new TypeListAdapter(TypeActivity.this, lists);
                        listView.setAdapter(adapter);

                        listView.setLayoutManager(new StaggeredGridLayoutManager(1, StaggeredGridLayoutManager.VERTICAL));
                    } else {
                        materialRefreshLayout.finishRefresh();
                        adapter.notifyDataSetChanged();
                    }

                }

            }
        }, new Response.ErrorListener() {

            @Override
            public void onErrorResponse(VolleyError error) {
                // TODO Auto-generated method stub
                Toast.makeText(TypeActivity.this, "网络请求失败", Toast.LENGTH_SHORT).show();

            }
        }) {
            @Override
            protected Map<String, String> getParams() throws AuthFailureError {
                // TODO Auto-generated method stub
                Map<String, String> map = new HashMap<String, String>();

                if (ConstantSet.cancel.equals("search")) {
                    map.put("keyword", keyword);
                } else if (ConstantSet.cancel.equals("kecheng")) {
                    map.put("tag", tag);
                }
                map.put("page", page);
                map.put("pagesize", "100");
                map.put("order_type", order_type);
                map.put("order_sort", order_sort);

                return map;
            }
        };

        MyApplication.getRq().add(rq);
    }


    private void setDialogData() {

        StringRequest rq = new StringRequest(Request.Method.POST, ConstantSet.homeAddress + "main/gettags?", new Response.Listener<String>() {

            @Override
            public void onResponse(String response) {
                // TODO Auto-generated method stub

                // System.out.print("response  "+response+"    "+response.length());
                //Toast.makeText(TypeActivity.this,response+"",Toast.LENGTH_LONG).show();
                if (response.length() > 50) {

                    Gson gson = new Gson();
                    ResultTypeBean result = gson.fromJson(response, new TypeToken<ResultTypeBean>() {
                    }.getType());

                    dialogList.clear();
                    dialogList.addAll(result.getData());
                    dialogAdapter = new TypeDialogListAdapter(result.getData(), TypeActivity.this);
                    dialogListView.setAdapter(dialogAdapter);
                }

            }
        }, new Response.ErrorListener() {

            @Override
            public void onErrorResponse(VolleyError error) {
                // TODO Auto-generated method stub
                Toast.makeText(TypeActivity.this, "网络请求失败", Toast.LENGTH_SHORT).show();

            }
        }) {
          /*  @Override
            protected Map<String, String> getParams() throws AuthFailureError {
                // TODO Auto-generated method stub
                Map<String,String> map=new HashMap<String, String>();
                map.put("keyword",keyword);
                //  map.put("tag",tag);
                map.put("page",page);
                map.put("pagesize","10");
                map.put("order_type",order_type);
                map.put("order_sort",order_sort);

                return map;
            }*/
        };

        MyApplication.getRq().add(rq);
    }
}