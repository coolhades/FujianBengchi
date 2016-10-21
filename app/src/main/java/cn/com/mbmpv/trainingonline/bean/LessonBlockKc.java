package cn.com.mbmpv.trainingonline.bean;

import java.util.List;

/**
 * Created by jiuzheyange on 2016/8/13.
 */
public class LessonBlockKc {
    String label;
    Action action;
    List<Lesson> item;

    public String getLabel() {
        return label;
    }

    public void setLabel(String label) {
        this.label = label;
    }

    public Action getAction() {
        return action;
    }

    public void setAction(Action action) {
        this.action = action;
    }

    public List<Lesson> getItem() {
        return item;
    }

    public void setItem(List<Lesson> item) {
        this.item = item;
    }
}
