package com.github.leo_scream.java_se_course.unit_01.task_05

import spock.lang.Specification

/**
 * @author Denis Verkhoturov, mod.satyr@gmail.com
 */
class SolutionSpecification extends Specification {
    Solution solution
    int[][] matrix

    def setup() {
        solution = new Solution(6)
        matrix = [
            [1, 0, 0, 0, 0, 1],
            [0, 1, 0, 0, 1, 0],
            [0, 0, 1, 1, 0, 0],
            [0, 0, 1, 1, 0, 0],
            [0, 1, 0, 0, 1, 0],
            [1, 0, 0, 0, 0, 1],
        ]
    }

    def "Check matrix"() {
        expect:
        matrix == solution.getMatrix()
    }
}
