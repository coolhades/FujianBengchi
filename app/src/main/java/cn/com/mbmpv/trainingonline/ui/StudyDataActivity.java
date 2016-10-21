package cn.com.mbmpv.trainingonline.ui;

import android.graphics.Color;
import android.os.Bundle;
import android.support.v4.widget.SwipeRefreshLayout;
import android.util.Log;
import android.view.Gravity;
import android.view.View;
import android.view.Window;
import android.widget.ExpandableListView;
import android.widget.ImageView;
import android.widget.Toast;

import com.android.volley.Request;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

import java.util.ArrayList;
import java.util.List;

import cn.com.mbmpv.trainingonline.BaseActivity;
import cn.com.mbmpv.trainingonline.R;
import cn.com.mbmpv.trainingonline.adapter.StudyDataListViewAdapter;
import cn.com.mbmpv.trainingonline.application.MyApplication;
import cn.com.mbmpv.trainingonline.bean.ClassResultDetail;
import cn.com.mbmpv.trainingonline.bean.JieBean;
import cn.com.mbmpv.trainingonline.bean.ZhangBean;
import cn.com.mbmpv.trainingonline.utils.ConstantSet;
import cn.com.mbmpv.trainingonline.widget.PDFWebViewDialog;


/**
 * Created by Hades on 16/9/1.
 * 学习资料
 */
public class StudyDataActivity extends BaseActivity {

    ExpandableListView studydata_listview;
    ClassResultDetail resultLessonKc;

    List<ZhangBean> parentLists;
    List<List<JieBean>>  childLists;//后端获取
    List<ZhangBean> zhangBeanList;

    ImageView back_left;

    SwipeRefreshLayout refresh_layout;
    private boolean isRefresh = false;//是否刷新中

    StudyDataListViewAdapter adapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.studydata_layout);

        initView();
        initData();
        initEvent();
    }

    @Override
    protected void initView() {

        studydata_listview = (ExpandableListView) findViewById(R.id.studydata_listview);

        back_left = (ImageView) findViewById(R.id.back);

        refresh_layout = (SwipeRefreshLayout) findViewById(R.id.refresh_layout);
        //上面的方法已经废弃
        refresh_layout.setColorSchemeColors(Color.BLUE,
                Color.GREEN,
                Color.YELLOW,
                Color.RED);
        // 设置手指在屏幕下拉多少距离会触发下拉刷新
        refresh_layout.setDistanceToTriggerSync(300);
        // 设定下拉圆圈的背景
        refresh_layout.setProgressBackgroundColorSchemeColor(Color.WHITE);
        // 设置圆圈的大小
        refresh_layout.setSize(SwipeRefreshLayout.LARGE);


    }

    @Override
    protected void initData() {
        parentLists=new ArrayList<ZhangBean>();
        childLists=new  ArrayList<List<JieBean>>();
        getMyData();

//        parentLists=zhangBeanList;
//        for(int i=0;i<parentLists.size();i++)
//        {
//            childLists.add(zhangBeanList.get(i).getChild());
//        }
//
//        if(parentLists!=null&&parentLists.size()>0) {
//
//            adapter = new StudyDataListViewAdapter(StudyDataActivity.this, parentLists, childLists);
//
//            studydata_listview.setAdapter(adapter);
//
//            for (int i = 0; i < parentLists.size(); i++) {
//                studydata_listview.expandGroup(i);
//            }
//
//        }
    }



    @Override
    protected void initEvent() {

        back_left.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                StudyDataActivity.this.finish();
            }
        });

        refresh_layout.setOnRefreshListener(new SwipeRefreshLayout.OnRefreshListener() {
            @Override
            public void onRefresh() {
                //检查是否处于刷新状态
                if (!isRefresh) {
                    isRefresh = true;

                    //显示或隐藏刷新进度条
                    refresh_layout.setRefreshing(false);
                    //修改adapter的数据
                    parentLists.clear();
                    childLists.clear();
                    getMyData();
                    isRefresh = false;

                    //模拟加载网络数据，这里设置4秒，正好能看到4色进度条
//                    new Handler().postDelayed(new Runnable() {
//                        public void run() {
//
//                            //显示或隐藏刷新进度条
//                            refresh_layout.setRefreshing(false);
//                            //修改adapter的数据
//                            fetchStudyData();
//
////                            data.add("这是新添加的数据");
////                            mAdapter.notifyDataSetChanged();
//
//                            isRefresh = false;
//                        }
//                    }, 4000);
                }
            }
        });

        studydata_listview.setOnChildClickListener(new ExpandableListView.OnChildClickListener() {
            @Override
            public boolean onChildClick(ExpandableListView expandableListView, View view, int i, int i1, long l) {
                if (!childLists.get(i).get(i1).getSource().isEmpty()) {
                    PDFWebViewDialog dialog = new PDFWebViewDialog(StudyDataActivity.this, null,
                            "http://training-online-file.mbmpv.com.cn/oper/pdfjs/web/viewer.html?file=" +
                                    childLists.get(i).get(i1).getSource().get(0).getDoc_url(),
                            childLists.get(i).get(i1).getNode_caption(),
                            0, null, null, "");
                    Log.i("TAG-PDF", "http://training-online-file.mbmpv.com.cn/oper/pdfjs/web/viewer.html?file=" + childLists.get(i).get(i1).getSource().get(0).getDoc_url());
                    Window window = dialog.getWindow();
                    window.setGravity(Gravity.BOTTOM);
                    dialog.setCanceledOnTouchOutside(true);// 设置点击Dialog外部任意区域关闭Dialog
                    dialog.show();

                }
                return false;
            }
        });


        studydata_listview.setOnGroupClickListener(new ExpandableListView.OnGroupClickListener() {
            @Override
            public boolean onGroupClick(ExpandableListView expandableListView, View view, int i, long l) {
                if (!parentLists.get(i).getSource().isEmpty()){

                    PDFWebViewDialog dialog = new PDFWebViewDialog(StudyDataActivity.this, null,
                            "http://training-online-file.mbmpv.com.cn/oper/pdfjs/web/viewer.html?file="+
                                    parentLists.get(i).getSource().get(0).getDoc_url(),
                            parentLists.get(i).getNode_caption(),
                            0, null, null,"");
                    Log.i("TAG-PDF",  "http://training-online.mbmpv.com.cn/pdfjs/web/viewer.html?file="+parentLists.get(i).getSource().get(0).getDoc_url());
                    Window window = dialog.getWindow();
                    window.setGravity(Gravity.BOTTOM);
                    dialog.setCanceledOnTouchOutside(true);// 设置点击Dialog外部任意区域关闭Dialog
                    dialog.show();

                }

                return true;
            }
        });
    }

    //获取对应课程资料
    //我的页面跳转  这个OK的
    public void getMyData() {
        StringRequest rq = new StringRequest(Request.Method.GET, ConstantSet.homeAddress + "course/getdownload?class_id="+ConstantSet.class_id, new Response.Listener<String>() {

            @Override
            public void onResponse(String response) {
                // TODO Auto-generated method stub

                Log.i("Video_info", response);
                try{
                    Gson gson = new Gson();
                    resultLessonKc = gson.fromJson(response, new TypeToken<ClassResultDetail>() {
                    }.getType());
                    //showShortToast(response);

                    if (resultLessonKc.getStatus() == 1){
                        Log.i("Video_info", "进入status");
                        zhangBeanList = resultLessonKc.getData();//获取到的CenterDetail中的section

                        parentLists=zhangBeanList;
                        for(int i=0;i<parentLists.size();i++)
                        {
                            childLists.add(zhangBeanList.get(i).getChild());
                        }

                        if(parentLists!=null&&parentLists.size()>0) {

                        adapter = new StudyDataListViewAdapter(StudyDataActivity.this, parentLists, childLists);

                        studydata_listview.setAdapter(adapter);

                        for (int i = 0; i < parentLists.size(); i++) {
                        studydata_listview.expandGroup(i);
                            }
                        }
                    }

                }catch (Exception e){

                }

            }
        }, new Response.ErrorListener() {

            @Override
            public void onErrorResponse(VolleyError error) {
                // TODO Auto-generated method stub
                Toast.makeText(StudyDataActivity.this, "网络请求失败1", Toast.LENGTH_SHORT).show();
            }
        });
//        {
//            @Override
//            protected Map<String, String> getParams() throws AuthFailureError {
//                // TODO Auto-generated method stub
//                Map<String, String> map = new HashMap<String, String>();
//                map.put("class_id", "E648294F0F6641C7AA5F73E6424CC427");//后期改成impower_id
////                if (ConstantSet.user != null) {
////                    map.put("user_id", ConstantSet.user.getUid());
////                }
////                map.put("okey", Md5Utils.md5("mooccoursegetdetail" + ConstantSet.course_id));
//                return map;
//            }
//        };

        MyApplication.getRq().add(rq);
    }




}
