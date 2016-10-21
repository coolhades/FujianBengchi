package cn.com.mbmpv.trainingonline.widget;

import android.app.Dialog;
import android.content.Context;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.view.KeyEvent;
import android.view.View;
import android.view.inputmethod.EditorInfo;
import android.widget.EditText;
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

import java.util.HashMap;
import java.util.Map;

import cn.com.mbmpv.trainingonline.R;
import cn.com.mbmpv.trainingonline.application.MyApplication;
import cn.com.mbmpv.trainingonline.bean.QuestionBean;
import cn.com.mbmpv.trainingonline.utils.ConstantSet;
import cn.com.mbmpv.trainingonline.utils.Md5Utils;

/**
 * Created by jiuzheyange on 2016/8/18.
 */
public class TiWenDialog extends Dialog {

    /*
     * author:qideli
       date:2016/1/23
       */

    //使用方式
    /*SharedDialog dialog = new SharedDialog(mContext, lists.get(position * 3 -3));

    Window window = dialog.getWindow();
    window.setGravity(Gravity.BOTTOM);
    dialog.setCanceledOnTouchOutside(true);// 设置点击Dialog外部任意区域关闭Dialog
    dialog.show();*/
    
    ImageView closeBt;
    TextView sendBt;
    EditText editText;
    
    public interface OnCustomDialogListener {
        public void back(String name);
    }

    /* TextView mCancel;
     TextView mOk;*/
    Context context;
    
    String obj_id;
    Handler handler;

    //first dialog's data

    public TiWenDialog(Context context,String obj_id,Handler handler) {
        super(context, R.style.AppTheme);
        this.context = context;
        this.obj_id=obj_id;
        this.handler=handler;
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.tiwen_dialog);

        initView();
        initData();
        initEvent();
        

    }


    public void colseDialog() {
        TiWenDialog.this.dismiss();
    }


    private void initView() {

        
        closeBt= (ImageView) findViewById(R.id.close_bt);
        sendBt= (TextView) findViewById(R.id.send_bt);
        editText= (EditText) findViewById(R.id.edit_text);
    }


    private void initData() {
       // getData();
    }


    private void initEvent() {

        closeBt.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                colseDialog(); 
            }
        });

        sendBt.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                getData();
                
            }
        });


        editText.setOnEditorActionListener(new TextView.OnEditorActionListener() {
            public boolean onEditorAction(TextView v, int actionId, KeyEvent event) {
                if (actionId== EditorInfo.IME_ACTION_SEARCH
                        ||(event!=null&&event.getKeyCode()== KeyEvent.KEYCODE_ENTER)) {
                    getData();
                    return false;
                }
                return false;
            }
        });
    }


    public void getData() {
        StringRequest rq = new StringRequest(Request.Method.POST, ConstantSet.homeAddress + "comment/addcomment?", new Response.Listener<String>() {

            @Override
            public void onResponse(String response) {
                // TODO Auto-generated method stub
                
               // Toast.makeText(context,response,Toast.LENGTH_LONG).show();
                if (response.length() > 20) {

                    Gson gson = new Gson();

                    QuestionBean result = gson.fromJson(response, new TypeToken< QuestionBean>() {
                    }.getType());
                    
                    if(result.getStatus().equals("1"))
                    {
                        colseDialog();
                        Message msg=Message.obtain();
                        msg.arg1=10000;
                        handler.sendMessage(msg);
                        Toast.makeText(context,"提问成功",Toast.LENGTH_SHORT).show();
                    }

                }
            }
        }, new Response.ErrorListener() {

            @Override
            public void onErrorResponse(VolleyError error) {
                // TODO Auto-generated method stub
                Toast.makeText(context, "网络请求失败", Toast.LENGTH_SHORT).show();
            }
        }) {
            @Override
            protected Map<String, String> getParams() throws AuthFailureError {
                // TODO Auto-generated method stub
                Map<String, String> map = new HashMap<String, String>();
                map.put("obj_type","course");
                map.put("obj_id",obj_id);
                map.put("user_uid",ConstantSet.user.getUid());
                map.put("info",editText.getText().toString());
                map.put("okey", Md5Utils.md5("moocuseraddcomment"+"course"+obj_id));
                return map;
            }
        };

        MyApplication.getRq().add(rq);
    }
}
