package cn.com.mbmpv.trainingonline.adapter;

import android.content.Context;
import android.util.Log;
import android.view.Gravity;
import android.view.View;
import android.view.ViewGroup;
import android.view.Window;
import android.widget.BaseAdapter;
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
import java.util.List;
import java.util.Map;

import cn.com.mbmpv.trainingonline.R;
import cn.com.mbmpv.trainingonline.application.MyApplication;
import cn.com.mbmpv.trainingonline.bean.Question;
import cn.com.mbmpv.trainingonline.bean.Sign;
import cn.com.mbmpv.trainingonline.utils.ConstantSet;
import cn.com.mbmpv.trainingonline.utils.LoadImgUtils;
import cn.com.mbmpv.trainingonline.utils.Md5Utils;
import cn.com.mbmpv.trainingonline.widget.CircularImage;
import cn.com.mbmpv.trainingonline.widget.DuihuaDialog;

public class VideoView3ListViewAdapter extends BaseAdapter {


    List<Question> list;
    Context mContext;


    public VideoView3ListViewAdapter(List<Question> list, Context mContext) {
        super();
        this.list = list;
        this.mContext = mContext;
    }


    @Override
    public int getCount() {
        // TODO Auto-generated method stub
        return list.size();
    }

    @Override
    public Object getItem(int position) {
        // TODO Auto-generated method stub

        return list.get(position);
    }

    @Override
    public long getItemId(int position) {
        // TODO Auto-generated method stub
        return position;
    }

    @Override
    public View getView(final int position, View convertView, ViewGroup parent) {
        // TODO Auto-generated method stub

        ViewHolder viewHolder = null;
        if (convertView == null) {
            viewHolder = new ViewHolder();
            convertView = View.inflate(mContext, R.layout.view3_question_item, null);

            viewHolder.headImg = (CircularImage) convertView.findViewById(R.id.head_img);
            viewHolder.userNick = (TextView) convertView.findViewById(R.id.user_nick);
            viewHolder.dateText = (TextView) convertView.findViewById(R.id.date);
            viewHolder.contentText = (TextView) convertView.findViewById(R.id.content);
            viewHolder.chakanText = (TextView) convertView.findViewById(R.id.chakan_text);
            viewHolder.chakanBar = (LinearLayout) convertView.findViewById(R.id.chakan_bar);
            viewHolder.caiImg = (ImageView) convertView.findViewById(R.id.cai_bt);
            viewHolder.zanImg = (ImageView) convertView.findViewById(R.id.zan_bt);
            viewHolder.num1 = (TextView) convertView.findViewById(R.id.num_1);
            viewHolder.num2 = (TextView) convertView.findViewById(R.id.num_2);

            convertView.setTag(viewHolder);
        } else {
            viewHolder = (ViewHolder) convertView.getTag();
        }

        LoadImgUtils.setImage(mContext, list.get(position).getUserInfo().getAvatar(), viewHolder.headImg);
        viewHolder.userNick.setText(list.get(position).getUserInfo().getNickname());
        viewHolder.dateText.setText(list.get(position).getCdate());
        viewHolder.contentText.setText(list.get(position).getContent());
        viewHolder.num1.setText(list.get(position).getNum_down());
        viewHolder.num2.setText(list.get(position).getNum_up());

        if (list.get(position).getCount().equals("0")) {
            viewHolder.chakanBar.setVisibility(View.GONE);
        } else {
            viewHolder.chakanBar.setVisibility(View.VISIBLE);
            viewHolder.chakanText.setText("问题回复（" + list.get(position).getCount() + ")");
        }
        
//		viewHolder.chakanText.setOnClickListener(new View.OnClickListener() {
//			@Override
//			public void onClick(View view) {
//
//				DuihuaDialog dialog = new DuihuaDialog(mContext);
//
//				Window window = dialog.getWindow();
//				window.setGravity(Gravity.BOTTOM);
//				dialog.setCanceledOnTouchOutside(true);// 设置点击Dialog外部任意区域关闭Dialog
//				dialog.show();
//			}
//		});


        viewHolder.chakanBar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                ConstantSet.obj_id = list.get(position).getUid(); //uid
                ConstantSet.uid = list.get(position).getUser_id();//user_id
                Log.i("TAGTAG", ConstantSet.obj_id);
                DuihuaDialog dialog = new DuihuaDialog(mContext);

                Window window = dialog.getWindow();
                window.setGravity(Gravity.BOTTOM);
                dialog.setCanceledOnTouchOutside(true);// 设置点击Dialog外部任意区域关闭Dialog
                dialog.show();
            }
        });

        viewHolder.zanImg.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                dianzan(list.get(position).getUid(),position);
            }
        });


        viewHolder.caiImg.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                cai(list.get(position).getUid(),position);
            }
        });

        return convertView;
    }


    class ViewHolder {

        CircularImage headImg;
        TextView userNick;
        TextView dateText;
        TextView contentText;
        TextView chakanText;
        ImageView chakanImg;

        LinearLayout chakanBar;
        ImageView caiImg;
        ImageView zanImg;

        TextView num1;
        TextView num2;

    }


    public void dianzan(final String questoin_id,final int i) {
        StringRequest rq = new StringRequest(Request.Method.POST, ConstantSet.homeAddress + "actions/doactions?", new Response.Listener<String>() {

            @Override
            public void onResponse(String response) {
                // TODO Auto-generated method stub

                System.out.print("response  " + response + "    " + response.length());
                //showShortToast(response);
                if (response.length() > 20) {
					
                    Gson gson = new Gson();
                    Sign resultUser = gson.fromJson(response, new TypeToken<Sign>() {
                    }.getType());

                    if (resultUser.getStatus() == 1) {
                        
                        list.get(i).setNum_up((Integer.parseInt(list.get(i).getNum_up())+1)+"");
                        notifyDataSetChanged();
                        Toast.makeText(mContext, "成功一赞", Toast.LENGTH_SHORT).show();
                    }
                    else{
                        Toast.makeText(mContext, "不能重复点赞", Toast.LENGTH_SHORT).show();
                    }
                }
            }
        }, new Response.ErrorListener() {

            @Override
            public void onErrorResponse(VolleyError error) {
                // TODO Auto-generated method stub
                Toast.makeText(mContext, "网络请求失败", Toast.LENGTH_SHORT).show();

            }
        }) {
            @Override
            protected Map<String, String> getParams() throws AuthFailureError {
                // TODO Auto-generated method stub
                Map<String, String> map = new HashMap<String, String>();
                map.put("obj_type", "userup");
                map.put("obj_id", questoin_id);
                if (ConstantSet.user != null) {
                    map.put("user_uid", ConstantSet.user.getUid());
                }
                map.put("action_type", "user");
                map.put("target_type", "up");
                map.put("okey", Md5Utils.md5("moocuserupuserup" + questoin_id));

                return map;
            }
        };

        MyApplication.getRq().add(rq);

    }


    public void cai(final String questoin_id,final int i) {
        StringRequest rq = new StringRequest(Request.Method.POST, ConstantSet.homeAddress + "actions/doactions?", new Response.Listener<String>() {

            @Override
            public void onResponse(String response) {
                // TODO Auto-generated method stub

                System.out.print("response  " + response + "    " + response.length());
                //showShortToast(response);
                if (response.length() > 20) {   


                    Gson gson = new Gson();
                    Sign resultUser = gson.fromJson(response, new TypeToken<Sign>() {
                    }.getType());

                    if (resultUser.getStatus()== 1) {

                        list.get(i).setNum_down((Integer.parseInt(list.get(i).getNum_down())+1)+"");
                        notifyDataSetChanged();
                        Toast.makeText(mContext, "成功一踩", Toast.LENGTH_SHORT).show();
                    }else
                    {
                        Toast.makeText(mContext, "不能重复踩", Toast.LENGTH_SHORT).show();
                    }


                }
            }
        }, new Response.ErrorListener() {

            @Override
            public void onErrorResponse(VolleyError error) {
                // TODO Auto-generated method stub
                Toast.makeText(mContext, "网络请求失败", Toast.LENGTH_SHORT).show();

            }
        }) {
            @Override
            protected Map<String, String> getParams() throws AuthFailureError {
                // TODO Auto-generated method stub
                Map<String, String> map = new HashMap<String, String>();
                map.put("obj_type", "userdown");
                map.put("obj_id", questoin_id);
                if (ConstantSet.user != null) {
                    map.put("user_uid", ConstantSet.user.getUid());
                }
                map.put("action_type", "user");
                map.put("target_type", "down");
                map.put("okey", Md5Utils.md5("moocuserdownuserdown" + questoin_id));

                return map;
            }
        };

        MyApplication.getRq().add(rq);
    }

}
