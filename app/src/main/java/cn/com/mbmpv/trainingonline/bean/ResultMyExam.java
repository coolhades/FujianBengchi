package cn.com.mbmpv.trainingonline.bean;

import java.util.List;

/**
 * Created by jiuzheyange on 2016/8/24.
 */
public class ResultMyExam {
    /**
     * status : 1
     * message : Ok
     * data : [{"exam_id":"22CC2FF32B1D43ADBBBECC6AC2D87ED3","exam_name":"V-Class目标客户考核测试","exam_link":"template/examdetail?exam_id=22CC2FF32B1D43ADBBBECC6AC2D87ED3&user_id=5CFB049AD4F331136FC58FF013C830BA&okey=039ab9871dc27095","exam_result":"template/myresults?exam_id=22CC2FF32B1D43ADBBBECC6AC2D87ED3&user_id=5CFB049AD4F331136FC58FF013C830BA&okey=039ab9871dc27095","course_album":"http://img1.auto-mooc.com/course/album/53/5301839CFCE04E32B2EAA67C5D45355C.jpg","is_pass":0},{"exam_id":"54266BF7F166404B9A730851288F128C","exam_name":"【品牌】考核考试","exam_link":"template/examdetail?exam_id=54266BF7F166404B9A730851288F128C&user_id=5CFB049AD4F331136FC58FF013C830BA&okey=21901ac163a28421","exam_result":"template/myresults?exam_id=54266BF7F166404B9A730851288F128C&user_id=5CFB049AD4F331136FC58FF013C830BA&okey=21901ac163a28421","course_album":"http://img1.auto-mooc.com/course/album/75/75C5B3172F3E415798BEF7B8DC027401.jpg","is_pass":-1},{"exam_id":"57CA8F00FA7D4353A31E3A35A387ABF2","exam_name":"V-Class竞品分析考核测试","exam_link":"template/examdetail?exam_id=57CA8F00FA7D4353A31E3A35A387ABF2&user_id=5CFB049AD4F331136FC58FF013C830BA&okey=5442c80816cb0fc0","exam_result":"template/myresults?exam_id=57CA8F00FA7D4353A31E3A35A387ABF2&user_id=5CFB049AD4F331136FC58FF013C830BA&okey=5442c80816cb0fc0","course_album":"http://img1.auto-mooc.com/course/album/D2/D216DB7D1B2C4B0A9D1EDC30FCC10F52.jpg","is_pass":-1},{"exam_id":"B37ADF75B2B94FF4AF3F4F6B97B2865C","exam_name":"V-Class产品知识测试","exam_link":"template/examdetail?exam_id=B37ADF75B2B94FF4AF3F4F6B97B2865C&user_id=5CFB049AD4F331136FC58FF013C830BA&okey=2320c15330125d01","exam_result":"template/myresults?exam_id=B37ADF75B2B94FF4AF3F4F6B97B2865C&user_id=5CFB049AD4F331136FC58FF013C830BA&okey=2320c15330125d01","course_album":"http://img1.auto-mooc.com/course/album/E1/E1392025656844BB97C2A23D67E9910C.jpg","is_pass":1}]
     */

    private String status;
    private String message;
    /**
     * exam_id : 22CC2FF32B1D43ADBBBECC6AC2D87ED3
     * exam_name : V-Class目标客户考核测试
     * exam_link : template/examdetail?exam_id=22CC2FF32B1D43ADBBBECC6AC2D87ED3&user_id=5CFB049AD4F331136FC58FF013C830BA&okey=039ab9871dc27095
     * exam_result : template/myresults?exam_id=22CC2FF32B1D43ADBBBECC6AC2D87ED3&user_id=5CFB049AD4F331136FC58FF013C830BA&okey=039ab9871dc27095
     * course_album : http://img1.auto-mooc.com/course/album/53/5301839CFCE04E32B2EAA67C5D45355C.jpg
     * is_pass : 0
     */

    private List<MyExam> data;

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

    public List<MyExam> getData() {
        return data;
    }

    public void setData(List<MyExam> data) {
        this.data = data;
    }

//    public static class MyExam {
//        private String exam_id;
//        private String exam_name;
//        private String exam_link;
//        private String exam_result;
//        private String course_album;
//        private String is_pass;
//
//        public String getExam_id() {
//            return exam_id;
//        }
//
//        public void setExam_id(String exam_id) {
//            this.exam_id = exam_id;
//        }
//
//        public String getExam_name() {
//            return exam_name;
//        }
//
//        public void setExam_name(String exam_name) {
//            this.exam_name = exam_name;
//        }
//
//        public String getExam_link() {
//            return exam_link;
//        }
//
//        public void setExam_link(String exam_link) {
//            this.exam_link = exam_link;
//        }
//
//        public String getExam_result() {
//            return exam_result;
//        }
//
//        public void setExam_result(String exam_result) {
//            this.exam_result = exam_result;
//        }
//
//        public String getCourse_album() {
//            return course_album;
//        }
//
//        public void setCourse_album(String course_album) {
//            this.course_album = course_album;
//        }
//
//        public String getIs_pass() {
//            return is_pass;
//        }
//
//        public void setIs_pass(String is_pass) {
//            this.is_pass = is_pass;
//        }
//    }

//    String status;
//    String message;
//   List<MyExam> data;
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
//
//    public List<MyExam> getData() {
//        return data;
//    }
//
//    public void setData(List<MyExam> data) {
//        this.data = data;
//    }
}
