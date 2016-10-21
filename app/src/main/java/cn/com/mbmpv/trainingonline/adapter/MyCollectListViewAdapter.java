package cn.com.mbmpv.trainingonline.adapter;

import android.content.Context;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.List;

import cn.com.mbmpv.trainingonline.R;
import cn.com.mbmpv.trainingonline.bean.MyCollect;
import cn.com.mbmpv.trainingonline.utils.LoadImgUtils;

public class MyCollectListViewAdapter extends BaseAdapter{

	
	List<MyCollect> list;
	Context mContext;
	
	
	
	public MyCollectListViewAdapter(List<MyCollect> list, Context mContext) {
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
		
		ViewHolder viewHolder=null;
		if(convertView==null)
		{
			viewHolder=new ViewHolder();
			convertView=View.inflate(mContext, R.layout.my_examine_listview_item, null);
			
			viewHolder.itemImg= (ImageView) convertView.findViewById(R.id.item_img);
			viewHolder.itemTitle= (TextView) convertView.findViewById(R.id.item_title);
			viewHolder.renwu= (TextView) convertView.findViewById(R.id.renwu);
			viewHolder.item_content= (TextView) convertView.findViewById(R.id.item_content);
			viewHolder.item_tip= (ImageView) convertView.findViewById(R.id.item_tip);
			
			convertView.setTag(viewHolder);
		}
		else
		{
			viewHolder=(ViewHolder) convertView.getTag();
		}

		LoadImgUtils.setImage(mContext,list.get(position).getCourse_album(),viewHolder.itemImg);
		viewHolder.itemTitle.setText(list.get(position).getCourse_name());
		
			viewHolder.renwu.setVisibility(View.GONE);
		
		
		return convertView;
	}

	
	class ViewHolder
	{
		
		ImageView itemImg;
		TextView itemTitle;
		TextView item_content;
		TextView renwu;
		ImageView item_tip;
	}
	
}
