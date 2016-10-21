package cn.com.mbmpv.trainingonline.bean;

import java.util.List;

/**
 * Created by Hades on 2016/10/19.
 */

public class QuestionReplyListBean {

    /**
     * status : 1
     * message : ok
     * data : {"questions":{"iid":"280","uid":"85BB4286E0DE85DFAF59E085E6D922D2","p_uid":"0","user_id":"1A0052A841F2F904046485BAAD3DF89E","obj_type":"class","obj_id":"3101BF7DD45F40FDA67074AE8A4DB328","content":"外观非常漂亮！","del_flag":"0","cdate":"2016-09-04 21:48:26","questions_uid":"0","reply_uid":"0","is_teacher":"0","num_up":"0","num_down":"0","is_deal":"1","num_reply":"0"},"answer":[{"uid":"BC874CECCB83B7EFD276F6599E27940B","user_id":"FJBC-ONLINE-User-21","obj_id":"3101BF7DD45F40FDA67074AE8A4DB328","content":"您说的很对！~","cdate":"2016-09-05 17:37:48","questions_uid":"85BB4286E0DE85DFAF59E085E6D922D2","reply_uid":"85BB4286E0DE85DFAF59E085E6D922D2","is_teacher":"1","replyUserInfo":{"uid":"FJBC-ONLINE-User-21","nickname":"FBAC_Trainer","avatar":"http://img1.auto-mooc.com/user/avatar/20160902/650BC504192C4D99BA6358FFAE88C46C.png"},"byReplyUserInfo":{"uid":"1A0052A841F2F904046485BAAD3DF89E","nickname":"范伟程","avatar":"http://img1.auto-mooc.com/user/avatar/20160905/3C9DE982E1064B1A990D275623178D9A.png"}}]}
     */

    private int status;
    private String message;
    /**
     * questions : {"iid":"280","uid":"85BB4286E0DE85DFAF59E085E6D922D2","p_uid":"0","user_id":"1A0052A841F2F904046485BAAD3DF89E","obj_type":"class","obj_id":"3101BF7DD45F40FDA67074AE8A4DB328","content":"外观非常漂亮！","del_flag":"0","cdate":"2016-09-04 21:48:26","questions_uid":"0","reply_uid":"0","is_teacher":"0","num_up":"0","num_down":"0","is_deal":"1","num_reply":"0"}
     * answer : [{"uid":"BC874CECCB83B7EFD276F6599E27940B","user_id":"FJBC-ONLINE-User-21","obj_id":"3101BF7DD45F40FDA67074AE8A4DB328","content":"您说的很对！~","cdate":"2016-09-05 17:37:48","questions_uid":"85BB4286E0DE85DFAF59E085E6D922D2","reply_uid":"85BB4286E0DE85DFAF59E085E6D922D2","is_teacher":"1","replyUserInfo":{"uid":"FJBC-ONLINE-User-21","nickname":"FBAC_Trainer","avatar":"http://img1.auto-mooc.com/user/avatar/20160902/650BC504192C4D99BA6358FFAE88C46C.png"},"byReplyUserInfo":{"uid":"1A0052A841F2F904046485BAAD3DF89E","nickname":"范伟程","avatar":"http://img1.auto-mooc.com/user/avatar/20160905/3C9DE982E1064B1A990D275623178D9A.png"}}]
     */

    private DataBean data;

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

    public DataBean getData() {
        return data;
    }

    public void setData(DataBean data) {
        this.data = data;
    }

    public static class DataBean {
        /**
         * iid : 280
         * uid : 85BB4286E0DE85DFAF59E085E6D922D2
         * p_uid : 0
         * user_id : 1A0052A841F2F904046485BAAD3DF89E
         * obj_type : class
         * obj_id : 3101BF7DD45F40FDA67074AE8A4DB328
         * content : 外观非常漂亮！
         * del_flag : 0
         * cdate : 2016-09-04 21:48:26
         * questions_uid : 0
         * reply_uid : 0
         * is_teacher : 0
         * num_up : 0
         * num_down : 0
         * is_deal : 1
         * num_reply : 0
         */

        private QuestionsBean questions;
        /**
         * uid : BC874CECCB83B7EFD276F6599E27940B
         * user_id : FJBC-ONLINE-User-21
         * obj_id : 3101BF7DD45F40FDA67074AE8A4DB328
         * content : 您说的很对！~
         * cdate : 2016-09-05 17:37:48
         * questions_uid : 85BB4286E0DE85DFAF59E085E6D922D2
         * reply_uid : 85BB4286E0DE85DFAF59E085E6D922D2
         * is_teacher : 1
         * replyUserInfo : {"uid":"FJBC-ONLINE-User-21","nickname":"FBAC_Trainer","avatar":"http://img1.auto-mooc.com/user/avatar/20160902/650BC504192C4D99BA6358FFAE88C46C.png"}
         * byReplyUserInfo : {"uid":"1A0052A841F2F904046485BAAD3DF89E","nickname":"范伟程","avatar":"http://img1.auto-mooc.com/user/avatar/20160905/3C9DE982E1064B1A990D275623178D9A.png"}
         */

        private List<AnswerBean> answer;

        public QuestionsBean getQuestions() {
            return questions;
        }

        public void setQuestions(QuestionsBean questions) {
            this.questions = questions;
        }

        public List<AnswerBean> getAnswer() {
            return answer;
        }

        public void setAnswer(List<AnswerBean> answer) {
            this.answer = answer;
        }

        public static class QuestionsBean {
            private String iid;
            private String uid;
            private String p_uid;
            private String user_id;
            private String obj_type;
            private String obj_id;
            private String content;
            private String del_flag;
            private String cdate;
            private String questions_uid;
            private String reply_uid;
            private String is_teacher;
            private String num_up;
            private String num_down;
            private String is_deal;
            private String num_reply;

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

            public String getP_uid() {
                return p_uid;
            }

            public void setP_uid(String p_uid) {
                this.p_uid = p_uid;
            }

            public String getUser_id() {
                return user_id;
            }

            public void setUser_id(String user_id) {
                this.user_id = user_id;
            }

            public String getObj_type() {
                return obj_type;
            }

            public void setObj_type(String obj_type) {
                this.obj_type = obj_type;
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

            public String getDel_flag() {
                return del_flag;
            }

            public void setDel_flag(String del_flag) {
                this.del_flag = del_flag;
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

            public String getIs_deal() {
                return is_deal;
            }

            public void setIs_deal(String is_deal) {
                this.is_deal = is_deal;
            }

            public String getNum_reply() {
                return num_reply;
            }

            public void setNum_reply(String num_reply) {
                this.num_reply = num_reply;
            }
        }

        public static class AnswerBean {
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
             * avatar : http://img1.auto-mooc.com/user/avatar/20160902/650BC504192C4D99BA6358FFAE88C46C.png
             */

            private ReplyUserInfoBean replyUserInfo;
            /**
             * uid : 1A0052A841F2F904046485BAAD3DF89E
             * nickname : 范伟程
             * avatar : http://img1.auto-mooc.com/user/avatar/20160905/3C9DE982E1064B1A990D275623178D9A.png
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
}
