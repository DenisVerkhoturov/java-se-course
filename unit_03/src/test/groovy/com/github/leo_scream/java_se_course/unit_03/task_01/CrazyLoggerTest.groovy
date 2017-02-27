package com.github.leo_scream.java_se_course.unit_03.task_01

import spock.lang.Specification

import java.util.regex.Pattern

/**
 * @author Denis Verkhoturov, mod.satyr@gmail.com
 */
class CrazyLoggerTest extends Specification {
    def "First and last logged messages of empty logger is empty string"() {
        setup:
        def CrazyLogger logger = new CrazyLogger()

        expect:
        assert logger.firstMessage().equals("")
        assert logger.lastMessage().equals("")
    }

    def "Head and tail of empty logger is empty list"() {
        setup:
        def CrazyLogger logger = new CrazyLogger()

        expect:
        assert logger.tail().isEmpty()
        assert logger.head().isEmpty()
    }

    def "Fist and last messages in logger with one message is same"() {
        setup:
        def CrazyLogger logger = new CrazyLogger()

        expect:
        assert logger.firstMessage().equals(logger.lastMessage())
    }

    def "Logger formats messages properly"() {
        setup:
        def pattern = '^[\\d]{2}-[\\d]{2}-[\\d]{4} : [\\d]{2}-[\\d]{2} — .+;$'
        def CrazyLogger logger = new CrazyLogger()
        logger.log("Hello, world")

        expect:
        assert Pattern.matches(pattern, logger.lastMessage())
    }

    def "Last logged message is actually last"() {
        setup:
        def CrazyLogger logger = new CrazyLogger()
        def message = "Hello, world"
        def pattern =
            '^[\\d]{2}-[\\d]{2}-[\\d]{4} : [\\d]{2}-[\\d]{2} — ' + message + ';$'
        logger.log("Definitely not last message")
        logger.log(message)

        expect:
        assert Pattern.matches(pattern, logger.lastMessage())
    }

    def "First logged message is actually first"() {
        setup:
        def CrazyLogger logger = new CrazyLogger()
        def message = "Hello, world"
        def pattern =
            '^[\\d]{2}-[\\d]{2}-[\\d]{4} : [\\d]{2}-[\\d]{2} — ' + message + ';$'
        logger.log(message)
        logger.log("Definitely not first message")

        expect:
        assert Pattern.matches(pattern, logger.firstMessage())
    }
}
