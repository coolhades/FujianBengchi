package cn.com.mbmpv.trainingonline.bean;

/**
 * Created by jiuzheyange on 2016/8/17.
 */
public class ResultVideo {
    String status;
    String message;
    Video data;

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

    public Video getData() {
        return data;
    }

    public void setData(Video data) {
        this.data = data;
    }
}
