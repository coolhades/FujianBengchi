package cn.com.mbmpv.trainingonline.adapter;

import android.content.Context;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.List;

import cn.com.mbmpv.trainingonline.R;
import cn.com.mbmpv.trainingonline.bean.Teacher;
import cn.com.mbmpv.trainingonline.utils.LoadImgUtils;

/**
 * Created by jiuzheyange on 2016/8/9.
 */
public class JiangshiListAdapter extends BaseAdapter{
    
    List<Teacher> lists;
    Context mContext;

    public JiangshiListAdapter(List<Teacher> lists, Context mContext) {
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
    public View getView(int i, View view, ViewGroup viewGroup) {
        ViewHolder viewHolder=null;
        if(view==null)
        {
            viewHolder=new ViewHolder();
            view=View.inflate(mContext, R.layout.mingshi_listview_item,null);
            viewHolder.img= (ImageView) view.findViewById(R.id.head_img);
            viewHolder.titleText= (TextView) view.findViewById(R.id.name);
            viewHolder.tip1= (TextView) view.findViewById(R.id.tip1);
            viewHolder.tip2= (TextView) view.findViewById(R.id.tip2);
            viewHolder.nextImg= (ImageView) view.findViewById(R.id.next_bt);
            
            view.setTag(viewHolder);
        }
        else{
            viewHolder= (ViewHolder) view.getTag();
                    
        }
        
        
        LoadImgUtils.setImage(mContext,lists.get(i).getTeacher_avatar(),viewHolder.img);
        viewHolder.titleText.setText(lists.get(i).getTeacher_name());
        viewHolder.tip1.setText(lists.get(i).getTeacher_tag().get(0).getTitle());
        viewHolder.tip2.setText(lists.get(i).getTeacher_tag().get(1).getTitle());


        viewHolder.nextImg.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                
            }
        });
        
        
        
        return view;
    }
    
    
    class ViewHolder
    {
        ImageView img;
        TextView titleText;
        TextView tip1;
        TextView tip2;
        ImageView nextImg;
    }
    
}
