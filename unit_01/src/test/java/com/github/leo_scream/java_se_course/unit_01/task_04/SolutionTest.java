package com.github.leo_scream.java_se_course.unit_01.task_04;

import org.junit.Test;

import static org.junit.Assert.assertEquals;

/**
 * @author Denis Verkhoturov, mod.satyr@gmail.com
 */
public class SolutionTest {

    @Test
    public void get() throws Exception {
        double[] values;

        values = new double[]{0, 1, 2, 3, 4, 5, 6, 7, 8, 9};
        assertEquals(9, Solution.get(values), 0.0);

        values = new double[]{0, Double.NaN, 2, 3, 4, 5, 6, 7, 8, 9};
        assertEquals(9, Solution.get(values), 0.0);

        values = new double[]{0, Double.NEGATIVE_INFINITY, 2, 3, 4, 5, 6, 7, 8, 9};
        assertEquals(9, Solution.get(values), 0.0);

        values = new double[]{0, Double.POSITIVE_INFINITY, 2, 3, 4, 5, 6, 7, 8, 9};
        assertEquals(Double.POSITIVE_INFINITY, Solution.get(values), 0.0);
    }
}
