package com.github.leo_scream.java_se_course.collections

import spock.lang.Specification

/**
 * @author Denis Verkhoturov, mod.satyr@gmail.com
 */
class TreeMapTest extends Specification {
    private Map<Integer, String> map

    void setup() {
        map = new HashMap<>() as Map<Integer, String>
    }

    def "Initialized map is empty"() {
        expect:
        map.isEmpty()
    }

    def "Putting null key throws exception"() {
        when:
        map.put(null, new String())

        then:
        thrown(NullPointerException)
    }

    def "Putting null value accepted"() {
        setup:
        map.put(1, null)
    }
}
