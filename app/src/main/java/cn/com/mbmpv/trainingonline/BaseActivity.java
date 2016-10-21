package cn.com.mbmpv.trainingonline;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.widget.Toast;

/**
 * Created by jiuzheyange on 2016/8/8.
 */
public abstract class BaseActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    protected abstract void initView();
    protected  abstract void initData();
    protected  abstract void initEvent();

    protected void showShortToast(String text)
    {
        Toast.makeText(this,text,Toast.LENGTH_SHORT).show();
    }

    protected void showLongToast(String text)
    {
        Toast.makeText(this,text,Toast.LENGTH_LONG).show();
    }
}
