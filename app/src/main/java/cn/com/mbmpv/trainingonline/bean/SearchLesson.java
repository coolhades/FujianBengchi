package cn.com.mbmpv.trainingonline.bean;

/**
 * Created by jiuzheyange on 2016/8/14.
 */
public class SearchLesson {
    
    String id;
    String course_id;
    String course_name;
    String teacher_name;
    String date_start;
    String course_album;
    String date_end;
    String num_visit;
    String num_hour;
    String price_original;
    String price;
    String recommend_type;


    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getCourse_id() {
        return course_id;
    }

    public void setCourse_id(String course_id) {
        this.course_id = course_id;
    }

    public String getCourse_album() {
        return course_album;
    }

    public void setCourse_album(String course_album) {
        this.course_album = course_album;
    }

    public String getCourse_name() {
        return course_name;
    }

    public void setCourse_name(String course_name) {
        this.course_name = course_name;
    }

    public String getRecommend_type() {
        return recommend_type;
    }

    public void setRecommend_type(String recommend_type) {
        this.recommend_type = recommend_type;
    }

    public String getPrice() {
        return price;
    }

    public void setPrice(String price) {
        this.price = price;
    }

    public String getPrice_original() {
        return price_original;
    }

    public void setPrice_original(String price_original) {
        this.price_original = price_original;
    }

    public String getNum_hour() {
        return num_hour;
    }

    public void setNum_hour(String num_hour) {
        this.num_hour = num_hour;
    }

    public String getNum_visit() {
        return num_visit;
    }

    public void setNum_visit(String num_visit) {
        this.num_visit = num_visit;
    }

    public String getDate_end() {
        return date_end;
    }

    public void setDate_end(String date_end) {
        this.date_end = date_end;
    }

    public String getDate_start() {
        return date_start;
    }

    public void setDate_start(String date_start) {
        this.date_start = date_start;
    }

    public String getTeacher_name() {
        return teacher_name;
    }

    public void setTeacher_name(String teacher_name) {
        this.teacher_name = teacher_name;
    }
}
