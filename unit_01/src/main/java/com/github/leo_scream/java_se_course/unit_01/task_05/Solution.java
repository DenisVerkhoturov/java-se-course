package com.github.leo_scream.java_se_course.unit_01.task_05;

/**
 * @author Denis Verkhoturov, mod.satyr@gmail.com
 */
public class Solution {

    private final int[][] matrix;

    public Solution(final int size) {
        this.matrix = new int[size][size];

        for (int i = 0; i < size; i++) {
            this.matrix[i][i] = 1;
            this.matrix[i][size - i - 1] = 1;
        }
    }

    public int[][] getMatrix() {
        return matrix;
    }
}
