package com.github.leo_scream.java_se_course.unit_02.task_03;

import java.math.BigDecimal;

/**
 * @author Denis Verkhoturov, mod.satyr@gmail.com
 */
public class Accountant extends Employee
{
    private final StarterKit<Stationery> employeeStarterKit;

    public Accountant()
    {
        this.employeeStarterKit = new StarterKit<>(
            new Pen(BigDecimal.valueOf(15)),
            new Pen(BigDecimal.valueOf(25)),
            new Clip(BigDecimal.valueOf(150)),
            new Stapler(BigDecimal.valueOf(315)),
            new Pencil(BigDecimal.valueOf(75))
        );
    }

    public void provide(Employee employee)
    {
        employeeStarterKit.objects().forEach(employee::takeStationery);
    }

    public BigDecimal calculateStationaryTotalPrice(Employee employee)
    {
        return employee.stationeries()
            .map(Stationery::cost)
            .reduce(BigDecimal.ZERO, BigDecimal::add);
    }
}
