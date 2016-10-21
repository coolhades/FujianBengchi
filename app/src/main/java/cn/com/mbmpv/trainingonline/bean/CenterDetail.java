package cn.com.mbmpv.trainingonline.bean;

import java.util.List;

/**
 * Created by jiuzheyange on 2016/8/15.
 */
public class CenterDetail {
    
    Jianjie info;
    List<ZhangBean> section;
    String num_comment;

    public Jianjie getInfo() {
        return info;
    }

    public void setInfo(Jianjie info) {
        this.info = info;
    }

    public String getNum_comment() {
        return num_comment;
    }

    public void setNum_comment(String num_comment) {
        this.num_comment = num_comment;
    }

    public CenterDetail(Jianjie info) {
        this.info = info;
    }

    public List<ZhangBean> getSection() {
        return section;
    }

    public void setSection(List<ZhangBean> section) {
        this.section = section;
    }
}
