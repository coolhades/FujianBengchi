package cn.com.mbmpv.trainingonline.bean;

import java.util.List;

/**
 * Created by Hades on 16/9/7.
 */
public class GongGaoBean {

    /**
     * status : 1
     * message : Ok
     * data : [{"title":"系统更新通知","content":"由于系统升级，微信端及iOS端目前暂时无法使用，苹果用户推荐使用网页版，安卓用户推荐使用安卓APP或网页版。"},{"title":"\u201c福建奔驰培训在线\u201d将于2016年9月3日上午10点开始逐步开放新威霆培训课程。","content":"各位学员：\n\n您好！全新梅赛德斯-奔驰威霆已于2016年9月2日在成都国际车展正式发布上市。作为新威霆产品培训的主要平台，\u201c福建奔驰培训在线\u201d将于2016年9月3日上午10点开始逐步开放新威霆培训课程。\n\n同时， \u201c福建奔驰培训在线\u201d已进行系统升级，此次升级引入积分、排名及积分兑奖机制，具体规则请查看系统说明。\nØ\t积分获取：学员登录签到、观看视频、提问互动、完成考试、社交分享均可获得一定积分；\nØ\t积分排名：月度及年度积分榜前3名将获得一定数额的奖励；\nØ\t积分兑换：一定的积分可兑换一定数额的奖励。\n\n请各位学员及时登录任一终端学习（网页端、微信端、APP端），并在所有章节学习结束后完成考试。\n\n如您在使用\u201c福建奔驰培训在线\u201d时有任何疑问，请随时联系我们：\n邮箱: sales_training@fujianbenz.com\n"}]
     */

    private int status;
    private String message;
    /**
     * title : 系统更新通知
     * content : 由于系统升级，微信端及iOS端目前暂时无法使用，苹果用户推荐使用网页版，安卓用户推荐使用安卓APP或网页版。
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
        private String title;
        private String content;
        private String date_create;


        public String getDate_create() {
            return date_create;
        }

        public void setDate_create(String date_create) {
            this.date_create = date_create;
        }


        public String getTitle() {
            return title;
        }

        public void setTitle(String title) {
            this.title = title;
        }

        public String getContent() {
            return content;
        }

        public void setContent(String content) {
            this.content = content;
        }
    }
}
