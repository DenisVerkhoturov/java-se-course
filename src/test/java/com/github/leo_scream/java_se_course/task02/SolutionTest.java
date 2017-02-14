package com.github.leo_scream.java_se_course.task02;

import org.junit.Test;

import static org.junit.Assert.*;

/**
 * @author Denis Verkhoturov, mod.satyr@gmail.com
 */
public class SolutionTest
{
    @Test
    public void get() throws Exception
    {
        Solution solution = new Solution();

        assertEquals(1, solution.get(15));
        assertEquals(1, solution.get(0.26));
        assertEquals(2, solution.get(0.25));
        assertEquals(2, solution.get(0.24));
        assertEquals(3, solution.get(0.1));
    }
}
