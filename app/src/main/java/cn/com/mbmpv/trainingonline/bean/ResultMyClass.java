package cn.com.mbmpv.trainingonline.bean;

import java.util.List;

/**
 * Created by jiuzheyange on 2016/8/17.
 */
public class ResultMyClass {
    
    String status;
    String message;
    List<MyClass> data;

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

    public List<MyClass> getData() {
        return data;
    }

    public void setData(List<MyClass> data) {
        this.data = data;
    }
}
