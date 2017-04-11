package com.github.leo_scream.java_se_course.unit_01.intset;

import java.util.function.LongBinaryOperator;

/**
 * @author Denis Verkhoturov, mod.satyr@gmail.com
 */
public class IntSet {

    private long[] positives;
    private long[] negatives;

    private IntSet(long[] positives, long[] negatives) {
        this.positives = positives;
        this.negatives = negatives;
    }

    public IntSet() {
        this(new long[0], new long[0]);
    }

    /**
     * Adds value in set.
     *
     * @param value Value to add
     */
    public void add(final int value) {
        final int index = (value < 0) ? -(value / 64) : value / 64;
        long[] data = (value < 0) ? negatives : positives;

        if (index >= data.length) {
            final long[] copy = new long[index + 1];
            System.arraycopy(data, 0, copy, 0, data.length);
            if (value < 0) {
                negatives = copy;
                data = copy;
            } else {
                positives = copy;
                data = copy;
            }
        }

        data[index] |= 1L << value;
    }

    /**
     * @param values Values to {@link IntSet#add(int) add}
     */
    public void add(final int... values) {
        for (int value : values) {
            this.add(value);
        }
    }

    /**
     * Removes value from set if such value is in set.
     *
     * @param value Value to remove
     */
    public void remove(final int value) {
        final int index = (value < 0) ? -(value / 64) : value / 64;
        long[] data = (value < 0) ? negatives : positives;

        if (index < data.length) {
            data[index] &= ~(1L << value);
        }
    }

    /**
     * @param values Values to {@link IntSet#remove(int) remove}
     */
    public void remove(final int... values) {
        for (int value : values) {
            this.remove(value);
        }
    }

    /**
     * Check if value is in set.
     *
     * @param value Value to check
     * @return <code>true</code> if value is in set, otherwise <code>false</code>
     */
    public boolean contains(final int value) {
        final int index = (value < 0) ? -(value / 64) : value / 64;
        final long[] data = (value < 0) ? negatives : positives;

        return index < data.length && (data[index] & (1L << value)) != 0;

    }

    /**
     * Union current set with another.
     *
     * @param other Set to union with current set
     * @return Returns new instance of <code>IntSet</code> which contains all the element of origin
     * set and <code>other</code> set
     */
    public IntSet union(IntSet other) {
        return zip(other, (left, right) -> left | right);
    }

    /**
     * Calculates intersection.
     *
     * @param other Set to intersect with current set
     * @return Returns new instance of <code>IntSet</code> with all the elements which contains in
     * both sets origin and <code>other</code>
     */
    public IntSet intersection(IntSet other) {
        return zip(other, (left, right) -> left & right);
    }

    /**
     * Calculate difference.
     *
     * @param other Set to difference with current set
     * @return Returns new instance of <code>IntSet</code> with all the elements which contains only
     * in one of sets nor both together
     */
    public IntSet difference(IntSet other) {
        return zip(other, (left, right) -> left ^ right);
    }

    /**
     * Check if current set is subset of <code>other</code>.
     *
     * @param other Set to check on
     * @return Returns <code>true</code> if all the elements of this set is in <code>other</code> set
     */
    public boolean isSubsetOf(IntSet other) {
        long left;
        long right;

        for (int i = 0; i < this.positives.length; i++) {
            left = i < this.positives.length ? this.positives[i] : 0L;
            right = i < other.positives.length ? other.positives[i] : 0L;

            if ((left & right) != left) {
                return false;
            }
        }

        for (int i = 0; i < this.negatives.length; i++) {
            left = i < this.negatives.length ? this.negatives[i] : 0L;
            right = i < other.negatives.length ? other.negatives[i] : 0L;

            if ((left & right) != left) {
                return false;
            }
        }

        return true;
    }

    /**
     * @param other    {@code IntSet} to zip with
     * @param operator what need to do with every element
     * @return new {@code IntSet} zipped by passed operator
     */
    private IntSet zip(IntSet other, LongBinaryOperator operator) {
        final long[] positives = new long[Math.max(this.positives.length, other.positives.length)];
        final long[] negatives = new long[Math.max(this.negatives.length, other.negatives.length)];
        long left;
        long right;

        for (int i = 0; i < positives.length; i++) {
            left = i < this.positives.length ? this.positives[i] : 0L;
            right = i < other.positives.length ? other.positives[i] : 0L;
            positives[i] = operator.applyAsLong(left, right);
        }

        for (int i = 0; i < negatives.length; i++) {
            left = i < this.negatives.length ? this.negatives[i] : 0L;
            right = i < other.negatives.length ? other.negatives[i] : 0L;
            negatives[i] = operator.applyAsLong(left, right);
        }

        return new IntSet(positives, negatives);
    }
}