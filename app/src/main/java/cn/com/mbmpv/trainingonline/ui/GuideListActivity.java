package cn.com.mbmpv.trainingonline.ui;

import android.os.Bundle;
import android.view.Gravity;
import android.view.View;
import android.view.Window;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.ListView;

import cn.com.mbmpv.trainingonline.BaseActivity;
import cn.com.mbmpv.trainingonline.R;
import cn.com.mbmpv.trainingonline.widget.MaskDialog_List;

/**
 * Created by Hades on 16/9/18.
 */
public class GuideListActivity extends BaseActivity {

    ListView guidelist_listview;
//    GuideListViewAdapter adapter;
    ImageView back;

    LinearLayout vedio_bt;
    LinearLayout question_bt;
    LinearLayout study_bt;
    LinearLayout exam_bt;
    LinearLayout coin_bt;
    LinearLayout coinchange_bt;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.guidelist_layout);
        initView();
        initData();
        initEvent();
    }

    @Override
    protected void initView() {
        back = (ImageView) findViewById(R.id.back);
        vedio_bt = (LinearLayout) findViewById(R.id.vedio_bt);
        question_bt = (LinearLayout) findViewById(R.id.question_bt);
        study_bt = (LinearLayout) findViewById(R.id.study_bt);
        exam_bt = (LinearLayout) findViewById(R.id.exam_bt);
        coin_bt = (LinearLayout) findViewById(R.id.coin_bt);
        coinchange_bt = (LinearLayout) findViewById(R.id.coinchange_bt);



    }

    @Override
    protected void initData() {


    }


    @Override
    protected void initEvent() {
        back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                GuideListActivity.this.finish();
            }
        });

        vedio_bt.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                openDialog("Guide.Video.Share");

            }
        });
        question_bt.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                openDialog("Guide.Question");
            }
        });
        study_bt.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                openDialog("Guide.Studey.Share");
            }
        });
        exam_bt.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                openDialog("Guide.Grade.Share");
            }
        });
        coin_bt.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                openDialog("Guide.Integrate.Share");
            }
        });
        coinchange_bt.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                openDialog("Guide.Exchange.Share");
            }
        });

    }


    private void openDialog(String question){
        //使用方式
        MaskDialog_List dialog = new MaskDialog_List(GuideListActivity.this, question);
        Window window = dialog.getWindow();
        window.setGravity(Gravity.BOTTOM);
        dialog.setCanceledOnTouchOutside(true);// 设置点击Dialog外部任意区域关闭Dialog
        dialog.show();
    }


}
