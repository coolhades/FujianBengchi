package cn.com.mbmpv.trainingonline.utils;

import android.content.Context;
import android.content.pm.PackageInfo;
import android.content.pm.PackageManager;

public class GetAppVersion {

	
	Context mContext;
	
	
	
	public GetAppVersion(Context mContext) {
		super();
		this.mContext = mContext;
	}

	
	public String getVersion() {
	    try {
	        PackageManager manager = mContext.getPackageManager();
	        PackageInfo info = manager.getPackageInfo(mContext.getPackageName(), 0);
	        String version = info.versionName;
	        return version;
	    } catch (Exception e) {
	        e.printStackTrace();
	        return "fail";
	    }
	}
}
