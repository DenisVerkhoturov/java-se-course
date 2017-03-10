package com.github.leo_scream.java_se_course.unit_02.task_02

import spock.lang.Specification

/**
 * @author Denis Verkhoturov, mod.satyr@gmail.com
 */
class AccountantTest extends Specification {
    def "Employee without any stationary"() {
        setup:
        def Accountant manager = new Accountant()
        def Employee employee = new Employee()
        def BigDecimal totalPrice = 0;

        expect:
        manager.calculateStationaryTotalPrice(employee) == totalPrice
    }

    def "Stationary total price of fully equipped employee"() {
        setup:
        def Accountant manager = new Accountant()
        def Employee employee = new Employee()
        def stationary = [
            new Pen(10),
            new Pen(15),
            new Pencil(5),
            new Clip(50),
            new Stapler(150)
        ]
        def BigDecimal totalPrice = 230
        stationary.each { some -> employee.takeStationery(some) }

        expect:
        manager.calculateStationaryTotalPrice(employee) == totalPrice
    }
}
