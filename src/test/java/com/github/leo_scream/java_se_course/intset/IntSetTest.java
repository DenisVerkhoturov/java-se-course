package com.github.leo_scream.java_se_course.intset;

import org.junit.Test;

import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

/**
 * @author Denis Verkhoturov, mod.satyr@gmail.com
 */
public class IntSetTest
{
    @Test
    public void add() throws Exception
    {
        final IntSet set = new IntSet();
        final int value = Integer.MIN_VALUE;

        assertFalse(set.contains(value));
        set.add(value);
        assertTrue(set.contains(value));
    }

    @Test
    public void addThroughVarArgs() throws Exception
    {
        final IntSet set = new IntSet();
        final int[] values = {Integer.MIN_VALUE, -1, 0, 1, Integer.MAX_VALUE};

        for (int value : values) {
            assertFalse(set.contains(value));
        }

        set.add(values);

        for (int value : values) {
            assertTrue(set.contains(value));
        }
    }

    @Test
    public void remove() throws Exception
    {
        final IntSet set = new IntSet();
        final int value = Integer.MIN_VALUE;

        set.add(value);
        assertTrue(set.contains(value));
        set.remove(value);
        assertFalse(set.contains(value));
    }

    @Test
    public void removeThroughVarArgs() throws Exception
    {
        final IntSet set = new IntSet();
        final int[] values = {Integer.MIN_VALUE, -1, 0, 1, Integer.MAX_VALUE};

        set.add(values);

        for (int value : values) {
            assertTrue(set.contains(value));
        }

        set.remove(values);

        for (int value : values) {
            assertFalse(set.contains(value));
        }
    }

    @Test
    public void contains() throws Exception
    {
        final IntSet set = new IntSet();
        final int[] values = {-512, -1, 0, 42, 63, 1997};

        for (int value : values) {
            assertFalse(set.contains(value));
        }

        for (int value : values) {
            set.add(value);
        }

        unexpected:
        for (int i = Integer.MIN_VALUE; i != Integer.MAX_VALUE; i++) {
            for (int expectedValue : values) {
                if (i == expectedValue) {
                    assertTrue(set.contains(i));
                    continue unexpected;
                }
            }

            assertFalse(set.contains(i));
        }
    }

    @Test
    public void union() throws Exception
    {
        final IntSet set = new IntSet();
        final IntSet other = new IntSet();
        final int[] setValues = {-1997, -512, -1, 0, 42, 63};
        final int[] otherValues = {-2017, -512, -1, 5, 42, 478};
        final int[] expectedValues = {-2017, -1997, -512, -1, 0, 5, 42, 63, 478};

        for (int value : setValues) {
            set.add(value);
        }

        for (int value : otherValues) {
            other.add(value);
        }

        final IntSet unionSet = set.union(other);

        for (int value : expectedValues) {
            assertTrue(unionSet.contains(value));
        }
    }

    @Test
    public void intersection() throws Exception
    {
        final IntSet set = new IntSet();
        final IntSet other = new IntSet();
        final int[] setValues = {-1997, -512, -1, 0, 42, 63};
        final int[] otherValues = {-2017, -512, -1, 5, 42, 478};
        final int[] expectedValues = {-512, -1, 42};

        for (int value : setValues) {
            set.add(value);
        }

        for (int value : otherValues) {
            other.add(value);
        }

        final IntSet unionSet = set.intersection(other);

        for (int value : expectedValues) {
            assertTrue(unionSet.contains(value));
        }
    }

    @Test
    public void difference() throws Exception
    {
        final IntSet set = new IntSet();
        final IntSet other = new IntSet();
        final int[] setValues = {-1997, -512, -1, 0, 42, 63};
        final int[] otherValues = {-2017, -512, -1, 5, 42, 478};
        final int[] expectedValues = {-2017, -1997, 0, 5, 63, 478};

        for (int value : setValues) {
            set.add(value);
        }

        for (int value : otherValues) {
            other.add(value);
        }

        final IntSet unionSet = set.difference(other);

        for (int value : expectedValues) {
            assertTrue(unionSet.contains(value));
        }
    }

    @Test
    public void isSubsetOf() throws Exception
    {
        final IntSet set = new IntSet();
        final IntSet subset = new IntSet();
        final IntSet notSubset = new IntSet();
        final int[] setValues = {-1997, -512, -1, 0, 42, 63};
        final int[] sunsetValues = {-512, -1, 42};
        final int[] notSubsetValues = {-1998, -1997, -512, -1};

        for (int value : setValues) {
            set.add(value);
        }

        for (int value : sunsetValues) {
            subset.add(value);
        }

        for (int value : notSubsetValues) {
            notSubset.add(value);
        }

        assertTrue(subset.isSubsetOf(set));
        assertFalse(notSubset.isSubsetOf(set));
    }
}
