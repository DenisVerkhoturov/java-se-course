package com.github.leo_scream.java_se_course.unit_02.task_07;

import java.lang.annotation.Documented;
import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

/**
 * @author Denis Verkhoturov, mod.satyr@gmail.com
 */
@Target(ElementType.TYPE)
@Retention(RetentionPolicy.RUNTIME)
@Documented
public @interface Radiation {

    public Level level();

    public enum Level {
        NEGLIGIBLE,
        FRIGHTENING
    }
}
