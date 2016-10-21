package cn.com.mbmpv.trainingonline.bean;

import java.util.List;

/**
 * Created by jiuzheyange on 2016/8/17.
 */
public class Video {
    String video_title;
    String store_type;
    String store_value;
    String watch_time;
    String watch_status;
    List<Exam> exam;

    public List<Exam> getExam() {
        return exam;
    }

    public void setExam(List<Exam> exam) {
        this.exam = exam;
    }

    public String getVideo_title() {
        return video_title;
    }

    public void setVideo_title(String video_title) {
        this.video_title = video_title;
    }
    

    public String getStore_value() {
        return store_value;
    }

    public void setStore_value(String store_value) {
        this.store_value = store_value;
    }

    public String getStore_type() {
        return store_type;
    }

    public void setStore_type(String store_type) {
        this.store_type = store_type;
    }

    public String getWatch_time() {
        return watch_time;
    }

    public void setWatch_time(String watch_time) {
        this.watch_time = watch_time;
    }

    public String getWatch_status() {
        return watch_status;
    }

    public void setWatch_status(String watch_status) {
        this.watch_status = watch_status;
    }
}
