package com.github.leo_scream.java_se_course.unit_02.task_03

import spock.lang.Specification

/**
 * @author Denis Verkhoturov, mod.satyr@gmail.com
 */
class AccountantTest extends Specification {
    def "Employee have no stationary when initialized"() {
        given:
        Employee employee = new Employee()

        expect:
        employee.stationeries().count() == 0
    }

    def "Provided employee have same kit as accountant provides"() {
        given:
        Accountant accountant = new Accountant()
        Employee employee = new Employee()

        when:
        accountant.provide(employee)

        then:
        accountant.@employeeStarterKit.objects().each {
            stationery -> employee.stationeries().anyMatch({ _ == stationery })
        }
    }
}
