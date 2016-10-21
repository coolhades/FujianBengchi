package cn.com.mbmpv.trainingonline.bean;

import java.util.List;

/**
 * Created by Hades on 2016/10/19.
 */

public class WorkInfoBean {


    /**
     * status : 1
     * message : ok
     * data : [{"iid":"946","uid":"FJBC-ONLINE-AREA-5","env_id":"10BE837A21D84D57B2D4EEDE3365A9A1","tree_fid":"947","tree_pid":"","tree_level":"0","tree_level_code":"947-","dep_name":"北区","dep_type":"1","del_flag":"0"},{"iid":"954","uid":"FJBC-ONLINE-SALE-35","env_id":"10BE837A21D84D57B2D4EEDE3365A9A1","tree_fid":"955","tree_pid":"FJBC-ONLINE-AREA-5","tree_level":"1","tree_level_code":"947-955-","dep_name":"北京福瑞浩洋","dep_type":"11","del_flag":"0"},{"iid":"957","uid":"FJBC-ONLINE-Dept-91","env_id":"10BE837A21D84D57B2D4EEDE3365A9A1","tree_fid":"958","tree_pid":"FJBC-ONLINE-SALE-35","tree_level":"2","tree_level_code":"947-955-958-","dep_name":"销售部","dep_type":"21","del_flag":"0"},{"iid":"959","uid":"FJBC-ONLINE-Posi-208","env_id":"10BE837A21D84D57B2D4EEDE3365A9A1","tree_fid":"960","tree_pid":"FJBC-ONLINE-Dept-91","tree_level":"3","tree_level_code":"947-955-958-960-","dep_name":"销售顾问","dep_type":"31","del_flag":"0"}]
     */

    private int status;
    private String message;
    /**
     * iid : 946
     * uid : FJBC-ONLINE-AREA-5
     * env_id : 10BE837A21D84D57B2D4EEDE3365A9A1
     * tree_fid : 947
     * tree_pid :
     * tree_level : 0
     * tree_level_code : 947-
     * dep_name : 北区
     * dep_type : 1
     * del_flag : 0
     */

    private List<DataBean> data;

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

    public List<DataBean> getData() {
        return data;
    }

    public void setData(List<DataBean> data) {
        this.data = data;
    }

    public static class DataBean {
        private String iid;
        private String uid;
        private String env_id;
        private String tree_fid;
        private String tree_pid;
        private String tree_level;
        private String tree_level_code;
        private String dep_name;
        private String dep_type;
        private String del_flag;

        public String getIid() {
            return iid;
        }

        public void setIid(String iid) {
            this.iid = iid;
        }

        public String getUid() {
            return uid;
        }

        public void setUid(String uid) {
            this.uid = uid;
        }

        public String getEnv_id() {
            return env_id;
        }

        public void setEnv_id(String env_id) {
            this.env_id = env_id;
        }

        public String getTree_fid() {
            return tree_fid;
        }

        public void setTree_fid(String tree_fid) {
            this.tree_fid = tree_fid;
        }

        public String getTree_pid() {
            return tree_pid;
        }

        public void setTree_pid(String tree_pid) {
            this.tree_pid = tree_pid;
        }

        public String getTree_level() {
            return tree_level;
        }

        public void setTree_level(String tree_level) {
            this.tree_level = tree_level;
        }

        public String getTree_level_code() {
            return tree_level_code;
        }

        public void setTree_level_code(String tree_level_code) {
            this.tree_level_code = tree_level_code;
        }

        public String getDep_name() {
            return dep_name;
        }

        public void setDep_name(String dep_name) {
            this.dep_name = dep_name;
        }

        public String getDep_type() {
            return dep_type;
        }

        public void setDep_type(String dep_type) {
            this.dep_type = dep_type;
        }

        public String getDel_flag() {
            return del_flag;
        }

        public void setDel_flag(String del_flag) {
            this.del_flag = del_flag;
        }
    }
}
