package cn.com.mbmpv.trainingonline.ui;

import android.os.Bundle;
import android.widget.ListView;

import java.util.ArrayList;
import java.util.List;

import cn.com.mbmpv.trainingonline.BaseActivity;
import cn.com.mbmpv.trainingonline.R;
import cn.com.mbmpv.trainingonline.adapter.BuyHistoryListViewAdapter;

public class BuyHistoryActivity extends BaseActivity {

    ListView buyHistoryListView;
    BuyHistoryListViewAdapter adapter;
    List<String> lists;
    
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_buy_history);
        initView();
        initData();
        initEvent();
    }

    @Override
    protected void initView() {
        
        buyHistoryListView= (ListView) findViewById(R.id.buy_history_listview);
    }

    @Override
    protected void initData() {
        lists=new ArrayList<String>();
        for(int i=0;i<10;i++)
        {
            lists.add("item"+i);
        }
        
        adapter=new BuyHistoryListViewAdapter(lists,this);
        
        buyHistoryListView.setAdapter(adapter);

    }

    @Override
    protected void initEvent() {

    }
}
