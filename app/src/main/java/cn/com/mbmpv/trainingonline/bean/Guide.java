package cn.com.mbmpv.trainingonline.bean;

import java.util.List;

/**
 * Created by jiuzheyange on 2016/8/17.
 */
public class Guide {
    
    String guide_name;
    String integral_flag;
    String integral_code;
    String integral_val;
    String stretch_flag;
    
    
    List<MaskImg> plan;

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

    public List<MaskImg> getPlan() {
        return plan;
    }

    public void setPlan(List<MaskImg> plan) {
        this.plan = plan;
    }
}
