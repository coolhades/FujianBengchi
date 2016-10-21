package cn.com.mbmpv.trainingonline.bean;

/**
 * Created by jiuzheyange on 2016/8/13.
 */
public class ResultLessonKc {
    String status;
    String message;
    LessonBlockKcCenter data;

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

    public LessonBlockKcCenter getData() {
        return data;
    }

    public void setData(LessonBlockKcCenter data) {
        this.data = data;
    }
}
