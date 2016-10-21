package cn.com.mbmpv.trainingonline.utils;

import android.content.Context;
import android.os.Handler;
import android.os.Message;
import android.util.Log;
import android.widget.Toast;

import com.android.volley.AuthFailureError;
import com.android.volley.Request;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;

import java.util.HashMap;
import java.util.Map;

import cn.com.mbmpv.trainingonline.application.MyApplication;
import cn.sharesdk.framework.Platform;
import cn.sharesdk.framework.PlatformActionListener;
import cn.sharesdk.framework.PlatformDb;
import cn.sharesdk.framework.ShareSDK;
import cn.sharesdk.sina.weibo.SinaWeibo;
import cn.sharesdk.tencent.qq.QQ;
import cn.sharesdk.wechat.friends.Wechat;
import cn.sharesdk.wechat.moments.WechatMoments;


/*QQ:1
weixin :0
weibo:2
self:3
nan:0
nv:1
wei zhi:2*/
public class SharedSdkUtils {

	Context mContext;
	Handler handler;
	String mActionTarget;
	String mActionType;

	
	PlatformDb db;

	// boolean mAuthorization=false;

	public SharedSdkUtils(Context mContext,Handler handler, String action_type,String action_target) {
		super();
		this.mContext = mContext;
		this.handler=handler;
		this.mActionTarget = action_target;
		this.mActionType = action_type;

	}

	

	public void sharedToQq(String imageUrl,final String emojiid)
	{
		QQ.ShareParams sp = new QQ.ShareParams();
        sp.imageUrl=imageUrl;
        Platform qq = ShareSDK.getPlatform(mContext,QQ.NAME);
        
        qq.setPlatformActionListener(new PlatformActionListener() {
        
			@Override
			public void onError(Platform arg0, int arg1, Throwable arg2) {
				// TODO Auto-generated method stub
				System.out.println("Error");
			}
			
			@Override
			public void onComplete(Platform arg0, int arg1, HashMap<String, Object> arg2) {
				// TODO Auto-generated method stub
				
				System.out.println("complete");
				
				
//				StringRequest rq=new StringRequest(Method.POST,ConstantSet.addUserAddress, new Listener<String>() {
//
//					@Override
//					public void onResponse(String response) {
//						// TODO Auto-generated method stub
//						
//						System.out.println(response);
//					}
//				}, new Response.ErrorListener() {
//
//					@Override
//					public void onErrorResponse(VolleyError error) {
//						// TODO Auto-generated method stub
//						System.out.println("fail");
//					}
//				})
//						{
//					@Override
//					protected Map<String, String> getParams() throws AuthFailureError {
//						// TODO Auto-generated method stub
//						
//						Map<String,String> map=new HashMap<String,String>();
//						map.put("act","shareemoji");
//						map.put("emojiid",emojiid);
//						map.put("user_id",ConstantSet.userId);
//						return map;
//					}
//						};
//				MyApplication.getRq().add(rq);
			}
			
			@Override
			public void onCancel(Platform arg0, int arg1) {
				// TODO Auto-generated method stub
				System.out.println("onCancel");
			}
		}); // ���÷����¼��ص�
        // ִ��ͼ�ķ���
        qq.share(sp);
		
	}
	
	public void sharedToWeixin(String imageUrl,final String emojiid)
	{
		    Wechat.ShareParams sp = new Wechat.ShareParams();
	        sp.imageUrl=imageUrl;
	        sp.shareType=Platform.SHARE_IMAGE;
	        Platform weixin = ShareSDK.getPlatform(mContext,Wechat.NAME);        
	        weixin.setPlatformActionListener(new PlatformActionListener() {
				
				@Override
				public void onError(Platform arg0, int arg1, Throwable arg2) {
					// TODO Auto-generated method stub
					Toast.makeText(mContext, "fail",Toast.LENGTH_SHORT).show();
				}
				
				@Override
				public void onComplete(Platform arg0, int arg1, HashMap<String, Object> arg2) {
					// TODO Auto-generated method stub
					
					
					Message msg = new Message();
					msg.arg1 = 6;
					handler.sendMessage(msg);
					
				}
				
				@Override
				public void onCancel(Platform arg0, int arg1) {
					
					
					   
					// TODO Auto-generated method stub
					Toast.makeText(mContext, "cancel", Toast.LENGTH_SHORT).show();
				}
			}); // ���÷����¼��ص�
	        // ִ��ͼ�ķ���
	        weixin.share(sp);
	}
	
	
	
	
	//Gif
	
	public void sharedToWeixinForGif(String imageUrl,final String emojiid)
	{
		    Wechat.ShareParams sp = new Wechat.ShareParams();
	        sp.imageUrl=imageUrl;
	        sp.shareType=Platform.SHARE_EMOJI;
	        Platform weixin = ShareSDK.getPlatform(mContext,Wechat.NAME);        
	        weixin.setPlatformActionListener(new PlatformActionListener() {
				
				@Override
				public void onError(Platform arg0, int arg1, Throwable arg2) {
					// TODO Auto-generated method stub
					Toast.makeText(mContext, "fail",Toast.LENGTH_SHORT).show();
				}
				
				@Override
				public void onComplete(Platform arg0, int arg1, HashMap<String, Object> arg2) {
					// TODO Auto-generated method stub
					
					
					Message msg = new Message();
					msg.arg1 = 6;
					handler.sendMessage(msg);
					
				}
				
				@Override
				public void onCancel(Platform arg0, int arg1) {
					
					
					   
					// TODO Auto-generated method stub
					Toast.makeText(mContext, "cancel", Toast.LENGTH_SHORT).show();
				}
			}); // ���÷����¼��ص�
	        // ִ��ͼ�ķ���
	        weixin.share(sp);
	}
	
	
	
	
	//分享到微信
	
		public void sharedToWeixin1(String title,String content,String imageUrl,String pagerUrl)
		{
			    Wechat.ShareParams sp = new Wechat.ShareParams();
			  //����
				sp.title=title;
				//���
				sp.text=content;
				//ͼƬ��ַ
				sp.imageUrl=imageUrl;
				//��ҳ����
		        sp.url=pagerUrl;
			Toast.makeText(mContext,"请稍等...",Toast.LENGTH_SHORT).show();
		        sp.shareType=Platform.SHARE_WEBPAGE;
		        Platform weixin = ShareSDK.getPlatform(Wechat.NAME);        
		        weixin.setPlatformActionListener(new PlatformActionListener() {
					
					@Override
					public void onError(Platform arg0, int arg1, Throwable arg2) {
						// TODO Auto-generated method stub
						Toast.makeText(mContext, "fail", Toast.LENGTH_SHORT).show();

					}
					
					@Override
					public void onComplete(Platform arg0, int arg1, HashMap<String, Object> arg2) {
						// TODO Auto-generated method stub
						addCoin(Wechat.NAME);
					}
					
					@Override
					public void onCancel(Platform arg0, int arg1) {
						// TODO Auto-generated method stub
						Toast.makeText(mContext, "cancel",Toast.LENGTH_SHORT).show();
					}
				}); // ���÷����¼��ص�
		        // ִ��ͼ�ķ���
		        weixin.share(sp);
		}
		
		
		//����QQ
		
		public void sharedToQq1(String title,String content,String imageUrl,String pagerUrl)
		{
			QQ.ShareParams sp = new QQ.ShareParams();
			//����
			sp.title=title;
			//���
			sp.text=content;
			//ͼƬ��ַ
			sp.imageUrl=imageUrl;
			//��ҳ����
	        sp.titleUrl=pagerUrl;
			Toast.makeText(mContext,"请稍等...",Toast.LENGTH_SHORT).show();
	        Platform qq = ShareSDK.getPlatform(QQ.NAME);
	    	//System.out.println("jinlaile");
	        qq.setPlatformActionListener(new PlatformActionListener() {
	        
				@Override
				public void onError(Platform arg0, int arg1, Throwable arg2) {
					// TODO Auto-generated method stub
					Toast.makeText(mContext, "fail", Toast.LENGTH_SHORT).show();
				}
				
				@Override
				public void onComplete(Platform arg0, int arg1, HashMap<String, Object> arg2) {
					// TODO Auto-generated method stub
					//分享完成 出发增加积分

				}

				@Override
				public void onCancel(Platform arg0, int arg1) {
					// TODO Auto-generated method stub
					
				}
	        });
	        
	        qq .share(sp);
		}
		
		
		//分享到微信朋友圈
			public void sharedToFriend1(String title,String imageUrl,String pagerUrl)
			{
				    Wechat.ShareParams sp = new Wechat.ShareParams();
				  //����
					sp.title=title;
					//ͼƬ��ַ
					sp.imageUrl=imageUrl;
					//��ҳ����
			        sp.url=pagerUrl;
			        sp.shareType=Platform.SHARE_WEBPAGE;
				Toast.makeText(mContext,"请稍等...",Toast.LENGTH_SHORT).show();
			        Platform weixin = ShareSDK.getPlatform(WechatMoments.NAME);        
			        weixin.setPlatformActionListener(new PlatformActionListener() {
						
						@Override
						public void onError(Platform arg0, int arg1, Throwable arg2) {
							// TODO Auto-generated method stub
							Toast.makeText(mContext, "fail", Toast.LENGTH_SHORT).show();

						}
						
						@Override
						public void onComplete(Platform arg0, int arg1, HashMap<String, Object> arg2) {
							// TODO Auto-generated method stub
							//分享完成 出发增加积分
							addCoin(WechatMoments.NAME);
						}
						
						@Override
						public void onCancel(Platform arg0, int arg1) {
							// TODO Auto-generated method stub
							Toast.makeText(mContext, "cancel", Toast.LENGTH_SHORT).show();
						}
					});
			        weixin.share(sp);
			}
			
			
			
			
		/*	//QZone
			
			public void sharedToQZone1(String title,String content,String imageUrl,String pagerUrl)
			{
				ShareParams sp = new ShareParams();
				sp.setTitle(title+pagerUrl);
				sp.setTitleUrl(pagerUrl); // ����ĳ�����
				sp.setText(content);
				sp.setImageUrl(imageUrl);
				sp.setSite("Ӧ���̵�");
				sp.setSiteUrl(pagerUrl);
				Platform qzone = ShareSDK.getPlatform (QZone.NAME);
				// ִ��ͼ�ķ���
				   
				qzone.setPlatformActionListener(new PlatformActionListener() {
						
						@Override
						public void onError(Platform arg0, int arg1, Throwable arg2) {
							// TODO Auto-generated method stub
							Toast.makeText(mContext, "fail", 1).show();
						}
						
						@Override
						public void onComplete(Platform arg0, int arg1, HashMap<String, Object> arg2) {
							// TODO Auto-generated method stub
						}
						
						@Override
						public void onCancel(Platform arg0, int arg1) {
							// TODO Auto-generated method stub
							Toast.makeText(mContext, "cancel", 1).show();
						}
					}); // ���÷����¼��ص�
			        // ִ��ͼ�ķ���
				 qzone.share(sp);
			}

			
			
			
			//����QQ(˵˵����ʽ)
			
			public void sharedToQqShuoShuo(String imageUrl)
			{
				QQ.ShareParams sp = new QQ.ShareParams();
				
				sp.setImageUrl(imageUrl);
				sp.setSite("΢�ű��鶷ͼ��");
		        Platform qq = ShareSDK.getPlatform(QZone.NAME);
		    	//System.out.println("jinlaile");
		        qq.setPlatformActionListener(new PlatformActionListener() {
		        
					@Override
					public void onError(Platform arg0, int arg1, Throwable arg2) {
						// TODO Auto-generated method stub
						Toast.makeText(mContext, "fail", 1).show();
					}
					
					@Override
					public void onComplete(Platform arg0, int arg1, HashMap<String, Object> arg2) {
						// TODO Auto-generated method stub
					}

					@Override
					public void onCancel(Platform arg0, int arg1) {
						// TODO Auto-generated method stub
						
					}
		        });
		        
		        qq .share(sp);
			}
			*/
			
			
			//΢�ţ�ͼƬ��
			
			public void sharedToFriendShuoShuo(String title,String imageUrl)
			{
				    Wechat.ShareParams sp = new Wechat.ShareParams();
					sp.title=title;
					sp.imageUrl=imageUrl;
			        //sp.url=pagerUrl;
			        sp.shareType=Platform.SHARE_IMAGE;
				Toast.makeText(mContext,"请稍等...",Toast.LENGTH_SHORT).show();
			        Platform weixin = ShareSDK.getPlatform(WechatMoments.NAME);        
			        weixin.setPlatformActionListener(new PlatformActionListener() {
						
						@Override
						public void onError(Platform arg0, int arg1, Throwable arg2) {
							// TODO Auto-generated method stub
							Toast.makeText(mContext, "fail", Toast.LENGTH_SHORT).show();
						}
						
						@Override
						public void onComplete(Platform arg0, int arg1, HashMap<String, Object> arg2) {
							// TODO Auto-generated method stub
						}
						
						@Override
						public void onCancel(Platform arg0, int arg1) {
							// TODO Auto-generated method stub
							Toast.makeText(mContext, "cancel", Toast.LENGTH_SHORT).show();
						}
					}); // ���÷����¼��ص�
			        // ִ��ͼ�ķ���
			        weixin.share(sp);
			}


	//΢�ţ�ͼƬ��

	public void sharedToSina(String title,String imageUrl,String imagePath)
	{
		SinaWeibo.ShareParams sp = new SinaWeibo.ShareParams();
		sp.imagePath =imageUrl;
		sp.imageUrl=imageUrl;
		sp.setText(title+imagePath);
		Toast.makeText(mContext,"请稍等...",Toast.LENGTH_SHORT).show();
		Platform weixin = ShareSDK.getPlatform(SinaWeibo.NAME);
		weixin.setPlatformActionListener(new PlatformActionListener() {

			@Override
			public void onError(Platform arg0, int arg1, Throwable arg2) {
				// TODO Auto-generated method stub
				Toast.makeText(mContext, "fail", Toast.LENGTH_SHORT).show();
			}

			@Override
			public void onComplete(Platform arg0, int arg1, HashMap<String, Object> arg2) {
				// TODO Auto-generated method stub
				addCoin(SinaWeibo.NAME);
			}

			@Override
			public void onCancel(Platform arg0, int arg1) {
				// TODO Auto-generated method stub
				Toast.makeText(mContext, "cancel", Toast.LENGTH_SHORT).show();
			}
		}); // ���÷����¼��ص�
		// ִ��ͼ�ķ���
		weixin.share(sp);
	}


	public void addCoin(final String platform){
		Log.i("TAG-Share","执行了add");
		StringRequest rq = new StringRequest(Request.Method.POST, ConstantSet.homeAddress + "/share/addintegral?", new Response.Listener<String>() {

			@Override
			public void onResponse(String response) {
				// TODO Auto-generated method stub
				Log.i("AddCoin",response);
				if (response.length() > 30) {

					Log.i("TAG-Share", response);
//					Gson gson=new Gson();
//					ResultVideo resultVideo = gson.fromJson(response, new TypeToken<ResultVideo>() {
//					}.getType());

				}

			}
		}, new Response.ErrorListener() {

			@Override
			public void onErrorResponse(VolleyError error) {
				// TODO Auto-generated method stub
				Toast.makeText(mContext, "网络请求失败1", Toast.LENGTH_SHORT).show();
			}
		}) {
			@Override
			protected Map<String, String> getParams() throws AuthFailureError {
				// TODO Auto-generated method stub
				Map<String,String> map=new HashMap<String, String>();
//				user_id
//				action_type
//				okey
//				action_target  06BC7ED972634172AECEA8F707F81705
//				action_platform

//				share.course	分享了某课程 action_target[选填] impower_id的值
//				share.study	分享了学习 action_target不用
//				share.integral	分享了积分详情 action_target不用
//				share.exchange	分享了积分兑换 action_target最好能传兑换的那个奖励的唯一标识码
//				share.exam	分享了考试内容 action_target传 exam_id的值

//				关于okey
//				moocshareaddintegral + [user_id] + [action_type]

				if(ConstantSet.user!=null) {
					map.put("user_id", ConstantSet.user.getUid());
				}

					map.put("action_type", mActionType);
				Log.i("TAG-Share-actiontype", mActionType);
				//积分排行、学习档案 积分兑换
				if (mActionTarget != null && !mActionTarget.equalsIgnoreCase(""))
				map.put("action_target",mActionTarget);
				map.put("action_platform",platform);
				map.put("okey", Md5Utils.md5("moocshareaddintegral"+ConstantSet.user.getUid()+ mActionType) );
				return map;
			}
		};

		MyApplication.getRq().add(rq);
	}

}
