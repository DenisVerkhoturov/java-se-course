package com.github.leo_scream.java_se_course.unit_01.task_02;

/**
 * @author Denis Verkhoturov, mod.satyr@gmail.com
 */
public class Solution
{
    public int get(final double epsilon)
    {
        double a;
        int n = 0;

        do {
            n += 1;
            a = 1 / Math.pow((n + 1), 2);
        } while (a >= epsilon);

        return n;
    }
}
