package cn.com.mbmpv.trainingonline.widget;

import android.app.Dialog;
import android.content.Context;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.android.volley.AuthFailureError;
import com.android.volley.Request;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

import java.util.Date;
import java.util.HashMap;
import java.util.Map;

import cn.com.mbmpv.trainingonline.R;
import cn.com.mbmpv.trainingonline.application.MyApplication;
import cn.com.mbmpv.trainingonline.bean.Sign;
import cn.com.mbmpv.trainingonline.utils.ConstantSet;
import cn.com.mbmpv.trainingonline.utils.Md5Utils;

public class SignDialog extends Dialog {

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

    //first dialog's data
    TextView showTextView;
    TextView signBt;
    ImageView closeDialog;


    public SignDialog(Context context) {
        super(context, R.style.CustomDialogStyle);
        this.context = context;
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.sign_layout);
        //  setTitle(name);

        initView();
        initData();
        initEvent();

    }

    public void colseDialog() {
        SignDialog.this.dismiss();
    }


    private void initView() {
        
        showTextView= (TextView) findViewById(R.id.show_textview);

        showTextView.setText("今天签到可获取"+ConstantSet.jifen+"积分");
        signBt= (TextView) findViewById(R.id.lingqu_bt);
        closeDialog= (ImageView) findViewById(R.id.close_dialog);
    }

    private void initData() {
    }

    private void initEvent() {

        closeDialog.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                colseDialog();
            }
        });


        //去签到
        signBt.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                getData();   
                
            }
        });
    }

    //签到
    public void getData()
    {
        StringRequest rq=new StringRequest(Request.Method.POST, ConstantSet.homeAddress+"ding/ding?", new Response.Listener<String>() {

            @Override
            public void onResponse(String response) {
                // TODO Auto-generated method stub
                
                System.out.print("response  "+response+"    "+(new Date()).getTime()+""+response.length());
                Log.i("TAGTAG", response);
                if(response.length()>20) {


                    Gson gson = new Gson();
                    Sign resultUser = gson.fromJson(response, new TypeToken<Sign>() {
                    }.getType());
                    //Toast.makeText(StartupActivity.this,response+"222",Toast.LENGTH_SHORT).show();
                  

                   if(resultUser.getStatus()==1) {
                       Toast.makeText(context,"签到成功",Toast.LENGTH_SHORT).show();
                       colseDialog();
                       ConstantSet.sign = true;
                   }
                    
                    
//                    Sign resultUser = gson.fromJson(response, new TypeToken<Sign>() {
//                    }.getType());
//                    SimpleDateFormat formatter=new SimpleDateFormat("yyyy-MM-dd hh:mm:ss");
//                    String str=formatter.format(new Date());

                   /* if((resultUser.getData()!=null)&&(resultUser.getData().size()>0))
                    {
                        if (!((str.substring(0, 10)).equals(resultUser.getData().get(0).substring(0, 10)))) {
                            ConstantSet.sign = false;
                        }
                    }
                    else
                    {
                        ConstantSet.sign = false;
                    }*/

                }
            }
        }, new Response.ErrorListener(){

            @Override
            public void onErrorResponse(VolleyError error) {
                // TODO Auto-generated method stub

            }}){
            @Override
            protected Map<String, String> getParams() throws AuthFailureError {
                // TODO Auto-generated method stub
                Map<String,String> map=new HashMap<String, String>();
                map.put("obj_type","course");
                map.put("user_uid",ConstantSet.user.getUid());
                map.put("okey", Md5Utils.md5("moocuserdingcourse"));

                return map;
            }
        };

        MyApplication.getRq().add(rq);
    }

}
