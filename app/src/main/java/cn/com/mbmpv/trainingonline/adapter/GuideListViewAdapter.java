package cn.com.mbmpv.trainingonline.adapter;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.LinearLayout;
import android.widget.TextView;

import java.util.List;

import cn.com.mbmpv.trainingonline.R;
import cn.com.mbmpv.trainingonline.bean.GongGaoBean;
import cn.com.mbmpv.trainingonline.ui.GongGaoDetailActivity;

public class GuideListViewAdapter extends BaseAdapter{


	List<GongGaoBean.DataBean> list;
	Context mContext;



	public GuideListViewAdapter(List<GongGaoBean.DataBean> list, Context mContext) {
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
			convertView=View.inflate(mContext, R.layout.gonggao_listview_item, null);

//			viewHolder.time= (TextView) convertView.findViewById(R.id.time_gonggao);
//			viewHolder.year= (TextView) convertView.findViewById(R.id.year_gonggao);
			viewHolder.itemTitle= (TextView) convertView.findViewById(R.id.title_gonggao);
			viewHolder.item_content= (TextView) convertView.findViewById(R.id.content_gonggao);
			viewHolder.gonggao_ly = (LinearLayout) convertView.findViewById(R.id.gonggao_ly);

			convertView.setTag(viewHolder);
		}
		else
		{
			viewHolder=(ViewHolder) convertView.getTag();
		}
		//event
//		viewHolder.time.setText("");
//		viewHolder.year.setText("");
		viewHolder.itemTitle.setText(list.get(position).getTitle());
		viewHolder.item_content.setText(list.get(position).getContent());

		viewHolder.gonggao_ly.setOnClickListener(new View.OnClickListener() {
			@Override
			public void onClick(View v) {
				//跳转查看具体公告
				//传Bundle
				Bundle bundle = new Bundle();
				bundle.putString("title", list.get(position).getTitle());
				bundle.putString("content", list.get(position).getContent());
				Intent i = new Intent(mContext,GongGaoDetailActivity.class);
				i.putExtras(bundle);
				mContext.startActivity(i);

			}
		});



		
		return convertView;
	}

	
	class ViewHolder
	{
//		TextView time;
//		TextView year;
		TextView itemTitle;
		TextView item_content;
		LinearLayout gonggao_ly;

	}
	
}
