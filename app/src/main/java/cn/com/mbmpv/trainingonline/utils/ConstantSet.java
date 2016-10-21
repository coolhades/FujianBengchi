package cn.com.mbmpv.trainingonline.utils;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import cn.com.mbmpv.trainingonline.bean.MyClass;
import cn.com.mbmpv.trainingonline.bean.User;

/**
 * Created by jiuzheyange on 2016/8/12.
 */

/*
"App.Switch.Course.MaskInfo": "1",
        "App.Switch.Teacher.Show": "1",
        "App.Switch.Course.Evaluate.Show": "1",
        "App.Switch.PGC.Show": "1",
        "App.Switch.MyOrder.Show": "1",
        "App.Switch.Third-Party.Login": "1"*/
public class ConstantSet {
    
//    public static String homeAddress="http://fjbcapi.auto-mooc.com/";//http://fjbcapi.auto-mooc.com/
    public static String newhomeAddress = "http://api.auto-mooc.com/main/getdomain";
//    public static String homeAddress_content ;
//    public static String homeAddress="http://"+homeAddress_content+"/";

    public static String homeAddress;
    public static String course_id;
    public static String tag;
    public static String keyWord; 
    public static String cancel="";
    public static String userId="1";//默认为1
    public static String teacher_id;
    public static String teacher_name="未知";
    public static String teacher_url;

    public static boolean sign=true;
    public static User user;
    
    public static String section_uid;
    
   public static  Map<String,String> confiMap;
    
    public static String videoTitle;
    
    public static List<String> exam_url = new ArrayList<>();
    public static List<String> exam_name= new ArrayList<>();
    public static List<String> exam_time= new ArrayList<>();
    
    public static List<String> can_close= new ArrayList<>();
    public static String impower_id;
    public static String class_id;
    public static String jifen;


    
    public static String sharedImageUrl="http://img1.auto-mooc.com/general/img/29/14/B3/29B114A0B3E346EB93147E40CE3C0345.png";

    public static  List<MyClass> myClassList;

    public static String obj_id;
    public static String Target_id;
    public static String uid;
    public static String home_impower_id;
    public static String home_course_id;

    public static String class_data_url;


    
}
