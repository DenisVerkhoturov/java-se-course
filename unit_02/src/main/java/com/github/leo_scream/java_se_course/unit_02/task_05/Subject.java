package com.github.leo_scream.java_se_course.unit_02.task_05;

/**
 * @author Denis Verkhoturov, mod.satyr@gmail.com
 */
public class Subject {

    public final String name;

    public Subject(String name) {
        this.name = name;
    }

    @Override
    public String toString() {
        return getClass().getSimpleName() + "{name: " + name + "}";
    }
}
