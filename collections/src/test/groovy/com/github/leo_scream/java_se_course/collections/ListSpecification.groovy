package com.github.leo_scream.java_se_course.collections

import spock.lang.Shared
import spock.lang.Specification

class ListSpecification extends Specification {
    private List<String> list
    @Shared
    private String[] listElements
    @Shared
    private notContainedElement

    def setupSpec() {
        listElements = ["apple", null, "orange", null, "tomato", null, "potato"]
        notContainedElement = "planet"
    }

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
        list.add(element)

        then:
        list.size() == old(list.size()) + 1

        and:
        !list.isEmpty()

        where:
        element << listElements
    }

    def "List contains element after adding"() {
        when:
        list.add(element)

        then:
        list.contains(element)

        where:
        element << listElements
    }

    def "List contains all elements after adding"() {
        setup:
        list.addAll(listElements)

        expect:
        list.containsAll(listElements)
    }

    def "Index of null not throws exception"() {
        setup:
        list.addAll(listElements)

        when:
        list.indexOf(null)

        then:
        notThrown(NullPointerException)
    }

    def "Index of element which does not contained in list is -1"() {
        expect:
        list.indexOf(notContainedElement) == -1
    }

    def "Element can be received by this index in list"() {
        setup:
        list.addAll(listElements)

        expect:
        list.get(list.indexOf(element)) == element

        where:
        element << listElements
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

    def "Removing by valid index returns removed element"() {
        setup:
        list.addAll(listElements)

        expect:
        element == list.remove(list.indexOf(element))

        where:
        element << listElements
    }

    def "After removing size decreases"() {
        setup:
        list.addAll(listElements)

        when:
        list.remove(list.indexOf(element))

        then:
        list.size() == old(list.size()) - 1

        where:
        element << listElements
    }
}
