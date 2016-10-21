package cn.com.mbmpv.trainingonline.widget;

import android.app.Dialog;
import android.content.Context;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.view.View;
import android.widget.DatePicker;
import android.widget.TextView;

import cn.com.mbmpv.trainingonline.R;

public class AgeDialog extends Dialog {

    /*
     * author:qideli
       date:2016/1/23
       */
    public interface OnCustomDialogListener {
        public void back(String name);
    }

    /* TextView mCancel;
     TextView mOk;*/
    Context context;

    TextView completeBt;
    DatePicker datePicker;

    Handler handler;
    //first dialog's data


    public AgeDialog(Context context,Handler handler) {
        super(context, R.style.AppTheme);
        this.context = context;
        this.handler=handler;
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.old_dialog_layout);
        //  setTitle(name);

        completeBt= (TextView) findViewById(R.id.complete_bt);

        datePicker= (DatePicker) findViewById(R.id.datePickerEnd);

        completeBt.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                Message msg= Message.obtain();
                msg.obj=datePicker.getYear()+"-"+(datePicker.getMonth()+1)+"-"+datePicker.getDayOfMonth();
                handler.sendMessage(msg);
                colseDialog();
                
            }
        });
        
        

    }

    public void colseDialog() {
        AgeDialog.this.dismiss();
    }


}
