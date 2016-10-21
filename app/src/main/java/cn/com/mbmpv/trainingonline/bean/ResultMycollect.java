package cn.com.mbmpv.trainingonline.bean;

import java.util.List;

/**
 * Created by jiuzheyange on 2016/8/25.
 */
public class ResultMycollect {
    
    String status;
    String message;
    
    
    List<MyCollect> data;

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

    public List<MyCollect> getData() {
        return data;
    }

    public void setData(List<MyCollect> data) {
        this.data = data;
    }
}
