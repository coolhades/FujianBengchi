package cn.com.mbmpv.trainingonline.ui;

import android.content.ContentValues;
import android.content.Context;
import android.content.Intent;
import android.database.Cursor;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.net.Uri;
import android.os.Bundle;
import android.os.Environment;
import android.os.Handler;
import android.os.Message;
import android.provider.MediaStore;
import android.support.v7.app.AppCompatActivity;
import android.view.Gravity;
import android.view.View;
import android.view.Window;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.android.volley.AuthFailureError;
import com.android.volley.Request;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.HashMap;
import java.util.Map;

import cn.com.mbmpv.trainingonline.R;
import cn.com.mbmpv.trainingonline.application.MyApplication;
import cn.com.mbmpv.trainingonline.bean.ResultPersoninfo;
import cn.com.mbmpv.trainingonline.bean.ResultUser;
import cn.com.mbmpv.trainingonline.utils.ConstantSet;
import cn.com.mbmpv.trainingonline.utils.LoadImgUtils;
import cn.com.mbmpv.trainingonline.utils.Md5Utils;
import cn.com.mbmpv.trainingonline.widget.AgeDialog;

public class PersonalSetActivity extends AppCompatActivity {

    ImageView headImg;
    String path;
    ImageView mBack;

    LinearLayout headImgBar;

    TextView bianjiBt;


    TextView sexBt;
    TextView ageBt;
    LinearLayout sexBar;

    TextView nanBt;
    TextView nvBt;

    TextView nickName;

    TextView phoneNumber;

    LinearLayout workinfo;


//    private static final String IMAGE_FILE_LOCATION = "file:///sdcard/temp.jpg";//temp file

    private static final String IMAGE_FILE_LOCATION = "file://" + "/" + Environment.getExternalStorageDirectory().getPath() + "/" + "small.jpg";

    Uri imageUri = Uri.parse(IMAGE_FILE_LOCATION);//The Uri to store the big bitmap

    Handler handler = new Handler() {
        @Override
        public void handleMessage(Message msg) {
            super.handleMessage(msg);

            ageBt.setText(msg.obj.toString());

        }
    };

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_personal_set);

        initView();
        initData();
        initEvent();
    }

    private void initView() {

        headImg = (ImageView) findViewById(R.id.head_img);
        headImgBar = (LinearLayout) findViewById(R.id.head_ing_bar);

        mBack = (ImageView) findViewById(R.id.back);

        bianjiBt = (TextView) findViewById(R.id.bianji);

        sexBt = (TextView) findViewById(R.id.sex_bt);
        ageBt = (TextView) findViewById(R.id.age_bt);

        sexBar = (LinearLayout) findViewById(R.id.sex_bar);

        nanBt = (TextView) findViewById(R.id.nan_bt);
        nvBt = (TextView) findViewById(R.id.nv_bt);

        nickName = (TextView) findViewById(R.id.nick_name);

        phoneNumber = (TextView) findViewById(R.id.phone_number);

        workinfo = (LinearLayout) findViewById(R.id.workinfo);
    }


    private void initData() {

        getDataPersoninfo();
    }


    private void initEvent() {


        headImgBar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                getImageFromAlbum();
            }
        });

        workinfo.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i = new Intent(PersonalSetActivity.this, WorkInfoActivity.class);
                startActivity(i);
            }
        });

        mBack.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                PersonalSetActivity.this.finish();

            }
        });


        sexBt.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                //  sexBar.setVisibility(View.VISIBLE);
            }
        });


        ageBt.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                AgeDialog dialog = new AgeDialog(PersonalSetActivity.this, handler);

                Window window = dialog.getWindow();
                window.setGravity(Gravity.BOTTOM);
                dialog.setCanceledOnTouchOutside(true);// 设置点击Dialog外部任意区域关闭Dialog
                dialog.show();

            }
        });


        nanBt.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                sexBar.setVisibility(View.GONE);
                sexBt.setText("男");
            }
        });


        nvBt.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                sexBar.setVisibility(View.GONE);
                sexBt.setText("女");
            }
        });


        bianjiBt.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                getData();

            }
        });


    }


    protected void getImageFromAlbum() {
//        Intent intent = new Intent(Intent.ACTION_GET_CONTENT);
        Intent intent = new Intent(Intent.ACTION_PICK, android.provider.MediaStore.Images.Media.EXTERNAL_CONTENT_URI);
        intent.setDataAndType(MediaStore.Images.Media.EXTERNAL_CONTENT_URI, "image/*");


//        intent.setType("image/*");// 相片类型
        startActivityForResult(intent, 2);
    }


    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        // TODO Auto-generated method stub
        //有些手机返回的是file 不是content
        if (requestCode == 2) {
            Object object = null;
            if (imageUri == null || data == null) {
                return;
            } else {
                try {
//                    Bitmap imageBitmap = BitmapFactory.decodeFile(data.getData().getPath());
//                    Log.i("TAGTAGTAG", "uri=" + getImageContentUri(PersonalSetActivity.this, new File(data.getData().getPath())) );
                    if (data.getData().toString().contains("file://")) {
                        //部分手机 返回的是file文件
//                        Uri uri = getImageContentUri(PersonalSetActivity.this, new File(data.getData().getPath()));
                        startCropIntent(data.getData().getPath());
                    } else {
                        Uri uri = data.getData();
                        Uri fileUri = converUri(uri);
                        startImageZoom(fileUri);
                    }
                } catch (Exception e) {
                    Toast.makeText(PersonalSetActivity.this, "出错了！", Toast.LENGTH_SHORT).show();
                }
            }

        } else if (requestCode == 3) {
            if (imageUri == null || data == null) {
                return;
            }

				/*Bundle temp = data.getExtras();
                if (null == temp) {
					return;
				}
				Bitmap bitmap = temp.getParcelable("data");*/

            Bitmap bitmap = decodeUriAsBitmap(imageUri);//decode bitmap

            headImg.setImageBitmap(bitmap);

            //头像上传

            LoadImgUtils.reg(PersonalSetActivity.this, bitmap);

        }


        super.onActivityResult(requestCode, resultCode, data);
    }

    //传入路径裁切图片
    private void startCropIntent(String path) throws FileNotFoundException {

        File file = new File(path);
        Intent intent = new Intent("com.android.camera.action.CROP");
        Uri uri = Uri.fromFile(file);// parse(pathUri);13
        intent.setDataAndType(uri, "image/*");
        intent.putExtra("crop", "true");
        intent.putExtra("aspectX", 1);
        intent.putExtra("aspectY", 1);
        intent.putExtra("outputX", 300);
        intent.putExtra("outputY", 300);
        // 设置为true直接返回bitmap
        intent.putExtra("return-data", false);
        // 上面设为false的时候将MediaStore.EXTRA_OUTPUT关联一个Uri
        intent.putExtra(MediaStore.EXTRA_OUTPUT, imageUri);
        intent.putExtra("outputFormat", Bitmap.CompressFormat.JPEG.toString());
        startActivityForResult(intent, 3);
    }


    private void startImageZoom(Uri uri) {
        Intent intent = new Intent("com.android.camera.action.CROP");
        intent.setDataAndType(uri, "image/*");
        intent.putExtra("crop", "true");
        intent.putExtra("aspectX", 1);
        intent.putExtra("aspectY", 1);
        intent.putExtra("outputX", 300);
        intent.putExtra("outputY", 300);
        intent.putExtra("return-data", false);
        intent.putExtra(MediaStore.EXTRA_OUTPUT, imageUri);
        startActivityForResult(intent, 3);
    }


    private Uri converUri(Uri uri) {
        InputStream is = null;
        try {
            is = getContentResolver().openInputStream(uri);
            Bitmap bitmap = BitmapFactory.decodeStream(is);
            is.close();

            return Uri.fromFile(getFile(bitmap));
        } catch (FileNotFoundException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
            return null;
        } catch (IOException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
            return null;
        }

    }


    private File getFile(Bitmap bitmap) {

        File temp = new File(Environment.getExternalStorageDirectory()
                + "/data");

        if (temp.exists()) {
            temp.mkdir();
        }

        path = temp.getAbsolutePath() + "/" + System.currentTimeMillis() + "jietu.jpeg";
        File image = new File(path);

        try {
            FileOutputStream fileOutputStream = new FileOutputStream(image);
            /**
             * 获取图片的旋转角度，有些系统把拍照的图片旋转了，有的没有旋转 
             */
//			int degree = readPictureDegree(path);  
//			
//			/** 
//			 * 把图片旋转为正的方向 
//			 */  
//			bitmap = rotaingImageView(degree, bitmap); 
            bitmap.compress(Bitmap.CompressFormat.JPEG, 100, fileOutputStream);
            return image;
        } catch (FileNotFoundException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
            return null;
        }

    }


    /**
     * Gets the content:// URI  from the given corresponding path to a file
     *
     * @param context
     * @param imageFile
     * @return content Uri
     */
    public static Uri getImageContentUri(Context context, java.io.File imageFile) {
        String filePath = imageFile.getAbsolutePath();
        Cursor cursor = context.getContentResolver().query(
                MediaStore.Images.Media.EXTERNAL_CONTENT_URI,
                new String[]{MediaStore.Images.Media._ID},
                MediaStore.Images.Media.DATA + "=? ",
                new String[]{filePath}, null);
        if (cursor != null && cursor.moveToFirst()) {
            int id = cursor.getInt(cursor
                    .getColumnIndex(MediaStore.MediaColumns._ID));
            Uri baseUri = Uri.parse("content://media/external/images/media");
            return Uri.withAppendedPath(baseUri, "" + id);
        } else {
            if (imageFile.exists()) {
                ContentValues values = new ContentValues();
                values.put(MediaStore.Images.Media.DATA, filePath);
                return context.getContentResolver().insert(
                        MediaStore.Images.Media.EXTERNAL_CONTENT_URI, values);
            } else {
                return null;
            }
        }
    }

    private Bitmap decodeUriAsBitmap(Uri uri) {
        Bitmap bitmap = null;
        try {
            bitmap = BitmapFactory.decodeStream(getContentResolver().openInputStream(uri));


            /**
             * 读取图片属性：旋转的角度
             * @param path 图片绝对路径
             * @return degree旋转的角度
             */


        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
        return bitmap;
    }


    public void getData() {
        StringRequest rq = new StringRequest(Request.Method.POST, ConstantSet.homeAddress + "user/edituserinfo?", new Response.Listener<String>() {

            @Override
            public void onResponse(String response) {
                // TODO Auto-generated method stub

                //  System.out.print("response  "+response+"    "+response.length());
                //  Toast.makeText(PersonalSetActivity.this,response+"",Toast.LENGTH_LONG).show();
                if (response.length() > 40) {

                    Toast.makeText(PersonalSetActivity.this, "修改成功", Toast.LENGTH_SHORT).show();
                    Gson gson = new Gson();
                    ResultUser resultUser = gson.fromJson(response, new TypeToken<ResultUser>() {
                    }.getType());

                }
            }
        }, new Response.ErrorListener() {

            @Override
            public void onErrorResponse(VolleyError error) {
                // TODO Auto-generated method stub
                Toast.makeText(PersonalSetActivity.this, "网络请求失败", Toast.LENGTH_SHORT).show();

            }
        }) {
            @Override
            protected Map<String, String> getParams() throws AuthFailureError {
                // TODO Auto-generated method stub
                Map<String, String> map = new HashMap<String, String>();
                map.put("obj_type", "edituserinfo");
                if (ConstantSet.user != null) {
                    map.put("obj_id", ConstantSet.user.getUid());
                }
                map.put("nick_name", nickName.getText().toString());
                if (sexBt.getText().toString().equals("男")) {
                    map.put("sex", "1");
                } else {
                    map.put("sex", "0");
                }

                map.put("birthday", ageBt.getText().toString());

                map.put("okey", Md5Utils.md5("moocuseredituserinfo" + "edituserinfo" + ConstantSet.user.getUid()));

                return map;
            }
        };

        MyApplication.getRq().add(rq);
    }


    public void getDataPersoninfo() {
        StringRequest rq = new StringRequest(Request.Method.POST, ConstantSet.homeAddress + "/user/getuserinfo?", new Response.Listener<String>() {

            @Override
            public void onResponse(String response) {
                // TODO Auto-generated method stub

                //  System.out.print("response  "+response+"    "+response.length());
                // Toast.makeText(PersonalSetActivity.this,response+"",Toast.LENGTH_LONG).show();
                if (response.length() > 30) {

                    Gson gson = new Gson();
                    ResultPersoninfo result = gson.fromJson(response, new TypeToken<ResultPersoninfo>() {
                    }.getType());

                    if (ConstantSet.user.getAvatar() != null && ConstantSet.user.getAvatar() != "") {
                        LoadImgUtils.setImage(PersonalSetActivity.this, ConstantSet.user.getAvatar(), headImg);
                    } else {
                        LoadImgUtils.setImage(PersonalSetActivity.this, result.getData().getAvatar(), headImg);
                    }
                    nickName.setText(result.getData().getNickname());


                    phoneNumber.setText(result.getData().getPhone());
                    if (result.getData().getSex() != null) {
                        if (result.getData().getSex().equals("1")) {
                            sexBt.setText("男");
                        } else {
                            sexBt.setText("女");
                        }
                    }
                    ageBt.setText(result.getData().getBirthday());


                }
            }
        }, new Response.ErrorListener() {

            @Override
            public void onErrorResponse(VolleyError error) {
                // TODO Auto-generated method stub
                Toast.makeText(PersonalSetActivity.this, "网络请求失败", Toast.LENGTH_SHORT).show();

            }
        }) {
            @Override
            protected Map<String, String> getParams() throws AuthFailureError {
                // TODO Auto-generated method stub
                Map<String, String> map = new HashMap<String, String>();
                map.put("obj_type", "edituserinfo");
                if (ConstantSet.user != null) {
                    map.put("user_id", ConstantSet.user.getUid());
                }

                return map;
            }
        };

        MyApplication.getRq().add(rq);
    }
}
