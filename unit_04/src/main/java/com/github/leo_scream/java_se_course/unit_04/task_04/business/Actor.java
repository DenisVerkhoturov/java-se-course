package com.github.leo_scream.java_se_course.unit_04.task_04.business;

import java.io.Serializable;
import java.util.Objects;

/**
 * @author Denis Verkhoturov, mod.satyr@gmail.com
 */
public class Actor implements Serializable {

    private final String name;

    public Actor() {
        name = new String();
    }

    private Actor(String name) {
        this.name = name;
    }

    public Actor withName(String name) {
        Objects.requireNonNull(name);
        return new Actor(name);
    }
}
