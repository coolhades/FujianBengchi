package cn.com.mbmpv.trainingonline.bean;

/**
 * Created by jiuzheyange on 2016/8/13.
 */
public class LastResultHome {
    String status;
    String message;
    ResultHome data;

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public ResultHome getData() {
        return data;
    }

    public void setData(ResultHome data) {
        this.data = data;
    }
}
