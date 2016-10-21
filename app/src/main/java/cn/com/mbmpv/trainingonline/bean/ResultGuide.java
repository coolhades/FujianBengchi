package cn.com.mbmpv.trainingonline.bean;

/**
 * Created by jiuzheyange on 2016/8/17.
 */
public class ResultGuide {
    
    String status;
    String message;
    Guide data;

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

    public Guide getData() {
        return data;
    }

    public void setData(Guide data) {
        this.data = data;
    }
}
