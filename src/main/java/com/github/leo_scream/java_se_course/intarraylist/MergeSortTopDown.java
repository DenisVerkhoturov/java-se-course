package com.github.leo_scream.java_se_course.intarraylist;

/**
 * @author Denis Verkhoturov, mod.satyr@gmail.com
 */
public class MergeSortTopDown extends AbstractMergeSort
{
    @Override
    public int[] sort(int... values)
    {
        final int[] sorted = new int[values.length];
        final int[] buffer = new int[values.length];
        System.arraycopy(values, 0, sorted, 0, values.length);

        mergeSort(sorted, 0, sorted.length, buffer);

        return sorted;
    }

    private void mergeSort(int[] data, int start, int end, int[] buffer)
    {
        final int length = end - start;
        if (length <= 1) {
            return;
        }

        final int mid = start + length / 2;

        mergeSort(data, start, mid, buffer);
        mergeSort(data, mid, end, buffer);

        merge(data, start, mid, end, buffer);
    }
}
