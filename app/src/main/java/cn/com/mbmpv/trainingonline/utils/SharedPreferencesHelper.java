package cn.com.mbmpv.trainingonline.utils;

import android.content.Context;
import android.content.SharedPreferences;
import android.content.SharedPreferences.Editor;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

import java.util.List;

public class SharedPreferencesHelper {

	
	Context mContext;
	
	Gson gson;
	List<String> lists;
	
	
	public SharedPreferencesHelper(Context mContext) {
		super();
		this.mContext = mContext;
		gson=new Gson();
	}

	
	@SuppressWarnings("null")
	public void saveData(String FilerName,String key,List<String> value)
	{	
		
		System.out.println("savedata   "+value);
		if(value!=null)
		{
		Editor share =mContext.getSharedPreferences(FilerName, 0).edit();    
		share.putString(key,gson.toJson(value));   
		share.commit(); 
		}
		else
		{
			Editor share =mContext.getSharedPreferences(FilerName, 0).edit();    
			share.putString(key,"");   
			share.commit(); 
		}
	}
	
	
	
	public List<String> getData(String FilerName,String key)
	{
		SharedPreferences sharedata = mContext.getSharedPreferences(FilerName, 0);    
		String data = sharedata.getString(key,""); 
		if(!(data.equals("")))
		{
		lists=gson.fromJson(data,new TypeToken<List<String>>(){}.getType());
		System.out.println("history   "+lists);
		return lists;
		}
		
		System.out.println("history   "+lists);
		return null;
	}
	
	
	
	
}
