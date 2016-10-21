package cn.com.mbmpv.trainingonline.utils;

import android.content.Context;
import android.graphics.Bitmap;
import android.util.Base64;
import android.widget.ImageView;
import android.widget.Toast;

import com.bumptech.glide.Glide;
import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import com.loopj.android.http.AsyncHttpClient;
import com.loopj.android.http.AsyncHttpResponseHandler;
import com.loopj.android.http.RequestParams;

import java.io.ByteArrayOutputStream;

import cn.com.mbmpv.trainingonline.bean.ResultUserInfo2;

/**
 * Created by jiuzheyange on 2016/8/15.
 */
public class LoadImgUtils {
    public static void setImage(Context mContext,String url, ImageView img)
    {
        Glide.with(mContext)

                .load(url)

                .error(0)//load失敗的Drawable

                .placeholder(0)//loading時候的Drawable
                .dontAnimate() //去掉淡入淡出
                // .animate()//設置load完的動畫

                //.centerCrop()//中心切圖, 會填滿

                .fitCenter()//中心fit, 以原本圖片的長寬為主

                .into(img);
    }

    public static void reg(final Context cont, Bitmap photodata) {
        try {
            ByteArrayOutputStream baos = new ByteArrayOutputStream();

            //将bitmap一字节流输出 Bitmap.CompressFormat.PNG 压缩格式，100：压缩率，baos：字节流  
            photodata.compress(Bitmap.CompressFormat.PNG, 50, baos);
            baos.close();
            byte[] buffer = baos.toByteArray();
            System.out.println("图片的大小："+buffer.length);

            //将图片的字节流数据加密成base64字符输出  
            String image = Base64.encodeToString(buffer, 0, buffer.length,Base64.DEFAULT);

            //photo=URLEncoder.encode(photo,"UTF-8");  
            RequestParams params = new RequestParams();
            params.put("obj_type", "useruploadavatar");
            params.put("okey",Md5Utils.md5("moocuseruploadavatar"+"useruploadavatar"));
            params.put("image",image);
            if(ConstantSet.user!=null) {
                params.put("user_uid", ConstantSet.user.getUid());//传输的字符数据  
            }

            String url = ConstantSet.homeAddress+"user/uploadavatar";


            AsyncHttpClient client = new AsyncHttpClient();
            client.post(url, params, new AsyncHttpResponseHandler() {
                @Override
                public void onSuccess(int statusCode, String content){
                    Toast.makeText(cont, "头像上传成功!", Toast.LENGTH_SHORT)
                            .show();
                    Gson gson = new Gson();
                    ResultUserInfo2 result = gson.fromJson(content, new TypeToken<ResultUserInfo2>() {
                    }.getType());

                    
                    ConstantSet.user.setAvatar(result.getData().getAvatar());
                    System.out.print("content  "+content);
                }
                @Override
                public void onFailure(Throwable e, String data){
                    Toast.makeText(cont, "头像上传失败!"+e.toString(), Toast.LENGTH_SHORT)
                            .show();
                }
            });

        } catch (Exception e) {
            e.printStackTrace();
        }

    }


}
