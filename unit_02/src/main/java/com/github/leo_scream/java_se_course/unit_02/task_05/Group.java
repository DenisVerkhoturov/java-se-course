package com.github.leo_scream.java_se_course.unit_02.task_05;

import java.util.Map;
import java.util.Optional;
import java.util.Set;
import java.util.function.Function;
import java.util.stream.Collectors;
import java.util.stream.Stream;

/**
 * @author Denis Verkhoturov, mod.satyr@gmail.com
 */
public class Group<T extends Mark>
{
    private final Map<Student, Optional<T>> journal;
    private final Subject subject;

    public Group(Subject subject, Set<? extends Student> students)
    {
        this.subject = subject;
        this.journal = students.stream().collect(
            Collectors.<Student, Student, Optional<T>>toMap(
                Function.identity(),
                student -> Optional.empty()
            )
        );
    }

    public Optional<T> getMarkFor(Student student)
    {
        return journal.get(student);
    }

    public void setMarkFor(Student student, T mark)
    {
        if (isPresent(student)) {
            journal.put(student, Optional.ofNullable(mark));
        }
    }

    public Subject getSubject()
    {
        return this.subject;
    }

    public boolean isPresent(Student student)
    {
        return journal.containsKey(student);
    }

    public void addStudent(Student student)
    {
        this.journal.put(student, null);
    }

    public void removeStudent(Student student)
    {
        this.journal.remove(student);
    }

    public Stream<Map.Entry<Student, Optional<T>>> stream()
    {
        return journal.entrySet().stream();
    }
}
