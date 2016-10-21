package cn.com.mbmpv.trainingonline.utils;

import android.content.Context;
import android.content.SharedPreferences;
import android.content.SharedPreferences.Editor;

public class SaveString {

	
	Context mContext;
	
	
	public SaveString(Context mContext) {
		super();
		this.mContext = mContext;
	}

	
	@SuppressWarnings("null")
	public void savePhoneNumber(String FilerName,String key,String phoneNumber)
	{	
		if(phoneNumber!=null&&phoneNumber!="")
		{
		Editor share =mContext.getSharedPreferences(FilerName, 0).edit();    
		share.putString(key,phoneNumber);   
		share.commit(); 
		}
		else
		{
			Editor share =mContext.getSharedPreferences(FilerName, 0).edit();    
			share.putString(key,"");   
			share.commit(); 
		}
	}
	
	
	
	public String getPhoneNumber(String FilerName,String key)
	{
		SharedPreferences sharedata = mContext.getSharedPreferences(FilerName, 0);    
		String data = sharedata.getString(key,""); 
		if(!(data.equals("")))
		{

		return data;
		}
		return "";
	}
	
	
	
	
}
