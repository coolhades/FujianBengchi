package cn.com.mbmpv.trainingonline.adapter;

import android.content.Context;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.List;

import cn.com.mbmpv.trainingonline.R;
import cn.com.mbmpv.trainingonline.bean.Question;
import cn.com.mbmpv.trainingonline.utils.LoadImgUtils;

public class FirstVideoView3ListViewAdapter extends BaseAdapter{

	
	List<Question> list;
	Context mContext;
	
	
	
	public FirstVideoView3ListViewAdapter(List<Question> list, Context mContext) {
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
			convertView=View.inflate(mContext, R.layout.view3_question_item, null);
			
			viewHolder.headImg= (ImageView) convertView.findViewById(R.id.head_img);
			viewHolder.userNick= (TextView) convertView.findViewById(R.id.user_nick);
			viewHolder.dateText= (TextView) convertView.findViewById(R.id.date);
			viewHolder.contentText= (TextView) convertView.findViewById(R.id.content);
			viewHolder.chakanText= (TextView) convertView.findViewById(R.id.chakan_text);
			
			convertView.setTag(viewHolder);
		}
		else
		{
			viewHolder=(ViewHolder) convertView.getTag();
		}

		LoadImgUtils.setImage(mContext,list.get(position).getUserInfo().getAvatar(),viewHolder.headImg);
		viewHolder.userNick.setText(list.get(position).getUserInfo().getNickname());
		viewHolder.dateText.setText(list.get(position).getCdate());
		viewHolder.contentText.setText(list.get(position).getContent());
		return convertView;
	}

	
	class ViewHolder
	{
		
		ImageView headImg;
		TextView userNick;
		TextView dateText;
		TextView contentText;
		TextView chakanText;
	}
	
}
