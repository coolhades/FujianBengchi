package cn.com.mbmpv.trainingonline.ui;

import android.os.Bundle;
import android.widget.ListView;

import java.util.ArrayList;
import java.util.List;

import cn.com.mbmpv.trainingonline.BaseActivity;
import cn.com.mbmpv.trainingonline.R;
import cn.com.mbmpv.trainingonline.adapter.DownLoadAdapter;

public class DownloadManagerActivity extends BaseActivity {

    
    ListView downLoadManagerListView;
    DownLoadAdapter adapter;
    List<String> lists;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_download_manager);
        
        initView();
        initData();
        initEvent();
    }

    @Override
    protected void initView() {

        downLoadManagerListView= (ListView) findViewById(R.id.download_listview);
    }

    @Override
    protected void initData() {
        
        lists=new ArrayList<String>();
        for(int i=0;i<10;i++)
        {
            lists.add("item"+i);
        }

        adapter=new DownLoadAdapter(lists,this);
        
        downLoadManagerListView.setAdapter(adapter);
    }

    @Override
    protected void initEvent() {

    }
}
