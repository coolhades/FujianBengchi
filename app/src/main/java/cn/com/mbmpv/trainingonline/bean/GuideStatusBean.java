package cn.com.mbmpv.trainingonline.bean;

import java.util.List;

/**
 * Created by Hades on 16/9/1.
 */
public class GuideStatusBean {
    /**
     * status : 1
     * message : ok
     * data : {"guide_name":"学习档案分享新手引导","integral_flag":"0","integral_code":"2","integral_val":"0","stretch_flag":"0","plan":[{"step_image":"http://img1.auto-mooc.com/guide/step/20160827/412A76CDFEE84EFB871BEA9FAB242464.png"},{"step_image":"http://img1.auto-mooc.com/guide/step/20160827/CEA503A146FB4CD18637D1C36C69F9BF.png"}]}
     */

    private String status;
    private String message;
    /**
     * guide_name : 学习档案分享新手引导
     * integral_flag : 0
     * integral_code : 2
     * integral_val : 0
     * stretch_flag : 0
     * plan : [{"step_image":"http://img1.auto-mooc.com/guide/step/20160827/412A76CDFEE84EFB871BEA9FAB242464.png"},{"step_image":"http://img1.auto-mooc.com/guide/step/20160827/CEA503A146FB4CD18637D1C36C69F9BF.png"}]
     */

    private DataBean data;

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

    public DataBean getData() {
        return data;
    }

    public void setData(DataBean data) {
        this.data = data;
    }

    public static class DataBean {
        private String guide_name;
        private String integral_flag;
        private String integral_code;
        private String integral_val;
        private String stretch_flag;
        /**
         * step_image : http://img1.auto-mooc.com/guide/step/20160827/412A76CDFEE84EFB871BEA9FAB242464.png
         */

        private List<PlanBean> plan;

        public String getGuide_name() {
            return guide_name;
        }

        public void setGuide_name(String guide_name) {
            this.guide_name = guide_name;
        }

        public String getIntegral_flag() {
            return integral_flag;
        }

        public void setIntegral_flag(String integral_flag) {
            this.integral_flag = integral_flag;
        }

        public String getIntegral_code() {
            return integral_code;
        }

        public void setIntegral_code(String integral_code) {
            this.integral_code = integral_code;
        }

        public String getIntegral_val() {
            return integral_val;
        }

        public void setIntegral_val(String integral_val) {
            this.integral_val = integral_val;
        }

        public String getStretch_flag() {
            return stretch_flag;
        }

        public void setStretch_flag(String stretch_flag) {
            this.stretch_flag = stretch_flag;
        }

        public List<PlanBean> getPlan() {
            return plan;
        }

        public void setPlan(List<PlanBean> plan) {
            this.plan = plan;
        }

        public static class PlanBean {
            private String step_image;

            public String getStep_image() {
                return step_image;
            }

            public void setStep_image(String step_image) {
                this.step_image = step_image;
            }
        }
    }

    /**
     * status : 2
     * message : 此动作已经做过
     */


}
