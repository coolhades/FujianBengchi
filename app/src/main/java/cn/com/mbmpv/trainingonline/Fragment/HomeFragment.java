package cn.com.mbmpv.trainingonline.Fragment;

import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.util.Log;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.Window;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.Toast;

import com.android.volley.Request;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.cjj.MaterialRefreshLayout;
import com.cjj.MaterialRefreshListener;
import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

import java.util.ArrayList;
import java.util.List;

import cn.com.mbmpv.trainingonline.BaseFragment;
import cn.com.mbmpv.trainingonline.R;
import cn.com.mbmpv.trainingonline.adapter.HomeFragmentListViewAdapter;
import cn.com.mbmpv.trainingonline.application.MyApplication;
import cn.com.mbmpv.trainingonline.bean.Banner;
import cn.com.mbmpv.trainingonline.bean.LastResultHome;
import cn.com.mbmpv.trainingonline.bean.Lessonblock;
import cn.com.mbmpv.trainingonline.ui.SearchActivity;
import cn.com.mbmpv.trainingonline.utils.ConstantSet;
import cn.com.mbmpv.trainingonline.view.HomeFragmentFootView;
import cn.com.mbmpv.trainingonline.view.HomeFragmentHeadView;
import cn.com.mbmpv.trainingonline.widget.ProgressDialog;

/**
 * Created by jiuzheyange on 2016/8/8.
 */
public class HomeFragment extends BaseFragment {

    View view;

    LinearLayout searchLayout;
    ListView listView;
    List<Lessonblock> lists;
    HomeFragmentListViewAdapter adapter;
    HomeFragmentHeadView headView;
    HomeFragmentFootView footView;
    
    List<Banner> listBanner;

    ProgressDialog dialog;

    MaterialRefreshLayout materialRefreshLayout;


    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {

        initView();
        initData();
        initEvent();

        return view;
    }

    @Override
    protected void initView() {
        view = View.inflate(getActivity(), R.layout.home_fragment, null);
        searchLayout = (LinearLayout) view.findViewById(R.id.search_layout);

        listView = (ListView) view.findViewById(R.id.home_fragment_listview);

        materialRefreshLayout = (MaterialRefreshLayout)view.findViewById(R.id.refresh);
        materialRefreshLayout.setWaveColor(Color.parseColor("#55FFFFFF"));
        materialRefreshLayout.setIsOverLay(true);
        materialRefreshLayout.setWaveShow(true);

        lists = new ArrayList<Lessonblock>();

        dialog = new ProgressDialog(getActivity());

        Window window = dialog.getWindow();
        window.setGravity(Gravity.CENTER);
        dialog.setCanceledOnTouchOutside(false);// 设置点击Dialog外部任意区域关闭Dialog
        dialog.show();
        
    }

    @Override
    protected void initData() {
       
        getData();
        footView = new HomeFragmentFootView(getActivity());
        listView.addFooterView(footView.getView());
    }

    @Override
    protected void initEvent() {

        searchLayout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(getActivity(), SearchActivity.class));
            }
        });

        

        materialRefreshLayout.setMaterialRefreshListener(new MaterialRefreshListener() {
            @Override
            public void onRefresh(MaterialRefreshLayout materialRefreshLayout) {

                getData();
            }

            @Override
            public void onRefreshLoadMore(MaterialRefreshLayout materialRefreshLayout) {
                //load more refreshing...
            }
        });

    }


    public void getData() {
        StringRequest rq = new StringRequest(Request.Method.POST, ConstantSet.homeAddress + "main/gethome?", new Response.Listener<String>() {

            @Override
            public void onResponse(String response) {
                // TODO Auto-generated method stub

                System.out.print("response  " + response + "    " + response.length());

                Log.i("TAGTAG-Home", response);
                if (response.length() > 50) {
                    
                    Gson gson=new Gson();

                    LastResultHome lastResultHome = gson.fromJson(response, new TypeToken<LastResultHome>() {
                    }.getType());
                    lists.clear();
                    lists.addAll(lastResultHome.getData().getBlock());
                    if(adapter==null) {
                        
                        dialog.colseDialog();
                        listBanner=lastResultHome.getData().getBanner();

                        headView = new HomeFragmentHeadView(getActivity());
                        listView.addHeaderView(headView.getView(lastResultHome.getData().getBanner(),lastResultHome.getData().getCategory()));
                        adapter = new HomeFragmentListViewAdapter(lists, getActivity());
                        listView.setAdapter(adapter);
                    }
                    else{
                        materialRefreshLayout.finishRefresh();
                        adapter.notifyDataSetChanged();
                    }
                }
            }
        }, new Response.ErrorListener() {

            @Override
            public void onErrorResponse(VolleyError error) {
                // TODO Auto-generated method stub
                Toast.makeText(getActivity(), "网络请求失败", Toast.LENGTH_SHORT).show();
            }
        });

        MyApplication.getRq().add(rq);
    }

   
}
