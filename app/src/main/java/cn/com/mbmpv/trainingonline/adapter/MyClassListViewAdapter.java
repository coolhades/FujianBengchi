package cn.com.mbmpv.trainingonline.adapter;

import android.content.Context;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.List;

import cn.com.mbmpv.trainingonline.R;
import cn.com.mbmpv.trainingonline.bean.MyClass;
import cn.com.mbmpv.trainingonline.utils.LoadImgUtils;

public class MyClassListViewAdapter extends BaseAdapter{

	
	List<MyClass> list;
	Context mContext;
	
	
	
	public MyClassListViewAdapter(List<MyClass> list, Context mContext) {
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
	public View getView(int position, View convertView, ViewGroup parent) {
		// TODO Auto-generated method stub
		
		ViewHolder viewHolder=null;
		if(convertView==null)
		{
			viewHolder=new ViewHolder();
			convertView=View.inflate(mContext, R.layout.my_class_listview_item, null);
			
			viewHolder.img= (ImageView) convertView.findViewById(R.id.item_img);
			viewHolder.title= (TextView) convertView.findViewById(R.id.item_title);
			viewHolder.content= (TextView) convertView.findViewById(R.id.item_content);
			
			convertView.setTag(viewHolder);
		}
		else
		{
			viewHolder=(ViewHolder) convertView.getTag();
		}

		LoadImgUtils.setImage(mContext,list.get(position).getCourse_album(),viewHolder.img);
		viewHolder.title.setText(list.get(position).getCourse_name());
		viewHolder.content.setText("已学习"+list.get(position).getNum_start()+"/"+list.get(position).getNum_end()+"课");
		
		
		
		return convertView;
	}

	
	class ViewHolder
	{
		
		ImageView img;
		TextView title;
		TextView content;
		
	}
	
}
