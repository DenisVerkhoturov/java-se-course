package com.github.leo_scream.java_se_course.collections

import spock.lang.Shared
import spock.lang.Specification

abstract class ListSpecification extends Specification {
    protected List<String> list
    @Shared
    protected String[] listElements
    @Shared
    protected notContainedElement

    def setupSpec() {
        listElements = ["apple", null, null, "orange", "tomato", null, "potato"]
        notContainedElement = "planet"
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

    def "Last index of null not throws exception"() {
        setup:
        list.addAll(listElements)

        when:
        list.lastIndexOf(null)

        then:
        notThrown(NullPointerException)
    }

    def "Last index of element which does not contained in list is -1"() {
        setup:
        list.addAll(listElements)

        when:
        list.lastIndexOf(null)

        then:
        notThrown(NullPointerException)
    }

    def "Element can be received by this last index in list"() {
        setup:
        list.addAll(listElements)

        expect:
        list.get(list.lastIndexOf(element)) == element

        where:
        element << listElements
    }

    def "After removing by value size decreases"() {
        setup:
        list.addAll(listElements)

        when:
        list.remove(element)

        then:
        list.size() == old(list.size()) - 1

        where:
        element << listElements
    }

    def "Removing null element not throws NullPointerException"() {
        when:
        list.remove(null)

        then:
        notThrown(NullPointerException)
    }

    def "Removing element which not contained in list returns false"() {
        expect:
        !list.remove(notContainedElement)
    }

    def "After removing list is the same except removed element"() {
        given:
        list.addAll(listElements)

        when:
        list.remove(element)

        then:
        listElements.every({
            item ->
                if (item != element) return list.contains(item)
                else return true
        })

        where:
        element << listElements
    }

    def "After removing by index size decreases"() {
        setup:
        list.addAll(listElements)

        when:
        list.remove(list.indexOf(element))

        then:
        list.size() == old(list.size()) - 1

        where:
        element << listElements
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

    def "Array made from list contains all element"() {
        setup:
        list.addAll(listElements)

        expect:
        list.toArray() == listElements
    }

    def "toArray throws NullPointerException if passed array is null"() {
        when:
        list.toArray(null)

        then:
        thrown(NullPointerException)
    }

    def "toArray throws ArrayStoreException if passed array of wrong type"() {
        given:
        list.addAll(listElements)
        Number[] array = new Number[list.size()]

        when:
        list.toArray(array)

        then:
        thrown(ArrayStoreException)
    }

    def "toArray works properly with array of smaller size"() {
        setup:
        list.addAll(listElements)
        String[] array = list.toArray(new String[0])

        expect:
        list.every(array.&contains)
    }

    def "toArray returns array of all elements"() {
        setup:
        list.addAll(listElements)
        String[] array = list.toArray(new String[list.size()])

        expect:
        list.every(array.&contains)
    }

    def "Cleared list is empty even if it was full before"() {
        given:
        list.addAll(listElements)

        when:
        list.clear()

        then:
        list.isEmpty()

        and:
        list.size() == 0

        and:
        list.toArray().size() == 0
    }
}
