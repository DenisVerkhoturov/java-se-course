package com.github.leo_scream.java_se_course.unit_02.task_04;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Stream;

/**
 * @author Denis Verkhoturov, mod.satyr@gmail.com
 */
public class Employee
{
    private final List<Stationery> stationery = new ArrayList<>();

    public void takeStationery(Stationery stationery)
    {
        this.stationery.add(stationery);
    }

    public Stream<Stationery> stationeries()
    {
        return stationery.stream();
    }
}
