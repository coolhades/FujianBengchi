package cn.com.mbmpv.trainingonline.bean;

import java.util.List;

/**
 * Created by jiuzheyange on 2016/8/13.
 */
public class Lessonblock {
    String type;
    String label;
    String has_action;
    List<Lesson> item;
    Action action;

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public String getLabel() {
        return label;
    }

    public void setLabel(String label) {
        this.label = label;
    }

    public List<Lesson> getItem() {
        return item;
    }

    public void setItem(List<Lesson> item) {
        this.item = item;
    }

    public Action getAction() {
        return action;
    }

    public void setAction(Action action) {
        this.action = action;
    }

    public String getHas_action() {
        return has_action;
    }

    public void setHas_action(String has_action) {
        this.has_action = has_action;
    }
}
