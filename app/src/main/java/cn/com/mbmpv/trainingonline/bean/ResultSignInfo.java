package cn.com.mbmpv.trainingonline.bean;

import java.util.List;

/**
 * Created by jiuzheyange on 2016/8/25.
 */
public class ResultSignInfo {
    String status;
    String message;
    List<SignInfo> data;

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

    public List<SignInfo> getData() {
        return data;
    }

    public void setData(List<SignInfo> data) {
        this.data = data;
    }
}
