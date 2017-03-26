package com.github.leo_scream.java_se_course.unit_07.task_03;

import java.util.ArrayList;
import java.util.List;

public class SharedResource {

    private List<Integer> list;

    public SharedResource() {
        list = new ArrayList<>();
    }

    public void setElement(Integer element) {
        list.add(element);
    }

    public Integer getELement() {

        if (list.size() > 0) {
            return list.remove(0);
        }
        return null;
    }
}
