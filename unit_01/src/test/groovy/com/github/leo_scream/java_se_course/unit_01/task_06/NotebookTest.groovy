package com.github.leo_scream.java_se_course.unit_01.task_06

import spock.lang.Specification;

/**
 * @author Denis Verkhoturov, mod.satyr@gmail.com
 */
class NotebookTest extends Specification {
    def Notebook notebook = new Notebook()
    def Note[] notes = [
        new Note(), new Note(), new Note(), new Note(), new Note()
    ]
    def Note specialNote = notes[1]

    def "Initialized notebook has zero size"() {
        expect:
        notebook.getSize() == 0
    }

    def "Size changes after adding"() {
        setup:
        notebook.add(new Note())

        expect:
        notebook.getSize() == 1
    }

    def "Size changes after removing"() {
        setup:
        notes.each { note -> notebook.add(note) }
        def size = notebook.getSize()

        when:
        notebook.remove(specialNote)

        then:
        notebook.getSize() == size - 1
    }

    def "Add method returns exact index of added note"() {
        setup:
        def Note note = new Note()
        def index = notebook.add(note);

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
        setup:
        notes.each { note -> notebook.add(note) }

        when:
        notebook.remove(specialNote)

        then:
        [1..notebook.getSize()].each { note -> note != specialNote }
    }

    def "Removing by negative index throws IndexOutOfBoundsException"() {
        setup:
        notes.each { note -> notebook.add(note) }

        when:
        notebook.remove(-1)

        then:
        thrown(IndexOutOfBoundsException)
    }

    def "Removing by index bigger than size throws IndexOutOfBoundsException"() {
        setup:
        notes.each { note -> notebook.add(note) }

        when:
        notebook.remove(notebook.getSize() * 2)

        then:
        thrown(IndexOutOfBoundsException)
    }
}
