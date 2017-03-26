package com.github.leo_scream.java_se_course.unit_01.task_06

import spock.lang.Specification;

/**
 * @author Denis Verkhoturov, mod.satyr@gmail.com
 */
class NotebookTest extends Specification {
    Notebook notebook
    Note specialNote

    def setup() {
        notebook = new Notebook()
        (1..10).each { notebook.add(new Note()) }
        specialNote = notebook.get(4)
    }

    def "Initialized notebook has zero size"() {
        expect:
        new Notebook().getSize() == 0
    }

    def "Size changes after adding"() {
        when:
        notebook.add(new Note())

        then:
        notebook.getSize() == old(notebook.size) + 1
    }

    def "Size changes after removing"() {
        when:
        notebook.remove(specialNote)

        then:
        notebook.getSize() == old(notebook.size) - 1
    }

    def "Add method returns exact index of added note"() {
        setup:
        def note = new Note()
        def index = notebook.add(note)

        expect:
        notebook.get(index) == note
    }

    def "Add method throws IllegalArgumentException if passed null"() {
        when:
        notebook.add(null)

        then:
        thrown(IllegalArgumentException)
    }

    def "Removing by valid index"() {
        when:
        notebook.remove(specialNote)

        then:
        (0..notebook.getSize() - 1).each { i -> notebook.get(i) != specialNote }
    }

    def "Removing by negative index throws IndexOutOfBoundsException"() {
        when:
        notebook.remove(-1)

        then:
        thrown(IndexOutOfBoundsException)
    }

    def "Removing by index bigger than size throws IndexOutOfBoundsException"() {
        when:
        notebook.remove(notebook.getSize() * 2)

        then:
        thrown(IndexOutOfBoundsException)
    }
}
