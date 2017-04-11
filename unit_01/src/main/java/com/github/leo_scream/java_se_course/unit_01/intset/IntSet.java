package com.github.leo_scream.java_se_course.unit_01.intset;

import java.util.Arrays;
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
            data = Arrays.copyOf(data, index + 1);
            if (value < 0) {
                negatives = data;
            } else {
                positives = data;
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
        boolean isSubset = true;

        if (this.positives.length > other.positives.length || this.negatives.length > other.negatives.length) {
            isSubset = false;
        }

        for (int i = 0; i < this.positives.length && isSubset; i++) {
            isSubset = isSubset(this.negatives[i], other.negatives[i]);
        }

        for (int i = 0; i < this.negatives.length && isSubset; i++) {
            isSubset = isSubset(this.negatives[i], other.negatives[i]);
        }

        return isSubset;
    }

    /**
     * @param subset set of ints in {@code long} representation
     * @param set    set of ints in {@code long} representation
     * @return {@code true} if all the ints of {@code subset} is in {@code set}
     */
    private boolean isSubset(final long subset, final long set) {
        return (subset & set) == subset;
    }

    /**
     * @param other    {@code IntSet} to zip with
     * @param operator what need to do with every element
     * @return new {@code IntSet} zipped by passed operator
     */
    private IntSet zip(IntSet other, LongBinaryOperator operator) {
        return new IntSet(
            zip(this.positives, other.positives, operator),
            zip(this.negatives, other.negatives, operator)
        );
    }

    /**
     * @param left     set of ints in {@code long} representation
     * @param right    set of ints in {@code long} representation
     * @param operator what need to do with every element
     * @return {@code long} array of {@code left} and {@code right} arrays zipped by {@code operator}
     */
    private long[] zip(final long[] left, final long[] right, final LongBinaryOperator operator) {
        final long[] array = new long[Math.max(left.length, right.length)];
        long leftValue;
        long rightValue;

        for (int i = 0; i < array.length; i++) {
            leftValue = i < left.length ? left[i] : 0L;
            rightValue = i < right.length ? right[i] : 0L;
            array[i] = operator.applyAsLong(leftValue, rightValue);
        }

        return array;
    }
}
