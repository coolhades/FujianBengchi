package cn.com.mbmpv.trainingonline.widget;

import android.app.Dialog;
import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.util.Log;
import android.view.Gravity;
import android.view.View;
import android.view.Window;
import android.webkit.DownloadListener;
import android.webkit.JsResult;
import android.webkit.WebChromeClient;
import android.webkit.WebSettings;
import android.webkit.WebView;
import android.webkit.WebViewClient;
import android.widget.ImageView;
import android.widget.TextView;

import com.bokecc.sdk.mobile.play.DWMediaPlayer;
import com.wang.avi.AVLoadingIndicatorView;

import cn.com.mbmpv.trainingonline.R;
import cn.com.mbmpv.trainingonline.utils.ShowMaskDialogUtils;

public class WebViewDialog extends Dialog {

    private static String gtitle = null;
    private static String gurl = null;
    private static int gresid = 0;
    private static String gThumbPath = null;
    private WebView webview = null;

    Context context;
    DWMediaPlayer player;
    
    boolean flag=true;

    ImageView btn;
    
    TextView sharedBt;
    TextView t;

    ImageView guide_iv;
    Handler mVideoHandler;

    AVLoadingIndicatorView loadingIndicatorView;

    String mActionType;
    
    Handler handler=new Handler(){
        @Override
        public void handleMessage(Message msg) {
            super.handleMessage(msg);

            if(msg.arg1==1002) {

//                Toast.makeText(context,webview.getUrl(),Toast.LENGTH_SHORT).show();
                if (webview.getUrl().contains("action=close")) {
                    colseDialog();
                }
                else if(webview.getUrl().contains("action=share"))
                {
                    sharedBt.setVisibility(View.VISIBLE);
                }

            }else if (msg.arg1 == 1011){

                if (mytitle.contains("签到")){
                    t.setText("我的签到");
                }else {
                    t.setText(mytitle);
                }

                if (mytitle.contains("学习")){
//                    guide_iv.setVisibility(View.VISIBLE);
                    guide_iv.setOnClickListener(new View.OnClickListener() {
                        @Override
                        public void onClick(View v) {
                            ShowMaskDialogUtils.showMaskDialog_Btn(context, "dangAnFile", "dangAn", "Guide.Studey.Share");
                        }
                    });
                }else if (mytitle.contains("我的积分")){
//                    guide_iv.setVisibility(View.VISIBLE);
                    guide_iv.setOnClickListener(new View.OnClickListener() {
                        @Override
                        public void onClick(View v) {
                            ShowMaskDialogUtils.showMaskDialog_Btn(context, "paiMingFile", "paiMing", "Guide. Integrate.Share");
                        }
                    });

                }else if (mytitle.contains("兑换")){
//                    guide_iv.setVisibility(View.VISIBLE);
                    guide_iv.setOnClickListener(new View.OnClickListener() {
                        @Override
                        public void onClick(View v) {
                            ShowMaskDialogUtils.showMaskDialog_Btn(context, "duiHuanFile", "duiHuan", "Guide. Integrate.Share");
                        }
                    });

                }else {
                    guide_iv.setVisibility(View.INVISIBLE);
                }
            }
        }
    };

    public interface OnCustomDialogListener {
        public void back(String name);
    }

    public WebViewDialog(Context context, Handler handler,String url, String title, int resid, String thumbPath, DWMediaPlayer player, String action_type) {
        super(context, R.style.AppTheme);
        this.context = context;
        this.gurl = url;
        this.gtitle = title;
        this.gresid = resid;
        this.gThumbPath = thumbPath;
        this.player = player;
        this.mActionType = action_type;
        if (handler != null) {
            this.mVideoHandler = handler;
        }
    }

    @Override
    protected void onStart() {
        super.onStart();

//		FlurryAgent.onStartSession(this, UIApplication.FLURRY_KEY);
    }

    public void colseDialog() {
        
        flag=false;
        if (mVideoHandler != null) {
            Message msg = Message.obtain();
            msg.arg1 = 10010;
            mVideoHandler.sendMessage(msg);
        }

        WebViewDialog.this.dismiss();
        if (player != null) {
            player.start();
        }

    }

    @Override
    protected void onStop() {
        super.onStop();

//		FlurryAgent.onEndSession(this);
    }

//	@Override
//	protected void onPause() {
//		super.onPause();
//        webview.reload();
//		//友盟统计
//		//MobclickAgent.onPause(this);
//	}

    private class MyWebViewDownLoadListener implements DownloadListener {
        @Override
        public void onDownloadStart(String url, String userAgent, String contentDisposition, String mimetype,
                                    long contentLength) {
            Uri uri = Uri.parse(url);
            Intent intent = new Intent(Intent.ACTION_VIEW, uri);
            context.startActivity(intent);
        }
    }

    String mytitle;
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.webview_layout);
        webview = (WebView) findViewById(R.id.webview);
        loadingIndicatorView= (AVLoadingIndicatorView) findViewById(R.id.avloadingIndicatorView_BallClipRotatePulse);

        sharedBt= (TextView) findViewById(R.id.shared_text);

        guide_iv = (ImageView) findViewById(R.id.guide_iv);


        webview.setWebViewClient(new WebViewClient() {
            @Override
            public boolean shouldOverrideUrlLoading(WebView view, String url) {

//                Toast.makeText(context,"ll",Toast.LENGTH_SHORT).show();
                if (url.contains("action=close")) {
                    colseDialog();
                    return true;
                } else if (url.contains("action=share")) {
                    sharedBt.setVisibility(View.VISIBLE);
                    webview.loadUrl(url);
                    return false;
                }
                return true;
            }

        });


        // 创建WebViewClient对象
//        WebViewClient wvc = new WebViewClient() {
//            @Override
//            public boolean shouldOverrideUrlLoading(WebView view, String url) {
//                Toast.makeText(context,"ll",Toast.LENGTH_SHORT).show();
//                if (url.contains("action=close")) {
//                    colseDialog();
//                    return true;
//                } else if (url.contains("action=share")) {
//                    webview.loadUrl(url);
//                    return false;
//                }
//                return true;
//            }
//        };
//        webview.setWebViewClient(wvc);



        sharedBt.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                //actiontype = "" 不传
                if(gtitle.equals("我的积分"))
                {
                    SharedDialog dialog = new SharedDialog(context,"jifen",gurl, mytitle,mActionType);

                    Window window = dialog.getWindow();
                    window.setGravity(Gravity.BOTTOM);
                    dialog.setCanceledOnTouchOutside(true);// 设置点击Dialog外部任意区域关闭Dialog
                    dialog.show();
                }

//      app没法传          share.exchange	分享了积分兑换 action_target最好能传兑换的那个奖励的唯一标识码
                else if(gtitle.equals("积分兑换"))
                {
                    SharedDialog dialog = new SharedDialog(context,"duihuan",gurl, mytitle,mActionType);

                    Window window = dialog.getWindow();
                    window.setGravity(Gravity.BOTTOM);
                    dialog.setCanceledOnTouchOutside(true);// 设置点击Dialog外部任意区域关闭Dialog
                    dialog.show();
                }
                //actiontype = ""
                else if(gtitle.equals("学习档案"))
                {
                    SharedDialog dialog = new SharedDialog(context,"dangan",gurl, mytitle, mActionType);

                    Window window = dialog.getWindow();
                    window.setGravity(Gravity.BOTTOM);
                    dialog.setCanceledOnTouchOutside(true);// 设置点击Dialog外部任意区域关闭Dialog
                    dialog.show();
                }
                
            }
        });
        
        
       // webview.setWebViewClient(new webViewClient());

        webview.setWebChromeClient(new WebChromeClient()
        {
            public void onProgressChanged(WebView view,int newProgress)
            {
                loadingIndicatorView.setVisibility(View.INVISIBLE);
            }
        });


        btn = (ImageView) findViewById(R.id.web_back);
        if (webview != null && gurl != null && gurl.length() > 0) {
            try {
                WebSettings webSettings = webview.getSettings();
                webSettings.setSavePassword(false);
                webSettings.setSaveFormData(false);
                webSettings.setJavaScriptEnabled(true);
                webSettings.setJavaScriptCanOpenWindowsAutomatically(true);
                webSettings.setSupportZoom(false);
                webSettings.setDomStorageEnabled(true);
                webview.clearSslPreferences();
                webview.setWebViewClient(new webViewClient());
                webview.setWebChromeClient(new MyWebChromeClient());
                webview.loadUrl(gurl);

                new Thread(
                        new Runnable() {
                            @Override
                            public void run() {
                                while (flag)
                                {

                                    try {
                                        Thread.sleep(1000);
                                    } catch (InterruptedException e) {
                                        e.printStackTrace();
                                    }
                                    Message msg=Message.obtain();
                                    
                                    msg.arg1=1002;
                                    handler.sendMessage(msg);
                                }
                            }
                        }
                ).start();

            } catch (Throwable t) {
            }
        }

        webview.setDownloadListener(new MyWebViewDownLoadListener());
        
		/*ImageView imageview = (ImageView)findViewById(R.id.title_image);
		if (imageview != null && gresid != 0)
		{
			imageview.setImageResource(gresid);
		}*/

        if (gtitle != null) {
            t = (TextView) findViewById(R.id.title_text);
            t.setText(gtitle);
            //	imageview.setVisibility(View.INVISIBLE);
        }

        //返回键
        {
            btn.setOnClickListener(new View.OnClickListener() {
                public void onClick(View v) {
                    colseDialog();
                }
            });
        }
		
		/*{
			final Button btn = (Button) findViewById(R.id.btn_share);
			if (gtitle != null && gurl != null)
			{btn.setVisibility(View.INVISIBLE);
				btn.setOnClickListener(new OnClickListener() {
					public void onClick(View v) {
						WeixinManager.sharedManager().sendWebReqToWeixi(gtitle, gtitle, gurl, gThumbPath, true);
						btn.setVisibility(View.INVISIBLE);
					}
				});
			}
			else
			{
				btn.setVisibility(View.INVISIBLE);
			}
		}*/
    }
	
	/*@Override
    public boolean onKeyDown(int keyCode, KeyEvent event) {
        if(event.getAction() == KeyEvent.ACTION_DOWN){
           *//* switch(keyCode)
            {
            case KeyEvent.KEYCODE_BACK:
//                if(webview!=null && webview.canGoBack() == true && webview.getUrl() != null){
//                	webview.goBack();
//                }else{
//                    //finish();
//                }
                return true;
            }*//*
            
            return true;

        }
        return super.onKeyDown(keyCode, event);
    }*/

    class webViewClient extends WebViewClient {
        //重写shouldOverrideUrlLoading方法，使点击链接后不使用其他的浏览器打开�?  
        @Override
        public boolean shouldOverrideUrlLoading(WebView view, String url) {
            WebView.HitTestResult hit = view.getHitTestResult();
            if (hit != null) {
                int hitType = hit.getType();
                if (hitType == WebView.HitTestResult.SRC_ANCHOR_TYPE) {//点击超链�?
                    //这里执行自定义的操
                    
                    if (url.contains("action=close")) {
                        colseDialog();
                        view.loadUrl(url);
                    }
                    return true;//返回true浏览器不再执行默认的操作
                } else if (hitType == 0) {//重定向时hitType�?0
                    return false;//不捕�?302重定�?
                } else {
                    return false;
                }
            }
            return false;
        }
    }

    /**
     * Provides a hook for calling "alert" from javascript. Useful for
     * debugging your javascript.
     */
    final class MyWebChromeClient extends WebChromeClient {
        @Override
        public void onReceivedTitle(WebView view, String title) {
            super.onReceivedTitle(view, title);
            Log.i("ANDROID_LAB", "TITLE=" + title);
            mytitle = title;

            Message msg=Message.obtain();
            msg.arg1=1011;
            handler.sendMessage(msg);
//            Toast.makeText(context, "Title"+title, Toast.LENGTH_SHORT).show();
        }

        @Override
        public boolean onJsAlert(WebView view, String url, String message, JsResult result) {
            result.confirm();
            return true;
        }
    }
}
