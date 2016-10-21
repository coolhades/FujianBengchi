package cn.com.mbmpv.trainingonline.Fragment;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
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

import cn.com.mbmpv.trainingonline.BaseFragment;
import cn.com.mbmpv.trainingonline.R;
import cn.com.mbmpv.trainingonline.application.MyApplication;
import cn.com.mbmpv.trainingonline.bean.ResultUser;
import cn.com.mbmpv.trainingonline.ui.HomeActivity;
import cn.com.mbmpv.trainingonline.ui.LoginAndRegisterActivity;
import cn.com.mbmpv.trainingonline.utils.AuthorizedLoginUtils;
import cn.com.mbmpv.trainingonline.utils.ConstantSet;
import cn.com.mbmpv.trainingonline.utils.Md5Utils;
import cn.com.mbmpv.trainingonline.utils.RegularUtils;

/**
 * Created by jiuzheyange on 2016/8/10.
 */
public class ResigterFragment extends BaseFragment {

    View mView;
    TextView nextBt;
    
    ImageView qqLoginBt;
    ImageView wxLoginBt;
    ImageView wbLoginBt;
    
    EditText inputEmail;
    EditText inputPassword1;
    EditText inputPassword2;
    
    
    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
     
        mView=inflater.inflate(R.layout.register_fragment,null);
        
        initView();
        initData();
        initEvent();

        return mView;
        
    }

    @Override
    protected void initView() {
        
        nextBt= (TextView) mView.findViewById(R.id.next_bt);
        qqLoginBt= (ImageView) mView.findViewById(R.id.qq_login_bt);
        wxLoginBt= (ImageView) mView.findViewById(R.id.wx_login_bt);
        wbLoginBt= (ImageView) mView.findViewById(R.id.wb_login_bt);
        
        inputEmail= (EditText) mView.findViewById(R.id.input_email);
        inputPassword1= (EditText) mView.findViewById(R.id.input_password1);
        inputPassword2= (EditText) mView.findViewById(R.id.input_password2);
    }

    @Override
    protected void initData() {

    }

    @Override
    protected void initEvent() {

        nextBt.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
              //  startActivity(new Intent(getActivity(), SetPasswordActivity.class));

               
                //提交注册时检查邮箱格式，密码是否一致
                if((inputEmail.getText().toString().length()>0)&&(inputPassword1.getText().toString().length()>0)&&(inputPassword2.getText().toString().length()>0))
                {
                   if(!(RegularUtils.emailValidation(inputEmail.getText().toString())))
                   {
                      showShortToast("请检查邮箱格式是否有误"); 
                   }
                    else if(!((inputPassword1.getText().toString()).equals(inputPassword2.getText().toString())))
                    {
                        
                        showShortToast("两次密码输入不一致");
                    }
                    if((RegularUtils.emailValidation(inputEmail.getText().toString()))&&(inputPassword1.getText().toString()).equals(inputPassword2.getText().toString()))
                    {

                        showShortToast("请稍等");
                        resigterUser(inputEmail.getText().toString(),inputPassword1.getText().toString());
                        
                    }
                    {
                        
                    }
                    
                }
                else
                {
                    showShortToast("邮箱或密码为空");
                }
                
                
            }
        });


        qqLoginBt.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Toast.makeText(getActivity(),"请稍等",Toast.LENGTH_SHORT).show();
                AuthorizedLoginUtils.authorzedQQ(getActivity());
               
                
            }
        });
        
        wxLoginBt.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
              AuthorizedLoginUtils.authorzedWechat(getActivity());
                showShortToast("请稍等");
            }
        });

        wbLoginBt.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                AuthorizedLoginUtils.authorzedSina(getActivity());
                showShortToast("请稍等");
            }
        });
    }
    
    
    public void resigterUser(final String account, final String passwd)
    {
        StringRequest rq=new StringRequest(Request.Method.POST, ConstantSet.homeAddress+"user/register?", new Response.Listener<String>() {

            @Override
            public void onResponse(String response) {
                // TODO Auto-generated method stub

                System.out.print("response  "+response+"    "+response.length());
                if(response.length()>35) {

                    Gson gson = new Gson();
                    ResultUser resultUser = gson.fromJson(response, new TypeToken<ResultUser>() {
                    }.getType());

                    if (resultUser.getStatus().equals("1")) {
                        showShortToast("注册成功"+response);
                        getActivity().startActivity(new Intent(getActivity(), HomeActivity.class));

                        ((LoginAndRegisterActivity) getActivity()).finish();
                    }
                }
                else
                {
                    showShortToast("此账户已存在");
                }

            }
        }, new Response.ErrorListener(){

            @Override
            public void onErrorResponse(VolleyError error) {
                // TODO Auto-generated method stub
                Toast.makeText(getActivity(), "网络请求失败", Toast.LENGTH_SHORT).show();

            }}){
            @Override
            protected Map<String, String> getParams() throws AuthFailureError {
                // TODO Auto-generated method stub
                Map<String,String> map=new HashMap<String, String>();
                map.put("account",account);
                map.put("passwd",passwd);
                map.put("okey", Md5Utils.md5("moocuserregister"+account+passwd));

                return map;
            }
        };

        MyApplication.getRq().add(rq);
    }
    
    
}
