package com.github.leo_scream.java_se_course.unit_02.task_02;

import java.math.BigDecimal;

/**
 * @author Denis Verkhoturov, mod.satyr@gmail.com
 */
public class Accountant extends Employee
{
    public BigDecimal calculateStationaryTotalPrice(Employee employee)
    {
        return employee.stationeries()
            .map(Stationery::cost)
            .reduce(BigDecimal.ZERO, BigDecimal::add);
    }
}
