package cn.com.mbmpv.trainingonline.widget;

import android.app.Dialog;
import android.content.Context;
import android.os.Bundle;
import android.view.View;

import com.wang.avi.AVLoadingIndicatorView;

import cn.com.mbmpv.trainingonline.R;

public class ProgressDialog extends Dialog{

	/*
	 * author:qideli
	   date:2016/1/23
	   */
	        public interface OnCustomDialogListener{
	                public void back(String name);
	        }
	       /* TextView mCancel;
	        TextView mOk;*/
	        Context context;

	        AVLoadingIndicatorView loadingIndicatorView;
	        
	        
	        //first dialog's data
	        
	        
	        public ProgressDialog(Context context) {
	        	    super(context, R.style.CustomDialogStyle);
	        	    this.context=context;
	        }
	        
	        @Override
	        protected void onCreate(Bundle savedInstanceState) { 
	                super.onCreate(savedInstanceState);
	                setContentView(R.layout.progress_dialog);
	              //  setTitle(name);

				loadingIndicatorView= (AVLoadingIndicatorView) findViewById(R.id.avloadingIndicatorView_BallClipRotatePulse);
	               
	        }
	        
	        public void colseDialog()
	        {
				loadingIndicatorView.setVisibility(View.INVISIBLE);
	        	ProgressDialog.this.dismiss();
	        }
	        
	       
}
