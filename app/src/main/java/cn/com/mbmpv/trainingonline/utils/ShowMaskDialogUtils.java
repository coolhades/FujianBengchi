package cn.com.mbmpv.trainingonline.utils;

import android.content.Context;
import android.view.Gravity;
import android.view.Window;

import cn.com.mbmpv.trainingonline.widget.MaskDialog;
import cn.com.mbmpv.trainingonline.widget.MaskDialog_Btn;

/**
 * Created by Hades on 16/9/1.
 */
public class ShowMaskDialogUtils {
    public static void showMaskDialog(Context context, String fileName, String key, String question){
        MaskDialog dialog1 = new MaskDialog(context,fileName,key,question);

        Window window1 = dialog1.getWindow();
        window1.setGravity(Gravity.CENTER);
        dialog1.setCanceledOnTouchOutside(false);// 设置点击Dialog外部任意区域关闭Dialog
        dialog1.show();
    }

    public static void showMaskDialog_Btn(Context context, String fileName, String key, String question){
        MaskDialog_Btn dialog1 = new MaskDialog_Btn(context,fileName,key,question);

        Window window1 = dialog1.getWindow();
        window1.setGravity(Gravity.CENTER);
        dialog1.setCanceledOnTouchOutside(false);// 设置点击Dialog外部任意区域关闭Dialog
        dialog1.show();
    }
}
