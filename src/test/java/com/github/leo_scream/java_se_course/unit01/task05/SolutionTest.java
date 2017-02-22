package com.github.leo_scream.java_se_course.unit01.task05;

import org.junit.Test;

import static org.junit.Assert.*;

/**
 * @author Denis Verkhoturov, mod.satyr@gmail.com
 */
public class SolutionTest
{
    @Test
    public void test()
    {
        final int size = 6;
        final Solution solution = new Solution(size);
        final int[][] matrix = {
            {1, 0, 0, 0, 0, 1},
            {0, 1, 0, 0, 1, 0},
            {0, 0, 1, 1, 0, 0},
            {0, 0, 1, 1, 0, 0},
            {0, 1, 0, 0, 1, 0},
            {1, 0, 0, 0, 0, 1},
        };

        for (int y = 0; y < size; y++) {
            for (int x = 0; x < size; x++) {
                assertEquals(matrix[y][x], solution.matrix[y][x]);
            }
        }
    }
}
