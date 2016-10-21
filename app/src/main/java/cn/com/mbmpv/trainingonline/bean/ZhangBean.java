package cn.com.mbmpv.trainingonline.bean;

import java.util.List;

/**
 * Created by jiuzheyange on 2016/8/15.
 */
public class ZhangBean {
    
    String section_uid;
    String node_type;
    String node_caption;
    String is_watched;
    List<JieBean> child;

    public List<PdfBean> getSource() {
        return source;
    }

    List<PdfBean> source;


    public String getIs_watched() {
        return is_watched;
    }

    public void setIs_watched(String is_watched) {
        this.is_watched = is_watched;
    }

    public String getSection_uid() {
        return section_uid;
    }

    public void setSection_uid(String section_uid) {
        this.section_uid = section_uid;
    }

    public String getNode_type() {
        return node_type;
    }

    public void setNode_type(String node_type) {
        this.node_type = node_type;
    }

    public String getNode_caption() {
        return node_caption;
    }

    public void setNode_caption(String node_caption) {
        this.node_caption = node_caption;
    }

    public List<JieBean> getChild() {
        return child;
    }

    public void setChild(List<JieBean> child) {
        this.child = child;
    }
}
