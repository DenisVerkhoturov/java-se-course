package com.github.leo_scream.java_se_course.unit_01.task_03;

/**
 * @author Denis Verkhoturov, mod.satyr@gmail.com
 */
public class Solution
{
    private final double step;

    public Solution(double h)
    {
        this.step = Math.abs(h);
    }

    protected double calculate(double x)
    {
        return Math.tan(2 * x) - 3;
    }

    public void onRange(double a, double b)
    {
        final double min = a < b ? a : b;
        final double max = a > b ? a : b;

        System.out.println("|      x     |     F(x)   |");
        System.out.println("|------------|------------|");
        for (double x = min; x <= max; x += step) {
            System.out.format("| %+10.3f | %+10.3f |\n", x, calculate(x));
        }
    }
}
