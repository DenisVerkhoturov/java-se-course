package com.github.leo_scream.java_se_course.unit_01.intarraylist;

import java.util.Arrays;
import java.util.NoSuchElementException;

/**
 * @author Denis Verkhoturov, mod.satyr@gmail.com
 */
public class IntArrayList {

    private int[] data;
    private int size;

    public IntArrayList(int[] data) {
        this.data = Arrays.copyOf(data, data.length);
        size = data.length;
    }

    public IntArrayList() {
        data = new int[10];
        size = 0;
    }

    /**
     * @return Returns count of stored elements
     */
    public int getSize() {
        return size;
    }

    /**
     * @return Array length synonym
     */
    private int getCapacity() {
        return data.length;
    }

    /**
     * Check if data array can get required number of elements.
     *
     * @param requiredCapacity Required length of data array
     */
    private void ensureCapacity(int requiredCapacity) {
        if (requiredCapacity <= getCapacity()) {
            return;
        }
        final int newCapacity = Math.max(requiredCapacity, (getCapacity() * 3) / 2 + 1);
        data = Arrays.copyOf(data, newCapacity);
    }

    /**
     * Adds value to array add updates size.
     *
     * @param value Value to add in array
     */
    public void add(int value) {
        ensureCapacity(size + 1);
        data[size] = value;
        size += 1;
    }

    /**
     * Returns element stored at <code>index</code>.
     *
     * @param index Index of element to return
     * @return Returns the element stored at <code>index</code>
     * @throws IndexOutOfBoundsException if <code>index</code> out of range from zero to size of the
     * list
     */
    public int get(int index) {
        if (index < 0 || index >= size) {
            throw new IndexOutOfBoundsException();
        }

        return data[index];
    }

    /**
     * Method uses recursive way to find a maximum value.
     *
     * @return Returns maximum value of array
     * @throws NoSuchElementException If array has no elements yet
     * @see #getMaxValueRecursive
     */
    public int maxValueInefficient() {
        if (size == 0) {
            throw new NoSuchElementException();
        }
        return getMaxValueRecursive(data, 0, size);
    }

    /**
     * @param data Array to find maximum value
     * @param startInclusive Index to start
     * @param endExclusive Index to finish
     * @return Maximum value of passed {@code data} array on a passed range
     */
    private int getMaxValueRecursive(int[] data, int startInclusive, int endExclusive) {
        final int length = endExclusive - startInclusive;

        if (length == 1) {
            return data[startInclusive];
        } else if (length == 0) {
            return Integer.MIN_VALUE;
        }

        final int mid = startInclusive + length / 2;

        return Math.max(
            getMaxValueRecursive(data, startInclusive, mid),
            getMaxValueRecursive(data, mid, endExclusive)
        );
    }

    /**
     * Sort array using recursive way.
     *
     * @param strategy Object of sorting strategy class
     */
    public void sort(Sorter strategy) {
        data = strategy.sort(this.data);
    }

    /**
     * Expects collection to be sorted.
     *
     * @param value Value to find in collection
     * @return Index of the value or -indexToInsert - 1
     */
    public int binarySearch(int value) {
        int index = -1;

        if (size != 0) {
            int low = 0;
            int mid;
            int high = size;

            while (low < high) {
                mid = (low + high) / 2;
                if (value == data[mid]) {
                    index = mid;
                    break;
                } else {
                    if (value < data[mid]) {
                        high = mid;
                    } else {
                        low = mid + 1;
                    }
                    index = -(mid + 1);
                }
            }
        }

        return index;
    }
}
