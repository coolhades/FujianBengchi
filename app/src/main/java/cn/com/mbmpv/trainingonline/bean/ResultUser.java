package cn.com.mbmpv.trainingonline.bean;

/**
 * Created by jiuzheyange on 2016/8/12.
 */
public class ResultUser {

    String status;
    String message;
    
    User data;

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


    public User getData() {
        return data;
    }

    public void setData(User data) {
        this.data = data;
    }
}
