package cn.com.mbmpv.trainingonline.bean;

import java.util.List;

/**
 * Created by jiuzheyange on 2016/8/15.
 */
public class ClassCenterDetail {


    /**
     * section_uid : 3AB167AA5C3148C3ACD827B53234D57D
     * node_type : 2
     * node_caption : 产品——USP
     * video_duration : 0
     * is_watched : 0
     * source : []
     */

    private String section_uid;
    private String node_type;
    private String node_caption;
    private String video_duration;
    private int is_watched;
    private List<String> source;

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

    public String getVideo_duration() {
        return video_duration;
    }

    public void setVideo_duration(String video_duration) {
        this.video_duration = video_duration;
    }

    public int getIs_watched() {
        return is_watched;
    }

    public void setIs_watched(int is_watched) {
        this.is_watched = is_watched;
    }

    public List<?> getSource() {
        return source;
    }

    public void setSource(List<String> source) {
        this.source = source;
    }
}
