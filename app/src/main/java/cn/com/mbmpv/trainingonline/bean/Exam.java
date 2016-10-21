package cn.com.mbmpv.trainingonline.bean;

/**
 * Created by jiuzheyange on 2016/8/23.
 */
public class Exam {
    
    String uid;
    String exam_url;
    String exam_name;
    String video_pos_type;
    String video_pos;
    String can_close;

    public String getUid() {
        return uid;
    }

    public void setUid(String uid) {
        this.uid = uid;
    }

    public String getExam_url() {
        return exam_url;
    }

    public void setExam_url(String exam_url) {
        this.exam_url = exam_url;
    }

    public String getExam_name() {
        return exam_name;
    }

    public void setExam_name(String exam_name) {
        this.exam_name = exam_name;
    }

    public String getVideo_pos_type() {
        return video_pos_type;
    }

    public void setVideo_pos_type(String video_pos_type) {
        this.video_pos_type = video_pos_type;
    }

    public String getVideo_pos() {
        return video_pos;
    }

    public void setVideo_pos(String video_pos) {
        this.video_pos = video_pos;
    }

    public String getCan_close() {
        return can_close;
    }

    public void setCan_close(String can_close) {
        this.can_close = can_close;
    }
}
