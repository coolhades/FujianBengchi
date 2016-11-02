package cn.com.mbmpv.trainingonline.adapter;

import android.content.Context;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import java.util.List;

import cn.com.mbmpv.trainingonline.R;
import cn.com.mbmpv.trainingonline.bean.QuestionReplyListBean;
import cn.com.mbmpv.trainingonline.utils.LoadImgUtils;
import cn.com.mbmpv.trainingonline.widget.CircularImage;

public class DuihuaListViewAdapter extends BaseAdapter{


	List<QuestionReplyListBean.DataBean.AnswerBean> list;
//List<QuestionListBean.DataBean> list;
	Context mContext;



	public DuihuaListViewAdapter(List<QuestionReplyListBean.DataBean.AnswerBean> list, Context mContext) {
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
			convertView=View.inflate(mContext, R.layout.duihua_item, null);
//			viewHolder.user_nick= (TextView) convertView.findViewById(R.id.user_nick);
//			viewHolder.question_title= (TextView) convertView.findViewById(R.id.question_title);
			viewHolder.reply_person= (TextView) convertView.findViewById(R.id.reply_person);
			viewHolder.reply_content= (TextView) convertView.findViewById(R.id.reply_content);
			viewHolder.headImg = (CircularImage) convertView.findViewById(R.id.headImg);
			viewHolder.time = (TextView) convertView.findViewById(R.id.time);
			convertView.setTag(viewHolder);
		}
		else
		{
			viewHolder=(ViewHolder) convertView.getTag();
		}
//		viewHolder.user_nick.setText(list.get(position).getReplyUserInfo().getNickname());
//		viewHolder.question_title.setText(list.get(position).getContent());
		viewHolder.reply_person.setText(list.get(position).getReplyUserInfo().getNickname());
		viewHolder.reply_content.setText(list.get(position).getContent());
		viewHolder.time.setText(list.get(position).getCdate());
		LoadImgUtils.setImage(mContext,list.get(position).getReplyUserInfo().getAvatar(),viewHolder.headImg);

		return convertView;
	}


	class ViewHolder
	{
//		TextView user_nick;
//		TextView question_title;
		TextView reply_person;
		TextView reply_content;
		CircularImage headImg;
		TextView time;
	}

}
