package cn.com.mbmpv.trainingonline.Fragment;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.LinearLayout;
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
import cn.com.mbmpv.trainingonline.utils.ConstantSet;
import cn.com.mbmpv.trainingonline.utils.Md5Utils;
import cn.com.mbmpv.trainingonline.utils.SaveUser;

/**
 * Created by jiuzheyange on 2016/8/10.
 */
public class ErrorLoginFragment extends BaseFragment {

    View mView;
    //TextView forgetPasswordBt;
    TextView loginBt;

    ImageView qqLoginBt;
    ImageView wxLoginBt;
    ImageView wbLoginBt;
    
    EditText inputEmail;
    EditText inputPassword;
    
    LinearLayout loginBar;
    
    TextView tip;
    
    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
     
        mView=inflater.inflate(R.layout.login_fragmentt,null);
        initView();
        initData();
        initEvent();

        return mView;
        
    }

    @Override
    protected void initView() {

       // forgetPasswordBt= (TextView) mView.findViewById(R.id.forget_password);
        loginBt= (TextView) mView.findViewById(R.id.login_bt);

        qqLoginBt= (ImageView) mView.findViewById(R.id.qq_login_bt);
        wxLoginBt= (ImageView) mView.findViewById(R.id.wx_login_bt);
        wbLoginBt= (ImageView) mView.findViewById(R.id.wb_login_bt);
        
        inputEmail= (EditText) mView.findViewById(R.id.input_email);
        inputPassword= (EditText) mView.findViewById(R.id.input_password);
        
        loginBar= (LinearLayout) mView.findViewById(R.id.login_bar);
        
        tip= (TextView) mView.findViewById(R.id.tip);

        if(ConstantSet.confiMap!=null) {
            if (ConstantSet.confiMap.get("App.Switch.Third-Party.Login").equals("0")) {
                loginBar.setVisibility(View.GONE);
                tip.setVisibility(View.GONE);
            }
        }
        
    }

    @Override
    protected void initData() {

    }

    @Override
    protected void initEvent() {


/*
        forgetPasswordBt.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                
                
                startActivity(new Intent(getActivity(), ChangePasswordActivity.class));
            }
        });
*/
        
        
         loginBt.setOnClickListener(new View.OnClickListener() {
             @Override
             public void onClick(View view) {
                 Toast.makeText(getActivity(),"网络错误，暂时无法登录",Toast.LENGTH_SHORT).show();
                 
//                 if(!((inputEmail.getText().toString().length()>0)&&(inputPassword.getText().toString().length()>0)))
//                 {
//                     showShortToast("用户账号或密码为空");
//                 }
//
//                 else{
//
//                     loginUser(inputEmail.getText().toString(),inputPassword.getText().toString());
//
//                 }
//                 startActivity(new Intent(getActivity(), HomeActivity.class));
//                 getActivity().finish();
             }
         });


        qqLoginBt.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
//                AuthorizedLoginUtils.authorzedQQ(getActivity());
                Toast.makeText(getActivity(),"网络错误，暂时无法登录",Toast.LENGTH_SHORT).show();

            }
        });

        wxLoginBt.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
//                AuthorizedLoginUtils.authorzedWechat(getActivity());
                Toast.makeText(getActivity(),"网络错误，暂时无法登录",Toast.LENGTH_SHORT).show();
            }
        });

        wbLoginBt.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
//                AuthorizedLoginUtils.authorzedSina(getActivity());
//                showShortToast("请稍等");
//                getActivity().startActivity(new Intent(getActivity(),HomeActivity.class));
//                getActivity().finish();
                Toast.makeText(getActivity(),"网络错误，暂时无法登录",Toast.LENGTH_SHORT).show();
                
            }
        });
    }

    public void loginUser(final String account, final String passwd)
    {
        StringRequest rq=new StringRequest(Request.Method.POST, ConstantSet.homeAddress+"user/login?", new Response.Listener<String>() {

            @Override
            public void onResponse(String response) {
                // TODO Auto-generated method stub

                System.out.print("response  "+response+"    "+response.length());
                //showShortToast(response);
                if(response.length()>40) {

                    Gson gson = new Gson();
                    ResultUser resultUser = gson.fromJson(response, new TypeToken<ResultUser>() {
                    }.getType());

                    if (resultUser.getStatus().equals("1")) {


                        SaveUser save=new SaveUser(getActivity());
                        ConstantSet.user=resultUser.getData();
                        save.saveData("userFile","user",resultUser.getData());
                        Toast.makeText(getActivity(),resultUser.getData().getNickname(),Toast.LENGTH_SHORT).show();
                        
                        getActivity().startActivity(new Intent(getActivity(), HomeActivity.class));

                        ((LoginAndRegisterActivity) getActivity()).finish();
                    }
                }
                else
                {
                    showShortToast("账户或密码错误");
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
                map.put("okey", Md5Utils.md5("moocuserlogin"+account+passwd));

                return map;
            }
        };

        MyApplication.getRq().add(rq);
    }
    
}
