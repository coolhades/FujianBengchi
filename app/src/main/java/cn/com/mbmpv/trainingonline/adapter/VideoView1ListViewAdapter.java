package cn.com.mbmpv.trainingonline.adapter;

import android.content.Context;
import android.graphics.Color;
import android.util.Log;
import android.view.Gravity;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AbsListView;
import android.widget.BaseExpandableListAdapter;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.bokecc.sdk.mobile.play.DWMediaPlayer;

import java.util.ArrayList;
import java.util.List;

import cn.com.mbmpv.trainingonline.R;
import cn.com.mbmpv.trainingonline.bean.JieBean;
import cn.com.mbmpv.trainingonline.bean.ZhangBean;

/**
 * Created by jiuzheyange on 2016/8/14.
 */
public class VideoView1ListViewAdapter extends BaseExpandableListAdapter {

    Context activity;
    List<ZhangBean> parentLists;
    List<List<JieBean>> childLists;
    public  List<TextView> alltextview = new ArrayList<>();
    DWMediaPlayer player;
    int mp=0;
    public VideoView1ListViewAdapter(Context mContext, List<ZhangBean> parentLists, List<List<JieBean>> childLists, DWMediaPlayer player)
    {

        activity = mContext;
        this.childLists=childLists;
        this.parentLists=parentLists;
        this.player=player;
        if (!alltextview.isEmpty()){
            alltextview.clear();
        }
    }

    public  Object getChild(int  groupPosition, int  childPosition)
    {
        return  childLists.get(groupPosition).get(childPosition);
    }
    public  long  getChildId(int  groupPosition, int  childPosition)
    {
        Log.d("TAG-Position-Child", "index = "+childPosition);
        return  childPosition;
    }
    public  int  getChildrenCount(int  groupPosition)
    {
        if(childLists.get(groupPosition)==null)
        {
            return 0;
        }else {
            return childLists.get(groupPosition).size();
        }
    }
    public View getChildView(final int groupPosition,final  int  childPosition,
                             boolean  isLastChild, View convertView, ViewGroup parent)
    {
        String string = childLists.get(groupPosition).get(childPosition).getNode_caption();
        View view=View.inflate(activity, R.layout.child_listview_item,null);

        TextView text= (TextView) view.findViewById(R.id.text);
        text.setText(string);

        
//        text.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View view) {
//                getChildVideo(groupPosition,childPosition);
//            }
//        });

        return  view;
    }
    // group method stub   
    public  Object getGroup(int  groupPosition)
    {
        return  parentLists.get(groupPosition);
    }
    public  int  getGroupCount()
    {
        return  parentLists.size();
    }
    public  long  getGroupId(int  groupPosition)
    {
        Log.d("TAG-Position-Parent", "index = "+groupPosition);
        return  groupPosition;
    }

    public void resetTextViewColor() {
        for (int i = 0;i<alltextview.size(); i++){
            alltextview.get(i).setTextColor(Color.parseColor("#000000"));
        }
    }

    public void setTextViewColor(int pos){
        Log.i("TAGTAG","listsize="+alltextview.size() );
        Log.i("TAGTAG","pos="+pos);
        if (alltextview.isEmpty()){
            return;
        }
        if (pos >= parentLists.size()){
            return;
        }
        alltextview.get(pos).setTextColor(Color.parseColor("#888765"));
    }


    public  View getGroupView(final int  groupPosition,boolean  isExpanded,
                              View convertView, ViewGroup parent) {
        String string = parentLists.get(groupPosition).getNode_caption();
        View view = View.inflate(activity, R.layout.parent_listview_item, null);

        LinearLayout expanditem_ly = (LinearLayout) view.findViewById(R.id.expanditem_ly);
        View view1 = view.findViewById(R.id.shuxian);


        TextView text = (TextView) view.findViewById(R.id.text);
        if (parentLists.size() > alltextview.size()){

            alltextview.add(text);
            Log.i("TAGTAG", "alltextview_size="+alltextview.size());
//            Log.i("TAGTAG", "添加TextView"+mp++);
        }
        if (parentLists.get(groupPosition).getIs_watched().equalsIgnoreCase("1")){
            text.setTextColor(Color.parseColor("#999999"));
        }

        if (selectedPosition == groupPosition){
            expanditem_ly.setBackgroundColor(Color.parseColor("#000000"));
            text.setTextColor(Color.parseColor("#ffffff"));
            view1.setBackgroundColor(Color.parseColor("#ffffff"));
        }


        // 设置 item 颜色
//        int i= SharedPreferencesUtils.getValue(activity, "Settings", "video_id", 5000);
//        if (i < parentLists.size()){
//            setTextViewColor(i);
//        }

        text.setText(string);



//        text.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View view) {
//                getParentVideo(groupPosition);
//            }
//        });
        return view;
    }

    private int selectedPosition = -1;// 选中的位置

    /**
     * 自定义方法，设定item点击状态保持
     *
     * @param position
     */
    public void setSelectedPosition(int position) {
        selectedPosition = position;
    }

    // View stub to create Group/Children 's View   
    public  TextView getGenericView(String string)
    {
        // Layout parameters for the ExpandableListView   
        AbsListView.LayoutParams layoutParams = new  AbsListView.LayoutParams(
                ViewGroup.LayoutParams.FILL_PARENT, 64 );
        TextView text = new  TextView(activity);
        text.setLayoutParams(layoutParams);
        // Center the text vertically   
        text.setGravity(Gravity.CENTER_VERTICAL | Gravity.LEFT);
        // Set the text starting position   
        text.setPadding(36 , 0 , 0 , 0 );
        text.setText(string);
        return  text;
    }
    public  boolean  hasStableIds()
    {
        return  false ;
    }
    public  boolean  isChildSelectable(int  groupPosition, int  childPosition)
    {
        return  true ;
    }
}

