package com.github.leo_scream.java_se_course.unit_04.task_04;

import java.io.Serializable;
import java.util.Set;

/**
 * @author Denis Verkhoturov, mod.satyr@gmail.com
 */
public class Movie implements Serializable {
    private String name;
    private Set<Actor> actors;
}
