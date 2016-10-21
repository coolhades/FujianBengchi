package com.mbmpv.expandablelistviewdemo;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.widget.ExpandableListView;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity {


    ExpandableListView listView;
    
    List<String> parentLists;
    List<List<String>>  childLists;
    List<String> tempLists;
    ListViewAdapter adapter;
    
    
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        
        
        listView= (ExpandableListView) findViewById(R.id.expandableListView);
        
        parentLists=new ArrayList<String>();
        childLists=new  ArrayList<List<String>>();
        tempLists=new ArrayList<String>();
        for(int i=0;i<5;i++)
        {
            parentLists.add("Father"+i);
        }

        for(int i=0;i<3;i++)
        {
            tempLists.add("Child"+i);
        }
        
        for(int i=0;i<parentLists.size();i++)
        {
            childLists.add(tempLists);
        }
        
        adapter=new ListViewAdapter(this,parentLists,childLists);
        
        listView.setAdapter(adapter);
        
    }
}
