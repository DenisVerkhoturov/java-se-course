package com.github.leo_scream.java_se_course.unit_02.task_03;

import java.math.BigDecimal;

/**
 * @author Denis Verkhoturov, mod.satyr@gmail.com
 */
public abstract class Stationery
{
    private final BigDecimal cost;

    protected Stationery(BigDecimal cost)
    {
        this.cost = cost;
    }

    public final BigDecimal cost()
    {
        return this.cost;
    }
}
