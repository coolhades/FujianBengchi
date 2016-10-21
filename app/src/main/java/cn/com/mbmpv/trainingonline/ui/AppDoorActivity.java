package cn.com.mbmpv.trainingonline.ui;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.TextView;

import cn.com.mbmpv.trainingonline.R;
import cn.com.mbmpv.trainingonline.utils.ConstantSet;

public class AppDoorActivity extends AppCompatActivity {

    TextView doorA;
    TextView doorB;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_app_door);


        doorA= (TextView) findViewById(R.id.doora);
        doorB= (TextView) findViewById(R.id.doorb);
        
        doorA.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                ConstantSet.homeAddress="http://api.auto-mooc.com/";  
                startActivity(new Intent(AppDoorActivity.this,LoginAndRegisterActivity.class));
                
                AppDoorActivity.this.finish();
                
            }
        });


        doorB.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {


                ConstantSet.homeAddress="http://fjbcapi.auto-mooc.com/";
                startActivity(new Intent(AppDoorActivity.this,LoginAndRegisterActivity.class));
               // Toast.makeText(AppDoorActivity.this,"维护中...",Toast.LENGTH_SHORT).show();
            }
        });
        
        
    }
    
    
}
