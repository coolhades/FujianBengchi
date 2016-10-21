package cn.com.mbmpv.trainingonline.bean;

/**
 * Created by jiuzheyange on 2016/8/20.
 */
public class Sign {
    /**
     * status : 1
     * message : 今天已经签到
     * data : {"action":"每日签到","action_type":"签到相关","integral_value":"1","integral_high_limit":"1"}
     */

    private int status;
    private String message;
    /**
     * action : 每日签到
     * action_type : 签到相关
     * integral_value : 1
     * integral_high_limit : 1
     */

    private DataBean data;

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

    public DataBean getData() {
        return data;
    }

    public void setData(DataBean data) {
        this.data = data;
    }

    public static class DataBean {
        private String action;
        private String action_type;
        private String integral_value;
        private String integral_high_limit;

        public String getAction() {
            return action;
        }

        public void setAction(String action) {
            this.action = action;
        }

        public String getAction_type() {
            return action_type;
        }

        public void setAction_type(String action_type) {
            this.action_type = action_type;
        }

        public String getIntegral_value() {
            return integral_value;
        }

        public void setIntegral_value(String integral_value) {
            this.integral_value = integral_value;
        }

        public String getIntegral_high_limit() {
            return integral_high_limit;
        }

        public void setIntegral_high_limit(String integral_high_limit) {
            this.integral_high_limit = integral_high_limit;
        }
    }
//
//    String status;
//    String message;
//
//    public String getStatus() {
//        return status;
//    }
//
//    public void setStatus(String status) {
//        this.status = status;
//    }
//
//    public String getMessage() {
//        return message;
//    }
//
//    public void setMessage(String message) {
//        this.message = message;
//    }




}
