package com.github.leo_scream.java_se_course.collections

import spock.lang.Specification

class ListSpecification extends Specification {
    private List<String> list

    def setup() {
        list = new LinkedList()
    }

    def "Initialized list is empty and has zero size"() {
        expect:
        list.isEmpty()

        and:
        list.size() == 0
    }

    def "List contains not throwing exception on null"() {
        when:
        list.contains(null)

        then:
        notThrown(Exception)
    }

    def "Adding null element not throws NullPointerException"() {
        when:
        list.add(null)

        then:
        notThrown(NullPointerException)
    }

    def "After adding list is not empty and size increases"() {
        when:
        list.add(new String())

        then:
        list.size() == old(list.size()) + 1

        and:
        !list.isEmpty()
    }

    def "List contains element after adding"() {
        given:
        String element = "element"

        when:
        list.add(element)

        then:
        list.contains(element)
    }

    def "Removing null element not throws NullPointerException"() {
        when:
        list.remove(null)

        then:
        notThrown(NullPointerException)
    }

    def "Removing by negative index throws IndexOutOfBoundsException"() {
        when:
        list.remove(-1)

        then:
        thrown(IndexOutOfBoundsException)
    }

    def "Removing by index bigger than size throws IndexOutOfBoundsException"() {
        when:
        list.remove(list.size() + 1)

        then:
        thrown(IndexOutOfBoundsException)
    }

    def "After removing by valid index list not contains element"() {
        given:
        String element = "element"
        list.add(element)
        int index = list.indexOf(element)

        when:
        list.remove(index)

        then:
        !list.contains(element)
    }

    def "List is not contains element after removing"() {
        given:
        String element = "element"

        when:
        list.add(element)
        list.remove(element)

        then:
        !list.contains(element)
    }

    def "After removing size decreases"() {
        given:
        String element = "element"
        list.add(element)

        when:
        list.remove(element)

        then:
        list.size() == old(list.size()) - 1
    }

    def "Index of null not throwns exception"() {
        given:
        String element = "element"
        list.add(element)

        when:
        list.indexOf(null)

        then:
        notThrown(NullPointerException)
    }

    def "Index of element which does not contained in list is -1"() {
        setup:
        String element = "element list not contains"

        expect:
        list.indexOf(element) == -1
    }

    def "Element can be received by this index in list"() {
        setup:
        String element = "element"
        list.add(element)
        int index = list.indexOf(element)

        expect:
        list.get(index) == element
    }
}
