package cn.com.mbmpv.trainingonline.ui;

import android.graphics.Color;
import android.os.Bundle;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import cn.com.mbmpv.trainingonline.BaseActivity;
import cn.com.mbmpv.trainingonline.Fragment.ErrorLoginFragment;
import cn.com.mbmpv.trainingonline.Fragment.ResigterFragment;
import cn.com.mbmpv.trainingonline.R;

public class ErrorLoginActivity extends BaseActivity {


    FragmentManager manager;
    FragmentTransaction transaction;

    ErrorLoginFragment loginFragment;
    ResigterFragment resigterFragment;

    TextView loginBt;
    //TextView registerBt;
    
    ImageView back;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login_and_register);

//        System.out.println("MD5  "+ Md5Utils.md5("123456"));
//
//        SaveUser save=new SaveUser(this);
//        ConstantSet.user=(save.getData("userFile","user"));
//
//        if(ConstantSet.user!=null)
//        {
//            if(!(ConstantSet.user.getUid().equals("0")))
//            {
//                startActivity(new Intent(this,HomeActivity.class));
//                this.finish();
//            }
//
//
//        }
        
        
       /* String[] mPermissionList = new String[]{Manifest.permission.ACCESS_FINE_LOCATION, Manifest.permission.CALL_PHONE, Manifest.permission.READ_LOGS, Manifest.permission.READ_PHONE_STATE, Manifest.permission.WRITE_EXTERNAL_STORAGE, Manifest.permission.SET_DEBUG_APP, Manifest.permission.SYSTEM_ALERT_WINDOW, Manifest.permission.GET_ACCOUNTS};
        ActivityCompat.requestPermissions(LoginAndRegisterActivity.this, mPermissionList, 100);*/

        initView();
        initData();
        initEvent();
    }

    @Override
    protected void initView() {


        loginBt = (TextView) findViewById(R.id.login_bt);
       // registerBt = (TextView) findViewById(R.id.register_bt);

        manager = getSupportFragmentManager();
        transaction = manager.beginTransaction();

        loginFragment = new ErrorLoginFragment();
        resigterFragment = new ResigterFragment();

        transaction.replace(R.id.fragment_layout, loginFragment).commit();
        
        back= (ImageView) findViewById(R.id.back);

    }

    @Override
    protected void initData() {

    }

    @Override
    protected void initEvent() {

        loginBt.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                transaction = manager.beginTransaction();
                transaction.replace(R.id.fragment_layout, loginFragment).commit();

               // registerBt.setTextColor(Color.parseColor("#333333"));
                loginBt.setTextColor(Color.parseColor("#FFFFFF"));
               // registerBt.setBackgroundColor(Color.parseColor("#00000000"));
                loginBt.setBackgroundResource(R.drawable.login_bg);
            }
        });


/*
        registerBt.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                transaction = manager.beginTransaction();
                transaction.replace(R.id.fragment_layout, resigterFragment).commit();

                loginBt.setTextColor(Color.parseColor("#333333"));
                registerBt.setTextColor(Color.parseColor("#FFFFFF"));
                loginBt.setBackgroundColor(Color.parseColor("#00000000"));
                registerBt.setBackgroundResource(R.drawable.login_bg);

            }
        });
*/

        back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                
                ErrorLoginActivity.this.finish();
            }
        });

    }
}
