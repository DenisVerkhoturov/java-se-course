package com.github.leo_scream.java_se_course.unit_02.task_05

import spock.lang.Specification

/**
 * @author Denis Verkhoturov, mod.satyr@gmail.com
 */
class CuratorTest extends Specification {
    def "Nonsense api testing"() {
        given:
        Student harry = new Student("Harry Potter");
        Student will = new Student("Will Hunting");
        Student anonymous = new Student("Anonymous raccoon");

        Set<Student> students = new HashSet<>();
        students.add(harry);
        students.add(will);
        students.add(anonymous);

        Subject physics = new Subject("Physics");

        Group<Mark<Integer>> group = new Group<>(physics, students);
        group.setMarkFor(harry, new Mark<>(5));

        group.getMarkFor(harry).ifPresent({
            mark -> System.out.println(harry.toString() + " has " + mark + " for " + physics)
        });
    }
}
