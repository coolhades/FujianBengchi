package cn.com.mbmpv.trainingonline.bean;

/**
 * Created by jiuzheyange on 2016/8/22.
 */
public class MyQuestion {
    
    String status;
    String message;
    CenterQuestion data;

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

    public CenterQuestion getData() {
        return data;
    }

    public void setData(CenterQuestion data) {
        this.data = data;
    }
}
