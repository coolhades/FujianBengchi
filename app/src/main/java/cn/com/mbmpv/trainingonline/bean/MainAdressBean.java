package cn.com.mbmpv.trainingonline.bean;

/**
 * Created by Hades on 16/8/31.
 */
public class MainAdressBean {


    /**
     * status : 1
     * message : ok
     * data : fjbcapi.auto-mooc.com
     */

    private int status;
    private String message;
    private String data;

    public int getStatus() {
        return status;
    }

    public void setStatus(int status) {
        this.status = status;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public String getData() {
        return data;
    }

    public void setData(String data) {
        this.data = data;
    }
}
