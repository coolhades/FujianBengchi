package cn.com.mbmpv.trainingonline.bean;

/**
 * Created by jiuzheyange on 2016/8/25.
 */
public class ResultStudy {
    
    String status;
    String message;
    Study data;

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

    public Study getData() {
        return data;
    }

    public void setData(Study data) {
        this.data = data;
    }
}
