package com.github.leo_scream.java_se_course.unit_03.task_01

import spock.lang.Specification

/**
 * @author Denis Verkhoturov, mod.satyr@gmail.com
 */
class CrazyLoggerTest extends Specification {
    def "Messages is empty if nothing was logged"() {
        setup:
        def logger = new CrazyLogger()

        expect:
        logger.messages().count() == 0
    }

    def "Message logged as string actually can be found in messages"() {
        given:
        def logger = new CrazyLogger()
        def message = "Some strongly informative message"

        when:
        logger.log(message)

        then:
        logger.messages().count() == old(logger.messages().count()) + 1
        logger.messages().anyMatch({
            loggedMessage -> message == loggedMessage.text
        })
    }

    def "Filtering by text"() {
        setup:
        def logger = new CrazyLogger()
        def unexpected = "Must not appears in filtered"
        def expected = "Must appear in filtered"

        logger.log(unexpected)
              .log(expected)
              .log(unexpected)
              .log(expected)
              .log(expected)
              .log(unexpected)
              .log(unexpected)
              .log(unexpected)

        expect:
        logger.messages()
            .filter({
                message -> message.getText().contains("Must appear")
            })
            .count() == 3
    }
}
