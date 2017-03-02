package com.github.leo_scream.java_se_course.unit_02.task_05;

/**
 * @author Denis Verkhoturov, mod.satyr@gmail.com
 */
public class Mark<T extends Number>
{
    public final T value;

    public Mark(T value)
    {
        this.value = value;
    }

    @Override
    public String toString()
    {
        return getClass().getSimpleName() + " {value: " + value + "}";
    }
}
