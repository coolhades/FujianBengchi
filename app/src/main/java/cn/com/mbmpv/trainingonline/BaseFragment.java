package cn.com.mbmpv.trainingonline;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

/**
 * Created by jiuzheyange on 2016/8/8.
 */
public abstract class BaseFragment extends Fragment {

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        return super.onCreateView(inflater, container, savedInstanceState);
    }
    
    
    protected void showShortToast(String text)
    {
        Toast.makeText(getActivity(),text,Toast.LENGTH_SHORT).show();
    }

    protected void showLongToast(String text)
    {
        Toast.makeText(getActivity(),text,Toast.LENGTH_LONG).show();
    }
    
    
    protected abstract void initView();
    protected  abstract void initData();
    protected  abstract void initEvent();
    
}
