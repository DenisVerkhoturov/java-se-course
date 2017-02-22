package com.github.leo_scream.java_se_course.unit01.task04;

/**
 * @author Denis Verkhoturov, mod.satyr@gmail.com
 */
public class Solution
{
    public static double get(final double[] values)
    {
        if (values.length < 2) {
            throw new IllegalArgumentException("Array is too short");
        }

        double max = Double.NEGATIVE_INFINITY;

        for (int i = 0; i < values.length / 2; i++) {
            double sum = values[i] + values[values.length - i - 1];
            max = sum > max ? sum : max;
        }

        return max;
    }
}
