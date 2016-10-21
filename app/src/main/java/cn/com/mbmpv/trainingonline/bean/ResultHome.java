package cn.com.mbmpv.trainingonline.bean;

import java.util.List;

/**
 * Created by jiuzheyange on 2016/8/13.
 */
public class ResultHome {
    List<Banner> banner;
    List<Banner> category;
    List<Lessonblock> block;


    public List<Banner> getBanner() {
        return banner;
    }

    public void setBanner(List<Banner> banner) {
        this.banner = banner;
    }

    public List<Banner> getCategory() {
        return category;
    }

    public void setCategory(List<Banner> category) {
        this.category = category;
    }

    public List<Lessonblock> getBlock() {
        return block;
    }

    public void setBlock(List<Lessonblock> block) {
        this.block = block;
    }
}
