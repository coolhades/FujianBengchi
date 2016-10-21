package cn.com.mbmpv.trainingonline.bean;

import java.util.List;

/**
 * Created by jiuzheyange on 2016/8/15.
 */
public class ClassResultDetail {
//    /**
//     * status : 1
//     * message : ok
//     * data : [{"section_uid":"3AB167AA5C3148C3ACD827B53234D57D","node_type":"2","node_caption":"产品\u2014\u2014USP","video_duration":"0","is_watched":0,"source":[]},{"section_uid":"1EF6380CC892498F944BB8E0C3C50AA0","node_type":"2","node_caption":"产品\u2014\u2014外观总揽","video_duration":"0","is_watched":0,"source":[]},{"section_uid":"9A1500395B4B4FA69AF180274E0E01F2","node_type":"2","node_caption":"产品\u2014\u2014前脸外观","video_duration":"0","is_watched":0,"source":[]},{"section_uid":"E13FDE4A7CEF45B5B0CB8E2845800A7B","node_type":"2","node_caption":"产品\u2014\u2014车身右侧外观","video_duration":"0","is_watched":0,"source":[]},{"section_uid":"BF58830DE9B9448DBE91FFA9A14C7BD1","node_type":"2","node_caption":"产品\u2014\u2014乘客舱","video_duration":"0","is_watched":0,"source":[]},{"section_uid":"9EE81605EA3143469149CCC451458145","node_type":"2","node_caption":"产品\u2014\u2014尾部外观+行李舱","video_duration":"0","is_watched":0,"source":[]}]
//     */
//
//    private int status;
//    private String message;
//    /**
//     * section_uid : 3AB167AA5C3148C3ACD827B53234D57D
//     * node_type : 2
//     * node_caption : 产品——USP
//     * video_duration : 0
//     * is_watched : 0
//     * source : []
//     */
//
//    private List<DataBean> data;
//
//    public int getStatus() {
//        return status;
//    }
//
//    public void setStatus(int status) {
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
//
//    public List<DataBean> getData() {
//        return data;
//    }
//
//    public void setData(List<DataBean> data) {
//        this.data = data;
//    }
//
//    public static class DataBean {
//        private String section_uid;
//        private String node_type;
//        private String node_caption;
//        private String video_duration;
//        private int is_watched;
//        private List<String> source;
//
//        public String getSection_uid() {
//            return section_uid;
//        }
//
//        public void setSection_uid(String section_uid) {
//            this.section_uid = section_uid;
//        }
//
//        public String getNode_type() {
//            return node_type;
//        }
//
//        public void setNode_type(String node_type) {
//            this.node_type = node_type;
//        }
//
//        public String getNode_caption() {
//            return node_caption;
//        }
//
//        public void setNode_caption(String node_caption) {
//            this.node_caption = node_caption;
//        }
//
//        public String getVideo_duration() {
//            return video_duration;
//        }
//
//        public void setVideo_duration(String video_duration) {
//            this.video_duration = video_duration;
//        }
//
//        public int getIs_watched() {
//            return is_watched;
//        }
//
//        public void setIs_watched(int is_watched) {
//            this.is_watched = is_watched;
//        }
//
//        public List<?> getSource() {
//            return source;
//        }
//
//        public void setSource(List<String> source) {
//            this.source = source;
//        }
//    }

    int status;
    String message;
    List<ZhangBean> data;

    public int getStatus() {
        return status;
    }

    public void setStatus(int status) {
        this.status = status;
    }

    public List<ZhangBean> getData() {
        return data;
    }

    public void setData(List<ZhangBean> data) {
        this.data = data;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }
}
