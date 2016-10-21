package cn.com.mbmpv.trainingonline.bean;

/**
 * Created by jiuzheyange on 2016/8/18.
 */
public class Question {
    
    String uid;
    String user_id;
    String obj_id;
    String content;
    String cdate;
    String questions_uid;
    String reply_uid;
    String is_teacher;
    UserInfo userInfo;
    String count;
    String num_up;
    String num_down;

    public String getImpower_id() {
        return impower_id;
    }

    public void setImpower_id(String impower_id) {
        this.impower_id = impower_id;
    }

    String impower_id;

    public String getNum_up() {
        return num_up;
    }

    public void setNum_up(String num_up) {
        this.num_up = num_up;
    }

    public String getNum_down() {
        return num_down;
    }

    public void setNum_down(String num_down) {
        this.num_down = num_down;
    }

    public String getUid() {
        return uid;
    }

    public void setUid(String uid) {
        this.uid = uid;
    }

    public String getUser_id() {
        return user_id;
    }

    public void setUser_id(String user_id) {
        this.user_id = user_id;
    }

    public String getObj_id() {
        return obj_id;
    }

    public void setObj_id(String obj_id) {
        this.obj_id = obj_id;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public String getCdate() {
        return cdate;
    }

    public void setCdate(String cdate) {
        this.cdate = cdate;
    }

    public String getQuestions_uid() {
        return questions_uid;
    }

    public void setQuestions_uid(String questions_uid) {
        this.questions_uid = questions_uid;
    }

    public String getReply_uid() {
        return reply_uid;
    }

    public void setReply_uid(String reply_uid) {
        this.reply_uid = reply_uid;
    }

    public String getIs_teacher() {
        return is_teacher;
    }

    public void setIs_teacher(String is_teacher) {
        this.is_teacher = is_teacher;
    }

    public UserInfo getUserInfo() {
        return userInfo;
    }

    public void setUserInfo(UserInfo userInfo) {
        this.userInfo = userInfo;
    }

    public String getCount() {
        return count;
    }

    public void setCount(String count) {
        this.count = count;
    }
}
