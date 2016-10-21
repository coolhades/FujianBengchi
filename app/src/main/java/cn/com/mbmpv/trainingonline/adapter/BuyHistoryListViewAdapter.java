package cn.com.mbmpv.trainingonline.adapter;

import android.content.Context;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;

import java.util.List;

import cn.com.mbmpv.trainingonline.R;

public class BuyHistoryListViewAdapter extends BaseAdapter{

	
	List<String> list;
	Context mContext;
	
	
	
	public BuyHistoryListViewAdapter(List<String> list, Context mContext) {
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
		
		final ViewHolder viewHolder;
		if(convertView==null)
		{
			viewHolder=new ViewHolder();
			convertView=View.inflate(mContext, R.layout.buy_history_listview_item, null);
			
			convertView.setTag(viewHolder);
		}
		else
		{
			viewHolder=(ViewHolder) convertView.getTag();
		}
		
		
		
	/*	Glide.with(mContext)

        .load(list.get(position).getImgUrl())

        .error(R.drawable.fail_img)

        .placeholder(R.drawable.fail_img)

       // .animate()

        .centerCrop()

        //.fitCenter()

        .into(viewHolder.img);*/
		
		
		/*ImageRequest irequest = new ImageRequest(
				list.get(position).getImgUrl(),
	                new Response.Listener<Bitmap>() {
	                    @SuppressLint("NewApi")
	                    @SuppressWarnings("deprecation")
	                    @Override
	                    public void onResponse(Bitmap bitmap) {
	                        
	                    	if (viewHolder.img.getTag() != null && viewHolder.img.getTag().equals(list.get(position).getImgUrl())) {
	                    	viewHolder.img.setImageBitmap(bitmap);
	                    	}
	                    }
	                }, R.drawable.fail_img,R.drawable.fail_img, Config.RGB_565, new ErrorListener() {
	                    @Override
	                    public void onErrorResponse(VolleyError arg0) {
	                    }
	                });
	        MyApplication.getRq().add(irequest);	*/
		
		
		
		
		return convertView;
	}

	
	class ViewHolder
	{
	}
	
}
