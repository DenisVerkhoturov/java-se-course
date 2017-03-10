package com.github.leo_scream.java_se_course.unit_02.task_04;

import java.util.Arrays;
import java.util.stream.Stream;

/**
 * @author Denis Verkhoturov, mod.satyr@gmail.com
 */
public class StarterKit<T> {

    private final T[] objects;

    public StarterKit(T[] objects) {
        this.objects = objects;
    }

    public Stream<T> objects() {
        return Arrays.stream(objects);
    }
}
