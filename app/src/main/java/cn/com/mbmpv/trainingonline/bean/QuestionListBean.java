package cn.com.mbmpv.trainingonline.bean;

import java.util.List;

/**
 * Created by Hades on 16/8/31.
 */
public class QuestionListBean {


    /**
     * status : 1
     * message : ok
     * data : [{"uid":"60EBFF995E02E9F413593953492A8206","user_id":"FJBC-ONLINE-User-21","obj_id":"8E59441A56334D9D8D342A8EEAA07726","content":"你来测试回答","cdate":"2016-08-30 11:26:07","questions_uid":"C1DE061EF7324C3A9657DA3885C21572","reply_uid":"C1DE061EF7324C3A9657DA3885C21572","is_teacher":"1","replyUserInfo":{"uid":"FJBC-ONLINE-User-21","nickname":"FBAC_Trainer","avatar":"http://img1.auto-mooc.com/user/avatar/20160812/18243139E5B64FD5AF8598DD9AA7FF0A.png"},"byReplyUserInfo":{"uid":"1A0052A841F2F904046485BAAD3DF89E","nickname":"1550刚刚更好5好6","avatar":"http://devimg.auto-mooc.com/user/avatar/2016/08/29/1A0052A841F2F904046485BAAD3DF89E.png"}}]
     */

    private int status;
    private String message;
    /**
     * uid : 60EBFF995E02E9F413593953492A8206
     * user_id : FJBC-ONLINE-User-21
     * obj_id : 8E59441A56334D9D8D342A8EEAA07726
     * content : 你来测试回答
     * cdate : 2016-08-30 11:26:07
     * questions_uid : C1DE061EF7324C3A9657DA3885C21572
     * reply_uid : C1DE061EF7324C3A9657DA3885C21572
     * is_teacher : 1
     * replyUserInfo : {"uid":"FJBC-ONLINE-User-21","nickname":"FBAC_Trainer","avatar":"http://img1.auto-mooc.com/user/avatar/20160812/18243139E5B64FD5AF8598DD9AA7FF0A.png"}
     * byReplyUserInfo : {"uid":"1A0052A841F2F904046485BAAD3DF89E","nickname":"1550刚刚更好5好6","avatar":"http://devimg.auto-mooc.com/user/avatar/2016/08/29/1A0052A841F2F904046485BAAD3DF89E.png"}
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
        private String uid;
        private String user_id;
        private String obj_id;
        private String content;
        private String cdate;
        private String questions_uid;
        private String reply_uid;
        private String is_teacher;
        /**
         * uid : FJBC-ONLINE-User-21
         * nickname : FBAC_Trainer
         * avatar : http://img1.auto-mooc.com/user/avatar/20160812/18243139E5B64FD5AF8598DD9AA7FF0A.png
         */

        private ReplyUserInfoBean replyUserInfo;
        /**
         * uid : 1A0052A841F2F904046485BAAD3DF89E
         * nickname : 1550刚刚更好5好6
         * avatar : http://devimg.auto-mooc.com/user/avatar/2016/08/29/1A0052A841F2F904046485BAAD3DF89E.png
         */

        private ByReplyUserInfoBean byReplyUserInfo;

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

        public ReplyUserInfoBean getReplyUserInfo() {
            return replyUserInfo;
        }

        public void setReplyUserInfo(ReplyUserInfoBean replyUserInfo) {
            this.replyUserInfo = replyUserInfo;
        }

        public ByReplyUserInfoBean getByReplyUserInfo() {
            return byReplyUserInfo;
        }

        public void setByReplyUserInfo(ByReplyUserInfoBean byReplyUserInfo) {
            this.byReplyUserInfo = byReplyUserInfo;
        }

        public static class ReplyUserInfoBean {
            private String uid;
            private String nickname;
            private String avatar;

            public String getUid() {
                return uid;
            }

            public void setUid(String uid) {
                this.uid = uid;
            }

            public String getNickname() {
                return nickname;
            }

            public void setNickname(String nickname) {
                this.nickname = nickname;
            }

            public String getAvatar() {
                return avatar;
            }

            public void setAvatar(String avatar) {
                this.avatar = avatar;
            }
        }

        public static class ByReplyUserInfoBean {
            private String uid;
            private String nickname;
            private String avatar;

            public String getUid() {
                return uid;
            }

            public void setUid(String uid) {
                this.uid = uid;
            }

            public String getNickname() {
                return nickname;
            }

            public void setNickname(String nickname) {
                this.nickname = nickname;
            }

            public String getAvatar() {
                return avatar;
            }

            public void setAvatar(String avatar) {
                this.avatar = avatar;
            }
        }
    }
}
