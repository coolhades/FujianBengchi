package cn.com.mbmpv.trainingonline.adapter;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.Window;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import java.util.List;

import cn.com.mbmpv.trainingonline.R;
import cn.com.mbmpv.trainingonline.bean.StudyDataBean;
import cn.com.mbmpv.trainingonline.utils.ConstantSet;
import cn.com.mbmpv.trainingonline.widget.StudyDataWebViewDialog;

/**
 * Created by Hades on 16/9/1.
 */
public class StudyDataRecyclerViewAdapter extends RecyclerView.Adapter<StudyDataRecyclerViewAdapter.MyViewHolder> {
    Context mContext;
    List<StudyDataBean> study_list;
    LinearLayout.LayoutParams layoutParams;


    public StudyDataRecyclerViewAdapter(Context context, List<StudyDataBean> list) {
        this.mContext = context;
        this.study_list = list;
    }

    @Override
    public MyViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(mContext).inflate(R.layout.studydata_item,parent,false);
        MyViewHolder holder = new MyViewHolder(view);

        return holder;
    }

    @Override
    public void onBindViewHolder(MyViewHolder holder, int position) {
        layoutParams = (LinearLayout.LayoutParams) holder.data_icon.getLayoutParams();
//        layoutParams.width

        holder.studydata_ly.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //传建url 启动h5页面
                if (ConstantSet.user != null) {
                    StudyDataWebViewDialog dialog = new StudyDataWebViewDialog(mContext, ConstantSet.homeAddress + "template/study?action=share&user_id=" + ConstantSet.user.getUid(),
                            "学习档案", 0, null, null);

                    Window window = dialog.getWindow();
                    window.setGravity(Gravity.BOTTOM);
                    dialog.setCanceledOnTouchOutside(true);// 设置点击Dialog外部任意区域关闭Dialog
                    dialog.show();
                }
            }
        });

    }

    @Override
    public int getItemCount() {
        return 8;
//        return study_list.size();
    }

    public static class MyViewHolder extends RecyclerView.ViewHolder{
        ImageView data_icon;
        TextView detail;
        LinearLayout studydata_ly;

        public MyViewHolder(View itemView) {
            super(itemView);

            data_icon = (ImageView) itemView.findViewById(R.id.data_icon);
            detail = (TextView) itemView.findViewById(R.id.detail);
            studydata_ly = (LinearLayout) itemView.findViewById(R.id.studydata_ly);

        }
    }
}
