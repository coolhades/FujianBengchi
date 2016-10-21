package cn.com.mbmpv.trainingonline.utils;

import android.content.Context;
import android.content.SharedPreferences;
import android.content.SharedPreferences.Editor;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

import cn.com.mbmpv.trainingonline.bean.User;

public class SaveUser {
	
    Context mContext;
	
	Gson gson;
	User object;
	
	
	public SaveUser(Context mContext) {
		super();
		this.mContext = mContext;
		gson=new Gson();
	}

	
	@SuppressWarnings("null")
	public void saveData(String FilerName,String key,User object)
	{	
		
		System.out.println("savedata   "+object);
		if(object!=null)
		{
		Editor share =mContext.getSharedPreferences(FilerName, 0).edit();    
		share.putString(key,gson.toJson(object));
		share.commit(); 
		}
		else
		{
			Editor share =mContext.getSharedPreferences(FilerName, 0).edit();    
			share.putString(key,"");   
			share.commit(); 
		}
	}
	
	
	
	public User  getData(String FilerName,String key)
	{
		SharedPreferences sharedata = mContext.getSharedPreferences(FilerName, 0);    
		String data = sharedata.getString(key,"");
		
		if(!(data.equals("")))
		{
			object=gson.fromJson(data,new TypeToken<User>(){}.getType());

		return object;
		}
		return null;
	}
}
