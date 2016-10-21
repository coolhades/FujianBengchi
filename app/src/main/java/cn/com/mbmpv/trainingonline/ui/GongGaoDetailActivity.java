package cn.com.mbmpv.trainingonline.ui;

import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import cn.com.mbmpv.trainingonline.BaseActivity;
import cn.com.mbmpv.trainingonline.R;

/**
 * Created by Hades on 16/9/7.
 */
public class GongGaoDetailActivity extends BaseActivity {

    TextView title;
    TextView content;
    TextView date_create;
    ImageView back;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.gonggao_detail_layout);

        initView();
        initData();
        initEvent();

    }

    @Override
    protected void initView() {
        title = (TextView) findViewById(R.id.title_gonggao);
        content = (TextView) findViewById(R.id.content_gonggao);
        date_create = (TextView) findViewById(R.id.date_create);

        back = (ImageView) findViewById(R.id.back);


    }

    @Override
    protected void initData() {
        Bundle bundle = getIntent().getExtras();
        Log.i("TAG-Bundle", "title"+bundle.getString("title")+"content"+bundle.getString("content"));
        if (!bundle.isEmpty()){
            title.setText(bundle.getString("title"));
            content.setText(bundle.getString("content"));
            date_create.setText(bundle.getString("data"));
        }
    }

    @Override
    protected void initEvent() {
        back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                GongGaoDetailActivity.this.finish();
            }
        });
    }
}
