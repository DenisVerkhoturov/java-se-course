package com.github.leo_scream.java_se_course.task05;

/**
 * @author Denis Verkhoturov, mod.satyr@gmail.com
 */
public class Solution
{
    final int[][] matrix;

    public Solution(final int size)
    {
        this.matrix = new int[size][size];

        for (int i = 0; i < size; i++) {
            this.matrix[i][i] = 1;
            this.matrix[i][size - i - 1] = 1;
        }
    }
}
