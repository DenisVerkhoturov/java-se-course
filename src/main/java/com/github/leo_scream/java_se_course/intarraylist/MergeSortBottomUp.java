package com.github.leo_scream.java_se_course.intarraylist;

/**
 * @author Denis Verkhoturov, mod.satyr@gmail.com
 */
public class MergeSortBottomUp extends AbstractMergeSort implements Sorter
{
    @Override
    public int[] sort(final int... values)
    {
        final int[] sorted = new int[values.length];
        final int[] buffer = new int[values.length];
        System.arraycopy(values, 0, sorted, 0, values.length);

        for (int range = 2; range <= values.length; range *= 2) {
            for (int start = 0; start < values.length; start += range) {
                final int end = start + range;
                final int mid = (start + end) / 2;

                merge(sorted, start, mid, end, buffer);
            }
        }

        return sorted;
    }
}
