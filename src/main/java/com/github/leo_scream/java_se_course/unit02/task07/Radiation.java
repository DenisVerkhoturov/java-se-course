package com.github.leo_scream.java_se_course.unit02.task07;

import java.lang.annotation.*;

/**
 * @author Denis Verkhoturov, mod.satyr@gmail.com
 */
@Target(ElementType.TYPE)
@Retention(RetentionPolicy.RUNTIME)
@Documented
public @interface Radiation
{
    public Level level();

    public enum Level
    {
        NEGLIGIBLE,
        FRIGHTENING
    }
}
