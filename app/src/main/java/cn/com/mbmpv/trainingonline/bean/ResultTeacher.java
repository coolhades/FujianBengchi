package cn.com.mbmpv.trainingonline.bean;

/**
 * Created by jiuzheyange on 2016/8/15.
 */
public class ResultTeacher {
    
    String status;
    String message;
    TeacherCenter data;


    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public TeacherCenter getData() {
        return data;
    }

    public void setData(TeacherCenter data) {
        this.data = data;
    }
}
