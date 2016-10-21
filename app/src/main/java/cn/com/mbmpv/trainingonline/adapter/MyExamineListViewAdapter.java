package cn.com.mbmpv.trainingonline.adapter;

import android.content.Context;
import android.view.Gravity;
import android.view.View;
import android.view.ViewGroup;
import android.view.Window;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.List;

import cn.com.mbmpv.trainingonline.R;
import cn.com.mbmpv.trainingonline.bean.MyExam;
import cn.com.mbmpv.trainingonline.utils.ConstantSet;
import cn.com.mbmpv.trainingonline.utils.LoadImgUtils;
import cn.com.mbmpv.trainingonline.widget.WebViewKaoshiDialog;

public class MyExamineListViewAdapter extends BaseAdapter{

	
	List<MyExam> list;
	Context mContext;
	
	
	
	public MyExamineListViewAdapter(List<MyExam> list, Context mContext) {
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
			viewHolder.check = (TextView) convertView.findViewById(R.id.check);

			convertView.setTag(viewHolder);
		}
		else
		{
			viewHolder=(ViewHolder) convertView.getTag();
		}

		LoadImgUtils.setImage(mContext,list.get(position).getCourse_album(),viewHolder.itemImg);
		viewHolder.itemTitle.setText(list.get(position).getExam_name());
		if(list.get(position).getIs_pass().equals("0"))//没通过
		{
			viewHolder.item_content.setText("未通过");
			viewHolder.renwu.setText("去重考");
			viewHolder.check.setVisibility(View.VISIBLE);
			viewHolder.check.setText("查看");
			viewHolder.check.setOnClickListener(new View.OnClickListener() {
				@Override
				public void onClick(View v) {
					showH5Dialog("查看", list.get(position).getExam_result(), list.get(position).getExam_id());
				}
			});
			viewHolder.renwu.setOnClickListener(new View.OnClickListener() {
				@Override
				public void onClick(View v) {
					showH5Dialog("重考", list.get(position).getExam_link(),"");
				}
			});

		}else if (list.get(position).getIs_pass().equals("1"))
		{
			viewHolder.item_content.setText("已通过");
			viewHolder.check.setVisibility(View.VISIBLE);
			viewHolder.check.setText("查看");
			viewHolder.check.setOnClickListener(new View.OnClickListener() {
				@Override
				public void onClick(View v) {
					showH5Dialog("查看", list.get(position).getExam_result(),list.get(position).getExam_id());
				}
			});
			viewHolder.renwu.setText("去重考");
			viewHolder.renwu.setOnClickListener(new View.OnClickListener() {
				@Override
				public void onClick(View v) {
					showH5Dialog("重考", list.get(position).getExam_link(),"");
				}
			});
		}else  //-1 没考过
		{
			viewHolder.item_content.setText("未考试");
			viewHolder.check.setVisibility(View.INVISIBLE);
			viewHolder.renwu.setText("去考试");
			viewHolder.renwu.setOnClickListener(new View.OnClickListener() {
				@Override
				public void onClick(View v) {
					showH5Dialog("去考试", list.get(position).getExam_link(),"");
				}
			});
		}


		
		return convertView;
	}

/**
* 创建时间 16/9/5
* auther Hades
* 描述   exmaid  分享使用参数
**/
	private void showH5Dialog(String title, String url, String examid){

		WebViewKaoshiDialog dialog =  new  WebViewKaoshiDialog(mContext, null,ConstantSet.homeAddress+url,
				title, 0, null,null, examid);

		Window window = dialog.getWindow();
		window.setGravity(Gravity.BOTTOM);
		dialog.setCanceledOnTouchOutside(true);// 设置点击Dialog外部任意区域关闭Dialog
		dialog.show();
	}


	
	class ViewHolder
	{
		
		ImageView itemImg;
		TextView itemTitle;
		TextView item_content;
		TextView renwu;
		ImageView item_tip;
		TextView check;
	}
	
}
