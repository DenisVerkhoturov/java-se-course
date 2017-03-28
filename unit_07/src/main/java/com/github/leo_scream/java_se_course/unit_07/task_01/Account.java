package com.github.leo_scream.java_se_course.unit_07.task_01;

import javax.xml.bind.annotation.XmlRootElement;

/**
 * @author Denis Verkhoturov, mod.satyr@gmail.com
 */
@XmlRootElement
public class Account {
    private final long id;
    private volatile long balance;

    public Account(final long id, final long balance) {
        this.id = id;
        this.balance = balance;
    }

    /**
     * @param value
     * @throws IllegalArgumentException if passed {@code value} is negative.
     */
    public void increase(final long value) {
        if (value < 0) throw new IllegalArgumentException("Value can not be negative");
        balance += value;
    }

    /**
     * @param value
     * @throws IllegalArgumentException if passed {@code value} is negative.
     */
    public void decrease(final long value) {
        if (value < 0) throw new IllegalArgumentException("Value can not be negative");
        balance -= value;
    }

    public long getBalance() {
        return balance;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        Account account = (Account) o;

        return id == account.id && balance == account.balance;
    }

    @Override
    public int hashCode() {
        int hash = (int) (balance ^ (balance >>> 32));
        hash = 31 * hash + (int) (id ^ (id >>> 32));
        return hash;
    }
}
