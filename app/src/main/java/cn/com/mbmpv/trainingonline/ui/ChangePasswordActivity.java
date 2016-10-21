package cn.com.mbmpv.trainingonline.ui;

import android.os.Bundle;
import android.view.View;
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

import cn.com.mbmpv.trainingonline.BaseActivity;
import cn.com.mbmpv.trainingonline.R;
import cn.com.mbmpv.trainingonline.application.MyApplication;
import cn.com.mbmpv.trainingonline.bean.Sign;
import cn.com.mbmpv.trainingonline.utils.ConstantSet;
import cn.com.mbmpv.trainingonline.utils.Md5Utils;


public class ChangePasswordActivity extends BaseActivity {

    EditText zhanghao;
    EditText password1;
    EditText password2;
    TextView nextBt;
    
    ImageView back;
    
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_change_password);
        
        initView();
        initData();
        initEvent();
    }

    @Override
    protected void initView() {
        
        zhanghao= (EditText) findViewById(R.id.zhanghao_text);
        password1= (EditText) findViewById(R.id.password_text1);
        password2= (EditText) findViewById(R.id.password_text2);
        
        nextBt= (TextView) findViewById(R.id.next_bt);

        back= (ImageView) findViewById(R.id.back);
        
    }

    @Override
    protected void initData() {

    }

    @Override
    protected void initEvent() {
        nextBt.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                
                if(password1.getText().toString().equals(password2.getText().toString())) {
                    changePassword();
                }
                else
                {
                    showShortToast("请确保输入密码一致");
                }
            }
        });



        back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                ChangePasswordActivity.this.finish();
            }
        });
    }


    public void changePassword()
    {
        StringRequest rq=new StringRequest(Request.Method.POST, ConstantSet.homeAddress+"user/resetpass?", new Response.Listener<String>() {

            @Override
            public void onResponse(String response) {
                // TODO Auto-generated method stub

                System.out.print("response  "+response+"    "+response.length());
               
                if(response.length()>10) {

                    Gson gson = new Gson();
                    Sign resultUser = gson.fromJson(response, new TypeToken<Sign>() {
                    }.getType());

                    if (resultUser.getStatus()==1) {
                        ;

                        showShortToast("密码修改成功");
                        
                        ChangePasswordActivity.this.finish();
                    }
                }

            }
        }, new Response.ErrorListener(){

            @Override
            public void onErrorResponse(VolleyError error) {
                // TODO Auto-generated method stub
                Toast.makeText(ChangePasswordActivity.this, "网络请求失败", Toast.LENGTH_SHORT).show();

            }}){
            @Override
            protected Map<String, String> getParams() throws AuthFailureError {
                // TODO Auto-generated method stub
                Map<String,String> map=new HashMap<String, String>();
                if(ConstantSet.user!=null) {
                    map.put("user_id", ConstantSet.user.getUid());
                    map.put("okey", Md5Utils.md5("moocuserresetpass"+ConstantSet.user.getUid()));
                }
                map.put("old_passwd",zhanghao.getText().toString());
                map.put("new_passwd",password1.getText().toString());
               
                return map;
            }
        };

        MyApplication.getRq().add(rq);
    }
    
}
