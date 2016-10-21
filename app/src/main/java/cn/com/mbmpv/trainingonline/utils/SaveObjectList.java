package cn.com.mbmpv.trainingonline.utils;

import android.content.Context;
import android.content.SharedPreferences;
import android.content.SharedPreferences.Editor;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

import java.util.List;

public class SaveObjectList {
	
    Context mContext;
	
	Gson gson;
	List<Object> lists;
	
	
	public SaveObjectList(Context mContext) {
		super();
		this.mContext = mContext;
		gson=new Gson();
	}

	
	@SuppressWarnings("null")
	public void saveData(String FilerName,String key,List<Object> lists)
	{	
		
		System.out.println("savedata   "+lists);
		if(lists!=null)
		{
		Editor share =mContext.getSharedPreferences(FilerName, 0).edit();    
		share.putString(key,gson.toJson(lists));   
		share.commit(); 
		}
		else
		{
			Editor share =mContext.getSharedPreferences(FilerName, 0).edit();    
			share.putString(key,"");   
			share.commit(); 
		}
	}
	
	
	
	public List<Object> getData(String FilerName,String key)
	{
		SharedPreferences sharedata = mContext.getSharedPreferences(FilerName, 0);    
		String data = sharedata.getString(key,""); 
		if(!(data.equals("")))
		{
		lists=gson.fromJson(data,new TypeToken<List<Object>>(){}.getType());

		return lists;
		}
		return null;
	}
}
