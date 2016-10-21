package cn.com.mbmpv.trainingonline.widget;

import android.app.Dialog;
import android.content.Context;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;

import cn.com.mbmpv.trainingonline.R;
import cn.com.mbmpv.trainingonline.utils.ConstantSet;
import cn.com.mbmpv.trainingonline.utils.SharedSdkUtils;

public class SharedDialog extends Dialog {

    /*
     * author:qideli
       date:2016/1/23
       */
    public interface OnCustomDialogListener {
        public void back(String name);
    }

    /* TextView mCancel;
     TextView mOk;*/
    Context context;

    ImageView wxBt;
    ImageView wxFriendBt;
    ImageView sinaBt;
    ImageView closeDialog;
    

    String type;
    String url;
    String h5title;
    String mActionTarget;
    //first dialog's data


    public SharedDialog(Context context,String type,String url, String title, String actiontarget) {
        super(context, R.style.CustomDialogStyle);
        this.context = context;
        this.type=type;
        this.url=url;
        this.h5title = title;
        this.mActionTarget = actiontarget;
    }

    //考试成绩分享
    final String mycodes_content = "验证你学习成果的时刻到来啦！准备好了吗？";
    final String mycoin_content = "一分耕耘，一分收获。晒晒你的积分吧！";


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.shared_layout);
        //  setTitle(name);
        
        wxBt= (ImageView) findViewById(R.id.wx_bt);
        wxFriendBt= (ImageView) findViewById(R.id.wx_friend_bt);
        sinaBt= (ImageView) findViewById(R.id.sina_bt);
        closeDialog= (ImageView) findViewById(R.id.close_dialog);
        
        
        
        wxBt.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                
                if(type.equals("jifen")) {
                    SharedSdkUtils shared = new SharedSdkUtils(context, null, "share.integral", mActionTarget);
                    shared.sharedToWeixin1(h5title, mycoin_content, ConstantSet.sharedImageUrl, url);
                    SharedDialog.this.dismiss();
                }
                else if(type.equals("duihuan"))
                {
                    SharedSdkUtils shared = new SharedSdkUtils(context, null,"share.exchange", mActionTarget);
                    shared.sharedToWeixin1(h5title, "积分越多，兑换券值越大！不说了，赚积分去喽~", ConstantSet.sharedImageUrl,url);
                    SharedDialog.this.dismiss();
                }

                else if(type.equals("dangan"))
                {
                    SharedSdkUtils shared = new SharedSdkUtils(context, null, "share.study", mActionTarget);
                    shared.sharedToWeixin1(h5title, "业精于勤，荒于嬉。你学了多少就收获多少！", ConstantSet.sharedImageUrl, url);
                    SharedDialog.this.dismiss();
                }

                else if(type.equals("kaoshi"))
                {
                    SharedSdkUtils shared = new SharedSdkUtils(context, null,"share.exam", mActionTarget);
                    shared.sharedToWeixin1(h5title, mycodes_content,ConstantSet.sharedImageUrl, url);
                    SharedDialog.this.dismiss();
                }

                else if(type.equals("video"))
                {
                    SharedSdkUtils shared = new SharedSdkUtils(context, null,"share.course", mActionTarget);
                    shared.sharedToWeixin1("福建奔驰", "超棒的视频，大家一起来看吧",ConstantSet.sharedImageUrl, url);
                    SharedDialog.this.dismiss();
                }
                
            }
        });
        
        
        wxFriendBt.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if(type.equals("jifen")) {
                    SharedSdkUtils shared=new SharedSdkUtils(context,null, "share.integral", mActionTarget);
                    shared. sharedToFriend1(h5title,ConstantSet.sharedImageUrl,url);
                    SharedDialog.this.dismiss();
                }
                else if(type.equals("duihuan"))
                {
                    SharedSdkUtils shared=new SharedSdkUtils(context,null,"share.exchange", mActionTarget);
                    shared. sharedToFriend1("积分越多，兑换券值越大！不说了，赚积分去喽~",ConstantSet.sharedImageUrl,url);
                    SharedDialog.this.dismiss();
                }

                else if(type.equals("dangan"))
                {
                    SharedSdkUtils shared=new SharedSdkUtils(context,null,"share.study", mActionTarget);
                    shared. sharedToFriend1("业精于勤，荒于嬉。你学了多少就收获多少！",ConstantSet.sharedImageUrl,url);
                    SharedDialog.this.dismiss();
                }

                else if(type.equals("kaoshi"))
                {
                    SharedSdkUtils shared=new SharedSdkUtils(context,null,"share.exam", mActionTarget);
                    shared. sharedToFriend1(h5title,ConstantSet.sharedImageUrl,url);
                    SharedDialog.this.dismiss();
                }

                else if(type.equals("video"))
                {
                    SharedSdkUtils shared=new SharedSdkUtils(context,null,"share.course", mActionTarget);
                    shared. sharedToFriend1("超棒的视频，大家一起来看吧",ConstantSet.sharedImageUrl,url);
                    SharedDialog.this.dismiss();
                }
                
            }
        });


        sinaBt.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

               

                if(type.equals("jifen")) {
                    SharedSdkUtils shared=new SharedSdkUtils(context,null,"share.integral", mActionTarget);
                    shared.sharedToSina(h5title,ConstantSet.sharedImageUrl,url);
                    SharedDialog.this.dismiss();
                }
                else if(type.equals("duihuan"))
                {
                    SharedSdkUtils shared=new SharedSdkUtils(context,null,"share.exchange", mActionTarget);
                    shared.sharedToSina("积分越多，兑换券值越大！不说了，赚积分去喽~",ConstantSet.sharedImageUrl,url);
                    SharedDialog.this.dismiss();
                }

                else if(type.equals("dangan"))
                {
                    SharedSdkUtils shared=new SharedSdkUtils(context,null,"share.study", mActionTarget);
                    shared.sharedToSina("业精于勤，荒于嬉。你学了多少就收获多少！",ConstantSet.sharedImageUrl,url);
                    SharedDialog.this.dismiss();
                }

                else if(type.equals("kaoshi"))
                {
                    SharedSdkUtils shared=new SharedSdkUtils(context,null,"share.exam", mActionTarget);
                    shared.sharedToSina(h5title,ConstantSet.sharedImageUrl,url);
                    SharedDialog.this.dismiss();
                }

                else if(type.equals("video"))
                {
                    SharedSdkUtils shared=new SharedSdkUtils(context,null,"share.course", mActionTarget);
                    shared.sharedToSina("超棒的视频，大家一起来看吧",ConstantSet.sharedImageUrl,url);
                    SharedDialog.this.dismiss();
                }
                
                
            }
        });


        closeDialog.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {


                colseDialog();
            }
        });
        

    }

    public void colseDialog() {
        SharedDialog.this.dismiss();
    }


}
