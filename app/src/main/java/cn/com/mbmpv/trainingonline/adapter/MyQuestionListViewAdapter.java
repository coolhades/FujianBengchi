package cn.com.mbmpv.trainingonline.adapter;

import android.content.Context;
import android.content.Intent;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.LinearLayout;
import android.widget.TextView;

import java.util.List;

import cn.com.mbmpv.trainingonline.R;
import cn.com.mbmpv.trainingonline.bean.MyQuestionBean;
import cn.com.mbmpv.trainingonline.ui.QuestionToVideoActivity;
import cn.com.mbmpv.trainingonline.utils.LoadImgUtils;
import cn.com.mbmpv.trainingonline.widget.CircularImage;

public class MyQuestionListViewAdapter extends BaseAdapter {


    List<MyQuestionBean.DataBean> list;
    Context mContext;


    public MyQuestionListViewAdapter(List<MyQuestionBean.DataBean> list, Context mContext) {
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
//			convertView=View.inflate(mContext, R.layout.my_question_item, null);
//			viewHolder.headImg= (CircularImage) convertView.findViewById(R.id.head_img);
//			viewHolder.titleText= (TextView) convertView.findViewById(R.id.title_text);
//			viewHolder.dateText= (TextView) convertView.findViewById(R.id.date_text);
//			viewHolder.contentText= (TextView) convertView.findViewById(R.id.content_text);
//			viewHolder.nextBar= (LinearLayout) convertView.findViewById(R.id.next_bar);
//			viewHolder.chakanTextView= (TextView) convertView.findViewById(R.id.chakan_text);
//			viewHolder.quest_item = (LinearLayout) convertView.findViewById(R.id.quest_item);
//			viewHolder.question_title = (TextView) convertView.findViewById(R.id.question_title);
            convertView=View.inflate(mContext, R.layout.my_question_item, null);

            viewHolder.quest_item = (LinearLayout) convertView.findViewById(R.id.quest_item);
            viewHolder.question_title = (TextView) convertView.findViewById(R.id.question_title);
            viewHolder.title_text = (TextView) convertView.findViewById(R.id.title_text);
            viewHolder.date_text = (TextView) convertView.findViewById(R.id.date_text);
            viewHolder.head_img = (CircularImage) convertView.findViewById(R.id.head_img);
            viewHolder.reply_ly = (LinearLayout) convertView.findViewById(R.id.reply_ly);
            viewHolder.line = convertView.findViewById(R.id.line);

            viewHolder.reply_person= (TextView) convertView.findViewById(R.id.reply_person);
            viewHolder.reply_content= (TextView) convertView.findViewById(R.id.reply_content);
            viewHolder.headImg = (CircularImage) convertView.findViewById(R.id.headImg);
            viewHolder.time = (TextView) convertView.findViewById(R.id.time);
            convertView.setTag(viewHolder);
        } else {
            viewHolder = (ViewHolder) convertView.getTag();
        }

//		LoadImgUtils.setImage(mContext,list.get(position).getUserInfo().getAvatar(),viewHolder.headImg);
//		viewHolder.titleText.setText(list.get(position).getUserInfo().getNickname());
//		viewHolder.dateText.setText(list.get(position).getCdate());
//		viewHolder.contentText.setText(list.get(position).getContent());
////		viewHolder.question_title.setText(list.get(position).g);
//		if(!(list.get(position).getCount().equals("0")))
//		{
//			viewHolder.chakanTextView.setText("查看对话（"+list.get(position).getCount()+"）");
//		}
//		else
//		{
//			viewHolder.nextBar.setVisibility(View.GONE);
//		}


        viewHolder.question_title.setText(list.get(position).getQuestions().getContent());
        viewHolder.title_text.setText(list.get(position).getQuestions().getUserInfo().getNickname());
        viewHolder.date_text.setText(list.get(position).getQuestions().getCdate());
        LoadImgUtils.setImage(mContext,list.get(position).getQuestions().getUserInfo().getAvatar(),viewHolder.head_img);

        if ( !list.get(position).getAnswer().isEmpty()){
            viewHolder.reply_person.setText(list.get(position).getAnswer().get(0).getReplyUserInfo().getNickname());
            viewHolder.reply_content.setText(list.get(position).getAnswer().get(0).getContent());
            viewHolder.time.setText(list.get(position).getAnswer().get(0).getCdate());
            LoadImgUtils.setImage(mContext,list.get(position).getAnswer().get(0).getReplyUserInfo().getAvatar(),viewHolder.headImg);
        }else {
            viewHolder.reply_ly.setVisibility(View.GONE);
            viewHolder.line.setVisibility(View.GONE);
        }

        viewHolder.quest_item.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i = new Intent(mContext, QuestionToVideoActivity.class);
                i.putExtra("impower_id", list.get(position).getQuestions().getImpower_id());
                mContext.startActivity(i);
            }
        });

        return convertView;
    }


    class ViewHolder {


        //		CircularImage headImg;
//		TextView titleText;
//		TextView dateText;
//		TextView contentText;
//		LinearLayout nextBar;
//		TextView chakanTextView;
//        TextView question_title;
        LinearLayout quest_item;

        TextView reply_person;
        TextView reply_content;
        CircularImage headImg;
        TextView time;
        TextView question_title;
        TextView title_text;
        TextView date_text;
        CircularImage head_img;

        LinearLayout reply_ly;
        View line;
        View line1;


    }

}
