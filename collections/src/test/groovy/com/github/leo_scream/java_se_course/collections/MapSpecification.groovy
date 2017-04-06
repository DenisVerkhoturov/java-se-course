package com.github.leo_scream.java_se_course.collections

import spock.lang.Shared
import spock.lang.Specification

/**
 * @author Denis Verkhoturov, mod.satyr@gmail.com
 */
abstract class MapSpecification extends Specification {
    protected Map<Integer, String> map
    @Shared
    protected String[] elements
    @Shared
    protected notContainedValue

    def setupSpec() {
        elements = ["apple", null, null, "orange", "tomato", null, "potato"]
        notContainedValue = "planet"
    }

    def "Initialized map is empty"() {
        expect:
        map.isEmpty()
    }

    def "Initialized map has zero size"() {
        expect:
        map.size() == 0
    }

    def "Map contains method do not throws exception on null key"() {
        when:
        map.containsKey(null)

        then:
        notThrown(Exception)
    }

    def "Map contains method do not throws exception on null value"() {
        when:
        map.containsValue(null)

        then:
        notThrown(Exception)
    }

    def "Adding null value in null key not throws NullPointerException"() {
        when:
        map.put(null, null)

        then:
        notThrown(NullPointerException)
    }

    def "After adding map is not empty"() {
        when:
        map.put(key as Integer, value as String)

        then:
        map.size() == old(map.size()) + 1

        where:
        key << (0..elements.size() - 1)
        value << elements
    }

    def "After adding size increases"() {
        when:
        map.put(key as Integer, value as String)

        then:
        !map.isEmpty()

        where:
        key << (0..elements.size() - 1)
        value << elements
    }

    def "After adding map contains element"() {
        when:
        map.put(key as Integer, value as String)

        then:
        map.containsKey(key)

        and:
        map.containsValue(value)

        where:
        key << (0..elements.size() - 1)
        value << elements
    }

    def "After removing map do not contains element"() {
        given:
        map.put(key as Integer, value as String)

        when:
        map.remove(key)

        then:
        !map.containsKey(key)

        and:
        !map.containsValue(value)

        where:
        key << (0..elements.size() - 1)
        value << elements
    }
}
