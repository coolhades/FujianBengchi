package com.mbmpv.multithreaddownload;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.ProgressBar;

import org.xutils.common.Callback;
import org.xutils.http.RequestParams;
import org.xutils.x;

import java.io.File;

public class MainActivity extends AppCompatActivity {

    ProgressBar progressBar1;
    ProgressBar progressBar2;
    ProgressBar progressBar3;
    ProgressBar progressBar4;
    ProgressBar progressBar5;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        progressBar1 = (ProgressBar) findViewById(R.id.progress_bar_1);
        progressBar2 = (ProgressBar) findViewById(R.id.progress_bar_2);
        progressBar3 = (ProgressBar) findViewById(R.id.progress_bar_3);
        progressBar4 = (ProgressBar) findViewById(R.id.progress_bar_4);
        progressBar5 = (ProgressBar) findViewById(R.id.progress_bar_5);
      
        
        progressBar1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                setDownLoad("http://ac-mihmcopc.clouddn.com/54c92f34ba6417b0.mp4",progressBar1);
            }
        });

        progressBar2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                setDownLoad("http://ac-mihmcopc.clouddn.com/367458d0185b7189.mp4",progressBar2);
            }
        });

        progressBar3.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                setDownLoad("http://ac-mihmcopc.clouddn.com/367458d0185b7189.mp4",progressBar3);
            }
        });

        progressBar4.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                setDownLoad("http://ac-mihmcopc.clouddn.com/367458d0185b7189.mp4",progressBar4);
            }
        });

        progressBar5.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                setDownLoad("http://ac-mihmcopc.clouddn.com/367458d0185b7189.mp4",progressBar5);
            }
        });


    }


    protected void setDownLoad(String downloadurl,final ProgressBar progressBar) {
// TODO Auto-generated method stub
        RequestParams params = new RequestParams(downloadurl);
        params.setAutoRename(true);//断点下载
        params.setSaveFilePath("/mnt/sdcard/demo.mp4");
        x.http().get(params, new Callback.ProgressCallback<File>() {
            @Override
            public void onCancelled(CancelledException arg0) {
// TODO Auto-generated method stub
            }

            @Override
            public void onError(Throwable arg0, boolean arg1) {
// TODO Auto-generated method stub

                System.out.println("提示更新失败");
            }

            @Override
            public void onFinished() {
// TODO Auto-generated method stub
            }

            @Override
            public void onSuccess(File arg0) {
// TODO Auto-generated method stub
                
              /*  Intent intent = new Intent(Intent.ACTION_VIEW);
                intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
                intent.setDataAndType(Uri.fromFile(new File(Environment
                                .getExternalStorageDirectory(), "demo.apk")),
                        "application/vnd.android.package-archive");
                startActivity(intent);*/
            }

            @Override
            public void onLoading(long arg0, long arg1, boolean arg2) {
// TODO Auto-generated method stub
                progressBar.setVisibility(View.VISIBLE);
                progressBar.setMax((int) arg0);
                progressBar.setProgress((int) arg1);
            }

            @Override
            public void onStarted() {
// TODO Auto-generated method stub
                System.out.println("开始下载");

            }

            @Override
            public void onWaiting() {
// TODO Auto-generated method stub
            }
        });
    }
}
