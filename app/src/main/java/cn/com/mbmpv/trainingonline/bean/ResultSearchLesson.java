package cn.com.mbmpv.trainingonline.bean;

/**
 * Created by jiuzheyange on 2016/8/14.
 */
public class ResultSearchLesson {
    String status;
    String message;
    SearchLessonCenter data;

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

    public SearchLessonCenter getData() {
        return data;
    }

    public void setData(SearchLessonCenter data) {
        this.data = data;
    }
}
