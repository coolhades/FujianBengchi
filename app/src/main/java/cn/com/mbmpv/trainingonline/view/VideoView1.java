package cn.com.mbmpv.trainingonline.view;

import android.content.Context;
import android.graphics.Color;
import android.net.Uri;
import android.os.Handler;
import android.os.Message;
import android.util.Log;
import android.view.View;
import android.widget.ExpandableListView;
import android.widget.Toast;

import com.android.volley.AuthFailureError;
import com.android.volley.Request;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.bokecc.sdk.mobile.play.DWMediaPlayer;
import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import cn.com.mbmpv.trainingonline.R;
import cn.com.mbmpv.trainingonline.adapter.VideoView1ListViewAdapter;
import cn.com.mbmpv.trainingonline.application.MyApplication;
import cn.com.mbmpv.trainingonline.bean.JieBean;
import cn.com.mbmpv.trainingonline.bean.ResultVideo;
import cn.com.mbmpv.trainingonline.bean.ZhangBean;
import cn.com.mbmpv.trainingonline.utils.ConstantSet;
import cn.com.mbmpv.trainingonline.utils.Md5Utils;
import cn.com.mbmpv.trainingonline.utils.SharedPreferencesUtils;

import static android.view.View.inflate;

/**
 * Created by jiuzheyange on 2016/8/10.
 */
public class VideoView1 {

    Context mContext;
    View mView;

    List<ZhangBean> parentLists;
    List<List<JieBean>>  childLists;
    VideoView1ListViewAdapter adapter;

    ExpandableListView listView;

    List<ZhangBean> zhangBeanList;
    DWMediaPlayer player;
    Handler handler;
    ResultVideo resultVideo;



    public VideoView1(Context mContext,List<ZhangBean> zhangBean, DWMediaPlayer player,Handler handler) {
        this.mContext = mContext;
        this.zhangBeanList=zhangBean;
        this.player=player;
        this.handler=handler;
    }

    public View getView()
    {
        mView= inflate(mContext, R.layout.video_view1,null);
        initView();
        initData();
        initEvent();

        return mView;

    }

    private void initView() {

        listView= (ExpandableListView) mView.findViewById(R.id.view1_listview);
        listView.setGroupIndicator(null);
    }

    private void initData() {

        parentLists=new ArrayList<ZhangBean>();
        childLists=new  ArrayList<List<JieBean>>();

        parentLists=zhangBeanList;




        for(int i=0;i<parentLists.size();i++)
        {
            childLists.add(zhangBeanList.get(i).getChild());
        }

        if(parentLists!=null&&parentLists.size()>0) {

            adapter = new VideoView1ListViewAdapter(mContext, parentLists, childLists,player);

            listView.setAdapter(adapter);

            for (int i = 0; i < parentLists.size(); i++) {
                listView.expandGroup(i);
            }




//            adapter.setTextViewColor(SharedPreferencesUtils.getValue(mContext, "Setting", "video_id", 0));

        }
    }

    private void initEvent() {
        
        listView.setOnChildClickListener(new ExpandableListView.OnChildClickListener() {
            @Override
            public boolean onChildClick(ExpandableListView expandableListView, View view, int i, int i1, long l) {
                //将该位置的view设置背景色

                view.setBackgroundColor(Color.parseColor("#000000"));
                getChildVideo(i,i1);
                parentVideo_index = i;
                childVideo_index = i1;

                SharedPreferencesUtils.putValue(mContext, "Settings", "video_child_index", i1);
                SharedPreferencesUtils.putValue(mContext, "Settings", "video_index", i);
                return false;
            }
        });

        
        listView.setOnGroupClickListener(new ExpandableListView.OnGroupClickListener() {
            @Override
            public boolean onGroupClick(ExpandableListView expandableListView, View view, int i, long l) {
                adapter.setSelectedPosition(i);
                adapter.notifyDataSetChanged();
//                adapter.notifyDataSetChanged();
                getParentVideo(i);//获取链接并播放
                parentVideo_index = i;

                SharedPreferencesUtils.putValue(mContext, "Settings", "video_index", i);
//                TextView textView = (TextView) view.findViewById(R.id.text);
//                adapter.resetTextViewColor();
//                adapter.alltextview.get(i).setTextColor(Color.parseColor("#888765"));
//                savePos(i);
                return true;
            }
        });
    }

    //保存全屏前的章节id
    void savePos(int pos){
        Log.i("TAGTAG", "设置的pos="+pos);
        SharedPreferencesUtils.putValue(mContext,"Settings", "video_id", pos);

    }


    int parentVideo_index; //用来获取 父节点位置
    int childVideo_index = 0; //当前节点位置

    public void playNext(){
        if (parentLists.get(parentVideo_index).getChild()== null)//目前章是否有节
        {
            //无节 进入下一章
            int i = parentVideo_index;
//            ToastUtils.showTextToast("现在是第"+parentVideo_index+"章"+"总共"+parentLists.size()+"章", mContext);
            Log.d("TAG-ParentIndex-next", "第"+parentVideo_index+"章"+"     总共"+parentLists.size()+"章");
            if (++i <parentLists.size()) {
                //有下一章的情况
                parentVideo_index++;
//                playNext();
                if (parentLists.get(parentVideo_index).getChild() == null){
                    getParentVideo(parentVideo_index);
                    adapter.setSelectedPosition(parentVideo_index);
                    adapter.notifyDataSetChanged();

                }else {
                    playNext();
                }
            }else {
                Toast.makeText(mContext, "已经是最后一集！", Toast.LENGTH_SHORT).show();
                return;
            }
        }else {
            //有节的情况
            if (childVideo_index<parentLists.get(parentVideo_index).getChild().size()-1){
                //不是最后一节
                getChildVideo(parentVideo_index, ++childVideo_index);
//                adapter.setSelectedPosition(parentVideo_index);
//                adapter.notifyDataSetChanged();
            }else {
                //最后一节 跳转下一章
                int i = parentVideo_index;
                if (++i <parentLists.size()) {

                    //有下一章的情况
                    parentVideo_index++;
                    childVideo_index = -1;
                    playNext();
                }else {
                    Toast.makeText(mContext, "已经是最后一集！", Toast.LENGTH_SHORT).show();
                    return;
                }
            }
        }

    }

    //逻辑有问题
    public void nextVideo(){
        try {
            int index = SharedPreferencesUtils.getValue(mContext, "Settings", "video_index", 5000);
            int childindex = SharedPreferencesUtils.getValue(mContext, "Settings", "video_child_index", 5000);

            if (childindex == 5000){
                //没有二级列表
                int i = index;
                if (++i < parentLists.size()){
                    getParentVideo(++index);
                    adapter.setSelectedPosition(index);
                    adapter.notifyDataSetChanged();
                    SharedPreferencesUtils.putValue(mContext, "Settings", "video_index", index);
                }else {
                    Toast.makeText(mContext, "已经是最后一集！", Toast.LENGTH_SHORT).show();
                    return;
                }
            }else {//有二级列表
                int i = childindex;
                int p = index;
                if (++p <parentLists.size()){
                    if (++i < childLists.size()){
                        getChildVideo(index, ++childindex);
                        SharedPreferencesUtils.putValue(mContext, "Settings", "video_child_index", childindex);//更新childID
                    }else {
                        SharedPreferencesUtils.putValue(mContext, "Settings", "video_index", ++index);
                        SharedPreferencesUtils.putValue(mContext, "Settings", "video_child_index", 0);//子节点从头开始
                        //重新开始
                        //递归调用
                        nextVideo();
                    }
                }else {
                    Toast.makeText(mContext, "已经是最后一集！", Toast.LENGTH_SHORT).show();
                    return;
                }

            }
        }catch (Exception e){

        }

    }

    public void getChildVideo(final int  groupPosition, final int  childPosition)
    {
        StringRequest rq = new StringRequest(Request.Method.POST, ConstantSet.homeAddress + "course/getvideo?", new Response.Listener<String>() {

            @Override
            public void onResponse(String response) {
                // TODO Auto-generated method stub

              //  Toast.makeText(mContext,response+"",Toast.LENGTH_SHORT).show();
                Log.d("Resopnse  ",response);
                if (response.length() > 50) {

                    Gson gson=new Gson();

                    resultVideo = gson.fromJson(response, new TypeToken<ResultVideo>() {
                    }.getType());

                    String s = resultVideo.getData().getWatch_time();
                    String p;
                    Message msg=Message.obtain();
                    msg.arg1=1001;
                    if (s.contains(".")) {
                        String[] sArray = s.split("\\.");
                        p = sArray[0];
                        msg.what = Integer.parseInt(p);
                    }else {
                        msg.what=Integer.parseInt(resultVideo.getData().getWatch_time());
                    }
                    if((resultVideo.getData().getExam()!=null)&&(resultVideo.getData().getExam().size()>0)) {
                        ConstantSet.exam_name.clear();
                        ConstantSet.exam_url.clear();
                        ConstantSet.can_close.clear();

                        int index = resultVideo.getData().getExam().size();
                        msg.arg2 = index;//考试次数传给video

                        //保存考试 链接 等信息
                        for (int i = 0;i<index;i++){
//                            msg.arg2 = Integer.parseInt(resultVideo.getData().getExam().get(i).getVideo_pos());
                            ConstantSet.exam_name.add(resultVideo.getData().getExam().get(i).getExam_name());
                            ConstantSet.exam_url.add(resultVideo.getData().getExam().get(i).getExam_url());
                            ConstantSet.can_close.add(resultVideo.getData().getExam().get(i).getCan_close());
                            ConstantSet.exam_time.add(resultVideo.getData().getExam().get(i).getVideo_pos());//考试时间点
                        }

//                        msg.arg2 = Integer.parseInt(resultVideo.getData().getExam().get(0).getVideo_pos());
//                        ConstantSet.exam_name=resultVideo.getData().getExam().get(0).getExam_name();
//                        ConstantSet.exam_url=resultVideo.getData().getExam().get(0).getExam_url();
//
//                        ConstantSet.can_close=resultVideo.getData().getExam().get(0).getCan_close();
                    }
                    msg.obj=resultVideo.getData().getVideo_title();
                    handler.sendMessage(msg);
                    ConstantSet.section_uid=childLists.get(groupPosition).get(childPosition).getSection_uid();
                    reSetPlayer(player,resultVideo.getData().getStore_value(),resultVideo.getData().getStore_type());
                    parentVideo_index = groupPosition;
                    childVideo_index = childPosition;

                }
            }
        }, new Response.ErrorListener() {

            @Override
            public void onErrorResponse(VolleyError error) {
                // TODO Auto-generated method stub
                Toast.makeText(mContext, "网络请求失败1", Toast.LENGTH_SHORT).show();
            }
        }) {
            @Override
            protected Map<String, String> getParams() throws AuthFailureError {
                // TODO Auto-generated method stub
                Map<String,String> map=new HashMap<String, String>();
                map.put("section_uid",childLists.get(groupPosition).get(childPosition).getSection_uid());//后期改成course_id
                map.put("okey", Md5Utils.md5("mooccoursegetvideo"+childLists.get(groupPosition).get(childPosition).getSection_uid()));
                if(ConstantSet.user!=null) {
                    map.put("user_id", ConstantSet.user.getUid());
                }
                map.put("class_id",ConstantSet.class_id);
                return map;
            }
        };

        MyApplication.getRq().add(rq);

    }



    public void getParentVideo(final int i)
    {
        StringRequest rq = new StringRequest(Request.Method.POST, ConstantSet.homeAddress + "course/getvideo?", new Response.Listener<String>() {

            @Override
            public void onResponse(String response) {
                // TODO Auto-generated method stub
//            try {
                Log.i("parent_video",response);
                //Toast.makeText(mContext,response+"",Toast.LENGTH_SHORT).show();
                if (response.length() > 30) {

                    Gson gson=new Gson();
                    ResultVideo resultVideo = gson.fromJson(response, new TypeToken<ResultVideo>() {
                    }.getType());
                    Message msg=Message.obtain();
                    msg.arg1=1001;
                    String s = resultVideo.getData().getWatch_time();
                    String p;
                    if (s.contains(".")) {
                        String[] sArray = s.split("\\.");
                        p = sArray[0];
                        msg.what = Integer.parseInt(p);
                    }else {
                        msg.what=Integer.parseInt(resultVideo.getData().getWatch_time());
                    }
                    //msg.what 目前观看时间
                    if((resultVideo.getData().getExam()!=null)&&(resultVideo.getData().getExam().size()>0)) {
                        ConstantSet.exam_name.clear();
                        ConstantSet.exam_url.clear();
                        ConstantSet.can_close.clear();

                        int index = resultVideo.getData().getExam().size();
                        msg.arg2 = index;//考试次数传给video

                        //保存考试 链接 等信息
                        for (int i = 0;i<index;i++){
//                            msg.arg2 = Integer.parseInt(resultVideo.getData().getExam().get(i).getVideo_pos());
                            ConstantSet.exam_name.add(resultVideo.getData().getExam().get(i).getExam_name());
                            ConstantSet.exam_url.add(resultVideo.getData().getExam().get(i).getExam_url());
                            ConstantSet.can_close.add(resultVideo.getData().getExam().get(i).getCan_close());
                            ConstantSet.exam_time.add(resultVideo.getData().getExam().get(i).getVideo_pos());//考试时间点
                        }
                        //旧版只支持1此考试
//                        msg.arg2 = Integer.parseInt(resultVideo.getData().getExam().get(0).getVideo_pos());
//                        ConstantSet.exam_name=resultVideo.getData().getExam().get(0).getExam_name();
//                        ConstantSet.exam_url=resultVideo.getData().getExam().get(0).getExam_url();
//                        ConstantSet.can_close=resultVideo.getData().getExam().get(0).getCan_close();

                    }
                    msg.obj=resultVideo.getData().getVideo_title();
                    handler.sendMessage(msg);

                    if(resultVideo.getData().getStore_value()!=null&&(!resultVideo.getData().getStore_value().equals(""))) {
                        ConstantSet.section_uid = parentLists.get(i).getSection_uid();
                        reSetPlayer(player, resultVideo.getData().getStore_value(), resultVideo.getData().getStore_type());
                        parentVideo_index = i;
                        Toast.makeText(mContext,"请稍等",Toast.LENGTH_SHORT).show();
                    }else
                    {
                        Toast.makeText(mContext,"视频不存在",Toast.LENGTH_SHORT).show();
                    }

                }
//            }
//            catch (Exception e){
//                Toast.makeText(mContext, "貌似出了点问题~", Toast.LENGTH_SHORT).show();
//
//            }

            }
        }, new Response.ErrorListener() {

            @Override
            public void onErrorResponse(VolleyError error) {
                // TODO Auto-generated method stub
                Toast.makeText(mContext, "网络请求失败1", Toast.LENGTH_SHORT).show();
            }
        }) {
            @Override
            protected Map<String, String> getParams() throws AuthFailureError {
                // TODO Auto-generated method stub
                Map<String,String> map=new HashMap<String, String>();
                Log.i("TAGTAG", "i="+i);
                map.put("section_uid",parentLists.get(i).getSection_uid());//后期改成course_id
                if(ConstantSet.user!=null) {
                    map.put("user_id", ConstantSet.user.getUid());
                }
                map.put("class_id",ConstantSet.class_id);
                map.put("okey", Md5Utils.md5("mooccoursegetvideo"+parentLists.get(i).getSection_uid()));
                return map;
            }
        };

        MyApplication.getRq().add(rq);
    }

    private void reSetPlayer(DWMediaPlayer player, String store_value, String store_type)
    {
        
        player.reset();
        if(store_type.equals("2"))
        {
            player.setVideoPlayInfo(store_value, "EB85B37C4546E6A4", "SsJifp5ht0KJUzgiEGACUrpVNYLdaAkR",mContext);
            player.prepareAsync();

            Message msg=Message.obtain();
            msg.arg1=1001;
            if(resultVideo!=null) {
                msg.obj = resultVideo.getData().getVideo_title();
            }
            handler.sendMessage(msg);

        }else if(store_type.equals("1"))
        {
            try {
                if(store_value!=null&&store_value!="") {
                    player.setDataSource(mContext, Uri.parse(store_value));
                    player.prepareAsync();
                }
            } catch (IOException e) {
                e.printStackTrace();
            }
        }

    }

    public int zhuanMiao(String time)
    {
        return Integer.parseInt(time.substring(0,2))*3600+Integer.parseInt(time.substring(3,5))*60+Integer.parseInt(time.substring(6,8));
    }
    
    
}
