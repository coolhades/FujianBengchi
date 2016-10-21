package cn.com.mbmpv.trainingonline.ui;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.KeyEvent;
import android.view.View;
import android.view.inputmethod.EditorInfo;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.List;

import cn.com.mbmpv.trainingonline.R;
import cn.com.mbmpv.trainingonline.adapter.SearchHistoryListAdapter;
import cn.com.mbmpv.trainingonline.adapter.SearchKeyListAdapter;
import cn.com.mbmpv.trainingonline.application.MyApplication;
import cn.com.mbmpv.trainingonline.utils.ConstantSet;
import cn.com.mbmpv.trainingonline.utils.SharedPreferencesHelper;

public class SearchActivity extends AppCompatActivity {

   // GridView grilView;
    ListView listView;

    List<String> lists;
    List<String> list;
    SearchKeyListAdapter adapter;
    SearchHistoryListAdapter historyAdapter;

    TextView cancelBt;

    EditText editText;
    TextView clear;
    boolean flag=true;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_search);

        initView();
        initData();
        initEvent();
    }

    private void initView() {
        //grilView = (GridView) findViewById(R.id.search_grilview);
        listView = (ListView) findViewById(R.id.search_listview);
        cancelBt = (TextView) findViewById(R.id.cancel_bt);
        editText = (EditText) findViewById(R.id.edittext_search);
        clear= (TextView) findViewById(R.id.clear);

    }


    private void initData() {

        lists = new ArrayList<String>();
        list = new ArrayList<String>();
//        for (int i = 0; i < 10; i++) {
//            lists.add(i + "Item");
//           // list.add(i + "Item");
//        }

        //adapter = new SearchKeyListAdapter(lists, this);
       // grilView.setAdapter(adapter);

      
        


        list= new SharedPreferencesHelper(SearchActivity.this).getData("history", "historyKey");
       // list=MyApplication.getHistoryLists();
           // pag11.setVisibility(View.VISIBLE);
            historyAdapter = new SearchHistoryListAdapter(list, this);
       

        listView.setAdapter(historyAdapter);

    }


    private void initEvent() {

        cancelBt.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                SearchActivity.this.finish();
            }
        });


        editText.setOnEditorActionListener(new TextView.OnEditorActionListener() {
            public boolean onEditorAction(TextView v, int actionId, KeyEvent event) {
                if (actionId== EditorInfo.IME_ACTION_SEARCH
                        ||(event!=null&&event.getKeyCode()== KeyEvent.KEYCODE_ENTER)) {


                    for(int i=0;i<MyApplication.getHistoryLists().size();i++)
                    {
                        if(MyApplication.getHistoryLists().get(i).equals(editText.getText().toString()))
                        {
                            flag=false;
                        }
                    }

                    if(flag)
                    {
                        MyApplication.getHistoryLists().add(0,editText.getText().toString());
                    }

                    if(MyApplication.getHistoryLists().size()>3)
                    {
                        MyApplication.setHistoryLists(MyApplication.getHistoryLists().subList(0,3));
                    }
                    new SharedPreferencesHelper(SearchActivity.this).saveData("history", "historyKey", MyApplication.getHistoryLists());
                    
                    ConstantSet.keyWord=editText.getText().toString();
                    ConstantSet.cancel="search";
                    startActivity(new Intent(SearchActivity.this,TypeActivity.class));
                    return false;
                }
                return false;
            }
        });
        
        
        
        clear.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                list.clear();
                MyApplication.setHistoryLists(list);
                List<String> lists=new ArrayList<String>();
                list.addAll(lists);
                new SharedPreferencesHelper(SearchActivity.this).saveData("history", "historyKey", MyApplication.getHistoryLists());
                historyAdapter.notifyDataSetChanged();
            }
        });


    }
}
