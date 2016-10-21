package cn.com.mbmpv.trainingonline.adapter;

import android.content.Entity;
import android.support.v7.widget.RecyclerView;
import android.view.ViewGroup;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

public class ListBaseAdapter<T extends Entity> extends RecyclerView.Adapter {
    //protected Context mContext;

    protected ArrayList<T> mDataList = new ArrayList<>();

    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        return null;
    }

    @Override
    public void onBindViewHolder(RecyclerView.ViewHolder holder, int position) {

    }

    @Override
    public int getItemCount() {
        return mDataList.size();
    }

    public List<T> getDataList() {
        return mDataList;
    }

    //刷新或第一次加载
    public void setDataList(Collection<T> list) {
        this.mDataList.clear();
        this.mDataList.addAll(list);
        notifyDataSetChanged();
    }

    //加载更多
    public void addAll(Collection<T> list) {
        int lastIndex = this.mDataList.size();
        if (this.mDataList.addAll(list)) {
            notifyItemRangeInserted(lastIndex, list.size());
        }
    }

    //删除某一个
    public void delete(int position) {
        mDataList.remove(position);
        notifyItemRemoved(position);
    }

    //清空
    public void clear() {
        mDataList.clear();
        notifyDataSetChanged();
    }


}
