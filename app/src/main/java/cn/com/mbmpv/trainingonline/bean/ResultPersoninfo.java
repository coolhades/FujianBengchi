package cn.com.mbmpv.trainingonline.bean;

/**
 * Created by jiuzheyange on 2016/8/23.
 */
public class ResultPersoninfo {
    
    String status;
    String message;
    Personinfo data;


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

    public Personinfo getData() {
        return data;
    }

    public void setData(Personinfo data) {
        this.data = data;
    }
}
