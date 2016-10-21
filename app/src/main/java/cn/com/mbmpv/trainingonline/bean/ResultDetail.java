package cn.com.mbmpv.trainingonline.bean;

/**
 * Created by jiuzheyange on 2016/8/15.
 */
public class ResultDetail {
    
    String status;
    String message;
    CenterDetail data;

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public CenterDetail getData() {
        return data;
    }

    public void setData(CenterDetail data) {
        this.data = data;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }
}
