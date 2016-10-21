package cn.com.mbmpv.trainingonline.view;

import android.content.Context;
import android.content.Intent;
import android.graphics.Color;
import android.os.Handler;
import android.os.Message;
import android.support.v4.view.ViewPager;
import android.util.Log;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.android.volley.Request;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.bumptech.glide.Glide;
import com.google.gson.Gson;

import java.util.ArrayList;
import java.util.List;

import cn.com.mbmpv.trainingonline.R;
import cn.com.mbmpv.trainingonline.adapter.GongGaoListViewAdapter;
import cn.com.mbmpv.trainingonline.adapter.HomeTopPagerAdapter;
import cn.com.mbmpv.trainingonline.application.MyApplication;
import cn.com.mbmpv.trainingonline.bean.Banner;
import cn.com.mbmpv.trainingonline.bean.GongGaoBean;
import cn.com.mbmpv.trainingonline.ui.GongGaoActivity;
import cn.com.mbmpv.trainingonline.ui.TypeActivity;
import cn.com.mbmpv.trainingonline.ui.VideoActivity;
import cn.com.mbmpv.trainingonline.ui.VideoActivityFirst;
import cn.com.mbmpv.trainingonline.utils.ConstantSet;
import cn.com.mbmpv.trainingonline.widget.VerticalTextview;
import me.relex.circleindicator.CircleIndicator;

/**
 * Created by jiuzheyange on 2016/8/9.
 */
public class HomeFragmentHeadView implements View.OnClickListener {

    Context mContext;
    View mView;

    ViewPager homeViewPager;
    List<View> lists;
    List<Banner> listBanner;
    List<Banner> categoryList;

    HomeTopPagerAdapter adapter;

    List<GongGaoBean.DataBean> announcelists;
    List<String>  textlist;
    GongGaoListViewAdapter announceadapter;


    ImageView img1;
    ImageView img2;
    ImageView img3;
    ImageView img4;
    ImageView img5;
    ImageView img6;
    ImageView img7;
    ImageView img8;

    TextView text1;
    TextView text2;
    TextView text3;
    TextView text4;
    TextView text5;
    TextView text6;
    TextView text7;
    TextView text8;

    VerticalTextview textbanner;

    LinearLayout imgBar1;
    LinearLayout imgBar2;
    LinearLayout textBar1;
    LinearLayout textBar2;


    Handler handler = new Handler() {
        @Override
        public void handleMessage(Message msg) {
            super.handleMessage(msg);

            if (msg.arg1 == 10001) {
                if (homeViewPager.getCurrentItem() < lists.size() - 1) {
                    homeViewPager.setCurrentItem(homeViewPager.getCurrentItem() + 1);
                } else {
                    homeViewPager.setCurrentItem(0);
                }
            }

        }
    };

    public HomeFragmentHeadView(Context mContext) {
        this.mContext = mContext;
    }

    public View getView(List<Banner> listBanner, List<Banner> categoryList) {
        mView = View.inflate(mContext, R.layout.homefragment_headview, null);
        this.listBanner = listBanner;
        this.categoryList = categoryList;
        initView();
        initData();
        initEvent();

        return mView;
    }


    protected void initView() {


        homeViewPager = (ViewPager) mView.findViewById(R.id.homefragment_viewpager);


        img1 = (ImageView) mView.findViewById(R.id.guanli_img);
        img2 = (ImageView) mView.findViewById(R.id.zhujichang_bt);
        img3 = (ImageView) mView.findViewById(R.id.weixiu_img);
        img4 = (ImageView) mView.findViewById(R.id.jinrong_img);
        img5 = (ImageView) mView.findViewById(R.id.banjin_img);
        img6 = (ImageView) mView.findViewById(R.id.meirong_img);
        img7 = (ImageView) mView.findViewById(R.id.pinggu_img);
        img8 = (ImageView) mView.findViewById(R.id.gengduo_img);

        text1 = (TextView) mView.findViewById(R.id.guanli_text);
        text2 = (TextView) mView.findViewById(R.id.zhujichang_text);
        text3 = (TextView) mView.findViewById(R.id.weixiu_text);
        text4 = (TextView) mView.findViewById(R.id.jinrong_text);
        text5 = (TextView) mView.findViewById(R.id.banjin_text);
        text6 = (TextView) mView.findViewById(R.id.meirong_text);
        text7 = (TextView) mView.findViewById(R.id.pinggu_text);
        text8 = (TextView) mView.findViewById(R.id.gengduo_text);

        textbanner = (VerticalTextview) mView.findViewById(R.id.textbanner);
        textlist = new ArrayList<>();
        announcelists = new ArrayList<>();
        fetchAnnounce();



        img1.setOnClickListener(this);
        img2.setOnClickListener(this);
        img3.setOnClickListener(this);
        img4.setOnClickListener(this);
        img5.setOnClickListener(this);
        img6.setOnClickListener(this);
        img7.setOnClickListener(this);
        img8.setOnClickListener(this);


        text1.setOnClickListener(this);
        text2.setOnClickListener(this);
        text3.setOnClickListener(this);
        text4.setOnClickListener(this);
        text5.setOnClickListener(this);
        text6.setOnClickListener(this);
        text7.setOnClickListener(this);
        text8.setOnClickListener(this);


        imgBar1 = (LinearLayout) mView.findViewById(R.id.img1);
        imgBar2 = (LinearLayout) mView.findViewById(R.id.img2);
        textBar1 = (LinearLayout) mView.findViewById(R.id.text1);
        textBar2 = (LinearLayout) mView.findViewById(R.id.text2);


        if (categoryList != null) {
            if (categoryList.size() == 0) {
                imgBar1.setVisibility(View.GONE);
                imgBar2.setVisibility(View.GONE);
                textBar1.setVisibility(View.GONE);
                textBar2.setVisibility(View.GONE);
            } else if (categoryList.size() == 1) {

                img2.setVisibility(View.INVISIBLE);
                img3.setVisibility(View.INVISIBLE);
                img4.setVisibility(View.INVISIBLE);

                text2.setVisibility(View.INVISIBLE);
                text3.setVisibility(View.INVISIBLE);
                text4.setVisibility(View.INVISIBLE);

                imgBar2.setVisibility(View.GONE);
                textBar2.setVisibility(View.GONE);

                setImage(categoryList.get(0).getImg(), img1);

                text1.setText(categoryList.get(0).getTitle());
            } else if (categoryList.size() == 2) {


                img3.setVisibility(View.INVISIBLE);
                img4.setVisibility(View.INVISIBLE);

                text3.setVisibility(View.INVISIBLE);
                text4.setVisibility(View.INVISIBLE);

                imgBar2.setVisibility(View.GONE);
                textBar2.setVisibility(View.GONE);

                setImage(categoryList.get(0).getImg(), img1);
                setImage(categoryList.get(1).getImg(), img2);

                text1.setText(categoryList.get(0).getTitle());
                text2.setText(categoryList.get(1).getTitle());

            } else if (categoryList.size() == 3) {


                img4.setVisibility(View.INVISIBLE);


                text4.setVisibility(View.INVISIBLE);

                imgBar2.setVisibility(View.GONE);
                textBar2.setVisibility(View.GONE);

                setImage(categoryList.get(0).getImg(), img1);
                setImage(categoryList.get(1).getImg(), img2);
                setImage(categoryList.get(2).getImg(), img3);

                text1.setText(categoryList.get(0).getTitle());
                text2.setText(categoryList.get(1).getTitle());
                text3.setText(categoryList.get(2).getTitle());

            } else if (categoryList.size() == 4) {

                imgBar2.setVisibility(View.GONE);
                textBar2.setVisibility(View.GONE);


                setImage(categoryList.get(0).getImg(), img1);
                setImage(categoryList.get(1).getImg(), img2);
                setImage(categoryList.get(2).getImg(), img3);
                setImage(categoryList.get(3).getImg(), img4);

                text1.setText(categoryList.get(0).getTitle());
                text2.setText(categoryList.get(1).getTitle());
                text3.setText(categoryList.get(2).getTitle());
                text4.setText(categoryList.get(3).getTitle());
            } else if (categoryList.size() == 5) {

                img6.setVisibility(View.INVISIBLE);
                img7.setVisibility(View.INVISIBLE);
                img8.setVisibility(View.INVISIBLE);

                text6.setVisibility(View.INVISIBLE);
                text7.setVisibility(View.INVISIBLE);
                text8.setVisibility(View.INVISIBLE);


                setImage(categoryList.get(0).getImg(), img1);
                setImage(categoryList.get(1).getImg(), img2);
                setImage(categoryList.get(2).getImg(), img3);
                setImage(categoryList.get(3).getImg(), img4);
                setImage(categoryList.get(4).getImg(), img5);

                text1.setText(categoryList.get(0).getTitle());
                text2.setText(categoryList.get(1).getTitle());
                text3.setText(categoryList.get(2).getTitle());
                text4.setText(categoryList.get(3).getTitle());
                text5.setText(categoryList.get(4).getTitle());
            } else if (categoryList.size() == 6) {
                img7.setVisibility(View.INVISIBLE);
                img8.setVisibility(View.INVISIBLE);

                text7.setVisibility(View.INVISIBLE);
                text8.setVisibility(View.INVISIBLE);

                setImage(categoryList.get(0).getImg(), img1);
                setImage(categoryList.get(1).getImg(), img2);
                setImage(categoryList.get(2).getImg(), img3);
                setImage(categoryList.get(3).getImg(), img4);
                setImage(categoryList.get(4).getImg(), img5);
                setImage(categoryList.get(5).getImg(), img6);

                text1.setText(categoryList.get(0).getTitle());
                text2.setText(categoryList.get(1).getTitle());
                text3.setText(categoryList.get(2).getTitle());
                text4.setText(categoryList.get(3).getTitle());
                text5.setText(categoryList.get(4).getTitle());
                text6.setText(categoryList.get(5).getTitle());
            } else if (categoryList.size() == 7) {

                img8.setVisibility(View.INVISIBLE);

                text8.setVisibility(View.INVISIBLE);


                setImage(categoryList.get(0).getImg(), img1);
                setImage(categoryList.get(1).getImg(), img2);
                setImage(categoryList.get(2).getImg(), img3);
                setImage(categoryList.get(3).getImg(), img4);
                setImage(categoryList.get(4).getImg(), img5);
                setImage(categoryList.get(5).getImg(), img6);
                setImage(categoryList.get(6).getImg(), img7);

                text1.setText(categoryList.get(0).getTitle());
                text2.setText(categoryList.get(1).getTitle());
                text3.setText(categoryList.get(2).getTitle());
                text4.setText(categoryList.get(3).getTitle());
                text5.setText(categoryList.get(4).getTitle());
                text6.setText(categoryList.get(5).getTitle());
                text7.setText(categoryList.get(6).getTitle());

            } else {

                //给第一个控件加载图片
                setImage(categoryList.get(0).getImg(), img1);
                setImage(categoryList.get(1).getImg(), img2);
                setImage(categoryList.get(2).getImg(), img3);
                setImage(categoryList.get(3).getImg(), img4);
                setImage(categoryList.get(4).getImg(), img5);
                setImage(categoryList.get(5).getImg(), img6);
                setImage(categoryList.get(6).getImg(), img7);
                setImage(categoryList.get(7).getImg(), img8);

                text1.setText(categoryList.get(0).getTitle());
                text2.setText(categoryList.get(1).getTitle());
                text3.setText(categoryList.get(2).getTitle());
                text4.setText(categoryList.get(3).getTitle());
                text5.setText(categoryList.get(4).getTitle());
                text6.setText(categoryList.get(5).getTitle());
                text7.setText(categoryList.get(6).getTitle());
                text8.setText(categoryList.get(7).getTitle());

            }
        }


    }

    void initTextBannerData(int k){
        if (k<= 3){
            for (int i = 0; i<k;i++){
                textlist.add(announcelists.get(i).getTitle());
            }
            textbanner.setTextList((ArrayList<String>) textlist);
            textbanner.setText(16, 5, Color.BLACK);//设置属性,具体跟踪源码
            textbanner.setTextStillTime(5000);//设置停留时长间隔
            textbanner.setAnimTime(300);//设置进入和退出的时间间隔
            //对单条文字的点击监听
            textbanner.setOnItemClickListener(new VerticalTextview.OnItemClickListener() {
                @Override
                public void onItemClick(int position) {
                    // TO DO
                    Intent i = new Intent(mContext, GongGaoActivity.class);
                    mContext.startActivity(i);
                }
            });

            textbanner.startAutoScroll();
        }else {
            for (int i = 0; i<3; i++){
                textlist.add(announcelists.get(i).getTitle());
            }
            textbanner.setTextList((ArrayList<String>) textlist);
            textbanner.setText(16, 5, Color.BLACK);//设置属性,具体跟踪源码
            textbanner.setTextStillTime(5000);//设置停留时长间隔
            textbanner.setAnimTime(300);//设置进入和退出的时间间隔
            //对单条文字的点击监听
            textbanner.setOnItemClickListener(new VerticalTextview.OnItemClickListener() {
                @Override
                public void onItemClick(int position) {
                    // TO DO
                    Intent i = new Intent(mContext, GongGaoActivity.class);
                    mContext.startActivity(i);
                }
            });

            textbanner.startAutoScroll();
        }
    }

    //获取公告List
    private void fetchAnnounce() {
        StringRequest rq=new StringRequest(Request.Method.GET, ConstantSet.homeAddress+"main/getnotice?", new Response.Listener<String>() {

            @Override
            public void onResponse(String response) {
                // TODO Auto-generated method stub

                Log.i("TAGTAG公告", response);
                Gson gson = new Gson();
                GongGaoBean bean = gson.fromJson(response, GongGaoBean.class);
                if (bean.getStatus() == 1) {
                    announcelists.clear();
                    announcelists = bean.getData();
                    int count = announcelists.size();
                    initTextBannerData(count);

                }
            }
        }, new Response.ErrorListener(){

            @Override
            public void onErrorResponse(VolleyError error) {
                // TODO Auto-generated method stub
                Toast.makeText(mContext, "网络请求失败", Toast.LENGTH_SHORT).show();

            }});

        MyApplication.getRq().add(rq);

    }


    protected void initData() {

        lists = new ArrayList<View>();
        for (int i = 0; i < listBanner.size(); i++) {
            View view = View.inflate(mContext, R.layout.home_top_viewpager_item, null);
           final  ImageView img = (ImageView) view.findViewById(R.id.img);
            Glide.with(mContext)

                    .load(listBanner.get(i).getImg())

                    .error(0)//load失敗的Drawable

                    .placeholder(0)//loading時候的Drawable

                    // .animate()//設置load完的動畫

                    .centerCrop()//中心切圖, 會填滿

                    //.fitCenter()//中心fit, 以原本圖片的長寬為主

                    .into(img);
            
            img.setTag(i);
            img.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {

                    // Toast.makeText(mContext,"dialog",Toast.LENGTH_SHORT).show();
                    if (listBanner != null) {
                        Log.i("TAG-Homes", "index="+(int)(img.getTag())+"/n"+"action= "+listBanner.get((int)(img.getTag())).getAction() );

                        if (listBanner.get((int)(img.getTag())).getAction().getCode().equals("OpenCourseItem")) {
                            if (!(isAdd(listBanner.get((int)(img.getTag())).getAction().getInfo().get(0)))) {
                                ConstantSet.course_id =listBanner.get((int)(img.getTag())).getAction().getInfo().get(0);
                                ConstantSet.videoTitle = listBanner.get((int)(img.getTag())).getTitle();

                                mContext.startActivity(new Intent(mContext, VideoActivityFirst.class));
                            }
                            else if (isAdd(listBanner.get((int)(img.getTag())).getAction().getInfo().get(0))) {
                                ConstantSet.course_id = listBanner.get((int)(img.getTag())).getAction().getInfo().get(0);
                                ConstantSet.videoTitle = listBanner.get((int)(img.getTag())).getTitle();

                                mContext.startActivity(new Intent(mContext, VideoActivity.class));
                            }
                        }
                    }
                }
            });

            lists.add(view);
        }

        adapter = new HomeTopPagerAdapter(lists, mContext);
        homeViewPager.setAdapter(adapter);

        homeViewPager.setOffscreenPageLimit(lists.size());

        CircleIndicator indicator = (CircleIndicator) mView.findViewById(R.id.indictor);
        indicator.setViewPager(homeViewPager);
        adapter.registerDataSetObserver(indicator.getDataSetObserver());

    }

    protected void initEvent() {

        new Thread(new Runnable() {
            @Override
            public void run() {

                while (true) {
                    try {
                        Thread.sleep(5000);
                        Message msg = Message.obtain();
                        msg.arg1 = 10001;
                        handler.sendMessage(msg);
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                }
            }
        }).start();


    }

    public void setImage(String url, ImageView img) {
        Glide.with(mContext)

                .load(url)

                .error(0)//load失敗的Drawable

                .placeholder(0)//loading時候的Drawable

                // .animate()//設置load完的動畫

                //  .centerCrop()//中心切圖, 會填滿

                .fitCenter()//中心fit, 以原本圖片的長寬為主

                .into(img);
    }

    @Override
    public void onClick(View view) {


        if (view.getId() == R.id.guanli_img || view.getId() == R.id.guanli_text) {
            ConstantSet.cancel = "kecheng";
            if (categoryList.get(0).getAction().getInfo() != null && categoryList.get(0).getAction().getInfo().size() != 0) {
                ConstantSet.keyWord = categoryList.get(0).getTitle();
                ConstantSet.tag = categoryList.get(0).getAction().getInfo().get(0);
            } else {
                ConstantSet.tag = "";
            }
            mContext.startActivity(new Intent(mContext, TypeActivity.class));
        } else if (view.getId() == R.id.zhujichang_bt || view.getId() == R.id.zhujichang_text) {

            ConstantSet.cancel = "kecheng";
            if (categoryList.get(1).getAction().getInfo() != null && categoryList.get(1).getAction().getInfo().size() != 0) {
                ConstantSet.keyWord = categoryList.get(1).getTitle();
                ConstantSet.tag = categoryList.get(1).getAction().getInfo().get(0);
            } else {
                ConstantSet.tag = "";
            }
            mContext.startActivity(new Intent(mContext, TypeActivity.class));
        } else if (view.getId() == R.id.weixiu_img || view.getId() == R.id.weixiu_text) {
            ConstantSet.cancel = "kecheng";
            if (categoryList.get(2).getAction().getInfo() != null && categoryList.get(2).getAction().getInfo().size() != 0) {
                ConstantSet.tag = categoryList.get(2).getAction().getInfo().get(0);
                ConstantSet.keyWord = categoryList.get(2).getTitle();
            } else {
                ConstantSet.tag = "";
            }
            mContext.startActivity(new Intent(mContext, TypeActivity.class));
        } else if (view.getId() == R.id.jinrong_img || view.getId() == R.id.jinrong_text) {
            ConstantSet.cancel = "kecheng";
            if (categoryList.get(3).getAction().getInfo() != null && categoryList.get(3).getAction().getInfo().size() != 0) {
                ConstantSet.tag = categoryList.get(3).getAction().getInfo().get(0);
                ConstantSet.keyWord = categoryList.get(3).getTitle();
            } else {
                ConstantSet.tag = "";
            }
            mContext.startActivity(new Intent(mContext, TypeActivity.class));
        } else if (view.getId() == R.id.banjin_img || view.getId() == R.id.banjin_text) {
            ConstantSet.cancel = "kecheng";
            if (categoryList.get(4).getAction().getInfo() != null && categoryList.get(4).getAction().getInfo().size() != 0) {
                ConstantSet.tag = categoryList.get(4).getAction().getInfo().get(0);
                ConstantSet.keyWord = categoryList.get(4).getTitle();
            } else {
                ConstantSet.tag = "";
            }
            mContext.startActivity(new Intent(mContext, TypeActivity.class));
        } else if (view.getId() == R.id.meirong_img || view.getId() == R.id.meirong_text) {
            ConstantSet.cancel = "kecheng";
            if (categoryList.get(5).getAction().getInfo() != null && categoryList.get(5).getAction().getInfo().size() != 0) {
                ConstantSet.tag = categoryList.get(5).getAction().getInfo().get(0);
                ConstantSet.keyWord = categoryList.get(5).getTitle();
            } else {
                ConstantSet.tag = "";
            }
            mContext.startActivity(new Intent(mContext, TypeActivity.class));
        } else if (view.getId() == R.id.pinggu_img || view.getId() == R.id.pinggu_text) {
            ConstantSet.cancel = "kecheng";
            if (categoryList.get(6).getAction().getInfo() != null && categoryList.get(6).getAction().getInfo().size() != 0) {
                ConstantSet.tag = categoryList.get(6).getAction().getInfo().get(0);
            } else {
                ConstantSet.tag = "";
            }
            mContext.startActivity(new Intent(mContext, TypeActivity.class));
        } else {
            ConstantSet.cancel = "kecheng";
            if (categoryList.get(7).getAction().getInfo() != null && categoryList.get(7).getAction().getInfo().size() != 0) {
                ConstantSet.tag = categoryList.get(7).getAction().getInfo().get(0);
            } else {
                ConstantSet.tag = "";
            }
            mContext.startActivity(new Intent(mContext, TypeActivity.class));
        }

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
