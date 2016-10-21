package cn.com.mbmpv.trainingonline.adapter;

import android.content.Context;
import android.content.Intent;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;

import java.util.List;

import cn.com.mbmpv.trainingonline.R;
import cn.com.mbmpv.trainingonline.bean.LessonBlockKc;
import cn.com.mbmpv.trainingonline.ui.TypeActivity;
import cn.com.mbmpv.trainingonline.ui.VideoActivity;
import cn.com.mbmpv.trainingonline.ui.VideoActivityFirst;
import cn.com.mbmpv.trainingonline.utils.ConstantSet;
import cn.com.mbmpv.trainingonline.utils.LoadImgUtils;

/**
 * Created by jiuzheyange on 2016/8/9.
 */
public class KechengListAdapter extends BaseAdapter{
    
    List<LessonBlockKc> lists;
    Context mContext;

    public KechengListAdapter(List<LessonBlockKc> lists, Context mContext) {
        this.lists = lists;
        this.mContext = mContext;
    }

    @Override
    public int getCount() {
        return lists.size();
    }

    @Override
    public Object getItem(int i) {
        return lists.get(i);
    }

    @Override
    public long getItemId(int i) {
        return i;
    }

    @Override
    public View getView(final int i, View view, ViewGroup viewGroup) {


        view=View.inflate(mContext, R.layout.kecheng_fragment_list_item,null);
        ImageView next_img = (ImageView) view.findViewById(R.id.next_bt);
        TextView titleName = (TextView) view.findViewById(R.id.title_name);

        titleName.setText(lists.get(i).getLabel());

        ImageView img1 = (ImageView) view.findViewById(R.id.img1);
        ImageView img2 = (ImageView) view.findViewById(R.id.img2);
        ImageView img3 = (ImageView) view.findViewById(R.id.img3);
        ImageView img4 = (ImageView) view.findViewById(R.id.img4);
        ImageView img5 = (ImageView) view.findViewById(R.id.img5);
        ImageView img6 = (ImageView) view.findViewById(R.id.img6);


        RelativeLayout imgBar1= (RelativeLayout) view.findViewById(R.id.img1_bar1);
        RelativeLayout imgBar2= (RelativeLayout) view.findViewById(R.id.img1_bar2);
        RelativeLayout imgBar3= (RelativeLayout) view.findViewById(R.id.img1_bar3);
        RelativeLayout imgBar4= (RelativeLayout) view.findViewById(R.id.img1_bar4);
        RelativeLayout imgBar5= (RelativeLayout) view.findViewById(R.id.img1_bar5);
        RelativeLayout imgBar6= (RelativeLayout) view.findViewById(R.id.img1_bar6);
        
        
        TextView text1= (TextView) view.findViewById(R.id.text1);
        TextView text2= (TextView) view.findViewById(R.id.text2);
        TextView text3= (TextView) view.findViewById(R.id.text3);
        TextView text4= (TextView) view.findViewById(R.id.text4);
        TextView text5= (TextView) view.findViewById(R.id.text5);
        TextView text6= (TextView) view.findViewById(R.id.text6);


        img1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (!(isAdd(lists.get(i).getItem().get(0).getVal()))) {
                    ConstantSet.course_id = lists.get(i).getItem().get(0).getVal();
                    ConstantSet.videoTitle = lists.get(i).getItem().get(0).getTitle();

                    mContext.startActivity(new Intent(mContext, VideoActivityFirst.class));
                }
                else if (isAdd(lists.get(i).getItem().get(0).getVal())) {
                    ConstantSet.course_id = lists.get(i).getItem().get(0).getVal();
                    ConstantSet.videoTitle = lists.get(i).getItem().get(0).getTitle();

                    Intent i= new Intent(mContext, VideoActivity.class);
                    i.putExtra("My", "");
                    mContext.startActivity(i);
                }
            }
        });


        img2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                if (!(isAdd(lists.get(i).getItem().get(1).getVal()))) {
                    ConstantSet.course_id = lists.get(i).getItem().get(1).getVal();
                    ConstantSet.videoTitle = lists.get(i).getItem().get(1).getTitle();

                    mContext.startActivity(new Intent(mContext, VideoActivityFirst.class));
                }
                else if (isAdd(lists.get(i).getItem().get(1).getVal())) {
                    ConstantSet.course_id = lists.get(i).getItem().get(1).getVal();
                    ConstantSet.videoTitle = lists.get(i).getItem().get(1).getTitle();

                    Intent i= new Intent(mContext, VideoActivity.class);
                    i.putExtra("My", "");
                    mContext.startActivity(i);

                }
            }
        });

        img3.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (!(isAdd(lists.get(i).getItem().get(2).getVal()))) {
                    ConstantSet.course_id = lists.get(i).getItem().get(2).getVal();
                    ConstantSet.videoTitle = lists.get(i).getItem().get(2).getTitle();

                    mContext.startActivity(new Intent(mContext, VideoActivityFirst.class));
                }
                else if (isAdd(lists.get(i).getItem().get(2).getVal())) {
                    ConstantSet.course_id = lists.get(i).getItem().get(2).getVal();
                    ConstantSet.videoTitle = lists.get(i).getItem().get(2).getTitle();


                    Intent i= new Intent(mContext, VideoActivity.class);
                    i.putExtra("My", "");
                    mContext.startActivity(i);
//                    mContext.startActivity(new Intent(mContext, VideoActivity.class));
                }
            }
        });

        img4.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (!(isAdd(lists.get(i).getItem().get(3).getVal()))) {
                    ConstantSet.course_id = lists.get(i).getItem().get(3).getVal();
                    ConstantSet.videoTitle = lists.get(i).getItem().get(3).getTitle();

                    mContext.startActivity(new Intent(mContext, VideoActivityFirst.class));
                }
                else if (isAdd(lists.get(i).getItem().get(3).getVal())) {
                    ConstantSet.course_id = lists.get(i).getItem().get(3).getVal();
                    ConstantSet.videoTitle = lists.get(i).getItem().get(3).getTitle();

                    Intent i= new Intent(mContext, VideoActivity.class);
                    i.putExtra("My", "");
                    mContext.startActivity(i);

//                    mContext.startActivity(new Intent(mContext, VideoActivity.class));
                }
            }
        });

        img5.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (!(isAdd(lists.get(i).getItem().get(4).getVal()))) {
                    ConstantSet.course_id = lists.get(i).getItem().get(4).getVal();
                    ConstantSet.videoTitle = lists.get(i).getItem().get(4).getTitle();

                    mContext.startActivity(new Intent(mContext, VideoActivityFirst.class));
                }
                else if (isAdd(lists.get(i).getItem().get(4).getVal())) {
                    ConstantSet.course_id = lists.get(i).getItem().get(4).getVal();
                    ConstantSet.videoTitle = lists.get(i).getItem().get(4).getTitle();

                    Intent i= new Intent(mContext, VideoActivity.class);
                    i.putExtra("My", "");
                    mContext.startActivity(i);

//                    mContext.startActivity(new Intent(mContext, VideoActivity.class));
                }
            }
        });

        img6.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (!(isAdd(lists.get(i).getItem().get(5).getVal()))) {
                    ConstantSet.course_id = lists.get(i).getItem().get(5).getVal();
                    ConstantSet.videoTitle = lists.get(i).getItem().get(5).getTitle();

                    mContext.startActivity(new Intent(mContext, VideoActivityFirst.class));
                }
                else if (isAdd(lists.get(i).getItem().get(5).getVal())) {
                    ConstantSet.course_id = lists.get(i).getItem().get(5).getVal();
                    ConstantSet.videoTitle = lists.get(i).getItem().get(5).getTitle();

                    Intent i= new Intent(mContext, VideoActivity.class);
                    i.putExtra("My", "");
                    mContext.startActivity(i);

//                    mContext.startActivity(new Intent(mContext, VideoActivity.class));
                }
            }
        });
        

        if(lists.get(i).getItem()!=null) {
            if (lists.get(i).getItem().size() == 1) {
                LoadImgUtils.setImage(mContext,lists.get(i).getItem().get(0).getImg(), img1);

                if (ConstantSet.confiMap != null) {
                    if (ConstantSet.confiMap.get("App.Switch.Course.MaskInfo").equals("1")) {
                        text1.setText(lists.get(i).getItem().get(0).getTitle());
                    }}
                imgBar1.setVisibility(View.VISIBLE);
                

            } else if (lists.get(i).getItem().size() == 2) {
                LoadImgUtils.setImage(mContext,lists.get(i).getItem().get(0).getImg(), img1);
                LoadImgUtils.setImage(mContext,lists.get(i).getItem().get(1).getImg(), img2);

                if (ConstantSet.confiMap != null) {
                    if (ConstantSet.confiMap.get("App.Switch.Course.MaskInfo").equals("1")) {
                        text1.setText(lists.get(i).getItem().get(0).getTitle());
                        text2.setText(lists.get(i).getItem().get(1).getTitle());
                    }}
                
                imgBar1.setVisibility(View.VISIBLE);
                imgBar2.setVisibility(View.VISIBLE);

            } else if (lists.get(i).getItem().size() == 3) {
                LoadImgUtils.setImage(mContext,lists.get(i).getItem().get(0).getImg(), img1);
                LoadImgUtils.setImage(mContext,lists.get(i).getItem().get(1).getImg(), img2);
                LoadImgUtils.setImage(mContext,lists.get(i).getItem().get(2).getImg(), img3);
                if (ConstantSet.confiMap != null) {
                    if (ConstantSet.confiMap.get("App.Switch.Course.MaskInfo").equals("1")) {
                        text1.setText(lists.get(i).getItem().get(0).getTitle());
                        text2.setText(lists.get(i).getItem().get(1).getTitle());
                        text3.setText(lists.get(i).getItem().get(2).getTitle());
                    }}
                
                imgBar1.setVisibility(View.VISIBLE);
                imgBar2.setVisibility(View.VISIBLE);
                imgBar3.setVisibility(View.VISIBLE);

            } else if (lists.get(i).getItem().size() == 4) {
                LoadImgUtils.setImage(mContext,lists.get(i).getItem().get(0).getImg(), img1);
                LoadImgUtils.setImage(mContext,lists.get(i).getItem().get(1).getImg(), img2);
                LoadImgUtils.setImage(mContext,lists.get(i).getItem().get(2).getImg(), img3);
                LoadImgUtils.setImage(mContext,lists.get(i).getItem().get(3).getImg(), img4);

                if (ConstantSet.confiMap != null) {
                    if (ConstantSet.confiMap.get("App.Switch.Course.MaskInfo").equals("1")) {
                        text1.setText(lists.get(i).getItem().get(0).getTitle());
                        text2.setText(lists.get(i).getItem().get(1).getTitle());
                        text3.setText(lists.get(i).getItem().get(2).getTitle());
                        text4.setText(lists.get(i).getItem().get(3).getTitle());
                    }}
                
                imgBar1.setVisibility(View.VISIBLE);
                imgBar2.setVisibility(View.VISIBLE);
                imgBar3.setVisibility(View.VISIBLE);
                imgBar4.setVisibility(View.VISIBLE);

            } else if (lists.get(i).getItem().size() == 5) {
                LoadImgUtils.setImage(mContext,lists.get(i).getItem().get(0).getImg(), img1);
                LoadImgUtils.setImage(mContext,lists.get(i).getItem().get(1).getImg(), img2);
                LoadImgUtils.setImage(mContext,lists.get(i).getItem().get(2).getImg(), img3);
                LoadImgUtils.setImage(mContext,lists.get(i).getItem().get(3).getImg(), img4);
                LoadImgUtils.setImage(mContext,lists.get(i).getItem().get(4).getImg(), img5);

                if (ConstantSet.confiMap != null) {
                    if (ConstantSet.confiMap.get("App.Switch.Course.MaskInfo").equals("1")) {
                        text1.setText(lists.get(i).getItem().get(0).getTitle());
                        text2.setText(lists.get(i).getItem().get(1).getTitle());
                        text3.setText(lists.get(i).getItem().get(2).getTitle());
                        text4.setText(lists.get(i).getItem().get(3).getTitle());
                        text5.setText(lists.get(i).getItem().get(4).getTitle());
                    }}
                

                imgBar1.setVisibility(View.VISIBLE);
                imgBar2.setVisibility(View.VISIBLE);
                imgBar3.setVisibility(View.VISIBLE);
                imgBar4.setVisibility(View.VISIBLE);
                imgBar5.setVisibility(View.VISIBLE);
                
            } else if (lists.get(i).getItem().size() == 6) {
                LoadImgUtils.setImage(mContext,lists.get(i).getItem().get(0).getImg(), img1);
                LoadImgUtils.setImage(mContext,lists.get(i).getItem().get(1).getImg(), img2);
                LoadImgUtils.setImage(mContext,lists.get(i).getItem().get(2).getImg(), img3);
                LoadImgUtils.setImage(mContext,lists.get(i).getItem().get(3).getImg(), img4);
                LoadImgUtils.setImage(mContext,lists.get(i).getItem().get(4).getImg(), img5);
                LoadImgUtils.setImage(mContext,lists.get(i).getItem().get(5).getImg(), img6);

                if (ConstantSet.confiMap != null) {
                    if (ConstantSet.confiMap.get("App.Switch.Course.MaskInfo").equals("1")) {
                        text1.setText(lists.get(i).getItem().get(0).getTitle());
                        text2.setText(lists.get(i).getItem().get(1).getTitle());
                        text3.setText(lists.get(i).getItem().get(2).getTitle());
                        text4.setText(lists.get(i).getItem().get(3).getTitle());
                        text5.setText(lists.get(i).getItem().get(4).getTitle());
                        text6.setText(lists.get(i).getItem().get(5).getTitle());
                    }}

                imgBar1.setVisibility(View.VISIBLE);
                imgBar2.setVisibility(View.VISIBLE);
                imgBar3.setVisibility(View.VISIBLE);
                imgBar4.setVisibility(View.VISIBLE);
                imgBar5.setVisibility(View.VISIBLE);
                imgBar6.setVisibility(View.VISIBLE);
            }
        }


        next_img.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                
                ConstantSet.cancel="kecheng";
                if(lists.get(i).getAction().getInfo()!=null&&lists.get(i).getAction().getInfo().size()!=0) {
                    ConstantSet.tag = lists.get(i).getAction().getInfo().get(0);
                    ConstantSet.keyWord=lists.get(i).getLabel();
                }
                else
                {
                    ConstantSet.tag="";
                }
                mContext.startActivity(new Intent(mContext, TypeActivity.class));
                
            }
        });
       
        return view;
    }

    public boolean isAdd(String course_id) {
        for (int i = 0; i < ConstantSet.myClassList.size(); i++) {
            if (ConstantSet.myClassList.get(i).getImpower_id().equals(course_id)) {
                return true;
            }
        }

        return false;
    }
    
}
