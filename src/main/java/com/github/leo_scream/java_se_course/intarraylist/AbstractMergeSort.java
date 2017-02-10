package com.github.leo_scream.java_se_course.intarraylist;

/**
 * @author Denis Verkhoturov, mod.satyr@gmail.com
 */
abstract class AbstractMergeSort
{
    protected void merge(int[] data, int start, int mid, int end, int[] buffer)
    {
        System.arraycopy(data, start, buffer, start, end - start);

        int left = start;
        int right = mid;
        for (int k = start; k < end; k++) {
            if (left >= mid) data[k] = buffer[right++];
            else if (right >= end) data[k] = buffer[left++];
            else if (buffer[left] < buffer[right]) data[k] = buffer[left++];
            else data[k] = buffer[right++];
        }
    }
}
